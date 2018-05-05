/**
 * 
 */
package com.weimhc.admin.modules.web.recruit;

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

import com.google.common.collect.Lists;
import com.thinkgem.javamg.common.config.Global;
import com.thinkgem.javamg.common.persistence.Page;
import com.thinkgem.javamg.common.utils.StringUtils;
import com.weimhc.admin.core.web.AdminBaseController;
import com.weimhc.framework.web.ResultMessage;
import com.weimhc.modules.recruit.entity.RecruitmentType;
import com.weimhc.modules.recruit.service.RecruitmentTypeService;

/**
 * 职位类型Controller
 * 
 * @author lc
 * @version 2017-04-06
 */
@Controller
@RequestMapping(value = "${adminPath}/recruit/recruitmentType")
public class RecruitmentTypeController extends AdminBaseController {

	@Autowired
	private RecruitmentTypeService recruitmentTypeService;

	@ModelAttribute
	public RecruitmentType get(@RequestParam(required = false) String id) {
		RecruitmentType entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = recruitmentTypeService.get(id);
		}
		if (entity == null) {
			entity = new RecruitmentType();
		}
		return entity;
	}

	@RequiresPermissions("recruit:recruitmentType:view")
	@RequestMapping(value = { "list", "" })
	public String list(RecruitmentType recruitmentType,
			HttpServletRequest request, HttpServletResponse response,
			Model model) {
		Page<RecruitmentType> page = recruitmentTypeService.findPage(
				new Page<RecruitmentType>(request, response), recruitmentType);
		model.addAttribute("page", page);
		model.addAttribute("recruitmentType", recruitmentType);
		return "modules/recruit/recruitmentTypeList";
	}

	@RequiresPermissions("recruit:recruitmentType:view")
	@RequestMapping(value = "form")
	public String form(RecruitmentType recruitmentType, Model model) {
		model.addAttribute("recruitmentType", recruitmentType);
		return "modules/recruit/recruitmentTypeForm";
	}

	@RequiresPermissions("recruit:recruitmentType:edit")
	@RequestMapping(value = "save")
	public String save(RecruitmentType recruitmentType, BindingResult result,
			Model model, RedirectAttributes redirectAttributes) {
		if (!isValid(result, recruitmentType)) {
			return form(recruitmentType, model);
		}
		recruitmentTypeService.save(recruitmentType);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:" + Global.getAdminPath()
				+ "/recruit/recruitmentType/?repage";
	}

	/**
	 * 批量修改排序
	 */
	@RequiresPermissions("recruit:recruitmentType:edit")
	@RequestMapping(value = "updateSort")
	public String updateSort(String[] ids, Integer[] sorts,
			RedirectAttributes redirectAttributes) {
		List<RecruitmentType> list = Lists.newArrayList();
		for (int i = 0; i < ids.length; i++) {
			RecruitmentType entity = new RecruitmentType(ids[i]);
			entity.setSort(sorts[i]);
			list.add(entity);
		}
		recruitmentTypeService.updateSort(list);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:" + Global.getAdminPath()
				+ "/recruit/recruitmentType/?repage";
	}

	@RequiresPermissions("recruit:recruitmentType:edit")
	@RequestMapping(value = "delete")
	public String delete(RecruitmentType recruitmentType,
			RedirectAttributes redirectAttributes) {
		recruitmentTypeService.deleteEntity(recruitmentType);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:" + Global.getAdminPath()
				+ "/recruit/recruitmentType/?repage";
	}

	@RequiresPermissions("recruit:recruitmentType:edit")
	@RequestMapping(value = "/deletes", method = RequestMethod.POST)
	public @ResponseBody ResultMessage deletes(String[] ids,
			RedirectAttributes redirectAttributes) {
		recruitmentTypeService.deleteBatchEntity(ids);
		return SUCCESS_MESSAGE;
	}
}