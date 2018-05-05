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
import com.weimhc.admin.core.web.AdminBaseController;
import com.weimhc.framework.web.ResultMessage;
import com.weimhc.modules.consultation.entity.Consultation;
import com.weimhc.modules.consultation.service.ConsultationService;
import com.weimhc.modules.consultation.utils.ConsultationTypeUtils;

/**
 * 咨询Controller
 * 
 * @author zsm
 * @version 2017-01-05
 */
@Controller
@RequestMapping(value = "${adminPath}/consultation/consultation")
public class ConsultationController extends AdminBaseController {

	@Autowired
	private ConsultationService consultationService;

	@ModelAttribute
	public Consultation get(@RequestParam(required = false) String id) {
		Consultation entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = consultationService.get(id);
		}
		if (entity == null) {
			entity = new Consultation();
		}
		return entity;
	}

	@RequiresPermissions("consultation:consultation:view")
	@RequestMapping(value = { "list", "" })
	public String list(Consultation consultation, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		Page<Consultation> page = consultationService.findPage(
				new Page<Consultation>(request, response), consultation);
		model.addAttribute("page", page);
		model.addAttribute("consultation", consultation);
		model.addAttribute("consultationTypeList",
				ConsultationTypeUtils.findAll());
		return "modules/consultation/consultationList";
	}

	@RequiresPermissions("consultation:consultation:view")
	@RequestMapping(value = "form")
	public String form(Consultation consultation, Model model) {
		model.addAttribute("consultation", consultation);
		return "modules/consultation/consultationForm";
	}

	@RequiresPermissions("consultation:consultation:edit")
	@RequestMapping(value = "save")
	public String save(Consultation consultation, BindingResult result,
			Model model, RedirectAttributes redirectAttributes) {
		if (!isValid(result, consultation)) {
			return form(consultation, model);
		}
		consultationService.save(consultation);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:" + Global.getAdminPath()
				+ "/consultation/consultation/?repage";
	}

	@RequiresPermissions("consultation:consultation:edit")
	@RequestMapping(value = "delete")
	public String delete(Consultation consultation,
			RedirectAttributes redirectAttributes) {
		consultationService.deleteEntity(consultation);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:" + Global.getAdminPath()
				+ "/consultation/consultation/?repage";
	}

	@RequiresPermissions("consultation:consultation:edit")
	@RequestMapping(value = "/deletes", method = RequestMethod.POST)
	public @ResponseBody ResultMessage deletes(String[] ids,
			RedirectAttributes redirectAttributes) {
		consultationService.deleteBatchEntity(ids);
		return SUCCESS_MESSAGE;
	}
}