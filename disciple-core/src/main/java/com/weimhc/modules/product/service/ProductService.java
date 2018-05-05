/**
 * 
 */
package com.weimhc.modules.product.service;

import java.util.List;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.javamg.common.persistence.Page;
import com.weimhc.framework.service.impl.CrudServiceImpl;
import com.weimhc.framework.utils.ConstantUtils;
import com.weimhc.modules.product.dao.ProductDao;
import com.weimhc.modules.product.dao.ProductExtraDao;
import com.weimhc.modules.product.dao.ProductPropertyDao;
import com.weimhc.modules.product.entity.Product;
import com.weimhc.modules.product.entity.ProductProperty;

/**
 * 商品Service
 * 
 * @author lc
 * @version 2017-01-03
 */
@Service
@Transactional(readOnly = true)
public class ProductService extends CrudServiceImpl<ProductDao, Product> {

	@Autowired
	private ProductPropertyDao productPropertyDao;
	@Autowired
	private ProductExtraDao productExtraDao;

	@Override
	public Product get(String id) {
		Product product = super.get(id);
		if (product != null) {
			ProductProperty productProperty = new ProductProperty();
			productProperty.setProduct(product);
			product.setProductPropertyList(
					productPropertyDao.findAllList(productProperty));
		}
		return product;
	}

	@Override
	public long count(Product product) {
		return super.count(product);
	}

	@Override
	public boolean exists(String id) {
		return super.exists(id);
	}

	@Override
	public List<Product> findList(Product product) {
		return super.findList(product);
	}

	@Override
	public Page<Product> findPage(Page<Product> page, Product product) {
		return super.findPage(page, product);
	}

	@Override
	@Transactional(readOnly = false)
	public void save(Product product) {
		if (product.getProductPropertyList().size() > 0) {
			dao.deleteProperties(product);
		}
		if (product.getProductProperties().size() > 0) {
			Stream<String> names = product.getProductProperties().stream()
					.map(pv -> {
						return pv.getPropertyName()
								+ ConstantUtils.SEPARATOR_COLON
								+ pv.getPropertyValueNames();
					});
			product.setPropertyValues(StringUtils.join(names.toArray(),
					ConstantUtils.SEPARATOR_COMMA));
		} else {
			product.setPropertyValues("");
		}
		if (product.getIsNewRecord()) {
			product.preInsert();
			dao.insert(product);
			productExtraDao.insert(product.getProductExtra());
		} else {
			product.preUpdate();
			dao.update(product);
			productExtraDao.update(product.getProductExtra());
		}
		dao.insertProperties(product);
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteEntity(Product product) {
		super.deleteEntity(product);
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteEntity(String... ids) {
		if (ids != null) {
			Product product = null;
			for (String id : ids) {
				product = new Product(id);
				deleteEntity(product);
			}
		}
	}

	public List<Product> findProductPackage(Product product) {
		return dao.findProductPackage(product);
	}

	public List<Product> findProductLesson(Product product) {
		return dao.findProductLesson(product);
	}

	public List<Product> findStatsPackage(Product product) {
		return dao.findStatsPackage(product);
	}

	public List<Product> findStatsLesson(Product product) {
		return dao.findStatsLesson(product);
	}
}