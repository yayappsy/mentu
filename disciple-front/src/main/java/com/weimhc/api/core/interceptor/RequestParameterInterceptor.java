/**
 * 
 */
package com.weimhc.api.core.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.thinkgem.javamg.common.mapper.JsonMapper;
import com.weimhc.framework.web.interceptor.AbstractInterceptor;

/**
 * api 输出请求参数
 * 
 * <p>
 * 处理 enctype="multipart/form-data"时
 * </p>
 * 
 * @see com.weimhc.framework.web.servlet.filter.RequestParameterFilter
 * @version 2016-11-19
 */
public class RequestParameterInterceptor extends AbstractInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		if (logger.isDebugEnabled()) {
			if (ServletFileUpload.isMultipartContent(request)) {
				MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
				// 获取请求参数
				Map<String, String[]> parameterMap = multipartRequest
						.getParameterMap();
				String requestString = JsonMapper.toJsonString(parameterMap);

				logger.debug("请求参数: {} URI: {}", requestString,
						request.getRequestURI());
			}

		}
		return true;
	}

}
