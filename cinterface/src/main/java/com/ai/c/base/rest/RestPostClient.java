package com.ai.c.base.rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

/**
 * 实现HTTPClient的调用封装，更便于调用本base模式下的POST接口
 */
public class RestPostClient extends RestClientBase {

	public String callRestRPC(String requestURI,
			Map<String, String> headerParam, Map<String, String> postParam) {
		try {
			HttpPost httpPost = new HttpPost();

			setURI(httpPost, requestURI);

			initHttpHeader(httpPost, headerParam);

			if (postParam != null) {
				List<NameValuePair> params = new ArrayList<NameValuePair>();
				for (Map.Entry<String, String> entry : postParam.entrySet()) {
					params.add(new BasicNameValuePair(entry.getKey(), entry
							.getValue()));
				}
				HttpEntity entity = new UrlEncodedFormEntity(params, "utf-8");
				httpPost.setEntity(entity);
			}
			System.out.println(EntityUtils.toString(httpPost.getEntity()));
			return getResponse(httpPost);
		} catch (URISyntaxException e) {
			e.printStackTrace();
			System.err.println("Error request uri: " + requestURI);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			System.err.println("Error request param on :  " + requestURI);
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String callRestRPC(String requestURI, Map<String, String> postParam) {
		try {
			HttpPost httpPost = new HttpPost();

			setURI(httpPost, requestURI);

			if (postParam != null) {
				List<NameValuePair> params = new ArrayList<NameValuePair>();
				for (Map.Entry<String, String> entry : postParam.entrySet()) {
					params.add(new BasicNameValuePair(entry.getKey(), entry
							.getValue()));
				}
				HttpEntity entity = new UrlEncodedFormEntity(params, "utf-8");
				httpPost.setEntity(entity);
			}
			System.out.println(EntityUtils.toString(httpPost.getEntity()));
			return getResponse(httpPost);
		} catch (URISyntaxException e) {
			e.printStackTrace();
			System.err.println("Error request uri: " + requestURI);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			System.err.println("Error request param on :  " + requestURI);
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String callRestRPCJson(String requestURI,String jsonstring) {
		HttpPost httpPost = new HttpPost();
		StringBuilder sbr=new StringBuilder();
		try{
			    setURI(httpPost, requestURI);
	            StringEntity se = new StringEntity(jsonstring, "utf-8");
	            se.setContentEncoding("UTF-8");    
	            se.setContentType("application/json");  
	            httpPost.setEntity(se);
	            InputStream in=getResponseInput(httpPost);
	            InputStreamReader inputStreamReader = new InputStreamReader(in,"UTF-8");
	            BufferedReader reader = new BufferedReader(inputStreamReader);// 读字符串用的。
	                String s;
	                while (((s = reader.readLine()) != null)) {
	                	sbr.append(s);
	                }
	                reader.close();// 关闭输入流
	                }catch (URISyntaxException e) {
	                	e.printStackTrace();
	                }catch(IOException e){
	                	e.printStackTrace();
	                }
		   return sbr.toString(); 
	 
	    }
}
