/**
 * 
 */
package com.weimhc.admin.modules.web.remind;

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
import com.weimhc.framework.dto.resp.ApiResult;
import com.weimhc.framework.web.ResultMessage;
import com.weimhc.modules.base.utils.MailConfigUtils;
import com.weimhc.modules.remind.entity.RemindTemplate;
import com.weimhc.modules.remind.service.RemindTemplateService;
import com.weimhc.modules.remind.utils.RemindUtils;

/**
 * 提醒模板Controller
 * 
 * @author zsm
 * @version 2017-03-23
 */
@Controller
@RequestMapping(value = "${adminPath}/remind/remindTemplate")
public class RemindTemplateController extends AdminBaseController {

	@Autowired
	private RemindTemplateService remindTemplateService;

	@ModelAttribute
	public RemindTemplate get(@RequestParam(required = false) String id) {
		RemindTemplate entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = remindTemplateService.get(id);
		}
		if (entity == null) {
			entity = new RemindTemplate();
		}
		return entity;
	}

	@RequiresPermissions("remind:remindTemplate:view")
	@RequestMapping(value = { "list", "" })
	public String list(RemindTemplate remindTemplate,
			HttpServletRequest request, HttpServletResponse response,
			Model model) {
		Page<RemindTemplate> page = remindTemplateService.findPage(
				new Page<RemindTemplate>(request, response), remindTemplate);
		model.addAttribute("page", page);
		model.addAttribute("remindTemplate", remindTemplate);
		return "modules/remind/remindTemplateList";
	}

	@RequiresPermissions("remind:remindTemplate:view")
	@RequestMapping(value = "form")
	public String form(RemindTemplate remindTemplate, Model model) {
		model.addAttribute("mailConfig", MailConfigUtils.findDefault());
		model.addAttribute("remindTemplate", remindTemplate);
		model.addAttribute("remindParameters",
				RemindUtils.findRemindParameterAll());
		return "modules/remind/remindTemplateEditForm";
	}

	@RequiresPermissions("remind:remindTemplate:edit")
	@RequestMapping(value = "ajaxSave")
	@ResponseBody
	public ApiResult<String> ajaxSave(RemindTemplate remindTemplate) {
		remindTemplateService.save(remindTemplate);
		return ApiResult.success();
	}

	@RequiresPermissions("remind:remindTemplate:edit")
	@RequestMapping(value = "save")
	public String save(RemindTemplate remindTemplate, BindingResult result,
			Model model, RedirectAttributes redirectAttributes) {
		if (!isValid(result, remindTemplate)) {
			return form(remindTemplate, model);
		}
		remindTemplateService.save(remindTemplate);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:" + Global.getAdminPath()
				+ "/remind/remindTemplate/?repage";
	}

	@RequiresPermissions("remind:remindTemplate:view")
	@RequestMapping(value = "contentForm")
	public String contentForm(RemindTemplate remindTemplate, Model model) {
		model.addAttribute("remindTemplate", remindTemplate);
		return "modules/remind/remindTemplateForm";
	}

	@RequiresPermissions("remind:remindTemplate:edit")
	@RequestMapping(value = "contentSave")
	public @ResponseBody ApiResult<String> contentSave(
			RemindTemplate remindTemplate) {
		remindTemplateService.save(remindTemplate);
		return ApiResult.success();
	}

	@RequiresPermissions("remind:remindTemplate:edit")
	@RequestMapping(value = "delete")
	public String delete(RemindTemplate remindTemplate,
			RedirectAttributes redirectAttributes) {
		remindTemplateService.deleteEntity(remindTemplate);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:" + Global.getAdminPath()
				+ "/remind/remindTemplate/?repage";
	}

	@RequiresPermissions("remind:remindTemplate:edit")
	@RequestMapping(value = "/deletes", method = RequestMethod.POST)
	public @ResponseBody ResultMessage deletes(String[] ids,
			RedirectAttributes redirectAttributes) {
		remindTemplateService.deleteBatchEntity(ids);
		return SUCCESS_MESSAGE;
	}
}