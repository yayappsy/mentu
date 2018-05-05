package com.weimhc.api.modules.dto.req;

import com.weimhc.framework.dto.req.AbstractRQ;
import com.weimhc.modules.user.entity.IdentityType;
import io.swagger.annotations.ApiModelProperty;

/**
 * 第三方登录使用的请求
 * 
 * @author lc
 *
 */
public class ThirdPartyLoginsRQ extends AbstractRQ {

	private static final long serialVersionUID = -2659037797172700696L;

	/**
	 * 登录方式
	 */
	private IdentityType identityType;
	/**
	 * open_id
	 */
	private String openid;

	private String accesstoken;
	/**
	 * 用户id
	 */
	private String uid;
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 性别
	 */
	private String gender;
	/**
	 * 头像
	 */
	private String iconurl;
	/**
	 * 国家
	 */
	private String country;
	/**
	 * 省份
	 */
	private String province;
	/**
	 * 城市
	 */
	private String city;

	/**
	 * 获取登录方式
	 * 
	 * @return the identityType
	 */
	@ApiModelProperty(value = "登录方式", example = "wechat", allowableValues = "wechat,qq")
	public IdentityType getIdentityType() {
		return identityType;
	}

	/**
	 * 设置登录方式
	 * 
	 * @param identityType
	 *            the identityType to set
	 */
	public void setIdentityType(IdentityType identityType) {
		this.identityType = identityType;
	}

	/**
	 * 获取openId
	 * 
	 * @return the openId
	 */
	@ApiModelProperty(value = "openId")
	public String getOpenid() {
		return openid;
	}

	/**
	 * 设置openId
	 * 
	 * @param openId
	 *            the openId to set
	 */
	public void setOpenid(String openid) {
		this.openid = openid;
	}

	/**
	 * 获取用户id
	 * 
	 * @return the uid
	 */
	@ApiModelProperty(value = "用户id")
	public String getUid() {
		return uid;
	}

	/**
	 * 设置用户id
	 * 
	 * @param uid
	 *            the uid to set
	 */
	public void setUid(String uid) {
		this.uid = uid;
	}

	/**
	 * 获取名称
	 * 
	 * @return the name
	 */
	@ApiModelProperty(value = "名称")
	public String getName() {
		return name;
	}

	/**
	 * 设置名称
	 * 
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获取性别
	 * 
	 * @return the gender
	 */
	@ApiModelProperty(value = "性别")
	public String getGender() {
		return gender;
	}

	/**
	 * 设置性别
	 * 
	 * @param gender
	 *            the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * 获取头像
	 * 
	 * @return the iconurl
	 */
	@ApiModelProperty(value = "头像")
	public String getIconurl() {
		return iconurl;
	}

	/**
	 * 设置头像
	 * 
	 * @param iconurl
	 *            the iconurl to set
	 */
	public void setIconurl(String iconurl) {
		this.iconurl = iconurl;
	}

	/**
	 * 获取国家
	 * 
	 * @return the country
	 */
	@ApiModelProperty(value = "国家")
	public String getCountry() {
		return country;
	}

	/**
	 * 设置国家
	 * 
	 * @param country
	 *            the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * 获取省份
	 * 
	 * @return the prvinice
	 */
	@ApiModelProperty(value = "省份")
	public String getProvince() {
		return province;
	}

	/**
	 * 设置省份
	 * 
	 * @param prvinice
	 *            the prvinice to set
	 */
	public void setProvince(String province) {
		this.province = province;
	}

	/**
	 * 获取城市
	 * 
	 * @return the city
	 */
	@ApiModelProperty(value = "城市")
	public String getCity() {
		return city;
	}

	/**
	 * 设置城市
	 * 
	 * @param city
	 *            the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the accesstoken
	 */
	@ApiModelProperty(value = "第三方登录凭证")
	public String getAccesstoken() {
		return accesstoken;
	}

	/**
	 * @param accesstoken
	 *            the accesstoken to set
	 */
	public void setAccesstoken(String accesstoken) {
		this.accesstoken = accesstoken;
	}
}
