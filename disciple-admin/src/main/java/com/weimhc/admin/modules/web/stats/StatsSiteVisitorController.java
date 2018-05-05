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
import com.weimhc.modules.stats.entity.StatsSiteVisitor;
import com.weimhc.modules.stats.service.StatsSiteVisitorService;

/**
 * 统计新老访客Controller
 * @author lc
 * @version 2017-04-14
 */
@Controller
@RequestMapping(value = "${adminPath}/stats/statsSiteVisitor")
public class StatsSiteVisitorController extends AdminBaseController {

	@Autowired
	private StatsSiteVisitorService statsSiteVisitorService;
	
	@ModelAttribute
	public StatsSiteVisitor get(@RequestParam(required=false) String id) {
		StatsSiteVisitor entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = statsSiteVisitorService.get(id);
		}
		if (entity == null){
			entity = new StatsSiteVisitor();
		}
		return entity;
	}
	
	@RequiresPermissions("stats:statsSiteVisitor:view")
	@RequestMapping(value = {"list", ""})
	public String list(StatsSiteVisitor statsSiteVisitor, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<StatsSiteVisitor> page = statsSiteVisitorService.findPage(new Page<StatsSiteVisitor>(request, response), statsSiteVisitor); 
		model.addAttribute("page", page);
		model.addAttribute("statsSiteVisitor", statsSiteVisitor);
		return "modules/stats/statsSiteVisitorList";
	}

	@RequiresPermissions("stats:statsSiteVisitor:view")
	@RequestMapping(value = "form")
	public String form(StatsSiteVisitor statsSiteVisitor, Model model) {
		model.addAttribute("statsSiteVisitor", statsSiteVisitor);
		return "modules/stats/statsSiteVisitorForm";
	}

	@RequiresPermissions("stats:statsSiteVisitor:edit")
	@RequestMapping(value = "save")
	public String save(StatsSiteVisitor statsSiteVisitor, BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {
		if (!isValid(result, statsSiteVisitor)){
			return form(statsSiteVisitor, model);
		}
		statsSiteVisitorService.save(statsSiteVisitor);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:"+Global.getAdminPath()+"/stats/statsSiteVisitor/?repage";
	}
	
	@RequiresPermissions("stats:statsSiteVisitor:edit")
	@RequestMapping(value = "delete")
	public String delete(StatsSiteVisitor statsSiteVisitor, RedirectAttributes redirectAttributes) {
		statsSiteVisitorService.deleteEntity(statsSiteVisitor);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:"+Global.getAdminPath()+"/stats/statsSiteVisitor/?repage";
	}

	@RequiresPermissions("stats:statsSiteVisitor:edit")
	@RequestMapping(value = "/deletes", method = RequestMethod.POST)
	public @ResponseBody ResultMessage deletes(String[] ids,
			RedirectAttributes redirectAttributes) {
		statsSiteVisitorService.deleteBatchEntity(ids);
		return SUCCESS_MESSAGE;
	}
}