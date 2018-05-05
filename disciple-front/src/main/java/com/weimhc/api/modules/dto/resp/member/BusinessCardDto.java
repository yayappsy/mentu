/**
 * 
 */
package com.weimhc.api.modules.dto.resp.member;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.weimhc.framework.dto.AbstractIdDto;
import com.weimhc.modules.base.entity.Region;
import io.swagger.annotations.ApiModelProperty;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 名片返回
 * 
 * @author shaozuo
 *
 */
public class BusinessCardDto extends AbstractIdDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3550624814976073220L;
	/**
	 * 姓名
	 * 
	 */
	private String name;
	/**
	 * 头像
	 * 
	 */
	private String avatar;
	/**
	 * 英文姓名
	 * 
	 */
	private String englishName;
	/**
	 * 性别
	 * 
	 */
	private String gender;
	/**
	 * 职位名称
	 * 
	 */
	private String position;
	/**
	 * 公司名称
	 * 
	 */
	private String corpName;
	/**
	 * 公司英文名称
	 * 
	 */
	private String corpEnglishName;
	/**
	 * 办公电话
	 * 
	 */
	private String businessPhone;
	/**
	 * 私人电话
	 * 
	 */
	private String personalPhone;
	/**
	 * 邮编
	 * 
	 */
	private String zipCode;
	/**
	 * 传真
	 * 
	 */
	private String fax;
	/**
	 * 邮箱
	 * 
	 */
	private String email;
	/**
	 * 部门
	 * 
	 */
	private String department;
	/**
	 * 公司地址
	 * 
	 */
	private Region corpAddress;
	/**
	 * 公司详细地址
	 * 
	 */
	private String corpDetailedAddress;
	/**
	 * 公司网址
	 * 
	 */
	private String corpWebsite;
	/**
	 * 个人信息
	 * 
	 */
	private String information;
	/**
	 * 故乡
	 * 
	 */
	private Region hometown;

	/**
	 * 常驻地
	 * 
	 */
	private Region residence;
	/**
	 * 公司办公地址名称
	 * 
	 */
	private String corpAddressName;
	/**
	 * 故乡名称
	 * 
	 */
	private String hometownName;
	/**
	 * 常驻地名称
	 * 
	 */
	private String residenceName;

	/**
	 * 获取姓名
	 * 
	 * @return 姓名
	 */
	@ApiModelProperty(value = "姓名", example = "张三")
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
	 * 获取头像
	 * 
	 * @return 头像
	 */
	@ApiModelProperty(value = "头像", example = "http://abc.def")
	public String getAvatar() {
		return avatar;
	}

	/**
	 * 设置头像
	 * 
	 * @param avatar
	 *            头像
	 */
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	/**
	 * 获取英文姓名
	 * 
	 * @return 英文姓名
	 */
	@ApiModelProperty(value = "英文姓名", example = "John")
	public String getEnglishName() {
		return englishName;
	}

	/**
	 * 设置英文姓名
	 * 
	 * @param englishName
	 *            英文姓名
	 */
	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}

	/**
	 * 获取性别
	 * 
	 * @return 性别
	 */
	@ApiModelProperty(value = "性别：1（男），2（女）", example = "1")
	public String getGender() {
		return gender;
	}

	/**
	 * 设置性别
	 * 
	 * @param gender
	 *            性别
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * 获取职位名称
	 * 
	 * @return 职位名称
	 */
	@ApiModelProperty(value = "职位名称", example = "主管")
	public String getPosition() {
		return position;
	}

	/**
	 * 设置职位名称
	 * 
	 * @param position
	 *            职位名称
	 */
	public void setPosition(String position) {
		this.position = position;
	}

	/**
	 * 获取公司名称
	 * 
	 * @return 公司名称
	 */
	@ApiModelProperty(value = "公司名称", example = "XXX公司")
	public String getCorpName() {
		return corpName;
	}

	/**
	 * 设置公司名称
	 * 
	 * @param corpName
	 *            公司名称
	 */
	public void setCorpName(String corpName) {
		this.corpName = corpName;
	}

	/**
	 * 获取公司英文名称
	 * 
	 * @return 公司英文名称
	 */
	@ApiModelProperty(value = "公司英文名称", example = "abc.def")
	public String getCorpEnglishName() {
		return corpEnglishName;
	}

	/**
	 * 设置公司英文名称
	 * 
	 * @param corpEnglishName
	 *            公司英文名称
	 */
	public void setCorpEnglishName(String corpEnglishName) {
		this.corpEnglishName = corpEnglishName;
	}

	/**
	 * 获取办公电话
	 * 
	 * @return 办公电话
	 */
	@ApiModelProperty(value = "办公电话", example = "188000000")
	public String getBusinessPhone() {
		return businessPhone;
	}

	/**
	 * 设置办公电话
	 * 
	 * @param businessPhone
	 *            办公电话
	 */
	public void setBusinessPhone(String businessPhone) {
		this.businessPhone = businessPhone;
	}

	/**
	 * 获取私人电话
	 * 
	 * @return 私人电话
	 */
	@ApiModelProperty(value = "私人电话", example = "188000000")
	public String getPersonalPhone() {
		return personalPhone;
	}

	/**
	 * 设置私人电话
	 * 
	 * @param personalPhone
	 *            私人电话
	 */
	public void setPersonalPhone(String personalPhone) {
		this.personalPhone = personalPhone;
	}

	/**
	 * 获取邮编
	 * 
	 * @return 邮编
	 */
	@ApiModelProperty(value = "邮编", example = "010040")
	public String getZipCode() {
		return zipCode;
	}

	/**
	 * 设置邮编
	 * 
	 * @param zipCode
	 *            邮编
	 */
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	/**
	 * 获取传真
	 * 
	 * @return 传真
	 */
	@ApiModelProperty(value = "传真", example = "56565656")
	public String getFax() {
		return fax;
	}

	/**
	 * 设置传真
	 * 
	 * @param fax
	 *            传真
	 */
	public void setFax(String fax) {
		this.fax = fax;
	}

	/**
	 * 获取邮箱
	 * 
	 * @return 邮箱
	 */
	@ApiModelProperty(value = "邮箱", example = "邮箱")
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
	 * 获取部门
	 * 
	 * @return 部门
	 */
	@ApiModelProperty(value = "部门", example = "部门")
	public String getDepartment() {
		return department;
	}

	/**
	 * 设置 部门
	 * 
	 * @param department
	 *            部门
	 */
	public void setDepartment(String department) {
		this.department = department;
	}

	/**
	 * 获取公司地址
	 * 
	 * @return 公司地址
	 */
	@ApiIgnore
	@JsonIgnore
	@ApiModelProperty(value = "公司地址", example = "110101")
	public Region getCorpAddress() {
		return corpAddress;
	}

	/**
	 * 设置公司地址
	 * 
	 * @param corpAddress
	 *            公司地址
	 */
	public void setCorpAddress(Region corpAddress) {
		this.corpAddress = corpAddress;
	}

	/**
	 * 获取公司地址Id
	 * 
	 * @return 公司地址Id
	 */
	@ApiModelProperty(value = "公司地址id", example = "110101")
	public String getCorpAddressId() {
		if (corpAddress != null) {
			return corpAddress.getId();
		}
		return "";
	}

	/**
	 * 返回 公司办公地址名称
	 * 
	 * @return the corpAddressName
	 */
	@ApiModelProperty(value = "公司办公地址名称", example = "中国")
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
	@ApiModelProperty(value = "公司详细地址", example = "XXXX")
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
	 * 获取公司网址
	 * 
	 * @return 公司网址
	 */
	@ApiModelProperty(value = "公司网址", example = "公司网址")
	public String getCorpWebsite() {
		return corpWebsite;
	}

	/**
	 * 设置 公司网址
	 * 
	 * @param corpWebsite
	 *            公司网址
	 */
	public void setCorpWebsite(String corpWebsite) {
		this.corpWebsite = corpWebsite;
	}

	/**
	 * 获取 个人信息
	 * 
	 * @return 个人信息
	 */
	@ApiModelProperty(value = "个人信息", example = "个人信息")
	public String getInformation() {
		return information;
	}

	/**
	 * 设置个人信息
	 * 
	 * @param information
	 *            个人信息
	 */
	public void setInformation(String information) {
		this.information = information;
	}

	/**
	 * 获取故乡
	 * 
	 * @return 故乡
	 */
	@ApiIgnore
	@JsonIgnore
	public Region getHometown() {
		return hometown;
	}

	/**
	 * 设置故乡
	 * 
	 * @param hometown
	 *            故乡
	 */
	public void setHometown(Region hometown) {
		this.hometown = hometown;
	}

	/**
	 * 获取故乡Id
	 * 
	 * @return 故乡Id
	 */
	@ApiModelProperty(value = "故乡Id", example = "110101")
	public String getHometownId() {
		if (hometown != null) {
			return hometown.getId();
		}
		return "";
	}

	/**
	 * 获取故乡名称
	 * 
	 * @return 故乡名称
	 */
	@ApiModelProperty(value = "故乡名称", example = "中国")
	public String getHometownName() {
		return hometownName;
	}

	/**
	 * 设置故乡名称
	 * 
	 * @param hometownName
	 *            故乡名称
	 */
	public void setHometownName(String hometownName) {
		this.hometownName = hometownName;
	}

	/**
	 * 获取 常驻地
	 * 
	 * @return 常驻地
	 */
	@ApiIgnore
	@JsonIgnore
	public Region getResidence() {
		return residence;
	}

	/**
	 * 设置常驻地
	 * 
	 * @param residence
	 *            常驻地
	 */
	public void setResidence(Region residence) {
		this.residence = residence;
	}

	/**
	 * 获取 常驻地
	 * 
	 * @return 常驻地
	 */
	@ApiModelProperty(value = "常驻地Id", example = "110101")
	public String getResidenceId() {
		if (residence != null) {
			return residence.getId();
		}
		return "";
	}

	/**
	 * 获取常驻地名称
	 * 
	 * @return 常驻地名称
	 */
	@ApiModelProperty(value = "常驻地名称", example = "中国")
	public String getResidenceName() {
		return residenceName;
	}

	/**
	 * 设置常驻地名称
	 * 
	 * @param residenceName
	 *            常驻地名称
	 */
	public void setResidenceName(String residenceName) {
		this.residenceName = residenceName;
	}
}
