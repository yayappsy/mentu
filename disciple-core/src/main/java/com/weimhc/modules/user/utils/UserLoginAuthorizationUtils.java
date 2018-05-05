/**
 * 
 */
package com.weimhc.modules.user.utils;

import java.util.List;

import com.thinkgem.javamg.common.utils.SpringContextHolder;
import com.weimhc.modules.sys.dao.UserDao;
import com.weimhc.modules.sys.entity.Role;
import com.weimhc.modules.sys.entity.User;
import com.weimhc.modules.user.dao.UserLoginAuthorizationDao;
import com.weimhc.modules.user.entity.BusinessSystem;
import com.weimhc.modules.user.entity.UserInfo;
import com.weimhc.modules.user.entity.UserLoginAuthorization;

/**
 * 用户授权信息工具类，用于获取用户授权信息
 * 
 * @version 2013-12-05
 */
public class UserLoginAuthorizationUtils {

	private static UserDao userDao = SpringContextHolder.getBean(UserDao.class);

	private static UserLoginAuthorizationDao userLoginAuthorizationDao = SpringContextHolder
			.getBean(UserLoginAuthorizationDao.class);

	/**
	 * 通过用户信息获取用户登录授权列表
	 * 
	 * @param userInfo
	 *            用户
	 * @param businessSystem
	 *            业务系统
	 * @return 取不到返回null
	 */
	public static UserLoginAuthorization get(UserInfo userInfo,
			BusinessSystem businessSystem) {
		UserLoginAuthorization searchEntity = new UserLoginAuthorization(
				userInfo);
		searchEntity.setBusinessSystem(businessSystem);
		return userLoginAuthorizationDao.getEntity(searchEntity);
	}

	/**
	 * 通过用户信息获取用户登录授权列表
	 * 
	 * @param userInfo
	 *            用户
	 * @return 取不到返回null
	 */
	public static List<UserLoginAuthorization> getList(UserInfo userInfo) {
		UserLoginAuthorization searchEntity = new UserLoginAuthorization(
				userInfo);
		return userLoginAuthorizationDao.findList(searchEntity);
	}

	/**
	 * 添加登录后台系统凭证
	 * 
	 * @param userInfo
	 * @return
	 */
	public static int addUserAppSys(UserInfo userInfo, List<Role> roleList) {
		User user = new User(userInfo);
		user.setRoleList(roleList);
		UserLoginAuthorization userLoginAuthorization = new UserLoginAuthorization(
				user.getLatestUserInfo());
		userLoginAuthorization.setBusinessSystem(BusinessSystem.admin);
		boolean ifExist = userLoginAuthorizationDao
				.count(userLoginAuthorization) == 0 ? false : true;
		if (ifExist) {
			userLoginAuthorization.setIfEnabled(true);
			userLoginAuthorizationDao.insert(userLoginAuthorization);
			userDao.insertUserRole(user);
		}
		return 0;
	}

	/**
	 * 删除登录后台系统凭证
	 * 
	 * @param userInfo
	 * @return
	 */
	public static int deleteUserAppSys(UserInfo userInfo) {
		UserLoginAuthorization userLoginAuthorization = new UserLoginAuthorization(
				userInfo);
		userLoginAuthorization.setBusinessSystem(BusinessSystem.admin);
		userLoginAuthorizationDao.deleteEntity(userLoginAuthorization);
		return 0;
	}

}
