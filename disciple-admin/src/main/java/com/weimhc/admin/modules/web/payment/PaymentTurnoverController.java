/**
 * 
 */
package com.weimhc.admin.modules.web.payment;

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
import com.weimhc.modules.payment.entity.PaymentMethod;
import com.weimhc.modules.payment.entity.PaymentTurnover;
import com.weimhc.modules.payment.service.PaymentMethodService;
import com.weimhc.modules.payment.service.PaymentTurnoverService;

/**
 * 订单支付流水Controller
 * 
 * @author lc
 * @version 2017-06-08
 */
@Controller
@RequestMapping(value = "${adminPath}/payment/paymentTurnover")
public class PaymentTurnoverController extends AdminBaseController {

	@Autowired
	private PaymentTurnoverService paymentTurnoverService;
	@Autowired
	private PaymentMethodService paymentMethodService;

	@ModelAttribute
	public PaymentTurnover get(@RequestParam(required = false) String id) {
		PaymentTurnover entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = paymentTurnoverService.get(id);
		}
		if (entity == null) {
			entity = new PaymentTurnover();
		}
		return entity;
	}

	@RequiresPermissions("payment:paymentTurnover:view")
	@RequestMapping(value = { "list", "" })
	public String list(PaymentTurnover paymentTurnover,
			HttpServletRequest request, HttpServletResponse response,
			Model model) {
		Page<PaymentTurnover> page = paymentTurnoverService.findPage(
				new Page<PaymentTurnover>(request, response), paymentTurnover);
		model.addAttribute("page", page);
		model.addAttribute("paymentTurnover", paymentTurnover);
		model.addAttribute("paymentMethods",
				paymentMethodService.findAllList(new PaymentMethod()));
		return "modules/payment/paymentTurnoverList";
	}

	@RequiresPermissions("payment:paymentTurnover:view")
	@RequestMapping(value = "form")
	public String form(PaymentTurnover paymentTurnover, Model model) {
		model.addAttribute("paymentTurnover", paymentTurnover);
		model.addAttribute("paymentMethods",
				paymentMethodService.findAllList(new PaymentMethod()));
		return "modules/payment/paymentTurnoverForm";
	}

	@RequiresPermissions("payment:paymentTurnover:edit")
	@RequestMapping(value = "save")
	public String save(PaymentTurnover paymentTurnover, BindingResult result,
			Model model, RedirectAttributes redirectAttributes) {
		if (!isValid(result, paymentTurnover)) {
			return form(paymentTurnover, model);
		}
		paymentTurnoverService.save(paymentTurnover);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:" + Global.getAdminPath()
				+ "/payment/paymentTurnover/?repage";
	}

	@RequiresPermissions("payment:paymentTurnover:edit")
	@RequestMapping(value = "delete")
	public String delete(PaymentTurnover paymentTurnover,
			RedirectAttributes redirectAttributes) {
		paymentTurnoverService.deleteEntity(paymentTurnover);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:" + Global.getAdminPath()
				+ "/payment/paymentTurnover/?repage";
	}

	@RequiresPermissions("payment:paymentTurnover:edit")
	@RequestMapping(value = "/deletes", method = RequestMethod.POST)
	public @ResponseBody ResultMessage deletes(String[] ids,
			RedirectAttributes redirectAttributes) {
		paymentTurnoverService.deleteBatchEntity(ids);
		return SUCCESS_MESSAGE;
	}
}