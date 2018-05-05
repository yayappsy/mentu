package com.weimhc.api.test.dto;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 
 * @author laozh
 *
 */
@ApiModel
public class CityDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -790344034512686049L;

	/**
	 * 城市id
	 */
	private String id;

	/**
	 * 城市名称
	 */
	private String name;

	@ApiModelProperty
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@ApiModelProperty
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
