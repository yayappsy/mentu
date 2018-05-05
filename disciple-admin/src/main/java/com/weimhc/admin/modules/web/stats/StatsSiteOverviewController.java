/**
 * 
 */
package com.weimhc.admin.modules.web.stats;

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
import com.weimhc.modules.stats.entity.StatsSiteOverview;
import com.weimhc.modules.stats.service.StatsSiteOverviewService;

/**
 * 统计网站概况Controller
 * 
 * @author lc
 * @version 2017-04-10
 */
@Controller
@RequestMapping(value = "${adminPath}/stats/statsSiteOverview")
public class StatsSiteOverviewController extends AdminBaseController {

	@Autowired
	private StatsSiteOverviewService statsSiteOverviewService;

	@ModelAttribute
	public StatsSiteOverview get(@RequestParam(required = false) String id) {
		StatsSiteOverview entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = statsSiteOverviewService.get(id);
		}
		if (entity == null) {
			entity = new StatsSiteOverview();
		}
		return entity;
	}

	@RequestMapping(value = { "visitor" })
	public String visitor(HttpServletRequest request,
			HttpServletResponse response) {

		return "modules/statsSite/visitor";
	}

	@RequestMapping(value = { "sources" })
	public String sources(HttpServletRequest request,
			HttpServletResponse response) {

		return "modules/statsSite/sources";
	}

	@RequestMapping(value = { "sezrchengine" })
	public String sezrchengine(HttpServletRequest request,
			HttpServletResponse response) {

		return "modules/statsSite/sezrchengine";
	}

	@RequestMapping(value = { "geographical" })
	public String geographical(HttpServletRequest request,
			HttpServletResponse response) {

		return "modules/statsSite/geographical";
	}

	@RequestMapping(value = { "systemenvironments" })
	public String systemenvironments(HttpServletRequest request,
			HttpServletResponse response) {

		return "modules/statsSite/systemenvironments";
	}

	@RequestMapping(value = { "xinlaovisitor" })
	public String xinlaovisitor(HttpServletRequest request,
			HttpServletResponse response) {

		return "modules/statsSite/xinlaovisitor";
	}

	@RequestMapping(value = { "survey" })
	public String survey(HttpServletRequest request,
			HttpServletResponse response) {

		return "modules/statsSite/survey";
	}

	@RequiresPermissions("stats:statsSiteOverview:view")
	@RequestMapping(value = { "list", "" })
	public String list(StatsSiteOverview statsSiteOverview,
			HttpServletRequest request, HttpServletResponse response,
			Model model) {
		Page<StatsSiteOverview> page = statsSiteOverviewService.findPage(
				new Page<StatsSiteOverview>(request, response),
				statsSiteOverview);
		model.addAttribute("page", page);
		model.addAttribute("statsSiteOverview", statsSiteOverview);
		return "modules/stats/statsSiteOverviewList";
	}

	@RequiresPermissions("stats:statsSiteOverview:view")
	@RequestMapping(value = "form")
	public String form(StatsSiteOverview statsSiteOverview, Model model) {
		model.addAttribute("statsSiteOverview", statsSiteOverview);
		return "modules/stats/statsSiteOverviewForm";
	}

	@RequiresPermissions("stats:statsSiteOverview:edit")
	@RequestMapping(value = "save")
	public String save(StatsSiteOverview statsSiteOverview,
			BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {
		if (!isValid(result, statsSiteOverview)) {
			return form(statsSiteOverview, model);
		}
		statsSiteOverviewService.save(statsSiteOverview);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:" + Global.getAdminPath()
				+ "/stats/statsSiteOverview/?repage";
	}

	@RequiresPermissions("stats:statsSiteOverview:edit")
	@RequestMapping(value = "delete")
	public String delete(StatsSiteOverview statsSiteOverview,
			RedirectAttributes redirectAttributes) {
		statsSiteOverviewService.deleteEntity(statsSiteOverview);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:" + Global.getAdminPath()
				+ "/stats/statsSiteOverview/?repage";
	}

	@RequiresPermissions("stats:statsSiteOverview:edit")
	@RequestMapping(value = "/deletes", method = RequestMethod.POST)
	public @ResponseBody ResultMessage deletes(String[] ids,
			RedirectAttributes redirectAttributes) {
		statsSiteOverviewService.deleteBatchEntity(ids);
		return SUCCESS_MESSAGE;
	}

	@RequestMapping(value = "siteList")
	public String siteList(HttpServletRequest request,
			HttpServletResponse response, Model model) {

		return "modules/statsSite/index";
	}
}