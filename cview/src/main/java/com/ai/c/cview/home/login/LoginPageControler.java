package com.ai.c.cview.home.login;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ai.c.base.entity.JsonResult;
import com.ai.c.base.rest.RestPostClient;
import com.ai.c.base.util.CommonCryptogram;
import com.ai.c.base.util.ConfigUtils;
import com.ai.c.base.util.IPUtil;
import com.ai.c.base.util.JsonUtil;
/**
 * CopyRright (c)2015
 * Comments:首页请求处理类
 * Author: LIUQIANMING
 * Create Date:2015-06-30
 * Modified By:
 * Modified Date:
 */
@Controller
@RequestMapping("/homePage")
public class LoginPageControler {

	Logger logger = Logger.getLogger(getClass());
	/**
     * 未登录首页-跳转登录页面
     * <功能详细描述>
     * @param request HTTP请求对象
     * @param response HTTP响应对象
     * @throws Exception 异常信息
     * @see [类、类#方法、类#成员]
     */
	@RequestMapping(value = "/home/login", method = RequestMethod.GET)
	public ModelAndView homeLogin(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("");
		modelAndView.setViewName("/home/login/login");
		return modelAndView;
	}
	
	/**
     * 登录首页-登录验证
     * <功能详细描述>
     * @param request HTTP请求对象
     * @param response HTTP响应对象
     * @throws Exception 异常信息
     * @see [类、类#方法、类#成员]
     */
	@RequestMapping(value = "/user/login", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult userLogin(HttpServletRequest request, HttpServletResponse response,
			@CookieValue(value = "verifyCode", required = true) String verifyCodeFromCookie)
	throws Exception {
		JsonResult jsonResult = new JsonResult(); 
		String userName=request.getParameter("userName");
		String passWord=request.getParameter("passWord");
		String verifyCode = request.getParameter("verifyCode");
		String loginSource = request.getParameter("loginSource");
		if(StringUtils.isEmpty(loginSource)){
			loginSource=ConfigUtils.getStringValue("user.loginSource");
		}
				
		if (StringUtils.isEmpty(userName)) {
			logger.error("用户名不能为空");
			jsonResult.setStatusCode(222);
			jsonResult.setReturnObj("用户名不能为空");
			return jsonResult;
		}
		if (StringUtils.isEmpty(passWord)) {
			logger.error("密码不能为空");
			jsonResult.setStatusCode(222);
			jsonResult.setReturnObj("密码不能为空");
			return jsonResult;
		}
		if (StringUtils.isEmpty(verifyCode)) {
			logger.error("校验码为空");
			jsonResult.setStatusCode(222);
			jsonResult.setReturnObj("校验码为空");
			return jsonResult;
		}
		String verifyCodeMing = CommonCryptogram.decrypt(verifyCodeFromCookie);
		if (!StringUtils.isEmpty(verifyCodeMing)
				&& verifyCodeMing.equalsIgnoreCase(verifyCode)) {
		} else {
			logger.error("校验码不正确");
			jsonResult.setStatusCode(222);
			jsonResult.setReturnObj("校验码不正确");
			return jsonResult;
		}
		String headUrl = ConfigUtils.getStringValue("api.serverURL");
		String tailUrl = ConfigUtils.getStringValue("user.login");
		String requestURI = headUrl + tailUrl;
		Map<String, String> bodyParam = new HashMap<String, String>();
		bodyParam.put("passWord", CommonCryptogram.encrypt(passWord));
		bodyParam.put("userName", userName);
		bodyParam.put("loginSource", loginSource);

		// 调REST接口
		String result = new RestPostClient().callRestRPC(requestURI, bodyParam,
				request);
		if (result == null) {
			logger.error("调用后台接口返回值为null");
			jsonResult.setStatusCode(222);
			jsonResult.setReturnObj("调用接口返回空");
			return jsonResult;
		}

		jsonResult = JsonUtil.fromJson(result, JsonResult.class);
		return jsonResult;
	}

}
