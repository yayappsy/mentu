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
import com.weimhc.modules.stats.entity.StatsSiteColor;
import com.weimhc.modules.stats.service.StatsSiteColorService;

/**
 * 统计屏幕颜色Controller
 * @author lc
 * @version 2017-04-12
 */
@Controller
@RequestMapping(value = "${adminPath}/stats/statsSiteColor")
public class StatsSiteColorController extends AdminBaseController {

	@Autowired
	private StatsSiteColorService statsSiteColorService;
	
	@ModelAttribute
	public StatsSiteColor get(@RequestParam(required=false) String id) {
		StatsSiteColor entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = statsSiteColorService.get(id);
		}
		if (entity == null){
			entity = new StatsSiteColor();
		}
		return entity;
	}
	
	@RequiresPermissions("stats:statsSiteColor:view")
	@RequestMapping(value = {"list", ""})
	public String list(StatsSiteColor statsSiteColor, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<StatsSiteColor> page = statsSiteColorService.findPage(new Page<StatsSiteColor>(request, response), statsSiteColor); 
		model.addAttribute("page", page);
		model.addAttribute("statsSiteColor", statsSiteColor);
		return "modules/stats/statsSiteColorList";
	}

	@RequiresPermissions("stats:statsSiteColor:view")
	@RequestMapping(value = "form")
	public String form(StatsSiteColor statsSiteColor, Model model) {
		model.addAttribute("statsSiteColor", statsSiteColor);
		return "modules/stats/statsSiteColorForm";
	}

	@RequiresPermissions("stats:statsSiteColor:edit")
	@RequestMapping(value = "save")
	public String save(StatsSiteColor statsSiteColor, BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {
		if (!isValid(result, statsSiteColor)){
			return form(statsSiteColor, model);
		}
		statsSiteColorService.save(statsSiteColor);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:"+Global.getAdminPath()+"/stats/statsSiteColor/?repage";
	}
	
	@RequiresPermissions("stats:statsSiteColor:edit")
	@RequestMapping(value = "delete")
	public String delete(StatsSiteColor statsSiteColor, RedirectAttributes redirectAttributes) {
		statsSiteColorService.deleteEntity(statsSiteColor);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:"+Global.getAdminPath()+"/stats/statsSiteColor/?repage";
	}

	@RequiresPermissions("stats:statsSiteColor:edit")
	@RequestMapping(value = "/deletes", method = RequestMethod.POST)
	public @ResponseBody ResultMessage deletes(String[] ids,
			RedirectAttributes redirectAttributes) {
		statsSiteColorService.deleteBatchEntity(ids);
		return SUCCESS_MESSAGE;
	}
}