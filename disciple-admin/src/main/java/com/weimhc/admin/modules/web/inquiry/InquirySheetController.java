/**
 * 
 */
package com.weimhc.admin.modules.web.inquiry;

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
import com.weimhc.modules.base.entity.SnType;
import com.weimhc.modules.base.service.SnService;
import com.weimhc.modules.inquiry.entity.InquirySheet;
import com.weimhc.modules.inquiry.entity.InquirySource;
import com.weimhc.modules.inquiry.service.InquirySheetService;

/**
 * 询价单Controller
 * 
 * @author zsm
 * @version 2017-04-10
 */
@Controller
@RequestMapping(value = "${adminPath}/inquiry/inquirySheet")
public class InquirySheetController extends AdminBaseController {

	@Autowired
	private InquirySheetService inquirySheetService;

	@Autowired
	private SnService snService;

	@ModelAttribute
	public InquirySheet get(@RequestParam(required = false) String id) {
		InquirySheet entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = inquirySheetService.get(id);
		}
		if (entity == null) {
			entity = new InquirySheet();
		}
		return entity;
	}

	@RequiresPermissions("inquiry:inquirySheet:view")
	@RequestMapping(value = { "list", "" })
	public String list(InquirySheet inquirySheet, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		Page<InquirySheet> page = inquirySheetService.findPage(
				new Page<InquirySheet>(request, response), inquirySheet);
		model.addAttribute("page", page);
		model.addAttribute("inquirySheet", inquirySheet);
		return "modules/inquiry/inquirySheetList";
	}

	@RequiresPermissions("inquiry:inquirySheet:view")
	@RequestMapping(value = "form")
	public String form(InquirySheet inquirySheet, Model model) {
		model.addAttribute("inquirySheet", inquirySheet);
		return "modules/inquiry/inquirySheetForm";
	}

	@RequiresPermissions("inquiry:inquirySheet:view")
	@RequestMapping(value = "view")
	public String view(InquirySheet inquirySheet, Model model) {
		model.addAttribute("inquirySheet", inquirySheet);
		return "modules/inquiry/inquirySheetView";
	}

	@RequiresPermissions("inquiry:inquirySheet:edit")
	@RequestMapping(value = "save")
	public String save(InquirySheet inquirySheet, BindingResult result,
			Model model, RedirectAttributes redirectAttributes) {
		if (!isValid(result, inquirySheet)) {
			return form(inquirySheet, model);
		}
		if (StringUtils.isBlank(inquirySheet.getId())) {
			inquirySheet.setSn(snService.generate(SnType.inquirySheet));
		}
		if (inquirySheet.getSource() == null) {
			inquirySheet.setSource(InquirySource.admin);
		}
		inquirySheetService.save(inquirySheet);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:" + Global.getAdminPath()
				+ "/inquiry/inquirySheet/?repage";
	}

	@RequiresPermissions("inquiry:inquirySheet:edit")
	@RequestMapping(value = "updateStatus")
	public String updateStatus(InquirySheet inquirySheet, Model model,
			RedirectAttributes redirectAttributes) {
		inquirySheetService.updateStatus(inquirySheet);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:" + Global.getAdminPath()
				+ "/inquiry/inquirySheet/?repage";
	}

	@RequiresPermissions("inquiry:inquirySheet:edit")
	@RequestMapping(value = "delete")
	public String delete(InquirySheet inquirySheet,
			RedirectAttributes redirectAttributes) {
		inquirySheetService.deleteEntity(inquirySheet);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:" + Global.getAdminPath()
				+ "/inquiry/inquirySheet/?repage";
	}

	@RequiresPermissions("inquiry:inquirySheet:edit")
	@RequestMapping(value = "/deletes", method = RequestMethod.POST)
	public @ResponseBody ResultMessage deletes(String[] ids,
			RedirectAttributes redirectAttributes) {
		inquirySheetService.deleteBatchEntity(ids);
		return SUCCESS_MESSAGE;
	}
}