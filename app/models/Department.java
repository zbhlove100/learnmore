package models;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="department")
public class Department extends BaseModel {
    
    public String name;
    
    public long parentCode;
    
    public String description;
    
    public Date createdAt;
    
    public Date removedAt;
    
    public String state;
    
    public String type;
    
    @OneToOne
    public Teacher leader;
    
    @OneToMany(mappedBy="department",fetch=FetchType.LAZY)
    public List<Teacher> teachers;
}
