/*
 * 
 * 
 * 
 */
package com.weimhc.front.modules.web.member;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.javamg.common.utils.StringUtils;
import com.weimhc.framework.security.PasswordUtils;
import com.weimhc.framework.service.RSAService;
import com.weimhc.framework.web.ResultMessage;
import com.weimhc.front.core.web.FrontBaseController;
import com.weimhc.front.modules.service.SystemService;
import com.weimhc.front.modules.utils.MemberUtils;
import com.weimhc.modules.member.entity.Member;
import com.weimhc.modules.member.service.MemberService;
import com.weimhc.modules.user.entity.IdentityType;
import com.weimhc.modules.user.entity.UserAuth;
import com.weimhc.modules.user.utils.UserAuthUtils;

/**
 * Controller - 会员中心 - 个人资料
 * 
 * @author zsm
 * @version 2016-01-12
 */
@Controller("memberPasswordController")
@RequestMapping("${frontPath}/member/password")
public class PasswordController extends FrontBaseController {

	@Autowired
	private MemberService memberService;

	@Autowired
	private SystemService systemService;

	@Resource
	private RSAService rsaService;

	/**
	 * 编辑
	 */
	@RequestMapping(value = { "form", "" })
	public String form(Member member, Model model) {
		member = MemberUtils.getCurrent();
		model.addAttribute("member", member);
		return "/modules/member/password/form";
	}

	/**
	 * 更新密码
	 */
	@RequestMapping(value = "save", method = RequestMethod.POST)
	@ResponseBody
	public ResultMessage save(HttpServletRequest request, Model model,
			RedirectAttributes redirectAttributes) {

		String oldPassword = rsaService.decryptParameter("enPassword", request);
		String newPassword = rsaService.decryptParameter("enNewPassword",
				request);
		rsaService.removePrivateKey(request);

		Member member = MemberUtils.getCurrent();
		if (StringUtils.isNotBlank(oldPassword)
				&& StringUtils.isNotBlank(newPassword)) {
			UserAuth searchUserAuth = UserAuthUtils.get(member.getUsername(),
					IdentityType.username);
			if (PasswordUtils.validatePassword(oldPassword,
					searchUserAuth.getCredential())) {
				member.setNewPassword(newPassword);
				systemService.updatePassword(member.getLatestUserInfo());
			} else {
				return ResultMessage.error("front.retrievePassword.error");
			}
		} else {
			return ResultMessage.error("front.retrievePassword.error");
		}
		return ResultMessage.success("front.retrievePassword.success");
	}

}