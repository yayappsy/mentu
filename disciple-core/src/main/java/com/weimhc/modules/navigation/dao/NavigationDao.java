/**
 * 
 */
package com.weimhc.modules.navigation.dao;

import com.thinkgem.javamg.common.persistence.annotation.MyBatisDao;
import com.weimhc.framework.persistence.TreeDao;
import com.weimhc.modules.navigation.entity.Navigation;

/**
 * 导航栏DAO接口
 * 
 * @author zsm
 * @version 2017-03-27
 */
@MyBatisDao
public interface NavigationDao extends TreeDao<Navigation> {

}