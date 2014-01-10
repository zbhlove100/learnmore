package models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import utils.Base64Util;
@Entity
@Table(name="user")
public class User extends BaseModel{
    public String name;
    
    public String email;
    
    public String password;
    
    public String state;
    
    public String isSuper;
    
    @ManyToOne
    public Role role;
    

    public void setPassword(String plainpassword) {
        System.out.println(plainpassword);
        System.out.println(Base64Util.encode(plainpassword));
        this.password = Base64Util.encode(plainpassword);
    }

    public String getPassword() {
        return Base64Util.decode(this.password);
    }
    public static User connect(String username,String password){
        System.out.println(password);
        System.out.println(Base64Util.encode(password));
        return User.find("name = ? and password = ? and state = ?", username,Base64Util.encode(password),BaseModel.ACTIVE).first();
    }
}
