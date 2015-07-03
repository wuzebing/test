package com.ai.c.base.interceptor;

import java.net.URLDecoder;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.ai.c.base.util.IPUtil;

/**
 * 用于记录日志,优先级应该设置为最高
 * 
 * @author zouning
 * @time 2014-7-23 下午4:28:08
 * 
 */

public class LogInterceptor implements HandlerInterceptor {
	Logger logger = Logger.getLogger(getClass());

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object arg2, Exception arg3)
			throws Exception {

	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object arg2, ModelAndView arg3)
			throws Exception {
	}

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object arg2) throws Exception {
		StringBuilder sb = new StringBuilder();
		String uri = request.getRequestURI();
		String lowUrl=request.getQueryString();
		String contrlUrl=URLDecoder.decode(uri+lowUrl,"utf-8").toLowerCase().replaceAll(" ", "");
		if(contrlUrl!=null&&!contrlUrl.contains("/billpage/")&&!contrlUrl.contains("/displayqrcode")&&!contrlUrl.contains("/k/")){
			//由于商品信息中带有[] 所以考虑后去掉contrlUrl.contains("[") ||contrlUrl.contains("]")|| 这个拦块
			//浏览器端扫码会出现该字段所以先去掉||contrlUrl.contains("(")||contrlUrl.contains(")")||contrlUrl.contains(";")
			if(contrlUrl.contains("alert")||contrlUrl.contains("<")||contrlUrl.contains(">")||contrlUrl.contains("script")
					 ||contrlUrl.contains("\\")||contrlUrl.contains("(")||contrlUrl.contains(")")||contrlUrl.contains(";")
					 ||contrlUrl.contains("\"")||contrlUrl.contains("'"))
			{
				logger.error("有非法的访问请求地址："+"lowUrl="+contrlUrl+" IP="+IPUtil.getIP(request));
				return false; 
			}
		} 
		sb.append("---------------> kinterface uri:").append(uri).append(" - ");

		Enumeration<String> enums2 = request.getParameterNames();
		while (enums2.hasMoreElements()) {
			String key = enums2.nextElement();
			sb.append("\"").append(key).append("\":").append(
					request.getParameter(key)).append(", ");
		}
		logger.info(sb.toString());
		return true;
	}
}
