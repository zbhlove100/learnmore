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
import javax.persistence.Table;

import play.db.jpa.Model;

@Entity
@Table(name="book")
public class Book extends Model{
	public String name;
	
	public String type;
	
	public String image;
	
	public int price;
	
	public int oldprice;
	
	public String description;
	
	public String state;
	
    public Date createdAt;
    
    public Date removedAt;
	
	@ManyToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinTable(name="book_student"
				,joinColumns={@JoinColumn(name="book_id")}
				,inverseJoinColumns={@JoinColumn(name="student_id")})
	public List<User> users;
	
	@ManyToOne
	public LessonSystem lesson_system;
}
