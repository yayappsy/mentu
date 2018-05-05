/**
 * 
 */
package com.weimhc.modules.user.entity;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thinkgem.javamg.common.persistence.DataEntity;

/**
 * 用户健康信息Entity
 * 
 * @author zsm
 * @version 2017-09-07
 */
public class UserHealthInfo extends DataEntity<UserHealthInfo> {

	private static final long serialVersionUID = 1L;
	/**
	 * 用户通用信息
	 * 
	 */
	private UserInfo userInfo;
	/**
	 * 身高
	 * 
	 */
	private Double height;
	/**
	 * 体重
	 * 
	 */
	private Double weight;
	/**
	 * 腰围
	 * 
	 */
	private Double waistline;
	/**
	 * 胸围
	 * 
	 */
	private Double bust;
	/**
	 * 臀围
	 * 
	 */
	private Double hipline;

	public UserHealthInfo() {
		super();
	}

	public UserHealthInfo(String id) {
		super(id);
	}

	public UserHealthInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
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
	 * 获取身高
	 * 
	 * @return 身高
	 */
	public Double getHeight() {
		return height;
	}

	/**
	 * 设置身高
	 * 
	 * @param height
	 *            身高
	 */
	public void setHeight(Double height) {
		this.height = height;
	}

	/**
	 * 获取体重
	 * 
	 * @return 体重
	 */
	public Double getWeight() {
		return weight;
	}

	/**
	 * 设置体重
	 * 
	 * @param weight
	 *            体重
	 */
	public void setWeight(Double weight) {
		this.weight = weight;
	}

	/**
	 * 获取腰围
	 * 
	 * @return 腰围
	 */
	public Double getWaistline() {
		return waistline;
	}

	/**
	 * 设置腰围
	 * 
	 * @param waistline
	 *            腰围
	 */
	public void setWaistline(Double waistline) {
		this.waistline = waistline;
	}

	/**
	 * 获取胸围
	 * 
	 * @return 胸围
	 */
	public Double getBust() {
		return bust;
	}

	/**
	 * 设置胸围
	 * 
	 * @param bust
	 *            胸围
	 */
	public void setBust(Double bust) {
		this.bust = bust;
	}

	/**
	 * 获取臀围
	 * 
	 * @return 臀围
	 */
	public Double getHipline() {
		return hipline;
	}

	/**
	 * 设置臀围
	 * 
	 * @param hipline
	 *            臀围
	 */
	public void setHipline(Double hipline) {
		this.hipline = hipline;
	}

}