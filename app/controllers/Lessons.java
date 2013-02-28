package controllers;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import models.BaseModel;
import models.Code;
import models.Grade;
import models.Lesson;
import models.LessonTable;
import models.Order;
import models.School;
import models.Student;
import models.Teacher;

import com.google.gson.Gson;

public class Lessons extends CRUD {
    public static void list(){
        StringBuffer bf = new StringBuffer("state != '"+ BaseModel.DELETE+"'");
        if (params.get("sname") != null && !"".equals(params.get("sname")))
            bf.append("\n and name like '%"+params.get("sname")+"%'");
        if (params.get("stype") != null && !"".equals(params.get("stype")))
            bf.append(" \n and lessonType like '"+params.get("stype")+"'"); 
        if (params.get("stimeType") != null && !"".equals(params.get("stimeType")))
            bf.append("\n and lessonTimeType like '"+params.get("stimeType")+"'");  
        if (params.get("schoolid") != null && !"".equals(params.get("schoolid")))
            bf.append("\n and school_id = "+params.get("schoolid",Long.class)); 
        if (params.get("scollection") != null && !"".equals(params.get("scollection"))){
            bf.append("\n and collection like '"+params.get("scollection")+"'"); 
            Code pcode = Code.find("codeValue = ? and state !=?", params.get("scollection"),BaseModel.DELETE).first();
            List<Code> subcollections = Code.find("state !=? and parentCode = ?", BaseModel.DELETE,pcode.id).fetch();
            renderArgs.put("subcollections", subcollections);
        }
        if (params.get("subcollection") != null && !"".equals(params.get("subcollection")))
            bf.append("\n and subCollection like '"+params.get("subcollection")+"'"); 
        if ((params.get("sstartDate") != null && !"".equals(params.get("sstartDate")))&& (params.get("sstopDate") != null&& !"".equals(params.get("sstopDate")))){
            String s = String.format("\n and( (startTime between '%s' and '%s') or (endTime between '%s' and '%s') )"
                                     ,params.get("sstartDate")
                                     ,params.get("sstopDate")
                                     ,params.get("sstartDate")
                                     ,params.get("sstopDate")); 
            bf.append(s);
        }else if(params.get("sstartDate") != null&& !"".equals(params.get("sstartDate"))){
            bf.append(String.format("\n and endTime > '%s'",params.get("sstartDate")));
        }else if(params.get("sstopDate") != null&& !"".equals(params.get("sstopDate"))){
            bf.append(String.format("\n and startTime < '%s'",params.get("sstopDate")));
        }
        List<Lesson> lessons = Lesson.find(bf.toString()).fetch();
        List<Code> collections = Code.find("parentCode = ? and state !=? and code_name = ?", Code.ROOT,BaseModel.DELETE,"collection").fetch();
        List<School> schools = School.find("state !=?", BaseModel.DELETE).fetch();
        List<Code> types = Code.find("parentCode = ? and state !=? and code_name = ?", Code.ROOT,BaseModel.DELETE,"lesson_type").fetch();
        List<Code> timeTypes = Code.find("parentCode = ? and state !=? and code_name = ?", Code.ROOT,BaseModel.DELETE,"lesson_time_type").fetch();
        renderArgs.put("schools", schools);
        renderArgs.put("objects", lessons);
        renderArgs.put("collections", collections);
        renderArgs.put("types", types);
        renderArgs.put("timeTypes", timeTypes);
        DWZPageAndOrder(lessons);
        render();
    }
    public static void blank(){
        List<Grade> grades = Grade.findAll();
        List<Code> collections = Code.find("parentCode = ? and state !=? and code_name = ?", Code.ROOT,BaseModel.DELETE,"collection").fetch();
        List<School> schools = School.find("state !=?", BaseModel.DELETE).fetch();
        List<Code> types = Code.find("parentCode = ? and state !=? and code_name = ?", Code.ROOT,BaseModel.DELETE,"lesson_type").fetch();
        List<Code> timeTypes = Code.find("parentCode = ? and state !=? and code_name = ?", Code.ROOT,BaseModel.DELETE,"lesson_time_type").fetch();
        renderArgs.put("grades", grades);
        renderArgs.put("schools", schools);
        renderArgs.put("collections", collections);
        renderArgs.put("types", types);
        renderArgs.put("timeTypes", timeTypes);
        render();
    }
    
    public static void selectLessonTeacher(String relid){
        int pageNum = Integer.parseInt(params.get("pageNum")==null?"1":params.get("pageNum"));
        int numPerPage = getPageSize();
        
        List<Teacher> teachers = Teacher.find("employeType = ?", Teacher.EM_TYPE.TEACHER.toString()).fetch(pageNum,numPerPage);
        Long totalCount =  Teacher.count("employeType = ?", Teacher.EM_TYPE.TEACHER.toString());
        renderArgs.put("pageNum", pageNum);
        renderArgs.put("numPerPage", numPerPage);
        renderArgs.put("totalCount", totalCount);
        renderArgs.put("objects", teachers);
        renderArgs.put("relid", relid);
        render();
    }
    
