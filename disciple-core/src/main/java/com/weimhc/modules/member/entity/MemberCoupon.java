/**
 * 
 */
package com.weimhc.modules.member.entity;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.javamg.common.persistence.DataEntity;
import com.weimhc.modules.coupon.entity.Coupon;

/**
 * 学员优惠券Entity
 * 
 * @author lc
 * @version 2017-06-07
 */
public class MemberCoupon extends DataEntity<MemberCoupon> {

	private static final long serialVersionUID = 1L;
	/**
	 * 优惠券
	 * 
	 */
	private Coupon coupon;
	/**
	 * 学员
	 * 
	 */
	private Member student;
	/**
	 * 代码
	 * 
	 */
	private String code;
	/**
	 * 是否使用
	 * 
	 */
	private Boolean isUsed;
	/**
	 * 使用时间
	 * 
	 */
	private Date usedDate;

	public MemberCoupon() {
		super();
	}

	public MemberCoupon(String id) {
		super(id);
	}

	/**
	 * 获取优惠券
	 * 
	 * @return 优惠券
	 */
	public Coupon getCoupon() {
		return coupon;
	}

	/**
	 * 设置优惠券
	 * 
	 * @param coupon
	 *            优惠券
	 */
	public void setCoupon(Coupon coupon) {
		this.coupon = coupon;
	}

	/**
	 * 获取学员
	 * 
	 * @return 学员
	 */
	public Member getStudent() {
		return student;
	}

	/**
	 * 设置学员
	 * 
	 * @param student
	 *            学员
	 */
	public void setStudent(Member student) {
		this.student = student;
	}

	/**
	 * 获取代码
	 * 
	 * @return 代码
	 */
	@Length(min = 1, max = 100)
	public String getCode() {
		return code;
	}

	/**
	 * 设置代码
	 * 
	 * @param code
	 *            代码
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * 获取是否使用
	 * 
	 * @return 是否使用
	 */
	@NotNull()
	public Boolean getIsUsed() {
		return isUsed;
	}

	/**
	 * 设置是否使用
	 * 
	 * @param isUsed
	 *            是否使用
	 */
	public void setIsUsed(Boolean isUsed) {
		this.isUsed = isUsed;
	}

	/**
	 * 获取使用时间
	 * 
	 * @return 使用时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getUsedDate() {
		return usedDate;
	}

	/**
	 * 设置使用时间
	 * 
	 * @param usedDate
	 *            使用时间
	 */
	public void setUsedDate(Date usedDate) {
		this.usedDate = usedDate;
	}

}