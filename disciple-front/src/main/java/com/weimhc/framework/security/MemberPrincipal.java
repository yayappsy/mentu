package com.weimhc.framework.security;

import java.io.Serializable;

import com.thinkgem.javamg.common.security.BasePrincipal;
import com.weimhc.modules.user.entity.AbstractUserInfo;

/**
 * 授权用户信息
 */
public class MemberPrincipal extends BasePrincipal implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 用户标志前缀
	 */
	public static final String PREFIX = "member_";

	/**
	 * 
	 * @param member
	 * @param appLogin
	 *            是否app登录
	 */
	@SuppressWarnings("rawtypes")
	public MemberPrincipal(AbstractUserInfo member, boolean appLogin) {
		super.setId(member.getId());
		super.setUsername(member.getUsername());
		super.setName(member.getNickname());
		super.setAppLogin(appLogin);
		super.setTokenPrefix(PREFIX);
	}

}
