package com.qa.client;

import org.json.JSONArray;
import org.json.JSONObject;

public class TestUtil {

	public static String getValueByJPath(JSONObject responsejson, String jpath) {
		
		Object obj = responsejson;
		String[] arr = jpath.split("/");
		
		for (String s : arr) {
		
			if(!s.isEmpty()) {
				if(!s.contains("[") || !s.contains("]")) {
					obj = ((JSONObject) obj).get(s);
				}
				
				else if (s.contains("[") || s.contains("[")) {
					String[] stringsplit = s.split("\\[");
					
					obj = ((JSONArray) ((JSONObject) obj).get(stringsplit[0])).get(Integer.parseInt(stringsplit[1].replace("]", "")));
				}}}
		   return obj.toString();
		}
}
