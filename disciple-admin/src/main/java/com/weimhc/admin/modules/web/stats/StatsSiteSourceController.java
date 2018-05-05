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
import com.weimhc.modules.stats.entity.StatsSiteSource;
import com.weimhc.modules.stats.service.StatsSiteSourceService;

/**
 * 统计来源网站Controller
 * @author lc
 * @version 2017-04-12
 */
@Controller
@RequestMapping(value = "${adminPath}/stats/statsSiteSource")
public class StatsSiteSourceController extends AdminBaseController {

	@Autowired
	private StatsSiteSourceService statsSiteSourceService;
	
	@ModelAttribute
	public StatsSiteSource get(@RequestParam(required=false) String id) {
		StatsSiteSource entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = statsSiteSourceService.get(id);
		}
		if (entity == null){
			entity = new StatsSiteSource();
		}
		return entity;
	}
	
	@RequiresPermissions("stats:statsSiteSource:view")
	@RequestMapping(value = {"list", ""})
	public String list(StatsSiteSource statsSiteSource, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<StatsSiteSource> page = statsSiteSourceService.findPage(new Page<StatsSiteSource>(request, response), statsSiteSource); 
		model.addAttribute("page", page);
		model.addAttribute("statsSiteSource", statsSiteSource);
		return "modules/stats/statsSiteSourceList";
	}

	@RequiresPermissions("stats:statsSiteSource:view")
	@RequestMapping(value = "form")
	public String form(StatsSiteSource statsSiteSource, Model model) {
		model.addAttribute("statsSiteSource", statsSiteSource);
		return "modules/stats/statsSiteSourceForm";
	}

	@RequiresPermissions("stats:statsSiteSource:edit")
	@RequestMapping(value = "save")
	public String save(StatsSiteSource statsSiteSource, BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {
		if (!isValid(result, statsSiteSource)){
			return form(statsSiteSource, model);
		}
		statsSiteSourceService.save(statsSiteSource);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:"+Global.getAdminPath()+"/stats/statsSiteSource/?repage";
	}
	
	@RequiresPermissions("stats:statsSiteSource:edit")
	@RequestMapping(value = "delete")
	public String delete(StatsSiteSource statsSiteSource, RedirectAttributes redirectAttributes) {
		statsSiteSourceService.deleteEntity(statsSiteSource);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:"+Global.getAdminPath()+"/stats/statsSiteSource/?repage";
	}

	@RequiresPermissions("stats:statsSiteSource:edit")
	@RequestMapping(value = "/deletes", method = RequestMethod.POST)
	public @ResponseBody ResultMessage deletes(String[] ids,
			RedirectAttributes redirectAttributes) {
		statsSiteSourceService.deleteBatchEntity(ids);
		return SUCCESS_MESSAGE;
	}
}