/**
 * 
 */
package com.weimhc.api.core.web.captcha;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Maps;
import com.thinkgem.javamg.common.utils.StringUtils;
import com.weimhc.api.core.dto.req.SMSCaptchaRQ;
import com.weimhc.api.core.web.ApiBaseController;
import com.weimhc.framework.dto.resp.ApiResult;
import com.weimhc.framework.service.CaptchaService;
import com.weimhc.framework.service.PictureCaptchaService;
import com.weimhc.framework.sms.SMSSendUtils;
import com.weimhc.framework.sms.dto.SmsType;
import com.weimhc.framework.sms.dto.req.DefaultSMSDto;
import com.weimhc.framework.web.utils.MessageSourceUtils;
import com.weimhc.front.modules.service.SystemService;
import com.weimhc.modules.base.utils.setting.CaptchaType;
import com.weimhc.modules.user.entity.IdentityType;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 验证码信息Controller
 * 
 * @author zsm
 * @version 2016-10-05
 */
@Api(value = "smsCaptcha")
@Controller("apiSMSCaptchaController")
@RequestMapping(value = "${apiPath}/smsCaptcha")
public class SMSCaptchaController extends ApiBaseController {

	@Autowired
	private CaptchaService sMSCaptchaService;

	@Resource
	private PictureCaptchaService pictureCaptchaService;

	@Autowired
	private SystemService systemService;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ApiOperation(value = "生成验证码", notes = "生成验证码并发送", tags = { "注册", "忘记密码" })
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public ApiResult add(@RequestBody SMSCaptchaRQ body, HttpServletRequest request) {
		ApiResult result = new ApiResult<>();
		// 验证是否已经注册
		if (CaptchaType.memberRegister.equals(body.getCaptchaType())) {
			if (!systemService.checkIdentifierCanUse(body.getMobile(), IdentityType.mobile)) {
				return ApiResult.error(getMessage("error.register.identifierExist"));
			}
		}
		if (StringUtils.isNotBlank(body.getImageCaptcha())) {
			if (!pictureCaptchaService.isValid(body.getCaptchaType(), body.getMobile(),
					body.getImageCaptcha())) {
				return ApiResult.error(-1,
						MessageSourceUtils.getMessage("error.captcha.image.invalid"));
			}
		}
		String captcha = sMSCaptchaService.generateCaptcha(body.getCaptchaType(), body.getMobile());
		Map<String, String> data = Maps.newHashMap();
		data.put("captcha", captcha);
		data.put("captchaType", body.getCaptchaType().name());
		DefaultSMSDto sendDto = new DefaultSMSDto(body.getMobile(), captcha, data);
		sendDto.setSmsType(SmsType.captcha);
		SMSSendUtils.putSMSInfo(sendDto);

		result.setData(data);
		return result;
	}

}