package models;

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
	
	public String lessonTimeType;
	
	public String lessonType;
	
	public String collection;
	
	public String subCollection;
	
	public String level;
	
	public String state;
	
	public String status;
	
	public int times;
	
	public String startTime;
	
	public String endTime;
	
	public String discription;
	
	public int price;
	
	public int studentNum;
	@ManyToOne
	public Teacher teacher;
	
	@ManyToOne
	public School school;
	
	@ManyToOne
	public Book book;
	
	@OneToMany(mappedBy="lesson",fetch=FetchType.LAZY)
	public List<LessonTable> lessonTables;
	
	@OneToMany(mappedBy="lesson",fetch=FetchType.LAZY)
	public List<Tag> tags;
	
	@ManyToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
    @JoinTable(name="lesson_student"
                ,joinColumns={@JoinColumn(name="lesson_id")}
                ,inverseJoinColumns={@JoinColumn(name="student_id")})
    public List<Student> students;
}
