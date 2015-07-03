package com.ai.c.base.interceptor;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * 记录日志的切面
 * 
 * @author LIUQIANMING
 * @createtime 2015-06-22
 */
@Aspect
public class LogAspect {
	Logger logger = Logger.getLogger(getClass());
	String line = System.getProperty("line.separator");

	@Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
	public void requestMap() {// 定义一个切点

	}

	@Around("requestMap()")
	public Object profile(ProceedingJoinPoint pjp) throws Throwable {
		StringBuilder sb = new StringBuilder();
		try {
			sb.append("kview invoke method:").append(
					pjp.getTarget().getClass().getName()).append(".").append(
					pjp.getSignature().getName());
			logger.info(sb.toString());

			long start = System.currentTimeMillis();
			Object obj = pjp.proceed();// 调用
			long spend = System.currentTimeMillis() - start;

			sb.delete(0, sb.length());
			ObjectMapper objectMapper = new ObjectMapper();
			String json = objectMapper.writeValueAsString(obj);
			sb.append(" ===============>kview response in ").append(spend).append("ms :").append(json);
			logger.info(sb.toString());
			return obj;
		} catch (Exception e) {
			Object[] args = pjp.getArgs();
			HttpServletRequest request = null;
			for (Object o : args) {
				if (o instanceof HttpServletRequest) {
					request = (HttpServletRequest) o;
					break;
				}
			}

			String uri = request.getRequestURI();
			sb.append(line);
			sb.append("uri:").append(uri).append(line);
			sb.append("params:");
			Enumeration<String> enums2 = request.getParameterNames();
			while (enums2.hasMoreElements()) {
				String key = enums2.nextElement();
				sb.append("\"").append(key).append("\"").append(" : ").append(
						request.getParameter(key)).append(", ");
			}

			logger.error(sb.toString());
			logger.error(e.getMessage(), e);

			throw e;
		} finally {
		}
	}

	@AfterThrowing(pointcut = "requestMap()", throwing = "ex")
	public void doRecoveryActions(Exception ex) {
		// logger.error(ex.getMessage(), ex);
	}

}
