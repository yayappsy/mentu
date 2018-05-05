package com.weimhc.framework.sms.zthysms;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Service;

import com.thinkgem.javamg.common.config.Global;
import com.thinkgem.javamg.common.security.Digests;
import com.thinkgem.javamg.common.utils.DateUtils;
import com.thinkgem.javamg.common.utils.StringUtils;
import com.weimhc.framework.http.invoker.HttpClientRestAPIInvoker;
import com.weimhc.framework.http.wrapper.ParameterWrapper;
import com.weimhc.framework.service.impl.BaseServiceImpl;
import com.weimhc.framework.sms.SMSSendService;
import com.weimhc.framework.sms.SMSSendUtils;
import com.weimhc.framework.sms.dto.AbstractSMSDto;
import com.weimhc.framework.sms.dto.SmsType;
import com.weimhc.framework.sms.dto.req.DefaultSMSDto;
import com.weimhc.framework.utils.ConstantUtils;
import com.weimhc.framework.web.utils.MessageSourceUtils;

/**
 * 
 * 助通科技短信平台 短信发送类
 *
 * @author laozh
 * @version 2017年4月27日
 */
@Service("zthySMSSendService")
public class ZthySMSSendService extends BaseServiceImpl implements SMSSendService {

	private static final String SMS_URL = Global.getConfig("sms.zthysms.url");
	private static final String USERNAME = Global.getConfig("sms.zthysms.username");
	private static final String PASSWORD = Global.getConfig("sms.zthysms.password");
	private static final String SIGNATURE = Global.getConfig("sms.zthysms.signature");

	private static final String DEFAULT_SMS = "http://www.api.zthysms.com/sendSms.do";

	String content = "【正气诚信】您的手机验证码是:885316,您当前正在做的操作是：密码找回。若非本人操作,请忽略! 短信验证码的有时间是5分钟！"; // 内容

	private ParameterWrapper createParameter(AbstractSMSDto sendDto) {
		String tkey = DateUtils.getDate("yyyyMMddHHmmss");
		ParameterWrapper query = new ParameterWrapper();
		query.addParameter("url", SMS_URL);
		query.addParameter("username", USERNAME);
		query.addParameter("password", Digests.md5(Digests.md5(PASSWORD) + tkey));
		query.addParameter("mobile", sendDto.getDestination());
		query.addParameter("tkey", tkey);
		StringBuilder content = new StringBuilder(SIGNATURE);
		if (SmsType.captcha.equals(sendDto.getSmsType())) {
			content.append(
					MessageSourceUtils.getMessage("sms.template.zthysms." + sendDto.getSmsType(),
							sendDto.getParameter().get(SmsType.captcha.name()), 5,
							MessageSourceUtils.getMessage(
									"CaptchaType." + sendDto.getParameter().get("captchaType"))));
		}
		query.addParameter("content", content.toString());
		return query;
	}

	@Override
	public boolean send() {
		DefaultSMSDto sendDto = SMSSendUtils.get();
		if (sendDto != null) {
			logger.debug("使用助通科技短信平台发送短信：" + sendDto.getContent());
			String result = HttpClientRestAPIInvoker.doGet(SMS_URL, createParameter(sendDto));
			if (StringUtils.isNotBlank(result)) {
				String[] resultArray = StringUtils.split(result, ConstantUtils.SEPARATOR_COMMA);
				logger.debug(resultArray.toString());
				/*logger.debug(
						MessageSourceUtils.getMessage("sms.resultCode.zthysms." + resultArray[0],
								resultArray[0], resultArray[1]));*/
				if (1 == NumberUtils.toInt(resultArray[0])) {
					SMSSendUtils.remove();
					return true;
				}
			}
		}
		return false;
	}

}
