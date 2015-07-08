package com.ai.c.cview.admin.permission.role;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ai.c.base.entity.JsonResult;
import com.ai.c.base.rest.RestPostClient;
import com.ai.c.base.util.ConfigUtils;
import com.ai.c.base.util.JsonUtil;

/**
 * @ClassName: RoleController
 * @Description: 角色管理
 * @author: 刚洪海
 * @date: 2015-1-27 下午2:33:57
 */
@Controller
@RequestMapping("/admin/manage/role")
public class RoleController {
	
	Logger logger = Logger.getLogger(getClass());

	/**
	 * @Title: roleListPage
	 * @Description: 跳转角色列表页面
	 * @param request
	 * @param response
	 * @return 
	 * @return: ModelAndView
	 */
	@RequestMapping(value = "/select_kpage", method = RequestMethod.GET)
	public ModelAndView roleListPage(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("");
		modelAndView.setViewName("/admin/manage/permission/role/roleList");
		return modelAndView;
	}
	
	/**
	 * @Title: addRolePage
	 * @Description: 跳转添加角色页面
	 * @param request
	 * @param response
	 * @return
	 * @return: ModelAndView
	 */
	@RequestMapping(value = "/addRolePage_kpage", method = RequestMethod.GET)
	public ModelAndView addRolePage(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("");
		modelAndView.setViewName("/admin/manage/permission/role/addRole");
		return modelAndView;
	}
	
	/**
	 * @Title: userRolePage
	 * @Description:跳转用户角色分配页面
	 * @param request
	 * @param response
	 * @return
	 * @return: ModelAndView
	 */
	@RequestMapping(value = "/userRolePage", method = RequestMethod.GET)
	public ModelAndView userRolePage(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("");
		modelAndView.setViewName("/admin/manage/permission/role/userRole");
		return modelAndView;
	}
	
	/**
	 * @Title: assignPermissionsPage
	 * @Description: 跳转权限分配页面
	 * @param request
	 * @param response
	 * @return
	 * @return: ModelAndView
	 */
	@RequestMapping(value = "/assignPermissionsPage", method = RequestMethod.GET)
	public ModelAndView assignPermissionsPage(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("");
		modelAndView.setViewName("/admin/manage/permission/role/assignPermissions");
		return modelAndView;
	}
	
	@RequestMapping(value = "/queryRoles", method = RequestMethod.POST)
	@ResponseBody
	public Object queryPartnerRecord(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String roleName = request.getParameter("roleName");
//		String endTime = request.getParameter("endTime");
//		String pageNo = request.getParameter("pageNo");
//		String pageSize = request.getParameter("pageSize");
		
		//设置参数
		String headUrl = ConfigUtils.getStringValue("api.serverURL");
		String tailUrl = ConfigUtils.getStringValue("admin.manage.role.select");
		String requestURI = headUrl + tailUrl;
		Map<String, String> bodyParam = new HashMap<String, String>();
		
//		bodyParam.put("pageNo", pageNo);
//		bodyParam.put("pageSize", pageSize);
//		bodyParam.put("export", "01");
//		
		if(StringUtils.hasLength(roleName)){
			bodyParam.put("jobName", roleName);
		}
//		if(StringUtils.hasLength(endTime)){
//			bodyParam.put("endTime", endTime);
//		}

		//调REST接口
		String result = new RestPostClient().callRestRPC(requestURI, bodyParam, request);
		//设置返回值
		//1.判断操作结果
		if(result == null){
			logger.error("调用后台接口返回值为null");
			return "false";
		}
		JsonResult jsonResult = JsonUtil.fromJson(result, JsonResult.class);
		return jsonResult;
	}
	
