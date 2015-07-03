package com.ai.c.base.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author LIUQIANMING
 * @time 2015-06-22 上午11:06:16
 * 
 */

public final class TimestampUtil {
	private static SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

	public static String getTimeStamp() {
		return dateFormat.format(new Date());
	}
	
	public static Date parserToDate(String date) throws ParseException {
		return dateFormat.parse(date);
	}
	
	public static String parserDateToString(Date date) throws ParseException {
		return dateFormat.format(date);
	}
}
