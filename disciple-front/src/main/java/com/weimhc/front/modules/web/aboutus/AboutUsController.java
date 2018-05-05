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

import com.thinkgem.javamg.common.utils.StringUtils;
import com.weimhc.front.modules.web.common.RyshFrontBaseController;
import com.weimhc.modules.article.entity.Article;
import com.weimhc.modules.article.service.ArticleService;
import com.weimhc.modules.article.service.ArticleTypeService;

/**
 * 
 * 关于我们
 * 
 * @author liuchao
 * 
 */
@Controller("aboutUsController")
@RequestMapping("${frontPath}/aboutUs")
public class AboutUsController extends RyshFrontBaseController {
	@Autowired
	private ArticleTypeService articleTypeService;
	@Autowired
	private ArticleService articleService;

	/**
	 * 关于我们
	 * 
	 * @throws IOException
	 */
	@RequestMapping(value = { "", "index" })
	public String index(String articleId, HttpServletRequest request,
			HttpServletResponse response, Model model,
			HttpSession httpSession) {
		String menu = "aboutUs";
		if (StringUtils.isNotBlank(articleId)) {
			Article article = articleService.get(articleId);
			model.addAttribute("article", article);
		} else {
			articleId = "4";
			Article article = articleService.get(articleId);
			model.addAttribute("article", article);
		}
		model.addAttribute("menu", menu);
		model.addAttribute("articleId", articleId);
		return "modules/about/index";

	}
	

}