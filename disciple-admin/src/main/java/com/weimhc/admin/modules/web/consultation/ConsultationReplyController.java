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
import com.weimhc.modules.consultation.entity.ConsultationReply;
import com.weimhc.modules.consultation.service.ConsultationReplyService;

/**
 * 咨询回复Controller
 * @author zsm
 * @version 2017-01-05
 */
@Controller
@RequestMapping(value = "${adminPath}/consultation/consultationReply")
public class ConsultationReplyController extends AdminBaseController {

	@Autowired
	private ConsultationReplyService consultationReplyService;
	
	@ModelAttribute
	public ConsultationReply get(@RequestParam(required=false) String id) {
		ConsultationReply entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = consultationReplyService.get(id);
		}
		if (entity == null){
			entity = new ConsultationReply();
		}
		return entity;
	}
	
	@RequiresPermissions("consultation:consultationReply:view")
	@RequestMapping(value = {"list", ""})
	public String list(ConsultationReply consultationReply, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ConsultationReply> page = consultationReplyService.findPage(new Page<ConsultationReply>(request, response), consultationReply); 
		model.addAttribute("page", page);
		model.addAttribute("consultationReply", consultationReply);
		return "modules/consultation/consultationReplyList";
	}

	@RequiresPermissions("consultation:consultationReply:view")
	@RequestMapping(value = "form")
	public String form(ConsultationReply consultationReply, Model model) {
		model.addAttribute("consultationReply", consultationReply);
		return "modules/consultation/consultationReplyForm";
	}

	@RequiresPermissions("consultation:consultationReply:edit")
	@RequestMapping(value = "save")
	public String save(ConsultationReply consultationReply, BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {
		if (!isValid(result, consultationReply)){
			return form(consultationReply, model);
		}
		consultationReplyService.save(consultationReply);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:"+Global.getAdminPath()+"/consultation/consultationReply/?repage";
	}
	@RequiresPermissions("consultation:consultationReply:edit")
	@RequestMapping(value = "delete")
	public String delete(ConsultationReply consultationReply, RedirectAttributes redirectAttributes) {
		consultationReplyService.deleteEntity(consultationReply);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:"+Global.getAdminPath()+"/consultation/consultationReply/?repage";
	}

	@RequiresPermissions("consultation:consultationReply:edit")
	@RequestMapping(value = "/deletes", method = RequestMethod.POST)
	public @ResponseBody ResultMessage deletes(String[] ids,
			RedirectAttributes redirectAttributes) {
		consultationReplyService.deleteBatchEntity(ids);
		return SUCCESS_MESSAGE;
	}
}