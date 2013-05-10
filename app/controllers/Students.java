package controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.persistence.EntityManager;

import play.Play;
import play.db.jpa.JPA;

import utils.MyDateUtils;
import utils.StringUtils;

import models.BaseModel;
import models.CurrentUser;
import models.Grade;
import models.ImgDetail;
import models.Lesson;
import models.Order;
import models.OrderHistory;
import models.Setting;
import models.Student;
import models.User;
import models.Where;

public class Students extends CRUD {
    public static void list(){
        Where where = new Where(params);
        if (StringUtils.checkNotNull(params.get("name")))
            where.add("name", "name like");
        if (StringUtils.checkNotNull(params.get("tel")))
            where.add("tel", "tel like");
        if (StringUtils.checkNotNull(params.get("grade")))
            where.addValue("grade.level =",Integer.parseInt(params.get("grade")));
        
        where.addValue("state !=", BaseModel.DELETE);
        List<Student> students = Student.find(where.where(), where.paramsarr()).fetch();
        List<Grade> grades = Grade.findAll();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        MyDateUtils dmu = new MyDateUtils();
        try {
            for(Student stu:students){
                stu.age = dmu.getAgeForBirthday(stu.birthday,"yyyy-MM-dd");
            }
            renderArgs.put("objects", students);
            renderArgs.put("grades", grades);
            DWZPageAndOrder(students);
            render();
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    public static void blank(){
        List<Grade> grades = Grade.findAll();
        render(grades);
    }
    
    public static void create(){
        File picture = params.get("picture",File.class);
        String studentname = params.get("studentname");
        String studentbirthday = params.get("studentbirthday");
        String studenttel = params.get("studenttel");
        String localtel = params.get("localtel");
        String studentemail = params.get("studentemail");
        String address = params.get("address");
        int grade = params.get("grade",Integer.class);
        String description = params.get("description");
        String sex = params.get("sex");
        
        String uploadFileName = UUID.randomUUID().toString()+".jpg";
        String uploadpath = Setting.value("uploadpath", "/public/images/student/");
        
        
        
        try {
            EntityManager em = JPA.em();
            MyDateUtils mdu = new MyDateUtils();
            Student student = new Student();
            student.name = studentname;
            student.birthday =studentbirthday;
            student.tel = studenttel;
            student.localtel = localtel;
            student.email = studentemail;
            student.location = address;
            Grade gradeObj = Grade.find("level = ?", grade).first();
            student.grade = gradeObj;
            student.sex = sex;
            student.age = mdu.getAgeForBirthday(studentbirthday, "yyyy-MM-dd");
            student.description = description;
            student.state = BaseModel.ACTIVE;
            student.createdAt = new Date(java.lang.System.currentTimeMillis());
            student.save();
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
                imgDetail.basicImg = uploadFileName;
                imgDetail.student = student;
                imgDetail.save();
            }
            
            
            renderJSON(forwardJsonCloseDailog("studentList","/students/list","添加学员信息成功！"));
        } catch (Exception e) {
            // TODO: handle exception
        	e.printStackTrace();
        }
    }
    
    public static void listDetailMessage(long id){
    }
    
    public static void detail(long id){
        Student student = Student.findById(id);
        List<Order> orders = Order.find("student = ? and state != ?", student,BaseModel.DELETE).fetch();
        try {
            
            MyDateUtils mdu = new MyDateUtils();
            for(Order o:orders){
                Lesson lesson = o.lesson;
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                lesson.status = mdu.getClassStatus(sdf.format(lesson.startTime),sdf.format(lesson.endTime),"yyyy-MM-dd");
            }
            renderArgs.put("orders", orders);
            renderArgs.put("student", student);
            render();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        
    }
    
    public static void edit(long id,String type){
        Student student = Student.findById(id);
        List<Grade> grades = Grade.findAll();
        renderArgs.put("type", type);
        renderArgs.put("student", student);
        renderArgs.put("grades", grades);
        render();
    }
    
    public static void save(){
        long id = params.get("id",Long.class);
        String type = params.get("type");
        Student student = Student.findById(id);
        try {
            
            File picture = params.get("picture",File.class);
            String studentname = params.get("studentname");
            String studentbirthday = params.get("studentbirthday");
            String studenttel = params.get("studenttel");
            String localtel = params.get("localtel");
            String studentemail = params.get("studentemail");
            String address = params.get("address");
            int grade = params.get("grade",Integer.class);
            String description = params.get("description");
            String sex = params.get("sex");
            
            MyDateUtils mdu = new MyDateUtils();
            student.name = studentname;
            student.birthday =studentbirthday;
            student.tel = studenttel;
            student.localtel = localtel;
            student.email = studentemail;
            student.location = address;
            Grade gradeObj = Grade.find("level = ?", grade).first();
            student.grade = gradeObj;
            student.sex = sex;
            student.age = mdu.getAgeForBirthday(studentbirthday, "yyyy-MM-dd");
            student.description = description;
            student.state = BaseModel.ACTIVE;
            student.createdAt = new Date(java.lang.System.currentTimeMillis());
            student.save();
            
            if(picture !=null&&picture.length()!=0){
                ImgDetail imgDetail = ImgDetail.find("student = ? and state !=?", student,BaseModel.DELETE).first();
                String uploadFileName = UUID.randomUUID().toString()+".jpg";
                String uploadpath = Setting.value("uploadpath", "/public/images/student/");
                if(imgDetail != null){
                    File file = new File(Play.applicationPath.getPath()+uploadpath+imgDetail.basicImg);
                    file.delete();
                }else{
                    imgDetail = new ImgDetail();
                    imgDetail.student = student;
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
            if(type.equals("detail")){
                renderJSON(forwardJsonCloseDailog("studentDetail"+id, "/students/detail/"+id, "修改成功！"));
            }else if(type.equals("list")){
                renderJSON(forwardJsonCloseDailog("studentList", "/students/list", "修改成功！"));
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }
            
    public static void deletes(String ids){
        String where = String.format("id in (%s)", ids);
        List<Student> students = Student.find(where).fetch();
        for(Student s : students){
            s.state = BaseModel.DELETE;
            s.removedAt = new Date(java.lang.System.currentTimeMillis());
            s.save();
            List<Order> orders = Order.find("student = ?", s).fetch();
            for(Order order :orders){
                System.out.println("state======================================>"+order.state);
                if(order.state.equals(BaseModel.ACTIVE)){
                    
                    order.lesson.studentNum--;
                    order.lesson.save(); 
                }
                order.state = BaseModel.DELETE;
                order.save();
                OrderHistory orderHistory = new OrderHistory();
                orderHistory.createdAt = new Date(java.lang.System.currentTimeMillis());
                orderHistory.name = order.name;
                orderHistory.money = order.money;
                orderHistory.description = "删除学生";
                orderHistory.user = User.findById(CurrentUser.current().id);
                orderHistory.optype = Setting.value("ORDER_DELETE","删除");
                orderHistory.student = order.student;
                orderHistory.order_message = order;
                orderHistory.save();
                
            }
            ImgDetail imgDetail = ImgDetail.find("student = ? and state !=?", s,BaseModel.DELETE).first();
            if(imgDetail!=null){
                imgDetail.state = BaseModel.DELETE;
                imgDetail.save();
            }
        }
        renderJSON(forwardJson("studentList", "/students/list", "删除成功！"));
    }
    
    public static void applyClass(long id){
        Student student = Student.findById(id);
        List<Lesson> lessons = Lesson.find("state = ? and endTime > NOW() ", BaseModel.ACTIVE).fetch();
        System.out.println("---------------------->"+lessons.size());
        render(student,lessons);
    }
}
