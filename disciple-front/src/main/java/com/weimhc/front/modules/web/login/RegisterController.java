/*
 * 
 * 
 * 
 */
package com.weimhc.front.modules.web.login;

import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.thinkgem.javamg.common.utils.CacheUtils;
import com.thinkgem.javamg.common.utils.IdGen;
import com.thinkgem.javamg.common.utils.StringUtils;
import com.weimhc.api.modules.dto.req.RegisterRQ;
import com.weimhc.framework.service.PictureCaptchaService;
import com.weimhc.framework.service.RSAService;
import com.weimhc.framework.utils.CookieUtils;
import com.weimhc.framework.web.ResultMessage;
import com.weimhc.front.core.security.UsernamePasswordToken;
import com.weimhc.front.core.web.FrontBaseController;
import com.weimhc.front.modules.service.SystemService;
import com.weimhc.front.modules.utils.MemberUtils;
import com.weimhc.modules.member.entity.Member;
import com.weimhc.modules.member.service.MemberService;
import com.weimhc.modules.sys.service.AreaService;
import com.weimhc.modules.user.entity.UserLoginAuthorization;

/**
 * Controller - 会员注册
 * 
 * 
 * 
 */
@Controller("registerController")
@RequestMapping("${frontPath}/register")
public class RegisterController extends FrontBaseController {

	@Resource
	private PictureCaptchaService pictureCaptchaService;

	@Resource
	private MemberService memberService;

	@Resource
	private SystemService systemService;

	@Resource
	private AreaService areaService;

	@Resource
	private RSAService rsaService;

	/**
	 * 检查用户名是否被禁用或已存在
	 */
	@RequestMapping(value = "/check_username", method = RequestMethod.GET)
	public @ResponseBody boolean checkUsername(String username) {
		if (StringUtils.isEmpty(username)) {
			return false;
		}
		if (memberService.usernameDisabled(username) || memberService.usernameExists(username)) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 检查E-mail是否存在
	 */
	@RequestMapping(value = "/check_email", method = RequestMethod.GET)
	public @ResponseBody boolean checkEmail(String email) {
		if (StringUtils.isEmpty(email)) {
			return false;
		}
		if (memberService.emailExists(email)) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 注册页面
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String index(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		String registerType = request.getParameter("registerType");
		if (StringUtils.isBlank(registerType)) {
			registerType = "mobile";
		}
		model.addAttribute("captchaId", IdGen.uuidByIdWorker());
		model.addAttribute("registerType", registerType);
		return "/modules/register/index";
	}

	/**
	 * 创建会员
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	private Member createMember(RegisterRQ body, HttpServletRequest request) {
		Member member = new Member();
		member.setUsername(body.getMobile());
		member.setNewPassword(body.getPassword());
		member.setMobile(body.getMobile());
		member.setRegisterIp(request.getRemoteAddr());
		UserLoginAuthorization userLoginAuthorization = new UserLoginAuthorization(
				member.getLatestUserInfo());
		userLoginAuthorization.setIfEnabled(true);
		userLoginAuthorization.setIfLocked(false);
		userLoginAuthorization.setLoginFailureCount(0);
		userLoginAuthorization.setLockedDate(null);
		userLoginAuthorization.setLoginIp(request.getRemoteAddr());
		userLoginAuthorization.setLoginDate(new Date());
		userLoginAuthorization.setLoginNum(3);
		member.setUserLoginAuthorization(userLoginAuthorization);
		systemService.insertMember(member);
		return member;
	}

	/**
	 * 注册提交
	 */
	@RequestMapping(value = "/submit", method = RequestMethod.POST)
	public @ResponseBody ResultMessage submit(RegisterRQ body, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		// String password = rsaService.decryptParameter("enPassword", request);
		String password = body.getPassword();
		rsaService.removePrivateKey(request);

		if (!StringUtils.isBlank(password)) {
			body.setPassword(password);
		}

		Object cacheCode = CacheUtils.get("captchaCache", body.getMobile());
		String captcha1 = request.getParameter("captcha");

		if (!captcha1.equals("123")) {
			return ResultMessage.error("captcha.invalid");
		}

		// CacheUtils.remove("captchaCache", loginRQ.getUsername());

		Member member = createMember(body, request);

		// 使session过期
		Map<String, Object> attributes = new HashMap<String, Object>();
		Enumeration<?> keys = session.getAttributeNames();
		while (keys.hasMoreElements()) {
			String key = (String) keys.nextElement();
			attributes.put(key, session.getAttribute(key));
		}
		session.invalidate();
		session = request.getSession();
		for (Entry<String, Object> entry : attributes.entrySet()) {
			session.setAttribute(entry.getKey(), entry.getValue());
		}

		CookieUtils.setCookie(request, response, Member.USERNAME_COOKIE_NAME, member.getUsername());

		boolean rememberMe = false;
		String host = StringUtils.getRemoteAddr(request);
		String captchaId = null;
		boolean mobile = false;
		UsernamePasswordToken token = new UsernamePasswordToken(body.getMobile(),
				password.toCharArray(), rememberMe, host, null, mobile);
		token.setIsNeedCaptcha(false);
		Subject subject = MemberUtils.getSubject();
		subject.login(token);

		return ResultMessage.success("front.register.success");
	}

}