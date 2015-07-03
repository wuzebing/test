package com.ai.c.base.interceptor;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import com.ai.c.base.rest.RestPostClient;
import com.ai.c.base.util.ConfigUtils;
import com.ai.c.base.util.IPUtil;

public class PageLimitInterceptor implements HandlerInterceptor {
	
	Logger logger = Logger.getLogger(getClass());
	
	

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object arg2, Exception arg3)
			throws Exception {
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object arg2, ModelAndView arg3)
			throws Exception {
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		String url = request.getRequestURI();
		String lowUrl=request.getQueryString();
		String contrlUrl=URLDecoder.decode(url+lowUrl,"utf-8");
		if(contrlUrl!=null&&!contrlUrl.contains("/billpage/")&&!contrlUrl.contains("/k/")&&!contrlUrl.contains("/publish/")){
			//由于商品信息中带有[] 所以考虑后去掉contrlUrl.contains("[") ||contrlUrl.contains("]")|| 这个拦块
			//扫码记录里面存在该xi
			contrlUrl=contrlUrl.toLowerCase().replaceAll(" ", "");
			if(contrlUrl.contains("alert")||contrlUrl.contains("<")||contrlUrl.contains(">")||contrlUrl.contains("script")
			 ||contrlUrl.contains("\\")||contrlUrl.contains(";")||contrlUrl.contains("(")||contrlUrl.contains(")")
			 ||contrlUrl.contains("\"")||contrlUrl.contains("'"))
			{
				logger.error("有非法的访问请求地址："+"lowUrl="+contrlUrl+" IP="+IPUtil.getIP(request));
				request.getRequestDispatcher("/error.jsp").forward(request, response);  
				return false; 
			}
		} 
//		if(url.contains("_kpage")){
//			String result = queryCurrentRolePages(request, response);
//			JSONObject jsonObject = JSONObject.fromObject(result);
//			JSONArray jsonArray = jsonObject.getJSONArray("returnObj");
//			for(int i=0;i<jsonArray.size();i++){
//				String myUrl = jsonArray.getString(i);
//				logger.info(myUrl);
////				if((request.getContextPath()+myUrl).startsWith(url)){
//				if((request.getContextPath()+myUrl).equals(url)){
//					return true;
//				}
//			}
//			Writer out = response.getWriter();
//			out.write("<html>");
//			out.write("<meta http-equiv='Content-Type' content='text/html; charset=utf-8' />");
//			out.write("<script type='text/javascript'>");
//			out.write("		alert('"+url+"\\n您当前的角色没有对本地址的访问权限，请尝试切换其他角色登陆！');");
//			//out.write("		parent.parent.location.href = '"+request.getContextPath()+"';");
//			out.write("</script>");
//			out.write("</html>");
//			out.close();
//			return false;
//		}
		return true;
	}
	
	public String queryCurrentRolePages(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//请求地址
		String headUrl = ConfigUtils.getStringValue("api.serverURL");
		String tailUrl = "/permission/menuinfo/queryMenusbyRole";
		String requestURI = headUrl + tailUrl;
		//设置参数
		Map<String, String> bodyParam = new HashMap<String, String>();
		//调REST接口
		String result = new RestPostClient().callRestRPC(requestURI, bodyParam, request);
		return result;
	}
	
}
