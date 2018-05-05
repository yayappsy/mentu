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
import com.weimhc.modules.order.entity.OrderInstalment;
import com.weimhc.modules.order.service.OrderInstalmentService;
import com.weimhc.modules.order.service.OrderService;

/**
 * 分期订单Controller
 * 
 * @author lc
 * @version 2017-06-14
 */
@Controller
@RequestMapping(value = "${adminPath}/order/orderInstalment")
public class OrderInstalmentController extends AdminBaseController {

	@Autowired
	private OrderInstalmentService orderInstalmentService;
	@Autowired
	private OrderService orderService;

	@ModelAttribute
	public OrderInstalment get(@RequestParam(required = false) String id) {
		OrderInstalment entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = orderInstalmentService.get(id);
		}
		if (entity == null) {
			entity = new OrderInstalment();
		}
		return entity;
	}

	/**
	 * 获取指定的会员
	 * 
	 * @param memberPrivilegeLog
	 * @param request
	 * @param model
	 */
	private void dealWithParticularOrder(OrderInstalment orderInstalment,
			HttpServletRequest request, Model model) {
		String searchType = request.getParameter("searchType");
		if (StringUtils.equals("particularMember", searchType)) {
			orderInstalment.setOrder(
					orderService.getEntity(orderInstalment.getOrder()));
		}
		model.addAttribute("searchType", searchType);
	}

	@RequiresPermissions("order:orderInstalment:view")
	@RequestMapping(value = { "list", "" })
	public String list(OrderInstalment orderInstalment,
			HttpServletRequest request, HttpServletResponse response,
			Model model) {
		dealWithParticularOrder(orderInstalment, request, model);
		Page<OrderInstalment> page = orderInstalmentService.findPage(
				new Page<OrderInstalment>(request, response), orderInstalment);
		model.addAttribute("page", page);
		model.addAttribute("orderInstalment", orderInstalment);
		return "modules/order/orderInstalmentList";
	}

	@RequiresPermissions("order:orderInstalment:view")
	@RequestMapping(value = "form")
	public String form(OrderInstalment orderInstalment,
			HttpServletRequest request, Model model) {
		dealWithParticularOrder(orderInstalment, request, model);
		model.addAttribute("orderInstalment", orderInstalment);
		return "modules/order/orderInstalmentForm";
	}

	@RequiresPermissions("order:orderInstalment:edit")
	@RequestMapping(value = "save")
	public String save(OrderInstalment orderInstalment, BindingResult result,
			Model model, RedirectAttributes redirectAttributes) {

		orderInstalmentService.save(orderInstalment);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:" + Global.getAdminPath()
				+ "/order/orderInstalment/?repage";
	}

	@RequiresPermissions("order:orderInstalment:edit")
	@RequestMapping(value = "delete")
	public String delete(OrderInstalment orderInstalment,
			RedirectAttributes redirectAttributes) {
		orderInstalmentService.deleteEntity(orderInstalment);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:" + Global.getAdminPath()
				+ "/order/orderInstalment/?repage";
	}

	@RequiresPermissions("order:orderInstalment:edit")
	@RequestMapping(value = "/deletes", method = RequestMethod.POST)
	public @ResponseBody ResultMessage deletes(String[] ids,
			RedirectAttributes redirectAttributes) {
		orderInstalmentService.deleteBatchEntity(ids);
		return SUCCESS_MESSAGE;
	}
}