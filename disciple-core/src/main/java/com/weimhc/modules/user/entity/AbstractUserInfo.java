/**
 * 
 */
package com.weimhc.modules.user.entity;

import java.util.Date;

import org.hibernate.validator.constraints.Length;
import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thinkgem.javamg.common.persistence.DataEntity;
import com.thinkgem.javamg.common.supcan.annotation.treelist.cols.SupCol;
import com.thinkgem.javamg.common.utils.excel.annotation.ExcelField;
import com.weimhc.modules.base.entity.Education;
import com.weimhc.modules.base.entity.Nation;
import com.weimhc.modules.base.entity.Region;
import com.weimhc.modules.base.entity.ReligiousBelief;

/**
 * 用户基本信息 Entity--所有用户的基类
 * 
 * @author zsm
 * @version 2016-01-12
 */
public abstract class AbstractUserInfo<T> extends DataEntity<T> {

	private static final long serialVersionUID = -4715131269380874780L;

	/** "用户名"Cookie名称 */
	public static final String USERNAME_COOKIE_NAME = "username";

	/** "id"Cookie名称 */
	public static final String ID_COOKIE_NAME = "userInfoId";

	/**
	 * 用户平台编号
	 * 
	 */
	private String sn;
	/**
	 * 登录名
	 * 
	 */
	private String username;

	/**
	 * 真实姓名
	 * 
	 */
	private String name;
	/**
	 * 昵称
	 * 
	 */
	private String nickname;
	/**
	 * 头像
	 * 
	 */
	private String avatar;
	/**
	 * 生日
	 * 
	 */
	private Date birth;
	/**
	 * 邮箱
	 * 
	 */
	private String email;
	/**
	 * 性别
	 * 
	 */
	private String gender;
	/**
	 * 手机号
	 * 
	 */
	private String mobile;
	/**
	 * 电话
	 * 
	 */
	private String phone;
	/**
	 * 出生地
	 * 
	 */
	private Region birthplace;
	/**
	 * 身份证
	 * 
	 */
	private String identityCard;
	/**
	 * 宗教信仰
	 * 
	 */
	private ReligiousBelief religiousBelief;
	/**
	 * 民族
	 * 
	 */
	private Nation nation;

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
	 * 积分
	 */
	private Integer point;
	/**
	 * 居住地
	 * 
	 */
	private Region residence;

	/**
	 * 注册时Ip
	 */
	private String registerIp; // 注册时Ip

	private String oldUsername;// 原登录名
	/**
	 * 新密码
	 */
	private String newPassword;

	/**
	 * 教育程度
	 */
	private Education education;

	/**
	 * 用户基本信息
	 */
	private UserInfo userInfo;
	/**
	 * 用户企业信息
	 */
	private UserCorpInfo userCorpInfo;
	/**
	 * 用户健康信息
	 */
	private UserHealthInfo userHealthInfo;

	/**
	 * 用户登录授权
	 */
	private UserLoginAuthorization userLoginAuthorization;

	/**
	 * 第三方登录返回用户id
	 * 
	 */
	private String thirdPartUserId;

	/**
	 * 教育经历
	 */
	private String educationExperience;
	/**
	 * 出身地名称
	 */
	private String birthplaceName;

	/**
	 * 居住地名称
	 */
	private String residenceName;
	/**
	 * 第三方IM用户名
	 */
	private String imUsername;
	/**
	 * 期待结识
	 */
	private String lookForwardToMeet;

	/**
	 * 个人签名
	 */
	private String personalSignature;
	/**
	 * 诚信指数
	 */
	private int integrityIndex;

	public AbstractUserInfo() {
		super();
	}

	public AbstractUserInfo(String id) {
		super(id);
	}

	public AbstractUserInfo(String id, String username) {
		super(id);
		this.username = username;
	}

	/**
	 * 返回 用户平台编号
	 * 
	 * @return the sn
	 */
	@SupCol(isUnique = "false")
	@ExcelField(title = "会员编号", value = "sn", align = 2, sort = 1)
	public String getSn() {
		return sn;
	}

	/**
	 * 设置 用户平台编号
	 * 
	 * @param sn
	 *            the sn to set
	 */
	public void setSn(String sn) {
		this.sn = sn;
	}

	/**
	 * 获取登录名
	 * 
	 * @return 登录名
	 */
	@Length(min = 1, max = 100)
	@ExcelField(title = "登录名", value = "username", align = 2, sort = 30)
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * 获取头像
	 * 
	 * @return 头像
	 */
	@Length(min = 0, max = 255)
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
	 * 获取生日
	 * 
	 * @return 生日
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getBirth() {
		return birth;
	}

	/**
	 * 设置生日
	 * 
	 * @param birth
	 *            生日
	 */
	public void setBirth(Date birth) {
		this.birth = birth;
	}

