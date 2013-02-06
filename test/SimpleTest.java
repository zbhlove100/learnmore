import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;


public class SimpleTest {

    //@Test
    public void test() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        long bir = sdf.parse("1985-03-01").getTime();
        Date d = new Date();
        long now = d.getTime();
        long i = 86400000;
        long x = now-bir;
        int hour = (int) (x/i);
        System.out.println(hour/365);
    }
    @Test
    public void test1() throws ParseException {
        long i = 1l;
        int j = 1;
        System.out.print(i==j);
    }

}
