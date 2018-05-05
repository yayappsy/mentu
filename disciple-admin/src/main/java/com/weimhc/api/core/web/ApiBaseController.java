/**
 * 
 */
package com.weimhc.api.core.web;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;

import com.thinkgem.javamg.common.utils.StringUtils;
import com.thinkgem.javamg.common.web.BaseController;
import com.weimhc.framework.utils.AppTokenHelper;
import com.weimhc.framework.web.utils.MessageSourceUtils;

/**
 * api控制器支持类
 * 
 * @version 2015-12-24
 */
public abstract class ApiBaseController extends BaseController {

	/**
	 * 默认语言
	 */
	@Value("${message.locale.default}")
	protected Locale defaultLocal;

	/**
	 * 获取国际化消息
	 * 
	 * @param code
	 *            代码
	 * @param args
	 *            参数
	 * @return 国际化消息
	 */
	protected String getMessage(String code, Object... args) {
		return MessageSourceUtils.getMessage(code, args);
	}

	/**
	 * 获取请求中的token
	 * 
	 * @param request
	 * @return
	 */
	protected String getToken(HttpServletRequest request) {
		String headerToken = request.getHeader(AppTokenHelper.TOKEN_STR);
		if (StringUtils.isNotBlank(headerToken)) {
			return headerToken;
		}
		String queryToken = request.getParameter(AppTokenHelper.TOKEN_STR);
		if (StringUtils.isNotBlank(queryToken)) {
			return queryToken;
		}
		return null;
	}

}
