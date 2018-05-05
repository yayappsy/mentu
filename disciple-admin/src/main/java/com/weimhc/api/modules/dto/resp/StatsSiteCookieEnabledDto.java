/**
 * 
 */
package com.weimhc.api.modules.dto.resp;

/**
 * 是否支持cookie Dto
 * 
 * @author lc
 * @version 2017-04-07
 */
public class StatsSiteCookieEnabledDto extends StatsSiteDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4371093896275487489L;
	/**
	 * 是否支持cookie
	 * 
	 */
	private Boolean cookieEnabled;

	/**
	 * 获取是否支持cookie
	 * 
	 * @return the cookieEnabled
	 */
	public Boolean getCookieEnabled() {
		return cookieEnabled;
	}

	/**
	 * 设置是否支持cookie
	 * 
	 * @param cookieEnabled
	 *            the cookieEnabled to set
	 */
	public void setCookieEnabled(Boolean cookieEnabled) {
		this.cookieEnabled = cookieEnabled;
	}

}