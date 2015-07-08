package com.ai.c.cview.base;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ai.c.base.entity.JsonResult;
import com.ai.c.base.rest.RestPostClient;
import com.ai.c.base.util.ConfigUtils;
import com.ai.c.base.util.JsonUtil;
 
/**
 * 菜单控制器
 * @author LIUQIANMING
 *
 */
@Controller
@RequestMapping("/base/MenuController")
public class MenuController {

	Logger logger = Logger.getLogger(getClass());
	
	@RequestMapping(value = "queryHeadMenu", method = RequestMethod.POST)
	@ResponseBody
	public Object queryHeadMenu(HttpServletRequest request, HttpServletResponse response) throws Exception {
		JsonResult jsonResult = new JsonResult();
		//请求地址
		String headUrl = ConfigUtils.getStringValue("api.serverURL");
		String tailUrl = ConfigUtils.getStringValue("base.MenuController.queryHeadMenu");
		String requestURI = headUrl + tailUrl;
		//设置参数
		Map<String, String> bodyParam = new HashMap<String, String>();
		//调REST接口
		String result = new RestPostClient().callRestRPC(requestURI, bodyParam, request);
		if(result == null){
			logger.error("调用后台接口返回值为null");
			jsonResult.setStatusCode(201);
			jsonResult.setReturnObj("调用接口返回空");
			return jsonResult;
		}
		return JsonUtil.fromJson(result, JsonResult.class);
	}
	
	@RequestMapping(value = "queryLeftMenu", method = RequestMethod.POST)
	@ResponseBody
	public Object queryLeftMenu(HttpServletRequest request, HttpServletResponse response) throws Exception {
		JsonResult jsonResult = new JsonResult();
		//请求地址
		String headUrl = ConfigUtils.getStringValue("api.serverURL");
		String tailUrl = ConfigUtils.getStringValue("base.MenuController.queryLeftMenu");
		String requestURI = headUrl + tailUrl;
		//设置参数
		Map<String, String> bodyParam = new HashMap<String, String>();
		bodyParam.put("upperMenuId", request.getParameter("menuId"));
		//调REST接口
		String result = new RestPostClient().callRestRPC(requestURI, bodyParam, request);
		if(result == null){
			logger.error("调用后台接口返回值为null");
			jsonResult.setStatusCode(201);
			jsonResult.setReturnObj("调用接口返回空");
			return jsonResult;
		}
		return JsonUtil.fromJson(result, JsonResult.class);
	}

}
