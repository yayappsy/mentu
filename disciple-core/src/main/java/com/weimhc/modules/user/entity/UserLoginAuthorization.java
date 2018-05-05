/**
 * 
 */
package com.weimhc.modules.user.entity;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.javamg.common.persistence.DataEntity;

/**
 * 平台用户登录授权表Entity
 * 
 * @author zsm
 * @version 2017-09-05
 */
public class UserLoginAuthorization extends DataEntity<UserLoginAuthorization> {

	private static final long serialVersionUID = 1L;
	/**
	 * 通用信息
	 * 
	 */
	private UserInfo userInfo;
	/**
	 * 授权登录系统
	 * 
	 */
	private BusinessSystem businessSystem;
	/**
	 * 是否可登录
	 * 
	 */
	private Boolean ifEnabled;
	/**
	 * 是否锁定
	 * 
	 */
	private Boolean ifLocked;
	/**
	 * 锁定时间
	 * 
	 */
	private Date lockedDate;
	/**
	 * 最后登录时间
	 * 
	 */
	private Date loginDate;
	/**
	 * 登录次数
	 * 
	 */
	private Integer loginNum;
	/**
	 * 登录失败重复次数
	 * 
	 */
	private Integer loginFailureCount;
	/**
	 * 最后登录ip
	 * 
	 */
	private String loginIp;

	private String oldLoginIp; // 上次登陆IP

	private Date oldLoginDate; // 上次登陆日期

	public UserLoginAuthorization() {
		super();
	}

	public UserLoginAuthorization(String id) {
		super(id);
	}

	/**
	 * @param userInfo
	 */
	public UserLoginAuthorization(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	/**
	 * 获取用户通用信息
	 * 
	 * @return 用户通用信息
	 */
	@NotNull()
	public UserInfo getUserInfo() {
		return userInfo;
	}

	/**
	 * 设置用户通用信息
	 * 
	 * @param userInfo
	 *            用户 通用信息
	 */
	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	/**
	 * 获取授权登录系统
	 * 
	 * @return 授权登录系统
	 */
	@NotNull()
	public BusinessSystem getBusinessSystem() {
		return businessSystem;
	}

	/**
	 * 设置授权登录系统
	 * 
	 * @param businessSystem
	 *            授权登录系统
	 */
	public void setBusinessSystem(BusinessSystem businessSystem) {
		this.businessSystem = businessSystem;
	}

	/**
	 * 获取是否可登录
	 * 
	 * @return 是否可登录
	 */
	public Boolean getIfEnabled() {
		return ifEnabled;
	}

	/**
	 * 设置是否可登录
	 * 
	 * @param ifEnabled
	 *            是否可登录
	 */
	public void setIfEnabled(Boolean ifEnabled) {
		this.ifEnabled = ifEnabled;
	}

	/**
	 * 获取是否锁定
	 * 
	 * @return 是否锁定
	 */
	public Boolean getIfLocked() {
		return ifLocked;
	}

	/**
	 * 设置是否锁定
	 * 
	 * @param ifLocked
	 *            是否锁定
	 */
	public void setIfLocked(Boolean ifLocked) {
		this.ifLocked = ifLocked;
	}

	/**
	 * 获取锁定时间
	 * 
	 * @return 锁定时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getLockedDate() {
		return lockedDate;
	}

	/**
	 * 设置锁定时间
	 * 
	 * @param lockedDate
	 *            锁定时间
	 */
	public void setLockedDate(Date lockedDate) {
		this.lockedDate = lockedDate;
	}

	/**
	 * 获取最后登录时间
	 * 
	 * @return 最后登录时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getLoginDate() {
		return loginDate;
	}

	/**
	 * 设置最后登录时间
	 * 
	 * @param loginDate
	 *            最后登录时间
	 */
	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}

	/**
	 * 获取登录次数
	 * 
	 * @return 登录次数
	 */
	@NotNull()
	public Integer getLoginNum() {
		return loginNum;
	}

	/**
	 * 设置登录次数
	 * 
	 * @param loginNum
	 *            登录次数
	 */
	public void setLoginNum(Integer loginNum) {
		this.loginNum = loginNum;
	}

	/**
	 * 获取登录失败重复次数
	 * 
	 * @return 登录失败重复次数
	 */
	@NotNull()
	public Integer getLoginFailureCount() {
		return loginFailureCount;
	}

	/**
	 * 设置登录失败重复次数
	 * 
	 * @param loginFailureCount
	 *            登录失败重复次数
	 */
	public void setLoginFailureCount(Integer loginFailureCount) {
		this.loginFailureCount = loginFailureCount;
	}

	/**
	 * 获取最后登录ip
	 * 
	 * @return 最后登录ip
	 */
	@Length(min = 0, max = 255)
	public String getLoginIp() {
		return loginIp;
	}

	/**
	 * 设置最后登录ip
	 * 
	 * @param loginIp
	 *            最后登录ip
	 */
	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

	/***
	 * 如果上次登录ip为空，返回最近登录ip
	 * 
	 * @return
	 */
	public String getOldLoginIp() {
		if (oldLoginIp == null) {
			return loginIp;
		}
		return oldLoginIp;
	}

	public void setOldLoginIp(String oldLoginIp) {
		this.oldLoginIp = oldLoginIp;
	}

	/***
	 * 如果上次登录时间为空，返回最近登录时间
	 * 
	 * @return
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getOldLoginDate() {
		if (oldLoginDate == null) {
			return loginDate;
		}
		return oldLoginDate;
	}

	public void setOldLoginDate(Date oldLoginDate) {
		this.oldLoginDate = oldLoginDate;
	}

	@Override
	public void setDefaultValue() {
		super.setDefaultValue();
		if (this.ifEnabled == null) {
			ifEnabled = Boolean.TRUE;
		}
		if (this.ifLocked == null) {
			ifLocked = Boolean.FALSE;
		}
	}

}