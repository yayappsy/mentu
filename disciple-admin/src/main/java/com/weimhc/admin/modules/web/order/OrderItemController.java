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
import com.weimhc.modules.order.entity.OrderItem;
import com.weimhc.modules.order.service.OrderItemService;

/**
 * 订单项Controller
 * 
 * @author lc
 * @version 2017-01-03
 */
@Controller
@RequestMapping(value = "${adminPath}/order/orderItem")
public class OrderItemController extends AdminBaseController {

	@Autowired
	private OrderItemService orderItemService;

	@ModelAttribute
	public OrderItem get(@RequestParam(required = false) String id) {
		OrderItem entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = orderItemService.get(id);
		}
		if (entity == null) {
			entity = new OrderItem();
		}
		return entity;
	}

	@RequiresPermissions("order:orderItem:view")
	@RequestMapping(value = { "list", "" })
	public String list(OrderItem orderItem, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<OrderItem> page = orderItemService.findPage(new Page<OrderItem>(request, response), orderItem);
		model.addAttribute("page", page);
		model.addAttribute("orderItem", orderItem);
		return "modules/order/orderItemList";
	}

	@RequiresPermissions("order:orderItem:view")
	@RequestMapping(value = "form")
	public String form(OrderItem orderItem, Model model) {
		model.addAttribute("orderItem", orderItem);
		return "modules/order/orderItemForm";
	}

	@RequiresPermissions("order:orderItem:edit")
	@RequestMapping(value = "save")
	public String save(OrderItem orderItem, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
		if (!isValid(result, orderItem)) {
			return form(orderItem, model);
		}
		orderItemService.save(orderItem);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:" + Global.getAdminPath() + "/order/orderItem/?repage";
	}

	@RequiresPermissions("order:orderItem:edit")
	@RequestMapping(value = "delete")
	public String delete(OrderItem orderItem, RedirectAttributes redirectAttributes) {
		orderItemService.deleteEntity(orderItem);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:" + Global.getAdminPath() + "/order/orderItem/?repage";
	}

	@RequiresPermissions("order:orderItem:edit")
	@RequestMapping(value = "/deletes", method = RequestMethod.POST)
	public @ResponseBody ResultMessage deletes(String[] ids, RedirectAttributes redirectAttributes) {
		orderItemService.deleteBatchEntity(ids);
		return SUCCESS_MESSAGE;
	}
}