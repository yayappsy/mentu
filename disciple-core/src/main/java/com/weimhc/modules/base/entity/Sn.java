/**
 * 
 */
package com.weimhc.modules.base.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.javamg.common.persistence.DataEntity;

/**
 * 编号Entity 编号生成规格，前缀+因数+日期+lastValue
 * 
 * @author zsm
 * @version 2016-02-01
 */
public class Sn extends DataEntity<Sn> {

	private static final long serialVersionUID = 1L;

	private String lastValue; // 最后值
	private SnType snType; // 编号类型
	private String prefix; // 前缀
	private String factor;//因数

	public Sn() {
		super();
	}

	public Sn(String id) {
		super(id);
	}

	public Sn(SnType snType) {
		super();
		this.snType = snType;
	}

	/**
	 * 获取最后值
	 * 
	 * @return 最后值
	 */
	@Length(min = 1, max = 64)
	public String getLastValue() {
		return lastValue;
	}

	/**
	 * 设置最后值
	 * 
	 * @param lastValue
	 *            最后值
	 */
	public void setLastValue(String lastValue) {
		this.lastValue = lastValue;
	}

	/**
	 * 获取编号类型
	 * 
	 * @return 编号类型
	 */
	public SnType getSnType() {
		return snType;
	}

	/**
	 * 设置编号类型
	 * 
	 * @param snType
	 *            编号类型
	 */
	public void setSnType(SnType snType) {
		this.snType = snType;
	}

	/**
	 * 获取前缀
	 * 
	 * @return the prefix
	 */
	public String getPrefix() {
		return prefix;
	}

	/**
	 * 设置前缀
	 * 
	 * @param prefix
	 *            the prefix to set
	 */
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	/**
	 * 获取因数，生成编号使用
	 * 
	 * @return the factor
	 */
	public String getFactor() {
		return factor;
	}

	/**
	 * 设置因数，生成编号使用
	 * 
	 * @param factor
	 *            the factor to set
	 */
	public void setFactor(String factor) {
		this.factor = factor;
	}

}