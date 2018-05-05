/**
 * 
 */
package com.weimhc.admin.modules.web.recruit;

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
import com.weimhc.modules.base.entity.Department;
import com.weimhc.modules.base.service.DepartmentService;
import com.weimhc.modules.base.utils.EducationUtils;
import com.weimhc.modules.base.utils.WorkingYearsUtils;
import com.weimhc.modules.recruit.entity.Recruitment;
import com.weimhc.modules.recruit.entity.RecruitmentType;
import com.weimhc.modules.recruit.service.RecruitmentService;
import com.weimhc.modules.recruit.service.RecruitmentTypeService;

/**
 * 招聘Controller
 * 
 * @author zsm
 * @version 2017-03-27
 */
@Controller
@RequestMapping(value = "${adminPath}/recruit/recruitment")
public class RecruitmentController extends AdminBaseController {

	@Autowired
	private RecruitmentService recruitmentService;
	@Autowired
	private DepartmentService departmentService;
	@Autowired
	private RecruitmentTypeService recruitmentTypeService;

	@ModelAttribute
	public Recruitment get(@RequestParam(required = false) String id) {
		Recruitment entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = recruitmentService.get(id);
		}
		if (entity == null) {
			entity = new Recruitment();
		}
		return entity;
	}

	@RequiresPermissions("recruit:recruitment:view")
	@RequestMapping(value = { "list", "" })
	public String list(Recruitment recruitment, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		Page<Recruitment> page = recruitmentService.findPage(
				new Page<Recruitment>(request, response), recruitment);
		model.addAttribute("page", page);
		model.addAttribute("recruitment", recruitment);
		return "modules/recruit/recruitmentList";
	}

	@RequiresPermissions("recruit:recruitment:view")
	@RequestMapping(value = "form")
	public String form(Recruitment recruitment, Model model) {
		model.addAttribute("departments",
				departmentService.findAllList(new Department()));
		model.addAttribute("educations", EducationUtils.findAll());
		model.addAttribute("workingYearsList", WorkingYearsUtils.findAll());
		model.addAttribute("recruitmentTypes",
				recruitmentTypeService.findAllList(new RecruitmentType()));
		if (StringUtils.isBlank(recruitment.getId())) {
			recruitment.setSalaryRangeId("面议");
		}
		model.addAttribute("recruitment", recruitment);
		return "modules/recruit/recruitmentForm";
	}

	@RequiresPermissions("recruit:recruitment:edit")
	@RequestMapping(value = "save")
	public String save(Recruitment recruitment, BindingResult result,
			Model model, RedirectAttributes redirectAttributes) {
		if (!isValid(result, recruitment)) {
			return form(recruitment, model);
		}
		recruitmentService.save(recruitment);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:" + Global.getAdminPath()
				+ "/recruit/recruitment/?repage";
	}

	@RequiresPermissions("recruit:recruitment:edit")
	@RequestMapping(value = "delete")
	public String delete(Recruitment recruitment,
			RedirectAttributes redirectAttributes) {
		recruitmentService.deleteEntity(recruitment);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:" + Global.getAdminPath()
				+ "/recruit/recruitment/?repage";
	}

	@RequiresPermissions("recruit:recruitment:edit")
	@RequestMapping(value = "/deletes", method = RequestMethod.POST)
	public @ResponseBody ResultMessage deletes(String[] ids,
			RedirectAttributes redirectAttributes) {
		recruitmentService.deleteBatchEntity(ids);
		return SUCCESS_MESSAGE;
	}
}