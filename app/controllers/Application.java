package controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import models.BaseModel;
import models.CurrentUser;
import models.Order;
import models.Student;
import models.Teacher;


import play.Logger;
import play.Play;
import play.libs.F.Promise;
import play.mvc.Before;
import play.mvc.Controller;
import play.mvc.Router;
import play.mvc.Scope;
import play.mvc.With;


@With(LearnmoreSecure.class)
public class Application extends Controller {

	@Before
	public static void checkUser(){
		// for flash authorization
	    
		if (request.cookies == null
				|| request.cookies.get("PLAY_SESSION") == null) {
			Logger.debug("PLAY_SESSION is null");
			if (params.get("sessionId") != null) {
				String ID_KEY = "___ID";
				session.put(ID_KEY, params.get("sessionId"));
				session.put("username", LearnmoreSecurity.connected());
			}
		}
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~appl:"+request.url);
		if (!CurrentUser.isAuthed()) {
			Logger.debug("CurrentUser.isAuthed() is false");
			String loginUrl = Router.reverse("LearnmoreSecure.login").url;
			redirect(loginUrl);
		} else {
			Logger.debug("CurrentUser.isAuthed() is true");
			if (CurrentUser.current() == null) {
				Logger.info("CurrentUser.current() is null");
				String logoutUrl = Router.reverse("LearnmoreSecure.logout").url;
				redirect(logoutUrl);
			}
			
			renderArgs.put("user", CurrentUser.current());
		}
	}
	
	/*@Before
	public static void LogTrace(){		
		try{
			if(!excludeurls.contains(request.url)){				
				Syslog log = new Syslog();				
				log.method = request.method;	
				log.type = Syslog.Type.APP.name();
				log.traceUrl = request.url;
				log.srcIp = request.remoteAddress;
				log.sessionKey = Scope.Session.current().getId(); 
				log.rawData = params.allSimple().toString();				
				log.save();
			}			
		}catch(Exception e){
			e.printStackTrace();
		}		
	}*/
    
	public static void index() {
	    render();
	}
	
	public static void dashboard() {
	    long tcount =  Teacher.count("state !=?", BaseModel.DELETE);
        long scount =  Student.count("state !=?", BaseModel.DELETE);
        long ocount =  Order.count("state !=?", BaseModel.DELETE);
        render(tcount,scount,ocount);
    }
}

