/**
 * 
 */
package com.weimhc.admin.modules.web.property;

import java.util.List;

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
import com.weimhc.modules.property.entity.Property;
import com.weimhc.modules.property.entity.PropertyTemplate;
import com.weimhc.modules.property.service.PropertyService;
import com.weimhc.modules.property.service.PropertyTemplateService;

/**
 * 模板属性Controller
 * 
 * @author zsm
 * @version 2017-03-29
 */
@Controller
@RequestMapping(value = "${adminPath}/property/propertyTemplate")
public class PropertyTemplateController extends AdminBaseController {

	@Autowired
	private PropertyTemplateService propertyTemplateService;

	@Autowired
	private PropertyService propertyService;

	@ModelAttribute
	public PropertyTemplate get(@RequestParam(required = false) String id) {
		PropertyTemplate entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = propertyTemplateService.get(id);
		}
		if (entity == null) {
			entity = new PropertyTemplate();
		}
		return entity;
	}

	@RequiresPermissions("property:propertyTemplate:view")
	@RequestMapping(value = { "list", "" })
	public String list(PropertyTemplate propertyTemplate,
			HttpServletRequest request, HttpServletResponse response,
			Model model) {
		Page<PropertyTemplate> page = propertyTemplateService.findPage(
				new Page<PropertyTemplate>(request, response),
				propertyTemplate);
		model.addAttribute("page", page);
		model.addAttribute("propertyTemplate", propertyTemplate);
		return "modules/property/propertyTemplateList";
	}

	@RequiresPermissions("property:propertyTemplate:view")
	@RequestMapping(value = "form")
	public String form(PropertyTemplate propertyTemplate, Model model) {
		model.addAttribute("propertyTemplate", propertyTemplate);
		return "modules/property/propertyTemplateForm";
	}

	@RequiresPermissions("property:propertyTemplate:edit")
	@RequestMapping(value = "save")
	public String save(PropertyTemplate propertyTemplate, BindingResult result,
			Model model, RedirectAttributes redirectAttributes) {
		if (!isValid(result, propertyTemplate)) {
			return form(propertyTemplate, model);
		}
		propertyTemplateService.save(propertyTemplate);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:" + Global.getAdminPath()
				+ "/property/propertyTemplate/?repage";
	}

	/**
	 * 批量修改排序
	 */
	@RequiresPermissions("property:propertyTemplate:edit")
	@RequestMapping(value = "updatePropertySort")
	@ResponseBody
	public ResultMessage updateSort(PropertyTemplate propertyTemplate,
			RedirectAttributes redirectAttributes) {

		List<Property> list = propertyTemplate.getPropertyList();
		propertyService.updateSort(list);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return SUCCESS_MESSAGE;
	}

	@RequiresPermissions("property:propertyTemplate:edit")
	@RequestMapping(value = "delete")
	public String delete(PropertyTemplate propertyTemplate,
			RedirectAttributes redirectAttributes) {
		propertyTemplateService.deleteEntity(propertyTemplate);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:" + Global.getAdminPath()
				+ "/property/propertyTemplate/?repage";
	}

	@RequiresPermissions("property:propertyTemplate:edit")
	@RequestMapping(value = "/deletes", method = RequestMethod.POST)
	public @ResponseBody ResultMessage deletes(String[] ids,
			RedirectAttributes redirectAttributes) {
		propertyTemplateService.deleteBatchEntity(ids);
		return SUCCESS_MESSAGE;
	}
}