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
import com.weimhc.modules.article.entity.RssFeed;
import com.weimhc.modules.article.service.RssFeedService;

/**
 * 信息源接口地址Controller
 * 
 * @author zsm
 * @version 2017-05-08
 */
@Controller
@RequestMapping(value = "${adminPath}/article/rssFeed")
public class RssFeedController extends AdminBaseController {

	@Autowired
	private RssFeedService rssFeedService;

	@ModelAttribute
	public RssFeed get(@RequestParam(required = false) String id) {
		RssFeed entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = rssFeedService.get(id);
		}
		if (entity == null) {
			entity = new RssFeed();
		}
		return entity;
	}

	@RequiresPermissions("article:rssFeed:view")
	@RequestMapping(value = { "list", "" })
	public String list(RssFeed rssFeed, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		Page<RssFeed> page = rssFeedService
				.findPage(new Page<RssFeed>(request, response), rssFeed);
		dealWithSearchType(request, model);
		model.addAttribute("page", page);
		model.addAttribute("rssFeed", rssFeed);
		return "modules/article/rssFeedList";
	}

	@RequiresPermissions("article:rssFeed:view")
	@RequestMapping(value = "form")
	public String form(RssFeed rssFeed, Model model) {
		model.addAttribute("rssFeed", rssFeed);
		return "modules/article/rssFeedForm";
	}

	@RequiresPermissions("article:rssFeed:edit")
	@RequestMapping(value = "save")
	public String save(RssFeed rssFeed, BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {
		if (!isValid(result, rssFeed)) {
			return form(rssFeed, model);
		}
		rssFeedService.save(rssFeed);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:" + Global.getAdminPath() + "/article/rssFeed/?repage";
	}

	/**
	 * 批量修改排序
	 */
	@RequiresPermissions("article:rssFeed:edit")
	@RequestMapping(value = "updateSort")
	public String updateSort(String[] ids, Integer[] sorts,
			RedirectAttributes redirectAttributes) {
		List<RssFeed> list = Lists.newArrayList();
		for (int i = 0; i < ids.length; i++) {
			RssFeed entity = new RssFeed(ids[i]);
			entity.setSort(sorts[i]);
			list.add(entity);
		}
		rssFeedService.updateSort(list);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:" + Global.getAdminPath() + "/article/rssFeed/?repage";
	}

	@RequiresPermissions("article:rssFeed:edit")
	@RequestMapping(value = "delete")
	public String delete(RssFeed rssFeed,
			RedirectAttributes redirectAttributes) {
		rssFeedService.deleteEntity(rssFeed);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:" + Global.getAdminPath() + "/article/rssFeed/?repage";
	}

	@RequiresPermissions("article:rssFeed:edit")
	@RequestMapping(value = "/deletes", method = RequestMethod.POST)
	public @ResponseBody ResultMessage deletes(String[] ids,
			RedirectAttributes redirectAttributes) {
		rssFeedService.deleteBatchEntity(ids);
		return SUCCESS_MESSAGE;
	}
}