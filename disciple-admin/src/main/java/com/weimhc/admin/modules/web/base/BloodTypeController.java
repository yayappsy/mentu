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
import com.weimhc.admin.core.web.AdminBaseController;
import com.weimhc.framework.web.ResultMessage;
import com.weimhc.modules.base.entity.BloodType;
import com.weimhc.modules.base.service.BloodTypeService;

/**
 * 血型Controller
 * 
 * @author lc
 * @version 2016-06-24
 */
@Controller
@RequestMapping(value = "${adminPath}/base/bloodType")
public class BloodTypeController extends AdminBaseController {

	@Autowired
	private BloodTypeService bloodTypeService;

	@ModelAttribute
	public BloodType get(@RequestParam(required = false) String id) {
		BloodType entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = bloodTypeService.get(id);
		}
		if (entity == null) {
			entity = new BloodType();
		}
		return entity;
	}

	@RequiresPermissions("base:bloodType:view")
	@RequestMapping(value = { "list", "" })
	public String list(BloodType bloodType, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		Page<BloodType> page = bloodTypeService
				.findPage(new Page<BloodType>(request, response), bloodType);
		model.addAttribute("page", page);
		return "modules/base/bloodType/bloodTypeList";
	}

	@RequiresPermissions("base:bloodType:view")
	@RequestMapping(value = "form")
	public String form(BloodType bloodType, Model model) {
		model.addAttribute("bloodType", bloodType);
		return "modules/base/bloodType/bloodTypeForm";
	}

	@RequiresPermissions("base:bloodType:edit")
	@RequestMapping(value = "save")
	public String save(BloodType bloodType, BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {
		if (!isValid(result, bloodType)) {
			return form(bloodType, model);
		}
		bloodTypeService.save(bloodType);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:" + Global.getAdminPath() + "/base/bloodType/?repage";
	}

	@RequiresPermissions("base:bloodType:edit")
	@RequestMapping(value = "delete")
	public String delete(BloodType bloodType,
			RedirectAttributes redirectAttributes) {
		bloodTypeService.deleteEntity(bloodType);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:" + Global.getAdminPath() + "/base/bloodType/?repage";
	}

	@RequiresPermissions("base:bloodType:edit")
	@RequestMapping(value = "/deletes", method = RequestMethod.POST)
	public @ResponseBody ResultMessage deletes(String[] ids,
			RedirectAttributes redirectAttributes) {
		bloodTypeService.deleteBatchEntity(ids);
		return SUCCESS_MESSAGE;
	}
}