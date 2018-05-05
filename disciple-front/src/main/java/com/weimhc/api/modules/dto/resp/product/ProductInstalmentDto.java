/**
 * 
 */
package com.weimhc.api.modules.dto.resp.product;

import com.weimhc.framework.dto.AbstractIdDto;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * 商品分期DTO
 * 
 * @author lc
 * @version 2016-12-15
 */
public class ProductInstalmentDto extends AbstractIdDto {

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
	 * 分期金额
	 * 
	 */
	private BigDecimal price;
	/**
	 * 当前期数
	 */
	private Integer periods;
	/**
	 * 手续费
	 */
	private BigDecimal brokerage;

	/**
	 * 获取分期名称
	 * 
	 * @return the name
	 */
	@ApiModelProperty(value = "分期名称")
	public String getName() {
		return name;
	}

	/**
	 * 设置分期名称
	 * 
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获取分期描述规则
	 * 
	 * @return the description
	 */
	@ApiModelProperty(value = "分期描述规则")
	public String getDescription() {
		return description;
	}

	/**
	 * 设置分期描述规则
	 * 
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * 获取分期价格
	 * 
	 * @return the price
	 */
	@ApiModelProperty(value = "分期价格")
	public BigDecimal getPrice() {
		return price;
	}

	/**
	 * 设置分期价格
	 * 
	 * @param price
	 *            the price to set
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	/**
	 * 获取当前期数
	 * 
	 * @return the periods
	 */
	@ApiModelProperty(value = "当前期数")
	public Integer getPeriods() {
		return periods;
	}

	/**
	 * 设置当前期数
	 * 
	 * @param periods
	 *            the periods to set
	 */
	public void setPeriods(Integer periods) {
		this.periods = periods;
	}

	/**
	 * @return the brokerage
	 */
	@ApiModelProperty(value = "手续费")
	public BigDecimal getBrokerage() {
		return brokerage;
	}

	/**
	 * @param brokerage
	 *            the brokerage to set
	 */
	public void setBrokerage(BigDecimal brokerage) {
		this.brokerage = brokerage;
	}
}