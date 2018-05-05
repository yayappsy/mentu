/**
 * 
 */
package com.weimhc.api.modules.dto.resp;

/**
 * 网站概况dto
 * 
 * @author lc
 * @version 2017-04-07
 */
public class StatsSiteResolutionDto extends StatsSiteDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4371093896275487489L;
	/**
	 * 屏幕分辨率
	 * 
	 */
	private String resolution;

	/**
	 * 获取屏幕分辨率
	 * 
	 * @return the resolution
	 */
	public String getResolution() {
		return resolution;
	}

	/**
	 * 设置屏幕分辨率
	 * 
	 * @param resolution
	 *            the resolution to set
	 */
	public void setResolution(String resolution) {
		this.resolution = resolution;
	}

}