/*
 * 
 * 
 * 
 */
package com.weimhc.framework.web.interceptor;

import java.lang.reflect.Method;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.weimhc.framework.annotation.AvoidDuplicateSubmission;

/**
 * Interceptor - 令牌 Token拦截器，防止用户重复提交数据
 * 
 * 
 */
public class TokenInterceptor extends HandlerInterceptorAdapter {

	private static Logger logger = LoggerFactory
			.getLogger(TokenInterceptor.class);

	/** "令牌"属性名称 */
	private static final String TOKEN_ATTRIBUTE_NAME = "token";

	/** "令牌"Cookie名称 */
	private static final String TOKEN_COOKIE_NAME = "token";

	/** "令牌"参数名称 */
	private static final String TOKEN_PARAMETER_NAME = "token";

	/** 错误消息 */
	private static final String ERROR_MESSAGE = "Bad or missing token!";

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		if (handler instanceof HandlerMethod) {
			HandlerMethod handlerMethod = (HandlerMethod) handler;
			Method method = handlerMethod.getMethod();
			AvoidDuplicateSubmission annotation = method
					.getAnnotation(AvoidDuplicateSubmission.class);
			if (annotation != null) {
				boolean needSaveSession = annotation.needSaveToken();
				if (needSaveSession) {
					request.getSession(false).setAttribute(TOKEN_PARAMETER_NAME,
							UUID.randomUUID().toString());
				}
				boolean needRemoveSession = annotation.needRemoveToken();
				if (needRemoveSession) {
					if (isRepeatSubmit(request)) {
						return false;
					}
					request.getSession(false)
							.removeAttribute(TOKEN_PARAMETER_NAME);
				}
			}
			return true;
		} else {
			return super.preHandle(request, response, handler);
		}
	}

	private boolean isRepeatSubmit(HttpServletRequest request) {
		String serverToken = (String) request.getSession(false)
				.getAttribute(TOKEN_PARAMETER_NAME);
		if (serverToken == null) {
			return true;
		}
		String clinetToken = request.getParameter(TOKEN_PARAMETER_NAME);
		if (clinetToken == null) {
			return true;
		}
		if (!serverToken.equals(clinetToken)) {
			return true;
		}
		return false;
	}

}