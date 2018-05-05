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

import com.weimhc.framework.web.ResultMessage;
import com.weimhc.admin.core.web.AdminBaseController;
import com.weimhc.modules.base.entity.BodyPartItem;
import com.weimhc.modules.base.service.BodyPartItemService;

/**
 * 身体部位细节Controller
 * @author zsm
 * @version 2017-01-22
 */
@Controller
@RequestMapping(value = "${adminPath}/base/bodyPartItem")
public class BodyPartItemController extends AdminBaseController {

	@Autowired
	private BodyPartItemService bodyPartItemService;
	
	@ModelAttribute
	public BodyPartItem get(@RequestParam(required=false) String id) {
		BodyPartItem entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = bodyPartItemService.get(id);
		}
		if (entity == null){
			entity = new BodyPartItem();
		}
		return entity;
	}
	
	@RequiresPermissions("base:bodyPartItem:view")
	@RequestMapping(value = {"list", ""})
	public String list(BodyPartItem bodyPartItem, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<BodyPartItem> page = bodyPartItemService.findPage(new Page<BodyPartItem>(request, response), bodyPartItem); 
		model.addAttribute("page", page);
		model.addAttribute("bodyPartItem", bodyPartItem);
		return "modules/base/bodyPartItemList";
	}

	@RequiresPermissions("base:bodyPartItem:view")
	@RequestMapping(value = "form")
	public String form(BodyPartItem bodyPartItem, Model model) {
		model.addAttribute("bodyPartItem", bodyPartItem);
		return "modules/base/bodyPartItemForm";
	}

	@RequiresPermissions("base:bodyPartItem:edit")
	@RequestMapping(value = "save")
	public String save(BodyPartItem bodyPartItem, BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {
		if (!isValid(result, bodyPartItem)){
			return form(bodyPartItem, model);
		}
		bodyPartItemService.save(bodyPartItem);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:"+Global.getAdminPath()+"/base/bodyPartItem/?repage";
	}
	@RequiresPermissions("base:bodyPartItem:edit")
	@RequestMapping(value = "delete")
	public String delete(BodyPartItem bodyPartItem, RedirectAttributes redirectAttributes) {
		bodyPartItemService.deleteEntity(bodyPartItem);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:"+Global.getAdminPath()+"/base/bodyPartItem/?repage";
	}

	@RequiresPermissions("base:bodyPartItem:edit")
	@RequestMapping(value = "/deletes", method = RequestMethod.POST)
	public @ResponseBody ResultMessage deletes(String[] ids,
			RedirectAttributes redirectAttributes) {
		bodyPartItemService.deleteBatchEntity(ids);
		return SUCCESS_MESSAGE;
	}
}