/**
 * 
 */
package com.weimhc.admin.modules.web.article;

import java.util.List;
import java.util.Map;

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

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.thinkgem.javamg.common.config.Global;
import com.thinkgem.javamg.common.utils.StringUtils;
import com.weimhc.admin.core.web.AdminBaseController;
import com.weimhc.framework.utils.UploadFolder;
import com.weimhc.framework.web.ResultMessage;
import com.weimhc.modules.article.entity.ArticleCategory;
import com.weimhc.modules.article.service.ArticleCategoryService;

/**
 * 文章分类Controller
 * 
 * @author srg
 * @version 2016-8-5
 */
@Controller
@RequestMapping(value = "${adminPath}/article/articleCategory")
public class ArticleCategoryController extends AdminBaseController {

	@Autowired
	private ArticleCategoryService articleCategoryService;

	@ModelAttribute
	public ArticleCategory get(@RequestParam(required = false) String id) {
		ArticleCategory entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = articleCategoryService.get(id);
		}
		if (entity == null) {
			entity = new ArticleCategory();
		}
		return entity;
	}

	@RequiresPermissions("article:articleCategory:view")
	@RequestMapping(value = { "list", "" })
	public String list(ArticleCategory articleCategory,
			HttpServletRequest request, HttpServletResponse response,
			Model model) {
		List<ArticleCategory> list = articleCategoryService
				.findAllList(articleCategory);
		model.addAttribute("list", list);
		return "modules/article/articleCategoryList";
	}

	@RequiresPermissions("article:articleCategory:view")
	@RequestMapping(value = "form")
	public String form(ArticleCategory articleCategory, Model model) {
		if (articleCategory.getParent() != null && StringUtils
				.isNotBlank(articleCategory.getParent().getId())) {
			articleCategory.setParent(articleCategoryService
					.get(articleCategory.getParent().getId()));
			// 获取排序号，最末节点排序号+30
			if (StringUtils.isBlank(articleCategory.getId())) {
				ArticleCategory articleCategoryChild = new ArticleCategory();
				articleCategoryChild.setParent(new ArticleCategory(
						articleCategory.getParent().getId()));
				List<ArticleCategory> list = articleCategoryService
						.findList(articleCategory);
				if (list.size() > 0) {
					articleCategory
							.setSort(list.get(list.size() - 1).getSort());
					if (articleCategory.getSort() != null) {
						articleCategory.setSort(articleCategory.getSort() + 30);
					}
				}
			}
		}
		if (articleCategory.getSort() == null) {
			articleCategory.setSort(30);
		}
		model.addAttribute("uploadFolder", UploadFolder.article);
		model.addAttribute("articleCategory", articleCategory);
		return "modules/article/articleCategoryForm";
	}

	@RequiresPermissions("article:articleCategory:edit")
	@RequestMapping(value = "save")
	public String save(ArticleCategory articleCategory, BindingResult result,
			Model model, RedirectAttributes redirectAttributes) {
		if (!isValid(result, articleCategory)) {
			return form(articleCategory, model);
		}
		articleCategoryService.save(articleCategory);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:" + Global.getAdminPath()
				+ "/article/articleCategory/?repage";
	}

	@RequiresPermissions("article:articleCategory:edit")
	@RequestMapping(value = "delete")
	public String delete(ArticleCategory articleCategory,
			RedirectAttributes redirectAttributes) {
		articleCategoryService.deleteEntity(articleCategory);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:" + Global.getAdminPath()
				+ "/article/articleCategory/?repage";
	}

	@RequiresPermissions("article:AdClick:edit")
	@RequestMapping(value = "/deletes", method = RequestMethod.POST)
	public @ResponseBody ResultMessage deletes(String[] ids,
			RedirectAttributes redirectAttributes) {
		articleCategoryService.deleteBatchEntity(ids);
		return SUCCESS_MESSAGE;
	}

	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(
			@RequestParam(required = false) String extId,
			HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		List<ArticleCategory> list = articleCategoryService
				.findList(new ArticleCategory());
		for (int i = 0; i < list.size(); i++) {
			ArticleCategory e = list.get(i);
			if (StringUtils.isBlank(extId)
					|| (extId != null && !extId.equals(e.getId())
							&& e.getParent().getId().equals(extId))) {
				Map<String, Object> map = Maps.newHashMap();
				map.put("id", e.getId());
				map.put("pId", e.getParentId());
				map.put("name", e.getName());
				mapList.add(map);
			}
		}
		return mapList;
	}

}