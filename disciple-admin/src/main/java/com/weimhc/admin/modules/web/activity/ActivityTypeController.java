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
import com.weimhc.framework.web.ResultMessage;
import com.weimhc.modules.activity.entity.ActivityType;
import com.weimhc.modules.activity.service.ActivityTypeService;

/**
 * 活动类型Controller
 * 
 * @author zsm
 * @version 2017-04-24
 */
@Controller
@RequestMapping(value = "${adminPath}/activity/activityType")
public class ActivityTypeController extends AdminBaseController {

	@Autowired
	private ActivityTypeService activityTypeService;

	@ModelAttribute
	public ActivityType get(@RequestParam(required = false) String id) {
		ActivityType entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = activityTypeService.get(id);
		}
		if (entity == null) {
			entity = new ActivityType();
		}
		return entity;
	}

	@RequiresPermissions("activity:activityType:view")
	@RequestMapping(value = { "list", "" })
	public String list(ActivityType activityType, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		Page<ActivityType> page = activityTypeService.findPage(
				new Page<ActivityType>(request, response), activityType);
		model.addAttribute("page", page);
		model.addAttribute("activityType", activityType);
		return "modules/activity/activityTypeList";
	}

	@RequiresPermissions("activity:activityType:view")
	@RequestMapping(value = "form")
	public String form(ActivityType activityType, Model model) {
		model.addAttribute("activityType", activityType);
		return "modules/activity/activityTypeForm";
	}

	@RequiresPermissions("activity:activityType:edit")
	@RequestMapping(value = "save")
	public String save(ActivityType activityType, BindingResult result,
			Model model, RedirectAttributes redirectAttributes) {
		if (!isValid(result, activityType)) {
			return form(activityType, model);
		}
		activityTypeService.save(activityType);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:" + Global.getAdminPath()
				+ "/activity/activityType/?repage";
	}

	/**
	 * 批量修改排序
	 */
	@RequiresPermissions("activity:activityType:edit")
	@RequestMapping(value = "updateSort")
	public String updateSort(String[] ids, Integer[] sorts,
			RedirectAttributes redirectAttributes) {
		List<ActivityType> list = Lists.newArrayList();
		for (int i = 0; i < ids.length; i++) {
			ActivityType entity = new ActivityType(ids[i]);
			entity.setSort(sorts[i]);
			list.add(entity);
		}
		activityTypeService.updateSort(list);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:" + Global.getAdminPath()
				+ "/activity/activityType/?repage";
	}

	@RequiresPermissions("activity:activityType:edit")
	@RequestMapping(value = "delete")
	public String delete(ActivityType activityType,
			RedirectAttributes redirectAttributes) {
		activityTypeService.deleteEntity(activityType);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:" + Global.getAdminPath()
				+ "/activity/activityType/?repage";
	}

	@RequiresPermissions("activity:activityType:edit")
	@RequestMapping(value = "/deletes", method = RequestMethod.POST)
	public @ResponseBody ResultMessage deletes(String[] ids,
			RedirectAttributes redirectAttributes) {
		activityTypeService.deleteBatchEntity(ids);
		return SUCCESS_MESSAGE;
	}
}