/*
 * 
 * 
 * 
 */
package com.weimhc.front.modules.web.aboutus;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.weimhc.front.modules.web.common.RyshFrontBaseController;
import com.weimhc.modules.article.service.ArticleService;
import com.weimhc.modules.article.service.ArticleTypeService;

/**
 * 
 * 招贤纳士
 * 
 * @author liuchao
 * 
 */
@Controller("recruitController")
@RequestMapping("${frontPath}/recruit")
public class RecruitController extends RyshFrontBaseController {
	@Autowired
	private ArticleTypeService articleTypeService;
	@Autowired
	private ArticleService articleService;

	/**
	 * 招贤纳士
	 * 
	 * @throws IOException
	 */
	@RequestMapping(value = { "", "index" })
	public String index(HttpServletRequest request,
			HttpServletResponse response, Model model,
			HttpSession httpSession) {
		String menu = "index";

		model.addAttribute("menu", menu);
		return "modules/recruit/index";

	}

}