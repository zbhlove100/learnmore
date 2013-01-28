package controllers;

import java.util.List;

import models.BaseModel;
import models.Lesson;
import models.Teacher;
import models.Where;

public class Teachers extends CRUD {
    public static void list(){
        long sunTeacher = Teacher.count("state = ? and employeType = ?", BaseModel.ACTIVE ,Teacher.EM_TYPE.TEACHER.toString());
        long sunAssistant = Teacher.count("state = ? and employeType = ?", BaseModel.ACTIVE ,Teacher.EM_TYPE.ASSISTANT.toString());
        long sunMarketer = Teacher.count("state = ? and employeType = ?", BaseModel.ACTIVE ,Teacher.EM_TYPE.MARKETER.toString());
        renderArgs.put("sunTeacher", sunTeacher);
        renderArgs.put("sunAssistant", sunAssistant);
        renderArgs.put("sunMarketer", sunMarketer);
        /*List<Teacher> teachers = Teacher.find("id = ?", 3l).fetch();
        renderArgs.put("objects",teachers);
        render();*/
        Where where = new Where(params);
        _list(where);
    }
    
    public static void blank(){
        render();
    }
    
    public static void deletes(){
        render();
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
