/**
 * 
 */
package com.weimhc.api.modules.dto.req;

import com.weimhc.framework.dto.req.AbstractRQ;
import io.swagger.annotations.ApiModelProperty;

/**
 * App菜单 请求
 * 
 * @author zsm
 * @version 2016-10-05
 */
public class BaseRegisterRQ extends AbstractRQ {

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
	 * 验证码
	 * 
	 */
	private String captcha;
	/**
	 * 类型
	 */
	private String type;

	/**
	 * /** 获取 手机号
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

	@ApiModelProperty(value = "用户类型：（1，普通用户，2企业用户）", example = "1")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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
	 * 获取 验证码
	 * 
	 * @return the captcha
	 */
	@ApiModelProperty(value = "验证码", example = "123456")
	public String getCaptcha() {
		return captcha;
	}

	/**
	 * 设定 验证码
	 * 
	 * @param captcha
	 *            the captcha to set
	 */
	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}

}