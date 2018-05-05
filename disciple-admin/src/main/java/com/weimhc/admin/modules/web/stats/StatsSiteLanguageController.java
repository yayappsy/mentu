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
import com.weimhc.modules.stats.entity.StatsSiteLanguage;
import com.weimhc.modules.stats.service.StatsSiteLanguageService;

/**
 * 统计语言环境Controller
 * @author lc
 * @version 2017-04-12
 */
@Controller
@RequestMapping(value = "${adminPath}/stats/statsSiteLanguage")
public class StatsSiteLanguageController extends AdminBaseController {

	@Autowired
	private StatsSiteLanguageService statsSiteLanguageService;
	
	@ModelAttribute
	public StatsSiteLanguage get(@RequestParam(required=false) String id) {
		StatsSiteLanguage entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = statsSiteLanguageService.get(id);
		}
		if (entity == null){
			entity = new StatsSiteLanguage();
		}
		return entity;
	}
	
	@RequiresPermissions("stats:statsSiteLanguage:view")
	@RequestMapping(value = {"list", ""})
	public String list(StatsSiteLanguage statsSiteLanguage, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<StatsSiteLanguage> page = statsSiteLanguageService.findPage(new Page<StatsSiteLanguage>(request, response), statsSiteLanguage); 
		model.addAttribute("page", page);
		model.addAttribute("statsSiteLanguage", statsSiteLanguage);
		return "modules/stats/statsSiteLanguageList";
	}

	@RequiresPermissions("stats:statsSiteLanguage:view")
	@RequestMapping(value = "form")
	public String form(StatsSiteLanguage statsSiteLanguage, Model model) {
		model.addAttribute("statsSiteLanguage", statsSiteLanguage);
		return "modules/stats/statsSiteLanguageForm";
	}

	@RequiresPermissions("stats:statsSiteLanguage:edit")
	@RequestMapping(value = "save")
	public String save(StatsSiteLanguage statsSiteLanguage, BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {
		if (!isValid(result, statsSiteLanguage)){
			return form(statsSiteLanguage, model);
		}
		statsSiteLanguageService.save(statsSiteLanguage);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:"+Global.getAdminPath()+"/stats/statsSiteLanguage/?repage";
	}
	
	@RequiresPermissions("stats:statsSiteLanguage:edit")
	@RequestMapping(value = "delete")
	public String delete(StatsSiteLanguage statsSiteLanguage, RedirectAttributes redirectAttributes) {
		statsSiteLanguageService.deleteEntity(statsSiteLanguage);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:"+Global.getAdminPath()+"/stats/statsSiteLanguage/?repage";
	}

	@RequiresPermissions("stats:statsSiteLanguage:edit")
	@RequestMapping(value = "/deletes", method = RequestMethod.POST)
	public @ResponseBody ResultMessage deletes(String[] ids,
			RedirectAttributes redirectAttributes) {
		statsSiteLanguageService.deleteBatchEntity(ids);
		return SUCCESS_MESSAGE;
	}
}