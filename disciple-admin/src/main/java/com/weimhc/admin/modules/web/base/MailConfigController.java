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
import com.thinkgem.javamg.common.security.Cryptos;
import com.thinkgem.javamg.common.utils.StringUtils;
import com.weimhc.admin.core.web.AdminBaseController;
import com.weimhc.framework.dto.resp.ApiResult;
import com.weimhc.framework.web.ResultMessage;
import com.weimhc.modules.base.entity.MailConfig;
import com.weimhc.modules.base.service.MailConfigService;
import com.weimhc.modules.base.utils.MailConfigUtils;

/**
 * 邮件发送配置信息Controller
 * 
 * @author zsm
 * @version 2017-06-14
 */
@Controller
@RequestMapping(value = "${adminPath}/base/mailConfig")
public class MailConfigController extends AdminBaseController {

	@Autowired
	private MailConfigService mailConfigService;

	@ModelAttribute
	public MailConfig get(@RequestParam(required = false) String id) {
		MailConfig entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = mailConfigService.get(id);
		}
		if (entity == null) {
			entity = new MailConfig();
		}
		return entity;
	}

	@RequiresPermissions("base:mailConfig:view")
	@RequestMapping(value = { "list", "" })
	public String list(MailConfig mailConfig, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		Page<MailConfig> page = mailConfigService
				.findPage(new Page<MailConfig>(request, response), mailConfig);
		model.addAttribute("page", page);
		model.addAttribute("mailConfig", mailConfig);
		return "modules/base/mailConfigList";
	}

	@RequiresPermissions("base:mailConfig:view")
	@RequestMapping(value = "form")
	public String form(MailConfig mailConfig, Model model) {
		model.addAttribute("mailConfig", mailConfig);
		return "modules/base/mailConfigForm";
	}

	@RequiresPermissions("base:mailConfig:edit")
	@RequestMapping(value = "save")
	public String save(MailConfig mailConfig, BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {
		if (!isValid(result, mailConfig)) {
			return form(mailConfig, model);
		}
		mailConfigService.save(mailConfig);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:" + Global.getAdminPath() + "/base/mailConfig/?repage";
	}

	@RequiresPermissions("base:mailConfig:view")
	@RequestMapping(value = "default")
	public String defaultForm(Model model) {
		model.addAttribute("mailConfig", MailConfigUtils.findDefault());
		return "modules/base/mailConfigDefaultForm";
	}

	@RequiresPermissions("base:mailConfig:edit")
	@RequestMapping(value = "ajaxSave")
	@ResponseBody
	public ApiResult<String> ajaxSave(MailConfig mailConfig) {
		if (StringUtils.isNotBlank(mailConfig.getNewPassword())) {
			mailConfig.setPassword(
					Cryptos.aesEncrypt(mailConfig.getNewPassword()));
		}
		mailConfigService.save(mailConfig);
		return ApiResult.success();
	}

	@RequiresPermissions("base:mailConfig:edit")
	@RequestMapping(value = "delete")
	public String delete(MailConfig mailConfig,
			RedirectAttributes redirectAttributes) {
		mailConfigService.deleteEntity(mailConfig);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:" + Global.getAdminPath() + "/base/mailConfig/?repage";
	}

	@RequiresPermissions("base:mailConfig:edit")
	@RequestMapping(value = "/deletes", method = RequestMethod.POST)
	public @ResponseBody ResultMessage deletes(String[] ids,
			RedirectAttributes redirectAttributes) {
		mailConfigService.deleteBatchEntity(ids);
		return SUCCESS_MESSAGE;
	}
}