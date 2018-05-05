package com.weimhc.framework.sms.hxt;

import org.springframework.stereotype.Service;

import com.thinkgem.javamg.common.config.Global;
import com.weimhc.framework.service.impl.BaseServiceImpl;
import com.weimhc.framework.sms.SMSSendService;
import com.weimhc.framework.sms.SMSSendUtils;
import com.weimhc.framework.sms.dto.req.DefaultSMSDto;

/**
 * 
 * 发送短信类型
 *
 * @author laozh
 * @version 2017年4月27日
 */
@Service("hxSMSSendService")
public class HxSMSSendService extends BaseServiceImpl implements SMSSendService {

	private static final String SMS_URL = Global.getConfig("hxt.api.sms.url");
	private static final String STATUS_API_URL = Global.getConfig("hxt.api.statusApi.url");
	private static final String CALL_API_URL = Global.getConfig("hxt.api.callApi.url");
	private static final String USER_ID = Global.getConfig("hxt.userId");
	private static final String ACCOUNT = Global.getConfig("hxt.account");
	private static final String PASSWORD = Global.getConfig("hxt.password");

	@Override
	public boolean send() {
		DefaultSMSDto sendInfo = SMSSendUtils.get();
		if (sendInfo != null) {
		}
		return true;
	}

}
