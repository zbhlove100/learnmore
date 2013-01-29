package controllers;

import java.text.ParseException;
import java.util.List;

import models.BaseModel;
import models.Lesson;
import models.Student;
import models.Where;

public class Students extends CRUD {
    public static void list(){
        List<Student> students = Student.find("state !=?", BaseModel.DELETE).fetch();
        renderArgs.put("objects", students);
        render();
    }
    public static void blank(){
        render();
    }
    
    public static void quickBlank(long id){
        Lesson lesson = Lesson.findById(id);
        List<Student> students = Student.find("state !=?", BaseModel.DELETE).fetch(100);
        renderArgs.put("lesson", lesson);
        renderArgs.put("students", students);
        render();
    }
    public static void listDetailMessage(long id){
    }
    
    public static void searchStudent(){
        Where where = new Where(params);

        if (params.get("studentname") != null)
            where.add("studentname", "name like");
        if (params.get("studenttel") != null)
            where.add("studenttel", "tel like");
        where.add("state !=", BaseModel.DELETE);
        
        List<Student> students = Student.find(where.where(), where.paramsarr()).fetch(100);
        renderArgs.put("students", students);
        render();
    }
    
    public static void quickCreate(){
    }
    
    public static void detail(long id){
        render();
    }
}
