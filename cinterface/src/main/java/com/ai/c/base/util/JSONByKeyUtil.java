package com.ai.c.base.util;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JSONByKeyUtil {
	/**
	* 通过json和key获取到该json中的key值，返回string
	* @param json
	*  json的string
	* @param key
	*  需要获取的key
	* @return String
	*  如果是没去到返回空
	*/ 
	public static String getJSONByKey(String json,String key){
		try{
			JSONObject object = JSONObject.fromObject(json);
			if(object.containsKey(key)){
				return object.getString(key);
			}
		}catch(Exception e){
			return null;
		}
		return null;
	}
	
	/**
	* 返还json数组的长度
	* @param array
	*  json数组的string
	* @return int
	*  返回长度
	*/ 
	public static int getJSONArrayLength(String array){
		try{
			JSONArray jsonArray = JSONArray.fromObject(array);
			return jsonArray.size();
		}catch(Exception e){
			return 0;
		}
	}
	
	/**
	* 通过json数组获取到第num个的object，返回string
	* @param array
	*  json的array
	* @param key
	*  需要获取的第几个值num
	* @return String
	*  如果是没去到返回空
	*/ 
	public static String getObjectByKey(String array,int num){
		try{
			JSONArray jsonArray = JSONArray.fromObject(array);
			if(num>=0 && jsonArray.size()>num){
				String o = jsonArray.getString(num);
				return o;
			}
		}catch(Exception e){
			return null;
		}
		return null;
	}
	
	/**
	* 获取object中的key对应的值，返回string
	* @param object
	*  json的String
	* @param key
	*  需要获取的key
	* @return String
	*  如果是没去到返回空
	*/ 
	public static String getValueByKey(String object,String key){
		try{
			JSONObject ob = JSONObject.fromObject(object);
			if(ob.containsKey(key)){
				return ob.getString(key);
			}
		}catch(Exception e){
			return null;
		}
		return null;
	}
}
