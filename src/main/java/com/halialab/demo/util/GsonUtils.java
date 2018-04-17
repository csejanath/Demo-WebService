/**
 * 
 */
package com.halialab.demo.util;

import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

/**
 * @author Janath
 *
 */
public class GsonUtils {
	  public static final Gson GSON = new GsonBuilder().disableHtmlEscaping().setDateFormat("yyyy-MM-dd HH:mm:ss.S").create();
	  
	  private GsonUtils() {
	    // do nothing
	  }

	  public static String toJson(Object src) {
	    return GSON.toJson(src);
	  }

	  public static <T> T fromJson(String json, Class<T> classOfT) {
	    return GSON.fromJson(json, classOfT);
	  }
	  
	  public static <T> T fromJson(String json, TypeToken<T> typeToken) {
	    return GSON.fromJson(json, typeToken.getType());
	  }
	  
	  public static Map<String, Object> fromJsonToMap(String json) {
	    return fromJson(json, new TypeToken<Map<String, Object>>(){});
	  }
	  
	  public static <T> T convert(Object o, Class<T> classOfT) {
	    return fromJson(toJson(o), classOfT);
	  }

	}
