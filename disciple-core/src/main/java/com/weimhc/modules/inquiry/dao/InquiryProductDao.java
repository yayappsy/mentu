/**
 * 
 */
package com.weimhc.modules.inquiry.dao;

import com.thinkgem.javamg.common.persistence.annotation.MyBatisDao;
import com.weimhc.framework.persistence.CrudDao;
import com.weimhc.modules.inquiry.entity.InquiryProduct;

/**
 * 询价产品DAO接口
 * @author zsm
 * @version 2017-04-10
 */
@MyBatisDao
public interface InquiryProductDao extends CrudDao<InquiryProduct> {
	
}