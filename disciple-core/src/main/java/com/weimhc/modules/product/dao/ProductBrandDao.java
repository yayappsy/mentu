/**
 * 
 */
package com.weimhc.modules.product.dao;

import com.thinkgem.javamg.common.persistence.annotation.MyBatisDao;
import com.weimhc.framework.persistence.CrudDao;
import com.weimhc.modules.product.entity.ProductBrand;

/**
 * 品牌系列DAO接口
 * 
 * @author zsm
 * @version 2016-02-01
 */
@MyBatisDao
public interface ProductBrandDao extends CrudDao<ProductBrand> {

}