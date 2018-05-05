/*
 * 
 * 
 * 
 */
package com.weimhc.front.modules.web.aboutus;

import java.io.IOException;
import java.util.List;

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
 * 联系我们
 * 
 * @author liuchao
 * 
 */
@Controller("contactUsController")
@RequestMapping("${frontPath}/")
public class ContactUsController extends RyshFrontBaseController {
	@Autowired
	private ArticleTypeService articleTypeService;
	@Autowired
	private ArticleService articleService;

	/**
	 * 联系我们
	 * 
	 * @throws IOException
	 */
	@RequestMapping(value = { "contactUs" })
	public String contactUs(HttpServletRequest request,
			HttpServletResponse response, Model model,
			HttpSession httpSession) {
		String menu = "contactUs";

		Article article = new Article();
		article.setCategory(new ArticleCategory("3"));
		article = articleService.getEntity(article);
		model.addAttribute("article", article);

		model.addAttribute("menu", menu);
		return "modules/contact/index";

	}

	/**
	 * 部门服务
	 * 
	 * @throws IOException
	 */
	@RequestMapping(value = { "services" })
	public String services(HttpServletRequest request,
			HttpServletResponse response, Model model,
			HttpSession httpSession) {
		String menu = "services";

		Article article = new Article();
		article.setCategory(new ArticleCategory("7"));
		article = articleService.getEntity(article);
		model.addAttribute("article", article);

		model.addAttribute("menu", menu);
		return "modules/about/index";

	}

	/**
	 * 客服服务
	 * 
	 * @throws IOException
	 */
	@RequestMapping(value = { "customerService" })
	public String customerService(HttpServletRequest request,
			HttpServletResponse response, Model model,
			HttpSession httpSession) {
		String menu = "index";

		Article article = new Article();
		article.setCategory(new ArticleCategory("4"));
		article = articleService.getEntity(article);
		model.addAttribute("article", article);

		model.addAttribute("menu", menu);
		return "modules/contact/service";

	}

	/**
	 * 免责申明
	 * 
	 * @throws IOException
	 */
	@RequestMapping(value = { "relief" })
	public String relief(HttpServletRequest request,
			HttpServletResponse response, Model model,
			HttpSession httpSession) {
		String menu = "index";

		Article article = new Article();
		article.setCategory(new ArticleCategory("5"));
		List<Article> result = articleService.findList(article);
		if (result.size() > 0) {
			article = result.get(0);
		}
		model.addAttribute("article", article);

		model.addAttribute("menu", menu);
		return "modules/contact/relief";

	}

}