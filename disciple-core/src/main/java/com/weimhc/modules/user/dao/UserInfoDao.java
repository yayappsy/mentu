/**
 * 
 */
package com.weimhc.modules.user.dao;

import com.thinkgem.javamg.common.persistence.annotation.MyBatisDao;
import com.weimhc.framework.persistence.CrudDao;
import com.weimhc.modules.user.entity.UserInfo;
import org.apache.ibatis.annotations.Param;

/**
 * 用户信息DAO接口
 * 
 * @author lc
 * @version 2017-01-04
 */
@MyBatisDao
public interface UserInfoDao extends CrudDao<UserInfo> {

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
	 * 更新用户密码
	 * 
	 * @param user
	 * @return
	 */
	public int updatePasswordById(UserInfo userInfo);

	/**
	 * 更新用户头像
	 */
	public int updateAvatarById(UserInfo userInfo);

	/**
	 * 更新用户基本信息
	 * 
	 * @param userInfo
	 * @return
	 */
	public int updateProfile(UserInfo userInfo);

	/**
	 * 更新第三方通讯账号(环信)
	 * 
	 * @param userInfo
	 */
	public int updateImUsername(UserInfo userInfo);

    UserInfo getByResume(String userId);

    void updateType(@Param(value = "userId") String userId, @Param(value = "type") String type);

	String getType(String userId);

	UserInfo getByUserId(UserInfo userInfo);

}