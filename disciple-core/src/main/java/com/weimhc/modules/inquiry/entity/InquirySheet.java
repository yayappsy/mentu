/**
 * 
 */
package com.weimhc.modules.inquiry.entity;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.javamg.common.persistence.DataEntity;
import com.weimhc.modules.member.entity.Member;

/**
 * 询价单Entity
 * 
 * @author zsm
 * @version 2017-04-10
 */
public class InquirySheet extends DataEntity<InquirySheet> {

	private static final long serialVersionUID = 1L;
	/**
	 * 名称
	 * 
	 */
	private String name;
	/**
	 * 名称
	 * 
	 */
	private String sn;
	/**
	 * 会员
	 * 
	 */
	private Member member;
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
	 * 传真号
	 * 
	 */
	private String faxNumber;
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
	 * 询价说明
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
	private InquiryStatus status;

	/**
	 * 来源
	 * 
	 */
	private InquirySource source;
	/**
	 * 询价单关联产品列表
	 */
	private List<InquiryProduct> inquiryProductList;

	public InquirySheet() {
		super();
	}

	public InquirySheet(String id) {
		super(id);
	}

	@Override
	public void preInsert() {
		super.preInsert();
		this.status = InquiryStatus.waiting;
	}

	/**
	 * 获取名称
	 * 
	 * @return 名称
	 */
	@Length(min = 1, max = 100)
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
	 * 获取会员
	 * 
	 * @return 会员
	 */
	public Member getMember() {
		return member;
	}

	/**
	 * 设置会员
	 * 
	 * @param member
	 *            会员
	 */
	public void setMember(Member member) {
		this.member = member;
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
	@Length(min = 1, max = 20)
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
	@Length(min = 0, max = 20)
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
	 * 获取传真号
	 * 
	 * @return 传真号
	 */
	@Length(min = 0, max = 20)
	public String getFaxNumber() {
		return faxNumber;
	}

	/**
	 * 设置传真号
	 * 
	 * @param faxNumber
	 *            传真号
	 */
	public void setFaxNumber(String faxNumber) {
		this.faxNumber = faxNumber;
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
	 * 获取询价说明
	 * 
	 * @return 询价说明
	 */
	@Length(min = 0, max = 255)
	public String getDetail() {
		return detail;
	}

	/**
	 * 设置询价说明
	 * 
	 * @param detail
	 *            询价说明
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
	public InquiryStatus getStatus() {
		return status;
	}

	/**
	 * 设置状态
	 * 
	 * @param status
	 *            状态
	 */
	public void setStatus(InquiryStatus status) {
		this.status = status;
	}

	/**
	 * 返回 询价单编号
	 * 
	 * @return the sn
	 */
	public String getSn() {
		return sn;
	}

	/**
	 * 设置 询价单编号
	 * 
	 * @param sn
	 *            the sn to set
	 */
	public void setSn(String sn) {
		this.sn = sn;
	}

	/**
	 * 返回 InquiryStatus
	 * 
	 * @return the source
	 */
	public InquirySource getSource() {
		return source;
	}

	/**
	 * 设置 询价单来源
	 * 
	 * @param source
	 *            the source to set
	 */
	public void setSource(InquirySource source) {
		this.source = source;
	}

	/**
	 * 返回 询价单关联产品列表
	 * 
	 * @return the inquiryProductList
	 */
	public List<InquiryProduct> getInquiryProductList() {
		return inquiryProductList;
	}

	/**
	 * 设置 询价单关联产品列表
	 * 
	 * @param inquiryProductList
	 *            the inquiryProductList to set
	 */
	public void setInquiryProductList(List<InquiryProduct> inquiryProductList) {
		this.inquiryProductList = inquiryProductList;
	}

}