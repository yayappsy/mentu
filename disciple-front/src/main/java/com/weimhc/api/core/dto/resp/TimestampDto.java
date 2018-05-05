/**
 * 
 */
package com.weimhc.api.core.dto.resp;

import com.weimhc.framework.dto.AbstractDto;

import io.swagger.annotations.ApiModelProperty;

/**
 * 广告返回数据
 * 
 * @author laoz
 * @version 2016-06-07
 */
public class TimestampDto extends AbstractDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = -62122814897302046L;

	/**
	 * 时间戳
	 * 
	 */
	private long timestamp;

	@ApiModelProperty("服务器时间戳")
	public long getTimestamp() {
		return System.currentTimeMillis();
	}

}