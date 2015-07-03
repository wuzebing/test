package com.ai.c.base.util;

import java.util.regex.Pattern;

/**
 * @author zouning
 * @time 2014-8-14 下午4:19:00
 * 
 */

public final class EmailCheckerUtil {
	/**
	 * 检查邮箱地址是否正确的正则表达式
	 */
	private static final String emailChecker = "^([a-z0-9A-Z_]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
	
	/**
	 * 检查电子邮箱地址的合法性
	 * @param emailAddress
	 * @return
	 */
	public final static boolean isValidEmail(final String emailAddress){
		try {
			Pattern emailPattern = Pattern.compile(emailChecker);
			return emailPattern.matcher(emailAddress).matches();
		} catch (Exception e) {
			return false;
		}
	}
}
