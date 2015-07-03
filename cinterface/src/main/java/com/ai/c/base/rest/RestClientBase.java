package com.ai.c.base.rest;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;


/**
 */
public abstract class RestClientBase {

	protected String baseHost = null;

	public String getBaseHost() {
		return baseHost;
	}

	public void setBaseHost(String baseHost) {
		this.baseHost = baseHost;
	}

	protected void setURI(HttpRequestBase requestBase, String requestURI) throws URISyntaxException {
		String uri;
		if (this.getBaseHost() != null) {
			uri = this.getBaseHost() + requestURI;
		} else {
			uri = requestURI;
		}
		requestBase.setURI(new URI(uri));
	}

	protected void initHttpHeader(HttpRequestBase requestBase, Map<String, String> headerParam) {
		if (headerParam != null) {
			for (Map.Entry<String, String> entry : headerParam.entrySet()) {
				requestBase.addHeader(entry.getKey(), entry.getValue());
			}
		}
	}

	protected String getResponse(HttpRequestBase requestBase) {
		try {
			DefaultHttpClient client = new DefaultHttpClient();

			HttpResponse response = client.execute(requestBase);
			
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				return EntityUtils.toString(entity, "UTF-8");
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("Http request failed on " +  requestBase.getURI().toString());
		}
		return null;
	}
	protected InputStream getResponseInput(HttpRequestBase requestBase) {
		try {
			DefaultHttpClient client = new DefaultHttpClient();

			HttpResponse response = client.execute(requestBase);
			//response.getEntity().getContent()
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				return entity.getContent();
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("Http request failed on " +  requestBase.getURI().toString());
		}
		return null;
	}
}
