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
import com.weimhc.modules.stats.entity.StatsVisitDistrict;
import com.weimhc.modules.stats.service.StatsVisitDistrictService;

/**
 * 地域统计Controller
 * @author lc
 * @version 2017-04-11
 */
@Controller
@RequestMapping(value = "${adminPath}/stats/statsVisitDistrict")
public class StatsVisitDistrictController extends AdminBaseController {

	@Autowired
	private StatsVisitDistrictService statsVisitDistrictService;
	
	@ModelAttribute
	public StatsVisitDistrict get(@RequestParam(required=false) String id) {
		StatsVisitDistrict entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = statsVisitDistrictService.get(id);
		}
		if (entity == null){
			entity = new StatsVisitDistrict();
		}
		return entity;
	}
	
	@RequiresPermissions("stats:statsVisitDistrict:view")
	@RequestMapping(value = {"list", ""})
	public String list(StatsVisitDistrict statsVisitDistrict, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<StatsVisitDistrict> page = statsVisitDistrictService.findPage(new Page<StatsVisitDistrict>(request, response), statsVisitDistrict); 
		model.addAttribute("page", page);
		model.addAttribute("statsVisitDistrict", statsVisitDistrict);
		return "modules/stats/statsVisitDistrictList";
	}

	@RequiresPermissions("stats:statsVisitDistrict:view")
	@RequestMapping(value = "form")
	public String form(StatsVisitDistrict statsVisitDistrict, Model model) {
		model.addAttribute("statsVisitDistrict", statsVisitDistrict);
		return "modules/stats/statsVisitDistrictForm";
	}

	@RequiresPermissions("stats:statsVisitDistrict:edit")
	@RequestMapping(value = "save")
	public String save(StatsVisitDistrict statsVisitDistrict, BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {
		if (!isValid(result, statsVisitDistrict)){
			return form(statsVisitDistrict, model);
		}
		statsVisitDistrictService.save(statsVisitDistrict);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:"+Global.getAdminPath()+"/stats/statsVisitDistrict/?repage";
	}
	
	@RequiresPermissions("stats:statsVisitDistrict:edit")
	@RequestMapping(value = "delete")
	public String delete(StatsVisitDistrict statsVisitDistrict, RedirectAttributes redirectAttributes) {
		statsVisitDistrictService.deleteEntity(statsVisitDistrict);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:"+Global.getAdminPath()+"/stats/statsVisitDistrict/?repage";
	}

	@RequiresPermissions("stats:statsVisitDistrict:edit")
	@RequestMapping(value = "/deletes", method = RequestMethod.POST)
	public @ResponseBody ResultMessage deletes(String[] ids,
			RedirectAttributes redirectAttributes) {
		statsVisitDistrictService.deleteBatchEntity(ids);
		return SUCCESS_MESSAGE;
	}
}