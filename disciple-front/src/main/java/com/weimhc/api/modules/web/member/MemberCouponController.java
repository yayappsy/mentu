/**
 * 
 */
package com.weimhc.api.modules.web.member;

import com.google.common.collect.Lists;
import com.weimhc.api.core.web.ApiBaseController;
import com.weimhc.api.modules.dto.resp.user.MemberCouponDto;
import com.weimhc.framework.dto.resp.ApiResult;
import com.weimhc.framework.utils.CustomBeanUtils;
import com.weimhc.framework.web.utils.MessageSourceUtils;
import com.weimhc.modules.member.entity.Member;
import com.weimhc.modules.member.entity.MemberCoupon;
import com.weimhc.modules.member.service.MemberCouponService;
import com.weimhc.modules.member.service.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 套餐信息Controller
 * 
 * @author lc
 * @version 2017-06-16
 */
@Api
@Controller("apiMemberCouponController")
@RequestMapping(value = "${apiPath}/member/coupon")
public class MemberCouponController extends ApiBaseController {

	@Autowired
	private MemberService memberService;
	@Autowired
	private MemberCouponService memberCouponService;

	@ApiOperation(value = "通过会员获取会员优惠券列表", notes = "通过会员获取会员优惠券列表", tags = "我要入学", authorizations = {
			@Authorization(value = "token") })
	@RequestMapping(value = "list", method = RequestMethod.GET)
	@ResponseBody
	public ApiResult<List<MemberCouponDto>> getInfo(
			HttpServletRequest request) {
		ApiResult<List<MemberCouponDto>> result = new ApiResult<>();
		Member member = getCurrentMember(request);
		if (member == null) {
			return ApiResult.error(MessageSourceUtils
					.getMessage("front.api.error.common.entityNotExist"));
		}
		MemberCoupon memberCoupon = new MemberCoupon();
		memberCoupon.setStudent(member);
		List<MemberCoupon> memberCouponList = memberCouponService
				.findList(memberCoupon);
		List<MemberCouponDto> lists = Lists.newArrayList();
		if (memberCouponList.size() > 0) {
			lists = CustomBeanUtils.copyList(memberCouponList,
					MemberCouponDto.class);
		}
		result.setData(lists);
		return result;
	}

}