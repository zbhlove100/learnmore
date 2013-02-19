import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

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
    //@Test
    public void test1() throws ParseException {
        long i = 1l;
        int j = 1;
        long year = 86400000;
        long yeart = year*365;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        sdf.parse("2012-02").getTime();
        long d = new Date().getTime();
        long day =  (d/year)-365;
        
        System.out.println(d);
        System.out.println(yeart);
        System.out.println(d-yeart);
        System.out.println(sdf.parse("2012-02").getTime());
        System.out.print(sdf.format(d));
    }
    @Test
    public void test2(){
        System.out.println(UUID.randomUUID().toString());
    }

}
