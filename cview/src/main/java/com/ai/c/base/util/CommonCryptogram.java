package com.ai.c.base.util;

import com.ai.c.base.encrypt.Cryptogram;



/**
 * 积分平台门户通用加密方法类
 * @author LIUQIANMING
 * @time 2014-8-1 下午4:30:54
 * 
 */
public final class CommonCryptogram {
	private static final String key = "FB1C9B45759BF8BAD5320E8A5757C4F84A85232CE50825A2";

	/**
	 * 加密啊
	 * 
	 * @param str
	 *            原始字符串
	 * @return 加密后的字符串.如果失败返回""
	 */
	public static final String encrypt(String str) {
		try {
			return Cryptogram.encryptByKey(str, key);
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	/**
	 * 解密
	 * 
	 * @param str
	 *            密文
	 * @return 解密后的字符串.如果失败,返回""
	 */
	public static final String decrypt(String str) {
		try {
			return Cryptogram.decryptByKey(str, key);
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
   public static void main(String args[]){
	   System.out.println(decrypt("ImHsSYNAQxjhYknnvlbo2A=="));
	   
   }
}
