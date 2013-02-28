package utils;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.zip.GZIPOutputStream;

import net.sf.json.JSONObject;

import org.apache.commons.codec.digest.DigestUtils;

import sun.misc.BASE64Decoder;
public class StringUtils{

    private Map<String,Object> allValues(JSONObject val,boolean ignorehead){
   	 Map<String,Object> values = new HashMap<String,Object>();
   	 Iterator vals = val.entrySet().iterator();
       while (vals.hasNext()) {
         
       	Map.Entry e = (Map.Entry)vals.next();
          Object ev = e.getValue();
          
          if(ev instanceof JSONObject){
       	     if(!ignorehead){
       	    	 values.put(e.getKey().toString(), allValues((JSONObject)ev,false));
       	     }else{
       	    	 values = allValues((JSONObject)ev,false);
       	     }
          }if(ev instanceof List){
       	   
       	   Object[] objects = ((List) ev).toArray();
       	   
       	   for(int i=0 ;i < objects.length; i++){
       		   objects[i]  = allValues((JSONObject)objects[i],false);
       	   }
       	   
          }else{
           	 if(!ignorehead){
           		 values.put(e.getKey().toString(), ev);
           	 }
            }
         }    	
   	  return values;
   }

    /** 
    *  compress a string
     *  
    * @param str 
     */  
    public static String compress(String str) throws Exception{
       if (str == null || str.length() == 0) {
            return str;
        }
       ByteArrayOutputStream out = new ByteArrayOutputStream();
       GZIPOutputStream gzip = new GZIPOutputStream(out);
       gzip.write(str.getBytes());
       gzip.close();
       return out.toString("ISO-8859-1");
     }
 	
    public static String apikey(){
    	return DigestUtils.md5Hex(UUID.randomUUID().toString());  	
    }
    
    public static String secretkey(){
    	return UUID.randomUUID().toString().replace("-", ""); 	
    }    
    
	public static String md5(String content) {
		return DigestUtils.md5Hex(content);  	
	}
	public static boolean checkNotNull(String o){
	    return (o != null&&!"".equals(o))?true:false;
	}
}
