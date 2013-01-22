package models;

import javax.persistence.Entity;

import utils.Base64Util;
@Entity
public class User extends BaseModel{
    public String name;
    
    public String email;
    
    public String passwordBase64;
    
    public String role;
    
    public User(String name, String email, String role) {
        super();
        this.name = name;
        this.email = email;
        this.role = role;
    }

    public void setPasswordBase64(String plainpassword) {
        this.passwordBase64 = Base64Util.encode(plainpassword);
    }

    public String getPasswordBase64() {
        return Base64Util.decode(this.passwordBase64);
    }
    public static User connect(String username,String password){
        return User.find("email = ? and password = ? and state = ?", username,password,BaseModel.ACTIVE).first();
    }
}
