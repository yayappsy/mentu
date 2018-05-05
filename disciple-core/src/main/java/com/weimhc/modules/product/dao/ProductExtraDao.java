/**
 * 
 */
package com.weimhc.modules.product.dao;

import com.thinkgem.javamg.common.persistence.annotation.MyBatisDao;
import com.weimhc.modules.product.entity.ProductExtra;

import com.weimhc.framework.persistence.CrudDao;

/**
 * 商品附加信息DAO接口
 * @author lc
 * @version 2017-06-07
 */
@MyBatisDao
public interface ProductExtraDao extends CrudDao<ProductExtra> {

}