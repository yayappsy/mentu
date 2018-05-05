/**
 * 
 */
package com.weimhc.admin.modules.web.member;

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
import com.weimhc.framework.dto.resp.ApiResult;
import com.weimhc.framework.web.ResultMessage;
import com.weimhc.modules.coupon.entity.Coupon;
import com.weimhc.modules.member.entity.Member;
import com.weimhc.modules.member.entity.MemberCoupon;
import com.weimhc.modules.member.service.MemberCouponService;
import com.weimhc.modules.member.service.MemberService;

/**
 * 学员优惠券Controller
 * 
 * @author lc
 * @version 2017-06-07
 */
@Controller
@RequestMapping(value = "${adminPath}/member/memberCoupon")
public class MemberCouponController extends AdminBaseController {

	@Autowired
	private MemberCouponService memberCouponService;
	@Autowired
	private MemberService memberService;

	@ModelAttribute
	public MemberCoupon get(@RequestParam(required = false) String id) {
		MemberCoupon entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = memberCouponService.get(id);
		}
		if (entity == null) {
			entity = new MemberCoupon();
		}
		return entity;
	}

	/**
	 * 获取指定的会员
	 * 
	 * @param memberCoupon
	 * @param request
	 * @param model
	 */
	private void dealWithParticularMember(MemberCoupon memberCoupon,
			HttpServletRequest request, Model model) {
		String searchType = request.getParameter("searchType");
		if (StringUtils.equals("particularMember", searchType)) {
			memberCoupon.setStudent(
					memberService.getEntity(memberCoupon.getStudent()));
		}
		model.addAttribute("searchType", searchType);
	}

	@RequiresPermissions("member:memberCoupon:view")
	@RequestMapping(value = { "list", "" })
	public String list(MemberCoupon memberCoupon, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		dealWithParticularMember(memberCoupon, request, model);
		Page<MemberCoupon> page = memberCouponService.findPage(
				new Page<MemberCoupon>(request, response), memberCoupon);
		model.addAttribute("page", page);
		model.addAttribute("memberCoupon", memberCoupon);
		return "modules/member/memberCouponList";
	}

	@RequiresPermissions("member:memberCoupon:view")
	@RequestMapping(value = "form")
	public String form(MemberCoupon memberCoupon, HttpServletRequest request,
			Model model) {
		model.addAttribute("memberCoupon", memberCoupon);
		dealWithParticularMember(memberCoupon, request, model);
		return "modules/member/memberCouponForm";
	}

	@RequiresPermissions("member:memberCoupon:edit")
	@RequestMapping(value = "save")
	public String save(MemberCoupon memberCoupon, BindingResult result,
			Model model, RedirectAttributes redirectAttributes) {

		memberCouponService.save(memberCoupon);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:" + Global.getAdminPath()
				+ "/member/memberCoupon/?repage";
	}

	@RequiresPermissions("member:memberCoupon:edit")
	@RequestMapping(value = "delete")
	public String delete(MemberCoupon memberCoupon,
			RedirectAttributes redirectAttributes) {
		memberCouponService.deleteEntity(memberCoupon);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:" + Global.getAdminPath()
				+ "/member/memberCoupon/?repage";
	}

	@RequiresPermissions("member:memberCoupon:edit")
	@RequestMapping(value = "/deletes", method = RequestMethod.POST)
	public @ResponseBody ResultMessage deletes(String[] ids,
			RedirectAttributes redirectAttributes) {
		memberCouponService.deleteBatchEntity(ids);
		return SUCCESS_MESSAGE;
	}

	@RequestMapping(value = "saveCoupon")
	@ResponseBody
	public ApiResult<?> saveCoupon(String memberIds, String couponId,
			HttpServletRequest request) {
		ApiResult<?> result = new ApiResult<>();
		String[] memberId = memberIds.split(",");
		for (String string : memberId) {
			MemberCoupon memberCoupon = new MemberCoupon();
			memberCoupon.setCoupon(new Coupon(couponId));
			memberCoupon.setStudent(new Member(string));
			memberCoupon.setIsUsed(false);
			memberCouponService.save(memberCoupon);
		}
		return result;
	}
}