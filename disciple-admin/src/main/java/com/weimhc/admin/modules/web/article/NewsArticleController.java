/**
 * 
 */
package com.weimhc.admin.modules.web.article;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.javamg.common.config.Global;
import com.thinkgem.javamg.common.persistence.Page;
import com.thinkgem.javamg.common.utils.StringUtils;
import com.weimhc.admin.core.web.AdminBaseController;
import com.weimhc.framework.utils.UploadFolder;
import com.weimhc.framework.web.ResultMessage;
import com.weimhc.modules.article.entity.Article;
import com.weimhc.modules.article.entity.ArticleType;
import com.weimhc.modules.article.service.ArticleCategoryService;
import com.weimhc.modules.article.service.ArticleService;
import com.weimhc.modules.article.service.ArticleTypeService;

/**
 * 文章Controller
 * 
 * @author srg
 * @version 2016-8-5
 */
@Controller
@RequestMapping(value = "${adminPath}/article/news")
public class NewsArticleController extends AdminBaseController {

	@Autowired
	private ArticleService articleService;

	@Autowired
	private ArticleTypeService articleTypeService;
	@Autowired
	private ArticleCategoryService articleCategoryService;

	@ModelAttribute
	public Article get(@RequestParam(required = false) String id) {
		Article entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = articleService.get(id);
		}
		if (entity == null) {
			entity = new Article();
		}
		return entity;
	}

	@RequiresPermissions("article:article:view")
	@RequestMapping(value = { "list", "" })
	public String list(Article article, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String categoryParentId = request.getParameter("categoryParentId");
		Page<Article> page = articleService.findCategoryChildrendArticle(
				new Page<Article>(request, response), article);

		model.addAttribute("page", page);
		model.addAttribute("articles", article);
		model.addAttribute("categoryParentId", categoryParentId);
		dealWithSearchType(request, model);
		return "modules/article/newsArticleList";
	}

	@RequiresPermissions("article:article:view")
	@RequestMapping(value = "form")
	public String form(Article article, Model model) {
		// 作为文章的类型
		List<ArticleType> articleTypeList = articleTypeService
				.findAllList(new ArticleType());

		model.addAttribute("article", article);
		model.addAttribute("articleTypeList", articleTypeList);
		model.addAttribute("uploadFolder", UploadFolder.article);

		return "modules/article/newsArticleForm";

	}

	@RequiresPermissions("article:article:edit")
	@RequestMapping(value = "save")
	public String save(Article article, BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {

		articleService.save(article);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:" + Global.getAdminPath()
				+ "/article/news/?repage&category.parent.id="
				+ article.getCategory().getParentId();

	}

	@RequiresPermissions("article:article:edit")
	@RequestMapping(value = "delete")
	public String delete(Article article,
			RedirectAttributes redirectAttributes) {
		articleService.deleteEntity(article);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:" + Global.getAdminPath()
				+ "/article/news/?repage&category.parent.id="
				+ article.getCategory().getParentId();

	}

	@RequiresPermissions("article:article:edit")
	@RequestMapping(value = "/deletes", method = RequestMethod.POST)
	public @ResponseBody ResultMessage deletes(String[] ids,
			RedirectAttributes redirectAttributes) {
		articleService.deleteBatchEntity(ids);
		return SUCCESS_MESSAGE;
	}
}