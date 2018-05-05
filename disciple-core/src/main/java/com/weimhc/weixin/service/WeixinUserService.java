/**
 * 
 */
package com.weimhc.weixin.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.javamg.common.persistence.Page;
import com.weimhc.framework.service.impl.CrudServiceImpl;
import com.weimhc.weixin.dao.WeixinUserDao;
import com.weimhc.weixin.entity.WeixinUser;

/**
 * 微信用户Service
 * 
 * @author zsm
 * @version 2016-07-06
 */
@Service
@Transactional(readOnly = true)
public class WeixinUserService
		extends CrudServiceImpl<WeixinUserDao, WeixinUser> {

	@Override
	public WeixinUser get(String id) {
		return super.get(id);
	}

	public long count(WeixinUser weixinUser) {
		return super.count(weixinUser);
	}

	@Override
	public boolean exists(String id) {
		return super.exists(id);
	}

	public List<WeixinUser> findList(WeixinUser weixinUser) {
		return super.findList(weixinUser);
	}

	public Page<WeixinUser> findPage(Page<WeixinUser> page,
			WeixinUser weixinUser) {
		return super.findPage(page, weixinUser);
	}

	@Transactional(readOnly = false)
	public void save(WeixinUser weixinUser) {
		super.save(weixinUser);
	}

	@Transactional(readOnly = false)
	public void deleteEntity(WeixinUser weixinUser) {
		super.deleteEntity(weixinUser);
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteEntity(String... ids) {
		if (ids != null) {
			WeixinUser weixinUser = null;
			for (String id : ids) {
				weixinUser = new WeixinUser(id);
				deleteEntity(weixinUser);
			}
		}
	}

}