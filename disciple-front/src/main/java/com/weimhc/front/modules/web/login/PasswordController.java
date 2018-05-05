/*
 * 
 * 
 * 
 */
package com.weimhc.front.modules.web.login;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.thinkgem.javamg.common.utils.StringUtils;
import com.weimhc.framework.service.RSAService;
import com.weimhc.framework.web.ResultMessage;
import com.weimhc.front.core.web.FrontBaseController;
import com.weimhc.front.modules.service.SystemService;
import com.weimhc.modules.member.entity.Member;
import com.weimhc.modules.member.service.MemberService;
import com.weimhc.modules.user.entity.IdentityType;

/**
 * Controller - 共用
 * 
 * 
 * 
 */
@Controller("passwordController")
@RequestMapping("${frontPath}/password")
public class PasswordController extends FrontBaseController {

	@Resource
	private MemberService memberService;
	@Resource
	private SystemService systemService;
	@Resource
	private RSAService rsaService;

	/**
	 * 跳转修改密码页
	 */
	@RequestMapping(value = "find")
	public String retrievePasswordForm(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String registerType = request.getParameter("registerType");
		if (StringUtils.isBlank(registerType)) {
			registerType = "mobile";
		}
		model.addAttribute("registerType", registerType);
		return "modules/password/modified";
	}

	/**
	 * 检查用户名是否被禁用或已存在
	 */
	@RequestMapping(value = "/check_username", method = RequestMethod.GET)
	public @ResponseBody boolean checkUsername(String username) {
		if (StringUtils.isEmpty(username)) {
			return false;
		}
		if (memberService.usernameExists(username)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 找回密码
	 */
	@RequestMapping(value = "retrievePassword")
	@ResponseBody
	public ResultMessage retrievePassword(HttpServletRequest request,
			HttpServletResponse response, String username, String captcha,
			Model model) {
		String password = rsaService.decryptParameter("enPassword", request);
		rsaService.removePrivateKey(request);
		Member member = systemService.getByIdentifier(username,
				IdentityType.username);
		if (null != member) {
			member.setNewPassword(password);
			systemService.updatePassword(member.getLatestUserInfo());
		} else {
			return ResultMessage.error("shop.retrievePassword.error");
		}

		return ResultMessage.success("shop.retrievePassword.success");
	}

}