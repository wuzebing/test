package com.ai.c.base.interceptor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.ai.c.base.annotation.Auth;
import com.ai.c.base.encrypt.Cryptogram;
import com.ai.c.base.entity.JsonResult;
import com.ai.c.base.entity.Token;
import com.ai.c.base.resultcode.ResultCode;
import com.ai.c.base.util.GetTokenUtil;
import com.ai.c.base.util.JsonUtil;
import com.ai.c.cinterface.par.service.ParSystemService;

public class AuthInterceptor implements HandlerInterceptor {
	
	Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private ParSystemService parSystemService;

	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object obj) throws Exception {
		if(obj instanceof HandlerMethod){
			HandlerMethod handlerMethod = (HandlerMethod)obj;
			Auth auth = handlerMethod.getMethod().getAnnotation(Auth.class);
			if(auth != null){
				JsonResult jsonResult = new JsonResult();
				if(auth.auth()){
					int validAuthCode = validAuthenticator(request);
			        if (validAuthCode != ResultCode.SUCCESS.getCode())
			        {
			        	this.responseToJson(response, jsonResult);
			            return false;
			        }
			        
				}
				if(auth.login()){
					Token token = GetTokenUtil.getToken(request);
					if (null == token || ResultCode.SUCCESS.getCode() != token.getStatusCode()
			                || StringUtils.isEmpty(token.getUserId()))
		            {
						this.responseToJson(response, jsonResult);
		                return false;
		            }
					request.setAttribute("token", token);
				}
				return true;
			}
		}
		return true;
	}
	
	/*
	 * 校验auth串
	 * 
	 * @param request
	 * @return 200为正确
	*/
	public int validAuthenticator(HttpServletRequest request) {
		try {
			String sysId = request.getParameter("sysId");
			String authenticator = request.getParameter("authenticator");

			// 非空判断
			if (StringUtils.isEmpty(sysId)
					|| StringUtils.isEmpty(authenticator)) {
				return 201;// 请求参数为空
			}

			String key = parSystemService.getSysKeyBySysId(sysId);
			if (StringUtils.isEmpty(key)) {
				// 根据sysId获取key,如果sysId不存在则返回202
				return 202;
			}

			Enumeration<String> enums2 = request.getParameterNames();
			List<String> params = new ArrayList<String>();
			while (enums2.hasMoreElements()) {
				String paramKey = enums2.nextElement();
				if ("authenticator".equals(paramKey)) {
					continue;
				}

				String value = request.getParameter(paramKey);
				if (!org.apache.commons.lang.StringUtils.isEmpty(value)) {
					params.add(value);
				}
				// params.add(request.getParameter(paramKey));
			}
			Collections.sort(params);// 按字母排序
			StringBuilder sb = new StringBuilder();
			for (String aParamValue : params) {
				sb.append(aParamValue);
			}
			// 根据请求参数生成的digest
			String digest = Cryptogram.getBase64HashString(sb.toString());

			// 从auth解释出digest
			String strParams = Cryptogram.decryptByKey(authenticator, key);
			String[] authParams = strParams.split("\\$");

			String digestFromAuth = authParams[2];

			if (digest.equals(digestFromAuth)) {
				return 200;// auth一致
			}
		} catch (Exception e) {
			logger.error("校验Authenticator异常", e);
		}

		return 999;
	}
	
	/**
	 * 以json形式回复
	 * 
	 * @param response
	 * @param jsonResult
	 * @throws Exception
	 */
	public void responseToJson(HttpServletResponse response, JsonResult jsonResult) throws Exception{
		
		jsonResult.setStatusCode(ResultCode.AUTHENTICATOR_ERROR.getCode());
        jsonResult.setReturnObj(ResultCode.AUTHENTICATOR_ERROR.getDesc());
        response.getWriter().write(JsonUtil.toJson(jsonResult));
        response.setStatus(200);
        response.setContentType("text/json;charset=UTF-8");
        logger.error("接口安全校验失败：" + jsonResult);
        response.flushBuffer();
        
	}

}
