package models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="student")
public class Student extends BaseModel {

    public String name;
    
    public String email;
    
    public int age;
    
    public String sex;
    
    public String birthday;
    
    public String location;
    
    public String state;
    
    public Date createdAt;
    
    public Date removedAt;
    
    public String tel;
    
    public String localtel;
    
    public String description;
    
    @ManyToOne
    public Grade grade;
    
    @OneToOne(mappedBy="student")
    public ImgDetail imgDetail;
    
    @ManyToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
    @JoinTable(name="lesson_student"
                ,joinColumns={@JoinColumn(name="student_id")}
                ,inverseJoinColumns={@JoinColumn(name="lesson_id")})
    public List<Lesson> lessons;
    
    public int birthdayToNow(){
        int result = 0;
        Calendar ca = Calendar.getInstance(); 
        ca.setTime(new java.util.Date()); 
        int nowyear = ca.get(Calendar.YEAR);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String thisYearBirthday = nowyear + birthday.substring(4);
        try {
            Date d2 = sdf.parse(thisYearBirthday);
            Calendar calendar = Calendar.getInstance();  
            calendar.setTime(d2);  
            int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);  
            int nowdayOfMonth = ca.get(Calendar.DAY_OF_MONTH);
            result = dayOfMonth - nowdayOfMonth ;
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result;
    }
}
