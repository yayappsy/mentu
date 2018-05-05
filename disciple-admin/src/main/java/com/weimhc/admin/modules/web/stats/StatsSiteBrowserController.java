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
import com.weimhc.modules.stats.entity.StatsSiteBrowser;
import com.weimhc.modules.stats.service.StatsSiteBrowserService;

/**
 * 统计浏览器Controller
 * @author lc
 * @version 2017-04-12
 */
@Controller
@RequestMapping(value = "${adminPath}/stats/statsSiteBrowser")
public class StatsSiteBrowserController extends AdminBaseController {

	@Autowired
	private StatsSiteBrowserService statsSiteBrowserService;
	
	@ModelAttribute
	public StatsSiteBrowser get(@RequestParam(required=false) String id) {
		StatsSiteBrowser entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = statsSiteBrowserService.get(id);
		}
		if (entity == null){
			entity = new StatsSiteBrowser();
		}
		return entity;
	}
	
	@RequiresPermissions("stats:statsSiteBrowser:view")
	@RequestMapping(value = {"list", ""})
	public String list(StatsSiteBrowser statsSiteBrowser, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<StatsSiteBrowser> page = statsSiteBrowserService.findPage(new Page<StatsSiteBrowser>(request, response), statsSiteBrowser); 
		model.addAttribute("page", page);
		model.addAttribute("statsSiteBrowser", statsSiteBrowser);
		return "modules/stats/statsSiteBrowserList";
	}

	@RequiresPermissions("stats:statsSiteBrowser:view")
	@RequestMapping(value = "form")
	public String form(StatsSiteBrowser statsSiteBrowser, Model model) {
		model.addAttribute("statsSiteBrowser", statsSiteBrowser);
		return "modules/stats/statsSiteBrowserForm";
	}

	@RequiresPermissions("stats:statsSiteBrowser:edit")
	@RequestMapping(value = "save")
	public String save(StatsSiteBrowser statsSiteBrowser, BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {
		if (!isValid(result, statsSiteBrowser)){
			return form(statsSiteBrowser, model);
		}
		statsSiteBrowserService.save(statsSiteBrowser);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:"+Global.getAdminPath()+"/stats/statsSiteBrowser/?repage";
	}
	
	@RequiresPermissions("stats:statsSiteBrowser:edit")
	@RequestMapping(value = "delete")
	public String delete(StatsSiteBrowser statsSiteBrowser, RedirectAttributes redirectAttributes) {
		statsSiteBrowserService.deleteEntity(statsSiteBrowser);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:"+Global.getAdminPath()+"/stats/statsSiteBrowser/?repage";
	}

	@RequiresPermissions("stats:statsSiteBrowser:edit")
	@RequestMapping(value = "/deletes", method = RequestMethod.POST)
	public @ResponseBody ResultMessage deletes(String[] ids,
			RedirectAttributes redirectAttributes) {
		statsSiteBrowserService.deleteBatchEntity(ids);
		return SUCCESS_MESSAGE;
	}
}