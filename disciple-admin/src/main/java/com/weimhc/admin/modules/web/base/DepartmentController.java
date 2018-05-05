/**
 * 
 */
package com.weimhc.admin.modules.web.base;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.weimhc.modules.base.entity.Department;
import com.weimhc.modules.base.service.DepartmentService;

/**
 * 工作部门Controller
 * 
 * @author lc
 * @version 2017-04-06
 */
@Controller
@RequestMapping(value = "${adminPath}/base/department")
public class DepartmentController extends AdminBaseController {

	@Autowired
	private DepartmentService departmentService;

	@ModelAttribute
	public Department get(@RequestParam(required = false) String id) {
		Department entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = departmentService.get(id);
		}
		if (entity == null) {
			entity = new Department();
		}
		return entity;
	}

	@RequestMapping(value = { "list", "" })
	public String list(Department department, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		Page<Department> page = departmentService
				.findPage(new Page<Department>(request, response), department);
		model.addAttribute("page", page);
		model.addAttribute("department", department);
		return "modules/base/department/departmentList";
	}

	@RequestMapping(value = "form")
	public String form(Department department, Model model) {
		model.addAttribute("department", department);
		return "modules/base/department/departmentForm";
	}

	@RequestMapping(value = "save")
	public String save(Department department, BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {
		if (!isValid(result, department)) {
			return form(department, model);
		}
		departmentService.save(department);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:" + Global.getAdminPath() + "/base/department/?repage";
	}

	/**
	 * 批量修改排序
	 */

	@RequestMapping(value = "updateSort")
	public String updateSort(String[] ids, Integer[] sorts,
			RedirectAttributes redirectAttributes) {
		List<Department> list = Lists.newArrayList();
		for (int i = 0; i < ids.length; i++) {
			Department entity = new Department(ids[i]);
			entity.setSort(sorts[i]);
			list.add(entity);
		}
		departmentService.updateSort(list);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:" + Global.getAdminPath() + "/base/department/?repage";
	}

	@RequestMapping(value = "delete")
	public String delete(Department department,
			RedirectAttributes redirectAttributes) {
		departmentService.deleteEntity(department);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:" + Global.getAdminPath() + "/base/department/?repage";
	}

	@RequestMapping(value = "/deletes", method = RequestMethod.POST)
	public @ResponseBody ResultMessage deletes(String[] ids,
			RedirectAttributes redirectAttributes) {
		departmentService.deleteBatchEntity(ids);
		return SUCCESS_MESSAGE;
	}
}