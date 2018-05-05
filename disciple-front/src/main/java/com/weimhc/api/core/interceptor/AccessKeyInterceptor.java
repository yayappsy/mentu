package com.weimhc.api.core.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.web.cors.CorsUtils;

import com.thinkgem.javamg.common.config.Global;
import com.weimhc.framework.web.interceptor.AbstractInterceptor;

/**
 * 跨域访问
 * 
 * @author Administrator
 *
 */
public class AccessKeyInterceptor extends AbstractInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler) throws Exception {
		response.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, request.getHeader("Origin"));

		// 加入支持自定义的header 加入元素 token，前端就可以发送自定义token过来
		response.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS,
				"Origin, No-Cache, X-Requested-With, If-Modified-Since, Pragma, Last-Modified, Cache-Control, Expires, Content-Type, X-E4M-With,userId,token");
		response.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS, "PUT,POST,GET,DELETE,OPTIONS");
		response.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS, "true");
		response.setHeader(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.SET_COOKIE);

		response.setHeader(HttpHeaders.ACCESS_CONTROL_MAX_AGE, "3600");

		response.setHeader(HttpHeaders.CONTENT_TYPE, "application/json;charset=utf-8");

		if (CorsUtils.isPreFlightRequest(request)) {
			response.setStatus(200);
			return false;
		}

		return true;
	}

	public static boolean checkAllowOrgin(String url) {
		return StringUtils.contains(Global.getConfig("cors.url"), url);
	}

}
