/**
 * 
 */
package com.weimhc.admin.modules.web.order;

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
import com.weimhc.modules.order.entity.OrderCoupon;
import com.weimhc.modules.order.service.OrderCouponService;

/**
 * 订单优惠券Controller
 * @author lc
 * @version 2017-06-22
 */
@Controller
@RequestMapping(value = "${adminPath}/order/orderCoupon")
public class OrderCouponController extends AdminBaseController {

	@Autowired
	private OrderCouponService orderCouponService;
	
	@ModelAttribute
	public OrderCoupon get(@RequestParam(required=false) String id) {
		OrderCoupon entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = orderCouponService.get(id);
		}
		if (entity == null){
			entity = new OrderCoupon();
		}
		return entity;
	}
	
	@RequiresPermissions("order:orderCoupon:view")
	@RequestMapping(value = {"list", ""})
	public String list(OrderCoupon orderCoupon, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<OrderCoupon> page = orderCouponService.findPage(new Page<OrderCoupon>(request, response), orderCoupon); 
		model.addAttribute("page", page);
		model.addAttribute("orderCoupon", orderCoupon);
		return "modules/order/orderCouponList";
	}

	@RequiresPermissions("order:orderCoupon:view")
	@RequestMapping(value = "form")
	public String form(OrderCoupon orderCoupon, Model model) {
		model.addAttribute("orderCoupon", orderCoupon);
		return "modules/order/orderCouponForm";
	}

	@RequiresPermissions("order:orderCoupon:edit")
	@RequestMapping(value = "save")
	public String save(OrderCoupon orderCoupon, BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {
		if (!isValid(result, orderCoupon)){
			return form(orderCoupon, model);
		}
		orderCouponService.save(orderCoupon);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:"+Global.getAdminPath()+"/order/orderCoupon/?repage";
	}
	
	@RequiresPermissions("order:orderCoupon:edit")
	@RequestMapping(value = "delete")
	public String delete(OrderCoupon orderCoupon, RedirectAttributes redirectAttributes) {
		orderCouponService.deleteEntity(orderCoupon);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:"+Global.getAdminPath()+"/order/orderCoupon/?repage";
	}

	@RequiresPermissions("order:orderCoupon:edit")
	@RequestMapping(value = "/deletes", method = RequestMethod.POST)
	public @ResponseBody ResultMessage deletes(String[] ids,
			RedirectAttributes redirectAttributes) {
		orderCouponService.deleteBatchEntity(ids);
		return SUCCESS_MESSAGE;
	}
}