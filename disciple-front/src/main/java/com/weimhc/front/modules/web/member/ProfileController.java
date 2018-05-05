/*
 * 
 * 
 * 
 */
package com.weimhc.front.modules.web.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.javamg.common.config.Global;
import com.weimhc.framework.utils.UploadFolder;
import com.weimhc.front.core.web.FrontBaseController;
import com.weimhc.front.modules.service.SystemService;
import com.weimhc.front.modules.utils.MemberUtils;
import com.weimhc.modules.member.entity.Member;
import com.weimhc.modules.member.service.MemberService;

/**
 * Controller - 会员中心 - 个人资料
 * 
 * @author zsm
 * @version 2016-01-12
 */
@Controller
@RequestMapping("${frontPath}/member/profile")
public class ProfileController extends FrontBaseController {

	@Autowired
	private MemberService memberService;

	@Autowired
	private SystemService systemService;

	/**
	 * 编辑
	 */
	@RequestMapping(value = { "form", "" })
	public String form(Member member, Model model) {
		member = MemberUtils.getCurrent();
		model.addAttribute("uploadFolder", UploadFolder.avatar);
		model.addAttribute("member", member);
		return "/modules/member/profile/form";
	}

	/**
	 * 更新
	 */
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public String save(Member member, BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {

		if (!isValid(result, member.getNickname())) {
			return form(member, model);
		}
		systemService.updateProfile(member);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:" + Global.getFrontPath() + "/member/profile/?repage";
	}

}