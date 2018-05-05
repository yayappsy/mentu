/**
* 
*/
package com.weimhc.api.modules.web.session;

import com.qq.connect.QQConnectException;
import com.qq.connect.api.OpenID;
import com.qq.connect.api.qzone.UserInfo;
import com.qq.connect.javabeans.AccessToken;
import com.qq.connect.javabeans.qzone.UserInfoBean;
import com.qq.connect.utils.QQConnectConfig;
import com.qq.connect.utils.RandomStatusGenerator;
import com.qq.connect.utils.http.HttpClient;
import com.qq.connect.utils.http.PostParameter;
import com.thinkgem.javamg.common.utils.StringUtils;
import com.thinkgem.javamg.common.utils.UserAgentUtils;
import com.weimhc.api.core.web.ApiBaseController;
import com.weimhc.api.modules.dto.resp.LoginDto;
import com.weimhc.framework.dto.resp.ApiResult;
import com.weimhc.framework.security.MemberPrincipal;
import com.weimhc.framework.service.RSAService;
import com.weimhc.framework.utils.AccessUtils;
import com.weimhc.framework.utils.AppTokenHelper;
import com.weimhc.front.core.security.UsernamePasswordToken;
import com.weimhc.front.modules.service.SystemService;
import com.weimhc.front.modules.utils.MemberUtils;
import com.weimhc.modules.member.entity.Member;
import com.weimhc.modules.member.service.MemberService;
import com.weimhc.modules.user.entity.IdentityType;
import com.weimhc.modules.user.entity.UserAuth;
import com.weimhc.modules.user.service.UserAuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.HtmlUtils;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 第三方登录qqController
 * 
 * @author zsm
 * @version 2016-10-05
 */
@Api
@Controller("apiThirdLoginQqSessionController")
@RequestMapping(value = "${apiPath}/session")
public class ThirdLoginQqSessionController extends ApiBaseController {

	@Autowired
	private SystemService systemService;

	@Resource
	private RSAService rsaService;
	@Resource
	private MemberService memberService;
	@Resource
	private UserAuthService userAuthService;

	protected HttpClient qqConnectClient = new HttpClient();

	public String getAuthorizeURL(ServletRequest request) throws QQConnectException {
		String state = RandomStatusGenerator.getUniqueState();
		((HttpServletRequest) request).getSession().setAttribute("qq_connect_state", state);
		String scope = QQConnectConfig.getValue("scope");
		if ((scope != null) && (!(scope.equals("")))) {
			return getAuthorizeURL("code", state, scope);
		}
		String encodeUrl = "";
		try {
			encodeUrl = URLEncoder.encode(QQConnectConfig.getValue("redirect_URI").trim(), "UTF-8");

		} catch (UnsupportedEncodingException neverHappen) {
		}
		logger.debug(encodeUrl);

		return QQConnectConfig.getValue("authorizeURL").trim() + "?client_id="
				+ QQConnectConfig.getValue("app_ID").trim() + "&redirect_uri=" + encodeUrl
				+ "&response_type=" + "code" + "&state=" + state;
	}

	/** @deprecated */
	@Deprecated
	public String getAuthorizeURL(String response_type, String state, String scope)
			throws QQConnectException {
		String encodeUrl = "";
		try {
			encodeUrl = URLEncoder.encode(QQConnectConfig.getValue("redirect_URI").trim(), "UTF-8");

		} catch (UnsupportedEncodingException neverHappen) {
		}
		return QQConnectConfig.getValue("authorizeURL").trim() + "?client_id="
				+ QQConnectConfig.getValue("app_ID").trim() + "&redirect_uri=" + encodeUrl
				+ "&response_type=" + response_type + "&state=" + state + "&scope=" + scope;
	}

	@RequestMapping(value = "/qq/pc/list", method = RequestMethod.GET)
	public void list(HttpServletResponse response, HttpServletRequest request)
			throws IOException, QQConnectException {
		response.setContentType("text/html;charset=utf-8");
		logger.debug(getAuthorizeURL(request));
		response.sendRedirect(getAuthorizeURL(request));
	}

