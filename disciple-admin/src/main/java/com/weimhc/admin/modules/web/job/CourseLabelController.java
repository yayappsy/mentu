/**
 * 
 */
package com.weimhc.admin.modules.web.job;

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
import com.weimhc.modules.job.entity.CourseLabel;
import com.weimhc.modules.job.service.CourseLabelService;

/**
 * 培训课程标签Controller
 * @author cwl
 * @version 2018-04-01
 */
@Controller
@RequestMapping(value = "${adminPath}/job/courseLabel")
public class CourseLabelController extends AdminBaseController {

	@Autowired
	private CourseLabelService courseLabelService;
	
	@ModelAttribute
	public CourseLabel get(@RequestParam(required=false) String id) {
		CourseLabel entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = courseLabelService.get(id);
		}
		if (entity == null){
			entity = new CourseLabel();
		}
		return entity;
	}
	
	@RequiresPermissions("job:courseLabel:view")
	@RequestMapping(value = {"list", ""})
	public String list(CourseLabel courseLabel, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<CourseLabel> page = courseLabelService.findPage(new Page<CourseLabel>(request, response), courseLabel); 
		model.addAttribute("page", page);
		model.addAttribute("courseLabel", courseLabel);
		return "modules/job/courseLabelList";
	}

	@RequiresPermissions("job:courseLabel:view")
	@RequestMapping(value = "form")
	public String form(CourseLabel courseLabel, Model model) {
		model.addAttribute("courseLabel", courseLabel);
		return "modules/job/courseLabelForm";
	}

	@RequiresPermissions("job:courseLabel:edit")
	@RequestMapping(value = "save")
	public String save(CourseLabel courseLabel, BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {
		if (!isValid(result, courseLabel)){
			return form(courseLabel, model);
		}
		courseLabelService.save(courseLabel);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:"+Global.getAdminPath()+"/job/courseLabel/?repage";
	}
	/**
	 * 批量修改排序
	 */
	@RequiresPermissions("job:courseLabel:edit")
	@RequestMapping(value = "updateSort")
	public String updateSort(String[] ids, Integer[] sorts,
			RedirectAttributes redirectAttributes) {
		List<CourseLabel> list = Lists.newArrayList();
		for (int i = 0; i < ids.length; i++) {
			CourseLabel entity = new CourseLabel(ids[i]);
			entity.setSort(sorts[i]);
			list.add(entity);
		}
		courseLabelService.updateSort(list);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:"+Global.getAdminPath()+"/job/courseLabel/?repage";
	}
	
	@RequiresPermissions("job:courseLabel:delete")
	@RequestMapping(value = "delete")
	public String delete(CourseLabel courseLabel, RedirectAttributes redirectAttributes) {
		courseLabelService.deleteEntity(courseLabel);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:"+Global.getAdminPath()+"/job/courseLabel/?repage";
	}

	@RequiresPermissions("job:courseLabel:delete")
	@RequestMapping(value = "/deletes", method = RequestMethod.POST)
	public @ResponseBody ResultMessage deletes(String[] ids,
			RedirectAttributes redirectAttributes) {
		courseLabelService.deleteBatchEntity(ids);
		return SUCCESS_MESSAGE;
	}
}