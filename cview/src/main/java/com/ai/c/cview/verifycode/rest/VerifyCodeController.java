package com.ai.c.cview.verifycode.rest;

import java.awt.Color;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ai.c.base.entity.JsonResult;
import com.ai.c.base.util.CommonCryptogram;
import com.ai.c.cview.verifycode.util.VerifyCodeUtil;
/**
 * CopyRright (c)2015
 * Comments:验证码请求处理类
 * Author: LIUQIANMING
 * Create Date:2015-06-30
 * Modified By:
 * Modified Date:
 */
@Controller
@RequestMapping("/verifycode")
public class VerifyCodeController {
	Logger logger = Logger.getLogger(getClass());

	@RequestMapping(value = "/getcode", method = RequestMethod.GET)
	@ResponseBody
	public JsonResult getCode(HttpServletRequest request,
			HttpServletResponse response) {
		JsonResult result = new JsonResult();
		// 获取参数
		String strWidth = request.getParameter("width");
		String strHeight = request.getParameter("height");
		String strCodeCount = request.getParameter("codeCount");
		String strSessionName = request.getParameter("sessionName");

		// 参数校验
		// 1.空参数校验
		String[] paramNames = new String[] { "width", "height", "codeCount",
				"sessionName" };
		String[] paramValues = new String[] { strWidth, strHeight,
				strCodeCount, strSessionName };
		String message = notEmpty(paramNames, paramValues);
		if (message != null) {
			result.setStatusCode(201);
			result.setReturnObj(message);
			return result;
		}

		// 2.数字类型校验
		String message1 = notInteger(strWidth);
		if (message1 != null) {
			result.setStatusCode(201);
			result.setReturnObj("width" + message1);
			return result;
		}
		String message2 = notInteger(strHeight);
		if (message2 != null) {
			result.setStatusCode(201);
			result.setReturnObj("height" + message2);
			return result;
		}
		String message3 = notInteger(strCodeCount);
		if (message3 != null) {
			result.setStatusCode(201);
			result.setReturnObj("codeCount" + message3);
			return result;
		}

		// 设置浏览器不缓存本页
		response.setHeader("Cache-Control", "no-cache");

		// 生成验证码，写入用户session
		String verifyCode = VerifyCodeUtil.generateTextCode(
				VerifyCodeUtil.TYPE_NUM_UPPER, Integer.parseInt(strCodeCount),
				null);
		request.getSession().setAttribute(strSessionName, verifyCode);

		// 输出验证码给客户端
		response.setContentType("image/jpeg");

		BufferedImage bim = VerifyCodeUtil.generateImageCode(verifyCode,
				Integer.parseInt(strWidth), Integer.parseInt(strHeight), 2,
				false, Color.white, new Color(73, 119, 46), null);
		try {
			//将验证码加密写到cookie中
			Cookie cookie = new Cookie("verifyCode", CommonCryptogram.encrypt(verifyCode));
			cookie.setPath("/");
			response.addCookie(cookie);
			
			ServletOutputStream sos = response.getOutputStream();
			ImageIO.write(bim, "JPEG", sos);
			sos.close();
			
			result.setStatusCode(200);
			result.setReturnObj("生成校验图片成功");
		} catch (IOException e) {
			result.setStatusCode(999);
			result.setReturnObj(e.getMessage());
			return result;
		}

		return result;
	}

	/** 数字类型校验 */
	public static String notInteger(String value) {
		try {
			if (value != null && value.length() != 0) {
				Integer.parseInt(value);
			}
		} catch (NumberFormatException e) {
			return "格式不正确!";
		}
		return null;
	}

	/** 空参数校验 */
	public static String notEmpty(String[] paramNames, String[] paramValues) {
		List<String> paramNameList = new ArrayList<String>();
		// 遍历paramValues，如果参数值为空，将参数名加入paramNameList
		for (int i = 0; i < paramNames.length; i++) {
			String paramName = paramNames[i];
			String paramValue = paramValues[i];
			if (paramValue == null || "".equals(paramValue)) {
				paramNameList.add(paramName);
			}
		}
		// 返回错误消息
		String message = null;
		if (paramNameList.size() != 0) {
			StringBuffer sb = new StringBuffer();
			sb.append("参数[");
			for (String paramName : paramNameList) {
				sb.append(paramName + ",");
			}
			if (paramNameList.size() > 0) {
				sb.deleteCharAt(sb.length() - 1);
			}
			sb.append("]不能为空!");
			message = sb.toString();
		}
		return message;
	}
}
