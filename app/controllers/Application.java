package controllers;

import java.util.Calendar;
import java.util.List;

import models.BaseModel;
import models.CurrentUser;
import models.Department;
import models.Order;
import models.OrderHistory;
import models.Student;
import models.Teacher;
import play.Logger;
import play.mvc.Before;
import play.mvc.Controller;
import play.mvc.Router;
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
			
			CurrentUser cuser = CurrentUser.current();
            session.put("username",cuser.name);
            renderArgs.put("user", cuser);
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~cu user:"+cuser.role);
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
	    long tcount =  Teacher.count("state !=?", BaseModel.DELETE);
        long scount =  Student.count("state !=?", BaseModel.DELETE);
        long ocount =  Order.count("state =?", BaseModel.ACTIVE);
        Calendar ca = Calendar.getInstance(); 
        ca.setTime(new java.util.Date()); 
        int month = ca.get(Calendar.MONTH);
        List<Student> students = Student.find("MONTH(birthday) > ? and MONTH(birthday) <=? and state = ? order by DAY(birthday)", month,month+1,BaseModel.ACTIVE).fetch();
        render(tcount,scount,ocount,students);
	}
	
	public static void dashboard() {
	    long tcount =  Teacher.count("state !=?", BaseModel.DELETE);
        long scount =  Student.count("state !=?", BaseModel.DELETE);
        long ocount =  Order.count("state =?", BaseModel.ACTIVE);
        Calendar ca = Calendar.getInstance(); 
        ca.setTime(new java.util.Date()); 
        int month = ca.get(Calendar.MONTH);
        List<Student> students = Student.find("MONTH(birthday) > ? and MONTH(birthday) <=? and state = ? order by DAY(birthday)", month,month+1,BaseModel.ACTIVE).fetch();
        /*Order o = Order.find("order by modifyAt desc").first();
        Mails.noticeModifyOrder(o);*/
        render(tcount,scount,ocount,students);
    }
}

