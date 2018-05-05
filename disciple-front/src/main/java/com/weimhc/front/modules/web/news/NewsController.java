/*
 * 
 * 
 * 
 */
package com.weimhc.front.modules.web.news;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.thinkgem.javamg.common.persistence.Page;
import com.thinkgem.javamg.common.utils.StringUtils;
import com.weimhc.front.modules.web.common.RyshFrontBaseController;
import com.weimhc.modules.article.entity.Article;
import com.weimhc.modules.article.entity.ArticleCategory;
import com.weimhc.modules.article.service.ArticleCategoryService;
import com.weimhc.modules.article.service.ArticleService;

/**
 * 
 * 资讯中心
 * 
 * @author liuchao
 * 
 */
@Controller("newsController")
@RequestMapping("${frontPath}/news")
public class NewsController extends RyshFrontBaseController {
	@Autowired
	private ArticleCategoryService articleCategoryService;
	@Autowired
	private ArticleService articleService;

	/**
	 * 资讯中心
	 * 
	 * @throws IOException
	 */
	@RequestMapping(value = { "", "index" })
	public String index(String categoryId, HttpServletRequest request,
			HttpServletResponse response, Model model,
			HttpSession httpSession) {
		String menu = "news";
		Article article = new Article();
		if (StringUtils.isNotBlank(categoryId)) {
			article.setCategory(new ArticleCategory(categoryId));
			Page<Article> page = articleService
					.findPage(new Page<Article>(request, response), article);
			model.addAttribute("page", page);
		} else {
			article.getSqlMap().put("searchType", "newsAll");
			article.setCategory(new ArticleCategory("1"));
			Page<Article> page = articleService.findCategoryChildrendArticle(
					new Page<Article>(request, response), article);
			model.addAttribute("page", page);
		}
		Integer random = articleService.getRandom();
		List<Article> randomArticles = articleService.getRandomArticle(random);
		model.addAttribute("randomArticles", randomArticles);
		model.addAttribute("menu", menu);
		model.addAttribute("categoryId", categoryId);
		return "modules/news/index";

	}
	
	/**
	 * 社会责任
	 * 
	 * @throws IOException
	 */
	@RequestMapping(value = { "social" })
	public String social(String categoryId, HttpServletRequest request,
			HttpServletResponse response, Model model,
			HttpSession httpSession) {
		String menu = "social";
		Article article = new Article();
		if (StringUtils.isNotBlank(categoryId)) {
			article.setCategory(new ArticleCategory(categoryId));
			Page<Article> page = articleService
					.findPage(new Page<Article>(request, response), article);
			model.addAttribute("page", page);
		} 
		model.addAttribute("menu", menu);
		model.addAttribute("categoryId", categoryId);
		return "modules/news/sindex";

	}

	/**
	 * 资讯中心详情
	 * 
	 * @throws IOException
	 */
	@RequestMapping(value = { "detail" })
	public String detail(String articleId, HttpServletRequest request,
			HttpServletResponse response, Model model,
			HttpSession httpSession) {
		String menu = "news";
		if (StringUtils.isNotBlank(articleId)) {
			Article article = articleService.get(articleId);
			model.addAttribute("article", article);

			Article searchArticle = new Article();
			searchArticle.setKeywords(article.getKeywords());
			Article relatedArticle = articleService
					.getRelatedArticle(searchArticle);
			if (relatedArticle == null) {
				searchArticle = new Article();
				searchArticle.setCategory(article.getCategory());
				searchArticle.setId(articleId);
				relatedArticle = articleService
						.getRelatedArticle(searchArticle);

			}
			model.addAttribute("relatedArticle", relatedArticle);
		}
		Integer random = articleService.getRandom();
		List<Article> randomArticles = articleService.getRandomArticle(random);
		model.addAttribute("randomArticles", randomArticles);
		model.addAttribute("menu", menu);
		return "modules/news/detail";

	}

	/**
	 * 资讯中心详情
	 * 
	 * @throws IOException
	 */
	@RequestMapping(value = { "subjectDetail" })
	public String subjectDetail(String articleId, HttpServletRequest request,
			HttpServletResponse response, Model model,
			HttpSession httpSession) {
		String menu = "news";
		if (StringUtils.isNotBlank(articleId)) {
			Article article = articleService.get(articleId);
			model.addAttribute("article", article);

		}
		model.addAttribute("menu", menu);
		return "modules/news/subjectDetail";

	}

}