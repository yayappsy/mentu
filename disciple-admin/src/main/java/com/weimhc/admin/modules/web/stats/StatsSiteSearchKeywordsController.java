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
import com.weimhc.modules.stats.entity.StatsSiteSearchKeywords;
import com.weimhc.modules.stats.service.StatsSiteSearchKeywordsService;

/**
 * 统计搜索关键字Controller
 * @author lc
 * @version 2017-04-12
 */
@Controller
@RequestMapping(value = "${adminPath}/stats/statsSiteSearchKeywords")
public class StatsSiteSearchKeywordsController extends AdminBaseController {

	@Autowired
	private StatsSiteSearchKeywordsService statsSiteSearchKeywordsService;
	
	@ModelAttribute
	public StatsSiteSearchKeywords get(@RequestParam(required=false) String id) {
		StatsSiteSearchKeywords entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = statsSiteSearchKeywordsService.get(id);
		}
		if (entity == null){
			entity = new StatsSiteSearchKeywords();
		}
		return entity;
	}
	
	@RequiresPermissions("stats:statsSiteSearchKeywords:view")
	@RequestMapping(value = {"list", ""})
	public String list(StatsSiteSearchKeywords statsSiteSearchKeywords, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<StatsSiteSearchKeywords> page = statsSiteSearchKeywordsService.findPage(new Page<StatsSiteSearchKeywords>(request, response), statsSiteSearchKeywords); 
		model.addAttribute("page", page);
		model.addAttribute("statsSiteSearchKeywords", statsSiteSearchKeywords);
		return "modules/stats/statsSiteSearchKeywordsList";
	}

	@RequiresPermissions("stats:statsSiteSearchKeywords:view")
	@RequestMapping(value = "form")
	public String form(StatsSiteSearchKeywords statsSiteSearchKeywords, Model model) {
		model.addAttribute("statsSiteSearchKeywords", statsSiteSearchKeywords);
		return "modules/stats/statsSiteSearchKeywordsForm";
	}

	@RequiresPermissions("stats:statsSiteSearchKeywords:edit")
	@RequestMapping(value = "save")
	public String save(StatsSiteSearchKeywords statsSiteSearchKeywords, BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {
		if (!isValid(result, statsSiteSearchKeywords)){
			return form(statsSiteSearchKeywords, model);
		}
		statsSiteSearchKeywordsService.save(statsSiteSearchKeywords);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:"+Global.getAdminPath()+"/stats/statsSiteSearchKeywords/?repage";
	}
	
	@RequiresPermissions("stats:statsSiteSearchKeywords:edit")
	@RequestMapping(value = "delete")
	public String delete(StatsSiteSearchKeywords statsSiteSearchKeywords, RedirectAttributes redirectAttributes) {
		statsSiteSearchKeywordsService.deleteEntity(statsSiteSearchKeywords);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:"+Global.getAdminPath()+"/stats/statsSiteSearchKeywords/?repage";
	}

	@RequiresPermissions("stats:statsSiteSearchKeywords:edit")
	@RequestMapping(value = "/deletes", method = RequestMethod.POST)
	public @ResponseBody ResultMessage deletes(String[] ids,
			RedirectAttributes redirectAttributes) {
		statsSiteSearchKeywordsService.deleteBatchEntity(ids);
		return SUCCESS_MESSAGE;
	}
}