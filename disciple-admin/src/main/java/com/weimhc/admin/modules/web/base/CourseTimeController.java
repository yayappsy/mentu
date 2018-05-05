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

import com.weimhc.framework.web.ResultMessage;
import com.weimhc.admin.core.web.AdminBaseController;
import com.weimhc.modules.base.entity.CourseTime;
import com.weimhc.modules.base.service.CourseTimeService;

/**
 * 课程时间配置Controller
 * @author lc
 * @version 2017-06-12
 */
@Controller
@RequestMapping(value = "${adminPath}/base/courseTime")
public class CourseTimeController extends AdminBaseController {

	@Autowired
	private CourseTimeService courseTimeService;
	
	@ModelAttribute
	public CourseTime get(@RequestParam(required=false) String id) {
		CourseTime entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = courseTimeService.get(id);
		}
		if (entity == null){
			entity = new CourseTime();
		}
		return entity;
	}
	
	@RequiresPermissions("base:courseTime:view")
	@RequestMapping(value = {"list", ""})
	public String list(CourseTime courseTime, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CourseTime> page = courseTimeService.findPage(new Page<CourseTime>(request, response), courseTime); 
		model.addAttribute("page", page);
		model.addAttribute("courseTime", courseTime);
		return "modules/base/courseTimeList";
	}

	@RequiresPermissions("base:courseTime:view")
	@RequestMapping(value = "form")
	public String form(CourseTime courseTime, Model model) {
		model.addAttribute("courseTime", courseTime);
		return "modules/base/courseTimeForm";
	}

	@RequiresPermissions("base:courseTime:edit")
	@RequestMapping(value = "save")
	public String save(CourseTime courseTime, BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {
		if (!isValid(result, courseTime)){
			return form(courseTime, model);
		}
		courseTimeService.save(courseTime);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:"+Global.getAdminPath()+"/base/courseTime/?repage";
	}
	/**
	 * 批量修改排序
	 */
	@RequiresPermissions("base:courseTime:edit")
	@RequestMapping(value = "updateSort")
	public String updateSort(String[] ids, Integer[] sorts,
			RedirectAttributes redirectAttributes) {
		List<CourseTime> list = Lists.newArrayList();
		for (int i = 0; i < ids.length; i++) {
			CourseTime entity = new CourseTime(ids[i]);
			entity.setSort(sorts[i]);
			list.add(entity);
		}
		courseTimeService.updateSort(list);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:"+Global.getAdminPath()+"/base/courseTime/?repage";
	}
	
	@RequiresPermissions("base:courseTime:edit")
	@RequestMapping(value = "delete")
	public String delete(CourseTime courseTime, RedirectAttributes redirectAttributes) {
		courseTimeService.deleteEntity(courseTime);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:"+Global.getAdminPath()+"/base/courseTime/?repage";
	}

	@RequiresPermissions("base:courseTime:edit")
	@RequestMapping(value = "/deletes", method = RequestMethod.POST)
	public @ResponseBody ResultMessage deletes(String[] ids,
			RedirectAttributes redirectAttributes) {
		courseTimeService.deleteBatchEntity(ids);
		return SUCCESS_MESSAGE;
	}
}