/**
 * 
 */
package com.weimhc.admin.modules.web.base;

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
import com.weimhc.modules.base.entity.ReligiousBelief;
import com.weimhc.modules.base.service.ReligiousBeliefService;

/**
 * 宗教信仰Controller
 * @author lc
 * @version 2017-01-05
 */
@Controller
@RequestMapping(value = "${adminPath}/base/religiousBelief")
public class ReligiousBeliefController extends AdminBaseController {

	@Autowired
	private ReligiousBeliefService religiousBeliefService;
	
	@ModelAttribute
	public ReligiousBelief get(@RequestParam(required=false) String id) {
		ReligiousBelief entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = religiousBeliefService.get(id);
		}
		if (entity == null){
			entity = new ReligiousBelief();
		}
		return entity;
	}
	
	@RequiresPermissions("base:religiousBelief:view")
	@RequestMapping(value = {"list", ""})
	public String list(ReligiousBelief religiousBelief, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ReligiousBelief> page = religiousBeliefService.findPage(new Page<ReligiousBelief>(request, response), religiousBelief); 
		model.addAttribute("page", page);
		model.addAttribute("religiousBelief", religiousBelief);
		return "modules/base/religiousBelief/religiousBeliefList";
	}

	@RequiresPermissions("base:religiousBelief:view")
	@RequestMapping(value = "form")
	public String form(ReligiousBelief religiousBelief, Model model) {
		model.addAttribute("religiousBelief", religiousBelief);
		return "modules/base/religiousBelief/religiousBeliefForm";
	}

	@RequiresPermissions("base:religiousBelief:edit")
	@RequestMapping(value = "save")
	public String save(ReligiousBelief religiousBelief, BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {
		if (!isValid(result, religiousBelief)){
			return form(religiousBelief, model);
		}
		religiousBeliefService.save(religiousBelief);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:"+Global.getAdminPath()+"/base/religiousBelief/?repage";
	}
	@RequiresPermissions("base:religiousBelief:edit")
	@RequestMapping(value = "delete")
	public String delete(ReligiousBelief religiousBelief, RedirectAttributes redirectAttributes) {
		religiousBeliefService.deleteEntity(religiousBelief);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:"+Global.getAdminPath()+"/base/religiousBelief/?repage";
	}

	@RequiresPermissions("base:religiousBelief:edit")
	@RequestMapping(value = "/deletes", method = RequestMethod.POST)
	public @ResponseBody ResultMessage deletes(String[] ids,
			RedirectAttributes redirectAttributes) {
		religiousBeliefService.deleteBatchEntity(ids);
		return SUCCESS_MESSAGE;
	}
}