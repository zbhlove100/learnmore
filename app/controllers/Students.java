package controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import play.Play;
import play.db.jpa.JPA;

import utils.MyDateUtils;

import models.BaseModel;
import models.ImgDetail;
import models.Lesson;
import models.Order;
import models.Setting;
import models.Student;
import models.Where;

public class Students extends CRUD {
    public static void list(){
        List<Student> students = Student.find("state !=?", BaseModel.DELETE).fetch();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        MyDateUtils dmu = new MyDateUtils();
        try {
            for(Student stu:students){
                stu.age = dmu.getAgeForBirthday(stu.birthday,"yyyy-MM-dd");
            }
            renderArgs.put("objects", students);
            render();
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    public static void blank(){
        render();
    }
    
    public static void create(){
        File picture = params.get("picture",File.class);
        String studentname = params.get("studentname");
        String studentbirthday = params.get("studentbirthday");
        String studenttel = params.get("studenttel");
        String studentemail = params.get("studentemail");
        String address = params.get("address");
        String grade = params.get("grade");
        String description = params.get("description");
        
        String uploadFileName = picture.getName();
        String uploadpath = Setting.value("uploadpath", "/public/images/student/");
        
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
        try {
            EntityManager em = JPA.em();
            MyDateUtils mdu = new MyDateUtils();
            Student student = new Student();
            student.name = studentname;
            student.birthday =studentbirthday;
            student.tel = studenttel;
            student.email = studentemail;
            student.location = address;
            student.grade = grade;
            student.age = mdu.getAgeForBirthday(studentbirthday, "yyyy-MM-dd");
            student.description = description;
            student.state = BaseModel.ACTIVE;
            student.save();
            
            em.getTransaction().commit();
            em.getTransaction().begin();
            ImgDetail imgDetail = new ImgDetail();
            imgDetail.basicImg = "/student/"+uploadFileName;
            imgDetail.student = student;
            imgDetail.save();
            
            renderJSON(forwardJsonCloseDailog("studentList","/students/list","添加学员信息成功！"));
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
    
    public static void listDetailMessage(long id){
    }
    
    public static void detail(long id){
        render();
    }
}
