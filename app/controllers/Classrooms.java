package controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import utils.MyDateUtils;

import com.google.gson.Gson;

import models.BaseModel;
import models.Classroom;
import models.Lesson;
import models.LessonTable;
import models.School;
import models.Setting;
import models.Teacher;
import models.Where;

public class Classrooms extends CRUD{
    public static void list(){
        Where where = new Where(params);
        if (params.get("name") != null)
            where.add("name", "name like");
        if (params.get("schoolid") != null)
            where.add("schoolid", "school_id =");
        where.add("state !=", BaseModel.DELETE);
        
        List<School> schools = School.find("state !=?", BaseModel.DELETE).fetch();
        renderArgs.put("schools", schools);
        _list(where);
    }
    
    public static void blank(){
        List<School> schools = School.find("state !=?", BaseModel.DELETE).fetch();
        renderArgs.put("schools", schools);
        render();
    }
    
    public static void create(){
        Classroom classroom = new Classroom();
        classroom.name = params.get("name");
        classroom.size = params.get("size");
        classroom.volume = params.get("volume",Integer.class);
        classroom.description = params.get("description");
        classroom.state = BaseModel.ACTIVE;
        classroom.createdAt = new Date(java.lang.System.currentTimeMillis());
        School school = School.findById(params.get("schoolid",Long.class));
        classroom.school = school;
        classroom.save();
        renderJSON(forwardJsonCloseDailog("classroomsList","/classroomsList/list","添加教室信息成功！"));
    }
    
    public static void detail(long id){
        Classroom classroom = Classroom.findById(id);
        List<Lesson> lessons = Lesson.find("classroom = ? and state != ?", classroom,BaseModel.DELETE).fetch();
        List<HashMap<String, Object>> calendarSource = new ArrayList<HashMap<String,Object>>();
        for(Lesson lesson:lessons){
            List<LessonTable> lessonTables = LessonTable.find("lesson = ?", lesson).fetch();
            for(LessonTable lessonTable:lessonTables){
                HashMap<String, Object> tmap = new HashMap<String, Object>();
                tmap.put("title", lesson.name+lessonTable.name);
                tmap.put("allDay", false);
                tmap.put("start", lessonTable.lessonDate);
                Date endDate =new Date((long) (lessonTable.lessonDate.getTime() - MyDateUtils.secondPerHour*lesson.duration));
                tmap.put("end", endDate);
                int hour = lessonTable.lessonDate.getHours();
                if(hour<12){
                    tmap.put("color", "#4EE387"); 
                }else if(hour<18&&hour>=12){
                    tmap.put("color", "#E15B36");
                }else if(hour<24&&hour>=18){
                    tmap.put("color", "#B235E0"); 
                }
                tmap.put("tipcontent", "教师："+lesson.teacher.name+"<br>报名人数："+lesson.studentNum+"人<br>课程时长："+lesson.duration+"小时"); 
                calendarSource.add(tmap);
            }
            
        }
        Gson gson = new Gson();
        renderArgs.put("classroom", classroom);
        renderArgs.put("calendarSource", gson.toJson(calendarSource));
        render();
    }
    
    public static void edit(long id){
        
    }
    
    public static void deletes(String ids){
        String where = String.format("id in (%s)", ids);
        List<Classroom> classrooms = Classroom.find(where).fetch();
        for(Classroom s : classrooms){
            s.state = BaseModel.DELETE;
            s.save();
        }
        renderJSON(forwardJson("classroomsList", "/classrooms/list", "删除成功！"));
    }
}
