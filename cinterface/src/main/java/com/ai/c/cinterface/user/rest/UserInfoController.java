package com.ai.c.cinterface.user.rest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.transaction.TransactionException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ai.c.base.controller.BaseSpringController;
import com.ai.c.base.entity.JsonResult;
import com.ai.c.base.resultcode.ResultCode;
import com.ai.c.cinterface.user.status.OtherConst;
import com.ai.c.cinterface.user.service.UserInfoService;
import com.ai.c.base.annotation.Auth;

@Controller
@RequestMapping("/user")
public class UserInfoController extends BaseSpringController{
	private static Logger logger = Logger.getLogger(UserInfoController.class);
	@Autowired
	private UserInfoService userInfoService;
	/**
	 * 登录
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@Auth(auth=true)
	@ResponseBody
	public JsonResult login(HttpServletRequest request,
			HttpServletResponse response) {
		JsonResult jsonResult = new JsonResult();
		try {
			String ip = request.getParameter("ip");
			String timestamp = request.getParameter("timestamp");
			String sysId = request.getParameter("sysId");
			String userName = request.getParameter("userName");
			String passWord = request.getParameter("passWord");
			String passwordType = OtherConst.PASSWORDTYPE.getParams();
			//01 pc 02 手机 
			String loginSource = request.getParameter("loginSource");
			if (StringUtils.isEmpty(ip) || StringUtils.isEmpty(timestamp)
					|| StringUtils.isEmpty(userName)
					|| StringUtils.isEmpty(passWord)
					|| StringUtils.isEmpty(sysId) || StringUtils.isEmpty(loginSource)) {
				jsonResult.setStatusCode(ResultCode.PARAMS_NULL.getCode());
				jsonResult.setReturnObj(ResultCode.PARAMS_NULL.getDesc());
				return jsonResult;
			}

			// 登录
			String token = userInfoService.login(userName, passWord, passwordType,
					sysId, "/login", ip,loginSource);
			if (token == null) {
				jsonResult.setStatusCode(ResultCode.USER_ERROR.getCode());
				jsonResult.setReturnObj(ResultCode.USER_ERROR.getDesc());
				return jsonResult;
			}
			jsonResult.setStatusCode(ResultCode.SUCCESS.getCode());
			jsonResult.setReturnObj(token);
		} catch (TransactionException e) {
			String msg = e.getMessage();
			if ("903".equals(msg)) {
				jsonResult.setStatusCode(ResultCode.USER_STATUS_ERROR.getCode());
				jsonResult.setReturnObj(ResultCode.USER_STATUS_ERROR.getDesc());
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return jsonResult;
	}

}
