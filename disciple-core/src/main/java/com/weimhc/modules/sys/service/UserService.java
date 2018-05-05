/**
 * Copyright &copy; 2012-2013 <a href="#">javamg</a> All rights reserved.
 */
package com.weimhc.modules.sys.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.javamg.common.service.impl.CrudServiceImpl;
import com.weimhc.modules.sys.dao.UserDao;
import com.weimhc.modules.sys.entity.User;

/**
 * 管家Service
 * 
 * @version 2014-05-16
 */
@Service
@Transactional(readOnly = true)
public class UserService extends CrudServiceImpl<UserDao, User> {

	@Override
	public User get(String id) {
		return dao.get(id);
	}

	/**
	 * 不再使用
	 */
	@Override
	@Deprecated
	public void save(User entity) {
		super.save(entity);
	}

	public List<User> findAllUser(User user) {
		return dao.findAllList(user);
	}
}
