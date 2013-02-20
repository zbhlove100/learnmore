package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="grade")
public class Grade extends BaseModel {
    public String name;
    
    public int level;
    
    @OneToMany(mappedBy="grade")
    public List<Student> students;
    
    @OneToMany(mappedBy="grade")
    public List<Lesson> lessons;
}
