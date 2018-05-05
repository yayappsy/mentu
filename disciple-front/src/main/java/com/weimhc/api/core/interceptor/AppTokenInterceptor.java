/**
 * 
 */
package com.weimhc.api.core.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thinkgem.javamg.common.mapper.JsonMapper;
import com.thinkgem.javamg.common.utils.StringUtils;
import com.weimhc.framework.dto.resp.ApiResult;
import com.weimhc.framework.utils.AppTokenHelper;
import com.weimhc.framework.web.interceptor.AbstractInterceptor;
import com.weimhc.framework.web.utils.MessageSourceUtils;

/**
 * 会员token 拦截器
 * 
 * @version 2014-8-19
 */
public class AppTokenInterceptor extends AbstractInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler) throws Exception {

		String headerToken = request.getHeader(AppTokenHelper.TOKEN_STR);
		String token = null;
		if (StringUtils.isNotBlank(headerToken)) {
			token = headerToken;
		} else {
			token = request.getParameter(AppTokenHelper.TOKEN_STR);
		}

		String errorMessage = null;
		logger.debug("token:" + token);
		if (StringUtils.isBlank(token) || !AppTokenHelper.valid(token)) {
			errorMessage = MessageSourceUtils.getMessage("error.common.tokenNotExist");
		}
		if (StringUtils.isNotBlank(errorMessage)) {
			response.setContentType("text/xml;charset=utf-8");
			PrintWriter out = response.getWriter();
			ApiResult<?> apiResult = ApiResult.error(-3, errorMessage);
			out.print(JsonMapper.toJsonString(apiResult));
			out.flush();
			out.close();
			return false;
		} else
			// 如果没有登录则返回
			return true;
	}

}
