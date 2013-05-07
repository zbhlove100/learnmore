import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import org.junit.Test;

import sun.security.provider.MD5;


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
    //@Test
    public void test2() throws ParseException {
    	String startMonth = null;
        String toMonth = null;
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-01");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM");
            long year1 = 86400000;
            long yeart = year1*365;
            toMonth = sdf.format(new Date());
            
            long now = sdf.parse(toMonth).getTime();
            startMonth = sdf.format(now-yeart);
            //System.out.println(startMonth);
        Date tempDate = sdf.parse(startMonth);
        long monthtime = 86400000*31l;
        int months = 12;
        for(int j=0;j<months;j++){
        	  long dtime = monthtime*j;
        	  //System.out.println(tempDate.getTime());
        	  //System.out.println(monthtime);
              long td = tempDate.getTime() + monthtime;
              //System.out.println(td);
              String year = sdf.format(td);
              tempDate = sdf.parse(year);
              System.out.println(td);
              System.out.println(year);
        }
    }
    //@Test
    public void test3() throws ParseException{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date d = (Date) sdf.parseObject("2012-03-01 05:30");
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        System.out.print(sdf1.format(d));
    }
    @Test
    public void test4(){
        System.out.println(5%3);
    }
}
