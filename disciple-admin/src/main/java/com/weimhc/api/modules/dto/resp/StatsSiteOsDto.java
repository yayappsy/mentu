/**
 * 
 */
package com.weimhc.api.modules.dto.resp;

/**
 * 网络设备类型dto
 * 
 * @author lc
 * @version 2017-04-07
 */
public class StatsSiteOsDto extends StatsSiteDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4371093896275487489L;
	/**
	 * 操作系统
	 * 
	 */
	private String os;
	/**
	 * 设备类型
	 * 
	 */
	private String deviceType;

	/**
	 * 获取操作系统
	 * 
	 * @return the os
	 */
	public String getOs() {
		return os;
	}

	/**
	 * 设置操作系统
	 * 
	 * @param os
	 *            the os to set
	 */
	public void setOs(String os) {
		this.os = os;
	}

	/**
	 * 获取设备类型
	 * 
	 * @return the deviceType
	 */
	public String getDeviceType() {
		return deviceType;
	}

	/**
	 * 设置设备类型
	 * 
	 * @param deviceType
	 *            the deviceType to set
	 */
	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

}