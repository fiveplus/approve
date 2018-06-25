package com.approve.struts.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * JSON∞Ô÷˙¿‡
 * @author Mr.Zhang
 *
 */
public class JsonUtil {
	private static ObjectMapper json;
	static{
		json = new ObjectMapper();
		json.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
		json.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}
	
	public static ObjectMapper getJson(){
		return json;
	}
	
	public static String ObjectToString(Object object){
		try{
			return json.writeValueAsString(object);
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public static Map<String,String> StrinToMap(String jsonString){
		Map<String,String> emptyMap = new HashMap<String,String>();
		if(StringUtils.isEmpty(jsonString)){
			return emptyMap;
		}else{
			try {
				return json.readValue(jsonString, emptyMap.getClass());
			} catch (Exception e) {
				return emptyMap;
			}
		}
	}
	
	public static <T> T StringToObject(String jsonString,Class<T> clazz){
		if(StringUtils.isEmpty(jsonString)) return null;
		try {
			return json.readValue(jsonString, clazz);
		} catch (Exception e) {
			return null;
		}
	}
	
	public static <T> List<T> getDTOList(String jsonString,Class<T> clazz){
		 List<T> emptyMap = new ArrayList<T>();
			if(StringUtils.isEmpty(jsonString)) {
				return emptyMap;
			}else{
				try{
					return json.readValue(jsonString,emptyMap.getClass());  
				}catch(Exception e){
					return emptyMap;
				}
			}
	}	
	
	public static List<Map<String,Integer>> getList(String jsonString){   
		List<Map<String,Integer>> emptyMap = new ArrayList<Map<String,Integer>>();
		if(StringUtils.isEmpty(jsonString)) {
			return emptyMap;
		}else{
			try{
				return json.readValue(jsonString,emptyMap.getClass());  
			}catch(Exception e){
				return emptyMap;
			}
		}
	}
	
	
}
