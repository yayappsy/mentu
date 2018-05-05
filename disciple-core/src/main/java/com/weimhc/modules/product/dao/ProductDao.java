/**
 * 
 */
package com.weimhc.modules.product.dao;

import java.util.List;

import com.thinkgem.javamg.common.persistence.annotation.MyBatisDao;
import com.weimhc.framework.persistence.CrudDao;
import com.weimhc.modules.product.entity.Product;

/**
 * 商品DAO接口
 * 
 * @author lc
 * @version 2017-01-03
 */
@MyBatisDao
public interface ProductDao extends CrudDao<Product> {
	/**
	 * 维护商品与扩展属性之间的关系
	 * 
	 * @param role
	 * @return
	 */
	public int deleteProperties(Product product);

	public int insertProperties(Product product);

	List<Product> findProductPackage(Product product);

	List<Product> findProductLesson(Product product);

	List<Product> findStatsPackage(Product product);

	List<Product> findStatsLesson(Product product);
}