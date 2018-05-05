/**
 * 
 */
package com.weimhc.modules.base.entity;

import java.util.Date;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.weimhc.framework.persistence.BaseNameEntity;

/**
 * 星座Entity
 * 
 * @author lc
 * @version 2016-06-24
 */
public class Constellation extends BaseNameEntity<Constellation> {

	private static final long serialVersionUID = 1L;

	/**
	 * 星座英文名称
	 * 
	 */
	private String englishName;
	/**
	 * 星座开始时间
	 * 
	 */
	private Date beginDate;
	/**
	 * 星座结束时间
	 * 
	 */
	private Date endDate;

	public Constellation() {
		super();
	}

	public Constellation(String id) {
		super(id);
	}

	/**
	 * 获取星座开始时间
	 * 
	 * @return 星座开始时间
	 */
	@JsonFormat(pattern = "YYYY-MM-dd")
	@NotNull()
	public Date getBeginDate() {
		return beginDate;
	}

	/**
	 * 设置星座开始时间
	 * 
	 * @param beginDate
	 *            星座开始时间
	 */
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	/**
	 * 获取星座结束时间
	 * 
	 * @return 星座结束时间
	 */
	@JsonFormat(pattern = "YYYY-MM-dd")
	@NotNull()
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * 设置星座结束时间
	 * 
	 * @param endDate
	 *            星座结束时间
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * 获取星座英文名称
	 * 
	 * @return the englishName
	 */
	public String getEnglishName() {
		return englishName;
	}

	/**
	 * 设置星座英文名称
	 * 
	 * @param englishName
	 *            the englishName to set
	 */
	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}

}