/**
 * 
 */
package com.weimhc.api.modules.dto.resp;

import com.weimhc.framework.dto.AbstractIdDto;
import io.swagger.annotations.ApiModelProperty;

/**
 * 企业DTO
 * 
 * @author lc
 * @version 2016-12-15
 */
public class ArticleCategoryDto extends AbstractIdDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1095501047336970672L;

	/**
	 * 文章分类名称
	 * 
	 */
	private String name;

	/**
	 * 获取文章分类名称
	 * 
	 * @return the name
	 */
	@ApiModelProperty(value = "分类名称")
	public String getName() {
		return name;
	}

	/**
	 * 设置文章分类名称
	 * 
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

}