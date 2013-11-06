import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import models.Student;

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
   
    static int V, E;
    static int[] E1, E2;
    static int AnswerN;
    static int[] Answer;
    //@Test
    public void test4(){
        int[] A1 = {6,4};
        int[] A2 = {1,4,2,4,5,2,5,6};
        int[] t = new int[10];
        for(int test_case =1;test_case<10;test_case++){
        V = A1[0];
        E = A1[1];
        E1 = new int[E];
        E2 = new int[E];
        for(int i=0;i<E;i++){
            
            E1[i] = A2[i*2];
            E2[i] = A2[i*2+1];
            
        }
            
            
            
            AnswerN = -1;
        Answer = new int [V];
        int[] nodes = new int [V];
        for(int i=0;i<V;i++){
            if(nodes[i]!=-2){
                nodes[i] = i+1;
                for(int j=0;j<E;j++){
                    if(E1[j]==nodes[i]){
                        nodes [E2[j]-1] = -2;
                    }
                    if(E2[j]==nodes[i]){
                        nodes [E1[j]-1] = -2;
                    }               
                }
            }
        }
        int len = 0;
        for(int i:nodes){
            System.out.println(i);
            if(i!=-2){
                
                Answer[len] = i;
                len ++;
            }
        }
        if(len>0)
            AnswerN = len;
        System.out.print("#" + test_case + " " + AnswerN);
        for(int i = 0; i < AnswerN; i++)
        {
            System.out.print(" " + Answer[i]);
        }
        System.out.println();
    }
    }
    //@Test
    public void test6(){
        int answer = 0;
        String result = Integer.toBinaryString(29);
        for(int i=0;i<result.length();i++){
            char t = result.charAt(i);
            if(t=='1')
                answer++;
        }
        int[] temp = new int[5];
        temp = null;
        int[] temp1 = new int[V];
        //System.out.println(result);
        System.out.println(temp);
    }
    @Test
    public void test7(){
    }
}
