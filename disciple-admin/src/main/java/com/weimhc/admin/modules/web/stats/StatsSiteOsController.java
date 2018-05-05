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
import com.weimhc.modules.stats.entity.StatsSiteOs;
import com.weimhc.modules.stats.service.StatsSiteOsService;

/**
 * 统计网络设备类型Controller
 * @author lc
 * @version 2017-04-12
 */
@Controller
@RequestMapping(value = "${adminPath}/stats/statsSiteOs")
public class StatsSiteOsController extends AdminBaseController {

	@Autowired
	private StatsSiteOsService statsSiteOsService;
	
	@ModelAttribute
	public StatsSiteOs get(@RequestParam(required=false) String id) {
		StatsSiteOs entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = statsSiteOsService.get(id);
		}
		if (entity == null){
			entity = new StatsSiteOs();
		}
		return entity;
	}
	
	@RequiresPermissions("stats:statsSiteOs:view")
	@RequestMapping(value = {"list", ""})
	public String list(StatsSiteOs statsSiteOs, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<StatsSiteOs> page = statsSiteOsService.findPage(new Page<StatsSiteOs>(request, response), statsSiteOs); 
		model.addAttribute("page", page);
		model.addAttribute("statsSiteOs", statsSiteOs);
		return "modules/stats/statsSiteOsList";
	}

	@RequiresPermissions("stats:statsSiteOs:view")
	@RequestMapping(value = "form")
	public String form(StatsSiteOs statsSiteOs, Model model) {
		model.addAttribute("statsSiteOs", statsSiteOs);
		return "modules/stats/statsSiteOsForm";
	}

	@RequiresPermissions("stats:statsSiteOs:edit")
	@RequestMapping(value = "save")
	public String save(StatsSiteOs statsSiteOs, BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {
		if (!isValid(result, statsSiteOs)){
			return form(statsSiteOs, model);
		}
		statsSiteOsService.save(statsSiteOs);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:"+Global.getAdminPath()+"/stats/statsSiteOs/?repage";
	}
	
	@RequiresPermissions("stats:statsSiteOs:edit")
	@RequestMapping(value = "delete")
	public String delete(StatsSiteOs statsSiteOs, RedirectAttributes redirectAttributes) {
		statsSiteOsService.deleteEntity(statsSiteOs);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:"+Global.getAdminPath()+"/stats/statsSiteOs/?repage";
	}

	@RequiresPermissions("stats:statsSiteOs:edit")
	@RequestMapping(value = "/deletes", method = RequestMethod.POST)
	public @ResponseBody ResultMessage deletes(String[] ids,
			RedirectAttributes redirectAttributes) {
		statsSiteOsService.deleteBatchEntity(ids);
		return SUCCESS_MESSAGE;
	}
}