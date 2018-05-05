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
import com.weimhc.modules.stats.entity.StatsSiteFlashVersion;
import com.weimhc.modules.stats.service.StatsSiteFlashVersionService;

/**
 * 统计flash版本Controller
 * @author lc
 * @version 2017-04-12
 */
@Controller
@RequestMapping(value = "${adminPath}/stats/statsSiteFlashVersion")
public class StatsSiteFlashVersionController extends AdminBaseController {

	@Autowired
	private StatsSiteFlashVersionService statsSiteFlashVersionService;
	
	@ModelAttribute
	public StatsSiteFlashVersion get(@RequestParam(required=false) String id) {
		StatsSiteFlashVersion entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = statsSiteFlashVersionService.get(id);
		}
		if (entity == null){
			entity = new StatsSiteFlashVersion();
		}
		return entity;
	}
	
	@RequiresPermissions("stats:statsSiteFlashVersion:view")
	@RequestMapping(value = {"list", ""})
	public String list(StatsSiteFlashVersion statsSiteFlashVersion, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<StatsSiteFlashVersion> page = statsSiteFlashVersionService.findPage(new Page<StatsSiteFlashVersion>(request, response), statsSiteFlashVersion); 
		model.addAttribute("page", page);
		model.addAttribute("statsSiteFlashVersion", statsSiteFlashVersion);
		return "modules/stats/statsSiteFlashVersionList";
	}

	@RequiresPermissions("stats:statsSiteFlashVersion:view")
	@RequestMapping(value = "form")
	public String form(StatsSiteFlashVersion statsSiteFlashVersion, Model model) {
		model.addAttribute("statsSiteFlashVersion", statsSiteFlashVersion);
		return "modules/stats/statsSiteFlashVersionForm";
	}

	@RequiresPermissions("stats:statsSiteFlashVersion:edit")
	@RequestMapping(value = "save")
	public String save(StatsSiteFlashVersion statsSiteFlashVersion, BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {
		if (!isValid(result, statsSiteFlashVersion)){
			return form(statsSiteFlashVersion, model);
		}
		statsSiteFlashVersionService.save(statsSiteFlashVersion);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:"+Global.getAdminPath()+"/stats/statsSiteFlashVersion/?repage";
	}
	
	@RequiresPermissions("stats:statsSiteFlashVersion:edit")
	@RequestMapping(value = "delete")
	public String delete(StatsSiteFlashVersion statsSiteFlashVersion, RedirectAttributes redirectAttributes) {
		statsSiteFlashVersionService.deleteEntity(statsSiteFlashVersion);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:"+Global.getAdminPath()+"/stats/statsSiteFlashVersion/?repage";
	}

	@RequiresPermissions("stats:statsSiteFlashVersion:edit")
	@RequestMapping(value = "/deletes", method = RequestMethod.POST)
	public @ResponseBody ResultMessage deletes(String[] ids,
			RedirectAttributes redirectAttributes) {
		statsSiteFlashVersionService.deleteBatchEntity(ids);
		return SUCCESS_MESSAGE;
	}
}