/**
 * 
 */
package com.weimhc.modules.product.dao;

import com.thinkgem.javamg.common.persistence.annotation.MyBatisDao;
import com.weimhc.framework.persistence.TreeDao;
import com.weimhc.modules.product.entity.ProductCategory;

/**
 * 商品分类DAO接口
 * 
 * @author lc
 * @version 2017-01-03
 */
@MyBatisDao
public interface ProductCategoryDao extends TreeDao<ProductCategory> {

}