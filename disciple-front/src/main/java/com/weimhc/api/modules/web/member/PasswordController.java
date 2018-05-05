/**
 * 
 */
package com.weimhc.api.modules.web.member;

import com.thinkgem.javamg.common.utils.StringUtils;
import com.weimhc.api.core.web.ApiBaseController;
import com.weimhc.api.modules.dto.req.BaseRegisterRQ;
import com.weimhc.api.modules.dto.req.UpdatePasswordRQ;
import com.weimhc.framework.dto.resp.ApiResult;
import com.weimhc.framework.security.PasswordUtils;
import com.weimhc.framework.service.CaptchaService;
import com.weimhc.framework.service.RSAService;
import com.weimhc.framework.web.utils.MessageSourceUtils;
import com.weimhc.front.modules.service.SystemService;
import com.weimhc.modules.base.utils.setting.CaptchaType;
import com.weimhc.modules.member.entity.Member;
import com.weimhc.modules.user.entity.IdentityType;
import com.weimhc.modules.user.entity.UserAuth;
import com.weimhc.modules.user.service.UserAuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 会员信息Controller
 * 
 * @author zsm
 * @version 2016-10-05
 */
@Api
@Controller("apiPasswordController")
@RequestMapping(value = "${apiPath}")
public class PasswordController extends ApiBaseController {

	@Autowired
	private SystemService systemService;
	@Autowired
	private UserAuthService userAuthService;

	@Resource
	private RSAService rsaService;

	@Autowired
	private CaptchaService sMSCaptchaService;

	@ApiOperation(value = "找回密码", notes = "找回密码通过手机号", tags = { "找回密码" })
	@RequestMapping(value = { "/password/find" }, method = RequestMethod.POST)
	@ResponseBody
	public ApiResult<?> findPassword(@RequestBody BaseRegisterRQ body, HttpServletRequest request) {
		// 验证是否有验证码
		if (StringUtils.isBlank(body.getCaptcha())) {
			return ApiResult.error(getMessage("error.captcha.captchaNull"));
		}
		// 验证验证码是否过期
		if (!sMSCaptchaService.isValid(CaptchaType.findPassword, body.getMobile(),
				body.getCaptcha())) {
			return ApiResult.error(getMessage("error.captcha.invalid"));
		}
		// 校验是否存在该用户
		if (systemService.checkIdentifierCanUse(body.getMobile(), IdentityType.mobile)) {
			return ApiResult.error(getMessage("error.register.identifierNotExist"));
		}

		Member member = systemService.getByIdentifier(body.getMobile(), IdentityType.mobile);
		member.setNewPassword(body.getPassword());
		systemService.updatePassword(member.getLatestUserInfo(), IdentityType.mobile);
		ApiResult<Object> result = new ApiResult<>();
		return result;
	}

	@ApiOperation(value = "修改密码", notes = "修改密码", tags = "修改密码", authorizations = {
			@Authorization(value = "token") })
	@RequestMapping(value = "/member/password/reset", method = { RequestMethod.POST,
			RequestMethod.OPTIONS })
	@ResponseBody
	public ApiResult<?> resetPassword(@RequestBody UpdatePasswordRQ body,
			HttpServletRequest request) {
		Member member =getCurrentMember(request);
		if (StringUtils.isBlank(member.getId())) {
			return ApiResult.error(MessageSourceUtils
					.getMessage("front.api.error.common.tokenNotExist"));
		}
		// 校验是否存在该用户
		if (systemService.checkIdentifierCanUse(body.getMobile(), body.getIdentityType())) {
			return ApiResult.error(getMessage("error.register.identifierNotExist"));
		}

		UserAuth userAuth = new UserAuth();
		userAuth.setIdentityType(body.getIdentityType());
		userAuth.setIdentifier(body.getMobile());
		userAuth = userAuthService.getEntity(userAuth);
		if (PasswordUtils.validatePassword(body.getPassword(), userAuth.getCredential())) {
			 member = systemService.getMember(userAuth.getUserInfo().getId());
			member.setNewPassword(body.getNewPassword());
			systemService.updatePassword(member.getLatestUserInfo(), body.getIdentityType());
		} else {
			return ApiResult.error(getMessage("error.login.accountLockCount"));
		}

		ApiResult<?> result = new ApiResult<>();
		return result;
	}
}