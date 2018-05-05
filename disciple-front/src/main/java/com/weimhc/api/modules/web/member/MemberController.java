/**
 * 
 */
package com.weimhc.api.modules.web.member;

import com.thinkgem.javamg.common.utils.StringUtils;
import com.thinkgem.javamg.common.utils.UserAgentUtils;
import com.weimhc.api.core.web.ApiBaseController;
import com.weimhc.api.modules.dto.req.BaseLoginRQ;
import com.weimhc.api.modules.dto.req.BaseRegisterRQ;
import com.weimhc.api.modules.dto.req.MemberProfileRQ;
import com.weimhc.api.modules.dto.resp.user.MemberDto;
import com.weimhc.framework.dto.resp.ApiResult;
import com.weimhc.framework.service.CaptchaService;
import com.weimhc.framework.service.RSAService;
import com.weimhc.framework.utils.AccessUtils;
import com.weimhc.front.modules.service.SystemService;
import com.weimhc.modules.base.utils.*;
import com.weimhc.modules.base.utils.setting.CaptchaType;
import com.weimhc.modules.member.entity.Member;
import com.weimhc.modules.user.entity.IdentityType;
import com.weimhc.modules.user.entity.UserCorpInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.Authorization;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 会员信息Controller
 * 
 * @author zsm
 * @version 2016-10-05
 */
@Api
@Controller("apiMemberController")
@RequestMapping(value = "${apiPath}/member")
public class MemberController extends ApiBaseController {

	@Autowired
	private SystemService systemService;

	@Resource
	private RSAService rsaService;

	@Autowired
	private CaptchaService sMSCaptchaService;

	/**
	 * 检查用户名是否可用
	 */
	@ApiIgnore
	@RequestMapping(value = "/checkUsername", method = RequestMethod.GET)
	@ResponseBody
	public boolean checkUsernameCanUse(String username) {
		return systemService.checkIdentifierCanUse(username, IdentityType.username);
	}

	/**
	 * 检查检查手机号是否可用
	 */
	@ApiOperation(value = "检查手机号是否可用", notes = "检查手机号是否可用", tags = "注册")
	@RequestMapping(value = "/checkMobile", method = RequestMethod.GET)
	@ResponseBody
	public boolean checkMobileCanUse(String mobile) {
		return systemService.checkIdentifierCanUse(mobile, IdentityType.mobile);
	}

	/**
	 * 检查检查手机号是否可用
	 */
	@ApiIgnore
	@RequestMapping(value = "/checkEmail", method = RequestMethod.GET)
	@ResponseBody
	public boolean checkEmailCanUse(String email) {
		return systemService.checkIdentifierCanUse(email, IdentityType.email);
	}

	@ApiOperation(value = "添加会员", notes = "添加会员", tags = "注册")
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public ApiResult<? extends MemberDto> add(@RequestBody BaseRegisterRQ body,
			HttpServletRequest request) {
		if (!"1".equals(body.getType())&&!"2".equals(body.getType())) {
			return ApiResult.error(-1, "type错误！请重新传值！");
		}
		// 验证是否有手机号
		if (StringUtils.isBlank(body.getMobile())) {
			return ApiResult.error(getMessage("error.register.identifierNull"));
		}
		// 验证是否有验证码
		if (StringUtils.isBlank(body.getCaptcha())) {
			return ApiResult.error(getMessage("error.captcha.captchaNull"));
		}
		// 验证验证码是否过期
		if (!sMSCaptchaService.isValid(CaptchaType.memberRegister, body.getMobile(),
				body.getCaptcha())) {
			return ApiResult.error(getMessage("error.captcha.invalid"));
		}
		// 验证是否已经注册
		if (!systemService.checkIdentifierCanUse(body.getMobile(), IdentityType.mobile)) {
			return ApiResult.error(getMessage("error.register.identifierExist"));
		}

		Member member = new Member();
		member.setUsername(body.getMobile());
		member.setMobile(body.getMobile());
		member.setNewPassword(body.getPassword());
		member.setNickname("u_" + StringUtils.substring(body.getMobile(), 0, 7));
		member.setRegisterIp(AccessUtils.getIpAddress(request));
		systemService.insertMember(member);

		BaseLoginRQ loginBody = new BaseLoginRQ();
		loginBody.setIdentifier(body.getMobile());
		loginBody.setPassword(body.getPassword());
		loginBody.setType(body.getType());
		loginBody.setIdentityType(IdentityType.mobile);
		return systemService.login(loginBody, UserAgentUtils.isMobileOrTablet(request));
	}

	@ApiOperation(value = "提交会员信息", notes = "提交会员信息", tags = { "注册" })
	@RequestMapping(value = "update", method = RequestMethod.POST)
	@ResponseBody
	public ApiResult<MemberDto> update(
			@ApiParam(value = "会员id", example = "1", required = true) @RequestParam(required = true) String id,
			@RequestBody MemberProfileRQ body, HttpServletRequest request) {
		Member member = systemService.getMember(id);
		if (member == null) {
			return ApiResult.error(getMessage("error.common.entityNotExist"));
		}
		BeanUtils.copyProperties(body, member);
		copyBaseInfo(body, member);
		systemService.updateProfile(member);

		ApiResult<MemberDto> apiResult = new ApiResult<>();
		MemberDto memberDto = new MemberDto();
		BeanUtils.copyProperties(member, memberDto);
		apiResult.setData(memberDto);
		return apiResult;
	}

