package com.ai.c.base.mvc;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 用于记录日志,优先级应该设置为�?��
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
