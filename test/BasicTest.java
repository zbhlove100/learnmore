import org.junit.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.persistence.Query;

import play.db.jpa.JPA;
import play.test.*;
import utils.Base64Util;
import models.*;

public class BasicTest extends UnitTest {

    //@Test
    public void aVeryImportantThingToTest() throws ParseException {
        /*User user = new User();
        user.name = "root";
        user.email = "root";
        user.role = Role.findById(1l);
        user.state = BaseModel.ACTIVE;
        user.isSuper = "true";
        user.setPassword("zbhxzz");
        user.save();
        Role r = new Role();
        r.roleName = "admin";
        r.save();*/
       /* Teacher t = Teacher.findById(3l);
        System.out.println(t.teacherDetail.tel);
        List<Lesson> lessons = Lesson.findAll();
        for(int i=2;i<6;i++){
            Student s = new Student();
            s.name = "张" + i;
            s.age = 11 + i;
            s.grade = i + "年级";
            s.email = i + "@126.com";
            s.location = "TYUIOPKJJ";
            s.lessons = lessons;
            s.state = BaseModel.ACTIVE;
            s.save();
        }*/
        List<Department> departments = Department.find("type = ? and state !=?"
                , Teacher.EM_TYPE.TEACHER.toString()
                ,BaseModel.DELETE).fetch();
        String sql = "select CONCAT(CAST(o.createdAt as varchar(7)),'-01'), count(*) as con,d from Order o,Teacher t,Department d where 1=1" +
        		"and o.teacher = t and t.department = d";
        sql += " group by CONCAT(CAST(o.createdAt as varchar(7)),'-01'),d";        
        Query query = JPA.em().createQuery(sql);
        List l = query.getResultList();
        Iterator i = l.iterator();
        
        
        String startMonth = null;
        String toMonth = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-01");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM");
            long year1 = 86400000;
            long yeart = year1*365;
            toMonth = sdf.format(new Date());
            
            long now = sdf.parse(toMonth).getTime();
            startMonth = sdf.format(now-yeart);
        Date tempDate = sdf.parse(toMonth);
        System.out.println(sdf.parse("2012-02-01").getTime());
        System.out.println(startMonth);
        System.out.println(toMonth);
        
        long monthtime = 86400000*31;
        int months = 12;
        
        for(Department department:departments){
        	Map map = new HashMap<String, Object>();
        	map.put("dept", department.name);
        	List datalist = new ArrayList<HashMap>();
            for(int j=0;j<months;j++){
                long dtime = monthtime*j;
                long td = tempDate.getTime() + dtime;
                String year = sdf.format(td);
                Map datamap = new HashMap<String, Object>();
                datamap.put("year", year);
                System.out.println(year);
                datamap.put("con", 0);
                
                while(i.hasNext()){
                    Object[] obj = (Object[]) i.next();
                    String dept = ((Department)obj[2]).name;
                    if(year.equals(obj[0])&&department.name.equals(dept)){
                    	datamap.put("con", obj[1]);
                    }
                }
                datalist.add(datamap);
            }
            map.put("data", datalist);
            //sResult.add(map);
    	}
        
    }
    @Test
    public void test1() throws ParseException {
    	String sql = "SELECT convert(varchar,current_time(),110) from Teacher";
    	Query query = JPA.em().createQuery(sql);
        List l = query.getResultList();
    }
    
}
