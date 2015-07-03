package com.ai.c.base.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtil {
	
	public static void setCookie(HttpServletResponse response, String key, String value) {
		try{
			Cookie cookie = new Cookie(key, URLEncoder.encode(value,"utf-8"));
			response.addCookie(cookie);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	
	public static String getCookie(HttpServletRequest request, String key) {
		Cookie[] cookieArray = request.getCookies();
		if(cookieArray != null){
			for(Cookie cookie : cookieArray){
				if(cookie.getName().equals(key)){
					try{
						return URLDecoder.decode(cookie.getValue(), "utf-8");
					}catch(Exception e){
						throw new RuntimeException(e);
					}
				}
			}
		}
		return null;
	}
	
	public static void main(String[] args) throws UnsupportedEncodingException {
		String ss = URLEncoder.encode("wfwe威锋网f","utf-8");
		System.out.println(ss);
		System.out.println(URLDecoder.decode(ss, "utf-8"));
	}
}
