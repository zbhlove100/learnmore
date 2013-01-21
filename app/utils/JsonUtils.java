package utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JsonUtils {
	
    private static List<Object> jsonArrayValues(JSONArray val){
    	
    	List<Object> values = new ArrayList<Object>();
    	
    	for(Object object:val){
          if(object instanceof JSONObject){
     	    	 values.add(jsonValues((JSONObject)object));
          }else if(object instanceof JSONArray){
        	  	 values.add(jsonArrayValues((JSONArray)object));
          }else{
         		 values.add(object.toString());
          }
        }   
    	return values;
    }
    
    private static Map<String,Object> jsonValues(JSONObject val){
    	
    	Map<String,Object> values = new HashMap<String,Object>();
    	Iterator vals = val.entrySet().iterator();
       while (vals.hasNext()) {
            
        	Map.Entry e = (Map.Entry)vals.next();
          Object ev = e.getValue();
          if(ev instanceof JSONObject){
     	    	 values.put(e.getKey().toString(), allValues((JSONObject)ev,false));
          }else if(ev instanceof JSONArray){
        	  	 values.put(e.getKey().toString(), jsonArrayValues((JSONArray)ev));
          }else{
         		 values.put(e.getKey().toString(), ev.toString());
          }
        }   
    	return values;
    }
    
    public static Map<String,Object> allValues(JSONObject val,boolean ignorehead){
    	 
    	 Map<String,Object> values = jsonValues(val);
    	 if(ignorehead){
    		 Iterator iterator = values.keySet().iterator();
    		 while(iterator.hasNext()){
    			 Object obj = values.get(iterator.next());
    			 if(obj instanceof Map){
    				 values = (Map<String,Object>) obj;
    			 }
    		 }
    	 }
    	 return values;
    }
}
