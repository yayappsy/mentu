
package com.weimhc.framework.sms.dto.req;

import java.util.Map;

import com.weimhc.framework.sms.dto.AbstractSMSDto;

/**
 * 发送短信内容
 * 
 * @author szuo
 * @version 2016年6月27日
 */
public class DefaultSMSDto extends AbstractSMSDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DefaultSMSDto() {
		super();
	}

	public DefaultSMSDto(String mobile, String content) {
		super(mobile, content);

	}

	public DefaultSMSDto(String mobile, String content, Map<String, String> data) {
		super(mobile, content, data);
	}

}
