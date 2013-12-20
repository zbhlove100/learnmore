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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import play.db.jpa.Model;
@Entity
@Table(name="lesson")
public class Lesson extends Model{

	public String name;
	
	public String lessonYear;
	
	public String lessonTimeType;
	
	public String lessonTime;
	
	public String periodOfDay;
	
	public String lessonType;
	
	public String collection;
	
	public String subCollection;
	
	public String state;
    
    public Date createdAt;
    
    public Date removedAt;
	
	public String status;
	
	public int times;
	
	public float duration;
	
	public Date startTime;
	
	public Date endTime;
	
	public String description;
	
	public int price;
	
	public int studentNum;
	@ManyToOne
	public Teacher teacher;
	
	@ManyToOne
	public School school;
	
	@ManyToOne
	public Book book;
	
	@ManyToOne
	public Grade grade;
	
	@ManyToOne
	public Classroom classroom;
	
	@OneToMany(mappedBy="lesson",fetch=FetchType.LAZY)
	public List<LessonTable> lessonTables;
	
	@OneToMany(mappedBy="lesson",fetch=FetchType.LAZY)
    public List<Order> orders;
	
	@OneToMany(mappedBy="lesson",fetch=FetchType.LAZY)
	public List<Tag> tags;
	
	@ManyToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
    @JoinTable(name="lesson_student"
                ,joinColumns={@JoinColumn(name="lesson_id")}
                ,inverseJoinColumns={@JoinColumn(name="student_id")})
    public List<Student> students;
	
	public Long getStudents(){
	    return Order.count("lesson = ? and state = ?", this,BaseModel.ACTIVE);
	}
	public String getLessonTimeName(){
	    Setting setting = Setting.find("name = ? and value = ?","LESSON_TIME",lessonTime).first();
	    return setting == null?"":setting.extvalue;
	}
}
