/**
 * 
 */
package com.weimhc.modules.member.entity;

import com.weimhc.modules.user.entity.AbstractUserInfo;
import com.weimhc.modules.user.entity.UserInfo;

/**
 * 会员Entity
 * 
 * @author zsm
 * @version 2016-01-12
 */
public class Member extends AbstractUserInfo<Member> {

	/** "身份信息"参数名称 */
	public static final String PRINCIPAL_ATTRIBUTE_NAME = "Member.PRINCIPAL";

	/** "openId"Cookie名称 */
	public static final String OPEN_ID_COOKIE_NAME = "openId";

	/** "weixin_user_Id"Cookie名称 */
	public static final String WEIXIN_USER_ID_COOKIE_NAME = "weixinUserId";

	/**
	 * 
	 */
	private static final long serialVersionUID = 1848153354135438065L;
	
	//private 

	public Member() {
		super();

	}

	public Member(String id) {
		super(id);
	}

	public Member(UserInfo userInfo) {
		super(userInfo.getId());
		this.setUserInfo(userInfo);
	}

	public Member(String id, String username) {
		super(id, username);
	}

}