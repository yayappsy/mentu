/**
 * 
 */
package com.weimhc.admin.modules.web.base;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.javamg.common.config.Global;
import com.thinkgem.javamg.common.persistence.Page;
import com.thinkgem.javamg.common.utils.StringUtils;
import com.weimhc.admin.core.web.AdminBaseController;
import com.weimhc.framework.utils.UploadFolder;
import com.weimhc.framework.web.ResultMessage;
import com.weimhc.modules.base.entity.Setting;
import com.weimhc.modules.base.service.SettingService;
import com.weimhc.modules.base.utils.setting.SettingUtils;

/**
 * 网站设置Controller
 * 
 * @author zsm
 * @version 2016-01-28
 */
@Controller
@RequestMapping(value = "${adminPath}/setting/setting")
public class SettingController extends AdminBaseController {

	@Autowired
	private SettingService settingService;

	@ModelAttribute
	public Setting get(@RequestParam(required = false) String id) {
		Setting entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = settingService.get(id);
		}
		if (entity == null) {
			entity = new Setting();
		}
		return entity;
	}

	@RequiresPermissions("setting:setting:view")
	@RequestMapping(value = { "list", "" })
	public String list(Setting setting, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		Page<Setting> page = settingService
				.findPage(new Page<Setting>(request, response), setting);
		model.addAttribute("page", page);
		return "modules/base/setting/settingList";
	}

	@RequiresPermissions("setting:setting:edit")
	@RequestMapping(value = { "/{type}" })
	public String formByType(@PathVariable String type, Setting setting,
			HttpServletRequest request, HttpServletResponse response,
			Model model) {
		List<Setting> settings = SettingUtils.getSettingList(type);
		model.addAttribute("settings", settings);
		model.addAttribute("type", type);
		model.addAttribute("uploadFolder", UploadFolder.base);
		return "modules/base/setting/settingsUpdate";
	}

	@RequiresPermissions("setting:setting:view")
	@RequestMapping(value = "form")
	public String form(Setting setting, Model model) {
		model.addAttribute("setting", setting);
		return "modules/base/setting/settingForm";
	}

	@RequiresPermissions("setting:setting:view")
	@RequestMapping(value = "update")
	public String saveByType(Setting setting, Model model,
			RedirectAttributes redirectAttributes) {
		settingService.saveSettings(setting);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:" + Global.getAdminPath() + "/setting/setting/"
				+ setting.getType();
	}

	@RequiresPermissions("setting:setting:edit")
	@RequestMapping(value = "save")
	public String save(Setting setting, BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {
		if (!isValid(result, setting)) {
			return form(setting, model);
		}
		settingService.save(setting);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:" + Global.getAdminPath() + "/setting/setting/?repage";
	}

	@RequiresPermissions("setting:setting:edit")
	@RequestMapping(value = "delete")
	public String delete(Setting setting,
			RedirectAttributes redirectAttributes) {
		settingService.deleteEntity(setting);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:" + Global.getAdminPath() + "/setting/setting/?repage";
	}

	@RequiresPermissions("setting:setting:edit")
	@RequestMapping(value = "/deletes", method = RequestMethod.POST)
	public @ResponseBody ResultMessage deletes(String[] ids,
			RedirectAttributes redirectAttributes) {
		settingService.deleteBatchEntity(ids);
		return SUCCESS_MESSAGE;
	}
}