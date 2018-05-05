/**
 * 
 */
package com.weimhc.api.core.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.math.NumberUtils;

import com.thinkgem.javamg.common.mapper.JsonMapper;
import com.thinkgem.javamg.common.utils.StringUtils;
import com.weimhc.framework.dto.resp.ApiResult;
import com.weimhc.framework.web.interceptor.AbstractInterceptor;
import com.weimhc.framework.web.utils.MessageSourceUtils;

/**
 * api 时间戳拦截器
 * 
 * @version 2014-8-19
 */
public class TimestampInterceptor extends AbstractInterceptor {

	private static final String TIMESTAPM_STR = "timestamp";

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		String timestampStr = request.getHeader(TIMESTAPM_STR);
		long currentTimeMillis = System.currentTimeMillis();
		if (StringUtils.isBlank(timestampStr)
				|| (NumberUtils.toLong(timestampStr)
						+ 30) < currentTimeMillis) {
			response.setContentType("text/xml;charset=utf-8");
			PrintWriter out = response.getWriter();
			ApiResult apiResult = ApiResult.error(-2, MessageSourceUtils
					.getMessage("error.common.timeout"));
			out.print(JsonMapper.toJsonString(apiResult));
			out.flush();
			out.close();
			return false;
		}

		return true;
	}

}
