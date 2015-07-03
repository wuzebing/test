package com.ai.c.base.rest;

import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.Map;

import org.apache.http.client.methods.HttpGet;

/**
 * Description: 实现HTTPClient的调用封装，更便于调用本base模式下的GET接口
 */
public class RestGetClient extends RestClientBase {

	public String callRestRPC(String requestURI, Map<String, String> bodyParam) {
		try {
			HttpGet httpGet = new HttpGet();
			// setURI(httpGet, requestURI);

			// initHttpHeader(httpGet, headerParam);
			StringBuilder sb = new StringBuilder();
			sb.append(requestURI);
			if (!bodyParam.isEmpty()) {
				sb.append("?");
				for (String key : bodyParam.keySet()) {
					sb.append(key).append("=").append(bodyParam.get(key))
							.append("&");
				}
				setURI(httpGet, sb.substring(0, sb.length() - 1));
			} else {
				setURI(httpGet, requestURI);
			}

			return getResponse(httpGet);
		} catch (URISyntaxException e) {
			e.printStackTrace();
			System.out.println("Error request uri: " + requestURI);
		}
		return null;
	}
	public InputStream callRestRPCInput(String requestURI, Map<String, String> bodyParam) {
		try {
			HttpGet httpGet = new HttpGet();
			// setURI(httpGet, requestURI);

			// initHttpHeader(httpGet, headerParam);
			StringBuilder sb = new StringBuilder();
			sb.append(requestURI);
			if (!bodyParam.isEmpty()) {
				sb.append("?");
				for (String key : bodyParam.keySet()) {
					sb.append(key).append("=").append(bodyParam.get(key))
							.append("&");
				}
				setURI(httpGet, sb.substring(0, sb.length() - 1));
			} else {
				setURI(httpGet, requestURI);
			}

			return getResponseInput(httpGet);
		} catch (URISyntaxException e) {
			e.printStackTrace();
			System.out.println("Error request uri: " + requestURI);
		}
		return null;
	}
	public InputStream callRestRPCInput(String requestURI, String bodyParam) {
		try {
			HttpGet httpGet = new HttpGet();
			// setURI(httpGet, requestURI);
				setURI(httpGet, bodyParam);
			return getResponseInput(httpGet);
		} catch (URISyntaxException e) {
			e.printStackTrace();
			System.out.println("Error request uri: " + requestURI);
		}
		return null;
	}
	 
}
