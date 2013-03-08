import org.junit.*;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.persistence.Query;

import jxl.CellView;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import play.Play;
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
    //@Test
    public void test1() throws ParseException {
    	OrderHistory history = new OrderHistory();
    	history.name = "test his";
    	history.createdAt = new Date(System.currentTimeMillis());
    	history.money = 1002;
    	history.optype = "Update";
    	history.user = (User) User.findAll().get(0);
    	history.order_message = Order.findById(1l);
    	history.save();
    }
    @Test
    public void test2() throws ParseException {
        Lesson lesson = Lesson.findById(1l);
        List<Order> orders = Order.find("lesson = ? and state = ?", lesson,BaseModel.ACTIVE).fetch();
        try {
            WritableWorkbook workbook = Workbook.createWorkbook(new File(Play.applicationPath.getPath()+"/public/upload/myfile.xls"));
            WritableSheet sheet = workbook.createSheet("First Sheet", 0);
            WritableFont twf = new WritableFont(WritableFont.ARIAL, 16,WritableFont.BOLD); 
            WritableCellFormat twcf = new WritableCellFormat(twf);
            twcf.setAlignment(Alignment.CENTRE);
            Label tlabel = new Label(3, 0, lesson.name,twcf); 
            sheet.addCell(tlabel);
            sheet.setRowView(0, 500);
            int rowMark = 5;
            for(int i=0;i<lesson.times;i++){
                WritableCellFormat wcf = new WritableCellFormat();
                if((i%2)==0){
                    wcf.setBackground(Colour.WHITE);
                }else{
                    wcf.setBackground(Colour.GREY_50_PERCENT);
                }
                
                wcf.setBorder(Border.ALL, BorderLineStyle.MEDIUM);
                wcf.setWrap(true);
                Label label2 = new Label(i%rowMark+1,i/rowMark+4 , "                      ",wcf);
                sheet.addCell(label2);
            }
            
            int dividerLine = lesson.times/rowMark +5;
            WritableCellFormat dwcf = new WritableCellFormat();
            dwcf.setBorder(Border.BOTTOM, BorderLineStyle.MEDIUM_DASH_DOT);
            Label label = new Label(0, dividerLine, "名单",dwcf); 
            sheet.addCell(label); 
            sheet.mergeCells(0, dividerLine, rowMark, dividerLine);
            WritableCellFormat wcf = new WritableCellFormat();
            wcf.setBorder(Border.BOTTOM, BorderLineStyle.THIN);
            for(int i=0;i<orders.size();i++){
                Order o = orders.get(i);
                Label olabel = new Label(1, dividerLine+1+i, o.student.name,wcf);
                sheet.addCell(olabel);
                Label olabel1 = new Label(2, dividerLine+1+i, o.student.tel,wcf);
                sheet.addCell(olabel1);
                Label olabel2 = new Label(3, dividerLine+1+i, "",wcf);
                sheet.addCell(olabel2);
            }
            for(int i=0;i<rowMark;i++){
                
                sheet.setColumnView(i+1, 100);
            }
            int widthLine = lesson.times%rowMark==0?lesson.times/rowMark:lesson.times/rowMark+1;
            for(int i=0;i<widthLine;i++){
                
                sheet.setRowView(i+4, 1000);
            }
            
            workbook.write();
            workbook.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
}
