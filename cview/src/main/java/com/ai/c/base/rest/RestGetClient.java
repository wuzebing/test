package com.ai.c.base.rest;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.client.methods.HttpGet;

/**
 * Description: 实现HTTPClient的调用封装，更便于调用本base模式下的GET接口
 */
public class RestGetClient extends RestClientBase {

	public String callRestRPC(String requestURI, Map<String, String> bodyParam,
			HttpServletRequest request) {
		try {
			HttpGet httpGet = new HttpGet();
			// setURI(httpGet, requestURI);

			// initHttpHeader(httpGet, headerParam);
			StringBuilder sb = new StringBuilder();
			sb.append(requestURI);
			if (bodyParam != null) {
				addCommonParams(request, bodyParam);
				sb.append("?");
				for (String key : bodyParam.keySet()) {
					sb.append(key)
							.append("=")
							.append(URLEncoder.encode(bodyParam.get(key),
									"UTF-8")).append("&");
				}
				System.out.println(sb.substring(0, sb.length() - 1));
				setURI(httpGet, sb.substring(0, sb.length() - 1));
			} else {
				setURI(httpGet, requestURI);
			}

			return getResponse(httpGet);
		} catch (URISyntaxException e) {
			e.printStackTrace();
			System.out.println("Error request uri: " + requestURI);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
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
}
