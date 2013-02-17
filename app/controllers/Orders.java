package controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.google.gson.Gson;

import play.db.jpa.JPA;
import utils.MyDateUtils;

import models.BaseModel;
import models.Department;
import models.Lesson;
import models.Order;
import models.Student;
import models.Teacher;
import models.Teacher.EM_TYPE;
import models.Where;

public class Orders extends CRUD {
    public static void list(){
        List<Order> orders = Order.find("state !=?", BaseModel.DELETE).fetch();
        renderArgs.put("orders", orders);
        render();
    }
    
    public static void blank(){
        
    }
    
    public static void quickBlank(long id){
        Lesson lesson = Lesson.findById(id);
        List<Student> students = Student.find("state !=?", BaseModel.DELETE).fetch(100);
        List<Order> orders = Order.find("lesson = ? and state = ?", lesson,BaseModel.ACTIVE).fetch();
        Map<Object,String> orderedStudents =new HashMap<Object, String>();
        for(Order order:orders){
            orderedStudents.put(order.student.id, "true");
        }
        renderArgs.put("lesson", lesson);
        renderArgs.put("students", students);
        renderArgs.put("orderedStudents", orderedStudents);
        render();
    }
    
    public static void searchStudent(){
        Where where = new Where(params);

        if (params.get("searchname") != null)
            where.add("searchname", "name like");
        if (params.get("searchtel") != null)
            where.add("searchtel", "tel like");
        where.add("state !=", BaseModel.DELETE);
        
        List<Student> students = Student.find(where.where(), where.paramsarr()).fetch(100);
        long id = params.get("lessonid",Long.class);
        Lesson lesson = Lesson.findById(id);
        List<Order> orders = Order.find("lesson = ? and state = ?", lesson,BaseModel.ACTIVE).fetch();
        Map<Object,String> orderedStudents =new HashMap<Object, String>();
        for(Order order:orders){
            orderedStudents.put(order.student.id, "true");
        }
        renderArgs.put("orderedStudents", orderedStudents);
        renderArgs.put("students", students);
        render();
    }
    
    public static void quickCreateOrder(){
        long id = params.get("lessonid",Long.class);
        String idstring = params.get("allid");
        int money = params.get("money",Integer.class);
        String description = params.get("orderDescription");
        
        if (idstring !=null && !"".equals(idstring)){
            Lesson lesson = Lesson.findById(id);
            String[] ids =idstring.split(",");
            if(ids.length > 0){
                for(String studentid : ids){
                    Student student = Student.findById(Long.parseLong(studentid));
                    Order order = new Order();
                    order.money = money;
                    order.description = description;
                    order.lesson = lesson;
                    order.student = student;
                    order.teacher = lesson.teacher;
                    order.state = BaseModel.ACTIVE;
                    order.createdAt = new Date(java.lang.System.currentTimeMillis());
                    order.save();
                }
                
            }
           // lesson.studentNum += ids.length;
           //lesson.save();
        }else{
            renderJSON(jsonError("请选择一名学生！"));
        }
        renderJSON(forwardJsonCloseDailog("lessonDetail"+id,"/lessons/detail/"+id,"添加报名信息成功！"));
    }
    
    public static void quickCreateOrderAndStu(){
        long id = params.get("lessonid",Long.class);
        int money = params.get("money",Integer.class);
        String description = params.get("orderDescription");
        Lesson lesson = Lesson.findById(id);
        EntityManager em = JPA.em();
        MyDateUtils mdu = new MyDateUtils();
        String studentname = params.get("studentname");
        String studentbirthday = params.get("studentbirthday");
        String studenttel = params.get("studenttel");
        try {
            Student student = new Student();
            student.name = studentname;
            student.birthday = studentbirthday;
            student.tel = studenttel;
            student.age = mdu.getAgeForBirthday(studentbirthday, "yyyy-MM-dd");
            student.state = BaseModel.ACTIVE;
            student.save();
            em.getTransaction().commit();
            em.getTransaction().begin();
            Order order = new Order();
            order.money = money;
            order.description = description;
            order.lesson = lesson;
            order.student = student;
            order.teacher = lesson.teacher;
            order.state = BaseModel.ACTIVE;
            order.createdAt = new Date(java.lang.System.currentTimeMillis());
            order.save();
            renderJSON(forwardJsonCloseDailog("lessonDetail"+id,"/lessons/detail/"+id,"添加报名信息成功！"));
        } catch (Exception e) {
            // TODO: handle exception
            renderJSON(jsonError("添加失败！"));
        }
    }
    public static void deletes(String ids){
        String where = String.format("id in (%s)", ids);
        List<Order> orders = Order.find(where).fetch();
        for(Order order:orders){
            order.state = BaseModel.DELETE;
            order.save();
        }
        //renderJSON(forwardJson("lessonDetail"+id,"/lessons/detail/"+id,"添加报名信息成功！"));
    }
    
    public static void detail(long id){
        
    }
    
    public static void deletesInLesson(){
        String ids = params.get("ids");
        String lessonid = params.get("lessonid");
        String where = String.format("id in (%s)", ids);
        List<Order> orders = Order.find(where).fetch();
        for(Order order:orders){
            order.state = BaseModel.DELETE;
            order.save();
        }
        renderJSON(forwardJson("lessonDetail"+lessonid,"/lessons/detail/"+lessonid,"删除报名信息成功！"));
    }
    
    public static void statisticsMonth(){
        int months = 12;
        try {
            List<Department> departments = Department.find("type = ? and state !=?"
                                                        , Teacher.EM_TYPE.TEACHER.toString()
                                                        ,BaseModel.DELETE).fetch();
            String toMonth = params.get("toMonth");
            String startMonth = null;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
            String sql = "select CONCAT(YEAR(o.createdAt),-MONTH(o.createdAt)) as month, count(*) as con,d from Order o,Teacher t,Department d" +
                		" where 1=1" +
                        "and o.teacher = t and t.department = d \n";
            if(toMonth==null||"".equals(toMonth)){
                long year = 86400000;
                long yeart = year*365;
                long now = new Date().getTime();
                toMonth = sdf.format(now);
                startMonth = sdf.format(now-yeart);
                sql = sql + "and o.createdAt > '"+startMonth +"' and o.createdAt < '"+toMonth +"'";
            }else{
                
            }
            sql += "group by CONCAT(YEAR(o.createdAt),-MONTH(o.createdAt)),d";        
            Query query = JPA.em().createQuery(sql);
            List l = query.getResultList();
            Iterator i = l.iterator();
            List<Map> sResult = new ArrayList<Map>();
            Date tempDate = sdf.parse(startMonth);
            for(int j=0;j<months;j++){
                Map map = new HashMap<String, Integer>();
                long monthtime = 86400000*30;
                long td = tempDate.getTime() + monthtime*j;
                String year = sdf.format(td);
                map.put("year", year);
                map.put("con", 0);
                for(Department department:departments){
                    map.put("dept", department.name);
                    while(i.hasNext()){
                        Object[] obj = (Object[]) i.next();
                        String dept = ((Department)obj[2]).name;
                        if(year.equals(obj[0])&&department.name.equals(dept)){
                            map.put("con", obj[1]);
                            map.put("dept", (dept));
                        }
                    }
                }
                
                sResult.add(map);
            }
            Gson gson = new Gson();
            renderArgs.put("sResult", gson.toJson(sResult));
            //renderArgs.put("departments", gson.toJson(departments));
            renderArgs.put("months", months);
            render();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
