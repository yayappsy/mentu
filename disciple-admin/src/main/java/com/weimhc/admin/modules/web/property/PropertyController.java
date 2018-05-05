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
import com.weimhc.modules.property.utils.PropertyTemplateUtils;

/**
 * 扩展属性Controller
 * 
 * @author zsm
 * @version 2017-03-29
 */
@Controller
@RequestMapping(value = "${adminPath}/property/property")
public class PropertyController extends AdminBaseController {

	@Autowired
	private PropertyService propertyService;

	@ModelAttribute
	public Property get(@RequestParam(required = false) String id) {
		Property entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = propertyService.get(id);
		}
		if (entity == null) {
			entity = new Property();
		}
		return entity;
	}

	@RequiresPermissions("property:property:view")
	@RequestMapping(value = { "list", "" })
	public String list(Property property, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		Page<Property> page = propertyService
				.findPage(new Page<Property>(request, response), property);
		model.addAttribute("page", page);
		model.addAttribute("property", property);
		return "modules/property/propertyList";
	}

	@RequiresPermissions("property:property:view")
	@RequestMapping(value = "form")
	public String form(Property property, Model model,
			HttpServletRequest request) {
		setPropertyTemplate(property, request);
		model.addAttribute("property", property);
		return "modules/property/propertyForm";
	}

	/**
	 * 从request中获取属性所属模板id
	 * 
	 * @param property
	 * @param request
	 */
	private void setPropertyTemplate(Property property,
			HttpServletRequest request) {
		String propertyTemplateId = request.getParameter("propertyTemplateId");
		if (StringUtils.isNotBlank(propertyTemplateId)) {
			property.setPropertyTemplate(
					new PropertyTemplate(propertyTemplateId));
		}
	}

	@RequiresPermissions("property:property:edit")
	@RequestMapping(value = "save")
	public String save(Property property, BindingResult result, Model model,
			RedirectAttributes redirectAttributes, HttpServletRequest request) {
		if (!isValid(result, property)) {
			return form(property, model, request);
		}
		propertyService.save(property);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:" + Global.getAdminPath()
				+ "/property/property/?repage";
	}

	@RequiresPermissions("property:property:edit")
	@RequestMapping(value = "ajaxSave")
	@ResponseBody
	public ResultMessage ajaxSave(Property property,
			HttpServletRequest request) {
		propertyService.save(property);
		return SUCCESS_MESSAGE;
	}

	@RequiresPermissions("property:property:edit")
	@RequestMapping(value = "delete")
	public String delete(Property property,
			RedirectAttributes redirectAttributes) {
		propertyService.deleteEntity(property);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:" + Global.getAdminPath()
				+ "/property/property/?repage";
	}

	@RequiresPermissions("property:property:edit")
	@RequestMapping(value = "/deletes", method = RequestMethod.POST)
	public @ResponseBody ResultMessage deletes(String[] ids,
			RedirectAttributes redirectAttributes) {
		propertyService.deleteBatchEntity(ids);
		return SUCCESS_MESSAGE;
	}

	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "data")
	public List<Property> data(@RequestParam(required = false) String extId,
			HttpServletRequest request, HttpServletResponse response) {
		Property property = new Property();
		setPropertyTemplate(property, request);
		List<Property> list = PropertyTemplateUtils.findPropertyList(property);
		return list;
	}
}