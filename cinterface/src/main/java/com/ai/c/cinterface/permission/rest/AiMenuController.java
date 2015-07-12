package com.ai.c.cinterface.permission.rest;

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
import com.ai.c.base.util.TokenUtil;
import com.ai.c.cinterface.par.service.ParSystemService;
import com.ai.c.cinterface.permission.entity.AiMenu;
import com.ai.c.cinterface.permission.service.AiMenuService;
import com.enterprisedt.util.debug.Logger;

@Controller
@RequestMapping(value="/permission/aiMenu")
public class AiMenuController extends BaseSpringController {
	
	private Logger logger = Logger.getLogger(getClass());
	
	@Autowired
	private AiMenuService aiMenuService;
	@Autowired
	private ParSystemService parSystemService;
	
	
	@ResponseBody
	@RequestMapping(value="/queryAiMenuList")
	public Object queryAiMenuList(HttpServletRequest request, 
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

			String parentId = request.getParameter("parentId");
			String menuType = request.getParameter("menuType");
			// 组装查询条件
			AiMenu aiMenu = new AiMenu();
			aiMenu.setParentId(parentId);
			aiMenu.setMenuType(menuType);
			
			List<AiMenu> results = aiMenuService.queryAiMenuList(aiMenu);
			jsonResult.setStatusCode(200);
			jsonResult.setReturnObj(results);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return jsonResult;
	}
	
	@ResponseBody
	@RequestMapping(value="/queryAiMenuById")
	public Object queryAiMenuById(HttpServletRequest request, 
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

			String funcId = request.getParameter("funcId");
			
			AiMenu aiMenu = aiMenuService.queryAiMenuById(funcId);
			jsonResult.setStatusCode(200);
			jsonResult.setReturnObj(aiMenu);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return jsonResult;
	}
	
	@ResponseBody
	@RequestMapping(value="/insertAiMenu", method=RequestMethod.POST)
	public Object insertAiMenu(HttpServletRequest request, 
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

			String funcName = request.getParameter("funcName");
			String menuUrl = request.getParameter("menuUrl");
			String parentId = request.getParameter("parentId");
			String remark = request.getParameter("remark");
			
			// 验证参数的合法性
			AiMenu parentAiMenu = aiMenuService.queryAiMenuById(parentId);
			if(parentAiMenu == null){
				jsonResult.setStatusCode(ResultCode.PARAMS_NULL.getCode());
				jsonResult.setReturnObj(ResultCode.PARAMS_NULL.getDesc());
				return jsonResult;
			}
			
			// 新增菜单的levelId根据上一级菜单levelId得到
			int levelId = parentAiMenu.getLevelId() + 1;
			
			AiMenu aiMenu = new AiMenu();
			aiMenu.setName(funcName);
			aiMenu.setMenuUrl(menuUrl);
			aiMenu.setMenuType(parentAiMenu.getMenuType());
			aiMenu.setParentId(parentId);
			aiMenu.setRemark(remark);
			aiMenu.setLevelId(levelId);
			// 增加菜单
			aiMenuService.insertAiMenu(aiMenu);
			
			jsonResult.setStatusCode(ResultCode.SUCCESS.getCode());
			jsonResult.setReturnObj(ResultCode.SUCCESS.getDesc());

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return jsonResult;
	}
	
	@ResponseBody
	@RequestMapping(value="/updateAiMenu", method=RequestMethod.POST)
	public Object updateAiMenu(HttpServletRequest request, 
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

			String funcId = request.getParameter("funcId");
			String funcName = request.getParameter("funcName");
			String menuUrl = request.getParameter("menuUrl");
			String remark = request.getParameter("remark");
			
			// 验证funcId是否为空
			if (StringUtils.isEmpty(funcId)) {
				jsonResult.setStatusCode(ResultCode.PARAMS_NULL.getCode());
				jsonResult.setReturnObj(ResultCode.PARAMS_NULL.getDesc());
				return jsonResult;
			}
			
			AiMenu aiMenu = new AiMenu();
			aiMenu.setFuncId(funcId);
			aiMenu.setName(funcName);
			aiMenu.setMenuUrl(menuUrl);
			aiMenu.setRemark(remark);
			// 修改菜单
			aiMenuService.updateAiMenu(aiMenu);
			
			jsonResult.setStatusCode(ResultCode.SUCCESS.getCode());
			jsonResult.setReturnObj(ResultCode.SUCCESS.getDesc());

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return jsonResult;
	}
	
	@ResponseBody
	@RequestMapping(value="/enableAiMenu", method=RequestMethod.POST)
	public Object enableAiMenu(HttpServletRequest request, 
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

			String funcId = request.getParameter("funcId");
			
			// 验证funcId是否为空
			if (StringUtils.isEmpty(funcId)) {
				jsonResult.setStatusCode(ResultCode.PARAMS_NULL.getCode());
				jsonResult.setReturnObj(ResultCode.PARAMS_NULL.getDesc());
				return jsonResult;
			}
			
			aiMenuService.enableAimenu(funcId);
			jsonResult.setStatusCode(ResultCode.SUCCESS.getCode());
			jsonResult.setReturnObj(ResultCode.SUCCESS.getDesc());

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return jsonResult;
	}
	
	
	@ResponseBody
	@RequestMapping(value="/disableAiMenu", method=RequestMethod.POST)
	public Object disableAiMenu(HttpServletRequest request, 
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

			String funcId = request.getParameter("funcId");
			
			// 验证funcId是否为空
			if (StringUtils.isEmpty(funcId)) {
				jsonResult.setStatusCode(ResultCode.PARAMS_NULL.getCode());
				jsonResult.setReturnObj(ResultCode.PARAMS_NULL.getDesc());
				return jsonResult;
			}
			
			aiMenuService.diableAiMenu(funcId);
			jsonResult.setStatusCode(ResultCode.SUCCESS.getCode());
			jsonResult.setReturnObj(ResultCode.SUCCESS.getDesc());

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return jsonResult;
	}
	
	
	@ResponseBody
	@RequestMapping(value="/deleteAiMenu", method=RequestMethod.POST)
	public Object deleteAiMenu(HttpServletRequest request, 
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

			String funcId = request.getParameter("funcId");
			
			// 验证funcId是否为空
			if (StringUtils.isEmpty(funcId)) {
				jsonResult.setStatusCode(ResultCode.PARAMS_NULL.getCode());
				jsonResult.setReturnObj(ResultCode.PARAMS_NULL.getDesc());
				return jsonResult;
			}
			
			int result = aiMenuService.deleteAiMenu(funcId);
			
			if(result == 1){
				jsonResult.setStatusCode(ResultCode.OTHER_ERROR.getCode());
				jsonResult.setReturnObj("该菜单下有子菜单，不允许删除");
			}else{
				jsonResult.setStatusCode(ResultCode.SUCCESS.getCode());
				jsonResult.setReturnObj(ResultCode.SUCCESS.getDesc());
			}	

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return jsonResult;
	}

	

}
