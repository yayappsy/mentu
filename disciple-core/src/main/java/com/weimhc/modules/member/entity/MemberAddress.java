/**
 * 
 */
package com.weimhc.modules.member.entity;

import org.hibernate.validator.constraints.Length;

import com.weimhc.framework.persistence.SortableEntity;
import com.weimhc.modules.base.entity.Region;

/**
 * 会员地址Entity
 * 
 * @author liuchao
 * @version 2016-10-12
 */
public class MemberAddress extends SortableEntity<MemberAddress> {

	private static final long serialVersionUID = 1L;
	/**
	 * 会员id
	 * 
	 */
	private Member member;

	/**
	 * 联系人名称
	 * 
	 */
	private String contactName;
	/**
	 * 手机
	 * 
	 */
	private String mobile;

	/**
	 * 电话
	 * 
	 */
	private String phone;
	/**
	 * 地区
	 */
	private Region area;
	/**
	 * 详细地址
	 * 
	 */
	private String detailedAddress;

	/**
	 * 邮编
	 */
	private String zipCode;

	/**
	 * 是否默认
	 * 
	 */
	private Boolean isDefault;
	/**
	 * 邮箱
	 * 
	 */
	private String email;

	public MemberAddress() {
		super();
	}

	public MemberAddress(String id) {
		super(id);
	}

	public enum AddressType {
		/**
		 * 服务地址
		 */
		service,
		/**
		 * 缴费地址
		 */
		fee
	}

	/**
	 * 获取会员id
	 * 
	 * @return 会员id
	 */
	public Member getMember() {
		return member;
	}

	/**
	 * 设置会员id
	 * 
	 * @param member
	 *            会员id
	 */
	public void setMember(Member member) {
		this.member = member;
	}

	/**
	 * 获取联系人名称
	 * 
	 * @return 联系人名称
	 */
	@Length(min = 1, max = 64)
	public String getContactName() {
		return contactName;
	}

	/**
	 * 设置联系人名称
	 * 
	 * @param contactName
	 *            联系人名称
	 */
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	/**
	 * 获取手机
	 * 
	 * @return 手机
	 */
	@Length(min = 1, max = 64)
	public String getMobile() {
		return mobile;
	}

	/**
	 * 设置手机
	 * 
	 * @param mobile
	 *            手机
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * 获取详细地址
	 * 
	 * @return 详细地址
	 */
	@Length(min = 1, max = 255)
	public String getDetailedAddress() {
		return detailedAddress;
	}

	/**
	 * 设置详细地址
	 * 
	 * @param detailedAddress
	 *            详细地址
	 */
	public void setDetailedAddress(String detailedAddress) {
		this.detailedAddress = detailedAddress;
	}

	/**
	 * 返回 地区
	 * 
	 * @return the area
	 */
	public Region getArea() {
		return area;
	}

	/**
	 * 设置 地区
	 * 
	 * @param area
	 *            the area to set
	 */
	public void setArea(Region area) {
		this.area = area;
	}

	/**
	 * 获取是否默认
	 * 
	 * @return 是否默认
	 */
	@Length(min = 1, max = 1)
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
	 * 返回 电话
	 * 
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * 设置 电话
	 * 
	 * @param phone
	 *            the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * 获取邮编
	 * 
	 * @return the zipCode
	 */
	public String getZipCode() {
		return zipCode;
	}

	/**
	 * 设置邮编
	 * 
	 * @param zipCode
	 *            the zipCode to set
	 */
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	/**
	 * 返回 邮箱
	 * 
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * 设置 邮箱
	 * 
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

}