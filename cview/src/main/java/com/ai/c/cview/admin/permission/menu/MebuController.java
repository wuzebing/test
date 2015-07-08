package com.ai.c.cview.admin.permission.menu;

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

@Controller
@RequestMapping("/admin/manage/menu")
public class MebuController {
	
	Logger logger = Logger.getLogger(getClass());
	
	@RequestMapping(value = "/queryAllMenus_kpage", method = RequestMethod.GET)
	public ModelAndView roleListPage(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("");
		modelAndView.setViewName("/admin/manage/permission/menu/menuManage");
		return modelAndView;
	}
	
	@RequestMapping(value = "/queryAllMenus", method = RequestMethod.POST)
	@ResponseBody
	public Object queryAllMenus(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String parentId = request.getParameter("parentId");
		
		//设置参数
		String headUrl = ConfigUtils.getStringValue("api.serverURL");
		String tailUrl = ConfigUtils.getStringValue("admin.manage.permission.menu.queryAll");
		String requestURI = headUrl + tailUrl;
		Map<String, String> bodyParam = new HashMap<String, String>();
		if(StringUtils.hasLength(parentId)){
			bodyParam.put("parentId", parentId);
		}

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
	 * @Title: addNewMenu
	 * @Description: 添加功能菜单
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @return: Object
	 */
	@RequestMapping(value = "/addNewMenu", method = RequestMethod.POST)
	@ResponseBody
	public Object addNewMenu(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String parentId = request.getParameter("parentID");
		String menuType = request.getParameter("treeMenuType");
		String menuUrl = request.getParameter("menuUrl");
		String funcName = request.getParameter("menuName");
		String remark = request.getParameter("remark");
		
		//设置参数
		String headUrl = ConfigUtils.getStringValue("api.serverURL");
		String tailUrl = ConfigUtils.getStringValue("admin.manage.permission.menu.addNewMenu");
		String requestURI = headUrl + tailUrl;
		Map<String, String> bodyParam = new HashMap<String, String>();
		if(StringUtils.hasLength(parentId)){
			bodyParam.put("parentId", parentId);
		}
		if(StringUtils.hasLength(menuType)){
			bodyParam.put("menuType", menuType);
		}
		if(StringUtils.hasLength(menuUrl)){
			bodyParam.put("menuUrl", menuUrl);
		}
		if(StringUtils.hasLength(funcName)){
			bodyParam.put("funcName", funcName);
		}
		if(StringUtils.hasLength(remark)){
			bodyParam.put("remark", remark);
		}

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
	 * @Title: updateMenu
	 * @Description: 修改菜单信息
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @return: Object
	 */
	@RequestMapping(value = "/updateMenu", method = RequestMethod.POST)
	@ResponseBody
	public Object updateMenu(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String nodeID = request.getParameter("nodeID");
		String menuUrl = request.getParameter("menuUrl");
		String funcName = request.getParameter("menuName");
		String remark = request.getParameter("remark");
		
		//设置参数
		String headUrl = ConfigUtils.getStringValue("api.serverURL");
		String tailUrl = ConfigUtils.getStringValue("admin.manage.permission.menu.updateMenu");
		String requestURI = headUrl + tailUrl;
		Map<String, String> bodyParam = new HashMap<String, String>();
		if(StringUtils.hasLength(nodeID)){
			bodyParam.put("funcId", nodeID);
		}
		if(StringUtils.hasLength(menuUrl)){
			bodyParam.put("menuUrl", menuUrl);
		}
		if(StringUtils.hasLength(funcName)){
			bodyParam.put("funcName", funcName);
		}
		if(StringUtils.hasLength(remark)){
			bodyParam.put("remark", remark);
		}
		
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
	 * @Title: deleteMenu
	 * @Description: 删除功能菜单
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @return: Object
	 */
	@RequestMapping(value = "/deleteMenu", method = RequestMethod.POST)
	@ResponseBody
	public Object deleteMenu(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String funcId = request.getParameter("menuID");
		
		//设置参数
		String headUrl = ConfigUtils.getStringValue("api.serverURL");
		String tailUrl = ConfigUtils.getStringValue("admin.manage.permission.menu.deleteMenu");
		String requestURI = headUrl + tailUrl;
		Map<String, String> bodyParam = new HashMap<String, String>();
		if(StringUtils.hasLength(funcId)){
			bodyParam.put("funcId", funcId);
		}
		
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
