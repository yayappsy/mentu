/**
 * 
 */
package com.weimhc.admin.modules.web.consultation;

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
import com.weimhc.modules.consultation.entity.ConsultationType;
import com.weimhc.modules.consultation.service.ConsultationTypeService;

/**
 * 咨询类型Controller
 * @author zsm
 * @version 2017-01-05
 */
@Controller
@RequestMapping(value = "${adminPath}/consultation/consultationType")
public class ConsultationTypeController extends AdminBaseController {

	@Autowired
	private ConsultationTypeService consultationTypeService;
	
	@ModelAttribute
	public ConsultationType get(@RequestParam(required=false) String id) {
		ConsultationType entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = consultationTypeService.get(id);
		}
		if (entity == null){
			entity = new ConsultationType();
		}
		return entity;
	}
	
	@RequiresPermissions("consultation:consultationType:view")
	@RequestMapping(value = {"list", ""})
	public String list(ConsultationType consultationType, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ConsultationType> page = consultationTypeService.findPage(new Page<ConsultationType>(request, response), consultationType); 
		model.addAttribute("page", page);
		model.addAttribute("consultationType", consultationType);
		return "modules/consultation/consultationTypeList";
	}

	@RequiresPermissions("consultation:consultationType:view")
	@RequestMapping(value = "form")
	public String form(ConsultationType consultationType, Model model) {
		model.addAttribute("consultationType", consultationType);
		return "modules/consultation/consultationTypeForm";
	}

	@RequiresPermissions("consultation:consultationType:edit")
	@RequestMapping(value = "save")
	public String save(ConsultationType consultationType, BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {
		if (!isValid(result, consultationType)){
			return form(consultationType, model);
		}
		consultationTypeService.save(consultationType);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:"+Global.getAdminPath()+"/consultation/consultationType/?repage";
	}
	@RequiresPermissions("consultation:consultationType:edit")
	@RequestMapping(value = "delete")
	public String delete(ConsultationType consultationType, RedirectAttributes redirectAttributes) {
		consultationTypeService.deleteEntity(consultationType);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:"+Global.getAdminPath()+"/consultation/consultationType/?repage";
	}

	@RequiresPermissions("consultation:consultationType:edit")
	@RequestMapping(value = "/deletes", method = RequestMethod.POST)
	public @ResponseBody ResultMessage deletes(String[] ids,
			RedirectAttributes redirectAttributes) {
		consultationTypeService.deleteBatchEntity(ids);
		return SUCCESS_MESSAGE;
	}
}