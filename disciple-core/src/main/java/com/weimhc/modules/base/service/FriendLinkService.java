/**
 * 
 */
package com.weimhc.modules.base.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.javamg.common.persistence.Page;
import com.weimhc.framework.service.impl.CrudServiceImpl;
import com.weimhc.modules.base.dao.FriendLinkDao;
import com.weimhc.modules.base.entity.FriendLink;

/**
 * 友情链接Service
 * @author lc
 * @version 2016-08-18
 */
@Service
@Transactional(readOnly = true)
public class FriendLinkService extends CrudServiceImpl<FriendLinkDao, FriendLink> {

	public FriendLink get(String id) {
		return super.get(id);
	}

	public long count(FriendLink friendLink) {
		return super.count(friendLink);
	}
	
	public boolean exists(String id) {
		return super.exists(id);
	}
	
	public List<FriendLink> findList(FriendLink friendLink) {
		return super.findList(friendLink);
	}
	
	public Page<FriendLink> findPage(Page<FriendLink> page, FriendLink friendLink) {
		return super.findPage(page, friendLink);
	}
	
	@Transactional(readOnly = false)
	public void save(FriendLink friendLink) {
		super.save(friendLink);
	}
	
	@Transactional(readOnly = false)
	public void deleteEntity(FriendLink friendLink) {
		super.deleteEntity(friendLink);
	}
	
	@Transactional(readOnly = false)
	public void deleteEntity(String... ids) {
		if (ids != null) {
			FriendLink friendLink = null;
			for (String id : ids) {
				friendLink = new FriendLink(id);
				deleteEntity(friendLink);
			}
		}
	}
	
}