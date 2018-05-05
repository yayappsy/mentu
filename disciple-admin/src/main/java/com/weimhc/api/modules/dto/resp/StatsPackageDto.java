/**
 * 
 */
package com.weimhc.api.modules.dto.resp;

import java.math.BigDecimal;

import com.weimhc.framework.dto.AbstractDto;

/**
 * 统计套餐dto
 * 
 * @author lc
 * @version 2017-04-07
 */
public class StatsPackageDto extends AbstractDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4371093896275487489L;
	/**
	 * 套餐名称
	 * 
	 */
	private String productName;
	/**
	 * 总金额
	 * 
	 */
	private BigDecimal amount;
	/**
	 * 数量
	 * 
	 */
	private Integer salesCount;

	/**
	 * @return the productName
	 */
	public String getProductName() {
		return productName;
	}

	/**
	 * @param productName
	 *            the productName to set
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}

	/**
	 * @return the amount
	 */
	public BigDecimal getAmount() {
		if (amount == null || amount.equals("")) {
			return BigDecimal.valueOf(0);
		}
		return amount;
	}

	/**
	 * @param amount
	 *            the amount to set
	 */
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	/**
	 * @return the salesCount
	 */
	public Integer getSalesCount() {
		return salesCount;
	}

	/**
	 * @param salesCount
	 *            the salesCount to set
	 */
	public void setSalesCount(Integer salesCount) {
		this.salesCount = salesCount;
	}

}