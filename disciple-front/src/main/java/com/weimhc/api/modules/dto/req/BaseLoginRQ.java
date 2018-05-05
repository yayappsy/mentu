package com.weimhc.api.modules.dto.req;

import com.weimhc.framework.dto.req.AbstractRQ;
import com.weimhc.modules.user.entity.IdentityType;
import io.swagger.annotations.ApiModelProperty;

/**
 * 登录使用的请求
 * 
 * @author szuo
 *
 */
public class BaseLoginRQ extends AbstractRQ {

	private static final long serialVersionUID = -2659037797172700696L;

	/**
	 * 登录标识（用户名或手机号或邮箱）
	 */
	private String identifier;
	/**
	 * 密码
	 */
	private String password;

	/**
	 * 登录类型
	 */
	private IdentityType identityType;

	/**
	 * 类型
	 */
	private String type;


	/**
	 * 获取密码
	 * 
	 * @return the password
	 */
	@ApiModelProperty(value = "密码", example = "123456")
	public String getPassword() {
		return password;
	}

	/**
	 * 设置密码
	 * 
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * 获取 标识
	 * 
	 * @return 标识
	 * 
	 */
	@ApiModelProperty(value = "登录标识（手机号或用户名或邮箱）", example = "18810000000", required = true)
	public String getIdentifier() {
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
	 * 获取登录类型
	 * 
	 * @return 登录类型
	 */
	@ApiModelProperty(value = "登录类型", example = "mobile")
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
	@ApiModelProperty(value = "用户类型：（1，普通用户，2企业用户,3门徒求职）", example = "1")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