    public static void create(){
        Lesson lesson = new Lesson();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        School school = School.findById(params.get("schoolid",Long.class));
        try {
            lesson.name = params.get("name");
            lesson.collection = params.get("scollection");
            lesson.subCollection = params.get("subcollection");
            lesson.description = params.get("description");
            lesson.startTime = sdf.parse(params.get("startDate"));
            lesson.endTime = sdf.parse(params.get("stopDate"));
            lesson.lessonTimeType = params.get("stimeType");
            lesson.lessonType = params.get("type");
            lesson.duration = params.get("duration");
            lesson.level = params.get("level");
            lesson.price = params.get("price",Integer.class);
            lesson.times = params.get("times",Integer.class);
            int grade = params.get("grade",Integer.class);
            Grade gradeObj = Grade.find("level = ?", grade).first();
            lesson.grade = gradeObj;
            Teacher teacher = Teacher.findById(params.get("teacherid",Long.class));
            lesson.teacher = teacher;
            lesson.studentNum = 0;
            lesson.state = BaseModel.ACTIVE;
            lesson.school = school;
            lesson.save();
            renderJSON(forwardJsonCloseDailog("lessonsList", "/lessons/list", "创建成功！"));
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        
    }
    
    public static void createLessonTable(long id){
        Lesson lesson = Lesson.findById(id);
        renderArgs.put("lesson", lesson);
        List<List> tables = new ArrayList<List>();
        int mark =1;
        for(int i=1;i<lesson.times;i+=LessonTable.CELL_PER_ROW){
            List<HashMap> tmap = new ArrayList<HashMap>();
            for(int j=0;j<LessonTable.CELL_PER_ROW;j++){
               HashMap m = new HashMap<String, String>();
               m.put("name", String.format("第%d课", mark));
               m.put("mark", mark);
               mark++;
               tmap.add(m);
            }
            tables.add(tmap);
           
        }
        renderArgs.put("lessonTables", tables);
        renderArgs.put("lessonid", id);
        render();
    }
    
    public static void saveLessonTable(long id){
        Lesson lesson = Lesson.findById(id);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        try {
            for(int i=1;i<=lesson.times;i++){
                LessonTable lessonTable = new LessonTable();
                lessonTable.lesson = lesson;
                lessonTable.name = "第"+i+"课";
                if(params.get("lessonDate"+i)!=null&&!"".equals(params.get("lessonDate"+i))){
                    lessonTable.lessonDate = sdf.parse(params.get("lessonDate"+i));
                }else{
                    lessonTable.lessonDate = new Date();
                }
                
                lessonTable.state = BaseModel.ACTIVE;
                lessonTable.save();
            }
            renderJSON(forwardJsonCloseDailog("lessonDetail"+id,"/lessons/detail/"+id,"添加课表成功！"));
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            renderJSON(jsonError(e.getMessage()));
        }
    }
    
    public static void editLessonTable(long id){
        
        try {
            Lesson lesson = Lesson.findById(id);
            List<List> lessonTables = getLessonTable(id);
            List<List> tables = new ArrayList<List>();
            int mark =1;
            int index = 0;
            for(int i=1;i<lesson.times;i+=LessonTable.CELL_PER_ROW){
                List<HashMap> tmap = new ArrayList<HashMap>();
                List<LessonTable> llt = lessonTables.get(index++);
                for(int j=0;j<LessonTable.CELL_PER_ROW;j++){
                   LessonTable lt = llt.get(j);
                   HashMap m = new HashMap<String, String>();
                   m.put("name", String.format("第%d课", mark));
                   m.put("mark", mark);
                   m.put("date", lt.lessonDate);
                   mark++;
                   tmap.add(m);
                }
                tables.add(tmap);
               
            }
            renderArgs.put("lesson", lesson);
            renderArgs.put("lessonTables", tables);
            renderArgs.put("lessonid", id);
            render();
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
       
        
    }
    
    public static void deletes(String ids){
        String where = String.format("id in (%s)", ids);
        List<Lesson> Lessons = Lesson.find(where).fetch();
        for(Lesson s : Lessons){
            s.state = BaseModel.DELETE;
            s.save();
        }
        renderJSON(forwardJson("lessonsList", "/lessons/list", "删除成功！"));
    }
    
    public static void listDetailMessage(long id){
        try {
            renderArgs.put("lessonTables",getLessonTable(id));
            render();
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public static void detail(long id){
        try {
            Lesson lesson = Lesson.findById(id);
            List<List> lessonTables = getLessonTable(id);
            List<Student> students = new ArrayList<Student>();
            List<Order> orders = Order.find("lesson = ? and state = ?", lesson,BaseModel.ACTIVE).fetch();
            for(Order order:orders){
                students.add(order.student);
            }
            renderArgs.put("lessonTables",lessonTables);
            renderArgs.put("lesson",lesson);
            renderArgs.put("orders",orders);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        render();
    }
    
    public static void printLessonStudents(long id){
        Lesson lesson = Lesson.findById(id);
        List<Order> orders = Order.find("lesson = ? and state = ?", lesson,BaseModel.ACTIVE).fetch();
        renderArgs.put("lesson", lesson);
        renderArgs.put("orders", orders);
        render();
    }
    
    public static void edit(long id,String type){
        Lesson lesson = Lesson.findById(id);
        List<Grade> grades = Grade.findAll();
        List<Code> collections = Code.find("parentCode = ? and state !=? and code_name = ?", Code.ROOT,BaseModel.DELETE,"collection").fetch();
        List<School> schools = School.find("state !=?", BaseModel.DELETE).fetch();
        List<Code> types = Code.find("parentCode = ? and state !=? and code_name = ?", Code.ROOT,BaseModel.DELETE,"lesson_type").fetch();
        List<Code> timeTypes = Code.find("parentCode = ? and state !=? and code_name = ?", Code.ROOT,BaseModel.DELETE,"lesson_time_type").fetch();
        renderArgs.put("schools", schools);
        renderArgs.put("grades", grades);
        renderArgs.put("collections", collections);
        renderArgs.put("types", types);
        renderArgs.put("timeTypes", timeTypes);
        renderArgs.put("lesson", lesson);
        renderArgs.put("type", type);
        render();
    }
    
    public static void save(){
        long id = params.get("id",Long.class);
        String type = params.get("rendertype");
        Lesson lesson = Lesson.findById(id);
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        School school = School.findById(params.get("schoolid",Long.class));
        try {
            lesson.name = params.get("name");
            lesson.collection = params.get("scollection");
            lesson.subCollection = params.get("subcollection");
            lesson.description = params.get("description");
            lesson.startTime = sdf.parse(params.get("startDate"));
            lesson.endTime = sdf.parse(params.get("stopDate"));
            lesson.lessonTimeType = params.get("stimeType");
            lesson.lessonType = params.get("type");
            lesson.level = params.get("level");
            lesson.price = params.get("price",Integer.class);
            lesson.times = params.get("times",Integer.class);
            lesson.duration = params.get("duration");
            Teacher teacher = Teacher.findById(params.get("teacherid",Long.class));
            lesson.teacher = teacher;
            int grade = params.get("grade",Integer.class);
            Grade gradeObj = Grade.find("level = ?", grade).first();
            lesson.grade = gradeObj;
            lesson.studentNum = 0;
            lesson.state = BaseModel.ACTIVE;
            lesson.school = school;
            lesson.save();
            if(type.equals("detail")){
                renderJSON(forwardJsonCloseDailog("lessonDetail"+id, "/lessons/detail/"+id, "修改成功！"));
            }else if(type.equals("list")){
                renderJSON(forwardJsonCloseDailog("studentList", "/lessons/list", "修改成功！"));
            }
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    private static List<List> getLessonTable(long id) throws ParseException{
        List<List> lessonTables = new ArrayList<List>();
        long maxLesson = LessonTable.count("lesson.id", id);
        new Date();
        int mark =1;
        for(int i=1;i<maxLesson;i+=LessonTable.CELL_PER_ROW){
            List<LessonTable> tlessonTables = LessonTable.find("lesson.id", id).fetch(mark, LessonTable.CELL_PER_ROW);
            for(LessonTable t:tlessonTables){
               if(t.lessonDate.before(new Date())){
                   t.state = BaseModel.FINISH;        
                }
            }
            lessonTables.add(tlessonTables);
            mark++;
        }
        return lessonTables;
    }
    
    public static void selectPcollection(String pcid){
        List<Map> data =new ArrayList<Map>();
        Code pcode = Code.find("codeValue = ? and state !=?", pcid,BaseModel.DELETE).first();
        List<Code> codes = Code.find("state !=? and parentCode = ?", BaseModel.DELETE,pcode.id).fetch();
        for(Code code:codes){
            Map m = new HashMap<String, String>();
            m.put("id", code.codeName);
            m.put("name", code.codeValue);
            data.add(m);
        }
        Gson gson = new Gson();
        String result = gson.toJson(data);
        response.setContentTypeIfNotSet("application/json; charset=UTF-8");
        try {
            response.out.write(result.getBytes());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
}
