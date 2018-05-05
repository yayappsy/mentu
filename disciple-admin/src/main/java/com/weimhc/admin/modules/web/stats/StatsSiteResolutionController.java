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
import com.weimhc.modules.stats.entity.StatsSiteResolution;
import com.weimhc.modules.stats.service.StatsSiteResolutionService;

/**
 * 统计屏幕分辨率Controller
 * @author lc
 * @version 2017-04-12
 */
@Controller
@RequestMapping(value = "${adminPath}/stats/statsSiteResolution")
public class StatsSiteResolutionController extends AdminBaseController {

	@Autowired
	private StatsSiteResolutionService statsSiteResolutionService;
	
	@ModelAttribute
	public StatsSiteResolution get(@RequestParam(required=false) String id) {
		StatsSiteResolution entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = statsSiteResolutionService.get(id);
		}
		if (entity == null){
			entity = new StatsSiteResolution();
		}
		return entity;
	}
	
	@RequiresPermissions("stats:statsSiteResolution:view")
	@RequestMapping(value = {"list", ""})
	public String list(StatsSiteResolution statsSiteResolution, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<StatsSiteResolution> page = statsSiteResolutionService.findPage(new Page<StatsSiteResolution>(request, response), statsSiteResolution); 
		model.addAttribute("page", page);
		model.addAttribute("statsSiteResolution", statsSiteResolution);
		return "modules/stats/statsSiteResolutionList";
	}

	@RequiresPermissions("stats:statsSiteResolution:view")
	@RequestMapping(value = "form")
	public String form(StatsSiteResolution statsSiteResolution, Model model) {
		model.addAttribute("statsSiteResolution", statsSiteResolution);
		return "modules/stats/statsSiteResolutionForm";
	}

	@RequiresPermissions("stats:statsSiteResolution:edit")
	@RequestMapping(value = "save")
	public String save(StatsSiteResolution statsSiteResolution, BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {
		if (!isValid(result, statsSiteResolution)){
			return form(statsSiteResolution, model);
		}
		statsSiteResolutionService.save(statsSiteResolution);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:"+Global.getAdminPath()+"/stats/statsSiteResolution/?repage";
	}
	
	@RequiresPermissions("stats:statsSiteResolution:edit")
	@RequestMapping(value = "delete")
	public String delete(StatsSiteResolution statsSiteResolution, RedirectAttributes redirectAttributes) {
		statsSiteResolutionService.deleteEntity(statsSiteResolution);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:"+Global.getAdminPath()+"/stats/statsSiteResolution/?repage";
	}

	@RequiresPermissions("stats:statsSiteResolution:edit")
	@RequestMapping(value = "/deletes", method = RequestMethod.POST)
	public @ResponseBody ResultMessage deletes(String[] ids,
			RedirectAttributes redirectAttributes) {
		statsSiteResolutionService.deleteBatchEntity(ids);
		return SUCCESS_MESSAGE;
	}
}