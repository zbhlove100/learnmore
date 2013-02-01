package controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;

import javax.persistence.EntityManager;

import play.Play;
import play.db.jpa.JPA;
import utils.MyDateUtils;

import models.BaseModel;
import models.ImgDetail;
import models.Lesson;
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
        String schoolid = params.get("schoolid");
        String enname = params.get("enname");
        String classType = params.get("classType");
        String bloodtype = params.get("bloodtype");
        int height = params.get("height",Integer.class);
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
        
        String uploadFileName = picture.getName();
        String uploadpath = Setting.value("uploadpath", "/public/images/teacher/");
        int age = 0;
        MyDateUtils mdu = new MyDateUtils();
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
            age = mdu.getAgeForBirthday(birthday, "yyyy-MM-dd");
            EntityManager em = JPA.em();
            School school = School.findById(schoolid);
            
            Teacher teacher = new Teacher();
            teacher.age = age;
            teacher.email = email;
            teacher.name = name;
            teacher.school = school;
            teacher.state = BaseModel.ACTIVE;
            teacher.save();
            em.getTransaction().commit();
            em.getTransaction().begin();
            
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
            teacherDetail.graduateDate = graduateDate;
            teacherDetail.graduateSchool = graduateSchool;
            teacherDetail.height = height;
            teacherDetail.hireDate = hireDate;
            teacherDetail.household = household;
            teacherDetail.interest = interest;
            teacherDetail.qq = qq;
            teacherDetail.sex = sex;
            teacherDetail.summary = summary;
            teacherDetail.teacherWord = teacherWord;
            teacherDetail.tel = tel;
            teacherDetail.weibo = weibo;
            teacherDetail.save();
            
            ImgDetail imgDetail = new ImgDetail();
            imgDetail.basicImg = "/teacher/"+uploadFileName;
            imgDetail.teacher = teacher;
            imgDetail.save();
        } catch (Exception e) {
            // TODO: handle exception
        }
        renderJSON(forwardJsonCloseDailog("createTeacher", "/teachers/list", "创建成功！"));
        
    }
    
    public static void deletes(String ids){
        String where = String.format("id in (%s)", ids);
        List<Teacher> teachers = Teacher.find(where).fetch();
        for(Teacher t : teachers){
            t.state = BaseModel.DELETE;
            t.save();
        }
        renderJSON(forwardJson("teacherlist", "/teachers/list", "删除成功！"));
    }
    
    public static void listDetailMessage(Long id){
        render();
    }
    
    public static void detail(long id){
        Teacher t = Teacher.findById(id);
        List<Lesson> lessons = Lesson.find("teacher = ? and state != ?", t,BaseModel.DELETE).fetch(5);
        renderArgs.put("showTeacher", t);
        renderArgs.put("lessons", lessons);
        render();
    }
}
