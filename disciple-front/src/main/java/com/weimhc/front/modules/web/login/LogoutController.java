/*
 * 
 * 
 * 
 */
package com.weimhc.front.modules.web.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.weimhc.framework.utils.CookieUtils;
import com.weimhc.front.core.web.FrontBaseController;
import com.weimhc.modules.member.entity.Member;

/**
 * Controller - 会员注销
 * 
 * 
 * 
 */
@Controller("logoutController")
public class LogoutController extends FrontBaseController {

	/**
	 * 注销
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String execute(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		session.removeAttribute(Member.PRINCIPAL_ATTRIBUTE_NAME);
		CookieUtils.removeCookie(request, response,
				Member.USERNAME_COOKIE_NAME);
		return "redirect:/";
	}

}