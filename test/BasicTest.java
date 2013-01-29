import org.junit.*;
import java.util.*;
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
        System.out.println(t.teacherDetail.tel);*/
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
        }
    }

}
