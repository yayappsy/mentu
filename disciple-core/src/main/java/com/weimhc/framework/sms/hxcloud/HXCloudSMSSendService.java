package com.weimhc.framework.sms.hxcloud;

import org.springframework.stereotype.Service;

import com.weimhc.framework.service.impl.BaseServiceImpl;
import com.weimhc.framework.sms.SMSSendService;

/**
 * 
 * 发送短信类型
 *
 * @author laozh
 * @version 2017年4月27日
 */
@Service("hxCloudSMSSendService")
public class HXCloudSMSSendService extends BaseServiceImpl implements SMSSendService {

	@Override
	public boolean send() {
		return true;
	}

}
