/**
 * 
 */
package com.weimhc.api.modules.dto.req;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.weimhc.framework.dto.req.AbstractRQ;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单分期 请求
 * 
 * @author zsm
 * @version 2016-10-05
 */
public class OrderInstalmentRQ extends AbstractRQ {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 分期金额
	 * 
	 */
	private BigDecimal price;
	/**
	 * 当前期数
	 * 
	 */
	private Integer periods;
	/**
	 * 是否已经还请
	 * 
	 */
	private boolean isOver;
	/**
	 * 到期时间
	 * 
	 */
	private Date expire;

	/**
	 * 获取分期金额
	 * 
	 * @return the price
	 */
	@ApiModelProperty(value = "分期金额")
	public BigDecimal getPrice() {
		return price;
	}

	/**
	 * 设置分期金额
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
	 * 获取是否已经还请
	 * 
	 * @return the isOver
	 */
	@ApiModelProperty(value = "是否已经还请")
	public boolean isOver() {
		return isOver;
	}

	/**
	 * 设置是否已经还请
	 * 
	 * @param isOver
	 *            the isOver to set
	 */
	public void setOver(boolean isOver) {
		this.isOver = isOver;
	}

	/**
	 * 获取到期时间
	 * 
	 * @return the expire
	 */
	@ApiModelProperty(value = "到期时间")
	@JsonFormat(pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public Date getExpire() {
		return expire;
	}

	/**
	 * 设置到期时间
	 * 
	 * @param expire
	 *            the expire to set
	 */
	public void setExpire(Date expire) {
		this.expire = expire;
	}

}