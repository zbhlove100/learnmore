package controllers;

import java.util.List;

import utils.Base64Util;

import models.BaseModel;
import models.Role;
import models.User;

@Check("root")
public class Users extends CRUD {
/*    @Check("root")
    public static void list(){
        
    }
    @Check("root")
    public static void detail(){
        
    }
    @Check("root")
    public static void delete(){
        
    }
*/
    public static void blank(){
        List<Role> rs = Role.findAll();
        render(rs);
    }
    public static void create(){
        User u = new User();
        u.name = params.get("name");
        u.email = params.get("email");
        u.password = params.get("password");
        String issuper = params.get("isSuper")==null?"false":"true";
        u.isSuper = issuper;
        u.state = BaseModel.ACTIVE;
        Role r = Role.find("name = ?", params.get("role")).first();
        u.role = r;
        u.save();
        renderJSON(forwardJsonCloseDailog("userlist", "/users/list", "创建成功！"));
    }
    public static void edit(long id){
        User object = User.findById(id);
        List<Role> rs = Role.findAll();
        render(object,rs);
    }
    public static void save(long id){
        User u = User.findById(id);
        u.name = params.get("name");
        u.email = params.get("email");
        u.password = params.get("password");
        String issuper = params.get("isSuper")==null?"false":"true";
        u.isSuper = issuper;
        u.state = BaseModel.ACTIVE;
        Role r = Role.find("name = ?", params.get("role")).first();
        u.role = r;
        u.save();
        renderJSON(forwardJsonCloseDailog("userlist", "/users/list", "修改成功！"));
    }
        
}
