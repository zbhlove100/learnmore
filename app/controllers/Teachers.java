package controllers;

import java.util.List;

import models.Teacher;
import models.Where;

public class Teachers extends CRUD {
    public static void list(){
        Where where = new Where(params);
        
        _list(where);
    }
    
    public static void blank(){
        render();
    }
    
    public static void deletes(){
        render();
    }
    
    public static void listDetailMessage(Long id){
        render();
    }
}
