package edu.psu.os.NettyServer;

import java.util.Map;

public class MapUtil {

	public static String getValue(String result, Map<Integer, String> myMap) {
		String [] parts = result.split(":");
        
        if (parts[0].equalsIgnoreCase("STOP")) {
        	return "";
        }
        Integer mapkey = Integer.parseInt(parts[0]);
        String mapValue = null;
        if (parts.length == 2) {
            mapValue = parts[1];                    
        }
        
        if (mapValue != null) {
        	myMap.put(mapkey, mapValue);
    		return "0";
           
        } else if (!myMap.containsKey(mapkey)) {
           return "-1";
        } else {
           return String.valueOf(myMap.get(mapkey));
        }
	}
}
