package com.ai.c.base.util;

import java.math.BigInteger;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ControllerUtil {

	public static final String DATE_FORMAT = "yyyy-MM-dd";
	public static final String TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

	/** 空参数校验 */
	public static String notEmpty(String[] paramNames, String[] paramValues){
		List<String> paramNameList = new ArrayList<String>();
		//遍历paramValues，如果参数值为空，将参数名加入paramNameList
		for(int i=0;i<paramNames.length;i++){
			String paramName = paramNames[i];
			String paramValue = paramValues[i];
			if(paramValue == null || "".equals(paramValue)){
				paramNameList.add(paramName);
			}
		}
		//返回错误消息
		String message = null;
		if(paramNameList.size() != 0){
			StringBuffer sb = new StringBuffer();
			sb.append("参数[");
			for(String paramName : paramNameList){
				sb.append(paramName+",");
			}
			if(paramNameList.size() > 0){
				sb.deleteCharAt(sb.length()-1);
			}
			sb.append("]不能为空!");
			message = sb.toString();
		}
		return message;
	}
	
	/** 时间参数格式校验 */
	public static String dateFormatValidate(String[] paramNames, String[] paramValues){
		List<String> paramNameList = new ArrayList<String>();
		//遍历paramValues，如果时间参数格式不正确，将参数名加入paramNameList
		DateFormat df = new SimpleDateFormat(DATE_FORMAT);
		for(int i=0;i<paramNames.length;i++){
			String paramName = paramNames[i];
			String paramValue = paramValues[i];
			//如果时间不为null，因为前面notEmpty()方法已经校验过空了
			if(paramValue != null){
				try {
					df.parse(paramValue);
				} catch (ParseException e) {
					paramNameList.add(paramName);
				}
			}
		}
		//返回错误消息
		String message = null;
		if(paramNameList.size() != 0){
			StringBuffer sb = new StringBuffer();
			sb.append("参数[");
			for(String paramName : paramNameList){
				sb.append(paramName+",");
			}
			if(paramNameList.size() > 0){
				sb.deleteCharAt(sb.length()-1);
			}
			sb.append("]格式不正确!");
			message = sb.toString();
		}
		return message;
	}
	
	/** sequenceID格式校验 */
	public static String bigIntegerFormatValidate(String str){
		String message = null;
		try{
			new BigInteger(str);
		}catch(Exception e){
			return "sequenceID格式不正确!";
		}
		return message;
	}
	
}
