package controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jobs.MessageJob;
import models.Setting;
import models.Syslog;
import models.spec.CurrentUser;
import models.spec.Message;
import play.Logger;
import play.Play;
import play.libs.F.Promise;
import play.mvc.Before;
import play.mvc.Controller;
import play.mvc.Router;
import play.mvc.Scope;
import play.mvc.With;
import utils.PaasClientUtil;

import com.samsung.cloudpi.client.api.CloudPiApiClient;
import com.samsung.cloudpi.client.api.exception.CloudPiException;

@With(CloudpiSecureCAS.class)
public class Application extends Controller {

	private static List<String> excludeurls = new ArrayList<String>();
	static{
		excludeurls.add("/events/list");
		excludeurls.add("/application/waitmessages");
	}
	
	@Before
	public static void checkUser(){
		// for flash authorization
		if (request.cookies == null
				|| request.cookies.get("PLAY_SESSION") == null) {
			Logger.debug("PLAY_SESSION is null");
			if (params.get("sessionId") != null) {
				String ID_KEY = "___ID";
				session.put(ID_KEY, params.get("sessionId"));
				session.put("username", CasSecurity.connected());
			}
		}
		if (!CurrentUser.isAuthed()) {
			Logger.debug("CurrentUser.isAuthed() is false");
			String loginUrl = Router.reverse("CloudpiSecureCAS.login").url;
			redirect(loginUrl);
		} else {
			Logger.debug("CurrentUser.isAuthed() is true");
			if (CurrentUser.current() == null) {
				Logger.info("CurrentUser.current() is null");
				String logoutUrl = Router.reverse("CloudpiSecureCAS.logout").url;
				redirect(logoutUrl);
			}
			
			// add wiki
			String wikiHome = Setting.value("WIKI_HOME_PAGE","http://wiki.cloudpi.net/wiki");
			renderArgs.put("wikiHome", wikiHome);
			renderArgs.put("user", CurrentUser.current());
		}
	}
	
	@Before
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
	}
    
	public static void index() {
		com.samsung.cloudpi.client.api.model.DashBoard dashboard = new com.samsung.cloudpi.client.api.model.DashBoard();
    	try {
			CloudPiApiClient client = PaasClientUtil.getCloudpiAPIClient();	
			dashboard = client.getDashBoard();
		} catch (CloudPiException e) {
			Logger.error(e, e.getError().toString());
		}
		String scalrHomePage = Setting.value("SCALR_HOME_PAGE", "http://test.cloudfarms.net");	
		String dnsHomePage = Setting.value("DNS_HOME_PAGE", "http://ns-api-staging.samsungcloud.org");
		
		render(dashboard, scalrHomePage,dnsHomePage);
	}

	public static void waitMessages() { 
		 Promise<Message> msg_job = new MessageJob(Scope.Session.current().getId()).now();
		 Message message = await(msg_job);
         renderJSON(message);
    }
	
	public static void downloadGuide(){
		String cloudpiVersion = Setting.value("CLOUDPI_VERSION", "derby");
		String fileName=Setting.value("CLOUDPI_USER_GUIDE_NAME", "CloudPi PaaS User Guide.pdf");
		String filePath=Play.applicationPath.getAbsolutePath()+File.separator+"public"+File.separator+"doc"+File.separator+cloudpiVersion+"_userguide.pdf";
		File guideFile=new File(filePath);
		if(!guideFile.exists()){
			try {
				guideFile.createNewFile();
			} catch (IOException e) {
				Logger.error(e,"Fail to get user guide.");
			}
		}
		renderBinary(guideFile, fileName);
	}
	
}

