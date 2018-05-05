/**
 * 
 */
package com.weimhc.admin.modules.web.property;

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
import com.weimhc.modules.property.entity.PropertyValue;
import com.weimhc.modules.property.service.PropertyValueService;

/**
 * 扩展属性选项Controller
 * @author zsm
 * @version 2017-03-29
 */
@Controller
@RequestMapping(value = "${adminPath}/property/propertyValue")
public class PropertyValueController extends AdminBaseController {

	@Autowired
	private PropertyValueService propertyValueService;
	
	@ModelAttribute
	public PropertyValue get(@RequestParam(required=false) String id) {
		PropertyValue entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = propertyValueService.get(id);
		}
		if (entity == null){
			entity = new PropertyValue();
		}
		return entity;
	}
	
	@RequiresPermissions("property:propertyValue:view")
	@RequestMapping(value = {"list", ""})
	public String list(PropertyValue propertyValue, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PropertyValue> page = propertyValueService.findPage(new Page<PropertyValue>(request, response), propertyValue); 
		model.addAttribute("page", page);
		model.addAttribute("propertyValue", propertyValue);
		return "modules/property/propertyValueList";
	}

	@RequiresPermissions("property:propertyValue:view")
	@RequestMapping(value = "form")
	public String form(PropertyValue propertyValue, Model model) {
		model.addAttribute("propertyValue", propertyValue);
		return "modules/property/propertyValueForm";
	}

	@RequiresPermissions("property:propertyValue:edit")
	@RequestMapping(value = "save")
	public String save(PropertyValue propertyValue, BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {
		if (!isValid(result, propertyValue)){
			return form(propertyValue, model);
		}
		propertyValueService.save(propertyValue);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:"+Global.getAdminPath()+"/property/propertyValue/?repage";
	}
	@RequiresPermissions("property:propertyValue:edit")
	@RequestMapping(value = "delete")
	public String delete(PropertyValue propertyValue, RedirectAttributes redirectAttributes) {
		propertyValueService.deleteEntity(propertyValue);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:"+Global.getAdminPath()+"/property/propertyValue/?repage";
	}

	@RequiresPermissions("property:propertyValue:edit")
	@RequestMapping(value = "/deletes", method = RequestMethod.POST)
	public @ResponseBody ResultMessage deletes(String[] ids,
			RedirectAttributes redirectAttributes) {
		propertyValueService.deleteBatchEntity(ids);
		return SUCCESS_MESSAGE;
	}
}