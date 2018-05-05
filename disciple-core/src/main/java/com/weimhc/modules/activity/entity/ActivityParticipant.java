/**
 * 
 */
package com.weimhc.modules.activity.entity;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.weimhc.framework.persistence.SortableEntity;

/**
 * 活动申请人Entity
 * 
 * @author zsm
 * @version 2017-04-24
 */
public class ActivityParticipant extends SortableEntity<ActivityParticipant> {

	private static final long serialVersionUID = 1L;
	/**
	 * 申请参加的活动
	 * 
	 */
	private Activity activity;
	/**
	 * 姓名
	 * 
	 */
	private String name;
	/**
	 * 账号
	 * 
	 */
	private String memberId;
	/**
	 * 会员昵称
	 * 
	 */
	private String memberNickname;
	/**
	 * 是否会员
	 * 
	 */
	private Boolean isMember;
	/**
	 * 邮箱
	 * 
	 */
	private String email;
	/**
	 * 联系电话
	 * 
	 */
	private String phone;
	/**
	 * 手机号
	 * 
	 */
	private String mobile;
	/**
	 * 详细地址
	 * 
	 */
	private String detailedAddress;
	/**
	 * 邮编
	 * 
	 */
	private String zipcode;
	/**
	 * 报名详情
	 * 
	 */
	private String detail;
	/**
	 * 是否接受邮件
	 * 
	 */
	private Boolean isAcceptEmail;
	/**
	 * 是否接受短信
	 * 
	 */
	private Boolean isAcceptSms;
	/**
	 * 状态
	 * 
	 */
	private String status;

	public ActivityParticipant() {
		super();
	}

	public ActivityParticipant(String id) {
		super(id);
	}

	/**
	 * 获取申请参加的活动
	 * 
	 * @return 申请参加的活动
	 */
	@NotNull
	public Activity getActivity() {
		return activity;
	}

	/**
	 * 设置申请参加的活动
	 * 
	 * @param activity
	 *            申请参加的活动
	 */
	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	/**
	 * 获取姓名
	 * 
	 * @return 姓名
	 */
	@Length(min = 1, max = 100)
	public String getName() {
		return name;
	}

	/**
	 * 设置姓名
	 * 
	 * @param name
	 *            姓名
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获取账号
	 * 
	 * @return 账号
	 */
	@Length(min = 0, max = 64)
	public String getMemberId() {
		return memberId;
	}

	/**
	 * 设置账号
	 * 
	 * @param memberId
	 *            账号
	 */
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	/**
	 * 获取会员昵称
	 * 
	 * @return 会员昵称
	 */
	@Length(min = 0, max = 100)
	public String getMemberNickname() {
		return memberNickname;
	}

	/**
	 * 设置会员昵称
	 * 
	 * @param memberNickname
	 *            会员昵称
	 */
	public void setMemberNickname(String memberNickname) {
		this.memberNickname = memberNickname;
	}

	/**
	 * 获取是否会员
	 * 
	 * @return 是否会员
	 */
	@NotNull()
	public Boolean getIsMember() {
		return isMember;
	}

	/**
	 * 设置是否会员
	 * 
	 * @param isMember
	 *            是否会员
	 */
	public void setIsMember(Boolean isMember) {
		this.isMember = isMember;
	}

	/**
	 * 获取邮箱
	 * 
	 * @return 邮箱
	 */
	@Length(min = 0, max = 20)
	public String getEmail() {
		return email;
	}

	/**
	 * 设置邮箱
	 * 
	 * @param email
	 *            邮箱
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * 获取联系电话
	 * 
	 * @return 联系电话
	 */
	@Length(min = 0, max = 20)
	public String getPhone() {
		return phone;
	}

	/**
	 * 设置联系电话
	 * 
	 * @param phone
	 *            联系电话
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * 获取手机号
	 * 
	 * @return 手机号
	 */
	@Length(min = 1, max = 20)
	public String getMobile() {
		return mobile;
	}

	/**
	 * 设置手机号
	 * 
	 * @param mobile
	 *            手机号
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * 获取详细地址
	 * 
	 * @return 详细地址
	 */
	@Length(min = 0, max = 100)
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
	 * 获取邮编
	 * 
	 * @return 邮编
	 */
	@Length(min = 0, max = 6)
	public String getZipcode() {
		return zipcode;
	}

	/**
	 * 设置邮编
	 * 
	 * @param zipcode
	 *            邮编
	 */
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	/**
	 * 获取报名详情
	 * 
	 * @return 报名详情
	 */
	@Length(min = 0, max = 255)
	public String getDetail() {
		return detail;
	}

	/**
	 * 设置报名详情
	 * 
	 * @param detail
	 *            报名详情
	 */
	public void setDetail(String detail) {
		this.detail = detail;
	}

	/**
	 * 获取是否接受邮件
	 * 
	 * @return 是否接受邮件
	 */
	@NotNull()
	public Boolean getIsAcceptEmail() {
		return isAcceptEmail;
	}

	/**
	 * 设置是否接受邮件
	 * 
	 * @param isAcceptEmail
	 *            是否接受邮件
	 */
	public void setIsAcceptEmail(Boolean isAcceptEmail) {
		this.isAcceptEmail = isAcceptEmail;
	}

	/**
	 * 获取是否接受短信
	 * 
	 * @return 是否接受短信
	 */
	@NotNull()
	public Boolean getIsAcceptSms() {
		return isAcceptSms;
	}

	/**
	 * 设置是否接受短信
	 * 
	 * @param isAcceptSms
	 *            是否接受短信
	 */
	public void setIsAcceptSms(Boolean isAcceptSms) {
		this.isAcceptSms = isAcceptSms;
	}

	/**
	 * 获取状态
	 * 
	 * @return 状态
	 */
	@Length(min = 0, max = 25)
	public String getStatus() {
		return status;
	}

	/**
	 * 设置状态
	 * 
	 * @param status
	 *            状态
	 */
	public void setStatus(String status) {
		this.status = status;
	}

}