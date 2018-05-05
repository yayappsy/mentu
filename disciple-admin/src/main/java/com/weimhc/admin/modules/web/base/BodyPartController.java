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
import com.weimhc.modules.base.entity.BodyPart;
import com.weimhc.modules.base.service.BodyPartService;

/**
 * 身体部位Controller
 * @author zsm
 * @version 2017-01-22
 */
@Controller
@RequestMapping(value = "${adminPath}/base/bodyPart")
public class BodyPartController extends AdminBaseController {

	@Autowired
	private BodyPartService bodyPartService;
	
	@ModelAttribute
	public BodyPart get(@RequestParam(required=false) String id) {
		BodyPart entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = bodyPartService.get(id);
		}
		if (entity == null){
			entity = new BodyPart();
		}
		return entity;
	}
	
	@RequiresPermissions("base:bodyPart:view")
	@RequestMapping(value = {"list", ""})
	public String list(BodyPart bodyPart, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<BodyPart> page = bodyPartService.findPage(new Page<BodyPart>(request, response), bodyPart); 
		model.addAttribute("page", page);
		model.addAttribute("bodyPart", bodyPart);
		return "modules/base/bodyPartList";
	}

	@RequiresPermissions("base:bodyPart:view")
	@RequestMapping(value = "form")
	public String form(BodyPart bodyPart, Model model) {
		model.addAttribute("bodyPart", bodyPart);
		return "modules/base/bodyPartForm";
	}

	@RequiresPermissions("base:bodyPart:edit")
	@RequestMapping(value = "save")
	public String save(BodyPart bodyPart, BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {
		if (!isValid(result, bodyPart)){
			return form(bodyPart, model);
		}
		bodyPartService.save(bodyPart);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:"+Global.getAdminPath()+"/base/bodyPart/?repage";
	}
	@RequiresPermissions("base:bodyPart:edit")
	@RequestMapping(value = "delete")
	public String delete(BodyPart bodyPart, RedirectAttributes redirectAttributes) {
		bodyPartService.deleteEntity(bodyPart);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:"+Global.getAdminPath()+"/base/bodyPart/?repage";
	}

	@RequiresPermissions("base:bodyPart:edit")
	@RequestMapping(value = "/deletes", method = RequestMethod.POST)
	public @ResponseBody ResultMessage deletes(String[] ids,
			RedirectAttributes redirectAttributes) {
		bodyPartService.deleteBatchEntity(ids);
		return SUCCESS_MESSAGE;
	}
}