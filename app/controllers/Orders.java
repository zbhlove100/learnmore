package controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import play.db.jpa.JPA;
import utils.MyDateUtils;

import models.BaseModel;
import models.Lesson;
import models.Order;
import models.Student;
import models.Teacher.EM_TYPE;
import models.Where;

public class Orders extends CRUD {
    
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
}
