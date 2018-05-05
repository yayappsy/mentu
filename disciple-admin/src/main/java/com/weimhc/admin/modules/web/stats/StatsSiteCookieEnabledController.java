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
import com.weimhc.modules.stats.entity.StatsSiteCookieEnabled;
import com.weimhc.modules.stats.service.StatsSiteCookieEnabledService;

/**
 * 统计是否支持cookieController
 * @author lc
 * @version 2017-04-12
 */
@Controller
@RequestMapping(value = "${adminPath}/stats/statsSiteCookieEnabled")
public class StatsSiteCookieEnabledController extends AdminBaseController {

	@Autowired
	private StatsSiteCookieEnabledService statsSiteCookieEnabledService;
	
	@ModelAttribute
	public StatsSiteCookieEnabled get(@RequestParam(required=false) String id) {
		StatsSiteCookieEnabled entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = statsSiteCookieEnabledService.get(id);
		}
		if (entity == null){
			entity = new StatsSiteCookieEnabled();
		}
		return entity;
	}
	
	@RequiresPermissions("stats:statsSiteCookieEnabled:view")
	@RequestMapping(value = {"list", ""})
	public String list(StatsSiteCookieEnabled statsSiteCookieEnabled, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<StatsSiteCookieEnabled> page = statsSiteCookieEnabledService.findPage(new Page<StatsSiteCookieEnabled>(request, response), statsSiteCookieEnabled); 
		model.addAttribute("page", page);
		model.addAttribute("statsSiteCookieEnabled", statsSiteCookieEnabled);
		return "modules/stats/statsSiteCookieEnabledList";
	}

	@RequiresPermissions("stats:statsSiteCookieEnabled:view")
	@RequestMapping(value = "form")
	public String form(StatsSiteCookieEnabled statsSiteCookieEnabled, Model model) {
		model.addAttribute("statsSiteCookieEnabled", statsSiteCookieEnabled);
		return "modules/stats/statsSiteCookieEnabledForm";
	}

	@RequiresPermissions("stats:statsSiteCookieEnabled:edit")
	@RequestMapping(value = "save")
	public String save(StatsSiteCookieEnabled statsSiteCookieEnabled, BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {
		if (!isValid(result, statsSiteCookieEnabled)){
			return form(statsSiteCookieEnabled, model);
		}
		statsSiteCookieEnabledService.save(statsSiteCookieEnabled);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:"+Global.getAdminPath()+"/stats/statsSiteCookieEnabled/?repage";
	}
	
	@RequiresPermissions("stats:statsSiteCookieEnabled:edit")
	@RequestMapping(value = "delete")
	public String delete(StatsSiteCookieEnabled statsSiteCookieEnabled, RedirectAttributes redirectAttributes) {
		statsSiteCookieEnabledService.deleteEntity(statsSiteCookieEnabled);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:"+Global.getAdminPath()+"/stats/statsSiteCookieEnabled/?repage";
	}

	@RequiresPermissions("stats:statsSiteCookieEnabled:edit")
	@RequestMapping(value = "/deletes", method = RequestMethod.POST)
	public @ResponseBody ResultMessage deletes(String[] ids,
			RedirectAttributes redirectAttributes) {
		statsSiteCookieEnabledService.deleteBatchEntity(ids);
		return SUCCESS_MESSAGE;
	}
}