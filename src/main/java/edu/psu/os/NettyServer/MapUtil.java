package edu.psu.os.NettyServer;

import java.util.Map;

public class MapUtil {

	public static String getValue(String result, Map<Integer, Integer> myMap) {
		String [] parts = result.split(":");
        
        if (parts[0].equalsIgnoreCase("STOP")) {
        	return "";
        }
        Integer mapkey = Integer.parseInt(parts[0]);
        Integer mapValue = null;
        if (parts.length == 2) {
            mapValue = Integer.parseInt(parts[1]);                    
        }
        
        if (mapValue != null) {
        	myMap.put(mapkey, mapValue);
    		return "inserted";
           
        } else if (!myMap.containsKey(mapkey)) {
           return "not present";
        } else {
           return String.valueOf(myMap.get(mapkey));
        }
	}
}
