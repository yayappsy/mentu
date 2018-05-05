/**
 * 
 */
package com.weimhc.admin.modules.web.stats;

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

import com.weimhc.framework.web.ResultMessage;
import com.weimhc.admin.core.web.AdminBaseController;
import com.weimhc.modules.stats.entity.StatsSiteJavaEnabled;
import com.weimhc.modules.stats.service.StatsSiteJavaEnabledService;

/**
 * 统计是否支持javaController
 * @author lc
 * @version 2017-04-12
 */
@Controller
@RequestMapping(value = "${adminPath}/stats/statsSiteJavaEnabled")
public class StatsSiteJavaEnabledController extends AdminBaseController {

	@Autowired
	private StatsSiteJavaEnabledService statsSiteJavaEnabledService;
	
	@ModelAttribute
	public StatsSiteJavaEnabled get(@RequestParam(required=false) String id) {
		StatsSiteJavaEnabled entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = statsSiteJavaEnabledService.get(id);
		}
		if (entity == null){
			entity = new StatsSiteJavaEnabled();
		}
		return entity;
	}
	
	@RequiresPermissions("stats:statsSiteJavaEnabled:view")
	@RequestMapping(value = {"list", ""})
	public String list(StatsSiteJavaEnabled statsSiteJavaEnabled, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<StatsSiteJavaEnabled> page = statsSiteJavaEnabledService.findPage(new Page<StatsSiteJavaEnabled>(request, response), statsSiteJavaEnabled); 
		model.addAttribute("page", page);
		model.addAttribute("statsSiteJavaEnabled", statsSiteJavaEnabled);
		return "modules/stats/statsSiteJavaEnabledList";
	}

	@RequiresPermissions("stats:statsSiteJavaEnabled:view")
	@RequestMapping(value = "form")
	public String form(StatsSiteJavaEnabled statsSiteJavaEnabled, Model model) {
		model.addAttribute("statsSiteJavaEnabled", statsSiteJavaEnabled);
		return "modules/stats/statsSiteJavaEnabledForm";
	}

	@RequiresPermissions("stats:statsSiteJavaEnabled:edit")
	@RequestMapping(value = "save")
	public String save(StatsSiteJavaEnabled statsSiteJavaEnabled, BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {
		if (!isValid(result, statsSiteJavaEnabled)){
			return form(statsSiteJavaEnabled, model);
		}
		statsSiteJavaEnabledService.save(statsSiteJavaEnabled);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:"+Global.getAdminPath()+"/stats/statsSiteJavaEnabled/?repage";
	}
	
	@RequiresPermissions("stats:statsSiteJavaEnabled:edit")
	@RequestMapping(value = "delete")
	public String delete(StatsSiteJavaEnabled statsSiteJavaEnabled, RedirectAttributes redirectAttributes) {
		statsSiteJavaEnabledService.deleteEntity(statsSiteJavaEnabled);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:"+Global.getAdminPath()+"/stats/statsSiteJavaEnabled/?repage";
	}

	@RequiresPermissions("stats:statsSiteJavaEnabled:edit")
	@RequestMapping(value = "/deletes", method = RequestMethod.POST)
	public @ResponseBody ResultMessage deletes(String[] ids,
			RedirectAttributes redirectAttributes) {
		statsSiteJavaEnabledService.deleteBatchEntity(ids);
		return SUCCESS_MESSAGE;
	}
}