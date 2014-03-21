package models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="order_history")
public class OrderHistory extends BaseModel{
    public String name;
    
    public String optype;
    
    public String description;
    
    public int money;
    
    public Date createdAt;
    
    @ManyToOne
    public User user;
    
    @ManyToOne
    public Order order_message;
    
    @ManyToOne
    public Student student;
    
    @ManyToOne
    public Lesson lesson;
}
