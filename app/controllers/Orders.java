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
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.google.gson.Gson;
import com.ning.http.util.Base64;

import play.db.jpa.JPA;
import utils.MyDateUtils;

import models.BaseModel;
import models.CurrentUser;
import models.Department;
import models.Lesson;
import models.Order;
import models.Student;
import models.Teacher;
import models.Teacher.EM_TYPE;
import models.User;
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
                    order.name = student.name +"-"+ lesson.name;
                    order.money = money;
                    order.description = description;
                    order.lesson = lesson;
                    order.student = student;
                    order.teacher = lesson.teacher;
                    order.state = BaseModel.ACTIVE;
                    order.user = User.findById(CurrentUser.current().id);
                    order.createdAt = new Date(java.lang.System.currentTimeMillis());
                    order.identifyNo = UUID.randomUUID().toString();
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
            order.name = student.name +"-"+ lesson.name;
            order.money = money;
            order.description = description;
            order.lesson = lesson;
            order.student = student;
            order.teacher = lesson.teacher;
            order.state = BaseModel.ACTIVE;
            order.user = User.findById(CurrentUser.current().id);
            order.createdAt = new Date(java.lang.System.currentTimeMillis());
            order.identifyNo = UUID.randomUUID().toString();
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
        renderJSON(forwardJson("ordersList", "/orders/list", "删除成功！"));
    }
    
    public static void detail(long id){
        Order order = Order.findById(id);
        renderArgs.put("order", order);
        renderArgs.put("lesson", order.lesson);
        renderArgs.put("student", order.student);
        render();
    }
    
    public static void printPage(long id){
        Order order = Order.findById(id);
        renderArgs.put("order", order);
        render();
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
        long monthtime = 86400000*31l;
        try {
            List<Department> departments = Department.find("type = ? and state !=?"
                                                        , Teacher.EM_TYPE.TEACHER.toString()
                                                        ,BaseModel.DELETE).fetch();
            String toMonth = params.get("toMonth");
            String startMonth = null;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-01");
            String sql = "select CONCAT(CONCAT(YEAR(o.createdAt),-MONTH(o.createdAt)),'-01') as month, count(*) as con,d from Order o,Teacher t,Department d" +
                		" where 1=1" +
                        "and o.teacher = t and t.department = d \n";
            if(toMonth==null||"".equals(toMonth)){
                long year = 86400000;
                long yeart = year*365;
                Date nodate = new Date();
                toMonth = sdf.format(nodate.getTime()+monthtime);
                long now = sdf.parse(toMonth).getTime();
                startMonth = sdf.format(now-yeart);
                sql = sql + "and o.createdAt > '"+startMonth +"' and o.createdAt < '"+toMonth +"'";
            }else{
                
            }
            sql += "group by CONCAT(CONCAT(YEAR(o.createdAt),-MONTH(o.createdAt)),'-01'),d";        
            Query query = JPA.em().createQuery(sql);
            List l = query.getResultList();
            List<Map> sResult = new ArrayList<Map>();
            
            
            for(Department department:departments){
            	Map map = new HashMap<String, Object>();
            	map.put("dept", department.name);
            	List datalist = new ArrayList<HashMap>();
            	Date tempDate = sdf.parse(startMonth);
	            for(int j=0;j<months;j++){
	                long td = j==0?tempDate.getTime():(tempDate.getTime() + monthtime);
	                String year = sdf.format(td);
	                Map datamap = new HashMap<String, Object>();
	                datamap.put("year", year.substring(0, 7));
	                datamap.put("con", 0);
	                tempDate = sdf.parse(year);
                    for(int i=0;i<l.size();i++){
                        Object[] obj = (Object[]) l.get(i);
                        String selectDate = (String) obj[0];
                        selectDate = getTheFormatDate(selectDate);
                        String dept = ((Department)obj[2]).name;
                        if(year.equals(selectDate)&&department.name.equals(dept)){
                        	datamap.put("con", obj[1]);
                        }
                    }
                    datalist.add(datamap);
	            }
	            map.put("data", datalist);
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
    private static String getTheFormatDate(String date){
    	String result = date;
    	String dates[] = date.split("-");
    	int month = Integer.parseInt(dates[1]);
    	if(month<10){
    		result = dates[0]+"-0"+dates[1]+"-"+dates[2];
    	}
    	return result;
    }
}
