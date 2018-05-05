/**
 * 
 */
package com.weimhc.modules.user.entity;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thinkgem.javamg.common.persistence.DataEntity;

/**
 * 用户授权信息Entity
 * 
 * @author zsm
 * @version 2017-02-15
 */
public class UserAuth extends DataEntity<UserAuth> {

	private static final long serialVersionUID = 1L;
	/**
	 * 用户id
	 * 
	 */
	private UserInfo userInfo;
	/**
	 * 登录类型
	 * 
	 */
	private IdentityType identityType;
	/**
	 * 登录系统权限
	 */
	private BusinessSystem businessSystem;
	/**
	 * 登录标识
	 * 
	 */
	private String identifier;
	/**
	 * 真密码凭证
	 * 
	 */
	private String credential;
	/**
	 * 是否第三方登录，默认为false
	 */
	private Boolean ifThirdPart = Boolean.FALSE;

	public UserAuth() {
		super();
	}

	public UserAuth(String id) {
		super(id);
	}

	public UserAuth(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	/**
	 * 获取用户
	 * 
	 * @return 用户
	 */
	@NotNull
	@JsonIgnore
	public UserInfo getUserInfo() {
		return userInfo;
	}

	/**
	 * 设置用户
	 * 
	 * @param userInfo
	 *            用户
	 */
	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	/**
	 * 获取登录类型
	 * 
	 * @return 登录类型
	 */
	public IdentityType getIdentityType() {
		return identityType;
	}

	/**
	 * 设置登录类型
	 * 
	 * @param identityType
	 *            登录类型
	 */
	public void setIdentityType(IdentityType identityType) {
		this.identityType = identityType;
	}

	/**
	 * 获取标识
	 * 
	 * @return 标识
	 */
	@Length(min = 1, max = 255)
	public String getIdentifier() {
		return identifier;
	}

	/**
	 * 设置标识
	 * 
	 * @param identifier
	 *            标识
	 */
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	/**
	 * 获取真密码凭证
	 * 
	 * @return 真密码凭证
	 */
	@Length(min = 0, max = 255)
	public String getCredential() {
		return credential;
	}

	/**
	 * 设置真密码凭证
	 * 
	 * @param credential
	 *            真密码凭证
	 */
	public void setCredential(String credential) {
		this.credential = credential;
	}

	/**
	 * 返回 是否第三方登录，默认为false
	 * 
	 * @return the ifThirdPart
	 */
	public Boolean getIfThirdPart() {
		return ifThirdPart;
	}

	/**
	 * 设置 是否第三方登录，默认为false
	 * 
	 * @param ifThirdPart
	 *            the ifThirdPart to set
	 */
	public void setIfThirdPart(Boolean ifThirdPart) {
		this.ifThirdPart = ifThirdPart;
	}

}