package controllers;

import controllers.Secure.Security;
import play.Logger;
import play.cache.Cache;
import play.data.validation.Required;
import play.libs.Crypto;
import play.mvc.Before;
import play.mvc.Controller;
import play.mvc.Http;

public class LearnmoreSecure extends Controller{
    @Before(unless={"login", "authenticate", "logout"})
    static void checkAccess() throws Throwable {
        // Authent
        if(!session.contains("username")) {
            Cache.add("url_" + session.getId(), request.method == "GET" ? request.url : "/", "10min");
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~checkAccess:"+request.url);
            //flash.put("url", "GET".equals(request.method) ? request.url : "/"); // seems a good default
            login();
        }
        // Checks
        Check check = getActionAnnotation(Check.class);
        if(check != null) {
            check(check);
        }
        check = getControllerInheritedAnnotation(Check.class);
        if(check != null) {
            check(check);
        }
    }

    private static void check(Check check) throws Throwable {
        for(String profile : check.value()) {
            boolean hasProfile = (Boolean)Security.invoke("check", profile);
            if(!hasProfile) {
                Security.invoke("onCheckFailed", profile);
            }
        }
    }

    // ~~~ Login

    public static void login() throws Throwable {
        
        Http.Cookie remember = request.cookies.get("rememberme");
        if(remember != null && remember.value.indexOf("-") > 0) {
            String sign = remember.value.substring(0, remember.value.indexOf("-"));
            String username = remember.value.substring(remember.value.indexOf("-") + 1);
            if(Crypto.sign(username).equals(sign)) {
                session.put("username", username);
                redirectToOriginalURL(username);
            }
        }
        render();
    }

    public static void authenticate(@Required String username, String password, boolean remember) throws Throwable {
        // Check tokens
        Boolean allowed = false;
        try {
            // This is the deprecated method name
            allowed = (Boolean)Security.invoke("authentify", username, password);
        } catch (UnsupportedOperationException e ) {
            // This is the official method name
            allowed = (Boolean)Security.invoke("authenticate", username, password);
        }
        if(validation.hasErrors() || !allowed) {
            flash.keep("url");
            flash.error("secure.error");
            params.flash();
            login();
        }
        // Mark user as connected
        session.put("username", username);
        // Remember if needed
        if(remember) {
            response.setCookie("rememberme", Crypto.sign(username) + "-" + username, "10d");
        }
        // Redirect to the original URL (or /)
        redirectToOriginalURL(username);
    }

    public static void logout() throws Throwable {
        Security.invoke("onDisconnect");
        session.clear();
        response.removeCookie("rememberme");
        Security.invoke("onTheDisconnected");
        flash.success("secure.logout");
        login();
    }

    // ~~~ Utils

    public static void redirectToOriginalURL(String username) throws Throwable {
        LearnmoreSecurity.invoke("onAuthenticated",username);
        String url = (String) Cache.get("url_" + session.getId());
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~redirectToOriginalURL:"+url);
        Cache.delete("url_" + session.getId());
        if(url == null) {
            url = "/";
        }
        redirect(url);
    }
}
