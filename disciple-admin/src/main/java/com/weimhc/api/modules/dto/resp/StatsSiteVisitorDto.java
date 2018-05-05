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
public class StatsSiteVisitorDto extends StatsSiteDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4371093896275487489L;

	/**
	 * 是否新访客
	 * 
	 */
	private Boolean ifNewVisitor;

	/**
	 * 获取是否新访客
	 * 
	 * @return the ifNewVisitor
	 */
	public Boolean getIfNewVisitor() {
		return ifNewVisitor;
	}

	/**
	 * 设置是否新访客
	 * 
	 * @param ifNewVisitor
	 *            the ifNewVisitor to set
	 */
	public void setIfNewVisitor(Boolean ifNewVisitor) {
		this.ifNewVisitor = ifNewVisitor;
	}

}