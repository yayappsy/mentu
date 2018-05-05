/**
 * 
 */
package com.weimhc.modules.user.entity;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thinkgem.javamg.common.persistence.DataEntity;
import com.weimhc.modules.base.entity.BaseIndustry;
import com.weimhc.modules.base.entity.Region;

/**
 * 用户公司信息Entity
 * 
 * @author zsm
 * @version 2017-09-07
 */
public class UserCorpInfo extends DataEntity<UserCorpInfo> {

	private static final long serialVersionUID = 1L;
	/**
	 * 用户通用信息
	 * 
	 */
	private UserInfo userInfo;
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
	private BaseIndustry corpIndustry;
	/**
	 * 公司办公地址
	 * 
	 */
	private Region corpAddress;
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

	public UserCorpInfo() {
		super();
	}

	public UserCorpInfo(String id) {
		super(id);
	}

	/**
	 * 获取用户通用信息
	 * 
	 * @return 用户通用信息
	 */
	@NotNull()
	@JsonIgnore
	public UserInfo getUserInfo() {
		return userInfo;
	}

	public UserCorpInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	/**
	 * 设置用户通用信息
	 * 
	 * @param userInfo
	 *            用户通用信息
	 */
	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	/**
	 * 获取企业名称
	 * 
	 * @return 企业名称
	 */
	@Length(min = 0, max = 100)
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
	@Length(min = 0, max = 100)
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
	@Length(min = 0, max = 20)
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
	@Length(min = 0, max = 100)
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
	@NotNull()
	public BaseIndustry getCorpIndustry() {
		return corpIndustry;
	}

	/**
	 * 设置公司所属行业
	 * 
	 * @param corpIndustry
	 *            公司所属行业
	 */
	public void setCorpIndustry(BaseIndustry corpIndustry) {
		this.corpIndustry = corpIndustry;
	}

	/**
	 * 获取公司办公地址
	 * 
	 * @return 公司办公地址
	 */
	public Region getCorpAddress() {
		return corpAddress;
	}

	/**
	 * 设置公司办公地址
	 * 
	 * @param corpAddress
	 *            公司办公地址
	 */
	public void setCorpAddress(Region corpAddress) {
		this.corpAddress = corpAddress;
	}

	/**
	 * 返回 公司办公地址名称
	 * 
	 * @return the corpAddressName
	 */
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
	@Length(min = 0, max = 255)
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

}