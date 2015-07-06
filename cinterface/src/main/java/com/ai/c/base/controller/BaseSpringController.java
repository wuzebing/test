package com.ai.c.base.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import com.ai.c.base.encrypt.Cryptogram;
import com.ai.c.cinterface.par.service.ParSystemService;
/**
 * @author LIUQIANMING
 * @createtime 2014-7-23
 */
public class BaseSpringController {
	private static Logger logger = Logger.getLogger(BaseSpringController.class);

	@Autowired
	private ParSystemService parSystemService;
	/*
	 * 校验auth串
	 * 
	 * @param request
	 * @return 200为正确
	*/
	public int validAuthenticator(HttpServletRequest request) {
		try {
			String sysId = request.getParameter("sysId");
			String authenticator = request.getParameter("authenticator");
			// 非空判断
			if (StringUtils.isEmpty(sysId)
					|| StringUtils.isEmpty(authenticator)) {
				return 201;// 请求参数为空
			}
			String key = parSystemService.getSysKeyBySysId(sysId);
			if (StringUtils.isEmpty(key)) {
				// 根据sysId获取key,如果sysId不存在则返回202
				return 202;
			}
			Enumeration<String> enums2 = request.getParameterNames();
			List<String> params = new ArrayList<String>();
			while (enums2.hasMoreElements()) {
				String paramKey = enums2.nextElement();
				if ("authenticator".equals(paramKey)) {
					continue;
				}
				String value = request.getParameter(paramKey);
				if (!org.apache.commons.lang.StringUtils.isEmpty(value)) {
					params.add(value);
				}
			}
			Collections.sort(params);// 按字母排序
			StringBuilder sb = new StringBuilder();
			for (String aParamValue : params) {
				sb.append(aParamValue);
			}
			// 根据请求参数生成的digest
			String digest = Cryptogram.getBase64HashString(sb.toString());
			// 从auth解释出digest
			String strParams = Cryptogram.decryptByKey(authenticator, key);
			String[] authParams = strParams.split("\\$");
			String digestFromAuth = authParams[2];
			if (digest.equals(digestFromAuth)) {
				return 200;// auth一致
			}
		} catch (Exception e) {
			logger.error("校验Authenticator异常", e);
		}
		return 999;
	}
}
