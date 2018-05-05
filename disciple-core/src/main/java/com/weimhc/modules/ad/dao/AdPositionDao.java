/**
 * 
 */
package com.weimhc.modules.ad.dao;

import com.thinkgem.javamg.common.persistence.annotation.MyBatisDao;
import com.weimhc.framework.persistence.CrudDao;
import com.weimhc.modules.ad.entity.AdPosition;

/**
 * 广告位DAO接口
 * 
 * @author lc
 * @version 2016-06-07
 */
@MyBatisDao
public interface AdPositionDao extends CrudDao<AdPosition> {

}