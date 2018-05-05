/**
 * 
 */
package com.weimhc.admin.modules.web.article;

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
import com.weimhc.modules.article.service.ArticleService;
import com.weimhc.modules.article.utils.ArticleCategoryUtils;
import com.weimhc.modules.article.utils.ArticleTypeUtils;

/**
 * 文章Controller
 * 
 * @author srg
 * @version 2016-8-5
 */
@Controller
@RequestMapping(value = "${adminPath}/article/article")
public class ArticleController extends AdminBaseController {

	@Autowired
	private ArticleService articleService;

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

		Page<Article> page = articleService
				.findPage(new Page<Article>(request, response), article);
		model.addAttribute("page", page);
		model.addAttribute("articleCategoryList",
				ArticleCategoryUtils.findAll());
		model.addAttribute("article", article);
		return "modules/article/articleList";
	}

	@RequiresPermissions("article:article:view")
	@RequestMapping(value = "form")
	public String form(Article article, Model model) {
		model.addAttribute("article", article);
		model.addAttribute("articleTypeList", ArticleTypeUtils.findAll());
		model.addAttribute("uploadFolder", UploadFolder.article);
		return "modules/article/articleForm";

	}

	@RequiresPermissions("article:article:edit")
	@RequestMapping(value = "save")
	public String save(Article article, BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {

		articleService.save(article);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:" + Global.getAdminPath() + "/article/article/?repage";

	}

	@RequiresPermissions("article:article:edit")
	@RequestMapping(value = "delete")
	public String delete(Article article,
			RedirectAttributes redirectAttributes) {
		articleService.deleteEntity(article);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:" + Global.getAdminPath() + "/article/article/?repage";

	}

	@RequiresPermissions("article:article:edit")
	@RequestMapping(value = "/deletes", method = RequestMethod.POST)
	public @ResponseBody ResultMessage deletes(String[] ids,
			RedirectAttributes redirectAttributes) {
		articleService.deleteBatchEntity(ids);
		return SUCCESS_MESSAGE;
	}
}