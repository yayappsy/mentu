/**
 * 
 */
package com.weimhc.api.modules.dto.resp;

/**
 * 是否支持javadto
 * 
 * @author lc
 * @version 2017-04-07
 */
public class StatsSiteJavaEnabledDto extends StatsSiteDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4371093896275487489L;
	/**
	 * 是否支持java
	 * 
	 */
	private Boolean javaEnabled;

	/**
	 * 获取是否支持java
	 * 
	 * @return the javaEnabled
	 */
	public Boolean getJavaEnabled() {
		return javaEnabled;
	}

	/**
	 * 设置是否支持java
	 * 
	 * @param javaEnabled
	 *            the javaEnabled to set
	 */
	public void setJavaEnabled(Boolean javaEnabled) {
		this.javaEnabled = javaEnabled;
	}

}