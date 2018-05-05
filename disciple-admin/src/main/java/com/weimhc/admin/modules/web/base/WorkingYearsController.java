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
import com.weimhc.modules.base.entity.WorkingYears;
import com.weimhc.modules.base.service.WorkingYearsService;

/**
 * 工作年限Controller
 * 
 * @author lc
 * @version 2017-04-06
 */
@Controller
@RequestMapping(value = "${adminPath}/base/workingYears")
public class WorkingYearsController extends AdminBaseController {

	@Autowired
	private WorkingYearsService workingYearsService;

	@ModelAttribute
	public WorkingYears get(@RequestParam(required = false) String id) {
		WorkingYears entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = workingYearsService.get(id);
		}
		if (entity == null) {
			entity = new WorkingYears();
		}
		return entity;
	}

	@RequiresPermissions("base:workingYears:view")
	@RequestMapping(value = { "list", "" })
	public String list(WorkingYears workingYears, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		Page<WorkingYears> page = workingYearsService.findPage(
				new Page<WorkingYears>(request, response), workingYears);
		model.addAttribute("page", page);
		model.addAttribute("workingYears", workingYears);
		return "modules/base/workingYearsList";
	}

	@RequiresPermissions("base:workingYears:view")
	@RequestMapping(value = "form")
	public String form(WorkingYears workingYears, Model model) {
		model.addAttribute("workingYears", workingYears);
		return "modules/base/workingYearsForm";
	}

	@RequiresPermissions("base:workingYears:edit")
	@RequestMapping(value = "save")
	public String save(WorkingYears workingYears, BindingResult result,
			Model model, RedirectAttributes redirectAttributes) {
		if (!isValid(result, workingYears)) {
			return form(workingYears, model);
		}
		workingYearsService.save(workingYears);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:" + Global.getAdminPath()
				+ "/base/workingYears/?repage";
	}

	/**
	 * 批量修改排序
	 */
	@RequiresPermissions("base:workingYears:edit")
	@RequestMapping(value = "updateSort")
	public String updateSort(String[] ids, Integer[] sorts,
			RedirectAttributes redirectAttributes) {
		List<WorkingYears> list = Lists.newArrayList();
		for (int i = 0; i < ids.length; i++) {
			WorkingYears entity = new WorkingYears(ids[i]);
			entity.setSort(sorts[i]);
			list.add(entity);
		}
		workingYearsService.updateSort(list);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:" + Global.getAdminPath()
				+ "/base/workingYears/?repage";
	}

	@RequiresPermissions("base:workingYears:edit")
	@RequestMapping(value = "delete")
	public String delete(WorkingYears workingYears,
			RedirectAttributes redirectAttributes) {
		workingYearsService.deleteEntity(workingYears);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:" + Global.getAdminPath()
				+ "/base/workingYears/?repage";
	}

	@RequiresPermissions("base:workingYears:edit")
	@RequestMapping(value = "/deletes", method = RequestMethod.POST)
	public @ResponseBody ResultMessage deletes(String[] ids,
			RedirectAttributes redirectAttributes) {
		workingYearsService.deleteBatchEntity(ids);
		return SUCCESS_MESSAGE;
	}
}