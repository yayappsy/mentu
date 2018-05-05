/**
 * 
 */
package com.weimhc.api.modules.dto.resp;

/**
 * 网络提供商dto
 * 
 * @author lc
 * @version 2017-04-07
 */
public class StatsSiteIspDto extends StatsSiteDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4371093896275487489L;
	/**
	 * 网络提供商
	 * 
	 */
	private String isp;

	/**
	 * 获取网络提供商
	 * 
	 * @return the isp
	 */
	public String getIsp() {
		return isp;
	}

	/**
	 * 设置网络提供商
	 * 
	 * @param isp
	 *            the isp to set
	 */
	public void setIsp(String isp) {
		this.isp = isp;
	}

}