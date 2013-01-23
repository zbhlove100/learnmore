package controllers;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import controllers.Secure.Security;

import models.BaseModel;
import models.CurrentUser;
import models.User;

import play.cache.Cache;
import play.jobs.Job;
import play.libs.F;
import play.mvc.Http;
import play.mvc.Scope;

public class LearnmoreSecurity extends Security {

    public static boolean check(String profile) {
        CurrentUser cu = (CurrentUser)connected();
        return cu != null && profile.equals(cu.role);
    }
    public static boolean onAuthenticated(String username) {
                  
        User acc = User.find("email=? and state=?", username,BaseModel.ACTIVE).first();
        CurrentUser currentuser = (CurrentUser)Cache.get(Scope.Session.current().getId());
        if(currentuser == null){
        	 currentuser = new CurrentUser(acc.id,acc.email,acc.role.roleName,acc.password);
        	 Cache.set(Scope.Session.current().getId(), currentuser, "30mn");
         }else{
        	 Cache.set(Scope.Session.current().getId(), currentuser, "30mn");
    	}
        return false;
        
    }

    public static boolean onTheDisconnected() {
		Cache.delete(Scope.Session.current().getId());
		Scope.Session.current().clear();
		// remove navtab information
		Http.Response.current().removeCookie("lastnavtab_id");
		Http.Response.current().removeCookie("lastnavtab_url");
		Http.Response.current().removeCookie("lastnavtab_title");
        return false;           
    }

    public static Object connected() {
        Object obj = null;
        try{
        	obj = Cache.get(Scope.Session.current().getId());
	        if(obj != null){
	        	 Cache.set(Scope.Session.current().getId(), obj, "30mn"); 
	         }
        }catch(Exception e){
        	play.Logger.warn("No a current user available");
        }
        return obj;
    }
    
    public static boolean isConnected() {
        return Cache.get(Scope.Session.current().getId()) != null;
    }    
    
}