	/**
	 * 获取邮箱
	 * 
	 * @return 邮箱
	 */
	@Length(min = 0, max = 255)
	@ExcelField(title = "邮箱", align = 2, sort = 50)
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
	 * 获取性别
	 * 
	 * @return 性别
	 */
	@Length(min = 0, max = 11)
	@ExcelField(title = "性别(1:男,2:女)", dictType = "sex", align = 2, sort = 32)
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
	 * 获取手机号
	 * 
	 * @return 手机号
	 */
	@Length(min = 0, max = 255)
	@ExcelField(title = "手机号", align = 2, sort = 45)
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
	 * 获取真实姓名
	 * 
	 * @return 真实姓名
	 */
	@Length(min = 0, max = 255)
	@ExcelField(title = "姓名", align = 2, sort = 40)
	public String getName() {
		return name;
	}

	/**
	 * 设置真实姓名
	 * 
	 * @param name
	 *            真实姓名
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获取座机电话
	 * 
	 * @return 座机电话
	 */
	@Length(min = 0, max = 255)
	@ExcelField(title = "电话", align = 2, sort = 55)
	public String getPhone() {
		return phone;
	}

	/**
	 * 设置座机电话
	 * 
	 * @param phone
	 *            座机电话
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * 获取居住地
	 * 
	 * @return 居住地
	 */
	public Region getResidence() {
		return residence;
	}

	/**
	 * 设置居住地
	 * 
	 * @param residence
	 *            居住地
	 */
	public void setResidence(Region residence) {
		this.residence = residence;
	}

	/**
	 * 昵称
	 * 
	 * @return the nickname
	 */
	@Length(min = 0, max = 200)
	@ExcelField(title = "昵称", align = 2, sort = 35)
	public String getNickname() {
		return nickname;
	}

	/**
	 * @param nickname
	 *            昵称
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	/**
	 * 获取qq
	 * 
	 * @return qq
	 */
	@Length(min = 0, max = 20)
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
	 * 获取出生地
	 * 
	 * @return 出生地
	 */
	public Region getBirthplace() {
		return birthplace;
	}

	/**
	 * 设置 出生地
	 * 
	 * @param birthplace
	 *            the birthplace to set
	 */
	public void setBirthplace(Region birthplace) {
		this.birthplace = birthplace;
	}

	/**
	 * 获取身份证
	 * 
	 * @return 身份证
	 */
	@Length(min = 0, max = 64)
	public String getIdentityCard() {
		return identityCard;
	}

	/**
	 * 设置身份证
	 * 
	 * @param identityCard
	 *            身份证
	 */
	public void setIdentityCard(String identityCard) {
		this.identityCard = identityCard;
	}

	/**
	 * 获取宗教信仰
	 * 
	 * @return 宗教信仰
	 */
	public ReligiousBelief getReligiousBelief() {
		return religiousBelief;
	}

	/**
	 * 设置宗教信仰
	 * 
	 * @param religiousBelief
	 *            宗教信仰
	 */
	public void setReligiousBelief(ReligiousBelief religiousBelief) {
		this.religiousBelief = religiousBelief;
	}

	/**
	 * 获取民族
	 * 
	 * @return 民族
	 */
	public Nation getNation() {
		return nation;
	}

	/**
	 * 设置民族
	 * 
	 * @param nation
	 *            民族
	 */
	public void setNation(Nation nation) {
		this.nation = nation;
	}

	/**
	 * 获取注册时Ip
	 * 
	 * @return 注册时Ip
	 */
	@Length(min = 1, max = 255)
	public String getRegisterIp() {
		return registerIp;
	}

	/**
	 * 设置注册时Ip
	 * 
	 * @param registerIp
	 *            注册时Ip
	 */
	public void setRegisterIp(String registerIp) {
		this.registerIp = registerIp;
	}

	public String getOldUsername() {
		return oldUsername;
	}

