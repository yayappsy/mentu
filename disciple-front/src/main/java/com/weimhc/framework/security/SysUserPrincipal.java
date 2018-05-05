package com.weimhc.framework.security;

import java.io.Serializable;

import com.thinkgem.javamg.common.security.BasePrincipal;
import com.weimhc.modules.user.entity.AbstractUserInfo;

/**
 * 授权用户信息
 */
public class SysUserPrincipal extends BasePrincipal implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 用户标志前缀
	 */
	public static final String PREFIX = "user_";

	/**
	 * 
	 * @param member
	 * @param appLogin
	 *            是否app登录
	 */
	public SysUserPrincipal(AbstractUserInfo user, boolean appLogin) {
		super.setId(user.getId());
		super.setUsername(user.getUsername());
		super.setName(user.getName());
		super.setAppLogin(appLogin);
		super.setTokenPrefix(PREFIX);
	}

}
