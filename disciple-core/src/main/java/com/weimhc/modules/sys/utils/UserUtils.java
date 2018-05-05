/**
 * 
 */
package com.weimhc.modules.sys.utils;

import org.apache.commons.lang3.StringUtils;

import com.thinkgem.javamg.common.security.BasePrincipal;
import com.thinkgem.javamg.common.security.utils.LoginUserUtils;
import com.thinkgem.javamg.common.utils.CacheUtils;
import com.thinkgem.javamg.common.utils.SpringContextHolder;
import com.weimhc.modules.sys.dao.RoleDao;
import com.weimhc.modules.sys.dao.UserDao;
import com.weimhc.modules.sys.entity.Role;
import com.weimhc.modules.sys.entity.User;

/**
 * 核心项目用户工具类，用于获取用户信息
 * 
 * @version 2013-12-05
 */
public class UserUtils {

	private static UserDao userDao = SpringContextHolder.getBean(UserDao.class);
	private static RoleDao roleDao = SpringContextHolder.getBean(RoleDao.class);

	public static final String USER_CACHE = "userCache";
	public static final String USER_CACHE_ID_ = "id_";
	public static final String USER_CACHE_USERNAME_ = "ln";

	/**
	 * 根据ID获取用户
	 * 
	 * @param id
	 * @return 取不到返回null
	 */
	public static User get(String id) {
		User user = (User) CacheUtils.get(USER_CACHE, USER_CACHE_ID_ + id);
		if (user == null) {
			user = userDao.get(id);
			if (user == null) {
				return null;
			}
			user.setRoleList(roleDao.findList(new Role(user)));
			putUserCache(user);
		}
		// 如果获取到的数据中，id为空，则重新查询数据
		if (StringUtils.isBlank(user.getId())) {
			user.setId(id);
			clearCache(user);
			return get(id);
		}
		return user;
	}

	public static void putUserCache(User user) {
		CacheUtils.put(USER_CACHE, USER_CACHE_ID_ + user.getId(), user);
		CacheUtils.put(USER_CACHE, USER_CACHE_USERNAME_ + user.getUsername(), user);
	}

	/**
	 * 根据登录名获取用户
	 * 
	 * @param username
	 * @return 取不到返回null
	 */
	public static User getByUsername(String username) {
		User user = (User) CacheUtils.get(USER_CACHE, USER_CACHE_USERNAME_ + username);
		if (user == null) {
			user = userDao.getByUsername(new User(null, username));
			if (user == null) {
				return null;
			}
			user.setRoleList(roleDao.findList(new Role(user)));
			putUserCache(user);
		}
		return user;
	}

	/**
	 * 清除指定用户缓存
	 * 
	 * @param userId
	 */
	public static void clearCache(String userId) {
		clearCache(get(userId));
	}

	/**
	 * 清除指定用户缓存
	 * 
	 * @param user
	 */
	public static void clearCache(User user) {
		if (user == null) {
			return;
		}
		if (StringUtils.isNotBlank(user.getId())) {
			CacheUtils.remove(USER_CACHE, USER_CACHE_ID_ + user.getId());
		}
		if (StringUtils.isNotBlank(user.getUsername())) {
			CacheUtils.remove(USER_CACHE, USER_CACHE_USERNAME_ + user.getUsername());
		}
		if (StringUtils.isNotBlank(user.getOldUsername())) {
			CacheUtils.remove(USER_CACHE, USER_CACHE_ID_ + user.getId());
		}
	}

	/**
	 * 获取当前用户
	 * 
	 * @return 取不到返回 new User()
	 */
	public static User getCurrentUser() {
		BasePrincipal principal = LoginUserUtils.getPrincipal();
		if (principal != null) {
			User user = UserUtils.get(principal.getId());
			if (user != null) {
				return user;
			}
			return new User();
		}
		// 如果没有登录，则返回实例化空的User对象。
		return new User();
	}
}
