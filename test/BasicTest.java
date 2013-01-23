import org.junit.*;
import java.util.*;
import play.test.*;
import utils.Base64Util;
import models.*;

public class BasicTest extends UnitTest {

    @Test
    public void aVeryImportantThingToTest() {
        User user = new User();
        user.name = "root";
        user.email = "1@1.com";
        user.role = Role.findById(1l);
        user.setPassword("zbhxzz");
        user.save();
        /*Role r = new Role();
        r.roleName = "admin";
        r.save();*/
    }

}
