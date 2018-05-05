/**
 * 
 */
package com.weimhc.api.modules.dto.req;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.weimhc.framework.dto.req.AbstractRQ;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Date;

/**
 * 会员信息 请求
 * 
 * @author zsm
 * @version 2016-10-05
 */
public class MemberProfileRQ extends AbstractRQ {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 真实姓名
	 * 
	 */
	private String name;

	/**
	 * 会员头像
	 */
	private String avatar;

	/**
	 * qq
	 * 
	 */
	private String qq;
	/**
	 * 微信号
	 */
	private String wechatId;
	/**
	 * 邮箱
	 */
	private String email;

	/**
	 * 生日
	 * 
	 */
	private Date birth;
	/**
	 * 性别
	 * 
	 */
	private String gender;

	/**
	 * 身份证号
	 */
	private String identityCard;

	/**
	 * 籍贯id
	 */
	private String birthpalceId;

	/**
	 * 居住城市id
	 */
	private String residenceId;
	/**
	 * 民族id
	 */
	private String nationId;

	/**
	 * 最低学历
	 */
	private String educationId;

	/**
	 * 企业名称
	 * 
	 */
	private String corpName;
	/**
	 * 公司网站
	 * 
	 */
	private String corpWebsite;
	/**
	 * 公司电话
	 * 
	 */
	private String corpPhone;
	/**
	 * 公司职务
	 * 
	 */
	private String corpJob;
	/**
	 * 公司所属行业
	 * 
	 */
	private String corpIndustryId;
	/**
	 * 公司办公地址
	 * 
	 */
	private String corpAddressId;
	/**
	 * 公司办公地址名称
	 * 
	 */
	private String corpAddressName;
	/**
	 * 公司详细地址
	 * 
	 */
	private String corpDetailedAddress;

	/**
	 * 期待结识
	 */
	private String lookForwardToMeet;
	/**
	 * 个人签名
	 */
	private String personalSignature;

	/**
	 * 返回 真实名称
	 * 
	 * @return the name
	 */
	@ApiModelProperty(value = "真实名称", example = "张三")
	public String getName() {
		return name;
	}

	/**
	 * 设置 真实名称
	 * 
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获取会员邮箱
	 * 
	 * @return the email
	 */
	@ApiModelProperty(value = "会员邮箱", example = "123@456.com")
	public String getEmail() {
		return email;
	}

