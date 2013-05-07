package models;

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
    
}
