/**
* 
*/
package com.weimhc.api.modules.web.session;

import com.thinkgem.javamg.common.utils.StringUtils;
import com.weimhc.api.core.web.ApiBaseController;
import com.weimhc.api.modules.dto.req.ThirdPartyLoginsRQ;
import com.weimhc.api.modules.dto.resp.LoginDto;
import com.weimhc.framework.dto.resp.ApiResult;
import com.weimhc.framework.security.MemberPrincipal;
import com.weimhc.framework.utils.AccessUtils;
import com.weimhc.framework.utils.AppTokenHelper;
import com.weimhc.front.modules.service.SystemService;
import com.weimhc.modules.member.entity.Member;
import com.weimhc.modules.user.entity.IdentityType;
import com.weimhc.modules.user.entity.UserAuth;
import com.weimhc.modules.user.service.UserAuthService;
import com.weimhc.modules.user.utils.UserAuthUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 第三方登录qqController
 * 
 * @author zsm
 * @version 2016-10-05
 */
@Api
@Controller("apiThirdLoginSessionController")
@RequestMapping(value = "${apiPath}/session")
public class ThirdLoginSessionController extends ApiBaseController {

	@Autowired
	private SystemService systemService;

	@Resource
	private UserAuthService userAuthService;

	@ApiOperation(value = "第三方登录验证", notes = "第三方登录验证", tags = { "第三方登录" })
	@RequestMapping(value = "/thirdPartyLogins", method = { RequestMethod.POST })
	@ResponseBody
	public ApiResult<LoginDto> thirdPartyLogins(@RequestBody ThirdPartyLoginsRQ thirdPartyLoginsRQ,
			HttpServletRequest request) {
		ApiResult<LoginDto> apiResult = new ApiResult<>();
		// 查询是否有用手机标识的账号
		UserAuth userAuth = new UserAuth();
		userAuth.setIdentityType(thirdPartyLoginsRQ.getIdentityType());
		if (IdentityType.qq.equals(thirdPartyLoginsRQ.getIdentityType())) {
			userAuth.setIdentifier(thirdPartyLoginsRQ.getOpenid());
		} else {
			userAuth.setIdentifier(thirdPartyLoginsRQ.getUid());
		}
		userAuth = userAuthService.getEntity(userAuth);
		Member member = new Member();
		if (userAuth == null) {
			if (IdentityType.qq.equals(thirdPartyLoginsRQ.getIdentityType())) {
				member.setThirdPartUserId(thirdPartyLoginsRQ.getOpenid());
			}
			member.setNickname(thirdPartyLoginsRQ.getName());
			if (StringUtils.isNotBlank(thirdPartyLoginsRQ.getGender())
					&& thirdPartyLoginsRQ.getGender().equals("男")) {
				member.setGender("1");
			} else {
				member.setGender("2");
			}
			member.setAvatar(thirdPartyLoginsRQ.getIconurl());
			member.setRegisterIp(AccessUtils.getIpAddress(request));
			member.setNewPassword(thirdPartyLoginsRQ.getOpenid());
			systemService.saveMember(member, true, thirdPartyLoginsRQ.getIdentityType());

			LoginDto loginDto = new LoginDto();
			BeanUtils.copyProperties(member, loginDto);
			apiResult.setData(loginDto);
		} else {
			LoginDto loginDto = new LoginDto();
			member = systemService.getMember(userAuth.getUserInfo().getId());
			UserAuth searchUserAuth = new UserAuth();
			searchUserAuth.setUserInfo(userAuth.getUserInfo());
			searchUserAuth.setIdentityType(IdentityType.mobile);
			Boolean hasMobileIdentity = userAuthService.countIdentity(searchUserAuth);
			if (hasMobileIdentity) {
				MemberPrincipal memberPrincipal = new MemberPrincipal(member, true);
				String token = AppTokenHelper.generateToken(memberPrincipal);
				BeanUtils.copyProperties(member, loginDto);
				loginDto.setToken(token);
				apiResult.setData(loginDto);
			} else {
				BeanUtils.copyProperties(member, loginDto);
				apiResult.setData(loginDto);
			}
		}
		return apiResult;
	}

	@ApiOperation(value = "绑定账号密码", notes = "绑定账号密码", tags = { "第三方登录" })
	@RequestMapping(value = "/needPassword", method = RequestMethod.POST)
	@ResponseBody
	public ApiResult<LoginDto> needPassword(@ApiParam(value = "手机号") @RequestParam String mobile,
			@ApiParam(value = "密码") @RequestParam String password,
			@ApiParam(value = "用户id") @RequestParam String memberId, HttpServletRequest request) {
		ApiResult<LoginDto> apiResult = new ApiResult<>();
		LoginDto loginDto = new LoginDto();
		Member member = systemService.getMember(memberId);
		member.setUsername(mobile);
		member.setMobile(mobile);
		member.setNewPassword(password);
		systemService.updateProfile(member);
		UserAuthUtils.insertPassword(member.getLatestUserInfo(), IdentityType.mobile);
		BeanUtils.copyProperties(member, loginDto);
		apiResult.setData(loginDto);
		return apiResult;
	}
}