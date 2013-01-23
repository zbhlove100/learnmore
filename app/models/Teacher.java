package models;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="teacher")
public class Teacher extends BaseModel {
    public String name;
    public int age;
    public String picture;
    public String email;
    public String tel;
    public String address;
    public String employeType;
    
    public enum EM_TYPE{
        TEACHER,ASSISTANT,MARKETER
    }
}
