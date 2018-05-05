/**
 * 
 */
package com.weimhc.modules.base.dao;

import com.thinkgem.javamg.common.persistence.annotation.MyBatisDao;
import com.weimhc.framework.persistence.SortableDao;
import com.weimhc.modules.base.entity.Interest;

/**
 * 兴趣 DAO接口
 */
@MyBatisDao
public interface InterestDao extends SortableDao<Interest> {

}