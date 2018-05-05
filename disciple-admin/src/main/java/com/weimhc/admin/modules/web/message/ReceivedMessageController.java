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
import com.weimhc.framework.dto.resp.ApiResult;
import com.weimhc.framework.web.ResultMessage;
import com.weimhc.modules.message.entity.InternalMessage;
import com.weimhc.modules.message.entity.ReceivedMessage;
import com.weimhc.modules.message.service.InternalMessageService;
import com.weimhc.modules.message.service.ReceivedMessageService;

/**
 * 接收到的消息Controller
 * 
 * @author zsm
 * @version 2017-03-23
 */
@Controller
@RequestMapping(value = "${adminPath}/message/receivedMessage")
public class ReceivedMessageController extends AdminBaseController {

	@Autowired
	private ReceivedMessageService receivedMessageService;
	@Autowired
	private InternalMessageService internalMessageService;

	@ModelAttribute
	public ReceivedMessage get(@RequestParam(required = false) String id) {
		ReceivedMessage entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = receivedMessageService.get(id);
		}
		if (entity == null) {
			entity = new ReceivedMessage();
		}
		return entity;
	}

	@RequiresPermissions("message:receivedMessage:view")
	@RequestMapping(value = { "list", "" })
	public String list(ReceivedMessage receivedMessage,
			HttpServletRequest request, HttpServletResponse response,
			Model model) {
		Page<ReceivedMessage> page = receivedMessageService.findPage(
				new Page<ReceivedMessage>(request, response), receivedMessage);
		model.addAttribute("page", page);
		model.addAttribute("receivedMessage", receivedMessage);
		return "modules/message/receivedMessageList";
	}

	@RequiresPermissions("message:receivedMessage:view")
	@RequestMapping(value = "form")
	public String form(ReceivedMessage receivedMessage, Model model) {
		model.addAttribute("receivedMessage", receivedMessage);
		return "modules/message/receivedMessageForm";
	}

	@RequiresPermissions("message:receivedMessage:edit")
	@RequestMapping(value = "save")
	public String save(ReceivedMessage receivedMessage, BindingResult result,
			Model model, RedirectAttributes redirectAttributes) {
		if (!isValid(result, receivedMessage)) {
			return form(receivedMessage, model);
		}
		receivedMessageService.save(receivedMessage);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:" + Global.getAdminPath()
				+ "/message/receivedMessage/?repage";
	}

	@RequiresPermissions("message:receivedMessage:edit")
	@RequestMapping(value = "delete")
	public String delete(ReceivedMessage receivedMessage,
			RedirectAttributes redirectAttributes) {
		receivedMessageService.deleteEntity(receivedMessage);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:" + Global.getAdminPath()
				+ "/message/receivedMessage/?repage";
	}

	@RequiresPermissions("message:receivedMessage:edit")
	@RequestMapping(value = "/deletes", method = RequestMethod.POST)
	public @ResponseBody ResultMessage deletes(String[] ids,
			RedirectAttributes redirectAttributes) {
		receivedMessageService.deleteBatchEntity(ids);
		return SUCCESS_MESSAGE;
	}

	@RequestMapping(value = "saveMessage")
	@ResponseBody
	public ApiResult<?> saveMessage(String memberIds, String messageId,
			HttpServletRequest request) {
		ApiResult<?> result = new ApiResult<>();
		String[] memberId = memberIds.split(",");
		InternalMessage internalMessage = internalMessageService.get(messageId);
		for (String member : memberId) {
			ReceivedMessage receivedMessage = new ReceivedMessage();
			receivedMessage.setRecipientId(member);
			receivedMessage.setMessage(internalMessage);
			receivedMessage.setIsRead(false);
			receivedMessage.setIsFavorite(false);
			receivedMessage.preInsert();
			receivedMessageService.save(receivedMessage);
		}
		return result;
	}
}