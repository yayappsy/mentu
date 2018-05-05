/**
 * 
 */
package com.weimhc.api.modules.dto.resp;

/**
 * 屏幕颜色dto
 * 
 * @author lc
 * @version 2017-04-07
 */
public class StatsSiteColorDto extends StatsSiteDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4371093896275487489L;
	/**
	 * 屏幕颜色
	 * 
	 */
	private Integer colorDepth;

	/**
	 * 获取屏幕颜色
	 * 
	 * @return the colorDepth
	 */
	public Integer getColorDepth() {
		return colorDepth;
	}

	/**
	 * 设置屏幕颜色
	 * 
	 * @param colorDepth
	 *            the colorDepth to set
	 */
	public void setColorDepth(Integer colorDepth) {
		this.colorDepth = colorDepth;
	}

}