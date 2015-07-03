package com.ai.c.base.util;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Encoder;

public class HmacSHA1Encrypt {
	public static String HmacSHA1Encrypt(String encryptText, String encryptKey) throws Exception,UnsupportedEncodingException,NoSuchAlgorithmException, InvalidKeyException {
        byte[] data = encryptKey.getBytes("UTF-8");
        // 根据给定的字节数组构造一个密钥,第二参数指定一个密钥算法的名称
        SecretKey secretKey = new SecretKeySpec(data, "HMAC-SHA1");
        // 生成一个指定 Mac 算法 的 Mac 对象
        Mac mac = Mac.getInstance("HmacSHA1");
        // 用给定密钥初始化 Mac 对象
        mac.init(secretKey);
        byte[] text = encryptText.getBytes("UTF-8");
        // 完成 Mac 操作
        return getBase64Bytes(mac.doFinal(text));
    }
	
	/**
	 * HAMC_SHA1加密，返回16进制的字符串
	 * @param encryptText
	 * @param encryptKey
	 * @return
	 * @throws Exception
	 * @throws UnsupportedEncodingException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeyException
	 */
	public static String HmacSHA1EncryptHex(String encryptText, String encryptKey) throws Exception,UnsupportedEncodingException,NoSuchAlgorithmException, InvalidKeyException {
        byte[] data = encryptKey.getBytes("UTF-8");
        // 根据给定的字节数组构造一个密钥,第二参数指定一个密钥算法的名称
        SecretKey secretKey = new SecretKeySpec(data, "HMAC-SHA1");
        // 生成一个指定 Mac 算法 的 Mac 对象
        Mac mac = Mac.getInstance("HmacSHA1");
        // 用给定密钥初始化 Mac 对象
        mac.init(secretKey);
        byte[] text = encryptText.getBytes("UTF-8");
        // 完成 Mac 操作
        return bytesToHexString(mac.doFinal(text));
    }
	   @SuppressWarnings("restriction")
	public static String getBase64(String str) 
	   {  
		    byte[] b = null;  
		    String s = null;  
		     try {  
		             b = str.getBytes("UTF-8");  
		         } catch (UnsupportedEncodingException e) {  
		             e.printStackTrace();  
		          }  
		         if (b != null) {  
		               s = new BASE64Encoder().encode(b);  
		           }  
		    return s;  
		}  
	@SuppressWarnings("restriction")
	public static String getBase64Bytes(byte[] b) 
	{  
	     String s = null;  
		 if (b != null) 
		 {  
			  s = new BASE64Encoder().encode(b);  
		 }  
	    return s;  
	}
	
	public static String bytesToHexString(byte[] bytes) {
	    StringBuilder sb = new StringBuilder(bytes.length * 2);
	 
	    Formatter formatter = new Formatter(sb);
	    for (byte b : bytes) {
	        formatter.format("%02x", b);
	    }
	 
	    return sb.toString();
	}
	
	public static void main(String[] args) {
		String str = "18903711181,42014001,20141102163838";
		String str1 = "73AJc0R9O2nN*xm&";
		try {
			System.out.println(HmacSHA1Encrypt(str, str1).toString());
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
