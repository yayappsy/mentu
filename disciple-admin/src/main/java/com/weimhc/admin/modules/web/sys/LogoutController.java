/*
 * 
 * 
 * 
 */
package com.weimhc.admin.modules.web.sys;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.weimhc.admin.core.service.SystemService;
import com.weimhc.admin.core.utils.AdminUserUtils;
import com.weimhc.admin.core.web.AdminBaseController;

/**
 * Controller - 会员注销
 * 
 * 
 * 
 */
@Controller
public class LogoutController extends AdminBaseController {

	@Autowired
	private SystemService systemService;

	/**
	 * 注销
	 */
	@RequestMapping(value = "${adminPath}/logout", method = RequestMethod.GET)
	public String execute(HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		systemService.logout(AdminUserUtils.getUser());
		return "redirect:/";
	}

}