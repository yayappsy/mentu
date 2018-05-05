/**
 * 
 */
package com.weimhc.modules.sys.dao;

import java.util.List;

import com.thinkgem.javamg.common.persistence.annotation.MyBatisDao;
import com.weimhc.framework.persistence.CrudDao;
import com.weimhc.modules.sys.entity.User;

/**
 * 后台用户DAO接口
 * 
 * @author zsm
 * @version 2016-02-18
 */
@MyBatisDao
public interface UserDao extends CrudDao<User> {

	/**
	 * 根据登录名称查询用户
	 * 
	 * @param username
	 * @return
	 */
	public User getByUsername(User user);

	/**
	 * 通过OfficeId获取用户列表，仅返回用户id和name（树查询用户时用）
	 * 
	 * @param user
	 * @return
	 */
	public List<User> findUserByOfficeId(User user);

	/**
	 * 查询全部用户数目
	 * 
	 * @return
	 */
	public long findAllCount(User user);

	/**
	 * 删除用户角色关联数据
	 * 
	 * @param user
	 * @return
	 */
	public int deleteUserRole(User user);

	/**
	 * 插入用户角色关联数据
	 * 
	 * @param user
	 * @return
	 */
	public int insertUserRole(User user);

}
