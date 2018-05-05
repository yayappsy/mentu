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
public class StatsSiteBrowserDto extends StatsSiteDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4371093896275487489L;
	/**
	 * 浏览器名称
	 * 
	 */
	private String browserName;
	/**
	 * 浏览器类型
	 * 
	 */
	private String browserType;

	/**
	 * 获取浏览器名称
	 * 
	 * @return the browserName
	 */
	public String getBrowserName() {
		return browserName;
	}

	/**
	 * 设置浏览器名称
	 * 
	 * @param browserName
	 *            the browserName to set
	 */
	public void setBrowserName(String browserName) {
		this.browserName = browserName;
	}

	/**
	 * 获取浏览器类型
	 * 
	 * @return the browserType
	 */
	public String getBrowserType() {
		return browserType;
	}

	/**
	 * 设置浏览器类型
	 * 
	 * @param browserType
	 *            the browserType to set
	 */
	public void setBrowserType(String browserType) {
		this.browserType = browserType;
	}

}