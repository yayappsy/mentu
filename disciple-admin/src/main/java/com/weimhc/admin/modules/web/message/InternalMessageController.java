/**
 * 
 */
package com.weimhc.admin.modules.web.message;

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
import com.weimhc.modules.message.entity.InternalMessage;
import com.weimhc.modules.message.entity.InternalMessageType;
import com.weimhc.modules.message.service.InternalMessageService;
import com.weimhc.modules.sys.utils.UserUtils;

/**
 * 站内信Controller
 * 
 * @author zsm
 * @version 2017-03-23
 */
@Controller
@RequestMapping(value = "${adminPath}/message/internalMessage")
public class InternalMessageController extends AdminBaseController {

	@Autowired
	private InternalMessageService internalMessageService;

	@ModelAttribute
	public InternalMessage get(@RequestParam(required = false) String id) {
		InternalMessage entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = internalMessageService.get(id);
		}
		if (entity == null) {
			entity = new InternalMessage();
		}
		return entity;
	}

	@RequiresPermissions("message:internalMessage:view")
	@RequestMapping(value = { "list", "" })
	public String list(InternalMessage internalMessage,
			HttpServletRequest request, HttpServletResponse response,
			Model model) {
		Page<InternalMessage> page = internalMessageService.findPage(
				new Page<InternalMessage>(request, response), internalMessage);
		model.addAttribute("page", page);
		model.addAttribute("internalMessage", internalMessage);
		model.addAttribute("messageTypes", InternalMessageType.values());
		return "modules/message/internalMessageList";
	}

	@RequiresPermissions("message:internalMessage:view")
	@RequestMapping(value = "form")
	public String form(InternalMessage internalMessage, Model model) {
		model.addAttribute("internalMessage", internalMessage);
		model.addAttribute("messageTypes", InternalMessageType.values());
		return "modules/message/internalMessageForm";
	}

	@RequiresPermissions("message:internalMessage:edit")
	@RequestMapping(value = "save")
	public String save(InternalMessage internalMessage, BindingResult result,
			Model model, RedirectAttributes redirectAttributes) {
		if (!isValid(result, internalMessage)) {
			return form(internalMessage, model);
		}
		if (internalMessage.getIsNewRecord()) {
			internalMessage.setSenderId(UserUtils.getCurrentUser().getId());
		}
		internalMessageService.save(internalMessage);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:" + Global.getAdminPath()
				+ "/message/internalMessage/?repage";
	}

	@RequiresPermissions("message:internalMessage:edit")
	@RequestMapping(value = "delete")
	public String delete(InternalMessage internalMessage,
			RedirectAttributes redirectAttributes) {
		internalMessageService.deleteEntity(internalMessage);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:" + Global.getAdminPath()
				+ "/message/internalMessage/?repage";
	}

	@RequiresPermissions("message:internalMessage:edit")
	@RequestMapping(value = "/deletes", method = RequestMethod.POST)
	public @ResponseBody ResultMessage deletes(String[] ids,
			RedirectAttributes redirectAttributes) {
		internalMessageService.deleteBatchEntity(ids);
		return SUCCESS_MESSAGE;
	}
}