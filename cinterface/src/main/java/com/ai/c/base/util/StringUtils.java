package com.ai.c.base.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 *  去除字符串的回车、换行符、制表符</p>
 * @author LiuQianMing
 * 2015-04-20
 * 
 * 
 */
public class StringUtils {
	 public static String replaceBlank(String str) {
		  String dest = "";
		  if (str!=null) {
		   Pattern p = Pattern.compile("\\s*|\t|\r|\n");
		   Matcher m = p.matcher(str);
		   dest = m.replaceAll("");
		  }
		  return dest;
		 }
	 public static void main(String[] args) {
		String str="12232'   ";
		Pattern p = Pattern.compile("\\s*|\t|\r|\n'");
	    Matcher m = p.matcher(str);
	    System.out.println(m.replaceAll(""));
	}

}
