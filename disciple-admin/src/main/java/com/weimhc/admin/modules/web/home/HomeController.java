/*
 * 
 * 
 * 
 */
package com.weimhc.admin.modules.web.home;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.weimhc.admin.core.web.AdminBaseController;

/**
 * Controller - 首页
 * 
 * 
 * 
 */
@Controller
@RequestMapping("${adminPath}/home")
public class HomeController extends AdminBaseController {

	/**
	 * 首页
	 */
	@RequestMapping(value = { "", "index" })
	public String index(Model model) {
		return "/modules/home/welcomeIndex";
	}

}