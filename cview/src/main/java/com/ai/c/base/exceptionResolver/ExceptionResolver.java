package com.ai.c.base.exceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

import com.ai.c.base.entity.JsonResult;


/**
 * @author LIUQIANMING
 * @createtime 2015-06-22
 */
public class ExceptionResolver implements HandlerExceptionResolver {

	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		JsonResult jsonResult = new JsonResult();
		jsonResult.setStatusCode(500);
		jsonResult.setReturnObj("system exception");
		MappingJacksonJsonView v = new MappingJacksonJsonView();
		ModelMap map = new ModelMap();
		map.put("statusCode", 500);
		map.put("returnObj", "system exception");
		
		return new ModelAndView(v, map);
	}

}
