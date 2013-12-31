package models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import play.db.jpa.Model;
@Entity
@Table(name="lesson_table")
public class LessonTable extends Model{
	public String name;
	
	public Date lessonDate;
	
	public String state;
	
	public int mark;
	
	@ManyToOne
	public Lesson lesson;
	
	public static final int CELL_PER_ROW = 5;
}
