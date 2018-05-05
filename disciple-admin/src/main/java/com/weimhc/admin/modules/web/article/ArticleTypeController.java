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
import com.weimhc.framework.web.ResultMessage;
import com.weimhc.modules.article.entity.ArticleType;
import com.weimhc.modules.article.service.ArticleTypeService;

/**
 * 文章类型Controller
 * 
 * @author lc
 * @version 2016-11-22
 */
@Controller
@RequestMapping(value = "${adminPath}/article/articleType")
public class ArticleTypeController extends AdminBaseController {

	@Autowired
	private ArticleTypeService articleTypeService;

	@ModelAttribute
	public ArticleType get(@RequestParam(required = false) String id) {
		ArticleType entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = articleTypeService.get(id);
		}
		if (entity == null) {
			entity = new ArticleType();
		}
		return entity;
	}

	@RequiresPermissions("article:articleType:view")
	@RequestMapping(value = { "list", "" })
	public String list(ArticleType articleType, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		Page<ArticleType> page = articleTypeService.findPage(
				new Page<ArticleType>(request, response), articleType);
		model.addAttribute("page", page);
		return "modules/article/articleTypeList";
	}

	@RequiresPermissions("article:articleType:view")
	@RequestMapping(value = "form")
	public String form(ArticleType articleType, Model model) {
		model.addAttribute("articleType", articleType);
		return "modules/article/articleTypeForm";
	}

	@RequiresPermissions("article:articleType:edit")
	@RequestMapping(value = "save")
	public String save(ArticleType articleType, BindingResult result,
			Model model, RedirectAttributes redirectAttributes) {
		if (!isValid(result, articleType)) {
			return form(articleType, model);
		}
		articleTypeService.save(articleType);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:" + Global.getAdminPath()
				+ "/article/articleType/?repage";
	}

	@RequiresPermissions("article:articleType:edit")
	@RequestMapping(value = "delete")
	public String delete(ArticleType articleType,
			RedirectAttributes redirectAttributes) {
		articleTypeService.deleteEntity(articleType);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:" + Global.getAdminPath()
				+ "/article/articleType/?repage";
	}

	@RequiresPermissions("article:articleType:edit")
	@RequestMapping(value = "/deletes", method = RequestMethod.POST)
	public @ResponseBody ResultMessage deletes(String[] ids,
			RedirectAttributes redirectAttributes) {
		articleTypeService.deleteBatchEntity(ids);
		return SUCCESS_MESSAGE;
	}
}