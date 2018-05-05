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
import com.weimhc.modules.base.entity.Education;
import com.weimhc.modules.base.service.EducationService;

/**
 * 学历Controller
 * 
 * @author lc
 * @version 2016-06-24
 */
@Controller
@RequestMapping(value = "${adminPath}/base/education")
public class EducationController extends AdminBaseController {

	@Autowired
	private EducationService educationService;

	@ModelAttribute
	public Education get(@RequestParam(required = false) String id) {
		Education entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = educationService.get(id);
		}
		if (entity == null) {
			entity = new Education();
		}
		return entity;
	}

	@RequiresPermissions("base:education:view")
	@RequestMapping(value = { "list", "" })
	public String list(Education education, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		Page<Education> page = educationService
				.findPage(new Page<Education>(request, response), education);
		model.addAttribute("page", page);
		return "modules/base/education/educationList";
	}

	@RequiresPermissions("base:education:view")
	@RequestMapping(value = "form")
	public String form(Education education, Model model) {
		model.addAttribute("education", education);
		return "modules/base/education/educationForm";
	}

	@RequiresPermissions("base:education:edit")
	@RequestMapping(value = "save")
	public String save(Education education, BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {
		if (!isValid(result, education)) {
			return form(education, model);
		}
		educationService.save(education);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:" + Global.getAdminPath() + "/base/education/?repage";
	}

	@RequiresPermissions("base:education:edit")
	@RequestMapping(value = "delete")
	public String delete(Education education,
			RedirectAttributes redirectAttributes) {
		educationService.deleteEntity(education);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:" + Global.getAdminPath() + "/base/education/?repage";
	}

	@RequiresPermissions("base:education:edit")
	@RequestMapping(value = "/deletes", method = RequestMethod.POST)
	public @ResponseBody ResultMessage deletes(String[] ids,
			RedirectAttributes redirectAttributes) {
		educationService.deleteBatchEntity(ids);
		return SUCCESS_MESSAGE;
	}
}