/**
* 
*/
package com.weimhc.api.modules.web.session;

import com.thinkgem.javamg.common.utils.StringUtils;
import com.thinkgem.javamg.common.utils.UserAgentUtils;
import com.weimhc.api.core.web.ApiBaseController;
import com.weimhc.api.modules.dto.req.BaseLoginRQ;
import com.weimhc.api.modules.dto.resp.LoginDto;
import com.weimhc.framework.dto.resp.ApiResult;
import com.weimhc.framework.security.MemberPrincipal;
import com.weimhc.framework.service.PictureCaptchaService;
import com.weimhc.framework.service.RSAService;
import com.weimhc.framework.utils.AppTokenHelper;
import com.weimhc.framework.web.utils.MessageSourceUtils;
import com.weimhc.front.core.security.UsernamePasswordToken;
import com.weimhc.front.modules.service.SystemService;
import com.weimhc.front.modules.utils.MemberUtils;
import com.weimhc.modules.member.entity.Member;
import com.weimhc.modules.member.service.MemberService;
import com.weimhc.modules.user.dao.UserInfoDao;
import com.weimhc.modules.user.service.UserAuthService;
import com.weimhc.modules.user.service.UserInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 小区信息Controller
 * 
 * @author zsm
 * @version 2016-10-05
 */
@Api
@Controller("apiSessionController")
@RequestMapping(value = "${apiPath}/session")
public class SessionController extends ApiBaseController {

	@Autowired
	private SystemService systemService;

	@Resource
	private RSAService rsaService;
	@Resource
	private MemberService memberService;
	@Resource
	private UserAuthService userAuthService;
	@Resource
	private PictureCaptchaService pictureCaptchaService;

	@Autowired
	private UserInfoDao userInfoDao;

	/**
	 * 
	 * 登录提交
	 * 
	 * <p>
	 * 手机app登录不需要验证码
	 * </p>
	 */
	@ApiOperation(value = "用户登录", notes = "用户登录", tags = { "登录退出" })
	@RequestMapping(value = "add", method = RequestMethod.POST)
	@ResponseBody
	public ApiResult<LoginDto> ajaxLogin(@RequestBody BaseLoginRQ body, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		UsernamePasswordToken uPasswordToken = new UsernamePasswordToken();
		uPasswordToken.setUsername(body.getIdentifier());
		uPasswordToken.setPassword(body.getPassword().toCharArray());
		uPasswordToken.setMobileLogin(UserAgentUtils.isMobileOrTablet(request));
		uPasswordToken.setIsNeedCaptcha(false);
		uPasswordToken.setIdentityType(body.getIdentityType());
		if (logger.isDebugEnabled()) {
			logger.debug("是否手机或平板登录： " + UserAgentUtils.isMobileOrTablet(request));
			logger.debug("UserAgent： " + UserAgentUtils.getUserAgent(request));
		}
		String message = MemberUtils.subjectLogin(uPasswordToken);

		if (StringUtils.isNotBlank(message)) {
			return ApiResult.error(getMessage(message));
		}
		Member member = systemService.getByIdentifier(body.getIdentifier(), body.getIdentityType());
		String userId = member.getId();
		String type = userInfoDao.getType(userId);
		MemberPrincipal memberPrincipal = new MemberPrincipal(member, true);
		String token = AppTokenHelper.generateToken(memberPrincipal);
		ApiResult<LoginDto> apiResult = new ApiResult<>();
		if (type.equals("3")) {
			type = "2";
		}
		if (!type.equals(body.getType())) {
			apiResult.setCode(-1);
			if (body.getType().equals("1")) {
				apiResult.setMessage("请前往企业入口登录！");
			} else {
				apiResult.setMessage("请前往用户入口登录!");
			}
			return apiResult;
		}
		LoginDto loginDto = new LoginDto();
		loginDto.setType(type);
		BeanUtils.copyProperties(member, loginDto);
		loginDto.setToken(token);
		apiResult.setData(loginDto);
		return apiResult;

	}

	@ApiOperation(value = "退出", notes = "退出", tags = { "登录退出" }, authorizations = {
			@Authorization(value = "token") })
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	@ResponseBody
	public ApiResult<?> logout(HttpServletRequest request) {
		ApiResult<?> apiResult = new ApiResult<>();
		String token = getToken(request);
		if (StringUtils.isNotBlank(token)) {
			Member member = MemberUtils.getMemberByToken(token);
			MemberUtils.clearCache(member);
			MemberUtils.getSubject().logout();
			AppTokenHelper.clearToken(token);
		}
		return apiResult;
	}

	@ApiOperation(value = "检测token是否过期", notes = "检测token是否过期", tags = {
			"登录退出" }, authorizations = { @Authorization(value = "token") })
	@RequestMapping(value = "/check", method = { RequestMethod.GET })
	@ResponseBody
	public ApiResult<?> check(HttpServletRequest request) {
		ApiResult<?> apiResult = new ApiResult<>();
		String token = getToken(request);
		if (StringUtils.isBlank(token) || !AppTokenHelper.valid(token)) {
			apiResult = ApiResult.error(-3,
					MessageSourceUtils.getMessage("error.common.tokenExpired"));
		}
		return apiResult;
	}

}