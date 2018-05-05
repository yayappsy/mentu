/**
 * 
 */
package com.weimhc.modules.ad.entity;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.javamg.common.persistence.DataEntity;

/**
 * 广告位Entity
 * 
 * @author zsm
 * @version 2017-04-01
 */
public class AdPosition extends DataEntity<AdPosition> {

	private static final long serialVersionUID = 1L;
	/**
	 * 名称
	 * 
	 */
	private String name;
	/**
	 * 代码
	 * 
	 */
	private AdPositionCode code;
	/**
	 * 描述
	 * 
	 */
	private String description;
	/**
	 * 广告类型
	 * 
	 */
	private AdType adType;
	/**
	 * 合适的高度(px)
	 * 
	 */
	private Integer properHeight;
	/**
	 * 合适的宽度(px)
	 * 
	 */
	private Integer properWidth;
	/**
	 * 广告详情数量
	 * 
	 */
	private Integer maxNumber;
	/**
	 * 是否启用
	 * 
	 */
	private Boolean isEnabled;

	public AdPosition() {
		super();
	}

	public AdPosition(String id) {
		super(id);
	}

	/**
	 * 获取名称
	 * 
	 * @return 名称
	 */
	@Length(min = 1, max = 255)
	public String getName() {
		return name;
	}

	/**
	 * 设置名称
	 * 
	 * @param name
	 *            名称
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获取代码
	 * 
	 * @return 代码
	 */
	public AdPositionCode getCode() {
		return code;
	}

	/**
	 * 设置代码
	 * 
	 * @param code
	 *            代码
	 */
	public void setCode(AdPositionCode code) {
		this.code = code;
	}

	/**
	 * 获取描述
	 * 
	 * @return 描述
	 */
	@Length(min = 0, max = 255)
	public String getDescription() {
		return description;
	}

	/**
	 * 设置描述
	 * 
	 * @param description
	 *            描述
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * 获取广告类型
	 * 
	 * @return 广告类型
	 */
	public AdType getAdType() {
		return adType;
	}

	/**
	 * 设置广告类型
	 * 
	 * @param adType
	 *            广告类型
	 */
	public void setAdType(AdType adType) {
		this.adType = adType;
	}

	/**
	 * 获取合适的高度(px)
	 * 
	 * @return 合适的高度(px)
	 */
	public Integer getProperHeight() {
		return properHeight;
	}

	/**
	 * 设置合适的高度(px)
	 * 
	 * @param properHeight
	 *            合适的高度(px)
	 */
	public void setProperHeight(Integer properHeight) {
		this.properHeight = properHeight;
	}

	/**
	 * 获取合适的宽度(px)
	 * 
	 * @return 合适的宽度(px)
	 */
	public Integer getProperWidth() {
		return properWidth;
	}

	/**
	 * 设置合适的宽度(px)
	 * 
	 * @param properWidth
	 *            合适的宽度(px)
	 */
	public void setProperWidth(Integer properWidth) {
		this.properWidth = properWidth;
	}

	/**
	 * 获取广告详情数量
	 * 
	 * @return 广告详情数量
	 */
	public Integer getMaxNumber() {
		return maxNumber;
	}

	/**
	 * 设置广告详情数量
	 * 
	 * @param maxNumber
	 *            广告详情数量
	 */
	public void setMaxNumber(Integer maxNumber) {
		this.maxNumber = maxNumber;
	}

	/**
	 * 获取是否启用
	 * 
	 * @return 是否启用
	 */
	@NotNull()
	public Boolean getIsEnabled() {
		return isEnabled;
	}

	/**
	 * 设置是否启用
	 * 
	 * @param isEnabled
	 *            是否启用
	 */
	public void setIsEnabled(Boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

}