	@ApiOperation(value = "QQ网页登录", notes = "QQ网页登录获取用户信息", tags = { "第三方登录" })
	@RequestMapping(value = "/qq/pc/auth", method = RequestMethod.GET)
	@ResponseBody
	public ApiResult<LoginDto> qqauth(String code, String state, String shareSoureId,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, QQConnectException {
		ApiResult<LoginDto> apiResult = new ApiResult<>();
		String accessToken = null, openID = null;
		UserInfoBean user = null;
		try {
			long tokenExpireIn = 0L;

			// 第一步-- 得到token类
			AccessToken accessTokenObj = this.getAccessTokenByRequest(request);
			logger.debug("token类" + accessTokenObj);
			if (accessTokenObj.getAccessToken().equals("")) {
				// 我们的网站被CSRF攻击了或者用户取消了授权
				// 做一些数据统计工作
				logger.debug("没有获取到响应参数");
			} else {
				accessToken = accessTokenObj.getAccessToken();
				tokenExpireIn = accessTokenObj.getExpireIn();

				request.getSession().setAttribute("demo_access_token", accessToken);
				request.getSession().setAttribute("demo_token_expirein",
						String.valueOf(tokenExpireIn));

				// 利用获取到的accessToken 去获取当前用的openid -------- start
				OpenID openIDObj = new OpenID(accessToken);
				openID = openIDObj.getUserOpenID();
				UserInfo qzoneUserInfo = new UserInfo(accessToken, openID);
				user = qzoneUserInfo.getUserInfo();
				if (user.getRet() == 0) {
					logger.debug(user.getNickname() + "<br/>");
					logger.debug(user.getGender() + "<br/>");
				} else {
					logger.debug("很抱歉，我们没能正确获取到您的信息，原因是： " + user.getMsg());
				}

			}
		} catch (QQConnectException e) {
		}

		if (user != null) {
			UserAuth userAuth = new UserAuth();
			userAuth.setIdentityType(IdentityType.qq);
			userAuth.setIdentifier(openID);
			userAuth = userAuthService.getEntity(userAuth);
			Member member = new Member();

			if (userAuth == null) {
				member.setNickname(HtmlUtils.htmlEscape(user.getNickname()));
				if (StringUtils.isNotBlank(user.getGender()) && user.getGender().equals("男")) {
					member.setGender("1");
				} else {
					member.setGender("2");
				}
				member.setAvatar(user.getAvatar().getAvatarURL50());
				member.setRegisterIp(AccessUtils.getIpAddress(request));
				member.setNewPassword(openID);
				systemService.saveMember(member, true, IdentityType.qq);

				LoginDto loginDto = new LoginDto();
				BeanUtils.copyProperties(member, loginDto);
				apiResult.setData(loginDto);
			} else {
				LoginDto loginDto = new LoginDto();
				member = memberService.get(userAuth.getUserInfo().getId());
				UserAuth searchUserAuth = new UserAuth();
				searchUserAuth.setUserInfo(userAuth.getUserInfo());
				searchUserAuth.setIdentityType(IdentityType.mobile);
				Boolean hasMobileIdentity = userAuthService.countIdentity(searchUserAuth);
				if (hasMobileIdentity) {
					UsernamePasswordToken uPasswordToken = new UsernamePasswordToken();
					uPasswordToken.setUsername(openID);
					uPasswordToken.setPassword(openID.toCharArray());
					uPasswordToken.setMobileLogin(UserAgentUtils.isMobileOrTablet(request));
					uPasswordToken.setIsNeedCaptcha(false);

					uPasswordToken.setIdentityType(IdentityType.qq);
					if (logger.isDebugEnabled()) {
						logger.debug("是否手机或平板登录： " + UserAgentUtils.isMobileOrTablet(request));
						logger.debug("UserAgent： " + UserAgentUtils.getUserAgent(request));
					}

					String message = MemberUtils.subjectLogin(uPasswordToken);

					if (StringUtils.isNotBlank(message)) {
						return ApiResult.error(getMessage(message));
					}
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

		}
		return apiResult;
	}

	/**
	 * 利用获取到的accessToken,openid 去获取用户在Qzone的昵称等信息
	 * 
	 * @param request
	 * @return
	 */
	public UserInfoBean createUserInfo(HttpServletRequest request) {
		UserInfoBean userInfoBean = null;
		try {

			String accessToken = null, openID = null;
			long tokenExpireIn = 0L;

			// 第一步-- 得到token类
			AccessToken accessTokenObj = getAccessTokenByRequest(request);
			if (accessTokenObj.getAccessToken().equals("")) {
				// 我们的网站被CSRF攻击了或者用户取消了授权
				// 做一些数据统计工作
				logger.debug("没有获取到响应参数");
			} else {
				accessToken = accessTokenObj.getAccessToken();
				tokenExpireIn = accessTokenObj.getExpireIn();

				request.getSession().setAttribute("demo_access_token", accessToken);
				request.getSession().setAttribute("demo_token_expirein",
						String.valueOf(tokenExpireIn));

				// 利用获取到的accessToken 去获取当前用的openid -------- start
				OpenID openIDObj = new OpenID(accessToken);
				openID = openIDObj.getUserOpenID();

				logger.debug("欢迎你，代号为 " + openID + " 的用户!");
				request.getSession().setAttribute("demo_openid", openID);
				// // 利用获取到的accessToken 去获取当前用户的openid --------- end

				logger.debug(
						"<p> start -----------------------------------利用获取到的accessToken,openid 去获取用户在Qzone的昵称等信息 ---------------------------- start </p>");
				UserInfo qzoneUserInfo = new UserInfo(accessToken, openID);
				userInfoBean = qzoneUserInfo.getUserInfo();
				if (userInfoBean.getRet() == 0) {
					logger.debug(userInfoBean.getNickname() + "<br/>");
					logger.debug(userInfoBean.getGender() + "<br/>");
				} else {
					logger.debug("很抱歉，我们没能正确获取到您的信息，原因是： " + userInfoBean.getMsg());
				}
				logger.debug(
						"<p> end -----------------------------------利用获取到的accessToken,openid 去获取用户在Qzone的昵称等信息 ---------------------------- end </p>");

			}
		} catch (QQConnectException e) {
		}

		return userInfoBean;
	}

	private String[] extractionAuthCodeFromUrl(String url) throws QQConnectException {
		if (url == null) {
			throw new QQConnectException("you pass a null String object");
		}
		Matcher m = Pattern.compile("code=(\\w+)&state=(\\w+)&?").matcher(url);
		String authCode = "";
		String state = "";
		if (m.find()) {
			authCode = m.group(1);
			state = m.group(2);
		}

		return new String[] { authCode, state };
	}

	private AccessToken getAccessTokenByRequest(ServletRequest request) throws QQConnectException {
		String queryString = ((HttpServletRequest) request).getQueryString();
		logger.debug("queryString" + queryString);
		if (queryString == null) {
			return new AccessToken();
		}
		String state = (String) ((HttpServletRequest) request).getSession()
				.getAttribute("qq_connect_state");
		logger.debug("state:" + state);
		if ((state == null) || (state.equals(""))) {
			return new AccessToken();
		}

		String[] authCodeAndState = extractionAuthCodeFromUrl(queryString);
		String returnState = authCodeAndState[1];
		String returnAuthCode = authCodeAndState[0];

		AccessToken accessTokenObj = null;
		logger.debug("returnAuthCode:" + returnAuthCode);
		logger.debug("returnState:" + returnState);
		if ((returnState.equals("")) || (returnAuthCode.equals(""))) {
			accessTokenObj = new AccessToken();
		} else if (!(state.equals(returnState))) {
			accessTokenObj = new AccessToken();
		} else
			logger.debug("accessTokenURL:" + QQConnectConfig.getValue("accessTokenURL"));
		logger.debug("client_id:" + QQConnectConfig.getValue("app_ID"));
		logger.debug("client_secret:" + QQConnectConfig.getValue("app_KEY"));
		logger.debug("redirect_uri:" + QQConnectConfig.getValue("redirect_URI"));
		accessTokenObj = new AccessToken(qqConnectClient.get(
				QQConnectConfig.getValue("accessTokenURL"),
				new PostParameter[] {
						new PostParameter("client_id", QQConnectConfig.getValue("app_ID")),
						new PostParameter("client_secret", QQConnectConfig.getValue("app_KEY")),
						new PostParameter("grant_type", "authorization_code"),
						new PostParameter("code", returnAuthCode), new PostParameter("redirect_uri",
								QQConnectConfig.getValue("redirect_URI")) }));
		logger.debug("accessTokenObj:" + accessTokenObj);

		return accessTokenObj;
	}
}