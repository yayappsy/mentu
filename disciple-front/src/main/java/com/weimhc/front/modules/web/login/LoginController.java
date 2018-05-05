/*
 * 
 * 
 * 
 */
package com.weimhc.front.modules.web.login;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.thinkgem.javamg.common.security.shiro.session.SessionDAO;
import com.thinkgem.javamg.common.utils.StringUtils;
import com.weimhc.framework.security.MemberPrincipal;
import com.weimhc.framework.service.RSAService;
import com.weimhc.framework.utils.CookieUtils;
import com.weimhc.framework.web.ResultMessage;
import com.weimhc.front.core.security.CoreFormAuthenticationFilter;
import com.weimhc.front.core.web.FrontBaseController;
import com.weimhc.front.modules.utils.MemberUtils;
import com.weimhc.modules.member.entity.Member;
import com.weimhc.modules.member.service.MemberService;

/**
 * Controller - 会员登录
 * 
 * 
 * 
 */
@Controller("loginController")
@RequestMapping("${frontPath}/login")
public class LoginController extends FrontBaseController {

	@Autowired
	private SessionDAO sessionDAO;

	@Resource
	private MemberService memberService;

	@Resource
	private RSAService rsaService;

	/**
	 * 登录检测
	 */
	@RequestMapping(value = "/check", method = RequestMethod.GET)
	public @ResponseBody Boolean check() {
		return MemberUtils.getSubject().isAuthenticated();
	}

	/**
	 * 登录失败，真正登录的POST请求由Filter完成
	 * 
	 */
	@RequestMapping(value = "submit", method = RequestMethod.POST)
	@Deprecated
	public String loginFail(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		MemberPrincipal principal = MemberUtils.getPrincipal();

		WebUtils.getSavedRequest(request);

		// 如果已经登录，则跳转到管理首页
		if (principal != null) {
			return "redirect:" + adminPath;
		}

		String username = WebUtils.getCleanParam(request,
				CoreFormAuthenticationFilter.DEFAULT_USERNAME_PARAM);
		boolean rememberMe = WebUtils.isTrue(request,
				CoreFormAuthenticationFilter.DEFAULT_REMEMBER_ME_PARAM);
		boolean mobile = WebUtils.isTrue(request,
				CoreFormAuthenticationFilter.DEFAULT_MOBILE_PARAM);
		String exception = (String) request.getAttribute(
				FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
		String message = (String) request.getAttribute(
				CoreFormAuthenticationFilter.DEFAULT_MESSAGE_PARAM);

		if (StringUtils.isBlank(message)
				|| StringUtils.equals(message, "null")) {
			message = "用户或密码错误, 请重试.";
		}

		model.addAttribute(CoreFormAuthenticationFilter.DEFAULT_USERNAME_PARAM,
				username);
		model.addAttribute(
				CoreFormAuthenticationFilter.DEFAULT_REMEMBER_ME_PARAM,
				rememberMe);
		model.addAttribute(CoreFormAuthenticationFilter.DEFAULT_MOBILE_PARAM,
				mobile);
		model.addAttribute(
				CoreFormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME,
				exception);
		model.addAttribute(CoreFormAuthenticationFilter.DEFAULT_MESSAGE_PARAM,
				message);

		if (logger.isDebugEnabled()) {
			logger.debug(
					"login fail, active session size: {}, message: {}, exception: {}",
					sessionDAO.getActiveSessions(false).size(), message,
					exception);
		}

		/*
		  // 非授权异常，登录失败，验证码加1。 if
		 (!UnauthorizedException.class.getName().equals(exception)) {
		   model.addAttribute("isValidateCodeLogin", SystemAuthorizingRealm
		  .isValidateCodeLogin(username, true, false)); }
		 */

		// 验证失败清空验证码

		// 如果是手机登录，则返回JSON字符串
		if (mobile) {
			return renderString(response, model);
		}

		return "modules/home/index";
	}

	/**
	 * 登录失败之后进行的处理，真正登录的POST请求由Filter完成
	 */
	@RequestMapping(value = "", method = RequestMethod.POST)
	@ResponseBody
	public ResultMessage loginAjax(HttpServletRequest request,
			HttpServletResponse response, Model model) {

		MemberPrincipal principal = MemberUtils.getPrincipal();

		// 如果已经登录，则跳转到管理首页
		if (principal != null) {
			CookieUtils.setCookie(request, response,
					Member.USERNAME_COOKIE_NAME, principal.getUsername());
			return SUCCESS_MESSAGE;
		}

		String exception = (String) request.getAttribute(
				FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
		String message = (String) request.getAttribute(
				CoreFormAuthenticationFilter.DEFAULT_MESSAGE_PARAM);

		if (StringUtils.equals(message, "null")) {
			message = "front.common.invalid";
		}
		if (logger.isDebugEnabled()) {
			logger.debug(
					"login fail, active session size: {}, message: {}, exception: {}",
					sessionDAO.getActiveSessions(false).size(), message,
					exception);
		}

		return ResultMessage.error(message);

	}

}