	public void setOldUsername(String oldUsername) {
		this.oldUsername = oldUsername;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	/**
	 * 返回 用户基本信息
	 * 
	 * 不保证能返回 请使用
	 * 
	 * @see com.weimhc.modules.user.entity.AbstractUserInfo#getLatestUserInfo()
	 * 
	 * @return the userInfo
	 */
	@JsonIgnore
	@Deprecated
	public UserInfo getUserInfo() {
		return userInfo;
	}

	/**
	 * 返回 用户最新的基本信息
	 * 
	 * @return the userInfo
	 */
	@JsonIgnore
	public UserInfo getLatestUserInfo() {
		if (userInfo == null) {
			userInfo = new UserInfo();
		}
		// 避免自己拷贝自己
		BeanUtils.copyProperties(this, userInfo, "userInfo", "isNewRecord");
		return userInfo;
	}

	public void refreshUserInfo() {
		if (userInfo != null) {
			// 避免自己拷贝自己
			BeanUtils.copyProperties(userInfo, this, "userInfo", "isNewRecord");
		}
	}

	/**
	 * 设置 用户基本信息
	 * 
	 * @param userInfo
	 *            the userInfo to set
	 */
	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	/**
	 * 返回 教育程度
	 * 
	 * @return the education
	 */
	public Education getEducation() {
		return education;
	}

	/**
	 * 设置 教育程度
	 * 
	 * @param education
	 *            the education to set
	 */
	public void setEducation(Education education) {
		this.education = education;
	}

	/**
	 * 返回 第三方登录返回用户id
	 * 
	 * @return the thirdPartUserId
	 */
	public String getThirdPartUserId() {
		return thirdPartUserId;
	}

	/**
	 * 设置 第三方登录返回用户id
	 * 
	 * @param thirdPartUserId
	 *            the thirdPartUserId to set
	 */
	public void setThirdPartUserId(String thirdPartUserId) {
		this.thirdPartUserId = thirdPartUserId;
	}

	/**
	 * 返回 用户企业信息
	 * 
	 * @return the userCorpInfo
	 */
	public UserCorpInfo getUserCorpInfo() {
		if (userCorpInfo == null) {
			userCorpInfo = new UserCorpInfo();
		}
		return userCorpInfo;
	}

	/**
	 * 设置 用户企业信息
	 * 
	 * @param userCorpInfo
	 *            the userCorpInfo to set
	 */
	public void setUserCorpInfo(UserCorpInfo userCorpInfo) {
		this.userCorpInfo = userCorpInfo;
	}

	/**
	 * 返回 用户健康信息
	 * 
	 * @return the userHealthInfo
	 */
	public UserHealthInfo getUserHealthInfo() {
		if (userHealthInfo == null) {
			userHealthInfo = new UserHealthInfo();
		}
		return userHealthInfo;
	}

	/**
	 * 设置 用户健康信息
	 * 
	 * @param userHealthInfo
	 *            the userHealthInfo to set
	 */
	public void setUserHealthInfo(UserHealthInfo userHealthInfo) {
		this.userHealthInfo = userHealthInfo;
	}

	/**
	 * 返回 积分
	 * 
	 * @return the point
	 */
	public Integer getPoint() {
		return point;
	}

	/**
	 * 设置 积分
	 * 
	 * @param point
	 *            the point to set
	 */
	public void setPoint(Integer point) {
		this.point = point;
	}

	/**
	 * 返回 用户登录授权
	 * 
	 * @return the userLoginAuthorization
	 */
	public UserLoginAuthorization getUserLoginAuthorization() {
		if (userLoginAuthorization == null) {
			userLoginAuthorization = new UserLoginAuthorization();
		}
		return userLoginAuthorization;
	}

	/**
	 * 设置 用户登录授权
	 * 
	 * @param userLoginAuthorization
	 *            the userLoginAuthorization to set
	 */
	public void setUserLoginAuthorization(UserLoginAuthorization userLoginAuthorization) {
		this.userLoginAuthorization = userLoginAuthorization;
	}

	/**
	 * 返回 教育经历
	 * 
	 * @return the educationExperience
	 */
	public String getEducationExperience() {
		return educationExperience;
	}

	/**
	 * 设置 教育经历
	 * 
	 * @param educationExperience
	 *            the educationExperience to set
	 */
	public void setEducationExperience(String educationExperience) {
		this.educationExperience = educationExperience;
	}

	/**
	 * 返回 出身地名称
	 * 
	 * @return the birthplaceName
	 */
	public String getBirthplaceName() {
		return birthplaceName;
	}

	/**
	 * 设置 出身地名称
	 * 
	 * @param birthplaceName
	 *            the birthplaceName to set
	 */
	public void setBirthplaceName(String birthplaceName) {
		this.birthplaceName = birthplaceName;
	}

	/**
	 * 返回 居住地名称
	 * 
	 * @return the residenceName
	 */
	public String getResidenceName() {
		return residenceName;
	}

	/**
	 * 设置 居住地名称
	 * 
	 * @param residenceName
	 *            the residenceName to set
	 */
	public void setResidenceName(String residenceName) {
		this.residenceName = residenceName;
	}

	/**
	 * 返回 第三方IM用户名
	 * 
	 * @return the imUsername
	 */
	public String getImUsername() {
		return imUsername;
	}

	/**
	 * 设置 第三方IM用户名
	 * 
	 * @param imUsername
	 *            the imUsername to set
	 */
	public void setImUsername(String imUsername) {
		this.imUsername = imUsername;
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
	public void setWechatId(String wechatId) {
		this.wechatId = wechatId;
	}

	/**
	 * 返回 期待结识
	 * 
	 * @return the lookForwardToMeet
	 */
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

	/**
	 * 返回 诚信指数
	 * 
	 * @return the integrityIndex
	 */
	public int getIntegrityIndex() {
		return integrityIndex;
	}

	/**
	 * 设置 诚信指数
	 * 
	 * @param integrityIndex
	 *            the integrityIndex to set
	 */
	public void setIntegrityIndex(int integrityIndex) {
		this.integrityIndex = integrityIndex;
	}
}