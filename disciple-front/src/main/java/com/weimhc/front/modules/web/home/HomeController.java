/*
 * 
 * 
 * 
 */
package com.weimhc.front.modules.web.home;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.weimhc.front.modules.web.common.RyshFrontBaseController;
import com.weimhc.modules.article.entity.Article;
import com.weimhc.modules.article.entity.ArticleCategory;
import com.weimhc.modules.article.service.ArticleService;
import com.weimhc.modules.article.service.ArticleTypeService;

/**
 * 
 * 首页
 * 
 * @author liuchao
 * 
 */
@Controller("homeController")
@RequestMapping("${frontPath}/")
public class HomeController extends RyshFrontBaseController {
	@Autowired
	private ArticleTypeService articleTypeService;
	@Autowired
	private ArticleService articleService;

	/**
	 * 网站首页
	 * 
	 * <p>
	 * 指向网站的首页
	 * </p>
	 * 
	 * @throws IOException
	 */
	@RequestMapping(value = { "", "index" })
	public String index(HttpServletRequest request,
			HttpServletResponse response, Model model,
			HttpSession httpSession) {
		String menu = "index";
		model.addAttribute("menu", menu);
		return "modules/home/index";

	}

	@RequestMapping(value = { "agreement" })
	public String agreement(HttpServletRequest request,
			HttpServletResponse response, Model model,
			HttpSession httpSession) {
		String menu = "index";
		Article article = new Article();
		article.setCategory(new ArticleCategory("9"));
		article = articleService.getEntity(article);
		model.addAttribute("article", article);
		model.addAttribute("menu", menu);
		return "modules/contact/agreement";

	}

}