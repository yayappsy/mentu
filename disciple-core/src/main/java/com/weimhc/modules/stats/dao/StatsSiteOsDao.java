/**
 * 
 */
package com.weimhc.modules.stats.dao;

import java.util.List;

import com.thinkgem.javamg.common.persistence.annotation.MyBatisDao;
import com.weimhc.framework.persistence.CrudDao;
import com.weimhc.modules.stats.entity.StatsSiteOs;

/**
 * 统计网络设备类型DAO接口
 * 
 * @author lc
 * @version 2017-04-12
 */
@MyBatisDao
public interface StatsSiteOsDao extends CrudDao<StatsSiteOs> {

	List<StatsSiteOs> findSiteOs(StatsSiteOs statsSiteOs);

}