/**
 * 
 */
package com.weimhc.modules.product.dao;

import com.thinkgem.javamg.common.persistence.annotation.MyBatisDao;
import com.weimhc.modules.product.entity.ProductInstalment;

import com.weimhc.framework.persistence.CrudDao;

/**
 * 商品分期信息DAO接口
 * @author lc
 * @version 2017-06-07
 */
@MyBatisDao
public interface ProductInstalmentDao extends CrudDao<ProductInstalment> {

}