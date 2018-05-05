/**
 * 
 */
package com.weimhc.admin.modules.web.remind;

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

import com.weimhc.framework.web.ResultMessage;
import com.weimhc.admin.core.web.AdminBaseController;
import com.weimhc.modules.remind.entity.RemindWay;
import com.weimhc.modules.remind.service.RemindWayService;

/**
 * 提醒方式Controller
 * @author zsm
 * @version 2017-03-23
 */
@Controller
@RequestMapping(value = "${adminPath}/remind/remindWay")
public class RemindWayController extends AdminBaseController {

	@Autowired
	private RemindWayService remindWayService;
	
	@ModelAttribute
	public RemindWay get(@RequestParam(required=false) String id) {
		RemindWay entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = remindWayService.get(id);
		}
		if (entity == null){
			entity = new RemindWay();
		}
		return entity;
	}
	
	@RequiresPermissions("remind:remindWay:view")
	@RequestMapping(value = {"list", ""})
	public String list(RemindWay remindWay, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<RemindWay> page = remindWayService.findPage(new Page<RemindWay>(request, response), remindWay); 
		model.addAttribute("page", page);
		model.addAttribute("remindWay", remindWay);
		return "modules/remind/remindWayList";
	}

	@RequiresPermissions("remind:remindWay:view")
	@RequestMapping(value = "form")
	public String form(RemindWay remindWay, Model model) {
		model.addAttribute("remindWay", remindWay);
		return "modules/remind/remindWayForm";
	}

	@RequiresPermissions("remind:remindWay:edit")
	@RequestMapping(value = "save")
	public String save(RemindWay remindWay, BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {
		if (!isValid(result, remindWay)){
			return form(remindWay, model);
		}
		remindWayService.save(remindWay);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:"+Global.getAdminPath()+"/remind/remindWay/?repage";
	}
	@RequiresPermissions("remind:remindWay:edit")
	@RequestMapping(value = "delete")
	public String delete(RemindWay remindWay, RedirectAttributes redirectAttributes) {
		remindWayService.deleteEntity(remindWay);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:"+Global.getAdminPath()+"/remind/remindWay/?repage";
	}

	@RequiresPermissions("remind:remindWay:edit")
	@RequestMapping(value = "/deletes", method = RequestMethod.POST)
	public @ResponseBody ResultMessage deletes(String[] ids,
			RedirectAttributes redirectAttributes) {
		remindWayService.deleteBatchEntity(ids);
		return SUCCESS_MESSAGE;
	}
}