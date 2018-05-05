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
public class AppSettingDto extends AbstractDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = -62122814897302046L;

	/**
	 * 图片地址前缀
	 * 
	 */
	private String imageUrlrefix;

	/**
	 * 返回 图片地址前缀
	 * 
	 * @return the imageUrlrefix
	 */
	@ApiModelProperty("图片地址前缀")
	public String getImageUrlrefix() {
		return imageUrlrefix;
	}

	/**
	 * 设置 图片地址前缀
	 * 
	 * @param imageUrlrefix
	 *            the imageUrlrefix to set
	 */
	public void setImageUrlrefix(String imageUrlrefix) {
		this.imageUrlrefix = imageUrlrefix;
	}

}