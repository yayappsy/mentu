/**
 * 
 */
package com.weimhc.admin.modules.web.remind;

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

import com.thinkgem.javamg.common.config.Global;
import com.thinkgem.javamg.common.persistence.Page;
import com.thinkgem.javamg.common.utils.StringUtils;
import com.weimhc.admin.core.web.AdminBaseController;
import com.weimhc.framework.web.ResultMessage;
import com.weimhc.modules.remind.entity.BusinessAction;
import com.weimhc.modules.remind.entity.Remind;
import com.weimhc.modules.remind.entity.RemindWay;
import com.weimhc.modules.remind.entity.TargetType;
import com.weimhc.modules.remind.service.RemindParameterService;
import com.weimhc.modules.remind.service.RemindService;
import com.weimhc.modules.remind.service.RemindTemplateService;
import com.weimhc.modules.remind.service.RemindWayService;

/**
 * 业务提醒Controller
 * 
 * @author zsm
 * @version 2017-03-23
 */
@Controller
@RequestMapping(value = "${adminPath}/remind/remind")
public class RemindController extends AdminBaseController {

	@Autowired
	private RemindService remindService;

	@Autowired
	private RemindTemplateService remindTemplateService;

	@Autowired
	private RemindParameterService remindParameterService;

	@Autowired
	private RemindWayService remindWayService;

	@ModelAttribute
	public Remind get(@RequestParam(required = false) String id) {
		Remind entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = remindService.get(id);
		}
		if (entity == null) {
			entity = new Remind();
		}
		return entity;
	}

	@RequiresPermissions("remind:remind:view")
	@RequestMapping(value = "index")
	public String index(Remind remind, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		List<Remind> list = remindService.findAllList(new Remind());
		model.addAttribute("list", list);
		model.addAttribute("remind", remind);
		return "modules/remind/remindIndex";
	}

	@RequiresPermissions("remind:remind:edit")
	@RequestMapping(value = "saveIndex")
	public String saveIndex(Remind remind, BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {
		remindService.saveIndex(remind);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:" + Global.getAdminPath() + "/remind/remind/?repage";
	}

	@RequiresPermissions("remind:remind:view")
	@RequestMapping(value = { "list", "" })
	public String list(Remind remind, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		Page<Remind> page = remindService
				.findPage(new Page<Remind>(request, response), remind);
		model.addAttribute("page", page);
		model.addAttribute("remind", remind);
		model.addAttribute("targetTypes", TargetType.values());
		model.addAttribute("businessActions", BusinessAction.values());
		return "modules/remind/remindList";
	}

	@RequiresPermissions("remind:remind:view")
	@RequestMapping(value = "form")
	public String form(Remind remind, Model model) {
		model.addAttribute("remindWays",
				remindWayService.findAllList(new RemindWay()));
		model.addAttribute("targetTypes", TargetType.values());
		model.addAttribute("businessActions", BusinessAction.values());
		model.addAttribute("remind", remind);
		return "modules/remind/remindForm";
	}

	@RequiresPermissions("remind:remind:edit")
	@RequestMapping(value = "save")
	public String save(Remind remind, BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {
		remindService.save(remind);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:" + Global.getAdminPath() + "/remind/remind/?repage";
	}

	@RequiresPermissions("remind:remind:view")
	@RequestMapping(value = { "setList" })
	public String setList(Remind remind, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		Page<Remind> page = remindService
				.findPage(new Page<Remind>(request, response), remind);
		model.addAttribute("page", page);
		model.addAttribute("remind", remind);
		model.addAttribute("targetTypes", TargetType.values());
		model.addAttribute("businessActions", BusinessAction.values());
		return "modules/remind/remindSetList";
	}

	@RequiresPermissions("remind:remind:view")
	@RequestMapping(value = "setForm")
	public String setForm(Remind remind, Model model) {
		model.addAttribute("remindWays",
				remindWayService.findAllList(new RemindWay()));
		model.addAttribute("targetTypes", TargetType.values());
		model.addAttribute("businessActions", BusinessAction.values());
		model.addAttribute("remind", remind);
		return "modules/remind/remindSetForm";
	}

	@RequiresPermissions("remind:remind:edit")
	@RequestMapping(value = "setSave")
	public String setSave(Remind remind, BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {
		remindService.save(remind);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:" + Global.getAdminPath()
				+ "/remind/remind/setList?repage";
	}

	@RequiresPermissions("remind:remind:edit")
	@RequestMapping(value = "delete")
	public String delete(Remind remind, RedirectAttributes redirectAttributes) {
		remindService.deleteEntity(remind);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:" + Global.getAdminPath() + "/remind/remind/?repage";
	}

	@RequiresPermissions("remind:remind:edit")
	@RequestMapping(value = "/deletes", method = RequestMethod.POST)
	public @ResponseBody ResultMessage deletes(String[] ids,
			RedirectAttributes redirectAttributes) {
		remindService.deleteBatchEntity(ids);
		return SUCCESS_MESSAGE;
	}
}