import org.junit.*;

import java.text.SimpleDateFormat;
import java.util.*;

import javax.persistence.Query;

import play.db.jpa.JPA;
import play.test.*;
import utils.Base64Util;
import models.*;

public class BasicTest extends UnitTest {

    @Test
    public void aVeryImportantThingToTest() {
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
        String sql = "select CONCAT(YEAR(o.createdAt),-MONTH(o.createdAt)) as month, count(*) as con,d from Order o,Teacher t,Department d where 1=1" +
        		"and o.teacher = t and t.department = d";
        sql += " group by CONCAT(YEAR(o.createdAt),-MONTH(o.createdAt)),d";        
        Query query = JPA.em().createQuery(sql);
        List l = query.getResultList();
        Iterator i = l.iterator();
        while(i.hasNext()){
            Object[] obj = (Object[]) i.next();
            System.out.print(obj[0]+" ");
            System.out.print(obj[1]+" ");
            System.out.println(((Department)obj[2]).name);
            
        }
    }

}
