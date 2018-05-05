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
public class StatsSiteFlashVersionDto extends StatsSiteDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4371093896275487489L;
	/**
	 * flash版本
	 * 
	 */
	private String flashVersion;

	/**
	 * 获取flash版本
	 * 
	 * @return the flashVersion
	 */
	public String getFlashVersion() {
		return flashVersion;
	}

	/**
	 * 设置flash版本
	 * 
	 * @param flashVersion
	 *            the flashVersion to set
	 */
	public void setFlashVersion(String flashVersion) {
		this.flashVersion = flashVersion;
	}

}