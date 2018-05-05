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
import com.weimhc.modules.base.entity.Disease;
import com.weimhc.modules.base.service.DiseaseService;

/**
 * 疾病Controller
 * @author lc
 * @version 2017-01-06
 */
@Controller
@RequestMapping(value = "${adminPath}/disease/disease")
public class DiseaseController extends AdminBaseController {

	@Autowired
	private DiseaseService diseaseService;
	
	@ModelAttribute
	public Disease get(@RequestParam(required=false) String id) {
		Disease entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = diseaseService.get(id);
		}
		if (entity == null){
			entity = new Disease();
		}
		return entity;
	}
	
	@RequiresPermissions("disease:disease:view")
	@RequestMapping(value = {"list", ""})
	public String list(Disease disease, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Disease> page = diseaseService.findPage(new Page<Disease>(request, response), disease); 
		model.addAttribute("page", page);
		model.addAttribute("disease", disease);
		return "modules/disease/diseaseList";
	}

	@RequiresPermissions("disease:disease:view")
	@RequestMapping(value = "form")
	public String form(Disease disease, Model model) {
		model.addAttribute("disease", disease);
		return "modules/disease/diseaseForm";
	}

	@RequiresPermissions("disease:disease:edit")
	@RequestMapping(value = "save")
	public String save(Disease disease, BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {
		if (!isValid(result, disease)){
			return form(disease, model);
		}
		diseaseService.save(disease);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:"+Global.getAdminPath()+"/disease/disease/?repage";
	}
	@RequiresPermissions("disease:disease:edit")
	@RequestMapping(value = "delete")
	public String delete(Disease disease, RedirectAttributes redirectAttributes) {
		diseaseService.deleteEntity(disease);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:"+Global.getAdminPath()+"/disease/disease/?repage";
	}

	@RequiresPermissions("disease:disease:edit")
	@RequestMapping(value = "/deletes", method = RequestMethod.POST)
	public @ResponseBody ResultMessage deletes(String[] ids,
			RedirectAttributes redirectAttributes) {
		diseaseService.deleteBatchEntity(ids);
		return SUCCESS_MESSAGE;
	}
}