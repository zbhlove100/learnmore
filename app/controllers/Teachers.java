package controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.persistence.EntityManager;

import com.google.gson.Gson;

import play.Play;
import play.db.jpa.JPA;
import utils.MyDateUtils;

import models.BaseModel;
import models.ImgDetail;
import models.Lesson;
import models.LessonTable;
import models.School;
import models.Setting;
import models.Student;
import models.Teacher;
import models.TeacherDetail;
import models.Where;

public class Teachers extends CRUD {
    public static void list(){
        long sunTeacher = Teacher.count("state = ? and employeType = ?", BaseModel.ACTIVE ,Teacher.EM_TYPE.TEACHER.toString());
        long sunAssistant = Teacher.count("state = ? and employeType = ?", BaseModel.ACTIVE ,Teacher.EM_TYPE.ASSISTANT.toString());
        long sunMarketer = Teacher.count("state = ? and employeType = ?", BaseModel.ACTIVE ,Teacher.EM_TYPE.MARKETER.toString());
        List<School> schools = School.find("state !=?", BaseModel.DELETE).fetch();
        List<String> employeTypes = Setting.values("EMPLOYE_TYPE");
        renderArgs.put("schools", schools);
        renderArgs.put("employeTypes", employeTypes);
        renderArgs.put("sunTeacher", sunTeacher);
        renderArgs.put("sunAssistant", sunAssistant);
        renderArgs.put("sunMarketer", sunMarketer);
        /*List<Teacher> teachers = Teacher.find("id = ?", 3l).fetch();
        renderArgs.put("objects",teachers);
        render();*/
        Where where = new Where(params);
        if (params.get("name") != null)
            where.add("name", "name like");
        if (params.get("type") != null)
            where.add("type", "employeType like");
        if (params.get("schoolid") != null)
            where.add("schoolid", "school_id =");
        where.add("state !=", BaseModel.DELETE);
        _list(where);
    }
    
    public static void blank(){
        List<School> schools = School.find("state !=?", BaseModel.DELETE).fetch();
        List<String> employeTypes = Setting.values("EMPLOYE_TYPE");
        renderArgs.put("schools", schools);
        renderArgs.put("employeTypes", employeTypes);
        render();
    }
    
