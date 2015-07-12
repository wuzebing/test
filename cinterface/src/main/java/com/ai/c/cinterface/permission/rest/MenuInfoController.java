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
import com.ai.c.cinterface.permission.entity.FolderMenuInfo;
import com.ai.c.cinterface.permission.entity.LowerFolderAndFunctionMenuInfo;
import com.ai.c.cinterface.permission.service.MenuInfoService;
import com.enterprisedt.util.debug.Logger;

@Controller
@RequestMapping(value = "/permission/menuinfo")
public class MenuInfoController<LowerMenuAndFuncMenuInfo> extends
		BaseSpringController {

	private Logger logger = Logger.getLogger(MenuInfoController.class);

	private static final String NOLOGIN_ROLE = "10007"; // 非登录用户的默认角色

	@Autowired
	private MenuInfoService menuInfoService;

	@Autowired
	private ParSystemService parSystemService;
	
	/**
	 * 根据用户所属的角色查询头部菜单（主菜单）
	 * <li>登录用户，角色从token中获取</li>
	 * <li>非登录用户，角色用默认值NOLOGIN_ROLE</li>
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/queryHeaderMenu", method = RequestMethod.POST)
	public Object queryHeaderMenu(HttpServletRequest request,
			HttpServletResponse response) {
		JsonResult jsonResult = new JsonResult();
		jsonResult.setStatusCode(999);
		try {
			 int validAuthCode = validAuthenticator(request);
			 if (validAuthCode != 200) {
			 jsonResult.setStatusCode(validAuthCode);
			 jsonResult.setReturnObj("Authenticator校验不通过");
			 return jsonResult;
			 }
			
			// 从token中获取该用户对应的角色，默认为未登录角色
			String role = MenuInfoController.NOLOGIN_ROLE;
			String ip = request.getParameter("ip");
			String timestamp = request.getParameter("timestamp");
			String sysId = request.getParameter("sysId");
			String token = request.getParameter("token");
			if (!StringUtils.isEmpty(ip) && !StringUtils.isEmpty(timestamp)
					&& !StringUtils.isEmpty(sysId) && !StringUtils.isEmpty(token)) {
				String sysKey = parSystemService.getSysKeyBySysId(sysId);
				Token tokenObj = TokenUtil.getToken(sysId, sysKey, token);
				if (tokenObj.getStatusCode() == 200) {
					role = tokenObj.getJobId();
				}
			}		

			List<FolderMenuInfo> results = menuInfoService
					.queryHeaderMenuInfo(role);
			jsonResult.setStatusCode(200);
			jsonResult.setReturnObj(results);

		} catch (Exception e) {
			logger.error(e.getMessage());
			return jsonResult;
		}
		return jsonResult;

	}
	
	/**
	 * 根据用户角色访问左侧菜单和相应的子菜单
	 * <li>登录用户才有权限访问该接口</li>
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/queryLowerFolderAndFunctionMenu", method = RequestMethod.POST)
	public Object queryLowerFolderAndFuncMenuInfo(HttpServletRequest request,
			HttpServletResponse response) {
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

			String role = tokenObj.getJobId();
			String upperMenuId = request.getParameter("upperMenuId");
			if (StringUtils.isEmpty(upperMenuId)) {
				jsonResult.setStatusCode(ResultCode.PARAMS_NULL.getCode());
				jsonResult.setReturnObj(ResultCode.PARAMS_NULL.getDesc()
						+ ":upperMenuId");
				return jsonResult;
			}
			
			List<LowerFolderAndFunctionMenuInfo> results = menuInfoService
					.queryLowerFolderAndFuncMenuInfo(upperMenuId, role);
			jsonResult.setStatusCode(200);
			jsonResult.setReturnObj(results);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return jsonResult;
	}
	
	/**
	 * 查询用户有权限访问的所有菜单URL
	 * 
	 * @param request
	 * @param response
	 * @return	菜单URL列表的JSON对象
	 */
	@RequestMapping(value="/queryMenusbyRole", method=RequestMethod.POST)
	@ResponseBody
	public Object queryMenusbyRole(HttpServletRequest request, 
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
			
			// 从token中获取该用户对应的角色，默认为未登录角色
			String role = MenuInfoController.NOLOGIN_ROLE;
			String ip = request.getParameter("ip");
			String timestamp = request.getParameter("timestamp");
			String sysId = request.getParameter("sysId");
			String token = request.getParameter("token");
			if (!StringUtils.isEmpty(ip) && !StringUtils.isEmpty(timestamp)
					&& !StringUtils.isEmpty(sysId) && !StringUtils.isEmpty(token)) {
				String sysKey = parSystemService.getSysKeyBySysId(sysId);
				Token tokenObj = TokenUtil.getToken(sysId, sysKey, token);
				if (tokenObj.getStatusCode() == 200) {
					role = tokenObj.getJobId();
				}
			}		

			List<String> results = menuInfoService
					.queryMenusbyRole(role);
			jsonResult.setStatusCode(200);
			jsonResult.setReturnObj(results);

		} catch (Exception e) {
			logger.error(e.getMessage());
			return jsonResult;
		}
		return jsonResult;
	}
	

	public void setMenuInfoService(MenuInfoService menuInfoService) {
		this.menuInfoService = menuInfoService;
	}

	public void setParSystemService(ParSystemService parSystemService) {
		this.parSystemService = parSystemService;
	}

}
