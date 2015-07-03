package com.ai.c.cview.home.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
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

}
