/**
 * 
 */
package com.weimhc.front.modules.security;

import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thinkgem.javamg.common.utils.StringUtils;
import com.weimhc.framework.service.RSAService;
import com.weimhc.framework.web.ResultMessage;
import com.weimhc.front.core.security.UsernamePasswordToken;
import com.weimhc.front.modules.utils.MemberUtils;
import com.weimhc.modules.member.entity.Member;

/**
 * 表单验证（包含验证码）过滤类
 * 
 * @version 2016-02-25
 */
@Service
public class FormAuthenticationFilter
		extends org.apache.shiro.web.filter.authc.FormAuthenticationFilter {

	private Logger logger = LoggerFactory.getLogger(getClass());

	public static final String DEFAULT_CAPTCHA_PARAM = "captcha";
	/** 使用id通过验证码组件校验 */
	public static final String DEFAULT_CAPTCHA_ID_PARAM = "captchaId";
	public static final String DEFAULT_MOBILE_PARAM = "mobileLogin";
	public static final String DEFAULT_MESSAGE_PARAM = "message";
	/** 经过加密的密码参数 */
	public static final String EN_PASSWORD_PARAM = "enPassword";

	private String captchaParam = DEFAULT_CAPTCHA_PARAM;
	private String captchaIdParam = DEFAULT_CAPTCHA_ID_PARAM;
	private String mobileLoginParam = DEFAULT_MOBILE_PARAM;
	private String messageParam = DEFAULT_MESSAGE_PARAM;
	private String enPasswordParam = EN_PASSWORD_PARAM;

	@Resource
	private RSAService rsaService;

	@Override
	protected AuthenticationToken createToken(ServletRequest request,
			ServletResponse response) {
		String username = getUsername(request);
		/** 密码是经过rsa加密的，所以要先解密 */
		String password = rsaService.decryptParameter(EN_PASSWORD_PARAM,
				(HttpServletRequest) request);
		rsaService.removePrivateKey((HttpServletRequest) request);
		if (password == null) {
			password = "";
		}
		boolean rememberMe = isRememberMe(request);
		String host = StringUtils.getRemoteAddr((HttpServletRequest) request);
		String captcha = getCaptcha(request);
		String captchaId = getCaptchaId(request);
		boolean mobile = isMobileLogin(request);
		UsernamePasswordToken token = new UsernamePasswordToken(username,
				password.toCharArray(), rememberMe, host, captcha, mobile);
		token.setCaptchaId(captchaId);
		return token;
	}

	public String getCaptchaParam() {
		return captchaParam;
	}

	public String getCaptchaIdParam() {
		return captchaIdParam;
	}

	protected String getCaptcha(ServletRequest request) {
		return WebUtils.getCleanParam(request, getCaptchaParam());
	}

	protected String getCaptchaId(ServletRequest request) {
		return WebUtils.getCleanParam(request, getCaptchaIdParam());
	}

	/** 在获取密码时，使用加密参数 */
	@Override
	public String getPasswordParam() {
		return enPasswordParam;
	}

	public String getMobileLoginParam() {
		return mobileLoginParam;
	}

	protected boolean isMobileLogin(ServletRequest request) {
		return WebUtils.isTrue(request, getMobileLoginParam());
	}

	public String getMessageParam() {
		return messageParam;
	}

	/**
	 * 登录成功之后跳转URL
	 */
	@Override
	public String getSuccessUrl() {
		return super.getSuccessUrl();
	}

	@Override
	protected void issueSuccessRedirect(ServletRequest request,
			ServletResponse response) throws Exception {
		// Principal p = UserUtils.getPrincipal();
		// if (p != null && !p.isMobileLogin()){
		WebUtils.issueRedirect(request, response, getSuccessUrl(), null, true);
		// }else{
		// super.issueSuccessRedirect(request, response);
		// }
	}

	/***
	 * 登录成功调用事件
	 */
	@Override
	protected boolean onLoginSuccess(AuthenticationToken token, Subject subject,
			ServletRequest request, ServletResponse response) throws Exception {
		// issueSuccessRedirect(request, response);
		// we handled the success redirect directly, prevent the chain from
		// continuing:
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;

		if (!"XMLHttpRequest".equalsIgnoreCase(
				httpServletRequest.getHeader("X-Requested-With"))
				|| request.getParameter("ajax") == null) {// 不是ajax请求
			logger.debug("is ajax request false");
			issueSuccessRedirect(request, response);
		} else {
			logger.debug("is ajax request true");
			httpServletResponse.setCharacterEncoding("UTF-8");
			PrintWriter out = httpServletResponse.getWriter();

			ObjectMapper mapper = new ObjectMapper();
			String json = mapper.writeValueAsString(
					ResultMessage.success("front.login.success"));

			out.println(json);
			out.flush();
			out.close();
		}

		return false;
	};

	/**
	 * 登录失败调用事件
	 */
	@Override
	protected boolean onLoginFailure(AuthenticationToken token,
			AuthenticationException e, ServletRequest request,
			ServletResponse response) {
		String className = e.getClass().getName(), message = "";
		if (IncorrectCredentialsException.class.getName().equals(className)
				|| UnknownAccountException.class.getName().equals(className)) {
			message = "error.login.incorrectCredentials";
		} else {
			message = e.getMessage();
			logger.debug("登录失败，错误信息是：" + message);
		}
		// 清除会员缓存
		UsernamePasswordToken uToken = (UsernamePasswordToken) token;
		if (StringUtils.isNotBlank(uToken.getUsername())) {
			Member member = MemberUtils.getByUsername(uToken.getUsername());
			if (member != null) {
				MemberUtils.clearCache(member);
			}
		}
		request.setAttribute(getFailureKeyAttribute(), className);
		request.setAttribute(getMessageParam(), message);
		return true;
	}

}