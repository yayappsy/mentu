/**
 * 
 */
package com.weimhc.weixin.entity;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.javamg.common.persistence.DataEntity;

/**
 * 微信用户Entity
 * 
 * @author zsm
 * @version 2016-07-06
 */
public class WeixinUser extends DataEntity<WeixinUser> {

	private static final long serialVersionUID = 1L;
	/**
	 * 昵称
	 * 
	 */
	private String nickname;
	/**
	 * 头像
	 * 
	 */
	private String headImgUrl;
	/**
	 * 微信open_id
	 * 
	 */
	private String openId;
	/**
	 * unionId
	 * 
	 */
	private String unionId;
	/**
	 * groupId
	 * 
	 */
	private String groupId;
	/**
	 * 性别
	 * 
	 */
	private String sex;
	/**
	 * 年龄
	 * 
	 */
	private String age;
	/**
	 * 城市
	 * 
	 */
	private String city;
	/**
	 * 省份
	 * 
	 */
	private String province;
	/**
	 * 国家
	 * 
	 */
	private String country;
	/**
	 * 是否订阅
	 * 
	 */
	private String subscribe;
	/**
	 * 语言
	 * 
	 */
	private String language;
	/**
	 * 个性签名
	 * 
	 */
	private String sign;
	/**
	 * 订阅时间
	 * 
	 */
	private Date subscribeTime;

	public WeixinUser() {
		super();
	}

	public WeixinUser(String id) {
		super(id);
	}

	/**
	 * 获取昵称
	 * 
	 * @return 昵称
	 */
	@Length(min = 0, max = 255)
	public String getNickname() {
		return nickname;
	}

	/**
	 * 设置昵称
	 * 
	 * @param nickname
	 *            昵称
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	/**
	 * 获取头像
	 * 
	 * @return 头像
	 */
	@Length(min = 0, max = 255)
	public String getHeadImgUrl() {
		return headImgUrl;
	}

	/**
	 * 设置头像
	 * 
	 * @param headImgUrl
	 *            头像
	 */
	public void setHeadImgUrl(String headImgUrl) {
		this.headImgUrl = headImgUrl;
	}

	/**
	 * 获取微信open_id
	 * 
	 * @return 微信open_id
	 */
	@Length(min = 1, max = 255)
	public String getOpenId() {
		return openId;
	}

	/**
	 * 设置微信open_id
	 * 
	 * @param openId
	 *            微信open_id
	 */
	public void setOpenId(String openId) {
		this.openId = openId;
	}

	/**
	 * 获取unionId
	 * 
	 * @return unionId
	 */
	@Length(min = 0, max = 255)
	public String getUnionId() {
		return unionId;
	}

	/**
	 * 设置unionId
	 * 
	 * @param unionId
	 *            unionId
	 */
	public void setUnionId(String unionId) {
		this.unionId = unionId;
	}

	/**
	 * 获取groupId
	 * 
	 * @return groupId
	 */
	@Length(min = 0, max = 255)
	public String getGroupId() {
		return groupId;
	}

	/**
	 * 设置groupId
	 * 
	 * @param groupId
	 *            groupId
	 */
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	/**
	 * 获取性别
	 * 
	 * @return 性别
	 */
	@Length(min = 0, max = 1)
	public String getSex() {
		return sex;
	}

	/**
	 * 设置性别
	 * 
	 * @param sex
	 *            性别
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}

	/**
	 * 获取年龄
	 * 
	 * @return 年龄
	 */
	@Length(min = 0, max = 11)
	public String getAge() {
		return age;
	}

	/**
	 * 设置年龄
	 * 
	 * @param age
	 *            年龄
	 */
	public void setAge(String age) {
		this.age = age;
	}

	/**
	 * 获取城市
	 * 
	 * @return 城市
	 */
	public String getCity() {
		return city;
	}

	/**
	 * 设置城市
	 * 
	 * @param city
	 *            城市
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * 获取省份
	 * 
	 * @return 省份
	 */
	@Length(min = 0, max = 64)
	public String getProvince() {
		return province;
	}

	/**
	 * 设置省份
	 * 
	 * @param province
	 *            省份
	 */
	public void setProvince(String province) {
		this.province = province;
	}

	/**
	 * 获取国家
	 * 
	 * @return 国家
	 */
	@Length(min = 0, max = 64)
	public String getCountry() {
		return country;
	}

	/**
	 * 设置国家
	 * 
	 * @param country
	 *            国家
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * 获取是否订阅
	 * 
	 * @return 是否订阅
	 */
	@Length(min = 0, max = 1)
	public String getSubscribe() {
		return subscribe;
	}

	/**
	 * 设置是否订阅
	 * 
	 * @param subscribe
	 *            是否订阅
	 */
	public void setSubscribe(String subscribe) {
		this.subscribe = subscribe;
	}

	/**
	 * 获取语言
	 * 
	 * @return 语言
	 */
	@Length(min = 0, max = 255)
	public String getLanguage() {
		return language;
	}

	/**
	 * 设置语言
	 * 
	 * @param language
	 *            语言
	 */
	public void setLanguage(String language) {
		this.language = language;
	}

	/**
	 * 获取个性签名
	 * 
	 * @return 个性签名
	 */
	@Length(min = 0, max = 255)
	public String getSign() {
		return sign;
	}

	/**
	 * 设置个性签名
	 * 
	 * @param sign
	 *            个性签名
	 */
	public void setSign(String sign) {
		this.sign = sign;
	}

	/**
	 * 获取订阅时间
	 * 
	 * @return 订阅时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getSubscribeTime() {
		return subscribeTime;
	}

	/**
	 * 设置订阅时间
	 * 
	 * @param subscribeTime
	 *            订阅时间
	 */
	public void setSubscribeTime(Date subscribeTime) {
		this.subscribeTime = subscribeTime;
	}

}