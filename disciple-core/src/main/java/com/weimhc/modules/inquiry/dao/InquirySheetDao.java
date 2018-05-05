/**
 * 
 */
package com.weimhc.modules.inquiry.dao;

import com.thinkgem.javamg.common.persistence.annotation.MyBatisDao;
import com.weimhc.framework.persistence.CrudDao;
import com.weimhc.modules.inquiry.entity.InquirySheet;

/**
 * 询价单DAO接口
 * 
 * @author zsm
 * @version 2017-04-10
 */
@MyBatisDao
public interface InquirySheetDao extends CrudDao<InquirySheet> {
	/**
	 * 更新询价单状态
	 * 
	 * @param inquirySheet
	 * @return
	 */
	int updateStatus(InquirySheet inquirySheet);
}