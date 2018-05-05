/**
 * 
 */
package com.weimhc.api.modules.dto.resp.user;

import com.weimhc.framework.dto.AbstractIdDto;
import com.weimhc.framework.utils.UploadUtils;
import io.swagger.annotations.ApiModelProperty;

/**
 * 会员DTO
 * 
 * @author zsm
 * @version 2016-10-05
 */
public class MemberProfileDto extends AbstractIdDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1095501047336970672L;

	/**
	 * 会员头像
	 */
	private String avatar;

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
	 * 获取会员头像
	 * 
	 * @return the avatar
	 */
	@ApiModelProperty(value = "会员头像")
	public String getAvatar() {
		return UploadUtils.getAccessThumbnailUrl(avatar);
	}

	/**
	 * 设置会员头像
	 * 
	 * @param avatar
	 *            the avatar to set
	 */
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	/**
	 * 返回真实姓名
	 * 
	 * @return the realname
	 */
	@ApiModelProperty(value = "真实姓名")
	public String getName() {
		return name;
	}

	/**
	 * 设置 真实姓名
	 * 
	 * @param realname
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获取会员名称
	 * 
	 * @return the nickname
	 */
	@ApiModelProperty(value = "昵称", example = "aaa")
	public String getNickname() {
		return nickname;
	}

	/**
	 * 设置昵称s
	 * 
	 * @param nickname
	 *            the nickname to set
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
}