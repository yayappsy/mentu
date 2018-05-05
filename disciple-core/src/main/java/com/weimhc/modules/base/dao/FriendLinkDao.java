/**
 * 
 */
package com.weimhc.modules.base.dao;

import com.thinkgem.javamg.common.persistence.annotation.MyBatisDao;
import com.weimhc.framework.persistence.CrudDao;
import com.weimhc.modules.base.entity.FriendLink;

/**
 * 友情链接DAO接口
 * @author lc
 * @version 2016-08-18
 */
@MyBatisDao
public interface FriendLinkDao extends CrudDao<FriendLink> {
	
}