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
import com.weimhc.front.modules.utils.MemberUtils;
import com.weimhc.modules.member.entity.Member;

import io.swagger.annotations.Api;

/**
 * api控制器支持类
 * 
 * @version 2015-12-24
 */
@Api
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
		return searchQueryString(request, AppTokenHelper.TOKEN_STR);
	}

	/**
	 * 根据token获取登录信息
	 * 
	 * @param request
	 * @return
	 */
	protected Member getCurrentMember(HttpServletRequest request) {
		Member member = MemberUtils.getMemberByToken(getToken(request));
		if (StringUtils.isBlank(member.getId())) {
			member = MemberUtils.get("b69b1ed8a31c467eaffa4fb90491017a");
		}
		return member;
	}
}
