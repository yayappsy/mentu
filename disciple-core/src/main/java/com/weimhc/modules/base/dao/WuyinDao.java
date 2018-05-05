/**
 * 
 */
package com.weimhc.modules.base.dao;

import com.thinkgem.javamg.common.persistence.annotation.MyBatisDao;
import com.weimhc.framework.persistence.CrudDao;
import com.weimhc.modules.base.entity.Wuyin;

/**
 * 五音 DAO接口
 * 
 * @author zsm
 * @version 2017-03-06
 */
@MyBatisDao
public interface WuyinDao extends CrudDao<Wuyin> {

}