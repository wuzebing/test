package com.ai.c.cinterface.permission.rest;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ai.c.base.controller.BaseSpringController;
import com.ai.c.base.dao.Page;
import com.ai.c.base.entity.JsonResult;
import com.ai.c.base.entity.Token;
import com.ai.c.base.resultcode.ResultCode;
import com.ai.c.base.util.TokenUtil;
import com.ai.c.base.util.ValidateUtil;
import com.ai.c.cinterface.par.service.ParSystemService;
import com.ai.c.cinterface.permission.entity.AiUserJobInfo;
import com.ai.c.cinterface.permission.service.AiUserJobInfoService;
import com.enterprisedt.util.debug.Logger;


@Controller
@RequestMapping("/permission/aiUserJobInfo")
public class AiUserJobInfoController extends BaseSpringController {
	
	private Logger logger = Logger.getLogger(getClass());
	
	@Autowired
	private AiUserJobInfoService aiUserJobInfoService;
	
	@Autowired
	private ParSystemService parSystemService;
	
	/**
	 * 查询单个用户角色关联信息
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/queryByUser", method=RequestMethod.POST)
	public Object queryJobInfoByUser(HttpServletRequest request, 
			HttpServletResponse response){
		JsonResult jsonResult = new JsonResult();
		jsonResult.setStatusCode(999);
		try {
			 int validAuthCode = validAuthenticator(request);
			 if (validAuthCode != 200) {
			 jsonResult.setStatusCode(validAuthCode);
			 jsonResult.setReturnObj("Authenticator校验不通过");
			 return jsonResult;
			 }
			String ip = request.getParameter("ip");
			String timestamp = request.getParameter("timestamp");
			String sysId = request.getParameter("sysId");
			String token = request.getParameter("token");
			// 分空判断
			if (StringUtils.isEmpty(ip) || StringUtils.isEmpty(timestamp)
					|| StringUtils.isEmpty(sysId) || StringUtils.isEmpty(token)) {
				jsonResult.setStatusCode(ResultCode.PARAMS_NULL.getCode());
				jsonResult.setReturnObj(ResultCode.PARAMS_NULL.getDesc());
				return jsonResult;
			}
			// token校验
			String sysKey = parSystemService.getSysKeyBySysId(sysId);
			Token tokenObj = TokenUtil.getToken(sysId, sysKey, token);
			if (tokenObj.getStatusCode() != 200) {
				jsonResult.setStatusCode(tokenObj.getStatusCode());
				jsonResult.setReturnObj(ResultCode.TOKEN_ILLEGAL.getDesc());
				return jsonResult;
			}

			String userId = request.getParameter("userId");
			// 验证userId是否为空
			if (StringUtils.isEmpty(userId)) {
				jsonResult.setStatusCode(ResultCode.PARAMS_NULL.getCode());
				jsonResult.setReturnObj(ResultCode.PARAMS_NULL.getDesc());
				return jsonResult;
			}
			
			AiUserJobInfo result = aiUserJobInfoService.queryJobInfoByUser(userId);
			jsonResult.setStatusCode(ResultCode.SUCCESS.getCode());
			jsonResult.setReturnObj(result);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return jsonResult;
	}
	
	/**
	 * 查询单个用户角色关联信息
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/queryUserJobInfoList", method=RequestMethod.POST)
	public Object queryAiUserJobInfoList(HttpServletRequest request, 
			HttpServletResponse response){
		JsonResult jsonResult = new JsonResult();
		jsonResult.setStatusCode(999);
		try {
			 int validAuthCode = validAuthenticator(request);
			 if (validAuthCode != 200) {
			 jsonResult.setStatusCode(validAuthCode);
			 jsonResult.setReturnObj("Authenticator校验不通过");
			 return jsonResult;
			 }
			String ip = request.getParameter("ip");
			String timestamp = request.getParameter("timestamp");
			String sysId = request.getParameter("sysId");
			String token = request.getParameter("token");
			// 分空判断
			if (StringUtils.isEmpty(ip) || StringUtils.isEmpty(timestamp)
					|| StringUtils.isEmpty(sysId) || StringUtils.isEmpty(token)) {
				jsonResult.setStatusCode(ResultCode.PARAMS_NULL.getCode());
				jsonResult.setReturnObj(ResultCode.PARAMS_NULL.getDesc());
				return jsonResult;
			}
			// token校验
			String sysKey = parSystemService.getSysKeyBySysId(sysId);
			Token tokenObj = TokenUtil.getToken(sysId, sysKey, token);
			if (tokenObj.getStatusCode() != 200) {
				jsonResult.setStatusCode(tokenObj.getStatusCode());
				jsonResult.setReturnObj(ResultCode.TOKEN_ILLEGAL.getDesc());
				return jsonResult;
			}
			
			// 获取分页参数和查询条件参数
			int[] pageParams = ValidateUtil.validatePageParams(request);
			String userName = request.getParameter("userName");
			
			Map<String,Object> params = new HashMap<String, Object>();
			params.put("userName", userName);
			Page<AiUserJobInfo> result = 
					aiUserJobInfoService.queryUserJobInfoListPage(params, pageParams[0], pageParams[1]);
			jsonResult.setStatusCode(ResultCode.SUCCESS.getCode());
			jsonResult.setReturnObj(result);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return jsonResult;
	}
	
	/**
	 * 修改单个用户角色关联信息
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public Object updateAiUserJobInfo(HttpServletRequest request, 
			HttpServletResponse response){
		JsonResult jsonResult = new JsonResult();
		jsonResult.setStatusCode(999);
		try {
			 int validAuthCode = validAuthenticator(request);
			 if (validAuthCode != 200) {
			 jsonResult.setStatusCode(validAuthCode);
			 jsonResult.setReturnObj("Authenticator校验不通过");
			 return jsonResult;
			 }
			String ip = request.getParameter("ip");
			String timestamp = request.getParameter("timestamp");
			String sysId = request.getParameter("sysId");
			String token = request.getParameter("token");
			// 分空判断
			if (StringUtils.isEmpty(ip) || StringUtils.isEmpty(timestamp)
					|| StringUtils.isEmpty(sysId) || StringUtils.isEmpty(token)) {
				jsonResult.setStatusCode(ResultCode.PARAMS_NULL.getCode());
				jsonResult.setReturnObj(ResultCode.PARAMS_NULL.getDesc());
				return jsonResult;
			}
			// token校验
			String sysKey = parSystemService.getSysKeyBySysId(sysId);
			Token tokenObj = TokenUtil.getToken(sysId, sysKey, token);
			if (tokenObj.getStatusCode() != 200) {
				jsonResult.setStatusCode(tokenObj.getStatusCode());
				jsonResult.setReturnObj(ResultCode.TOKEN_ILLEGAL.getDesc());
				return jsonResult;
			}

			String userId = request.getParameter("userId");
			String jobId = request.getParameter("jobId");
			// 验证parentId是否为空
			if (StringUtils.isEmpty(userId)) {
				jsonResult.setStatusCode(ResultCode.PARAMS_NULL.getCode());
				jsonResult.setReturnObj(ResultCode.PARAMS_NULL.getDesc());
				return jsonResult;
			}
			
			AiUserJobInfo aiUserJobInfo = new AiUserJobInfo();
			aiUserJobInfo.setUserId(userId);
			aiUserJobInfo.setJobId(jobId);
			
			aiUserJobInfoService.updateAiUserJobInfo(aiUserJobInfo);
			jsonResult.setStatusCode(ResultCode.SUCCESS.getCode());
			jsonResult.setReturnObj(ResultCode.SUCCESS.getDesc());

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return jsonResult;
	}
}
