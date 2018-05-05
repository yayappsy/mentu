/**
 * 
 */
package com.weimhc.modules.product.dao;

import com.thinkgem.javamg.common.persistence.annotation.MyBatisDao;
import com.weimhc.framework.persistence.CrudDao;
import com.weimhc.modules.product.entity.ProductProperty;

/**
 * 商品属性DAO接口
 * 
 * @author zsm
 * @version 2016-02-04
 */
@MyBatisDao
public interface ProductPropertyDao extends CrudDao<ProductProperty> {

	/**
	 * 根据商品删除所有属性
	 * 
	 * @param productProperty
	 * @return
	 */
	int deleteByProductId(ProductProperty productProperty);
}