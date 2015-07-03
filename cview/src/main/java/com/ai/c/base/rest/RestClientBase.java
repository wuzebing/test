package com.ai.c.base.rest;

import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLException;
import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import com.ai.c.base.encrypt.Cryptogram;
import com.ai.c.base.util.ConfigUtils;
import com.ai.c.base.util.CookieUtil;
import com.ai.c.base.util.IPUtil;
import com.ai.c.base.util.TimestampUtil;

/**
 */
public abstract class RestClientBase {

	protected String baseHost = null;

	private static PoolingHttpClientConnectionManager cm = null;

	public String getBaseHost() {
		return baseHost;
	}

	public void setBaseHost(String baseHost) {
		this.baseHost = baseHost;
	}

	protected void setURI(HttpRequestBase requestBase, String requestURI)
			throws URISyntaxException {
		String uri;
		if (this.getBaseHost() != null) {
			uri = this.getBaseHost() + requestURI;
		} else {
			uri = requestURI;
		}
		requestBase.setURI(new URI(uri));
	}

	protected void initHttpHeader(HttpRequestBase requestBase,
			Map<String, String> headerParam) {
		if (headerParam != null) {
			for (Map.Entry<String, String> entry : headerParam.entrySet()) {
				requestBase.addHeader(entry.getKey(), entry.getValue());
			}
		}
	}

	protected String getResponse(HttpRequestBase requestBase) {
		CloseableHttpResponse response = null;
		try {
			// 以下是老版本的做法
			// DefaultHttpClient client = new DefaultHttpClient();
			// HttpResponse response = client.execute(requestBase);

			// 以下httpclient 4.3的官方指南修改
			CloseableHttpClient httpClient = HttpClients
					.custom()
					.setConnectionManager(
							getPoolingHttpClientConnectionManager())
					.setRetryHandler(myRetryHandler).build();

			response = httpClient.execute(requestBase);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				return EntityUtils.toString(entity, "UTF-8");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Http request failed on "
					+ requestBase.getURI().toString());
		} finally {
			try {
				response.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		return null;
	}

	private static PoolingHttpClientConnectionManager getPoolingHttpClientConnectionManager() {
		if (cm != null) {
			return cm;
		}

		cm = new PoolingHttpClientConnectionManager();
		// Increase max total connection to 200
		cm.setMaxTotal(200);
		// Increase default max connection per route to 20
		cm.setDefaultMaxPerRoute(40);// 官方值20,但我们的系统出口的地址不多,就40吧
		// Increase max connections for localhost:80 to 50
		HttpHost localhost = new HttpHost("locahost", 80);
		cm.setMaxPerRoute(new HttpRoute(localhost), 50);

		return cm;
	}

	/***
	 * 重试策略
	 */
	private HttpRequestRetryHandler myRetryHandler = new HttpRequestRetryHandler() {

		public boolean retryRequest(IOException exception, int executionCount,
				HttpContext context) {
			if (executionCount >= 5) {
				// Do not retry if over max retry count
				return false;
			}
			if (exception instanceof InterruptedIOException) {
				// Timeout
				return false;
			}
			if (exception instanceof UnknownHostException) {
				// Unknown host
				return false;
			}
			if (exception instanceof ConnectTimeoutException) {
				// Connection refused
				return false;
			}
			if (exception instanceof SSLException) {
				// SSL handshake exception
				return false;
			}
			HttpClientContext clientContext = HttpClientContext.adapt(context);
			HttpRequest request = clientContext.getRequest();
			boolean idempotent = !(request instanceof HttpEntityEnclosingRequest);
			if (idempotent) {
				// Retry if the request is considered idempotent
				return true;
			}
			return false;
		}

	};

	public static String getIP(HttpServletRequest request) {
		return IPUtil.getIP(request);
	}

	/**
	 * 添加公共的参数,ip,timestamp,sysId,authenticator,token
	 * 
	 * @param request
	 * @param bodyParam
	 */
	protected void addCommonParams(HttpServletRequest request,
			Map<String, String> bodyParam) {
		String ip = getIP(request);
		// ip
		bodyParam.put("ip", ip);
		// timestamp
		String timestamp = TimestampUtil.getTimeStamp();
		bodyParam.put("timestamp", timestamp);

		String token = CookieUtil.getCookie(request, "token");
		if (token != null) {
			bodyParam.put("token", token);
		}

		// sysId 系统id
		String sysId = null;
		if (token != null && token.length() > 3 && !"null".equals(token)) {
			sysId = token.substring(0, 3);
		} else {
			sysId = ConfigUtils.getStringValue("sysId");
		}
		bodyParam.put("sysId", sysId);

		// token,从session,cookie获取
		// bodyParam
		// .put("token",
		// "007123456zou02002014-07-30 15:48:19192.168.1.1\nJn521K4IcXwLPzmII3f75UO91JU=");

		// authenticator
		String key = ConfigUtils.getStringValue("key");
		String authenticator = getAuthenticator(bodyParam, key, sysId,
				timestamp);
		// System.out.println("authenticator:" + authenticator);
		bodyParam.put("authenticator", authenticator);
	}

	/**
	 * <pre>
	 * 所有请求参数(authenticator还没生成,故没有)按照字母排序,拼成字符串,生成digest
	 * sysID + "$" + timestamp + "$" + digest 加密生成authenticator
	 * </pre>
	 * 
	 * @param bodyParam
	 * @param key
	 * @param sysId
	 * @param timestamp
	 * @return
	 */
	private String getAuthenticator(Map<String, String> bodyParam, String key,
			String sysId, String timestamp) {
		try {
			// List<String> list = new ArrayList<String>(bodyParam.values());
			List<String> list = new ArrayList<String>(bodyParam.size());
			for (String paramKey : bodyParam.keySet()) {
				String value = bodyParam.get(paramKey);
				if (!org.apache.commons.lang.StringUtils.isEmpty(value)) {
					list.add(value);
				}
			}
			Collections.sort(list);

			StringBuilder sb = new StringBuilder();
			for (String paramKey : list) {
				// System.out.println(paramKey);
				sb.append(paramKey);
			}
			String digest = Cryptogram.getBase64HashString(sb.toString());

			sb.setLength(0);
			sb.append(sysId).append("$").append(timestamp).append("$")
					.append(digest);
			return Cryptogram.encryptByKey(sb.toString(), key);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	protected InputStream getResponseInput(HttpRequestBase requestBase) {
//		try {
//			DefaultHttpClient client = new DefaultHttpClient();
//
//			HttpResponse response = client.execute(requestBase);
			CloseableHttpResponse response = null;
			try {
				// 以下是老版本的做法
				// DefaultHttpClient client = new DefaultHttpClient();
				// HttpResponse response = client.execute(requestBase);

				// 以下httpclient 4.3的官方指南修改
				CloseableHttpClient httpClient = HttpClients
						.custom()
						.setConnectionManager(
								getPoolingHttpClientConnectionManager())
						.setRetryHandler(myRetryHandler).build();

				response = httpClient.execute(requestBase);
			// response.getEntity().getContent()
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				return entity.getContent();
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("Http request failed on "
					+ requestBase.getURI().toString());
		}
		return null;
	}
}
