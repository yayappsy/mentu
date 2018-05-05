package com.weimhc.framework.spring.interceptor;

import org.aspectj.lang.JoinPoint;
import org.springframework.cglib.proxy.Proxy;
import org.springframework.util.ClassUtils;

/**
 * 多数据源动态配置拦截器
 * 
 * @author zsm
 * 
 * @version 1.0
 */
//@Aspect
public class MultipleDataSourceInterceptor {

	public void dynamicSetDataSoruce(JoinPoint joinPoint) throws Exception {

		Class<?> clazz = joinPoint.getTarget().getClass();
		String className = clazz.getName();
		if (ClassUtils.isAssignable(clazz, Proxy.class)) {
			className = joinPoint.getSignature().getDeclaringTypeName();
		}
		String methodName = joinPoint.getSignature().getName();
		Object[] arguments = joinPoint.getArgs();

	}
}