	/**
	 * @Title: addRole
	 * @Description: 添加角色信息
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @return: Object
	 */
	@RequestMapping(value = "/addRole", method = RequestMethod.POST)
	@ResponseBody
	public Object addRole(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String roleName = request.getParameter("roleName");
		String roleType = request.getParameter("roleType");
		String roleRemark = request.getParameter("roleRemark");
		
		//设置参数
		String headUrl = ConfigUtils.getStringValue("api.serverURL");
		String tailUrl = ConfigUtils.getStringValue("admin.manage.permission.role.addRole");
		String requestURI = headUrl + tailUrl;
		Map<String, String> bodyParam = new HashMap<String, String>();
		
		bodyParam.put("jobName", roleName);
		bodyParam.put("jobType", roleType);
		bodyParam.put("remark", roleRemark);
		
		//调REST接口
		String result = new RestPostClient().callRestRPC(requestURI, bodyParam, request);
		//设置返回值
		//1.判断操作结果
		if(result == null){
			logger.error("调用后台接口返回值为null");
			return "false";
		}
		JsonResult jsonResult = JsonUtil.fromJson(result, JsonResult.class);
		return jsonResult;
	}
	
	
	/**
	 * @Title: queryPermissionByRoleId
	 * @Description: 根据角色ID查询菜单及已分配信息
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @return: Object
	 */
	@RequestMapping(value = "/assignPermission/query", method = RequestMethod.POST)
	@ResponseBody
	public Object queryPermissionByRoleId(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String roleId = request.getParameter("roleId");
		//设置参数
		String headUrl = ConfigUtils.getStringValue("api.serverURL");
		String tailUrl = ConfigUtils.getStringValue("admin.manage.permission.assignPermission.queryById");
		String requestURI = headUrl + tailUrl;
		Map<String, String> bodyParam = new HashMap<String, String>();
		bodyParam.put("jobId", roleId);
		//调REST接口
		String result = new RestPostClient().callRestRPC(requestURI, bodyParam, request);
		//设置返回值
		//1.判断操作结果
		if(result == null){
			logger.error("调用后台接口返回值为null");
			return "false";
		}
		JsonResult jsonResult = JsonUtil.fromJson(result, JsonResult.class);
		return jsonResult;
	}
	
	
	/**
	 * @Title: queryUserInfoForRole
	 * @Description: 查询用户信息
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @return: Object
	 */
	@RequestMapping(value = "/userInfo/query", method = RequestMethod.POST)
	@ResponseBody
	public Object queryUserInfoForRole(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String userName = request.getParameter("userName");
		String pageNo = request.getParameter("pageNo");
		String pageSize = request.getParameter("pageSize");
		//设置参数
		String headUrl = ConfigUtils.getStringValue("api.serverURL");
		String tailUrl = ConfigUtils.getStringValue("admin.manage.permission.userInfo.query");
		String requestURI = headUrl + tailUrl;
		Map<String, String> bodyParam = new HashMap<String, String>();
		if(StringUtils.hasLength(userName)){
			bodyParam.put("userName", userName);
		}
		bodyParam.put("pageNo", pageNo);
		bodyParam.put("pageSize", pageSize);
		//调REST接口
		String result = new RestPostClient().callRestRPC(requestURI, bodyParam, request);
		//设置返回值
		//1.判断操作结果
		if(result == null){
			logger.error("调用后台接口返回值为null");
			return "false";
		}
		JsonResult jsonResult = JsonUtil.fromJson(result, JsonResult.class);
		return jsonResult;
	}
	
	
	/**
	 * @Title: saveUserInfoWithRole
	 * @Description: 给用户分配角色
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @return: Object
	 */
	@RequestMapping(value = "/userInfo/save", method = RequestMethod.POST)
	@ResponseBody
	public Object saveUserInfoWithRole(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String userId = request.getParameter("userId");
		String roleId = request.getParameter("roleId");
		//设置参数
		String headUrl = ConfigUtils.getStringValue("api.serverURL");
		String tailUrl = ConfigUtils.getStringValue("admin.manage.permission.userInfo.save");
		String requestURI = headUrl + tailUrl;
		Map<String, String> bodyParam = new HashMap<String, String>();
		bodyParam.put("userId", userId);
		bodyParam.put("jobId", roleId);
		//调REST接口
		String result = new RestPostClient().callRestRPC(requestURI, bodyParam, request);
		//设置返回值
		//1.判断操作结果
		if(result == null){
			logger.error("调用后台接口返回值为null");
			return "false";
		}
		JsonResult jsonResult = JsonUtil.fromJson(result, JsonResult.class);
		return jsonResult;
	}
	
	
	@RequestMapping(value = "/assignPermission/save", method = RequestMethod.POST)
	@ResponseBody
	public Object savePermission(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String roleId = request.getParameter("roleId");
		String menus = request.getParameter("menus");
		
		//设置参数
		String headUrl = ConfigUtils.getStringValue("api.serverURL");
		String tailUrl = ConfigUtils.getStringValue("admin.manage.permission.assignPermission.save");
		String requestURI = headUrl + tailUrl;
		Map<String, String> bodyParam = new HashMap<String, String>();
		bodyParam.put("jobId", roleId);
		bodyParam.put("funcIds", menus);
		//调REST接口
		String result = new RestPostClient().callRestRPC(requestURI, bodyParam, request);
		//设置返回值
		//1.判断操作结果
		if(result == null){
			logger.error("调用后台接口返回值为null");
			return "false";
		}
		JsonResult jsonResult = JsonUtil.fromJson(result, JsonResult.class);
		return jsonResult;
	}
	
}
