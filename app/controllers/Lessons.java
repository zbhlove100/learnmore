package controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import models.BaseModel;
import models.Lesson;
import models.LessonTable;
import models.Order;
import models.Student;

public class Lessons extends CRUD {
    public static void list(){
        List<Lesson> lessons = Lesson.find("state !=?", BaseModel.DELETE).fetch();
        renderArgs.put("objects", lessons);
        render();
    }
    public static void blank(){
        render();
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
    private static List<List> getLessonTable(long id) throws ParseException{
        List<List> lessonTables = new ArrayList<List>();
        int fetchNum = 5;
        long maxLesson = LessonTable.count("lesson.id", id);
        new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        int mark =1;
        for(int i=1;i<maxLesson;i+=fetchNum){
            List<LessonTable> tlessonTables = LessonTable.find("lesson.id", id).fetch(mark, 5);
            for(LessonTable t:tlessonTables){
               if(sdf.parse(t.lessonDate).before(new Date())){
                   t.state = BaseModel.FINISH;        
                }
            }
            lessonTables.add(tlessonTables);
            mark++;
        }
        return lessonTables;
    }
}
