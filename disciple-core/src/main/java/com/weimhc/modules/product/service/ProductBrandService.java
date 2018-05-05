/**
 * 
 */
package com.weimhc.modules.product.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.javamg.common.persistence.Page;
import com.weimhc.framework.service.impl.CrudServiceImpl;
import com.weimhc.modules.product.dao.ProductBrandDao;
import com.weimhc.modules.product.entity.ProductBrand;
import com.weimhc.modules.product.utils.ProductUtils;

/**
 * 品牌系列Service
 * 
 * @author zsm
 * @version 2016-02-01
 */
@Service
@Transactional(readOnly = true)
public class ProductBrandService
		extends CrudServiceImpl<ProductBrandDao, ProductBrand> {

	@Override
	public ProductBrand get(String id) {
		return super.get(id);
	}

	@Override
	public long count(ProductBrand productBrand) {
		return super.count(productBrand);
	}

	@Override
	public boolean exists(String id) {
		return super.exists(id);
	}

	@Override
	public List<ProductBrand> findList(ProductBrand productBrand) {
		return super.findList(productBrand);
	}

	@Override
	public Page<ProductBrand> findPage(Page<ProductBrand> page,
			ProductBrand productBrand) {
		return super.findPage(page, productBrand);
	}

	@Override
	@Transactional(readOnly = false)
	public void save(ProductBrand productBrand) {
		ProductUtils.removeCache(ProductUtils.CACHE_PRODUCT_BRAND_LIST);
		super.save(productBrand);
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteEntity(ProductBrand productBrand) {
		ProductUtils.removeCache(ProductUtils.CACHE_PRODUCT_BRAND_LIST);
		super.deleteEntity(productBrand);
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteEntity(String... ids) {
		if (ids != null) {
			ProductBrand productBrand = null;
			for (String id : ids) {
				productBrand = new ProductBrand(id);
				deleteEntity(productBrand);
			}
		}

	}

}