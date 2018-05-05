/**
* 
*/
package com.weimhc.api.modules.web.session;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.thinkgem.javamg.common.utils.StringUtils;
import com.thinkgem.javamg.common.utils.UserAgentUtils;
import com.weimhc.api.core.web.ApiBaseController;
import com.weimhc.api.modules.dto.resp.LoginDto;
import com.weimhc.framework.dto.resp.ApiResult;
import com.weimhc.framework.security.MemberPrincipal;
import com.weimhc.framework.service.PictureCaptchaService;
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
import io.swagger.annotations.ApiParam;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 第三方登录微信Controller
 * 
 * @author zsm
 * @version 2016-10-05
 */
@Api
@Controller("apiThirdLoginWxSessionController")
@RequestMapping(value = "${apiPath}/session")
public class ThirdLoginWxSessionController extends ApiBaseController {

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

	private static String OPEN_APP_ID = "wxef1db69c43f23bec";

	private static String OPEN_APP_SECRET = "e2423bbfe81e8b55e94b3538ad5e7ab2";

	@ApiOperation(value = "微信网页登录", notes = "微信网页登录获取用户信息", tags = { "第三方登录" })
	@RequestMapping(value = "/weixin/pc/auth", method = RequestMethod.GET)
	@ResponseBody
	public ApiResult<LoginDto> auth(@ApiParam(value = "code") @RequestParam String code,
			@ApiParam(value = "state") @RequestParam String state, HttpServletRequest request,
			HttpServletResponse response) {
		ApiResult<LoginDto> apiResult = new ApiResult<>();
		Map<String, String> result = getUserInfoAccessToken(code);// 通过这个code获取access_token
		String openId = result.get("openid");

		if (StringUtils.isNotEmpty(openId)) {

			logger.info("try getting user info. [openid={}]", openId);
			Map<String, String> userInfo = this.getUserInfo(result.get("access_token"), openId);// 使用access_token获取用户信息
			logger.info("received user info. [result={}]", userInfo);
			UserAuth userAuth = new UserAuth();
			userAuth.setIdentifier(userInfo.get("unionid"));
			logger.info("unionid: ", userInfo.get("unionid"));
			userAuth.setIdentityType(IdentityType.wechat);
			userAuth = userAuthService.getEntity(userAuth);
			Member member = new Member();

			if (userAuth == null) {
				member.setNickname(userInfo.get("nickname"));
				if (StringUtils.isNotBlank(userInfo.get("sex")) && result.get("sex").equals("男")) {
					member.setGender("1");
				} else {
					member.setGender("2");
				}
				member.setAvatar(userInfo.get("headimgurl"));
				member.setRegisterIp(AccessUtils.getIpAddress(request));
				member.setNewPassword(userInfo.get("unionid"));
				member.setThirdPartUserId(userInfo.get("unionid"));
				systemService.saveMember(member, true, IdentityType.wechat);

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
					uPasswordToken.setUsername(userInfo.get("unionid"));
					uPasswordToken.setPassword(userInfo.get("unionid").toCharArray());
					uPasswordToken.setMobileLogin(UserAgentUtils.isMobileOrTablet(request));
					uPasswordToken.setIsNeedCaptcha(false);

					uPasswordToken.setIdentityType(IdentityType.wechat);
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

		} else {
			logger.info("没有获取到openid");
		}
		return apiResult;
	}

	/**
	 * 获取请求用户信息的access_token
	 *
	 * @param code
	 * @return
	 */
	public Map<String, String> getUserInfoAccessToken(String code) {
		JsonObject object = null;
		Map<String, String> data = new HashMap<String, String>();
		try {
			String url = String.format(
					"https://api.weixin.qq.com/sns/oauth2/access_token?appid=%s&secret=%s&code=%s&grant_type=authorization_code",
					OPEN_APP_ID, OPEN_APP_SECRET, code);
			logger.info("request accessToken from url: {}", url);
			CloseableHttpClient httpClient = HttpClientBuilder.create().build();
			HttpGet httpGet = new HttpGet(url);
			HttpResponse httpResponse = httpClient.execute(httpGet);
			HttpEntity httpEntity = httpResponse.getEntity();
			String tokens = EntityUtils.toString(httpEntity, "utf-8");
			Gson token_gson = new Gson();
			object = token_gson.fromJson(tokens, JsonObject.class);
			logger.info("request accessToken success. [result={}]", object);
			data.put("openid", object.get("openid").toString().replaceAll("\"", ""));
			data.put("access_token", object.get("access_token").toString().replaceAll("\"", ""));
		} catch (Exception ex) {
			logger.error("fail to request wechat access token. [error={}]", ex);
		}
		return data;
	}

	/**
	 * 获取微信用户信息
	 *
	 * @param accessToken
	 * @param openId
	 * @return
	 */
	private Map<String, String> getUserInfo(String accessToken, String openId) {
		Map<String, String> data = new HashMap<String, String>();
		String url = "https://api.weixin.qq.com/sns/userinfo?access_token=" + accessToken
				+ "&openid=" + openId + "&lang=zh_CN";
		logger.info("request user info from url: {}", url);
		JsonObject userInfo = null;
		try {
			CloseableHttpClient httpClient = HttpClientBuilder.create().build();
			HttpGet httpGet = new HttpGet(url);
			HttpResponse httpResponse = httpClient.execute(httpGet);
			HttpEntity httpEntity = httpResponse.getEntity();
			String response = EntityUtils.toString(httpEntity, "utf-8");
			Gson token_gson = new Gson();
			userInfo = token_gson.fromJson(response, JsonObject.class);
			logger.info("get userinfo success. [result={}]", userInfo);
			data.put("openid", userInfo.get("openid").toString().replaceAll("\"", ""));
			data.put("nickname", userInfo.get("nickname").toString().replaceAll("\"", ""));
			data.put("city", userInfo.get("city").toString().replaceAll("\"", ""));
			data.put("province", userInfo.get("province").toString().replaceAll("\"", ""));
			data.put("country", userInfo.get("country").toString().replaceAll("\"", ""));
			data.put("headimgurl", userInfo.get("headimgurl").toString().replaceAll("\"", ""));
			data.put("unionid", userInfo.get("unionid").toString().replaceAll("\"", ""));
		} catch (Exception ex) {
			logger.error("fail to request wechat user info. [error={}]", ex);
		}
		return data;
	}

}