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
import com.weimhc.modules.stats.entity.StatsSiteIsp;
import com.weimhc.modules.stats.service.StatsSiteIspService;

/**
 * 统计网络提供商Controller
 * @author lc
 * @version 2017-04-12
 */
@Controller
@RequestMapping(value = "${adminPath}/stats/statsSiteIsp")
public class StatsSiteIspController extends AdminBaseController {

	@Autowired
	private StatsSiteIspService statsSiteIspService;
	
	@ModelAttribute
	public StatsSiteIsp get(@RequestParam(required=false) String id) {
		StatsSiteIsp entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = statsSiteIspService.get(id);
		}
		if (entity == null){
			entity = new StatsSiteIsp();
		}
		return entity;
	}
	
	@RequiresPermissions("stats:statsSiteIsp:view")
	@RequestMapping(value = {"list", ""})
	public String list(StatsSiteIsp statsSiteIsp, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<StatsSiteIsp> page = statsSiteIspService.findPage(new Page<StatsSiteIsp>(request, response), statsSiteIsp); 
		model.addAttribute("page", page);
		model.addAttribute("statsSiteIsp", statsSiteIsp);
		return "modules/stats/statsSiteIspList";
	}

	@RequiresPermissions("stats:statsSiteIsp:view")
	@RequestMapping(value = "form")
	public String form(StatsSiteIsp statsSiteIsp, Model model) {
		model.addAttribute("statsSiteIsp", statsSiteIsp);
		return "modules/stats/statsSiteIspForm";
	}

	@RequiresPermissions("stats:statsSiteIsp:edit")
	@RequestMapping(value = "save")
	public String save(StatsSiteIsp statsSiteIsp, BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {
		if (!isValid(result, statsSiteIsp)){
			return form(statsSiteIsp, model);
		}
		statsSiteIspService.save(statsSiteIsp);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:"+Global.getAdminPath()+"/stats/statsSiteIsp/?repage";
	}
	
	@RequiresPermissions("stats:statsSiteIsp:edit")
	@RequestMapping(value = "delete")
	public String delete(StatsSiteIsp statsSiteIsp, RedirectAttributes redirectAttributes) {
		statsSiteIspService.deleteEntity(statsSiteIsp);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:"+Global.getAdminPath()+"/stats/statsSiteIsp/?repage";
	}

	@RequiresPermissions("stats:statsSiteIsp:edit")
	@RequestMapping(value = "/deletes", method = RequestMethod.POST)
	public @ResponseBody ResultMessage deletes(String[] ids,
			RedirectAttributes redirectAttributes) {
		statsSiteIspService.deleteBatchEntity(ids);
		return SUCCESS_MESSAGE;
	}
}