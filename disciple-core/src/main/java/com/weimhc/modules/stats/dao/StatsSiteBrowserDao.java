/**
 * 
 */
package com.weimhc.modules.stats.dao;

import java.util.List;

import com.thinkgem.javamg.common.persistence.annotation.MyBatisDao;
import com.weimhc.framework.persistence.CrudDao;
import com.weimhc.modules.stats.entity.StatsSiteBrowser;

/**
 * 统计浏览器DAO接口
 * 
 * @author lc
 * @version 2017-04-12
 */
@MyBatisDao
public interface StatsSiteBrowserDao extends CrudDao<StatsSiteBrowser> {

	List<StatsSiteBrowser> findSiteBrowser(StatsSiteBrowser statsSiteBrowser);

}