	/**
	 * 设置会员邮箱
	 * 
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * 返回 生日
	 * 
	 * @return the birth
	 */
	@ApiModelProperty(value = "生日", example = "1999-01-28")
	@JsonFormat(pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public Date getBirth() {
		return birth;
	}

	/**
	 * 设置 生日
	 * 
	 * @param birth
	 *            the birth to set
	 */
	public void setBirth(Date birth) {
		this.birth = birth;
	}

	/**
	 * 返回性别
	 * 
	 * @return the gender
	 */
	@ApiModelProperty(value = "性别,1(男)，2(女)", example = "1")
	public String getGender() {
		return gender;
	}

	/**
	 * 设置 性别
	 * 
	 * @param gender
	 *            the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * 返回头像
	 * 
	 * @return the avatar
	 */
	@ApiModelProperty(value = "头像", example = "http://www.abc.def")
	public String getAvatar() {
		return avatar;
	}

	/**
	 * 设置头像
	 * 
	 * @param avatar
	 *            the avatar to set
	 */
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	/**
	 * 返回身份证号
	 * 
	 * @return the identityCard
	 */
	@ApiIgnore
	@JsonIgnore
	@ApiModelProperty(value = "身份证号", example = "1854575646")
	public String getIdentityCard() {
		return identityCard;
	}

	/**
	 * 设置身份证号
	 * 
	 * @param identityCard
	 *            the identityCard to set
	 */
	public void setIdentityCard(String identityCard) {
		this.identityCard = identityCard;
	}

	/**
	 * 返回 籍贯id
	 * 
	 * @return the birthpalceId
	 */
	@ApiModelProperty(value = " 籍贯id", example = "110101")
	public String getBirthpalceId() {
		return birthpalceId;
	}

	/**
	 * 设置 籍贯id
	 * 
	 * @param birthpalceId
	 *            the birthpalceId to set
	 */
	public void setBirthpalceId(String birthpalceId) {
		this.birthpalceId = birthpalceId;
	}

	/**
	 * 返回居住地id
	 * 
	 * @return the residenceId
	 */
	@ApiModelProperty(value = "居住地id", example = "110106")
	public String getResidenceId() {
		return residenceId;
	}

	/**
	 * 设置居住地id
	 * 
	 * @param residenceId
	 *            the residenceId to set
	 */
	public void setResidenceId(String residenceId) {
		this.residenceId = residenceId;
	}

	/**
	 * 返回民族id
	 * 
	 * @return the nationId
	 */
	@ApiModelProperty(value = "民族id", example = "1")
	public String getNationId() {
		return nationId;
	}

	/**
	 * 设置民族id
	 * 
	 * @param nationId
	 *            the nationId to set
	 */
	public void setNationId(String nationId) {
		this.nationId = nationId;
	}

	/**
	 * 返回 最低学历
	 * 
	 * @return the educationId
	 */
	@ApiModelProperty(value = "最低学历id", example = "1")
	public String getEducationId() {
		return educationId;
	}

	/**
	 * 设置 最低学历
	 * 
	 * @param educationId
	 *            the educationId to set
	 */
	public void setEducationId(String educationId) {
		this.educationId = educationId;
	}

	/**
	 * 获取企业名称
	 * 
	 * @return 企业名称
	 */
	@ApiModelProperty(value = "企业名称", example = "天下一家")
	public String getCorpName() {
		return corpName;
	}

	/**
	 * 设置企业名称
	 * 
	 * @param corpName
	 *            企业名称
	 */
	public void setCorpName(String corpName) {
		this.corpName = corpName;
	}

	/**
	 * 获取公司网站
	 * 
	 * @return 公司网站
	 */
	@ApiModelProperty(value = "公司网站", example = "http://www.abc.def")
	public String getCorpWebsite() {
		return corpWebsite;
	}

	/**
	 * 设置公司网站
	 * 
	 * @param corpWebsite
	 *            公司网站
	 */
	public void setCorpWebsite(String corpWebsite) {
		this.corpWebsite = corpWebsite;
	}

	/**
	 * 获取公司电话
	 * 
	 * @return 公司电话
	 */
	@ApiModelProperty(value = "公司电话", example = "010-1000000")
	public String getCorpPhone() {
		return corpPhone;
	}

	/**
	 * 设置公司电话
	 * 
	 * @param corpPhone
	 *            公司电话
	 */

	public void setCorpPhone(String corpPhone) {
		this.corpPhone = corpPhone;
	}

	/**
	 * 获取公司职务
	 * 
	 * @return 公司职务
	 */
	@ApiModelProperty(value = "公司职务", example = "ceo")
	public String getCorpJob() {
		return corpJob;
	}

	/**
	 * 设置公司职务
	 * 
	 * @param corpJob
	 *            公司职务
	 */
	public void setCorpJob(String corpJob) {
		this.corpJob = corpJob;
	}

	/**
	 * 获取公司所属行业
	 * 
	 * @return 公司所属行业
	 */
	@ApiModelProperty(value = "公司所属行业Id", example = "2")
	public String getCorpIndustryId() {
		return corpIndustryId;
	}

	/**
	 * 设置公司所属行业
	 * 
	 * @param corpIndustry
	 *            公司所属行业
	 */
	public void setCorpIndustryId(String corpIndustryId) {
		this.corpIndustryId = corpIndustryId;
	}

	/**
	 * 获取公司办公地址
	 * 
	 * @return 公司办公地址
	 */
	@ApiModelProperty(value = "公司办公地址Id", example = "110108")
	public String getCorpAddressId() {
		return corpAddressId;
	}

	/**
	 * 设置公司办公地址
	 * 
	 * @param corpAddress
	 *            公司办公地址
	 */
	public void setCorpAddressId(String corpAddressId) {
		this.corpAddressId = corpAddressId;
	}

	/**
	 * 返回 公司办公地址名称
	 * 
	 * @return the corpAddressName
	 */
	@ApiModelProperty(value = "公司办公地址名称", example = "海淀区")
	public String getCorpAddressName() {
		return corpAddressName;
	}

	/**
	 * 设置 公司办公地址名称
	 * 
	 * @param corpAddressName
	 *            the corpAddressName to set
	 */
	public void setCorpAddressName(String corpAddressName) {
		this.corpAddressName = corpAddressName;
	}

	/**
	 * 获取公司详细地址
	 * 
	 * @return 公司详细地址
	 */
	@ApiModelProperty(value = "公司详细地址", example = "地址详情")
	public String getCorpDetailedAddress() {
		return corpDetailedAddress;
	}

	/**
	 * 设置公司详细地址
	 * 
	 * @param corpDetailedAddress
	 *            公司详细地址
	 */
	public void setCorpDetailedAddress(String corpDetailedAddress) {
		this.corpDetailedAddress = corpDetailedAddress;
	}

	/**
	 * 获取qq
	 * 
	 * @return qq
	 */
	@ApiModelProperty(value = "qq号", example = "000000000")
	public String getQq() {
		return qq;
	}

	/**
	 * 设置qq
	 * 
	 * @param qq
	 *            qq
	 */
	public void setQq(String qq) {
		this.qq = qq;
	}

	/**
	 * 返回 微信号
	 * 
	 * @return the wechatId
	 */
	public String getWechatId() {
		return wechatId;
	}

	/**
	 * 设置 微信号
	 * 
	 * @param wechatId
	 *            the wechatId to set
	 */
	@ApiModelProperty(value = "微信号", example = "wh10254")
	public void setWechatId(String wechatId) {
		this.wechatId = wechatId;
	}

	/**
	 * 返回 期待结识
	 * 
	 * @return the lookForwardToMeet
	 */
	@ApiModelProperty(value = "期待结识", example = "我期待结识精英任务")
	public String getLookForwardToMeet() {
		return lookForwardToMeet;
	}

	/**
	 * 设置 期待结识
	 * 
	 * @param lookForwardToMeet
	 *            the lookForwardToMeet to set
	 */
	public void setLookForwardToMeet(String lookForwardToMeet) {
		this.lookForwardToMeet = lookForwardToMeet;
	}

	/**
	 * 返回 个人签名
	 * 
	 * @return the personalSignature
	 */
	@ApiModelProperty(value = "个人签名", example = "")
	public String getPersonalSignature() {
		return personalSignature;
	}

	/**
	 * 设置 个人签名
	 * 
	 * @param personalSignature
	 *            the personalSignature to set
	 */
	public void setPersonalSignature(String personalSignature) {
		this.personalSignature = personalSignature;
	}

}