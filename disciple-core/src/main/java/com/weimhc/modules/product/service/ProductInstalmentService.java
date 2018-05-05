/**
 * 
 */
package com.weimhc.modules.product.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weimhc.modules.product.entity.ProductInstalment;
import com.weimhc.modules.product.dao.ProductInstalmentDao;

import com.weimhc.framework.service.impl.CrudServiceImpl;

/**
 * 商品分期信息Service
 * @author lc
 * @version 2017-06-07
 */
@Service
@Transactional(readOnly = true)
public class ProductInstalmentService extends CrudServiceImpl<ProductInstalmentDao, ProductInstalment> {

	
}