/*
 * 
 * 
 * 
 */
package com.weimhc.framework.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.thinkgem.javamg.common.service.impl.BaseServiceImpl;

/**
 * abatract Interceptor - 虚拟拦截器，添加打印log功能
 * 
 * 
 */
public abstract class AbstractInterceptor extends BaseServiceImpl
		implements HandlerInterceptor {
	/**
	 * This implementation is empty.
	 */
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	/**
	 * This implementation is empty.
	 */
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}

}