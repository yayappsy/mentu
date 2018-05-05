/**
 * 
 */
package com.weimhc.admin.modules.web.activity;

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
import com.weimhc.framework.utils.UploadFolder;
import com.weimhc.framework.web.ResultMessage;
import com.weimhc.modules.activity.entity.Activity;
import com.weimhc.modules.activity.service.ActivityService;
import com.weimhc.modules.activity.utils.ActivityUtils;

/**
 * 活动Controller
 * 
 * @author zsm
 * @version 2017-04-24
 */
@Controller
@RequestMapping(value = "${adminPath}/activity/activity")
public class ActivityController extends AdminBaseController {

	@Autowired
	private ActivityService activityService;

	@ModelAttribute
	public Activity get(@RequestParam(required = false) String id) {
		Activity entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = activityService.get(id);
		}
		if (entity == null) {
			entity = new Activity();
		}
		return entity;
	}

	@RequiresPermissions("activity:activity:view")
	@RequestMapping(value = { "list", "" })
	public String list(Activity activity, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		Page<Activity> page = activityService
				.findPage(new Page<Activity>(request, response), activity);
		model.addAttribute("page", page);
		model.addAttribute("activity", activity);
		dealWithSearchType(request, model);
		return "modules/activity/activityList";
	}

	@RequiresPermissions("activity:activity:view")
	@RequestMapping(value = "form")
	public String form(Activity activity, Model model) {
		model.addAttribute("activity", activity);
		model.addAttribute("uploadFolder", UploadFolder.activity);
		model.addAttribute("activityTypes",
				ActivityUtils.findActivityTypeAll());
		return "modules/activity/activityForm";
	}

	@RequiresPermissions("activity:activity:edit")
	@RequestMapping(value = "save")
	public String save(Activity activity, BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {
		if (!isValid(result, activity)) {
			return form(activity, model);
		}
		activityService.save(activity);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:" + Global.getAdminPath()
				+ "/activity/activity/?repage";
	}

	/**
	 * 批量修改排序
	 */
	@RequiresPermissions("activity:activity:edit")
	@RequestMapping(value = "updateSort")
	public String updateSort(String[] ids, Integer[] sorts,
			RedirectAttributes redirectAttributes) {
		List<Activity> list = Lists.newArrayList();
		for (int i = 0; i < ids.length; i++) {
			Activity entity = new Activity(ids[i]);
			entity.setSort(sorts[i]);
			list.add(entity);
		}
		activityService.updateSort(list);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:" + Global.getAdminPath()
				+ "/activity/activity/?repage";
	}

	@RequiresPermissions("activity:activity:edit")
	@RequestMapping(value = "delete")
	public String delete(Activity activity,
			RedirectAttributes redirectAttributes) {
		activityService.deleteEntity(activity);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:" + Global.getAdminPath()
				+ "/activity/activity/?repage";
	}

	@RequiresPermissions("activity:activity:edit")
	@RequestMapping(value = "/deletes", method = RequestMethod.POST)
	public @ResponseBody ResultMessage deletes(String[] ids,
			RedirectAttributes redirectAttributes) {
		activityService.deleteBatchEntity(ids);
		return SUCCESS_MESSAGE;
	}
}