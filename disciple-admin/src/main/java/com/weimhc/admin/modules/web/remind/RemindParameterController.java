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

import com.google.common.collect.Lists;
import com.thinkgem.javamg.common.config.Global;
import com.thinkgem.javamg.common.persistence.Page;
import com.thinkgem.javamg.common.utils.StringUtils;
import com.weimhc.admin.core.web.AdminBaseController;
import com.weimhc.framework.web.ResultMessage;
import com.weimhc.modules.remind.entity.EntityName;
import com.weimhc.modules.remind.entity.RemindParameter;
import com.weimhc.modules.remind.service.RemindParameterService;

/**
 * 模板可选参数Controller
 * 
 * @author zsm
 * @version 2017-04-14
 */
@Controller
@RequestMapping(value = "${adminPath}/remind/remindParameter")
public class RemindParameterController extends AdminBaseController {

	@Autowired
	private RemindParameterService remindParameterService;

	@ModelAttribute
	public RemindParameter get(@RequestParam(required = false) String id) {
		RemindParameter entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = remindParameterService.get(id);
		}
		if (entity == null) {
			entity = new RemindParameter();
		}
		return entity;
	}

	@RequiresPermissions("remind:remindParameter:view")
	@RequestMapping(value = { "list", "" })
	public String list(RemindParameter remindParameter,
			HttpServletRequest request, HttpServletResponse response,
			Model model) {
		Page<RemindParameter> page = remindParameterService.findPage(
				new Page<RemindParameter>(request, response), remindParameter);
		model.addAttribute("page", page);
		model.addAttribute("remindParameter", remindParameter);
		model.addAttribute("entityNames", EntityName.values());
		return "modules/remind/remindParameterList";
	}

	@RequiresPermissions("remind:remindParameter:view")
	@RequestMapping(value = "form")
	public String form(RemindParameter remindParameter, Model model) {
		model.addAttribute("remindParameter", remindParameter);
		model.addAttribute("entityNames", EntityName.values());
		return "modules/remind/remindParameterForm";
	}

	@RequiresPermissions("remind:remindParameter:edit")
	@RequestMapping(value = "save")
	public String save(RemindParameter remindParameter, BindingResult result,
			Model model, RedirectAttributes redirectAttributes) {
		if (!isValid(result, remindParameter)) {
			return form(remindParameter, model);
		}
		remindParameterService.save(remindParameter);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:" + Global.getAdminPath()
				+ "/remind/remindParameter/?repage";
	}

	/**
	 * 批量修改排序
	 */
	@RequiresPermissions("remind:remindParameter:edit")
	@RequestMapping(value = "updateSort")
	public String updateSort(String[] ids, Integer[] sorts,
			RedirectAttributes redirectAttributes) {
		List<RemindParameter> list = Lists.newArrayList();
		for (int i = 0; i < ids.length; i++) {
			RemindParameter entity = new RemindParameter(ids[i]);
			entity.setSort(sorts[i]);
			list.add(entity);
		}
		remindParameterService.updateSort(list);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:" + Global.getAdminPath()
				+ "/remind/remindParameter/?repage";
	}

	@RequiresPermissions("remind:remindParameter:edit")
	@RequestMapping(value = "delete")
	public String delete(RemindParameter remindParameter,
			RedirectAttributes redirectAttributes) {
		remindParameterService.deleteEntity(remindParameter);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:" + Global.getAdminPath()
				+ "/remind/remindParameter/?repage";
	}

	@RequiresPermissions("remind:remindParameter:edit")
	@RequestMapping(value = "/deletes", method = RequestMethod.POST)
	public @ResponseBody ResultMessage deletes(String[] ids,
			RedirectAttributes redirectAttributes) {
		remindParameterService.deleteBatchEntity(ids);
		return SUCCESS_MESSAGE;
	}
}