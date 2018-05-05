/**
 * 
 */
package com.weimhc.admin.modules.web.order;

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
import com.weimhc.modules.order.entity.Order;
import com.weimhc.modules.order.service.OrderService;

/**
 * 订单Controller
 * 
 * @author lc
 * @version 2017-01-03
 */
@Controller
@RequestMapping(value = "${adminPath}/order/order")
public class OrderController extends AdminBaseController {

	@Autowired
	private OrderService orderService;

	@ModelAttribute
	public Order get(@RequestParam(required = false) String id) {
		Order entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = orderService.get(id);
		}
		if (entity == null) {
			entity = new Order();
		}
		return entity;
	}

	@RequiresPermissions("order:order:view")
	@RequestMapping(value = { "list", "" })
	public String list(Order order, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		Page<Order> page = orderService
				.findPage(new Page<Order>(request, response), order);
		model.addAttribute("page", page);
		model.addAttribute("order", order);
		return "modules/order/orderList";
	}

	@RequiresPermissions("order:order:view")
	@RequestMapping(value = "form")
	public String form(Order order, Model model) {
		model.addAttribute("order", order);
		return "modules/order/orderForm";
	}

	@RequiresPermissions("order:order:edit")
	@RequestMapping(value = "save")
	public String save(Order order, BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {
		if (!isValid(result, order)) {
			return form(order, model);
		}
		orderService.save(order);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:" + Global.getAdminPath() + "/order/order/?repage";
	}

	@RequiresPermissions("order:order:edit")
	@RequestMapping(value = "delete")
	public String delete(Order order, RedirectAttributes redirectAttributes) {
		orderService.deleteEntity(order);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:" + Global.getAdminPath() + "/order/order/?repage";
	}

	@RequiresPermissions("order:order:edit")
	@RequestMapping(value = "/deletes", method = RequestMethod.POST)
	public @ResponseBody ResultMessage deletes(String[] ids,
			RedirectAttributes redirectAttributes) {
		orderService.deleteBatchEntity(ids);
		return SUCCESS_MESSAGE;
	}
}