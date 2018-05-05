/**
 * 
 */
package com.weimhc.framework.dto.resp;

import com.weimhc.framework.dto.AbstractIdDto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 基础数据 DTO
 * 
 * @author zsm
 * @version 2016-10-05
 */
@ApiModel
public class BaseNameDto extends AbstractIdDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1095501047336970672L;
	/**
	 * 名称
	 * 
	 */
	private String name;
	/**
	 * 描述
	 * 
	 */
	private String description;

	/**
	 * 获取名称
	 * 
	 * @return the name
	 */
	@ApiModelProperty(value = "名称")
	public String getName() {
		return name;
	}

	/**
	 * 设置疾病名称
	 * 
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获取描述
	 * 
	 * @return the description
	 */
	@ApiModelProperty(value = "描述")
	public String getDescription() {
		return description;
	}

	/**
	 * 设置描述
	 * 
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

}