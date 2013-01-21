package utils;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

public class MapEntryConverter implements Converter {
	
   	public boolean canConvert(Class clazz) {
	    return AbstractMap.class.isAssignableFrom(clazz);
	}

	public void marshal(Object value, HierarchicalStreamWriter writer, MarshallingContext context) {
	    AbstractMap<String,String> map = (AbstractMap<String,String>) value;
	    for (Entry<String,String> entry : map.entrySet()) {
	        writer.startNode(entry.getKey().toString());
	        writer.setValue(entry.getValue().toString());
	        writer.endNode();
	    }
	}

	public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
		return children(reader);
	}
	
	private Map<String, Object> children(HierarchicalStreamReader reader){
		Map<String, Object> map = new HashMap<String, Object>();
	    while(reader.hasMoreChildren()) {
	        reader.moveDown();
	        if(reader.hasMoreChildren()){
	        	Object obj = map.get(reader.getNodeName());
	        	if(obj != null){
	        		if(obj instanceof List){
	        			((List)obj).add(children(reader));
	        		}else{
	        			List objs = new ArrayList();
	        			objs.add(obj);
	        			objs.add(children(reader));
	        			map.put(reader.getNodeName(), objs);
	        		}
	        	}else{
	        		map.put(reader.getNodeName(), children(reader));
	        	}
	        }else{
	        	 map.put(reader.getNodeName(), reader.getValue());
	         }
	        reader.moveUp();
	    }
	    return map;    		
	}
}
