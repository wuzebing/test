package com.ai.c.cinterface.permission.rest;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ai.c.base.controller.BaseSpringController;
import com.ai.c.base.dao.Page;
import com.ai.c.base.entity.JsonResult;
import com.ai.c.base.entity.Token;
import com.ai.c.base.resultcode.ResultCode;
import com.ai.c.base.util.DateUtil;
import com.ai.c.base.util.GetTokenUtil;
import com.ai.c.base.util.ValidateUtil;
import com.ai.c.cinterface.permission.entity.AiJob;
import com.ai.c.cinterface.permission.service.AiJobService;
import com.enterprisedt.util.debug.Logger;

@Controller
@RequestMapping("/permission/aiJob")
public class AiJobController extends BaseSpringController{
	
	Logger logger = Logger.getLogger(getClass());
	
	@Autowired
	private AiJobService aiJobService;
	
	/**
	 * 查询所有的用户角色
	 *
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/queryAiJobs", method=RequestMethod.POST)
	@ResponseBody
	public Object queryAiJobs(HttpServletRequest request, 
			HttpServletResponse response){
		JsonResult jsonResult = new JsonResult();
        int validAuthCode = validAuthenticator(request);
        if (validAuthCode != ResultCode.SUCCESS.getCode())
        {
            jsonResult.setStatusCode(ResultCode.AUTHENTICATOR_ERROR.getCode());
            jsonResult.setReturnObj(ResultCode.AUTHENTICATOR_ERROR.getDesc());
            logger.error("=====ERROR=====, Invoke interface method queryAllJobs" 
            		+ jsonResult.toString());
            return jsonResult;
        }
        
        try
        {
            Token token = GetTokenUtil.getToken(request);
            if (null == token || ResultCode.SUCCESS.getCode() != token.getStatusCode()
                || StringUtils.isBlank(token.getUserId()))
            {
                jsonResult.setStatusCode(ResultCode.TOKEN_ILLEGAL.getCode());
                jsonResult.setReturnObj(ResultCode.TOKEN_ILLEGAL.getDesc());
                logger.error("=====ERROR=====, Invoke interface method queryAllJobs"
                    + jsonResult.toString());
                return jsonResult;
            }
            
            String jobName = request.getParameter("jobName");
            String jobId   = request.getParameter("jobId");
            String jobType = request.getParameter("jobType");
            int[] pageParams = ValidateUtil.validatePageParams(request);        
            Map<String, Object> params = new HashMap<String, Object>();	//查询参数
            
            // 组装查询条件
            params.put("jobName", jobName);
            params.put("jobId", jobId);
            params.put("jobType", jobType);
    
            Page<AiJob> results = aiJobService.queryAiJobs(params, 
            		pageParams[0], pageParams[1]);
     
            jsonResult.setStatusCode(ResultCode.SUCCESS.getCode());
            jsonResult.setReturnObj(results);
            
        }
        catch(Exception e){
        	e.printStackTrace();
        	logger.error(e.getMessage(), e);
        	jsonResult.setStatusCode(ResultCode.OTHER_ERROR.getCode());
        	jsonResult.setReturnObj(ResultCode.OTHER_ERROR.getDesc());
        }
		return jsonResult;
	}
	
	/**
	 * 查询单个角色信息
	 *
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/queryAiJobTypeById", method=RequestMethod.POST)
	@ResponseBody
	public Object queryAiJobTypeById(HttpServletRequest request, 
			HttpServletResponse response){
		JsonResult jsonResult = new JsonResult();
        int validAuthCode = validAuthenticator(request);
        if (validAuthCode != ResultCode.SUCCESS.getCode())
        {
            jsonResult.setStatusCode(ResultCode.AUTHENTICATOR_ERROR.getCode());
            jsonResult.setReturnObj(ResultCode.AUTHENTICATOR_ERROR.getDesc());
            logger.error("=====ERROR=====, Invoke interface method queryAllJobs" 
            		+ jsonResult.toString());
            return jsonResult;
        }
        
        try
        {
            Token token = GetTokenUtil.getToken(request);
            if (null == token || ResultCode.SUCCESS.getCode() != token.getStatusCode()
                || StringUtils.isBlank(token.getUserId()))
            {
                jsonResult.setStatusCode(ResultCode.TOKEN_ILLEGAL.getCode());
                jsonResult.setReturnObj(ResultCode.TOKEN_ILLEGAL.getDesc());
                logger.error("=====ERROR=====, Invoke interface method queryAllJobs"
                    + jsonResult.toString());
                return jsonResult;
            }
            
            String jobId   = request.getParameter("jobId");
            
            AiJob result = aiJobService.queryAiJobByJobId(jobId);
            
            jsonResult.setStatusCode(ResultCode.SUCCESS.getCode());
            // 若未查到记录，jobType返回null
            if(result == null){
            	jsonResult.setReturnObj(result);
            }else{
            	jsonResult.setReturnObj(result.getJobType());
            }       
        }
        catch(Exception e){
        	e.printStackTrace();
        	logger.error(e.getMessage(), e);
        	jsonResult.setStatusCode(ResultCode.OTHER_ERROR.getCode());
        	jsonResult.setReturnObj(ResultCode.OTHER_ERROR.getDesc());
        }
		return jsonResult;
	}
	
	@ResponseBody
	@RequestMapping(value="/insertAiJob", method=RequestMethod.POST)
	public Object insertAiJob(HttpServletRequest request, 
			HttpServletResponse response){
		JsonResult jsonResult = new JsonResult();
        int validAuthCode = validAuthenticator(request);
        if (validAuthCode != ResultCode.SUCCESS.getCode())
        {
            jsonResult.setStatusCode(ResultCode.AUTHENTICATOR_ERROR.getCode());
            jsonResult.setReturnObj(ResultCode.AUTHENTICATOR_ERROR.getDesc());
            logger.error("=====ERROR=====, Invoke interface method addAiJob" 
            		+ jsonResult.toString());
            return jsonResult;
        }
		
		try{
			Token token = GetTokenUtil.getToken(request);
            if (null == token || ResultCode.SUCCESS.getCode() != token.getStatusCode()
                || StringUtils.isBlank(token.getUserId()))
            {
                jsonResult.setStatusCode(ResultCode.TOKEN_ILLEGAL.getCode());
                jsonResult.setReturnObj(ResultCode.TOKEN_ILLEGAL.getDesc());
                logger.error("=====ERROR=====, Invoke interface method addAiJob"
                    + jsonResult.toString());
                return jsonResult;
            }
            
			String jobName = request.getParameter("jobName");
			String remark = request.getParameter("remark");
			String jobType= request.getParameter("jobType");
			if(StringUtils.isEmpty(jobName)){
				jsonResult.setStatusCode(ResultCode.PARAMS_NULL.getCode());
				jsonResult.setReturnObj(ResultCode.PARAMS_NULL.getDesc());
				return jsonResult;
			}
			
			// 组装AiJob实体类
			AiJob aiJob = new AiJob();
			aiJob.setJobName(jobName);
			aiJob.setJobType(jobType);
			aiJob.setAdminId(token.getUserId());
			aiJob.setAdminName(token.getUserName());
			aiJob.setAdminTime(DateUtil.getInstance().getToday("yyyy-MM-dd HH:mm:ss"));
			aiJob.setRemark(remark);
			
			aiJobService.insertAiJob(aiJob);
			
			jsonResult.setStatusCode(ResultCode.SUCCESS.getCode());
			jsonResult.setReturnObj(ResultCode.SUCCESS.getDesc());
			
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e.getMessage());
			jsonResult.setReturnObj(ResultCode.OTHER_ERROR.getDesc());
		}
		
		return jsonResult;
	}
	
	@ResponseBody
	@RequestMapping(value="/updateAiJob", method=RequestMethod.POST)
	public Object updateAiJob(HttpServletRequest request, 
			HttpServletResponse response){
		JsonResult jsonResult = new JsonResult();
        int validAuthCode = validAuthenticator(request);
        if (validAuthCode != ResultCode.SUCCESS.getCode())
        {
            jsonResult.setStatusCode(ResultCode.AUTHENTICATOR_ERROR.getCode());
            jsonResult.setReturnObj(ResultCode.AUTHENTICATOR_ERROR.getDesc());
            logger.error("=====ERROR=====, Invoke interface method updateAiJob" 
            		+ jsonResult.toString());
            return jsonResult;
        }
		
		try{
			Token token = GetTokenUtil.getToken(request);
            if (null == token || ResultCode.SUCCESS.getCode() != token.getStatusCode()
                || StringUtils.isBlank(token.getUserId()))
            {
                jsonResult.setStatusCode(ResultCode.TOKEN_ILLEGAL.getCode());
                jsonResult.setReturnObj(ResultCode.TOKEN_ILLEGAL.getDesc());
                logger.error("=====ERROR=====, Invoke interface method updateAiJob"
                    + jsonResult.toString());
                return jsonResult;
            }
            String jobId = request.getParameter("jobId");
			String jobName = request.getParameter("jobName");
			String remark = request.getParameter("remark");
			String jobType= request.getParameter("jobType");
			
			// 组装AiJob实体类
			AiJob aiJob = new AiJob();
			aiJob.setJobId(jobId);
			aiJob.setJobName(jobName);
			aiJob.setJobType(jobType);
			aiJob.setAdminId(token.getUserId());
			aiJob.setAdminName(token.getUserName());
			aiJob.setRemark(remark);
			aiJob.setAdminTime(DateUtil.getInstance().getToday("yyyy-MM-dd HH:mm:ss"));
			
			aiJobService.updateAiJob(aiJob);
			
			jsonResult.setStatusCode(ResultCode.SUCCESS.getCode());
			jsonResult.setReturnObj(ResultCode.SUCCESS.getDesc());
			
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e.getMessage());
			jsonResult.setReturnObj(ResultCode.OTHER_ERROR.getDesc());
		}
		
		return jsonResult;
	}

	public void setAiJobService(AiJobService aiJobService) {
		this.aiJobService = aiJobService;
	}
	
}