    public static void create(){
        String name = params.get("name");
        String sex = params.get("sex");
        String birthday = params.get("birthday");
        String tel = params.get("tel");
        String email = params.get("email");
        String type = params.get("type");
        long schoolid = params.get("schoolid",Long.class);
        String enname = params.get("enname");
        String classType = params.get("classType");
        String bloodtype = params.get("bloodtype");
        String heightString = params.get("height");
        int height = (heightString == null || "".equals(heightString))?0:Integer.parseInt(heightString);
        String interest = params.get("interest");
        String favoriteColor = params.get("favoriteColor");
        String favoriteSport = params.get("favoriteSport");
        String favoriteAnimal = params.get("favoriteAnimal");
        String favoritePlace = params.get("favoritePlace");
        String adoreMan = params.get("adoreMan");
        String qq = params.get("qq");
        String weibo = params.get("weibo");
        String address = params.get("address");
        String household = params.get("household");
        String education = params.get("education");
        String graduateSchool = params.get("graduateSchool");
        String graduateDate = params.get("graduateDate");
        String hireDate = params.get("hireDate");
        String summary = params.get("summary");
        String teacherWord = params.get("teacherWord");
        File picture = params.get("picture",File.class);
        
        String uploadFileName = UUID.randomUUID().toString()+".jpg";;
        String uploadpath = Setting.value("uploadpath", "/public/images/teacher/");
        int age = 0;
        MyDateUtils mdu = new MyDateUtils();
        
        try {
            age = mdu.getAgeForBirthday(birthday, "yyyy-MM-dd");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            School school = School.findById(schoolid);
            
            Teacher teacher = new Teacher();
            teacher.age = age;
            teacher.email = email;
            teacher.name = name;
            teacher.school = school;
            teacher.state = BaseModel.ACTIVE;
            teacher.employeType = type;
            teacher.createdAt = new Date(java.lang.System.currentTimeMillis());
            teacher.save();
            
            TeacherDetail teacherDetail = new TeacherDetail();
            teacherDetail.address = address;
            teacherDetail.adoreMan = adoreMan;
            teacherDetail.birthday = birthday;
            teacherDetail.bloodtype = bloodtype;
            teacherDetail.classType = classType;
            teacherDetail.education = education;
            teacherDetail.email = email;
            teacherDetail.enName = enname;
            teacherDetail.favoriteAnimal = favoriteAnimal;
            teacherDetail.favoriteColor = favoriteColor;
            teacherDetail.favoritePlace = favoritePlace;
            teacherDetail.favoriteSport = favoriteSport;
            if(graduateDate==null||"".equals(graduateDate)){
                teacherDetail.graduateDate = new Date(java.lang.System.currentTimeMillis());
            }else{
                teacherDetail.graduateDate = sdf.parse(graduateDate);
            }
            if(hireDate==null||"".equals(hireDate)){
                teacherDetail.hireDate = new Date(java.lang.System.currentTimeMillis());
            }else{
                teacherDetail.hireDate = sdf.parse(hireDate);
            }    
            
            teacherDetail.graduateSchool = graduateSchool;
            teacherDetail.height = height;
            
            teacherDetail.household = household;
            teacherDetail.interest = interest;
            teacherDetail.qq = qq;
            teacherDetail.sex = sex;
            teacherDetail.summary = summary;
            teacherDetail.teacherWord = teacherWord;
            teacherDetail.tel = tel;
            teacherDetail.weibo = weibo;
            teacherDetail.teacher = teacher;
            teacherDetail.save();
            File file = new File(Play.applicationPath.getPath()+uploadpath+uploadFileName);
            if(picture !=null&&picture.length()!=0){
            	FileInputStream is = null;
                FileOutputStream os = null;
                try {
                    is = new FileInputStream(picture);
                    os = new FileOutputStream(file);
                    int read;
                    byte[] buffer = new byte[1024*1024*8];
                    while ((read = is.read(buffer)) > 0) {
                        os.write(buffer, 0, read);
                        os.flush();
                    }
                    
                } catch (Exception e) {
                    throw new RuntimeException(e);
                } finally {
                    try {
                        is.close();
                    } catch (Exception ignored) {
                    }
                    try {
                        os.close();
                    } catch (Exception ignored) {
                    }
                }
                ImgDetail imgDetail = new ImgDetail();
                imgDetail.basicImg = "/teacher/"+uploadFileName;
                imgDetail.teacher = teacher;
                imgDetail.save();
            }
            renderJSON(forwardJsonCloseDailog("teacherlist", "/teachers/list", "创建成功！"));
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        
        
    }
    
    public static void save(long id){
        String name = params.get("name");
        String sex = params.get("sex");
        String birthday = params.get("birthday");
        String tel = params.get("tel");
        String email = params.get("email");
        String type = params.get("type");
        long schoolid = params.get("schoolid",Long.class);
        String enname = params.get("enname");
        String classType = params.get("classType");
        String bloodtype = params.get("bloodtype");
        String heightString = params.get("height");
        int height = (heightString == null || "".equals(heightString))?0:Integer.parseInt(heightString);
        String interest = params.get("interest");
        String favoriteColor = params.get("favoriteColor");
        String favoriteSport = params.get("favoriteSport");
        String favoriteAnimal = params.get("favoriteAnimal");
        String favoritePlace = params.get("favoritePlace");
        String adoreMan = params.get("adoreMan");
        String qq = params.get("qq");
        String weibo = params.get("weibo");
        String address = params.get("address");
        String household = params.get("household");
        String education = params.get("education");
        String graduateSchool = params.get("graduateSchool");
        String graduateDate = params.get("graduateDate");
        String hireDate = params.get("hireDate");
        String summary = params.get("summary");
        String teacherWord = params.get("teacherWord");
        try {
            int age = 0;
            MyDateUtils mdu = new MyDateUtils();
            age = mdu.getAgeForBirthday(birthday, "yyyy-MM-dd");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            School school = School.findById(schoolid);
            
            Teacher teacher = Teacher.findById(id);
            teacher.age = age;
            teacher.email = email;
            teacher.name = name;
            teacher.school = school;
            teacher.state = BaseModel.ACTIVE;
            teacher.employeType = type;
            teacher.createdAt = new Date(java.lang.System.currentTimeMillis());
            teacher.save();
            
            TeacherDetail teacherDetail = TeacherDetail.find("teacher = ?", teacher).first();
            if(teacherDetail==null)
                teacherDetail = new TeacherDetail();
            teacherDetail.address = address;
            teacherDetail.adoreMan = adoreMan;
            teacherDetail.birthday = birthday;
            teacherDetail.bloodtype = bloodtype;
            teacherDetail.classType = classType;
            teacherDetail.education = education;
            teacherDetail.email = email;
            teacherDetail.enName = enname;
            teacherDetail.favoriteAnimal = favoriteAnimal;
            teacherDetail.favoriteColor = favoriteColor;
            teacherDetail.favoritePlace = favoritePlace;
            teacherDetail.favoriteSport = favoriteSport;
            teacherDetail.graduateDate = sdf.parse(graduateDate);
            teacherDetail.graduateSchool = graduateSchool;
            teacherDetail.height = height;
            teacherDetail.hireDate = sdf.parse(hireDate);
            teacherDetail.household = household;
            teacherDetail.interest = interest;
            teacherDetail.qq = qq;
            teacherDetail.sex = sex;
            teacherDetail.summary = summary;
            teacherDetail.teacherWord = teacherWord;
            teacherDetail.tel = tel;
            teacherDetail.weibo = weibo;
            teacherDetail.teacher = teacher;
            teacherDetail.save();
            
            File picture = params.get("picture",File.class);
            if(picture !=null&&picture.length()!=0){
                ImgDetail imgDetail = ImgDetail.find("teacher = ? and state !=?", teacher,BaseModel.DELETE).first();
                String uploadFileName = UUID.randomUUID().toString()+".jpg";
                String uploadpath = Setting.value("uploadpath", "/public/images/teacher/");
                if(imgDetail != null){
                    File file = new File(Play.applicationPath.getPath()+uploadpath+imgDetail.basicImg);
                    file.delete();
                }else{
                    imgDetail = new ImgDetail();
                    imgDetail.teacher = teacher;
                    imgDetail.state = BaseModel.ACTIVE;
                }
                
                
                File file = new File(Play.applicationPath.getPath()+uploadpath+uploadFileName);
                FileInputStream is = null;
                FileOutputStream os = null;
                try {
                    is = new FileInputStream(picture);
                    os = new FileOutputStream(file);
                    int read;
                    byte[] buffer = new byte[1024*1024*8];
                    while ((read = is.read(buffer)) > 0) {
                        os.write(buffer, 0, read);
                        os.flush();
                    }
                    
                } catch (Exception e) {
                    throw new RuntimeException(e);
                } finally {
                    try {
                        is.close();
                    } catch (Exception ignored) {
                    }
                    try {
                        os.close();
                    } catch (Exception ignored) {
                    }
                }
                imgDetail.basicImg = uploadFileName;
                imgDetail.save();
            }
            
            
            renderJSON(forwardJsonCloseDailog("createTeacher", "/teachers/list", "创建成功！"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        
    }
    public static void deletes(String ids){
        String where = String.format("id in (%s)", ids);
        List<Teacher> teachers = Teacher.find(where).fetch();
        for(Teacher t : teachers){
            t.state = BaseModel.DELETE;
            t.removedAt = new Date(java.lang.System.currentTimeMillis());
            t.save();
        }
        renderJSON(forwardJson("teacherlist", "/teachers/list", "删除成功！"));
    }
    
    public static void listDetailMessage(Long id){
        render();
    }
    
    public static void detail(long id){
        try {
            Teacher t = Teacher.findById(id);
            Map workdate = MyDateUtils.getYearAndMonthSinceNow(t.teacherDetail.hireDate, "yyyy-MM-dd");
            List<Lesson> lessons = Lesson.find("teacher = ? and state != ?", t,BaseModel.DELETE).fetch();
            List<HashMap<String, Object>> calendarSource = new ArrayList<HashMap<String,Object>>();
            /*for(Lesson lesson:lessons){
                List<LessonTable> lessonTables = LessonTable.find("lesson = ?", lesson).fetch();
                for(LessonTable lessonTable:lessonTables){
                    HashMap<String, Object> tmap = new HashMap<String, Object>();
                    tmap.put("title", lesson.name+lessonTable.name);
                    tmap.put("allDay", false);
                    tmap.put("start", lessonTable.lessonDate);
                    tmap.put("id", lessonTable.id);
                    Float fvalue = new Float(lesson.duration*60);
                    int mins =fvalue.intValue();
                    long Time=(lessonTable.lessonDate.getTime()/1000)+60*mins;
                    Date endDate = new Date(Time*1000);
                    tmap.put("end", endDate);
                    tmap.put("mark", lessonTable.mark);
                    int hour = lessonTable.lessonDate.getHours();
                    if(hour<12){
                        tmap.put("color", "#4EE387"); 
                    }else if(hour<17&&hour>=12){
                        tmap.put("color", "#E15B36");
                    }else if(hour<24&&hour>=17){
                        tmap.put("color", "#B235E0"); 
                    }
                    calendarSource.add(tmap);
                }
                
            }*/
            Gson gson = new Gson();
            renderArgs.put("showTeacher", t);
            //renderArgs.put("calendarSource", gson.toJson(calendarSource));
            renderArgs.put("lessons", lessons);
            renderArgs.put("workdate", workdate);
            render();
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    
    public static void teacherCalender(long id){
        Teacher t = Teacher.findById(id);
        renderArgs.put("showTeacher", t);
        render();
    }
    
    public static void getLessonTable(long id){
        Teacher t = Teacher.findById(id);
        List<Lesson> lessons = Lesson.find("teacher = ? and state != ?", t,BaseModel.DELETE).fetch();
        List<HashMap<String, Object>> calendarSource = new ArrayList<HashMap<String,Object>>();
        //List<LessonTable> lessonTables = LessonTable.find("lesson = ?", lesson).fetch();
        for(Lesson lesson:lessons){
            List<LessonTable> lessonTables = LessonTable.find("lesson = ?", lesson).fetch();
            for(LessonTable lessonTable:lessonTables){
                HashMap<String, Object> tmap = new HashMap<String, Object>();
                tmap.put("title", lesson.name+lessonTable.name);
                tmap.put("allDay", false);
                tmap.put("start", lessonTable.lessonDate);
                tmap.put("id", lessonTable.id);
                Float fvalue = new Float(lesson.duration*60);
                int mins =fvalue.intValue();
                long Time=(lessonTable.lessonDate.getTime()/1000)+60*mins;
                Date endDate = new Date(Time*1000);
                tmap.put("end", endDate);
                tmap.put("mark", lessonTable.mark);
                int hour = lessonTable.lessonDate.getHours();
                if(hour<12){
                    tmap.put("color", "#4EE387"); 
                }else if(hour<17&&hour>=12){
                    tmap.put("color", "#E15B36");
                }else if(hour<24&&hour>=17){
                    tmap.put("color", "#B235E0"); 
                }
                calendarSource.add(tmap);
            }
        }
            
        Gson gson = new Gson();
        renderJSON(gson.toJson(calendarSource));
    }
    
    public static void edit(long id){
        Teacher teacher = Teacher.findById(id);
        List<School> schools = School.find("state !=?", BaseModel.DELETE).fetch();
        List<String> employeTypes = Setting.values("EMPLOYE_TYPE");
        renderArgs.put("schools", schools);
        renderArgs.put("employeTypes", employeTypes);
        renderArgs.put("teacher", teacher);
        render();
    }
}
