/**
 * 
 */
package com.weimhc.api.modules.dto.resp;

import com.weimhc.api.modules.dto.resp.user.MemberDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 登录返回数据 DTO
 * 
 * @author zsm
 * @version 2016-10-05
 */
@ApiModel
public class LoginDto extends MemberDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1095501047336970672L;
	/**
	 * token
	 * 
	 */
	private String token;

	private String type;

	@ApiModelProperty(value = "用户类型：（1，普通用户，2企业用户）", example = "1")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	/**
	 * 获取 token
	 * 
	 * @return the token
	 */
	@ApiModelProperty(value = "登录凭证")
	public String getToken() {
		return token;
	}

	/**
	 * 设定 token
	 * 
	 * @param token
	 *            the token to set
	 */
	public void setToken(String token) {
		this.token = token;
	}

}