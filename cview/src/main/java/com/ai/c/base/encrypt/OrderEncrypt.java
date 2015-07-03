package com.ai.c.base.encrypt;

import java.util.UUID;
/**
 * @author LiuQianMing
 * @create 2015-01-05
 */
public class OrderEncrypt {
	/**
	 * 通过获取UUID 然后用Base64加密，然后再通过MD5加密完成唯一字符串
	 * @return MD5 加密字符串
	 */
	public final static String userEncrypt(){
		/*
		 * 获取UUID
		 */
		UUID uuid = UUID.randomUUID(); 
		/*
		 * 获取base64加密字符串
		 */
		String str=Base64Encrypt.getBASE64(uuid.toString());
		/*
		 * 返回MD5加密字符串
		 */
		return MD5Util.MD5(str);
	}
	public static void main(String[] args) {
		System.out.println(OrderEncrypt.userEncrypt());
		String str="123kingkey0109900010198064C1F2113410EBF98F4340015B8F8D8E";
		System.out.println(str.substring(str.indexOf("kingkey")));
	}
}
