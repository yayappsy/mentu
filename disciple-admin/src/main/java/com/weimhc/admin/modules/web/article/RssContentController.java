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

import com.google.common.collect.Lists;
import com.thinkgem.javamg.common.config.Global;
import com.thinkgem.javamg.common.persistence.Page;
import com.thinkgem.javamg.common.utils.StringUtils;
import com.weimhc.admin.core.web.AdminBaseController;
import com.weimhc.framework.web.ResultMessage;
import com.weimhc.modules.article.entity.RssContent;
import com.weimhc.modules.article.service.RssContentService;
import com.weimhc.modules.article.utils.RssContentUtils;

/**
 * 信息源内容Controller
 * 
 * @author zsm
 * @version 2017-05-08
 */
@Controller
@RequestMapping(value = "${adminPath}/article/rssContent")
public class RssContentController extends AdminBaseController {

	@Autowired
	private RssContentService rssContentService;

	@ModelAttribute
	public RssContent get(@RequestParam(required = false) String id) {
		RssContent entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = rssContentService.get(id);
		}
		if (entity == null) {
			entity = new RssContent();
		}
		return entity;
	}

	@RequiresPermissions("article:rssContent:view")
	@RequestMapping(value = { "list", "" })
	public String list(RssContent rssContent, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		Page<RssContent> page = rssContentService
				.findPage(new Page<RssContent>(request, response), rssContent);
		model.addAttribute("page", page);
		model.addAttribute("rssContent", rssContent);
		return "modules/article/rssContentList";
	}

	@RequiresPermissions("article:rssContent:view")
	@RequestMapping(value = "form")
	public String form(RssContent rssContent, Model model) {
		model.addAttribute("rssContent", rssContent);
		return "modules/article/rssContentForm";
	}

	@RequiresPermissions("article:rssContent:edit")
	@RequestMapping(value = "save")
	public String save(RssContent rssContent, BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {
		if (!isValid(result, rssContent)) {
			return form(rssContent, model);
		}
		rssContentService.save(rssContent);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:" + Global.getAdminPath()
				+ "/article/rssContent/?repage";
	}

	@RequiresPermissions("article:rssContent:edit")
	@RequestMapping(value = "fetch")
	public String fetch(RssContent rssContent, BindingResult result,
			Model model, RedirectAttributes redirectAttributes) {
		RssContentUtils.fetchRss(rssContent);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:" + Global.getAdminPath()
				+ "/article/rssContent/?repage";
	}

	/**
	 * 批量修改排序
	 */
	@RequiresPermissions("article:rssContent:edit")
	@RequestMapping(value = "updateSort")
	public String updateSort(String[] ids, Integer[] sorts,
			RedirectAttributes redirectAttributes) {
		List<RssContent> list = Lists.newArrayList();
		for (int i = 0; i < ids.length; i++) {
			RssContent entity = new RssContent(ids[i]);
			entity.setSort(sorts[i]);
			list.add(entity);
		}
		rssContentService.updateSort(list);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:" + Global.getAdminPath()
				+ "/article/rssContent/?repage";
	}

	@RequiresPermissions("article:rssContent:edit")
	@RequestMapping(value = "delete")
	public String delete(RssContent rssContent,
			RedirectAttributes redirectAttributes) {
		rssContentService.deleteEntity(rssContent);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:" + Global.getAdminPath()
				+ "/article/rssContent/?repage";
	}

	@RequiresPermissions("article:rssContent:edit")
	@RequestMapping(value = "/deletes", method = RequestMethod.POST)
	public @ResponseBody ResultMessage deletes(String[] ids,
			RedirectAttributes redirectAttributes) {
		rssContentService.deleteBatchEntity(ids);
		return SUCCESS_MESSAGE;
	}
}