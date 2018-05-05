/**
 * 
 */
package com.weimhc.api.modules.dto.req;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.weimhc.framework.dto.req.AbstractRQ;
import com.weimhc.modules.user.entity.IdentityType;
import io.swagger.annotations.ApiModelProperty;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 修改密码 请求
 * 
 * @author zsm
 * @version 2016-10-05
 */
public class UpdatePasswordRQ extends AbstractRQ {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 手机号
	 * 
	 */
	private String mobile;

	/**
	 * 密码
	 * 
	 */
	private String password;
	/**
	 * 新密码
	 * 
	 */
	private String newPassword;
	/**
	 * 登录标识
	 * 
	 */
	private String identifier;

	/**
	 * 登录类型
	 */
	private IdentityType identityType;

	/**
	 * 获取 手机号
	 * 
	 * @return the mobile
	 */
	@ApiModelProperty(value = "手机号", example = "18800000000")
	public String getMobile() {
		return mobile;
	}

	/**
	 * 设定 手机号
	 * 
	 * @param mobile
	 *            the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * 获取 密码
	 * 
	 * @return the password
	 */
	@ApiModelProperty(value = "密码", example = "123456")
	public String getPassword() {
		return password;
	}

	/**
	 * 设定 密码
	 * 
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * 获取登录类型
	 * 
	 * @return 登录类型
	 */
	@ApiModelProperty(value = "登录类型,与字段对应,如果登录类型为mobile,则手机号必填", example = "mobile")
	public IdentityType getIdentityType() {
		return identityType;
	}

	/**
	 * 获取 标识
	 * 
	 * @return 标识
	 * 
	 */
	@ApiIgnore
	@JsonIgnore
	public String getIdentifier() {
		switch (identityType) {
		case mobile:
			identifier = this.mobile;
			break;
		default:
			break;
		}
		return identifier;
	}

	/**
	 * 设置 标识
	 * 
	 * @param identifier
	 *            标识
	 */
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	/**
	 * 获取新密码
	 * 
	 * @return the newPassword
	 */
	@ApiModelProperty(value = "新密码", example = "123456")
	public String getNewPassword() {
		return newPassword;
	}

	/**
	 * 设置新密码
	 * 
	 * @param newPassword
	 *            the newPassword to set
	 */
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

}