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
import com.weimhc.modules.base.entity.OnlineCustomerService;
import com.weimhc.modules.base.entity.OnlineCustomerService.CustomerType;
import com.weimhc.modules.base.service.OnlineCustomerServiceService;

/**
 * 在线客服Controller
 * 
 * @author lc
 * @version 2017-07-03
 */
@Controller
@RequestMapping(value = "${adminPath}/base/onlineCustomerService")
public class OnlineCustomerServiceController extends AdminBaseController {

	@Autowired
	private OnlineCustomerServiceService onlineCustomerServiceService;

	@ModelAttribute
	public OnlineCustomerService get(
			@RequestParam(required = false) String id) {
		OnlineCustomerService entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = onlineCustomerServiceService.get(id);
		}
		if (entity == null) {
			entity = new OnlineCustomerService();
		}
		return entity;
	}

	@RequiresPermissions("base:onlineCustomerService:view")
	@RequestMapping(value = { "list", "" })
	public String list(OnlineCustomerService onlineCustomerService,
			HttpServletRequest request, HttpServletResponse response,
			Model model) {
		Page<OnlineCustomerService> page = onlineCustomerServiceService
				.findPage(new Page<OnlineCustomerService>(request, response),
						onlineCustomerService);
		model.addAttribute("page", page);
		model.addAttribute("onlineCustomerService", onlineCustomerService);
		return "modules/base/onlineCustomerServiceList";
	}

	@RequiresPermissions("base:onlineCustomerService:view")
	@RequestMapping(value = "form")
	public String form(OnlineCustomerService onlineCustomerService,
			Model model) {
		model.addAttribute("onlineCustomerService", onlineCustomerService);
		model.addAttribute("customerTypes", CustomerType.values());
		return "modules/base/onlineCustomerServiceForm";
	}

	@RequiresPermissions("base:onlineCustomerService:edit")
	@RequestMapping(value = "save")
	public String save(OnlineCustomerService onlineCustomerService,
			BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {
		onlineCustomerServiceService.save(onlineCustomerService);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:" + Global.getAdminPath()
				+ "/base/onlineCustomerService/?repage";
	}

	/**
	 * 批量修改排序
	 */
	@RequiresPermissions("base:onlineCustomerService:edit")
	@RequestMapping(value = "updateSort")
	public String updateSort(String[] ids, Integer[] sorts,
			RedirectAttributes redirectAttributes) {
		List<OnlineCustomerService> list = Lists.newArrayList();
		for (int i = 0; i < ids.length; i++) {
			OnlineCustomerService entity = new OnlineCustomerService(ids[i]);
			entity.setSort(sorts[i]);
			list.add(entity);
		}
		onlineCustomerServiceService.updateSort(list);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:" + Global.getAdminPath()
				+ "/base/onlineCustomerService/?repage";
	}

	@RequiresPermissions("base:onlineCustomerService:edit")
	@RequestMapping(value = "delete")
	public String delete(OnlineCustomerService onlineCustomerService,
			RedirectAttributes redirectAttributes) {
		onlineCustomerServiceService.deleteEntity(onlineCustomerService);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:" + Global.getAdminPath()
				+ "/base/onlineCustomerService/?repage";
	}

	@RequiresPermissions("base:onlineCustomerService:edit")
	@RequestMapping(value = "/deletes", method = RequestMethod.POST)
	public @ResponseBody ResultMessage deletes(String[] ids,
			RedirectAttributes redirectAttributes) {
		onlineCustomerServiceService.deleteBatchEntity(ids);
		return SUCCESS_MESSAGE;
	}
}