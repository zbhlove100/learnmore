package models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="classroom")
public class Classroom extends BaseModel {
    public String name;
    
    public String size;
    
    public int volume;
    
    public String description;
    
    public String state;
    
    public Date createdAt;
    
    public Date removedAt;
    
    @ManyToOne
    public School school;
}
