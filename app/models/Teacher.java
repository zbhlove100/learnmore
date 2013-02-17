package models;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="teacher")
public class Teacher extends BaseModel {
    
    public String name;
    
    public int age;
    
    public String email;
    
    public String employeType;
    
    public String state;
    
    public Date createdAt;
    
    public Date removedAt;
    
    @OneToOne(mappedBy="teacher")
    public TeacherDetail teacherDetail;
    
    @OneToOne(mappedBy="teacher")
    public ImgDetail imgDetail;
    
    @OneToMany(mappedBy="teacher",fetch=FetchType.LAZY)
    public List<Lesson> lessons;
    
    @ManyToOne
    public School school;
    
    @ManyToOne
    public Department department;
    
    @OneToMany(mappedBy="teacher",fetch=FetchType.LAZY)
    public List<Tag> tags;
    
    public enum EM_TYPE{
        TEACHER,ASSISTANT,MARKETER
    }
}
