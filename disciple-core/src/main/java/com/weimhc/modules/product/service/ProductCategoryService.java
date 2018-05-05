/**
 * 
 */
package com.weimhc.modules.product.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.javamg.common.utils.StringUtils;
import com.weimhc.framework.service.impl.TreeServiceImpl;
import com.weimhc.modules.product.dao.ProductCategoryDao;
import com.weimhc.modules.product.entity.ProductCategory;

/**
 * 商品分类Service
 * 
 * @author lc
 * @version 2017-01-03
 */
@Service
@Transactional(readOnly = true)
public class ProductCategoryService extends TreeServiceImpl<ProductCategoryDao, ProductCategory> {

	@Override
	public ProductCategory get(String id) {
		return super.get(id);
	}

	public List<ProductCategory> findList(ProductCategory productCategory) {
		if (StringUtils.isNotBlank(productCategory.getParentIds())) {
			productCategory.setParentIds("," + productCategory.getParentIds() + ",");
		}
		return super.findList(productCategory);
	}

	@Transactional(readOnly = false)
	public void save(ProductCategory productCategory) {
		super.save(productCategory);
	}

	@Transactional(readOnly = false)
	public void deleteEntity(ProductCategory productCategory) {
		super.deleteEntity(productCategory);
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteEntity(String... ids) {
		if (ids != null) {
			ProductCategory productCategory = null;
			for (String id : ids) {
				productCategory = new ProductCategory(id);
				super.deleteEntity(productCategory);
			}
		}
	}

}