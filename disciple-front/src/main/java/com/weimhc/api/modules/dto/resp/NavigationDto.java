/**
 * 
 */
package com.weimhc.api.modules.dto.resp;

import com.weimhc.framework.dto.AbstractIdDto;
import io.swagger.annotations.ApiModelProperty;

/**
 * 导航DTO
 * 
 * @author lc
 * @version 2016-12-15
 */
public class NavigationDto extends AbstractIdDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1095501047336970672L;

	/**
	 * 导航名称
	 * 
	 */
	private String name;

	/**
	 * 获取导航名称
	 * 
	 * @return the name
	 */
	@ApiModelProperty(value = "导航名称")
	public String getName() {
		return name;
	}

	/**
	 * 设置导航名称
	 * 
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

}