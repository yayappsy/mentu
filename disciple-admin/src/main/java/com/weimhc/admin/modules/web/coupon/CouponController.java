/**
 * 
 */
package com.weimhc.admin.modules.web.coupon;

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
import com.weimhc.modules.coupon.entity.Coupon;
import com.weimhc.modules.coupon.service.CouponService;

/**
 * 优惠券Controller
 * 
 * @author lc
 * @version 2017-06-07
 */
@Controller
@RequestMapping(value = "${adminPath}/coupon/coupon")
public class CouponController extends AdminBaseController {

	@Autowired
	private CouponService couponService;

	@ModelAttribute
	public Coupon get(@RequestParam(required = false) String id) {
		Coupon entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = couponService.get(id);
		}
		if (entity == null) {
			entity = new Coupon();
		}
		return entity;
	}

	@RequiresPermissions("coupon:coupon:view")
	@RequestMapping(value = { "list", "" })
	public String list(Coupon coupon, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		Page<Coupon> page = couponService
				.findPage(new Page<Coupon>(request, response), coupon);
		model.addAttribute("page", page);
		model.addAttribute("coupon", coupon);
		if (dealWithSearchType(request, model)) {
			return "modules/coupon/couponSearchList";
		}
		return "modules/coupon/couponList";
	}

	@RequiresPermissions("coupon:coupon:view")
	@RequestMapping(value = "form")
	public String form(Coupon coupon, Model model) {
		model.addAttribute("coupon", coupon);
		return "modules/coupon/couponForm";
	}

	@RequiresPermissions("coupon:coupon:edit")
	@RequestMapping(value = "save")
	public String save(Coupon coupon, BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {
		if (!isValid(result, coupon)) {
			return form(coupon, model);
		}
		couponService.save(coupon);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:" + Global.getAdminPath() + "/coupon/coupon/?repage";
	}

	@RequiresPermissions("coupon:coupon:edit")
	@RequestMapping(value = "delete")
	public String delete(Coupon coupon, RedirectAttributes redirectAttributes) {
		couponService.deleteEntity(coupon);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:" + Global.getAdminPath() + "/coupon/coupon/?repage";
	}

	@RequiresPermissions("coupon:coupon:edit")
	@RequestMapping(value = "/deletes", method = RequestMethod.POST)
	public @ResponseBody ResultMessage deletes(String[] ids,
			RedirectAttributes redirectAttributes) {
		couponService.deleteBatchEntity(ids);
		return SUCCESS_MESSAGE;
	}
}