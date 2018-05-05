/**
 * 
 */
package com.weimhc.modules.member.dao;

import com.thinkgem.javamg.common.persistence.annotation.MyBatisDao;
import com.weimhc.framework.persistence.CrudDao;
import com.weimhc.modules.member.entity.Member;

/**
 * 会员DAO接口
 * 
 * @author zsm
 * @version 2016-01-12
 */
@MyBatisDao
public interface MemberDao extends CrudDao<Member> {

	/**
	 * 判断用户名是否存在
	 * 
	 * @param username
	 *            用户名(忽略大小写)
	 * @return 用户名是否存在
	 */
	boolean usernameExists(String username);

	/**
	 * 判断E-mail是否存在
	 * 
	 * @param email
	 *            E-mail(忽略大小写)
	 * @return E-mail是否存在
	 */
	boolean emailExists(String email);

	/**
	 * 判断手机是否存在
	 * 
	 * @param mobile
	 *            mobile
	 * @return mobile是否存在
	 */
	boolean mobileExists(String mobile);

	/**
	 * 更新登录信息，如：登录IP、登录时间
	 * 
	 * @param user
	 * @return
	 */
	public int updateLoginInfo(Member member);

	/**
	 * 更新个人信息，如：头像、生日、真实姓名等
	 * 
	 * @param user
	 * @return
	 */
	public int updateProfile(Member member);

	/**
	 * 更新用户密码
	 * 
	 * @param user
	 * @return
	 */
	public int updatePasswordById(Member member);

	/**
	 * 更新用户密码
	 * 
	 * @param user
	 * @return
	 */
	void updateMemberLoginFailure(Member member);

	/**
	 * 增加会员
	 * 
	 */
	public void addMember(Member member);

	/**
	 * 更新会员信息
	 * 
	 */
	public void updateMember(Member member);

	/**
	 * 更新头像
	 * 
	 */
	public int updateAvatarById(Member member);

	/**
	 * 会员编号是否存在
	 * 
	 * @param member
	 * @return
	 */
	boolean snExists(Member member);
}