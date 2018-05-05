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
import com.weimhc.framework.web.ResultMessage;
import com.weimhc.modules.member.entity.MemberAddress;
import com.weimhc.modules.member.service.MemberAddressService;
import com.weimhc.modules.member.service.MemberService;

/**
 * 会员地址Controller
 * 
 * @author liuchao
 * @version 2016-10-12
 */
@Controller
@RequestMapping(value = "${adminPath}/member/memberAddress")
public class MemberAddressController extends AdminBaseController {

	@Autowired
	private MemberAddressService memberAddressService;
	@Autowired
	private MemberService memberService;

	@ModelAttribute
	public MemberAddress get(@RequestParam(required = false) String id) {
		MemberAddress entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = memberAddressService.get(id);
		}
		if (entity == null) {
			entity = new MemberAddress();
		}
		return entity;
	}

	@RequiresPermissions("member:memberAddress:view")
	@RequestMapping(value = { "list", "" })
	public String list(MemberAddress memberAddress, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		dealWithParticularMember(memberAddress, request, model);
		Page<MemberAddress> page = memberAddressService.findPage(
				new Page<MemberAddress>(request, response), memberAddress);
		model.addAttribute("page", page);

		return "modules/member/memberAddressList";
	}

	@RequiresPermissions("member:memberAddress:view")
	@RequestMapping(value = "form")
	public String form(MemberAddress memberAddress, Model model,
			HttpServletRequest request) {
		dealWithParticularMember(memberAddress, request, model);

		model.addAttribute("memberAddress", memberAddress);
		return "modules/member/memberAddressForm";
	}

	/**
	 * 获取指定的会员
	 * 
	 * @param memberAddress
	 * @param request
	 * @param model
	 */
	private void dealWithParticularMember(MemberAddress memberAddress,
			HttpServletRequest request, Model model) {
		String searchType = request.getParameter("searchType");
		if (StringUtils.equals("particularMember", searchType)) {
			memberAddress.setMember(
					memberService.getEntity(memberAddress.getMember()));
		}
		model.addAttribute("searchType", searchType);
	}
	@RequiresPermissions("member:memberAddress:edit")
	@RequestMapping(value = "save")
	public String save(MemberAddress memberAddress, BindingResult result,
			Model model, RedirectAttributes redirectAttributes,
			HttpServletRequest request) {
		if (!isValid(result, memberAddress)) {
			return form(memberAddress, model, request);
		}
		memberAddressService.save(memberAddress);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:" + Global.getAdminPath()
				+ "/member/memberAddress/?repage";
	}

	@RequiresPermissions("member:memberAddress:edit")
	@RequestMapping(value = "delete")
	public String delete(MemberAddress memberAddress,
			RedirectAttributes redirectAttributes) {
		memberAddressService.deleteEntity(memberAddress);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:" + Global.getAdminPath()
				+ "/member/memberAddress/?repage";
	}

	@RequiresPermissions("member:memberAddress:edit")
	@RequestMapping(value = "/deletes", method = RequestMethod.POST)
	public @ResponseBody ResultMessage deletes(String[] ids,
			RedirectAttributes redirectAttributes) {
		memberAddressService.deleteBatchEntity(ids);
		return SUCCESS_MESSAGE;
	}
}