	@ApiOperation(value = "修改会员信息", notes = "修改会员信息", tags = { "我的-会员操作" }, authorizations = {
			@Authorization(value = "token") })
	@RequestMapping(value = "profile/update", method = RequestMethod.POST)
	@ResponseBody
	public ApiResult<MemberDto> profileUpdate(@RequestBody MemberProfileRQ body,
			HttpServletRequest request) {
		Member member = getCurrentMember(request);
		BeanUtils.copyProperties(body, member);
		copyBaseInfo(body, member);
		systemService.updateProfile(member);
		ApiResult<MemberDto> apiResult = new ApiResult<>();
		MemberDto memberDto = new MemberDto();
		BeanUtils.copyProperties(member, memberDto);
		BeanUtils.copyProperties(member.getUserCorpInfo(), memberDto);
		apiResult.setData(memberDto);
		return apiResult;
	}

	@ApiOperation(value = "上传头像", notes = "上传头像", tags = "我的-会员操作", authorizations = {
			@Authorization(value = "token") })
	@RequestMapping(value = "updateAvatar", method = RequestMethod.POST)
	@ResponseBody
	public ApiResult<MemberDto> updateAvatar(
			@ApiParam(value = "头像", required = true) @RequestParam String avatar,
			HttpServletRequest request) {
		Member member = getCurrentMember(request);
		member.setAvatar(avatar);
		systemService.updateAvatarById(member);

		ApiResult<MemberDto> apiResult = new ApiResult<>();
		MemberDto memberDto = new MemberDto();
		BeanUtils.copyProperties(member, memberDto);

		apiResult.setData(memberDto);
		return apiResult;
	}

	@ApiOperation(value = "根据token获取会员信息", notes = "获取会员信息", tags = {
			"我的-会员操作" }, authorizations = { @Authorization(value = "token") })
	@RequestMapping(value = "get", method = RequestMethod.GET)
	@ResponseBody
	public ApiResult<MemberDto> get(HttpServletRequest request) {
		ApiResult<MemberDto> result = new ApiResult<MemberDto>();
		Member member = getCurrentMember(request);
		member = systemService.getMember(member.getId());
		getBaseInfo(member);
		MemberDto memberDto = new MemberDto();
		BeanUtils.copyProperties(member, memberDto);
		result.setData(memberDto);
		return result;
	}

	/**
	 * 获取详细信息
	 */
	private void getBaseInfo(Member member) {
		if (member.getNation() != null && StringUtils.isNotBlank(member.getNation().getId())) {
			member.setNation(NationUtils.findById(member.getNation().getId()));
		}
		if (member.getReligiousBelief() != null
				&& StringUtils.isNotBlank(member.getReligiousBelief().getId())) {
			member.setReligiousBelief(
					ReligiousBeliefUtils.findById(member.getReligiousBelief().getId()));
		}

		if (member.getResidence() != null
				&& StringUtils.isNotBlank(member.getResidence().getId())) {
			member.setResidence(RegionUtils.getRegion(member.getResidence().getId()));
		}
		if (member.getEducation() != null
				&& StringUtils.isNotBlank(member.getEducation().getId())) {
			member.setEducation(EducationUtils.findById(member.getEducation().getId()));
		}
		if (member.getUserCorpInfo() != null) {
			UserCorpInfo corpInfo = member.getUserCorpInfo();
			if (corpInfo.getCorpAddress() != null) {
				corpInfo.setCorpAddress(RegionUtils.getRegion(corpInfo.getCorpAddress().getId()));
			}
			if (corpInfo.getCorpIndustry() != null) {
				corpInfo.setCorpIndustry(
						BaseIndustryUtils.findById(corpInfo.getCorpIndustry().getId()));
			}
		}

	}

	/**
	 * 获取详细信息
	 */
	private void copyBaseInfo(MemberProfileRQ body, Member member) {
		if (body != null) {
			member.setResidence(RegionUtils.getRegion(body.getResidenceId()));
			member.setBirthplace(RegionUtils.getRegion(body.getBirthpalceId()));
			member.setEducation(EducationUtils.findById(body.getEducationId()));
			UserCorpInfo corpInfo = new UserCorpInfo();
			BeanUtils.copyProperties(body, corpInfo);
			corpInfo.setCorpAddress(RegionUtils.getRegion(body.getCorpAddressId()));
			corpInfo.setCorpIndustry(BaseIndustryUtils.findById(body.getCorpIndustryId()));
			member.setUserCorpInfo(corpInfo);
		} else {
			getBaseInfo(member);
		}

	}

	@ApiOperation(value = "根据token获取会员简略信息", notes = "获取会员简略信息", tags = {
			"我的-首页" }, authorizations = { @Authorization(value = "token") })
	@RequestMapping(value = "getProfile", method = RequestMethod.GET)
	@ResponseBody
	public ApiResult<MemberDto> getProfile(HttpServletRequest request) {
		ApiResult<MemberDto> result = new ApiResult<MemberDto>();
		Member member = getCurrentMember(request);
		member = systemService.getMember(member.getId());
		MemberDto memberDto = new MemberDto();
		BeanUtils.copyProperties(member, memberDto);
		result.setData(memberDto);
		return result;
	}

}