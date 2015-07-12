package com.ai.c.cinterface.permission.rest;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ai.c.base.controller.BaseSpringController;
import com.ai.c.base.entity.JsonResult;
import com.ai.c.base.entity.Token;
import com.ai.c.base.resultcode.ResultCode;
import com.ai.c.base.util.DateUtil;
import com.ai.c.base.util.TokenUtil;
import com.ai.c.cinterface.par.service.ParSystemService;
import com.ai.c.cinterface.permission.entity.AiJobFunc;
import com.ai.c.cinterface.permission.entity.JobMenuInfo;
import com.ai.c.cinterface.permission.service.AiJobFuncService;
import com.enterprisedt.util.debug.Logger;

/**
 * 提供角色菜单关联关系相关的REST接口
 * 
 * @author zhuwenkai
 *
 */
@Controller
@RequestMapping("permission/aiJobFunc")
public class AiJobFuncController extends BaseSpringController {

	private Logger logger = Logger.getLogger(getClass());
	
	@Autowired
	private AiJobFuncService aiFuncService;
	
	@Autowired
	private ParSystemService parSystemService;
	
	/**
	 * 根据角色查询角色菜单管理关系
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/queryByJob", method=RequestMethod.POST)
	public Object queryAiFuncsByJob(HttpServletRequest request,
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

			String jobId = request.getParameter("jobId");
			// 验证jobId是否为空
			if (StringUtils.isEmpty(jobId)) {
				jsonResult.setStatusCode(ResultCode.PARAMS_NULL.getCode());
				jsonResult.setReturnObj(ResultCode.PARAMS_NULL.getDesc());
				return jsonResult;
			}
			
			List<JobMenuInfo> results = aiFuncService.queryAllAndAssignedFuncs(jobId);
			jsonResult.setStatusCode(ResultCode.SUCCESS.getCode());
			jsonResult.setReturnObj(results);	

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			jsonResult.setReturnObj(ResultCode.OTHER_ERROR.getDesc());
		}
		return jsonResult;
	}
	
	/**
	 * 保存用户角色权限信息，支持多个保存
	 * <li>权限信息以字符串的形式进行传输，多个权限信息以逗号进行分割</li>
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/saveAiFuncs", method=RequestMethod.POST)
	public Object saveAiFuncs(HttpServletRequest request,
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

			String jobId = request.getParameter("jobId");
			String funcIds = request.getParameter("funcIds");
			// 验证jobId是否为空
			if (StringUtils.isEmpty(jobId)) {
				jsonResult.setStatusCode(ResultCode.PARAMS_NULL.getCode());
				jsonResult.setReturnObj(ResultCode.PARAMS_NULL.getDesc());
				return jsonResult;
			}
			
			/* 如果funcIds为空，意味着取消分配给该角色所有的权限 */
			if(StringUtils.isEmpty(funcIds)){
				aiFuncService.deleteAiJobFuncByJob(jobId);
				
				jsonResult.setStatusCode(ResultCode.SUCCESS.getCode());
				jsonResult.setReturnObj(ResultCode.SUCCESS.getDesc());
				
				return jsonResult;
			}
			
			/* 如果funcIds不为空，意味着重新给该角色分配了新的权限 */
			List<AiJobFunc> aiFuncList = new ArrayList<AiJobFunc>();
			String adminTime = DateUtil.getInstance().getToday("yyyy-MM-dd HH:mm:ss");
			int menuOrder = 1;
			for(String funcId : funcIds.split(",")){
				// 判断funcId是否为空，非空的才添加到数据库
				if(!StringUtils.isEmpty(funcId)){
					AiJobFunc aiFunc = new AiJobFunc();
					aiFunc.setFuncId(funcId);
					aiFunc.setJobId(jobId);
					aiFunc.setAdminId(tokenObj.getUserId());
					aiFunc.setAdminName(tokenObj.getUserName());
					aiFunc.setAdminTime(adminTime);
					aiFunc.setMenuOrder(Integer.toString(menuOrder));
					aiFuncList.add(aiFunc);
					menuOrder++;
				}
			}
			
			aiFuncService.saveAiFuncs(aiFuncList, jobId);
			
			jsonResult.setStatusCode(ResultCode.SUCCESS.getCode());
			jsonResult.setReturnObj(ResultCode.SUCCESS.getDesc());	

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			jsonResult.setReturnObj(ResultCode.OTHER_ERROR.getDesc());
		}
		return jsonResult;
	}
	

}
