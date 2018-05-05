/**
 * 
 */
package com.weimhc.modules.member.entity;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.javamg.common.persistence.DataEntity;

/**
 * 会员等级Entity
 * @author lc
 * @version 2016-11-30
 */
public class MemberRank extends DataEntity<MemberRank> {
	
	private static final long serialVersionUID = 1L;
	/**
	 * 是否默认
	 * 
	 */		
	private Boolean isDefault;	
	/**
	 * 是否特殊
	 * 
	 */		
	private Boolean isSpecial;	
	/**
	 * 等级名称
	 * 
	 */		
	private String name;	
	/**
	 * 消耗的金额
	 * 
	 */		
	private BigDecimal amount;	
	/**
	 * 优惠比例
	 * 
	 */		
	private Double preferentia;	
	/**
	 * 会员等级使用图标
	 * 
	 */		
	private String imageUrl;	
	
	public MemberRank() {
		super();
	}

	public MemberRank(String id){
		super(id);
	}

	/**
	 * 获取是否默认
	 * 
	 * @return 是否默认
	 */	
	@NotNull()
	public Boolean getIsDefault() {
		return isDefault;
	}
	/**
	 * 设置是否默认
	 * 
	 * @param isDefault
	 *            是否默认
	 */
	public void setIsDefault(Boolean isDefault) {
		this.isDefault = isDefault;
	}
	
	/**
	 * 获取是否特殊
	 * 
	 * @return 是否特殊
	 */	
	@NotNull()
	public Boolean getIsSpecial() {
		return isSpecial;
	}
	/**
	 * 设置是否特殊
	 * 
	 * @param isSpecial
	 *            是否特殊
	 */
	public void setIsSpecial(Boolean isSpecial) {
		this.isSpecial = isSpecial;
	}
	
	/**
	 * 获取等级名称
	 * 
	 * @return 等级名称
	 */	
	@Length(min=1, max=100)
	public String getName() {
		return name;
	}
	/**
	 * 设置等级名称
	 * 
	 * @param name
	 *            等级名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * 获取消耗的金额
	 * 
	 * @return 消耗的金额
	 */	
	public BigDecimal getAmount() {
		return amount;
	}
	/**
	 * 设置消耗的金额
	 * 
	 * @param amount
	 *            消耗的金额
	 */
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
	/**
	 * 获取优惠比例
	 * 
	 * @return 优惠比例
	 */	
	@NotNull()
	public Double getPreferentia() {
		return preferentia;
	}
	/**
	 * 设置优惠比例
	 * 
	 * @param preferentia
	 *            优惠比例
	 */
	public void setPreferentia(Double preferentia) {
		this.preferentia = preferentia;
	}
	
	/**
	 * 获取会员等级使用图标
	 * 
	 * @return 会员等级使用图标
	 */	
	@Length(min=0, max=64)
	public String getImageUrl() {
		return imageUrl;
	}
	/**
	 * 设置会员等级使用图标
	 * 
	 * @param imageUrl
	 *            会员等级使用图标
	 */
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	
}