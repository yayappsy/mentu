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
import com.weimhc.modules.activity.entity.ActivityParticipant;
import com.weimhc.modules.activity.service.ActivityParticipantService;

/**
 * 活动申请人Controller
 * 
 * @author zsm
 * @version 2017-04-24
 */
@Controller
@RequestMapping(value = "${adminPath}/activity/activityParticipant")
public class ActivityParticipantController extends AdminBaseController {

	@Autowired
	private ActivityParticipantService activityParticipantService;

	@ModelAttribute
	public ActivityParticipant get(@RequestParam(required = false) String id) {
		ActivityParticipant entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = activityParticipantService.get(id);
		}
		if (entity == null) {
			entity = new ActivityParticipant();
		}
		return entity;
	}

	@RequiresPermissions("activity:activityParticipant:view")
	@RequestMapping(value = { "list", "" })
	public String list(ActivityParticipant activityParticipant,
			HttpServletRequest request, HttpServletResponse response,
			Model model) {
		Page<ActivityParticipant> page = activityParticipantService.findPage(
				new Page<ActivityParticipant>(request, response),
				activityParticipant);
		model.addAttribute("page", page);
		model.addAttribute("activityParticipant", activityParticipant);
		return "modules/activity/activityParticipantList";
	}

	@RequiresPermissions("activity:activityParticipant:view")
	@RequestMapping(value = "form")
	public String form(ActivityParticipant activityParticipant, Model model) {
		model.addAttribute("activityParticipant", activityParticipant);
		return "modules/activity/activityParticipantForm";
	}

	@RequiresPermissions("activity:activityParticipant:edit")
	@RequestMapping(value = "save")
	public String save(ActivityParticipant activityParticipant,
			BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {
		if (!isValid(result, activityParticipant)) {
			return form(activityParticipant, model);
		}
		activityParticipantService.save(activityParticipant);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:" + Global.getAdminPath()
				+ "/activity/activityParticipant/?repage";
	}

	@RequiresPermissions("activity:activityParticipant:edit")
	@RequestMapping(value = "updateStatus")
	public String updateStatus(ActivityParticipant activityParticipant,
			BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {
		activityParticipantService.updateStatus(activityParticipant);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:" + Global.getAdminPath()
				+ "/activity/activityParticipant/?repage";
	}

	/**
	 * 批量修改排序
	 */
	@RequiresPermissions("activity:activityParticipant:edit")
	@RequestMapping(value = "updateSort")
	public String updateSort(String[] ids, Integer[] sorts,
			RedirectAttributes redirectAttributes) {
		List<ActivityParticipant> list = Lists.newArrayList();
		for (int i = 0; i < ids.length; i++) {
			ActivityParticipant entity = new ActivityParticipant(ids[i]);
			entity.setSort(sorts[i]);
			list.add(entity);
		}
		activityParticipantService.updateSort(list);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:" + Global.getAdminPath()
				+ "/activity/activityParticipant/?repage";
	}

	@RequiresPermissions("activity:activityParticipant:edit")
	@RequestMapping(value = "delete")
	public String delete(ActivityParticipant activityParticipant,
			RedirectAttributes redirectAttributes) {
		activityParticipantService.deleteEntity(activityParticipant);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:" + Global.getAdminPath()
				+ "/activity/activityParticipant/?repage";
	}

	@RequiresPermissions("activity:activityParticipant:edit")
	@RequestMapping(value = "/deletes", method = RequestMethod.POST)
	public @ResponseBody ResultMessage deletes(String[] ids,
			RedirectAttributes redirectAttributes) {
		activityParticipantService.deleteBatchEntity(ids);
		return SUCCESS_MESSAGE;
	}
}