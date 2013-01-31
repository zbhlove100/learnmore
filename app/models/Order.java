package models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name="order_message")
public class Order extends BaseModel {
    
    public String name;
    
    public String description;
    
    public int money;
    
    public String state;
    
    public Date createdAt;
    
    public Date removedAt;
    
    @ManyToOne
    public Student student;
    
    @ManyToOne
    public Teacher teacher;
    
    @ManyToOne
    public Lesson lesson;
}