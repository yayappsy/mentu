/**
 * 
 */
package com.weimhc.api.modules.dto.req;

import com.weimhc.framework.dto.req.AbstractRQ;
import io.swagger.annotations.ApiModelProperty;

/**
 * 修改密码 请求
 * 
 * @author zsm
 * @version 2016-10-05
 */
public class ResetPasswordRQ extends AbstractRQ {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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