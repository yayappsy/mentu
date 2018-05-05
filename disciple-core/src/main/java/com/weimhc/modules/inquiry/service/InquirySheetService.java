/**
 * 
 */
package com.weimhc.modules.inquiry.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weimhc.framework.service.impl.CrudServiceImpl;
import com.weimhc.modules.inquiry.dao.InquiryProductDao;
import com.weimhc.modules.inquiry.dao.InquirySheetDao;
import com.weimhc.modules.inquiry.entity.InquiryProduct;
import com.weimhc.modules.inquiry.entity.InquirySheet;

/**
 * 询价单Service
 * 
 * @author zsm
 * @version 2017-04-10
 */
@Service
@Transactional(readOnly = true)
public class InquirySheetService
		extends CrudServiceImpl<InquirySheetDao, InquirySheet> {

	@Autowired
	InquiryProductDao inquiryProductDao;

	@Override
	public InquirySheet get(String id) {
		InquirySheet inquirySheet = super.get(id);
		if (inquirySheet != null) {
			inquirySheet.setInquiryProductList(inquiryProductDao
					.findAllList(new InquiryProduct(inquirySheet)));
		}
		return inquirySheet;
	}

	@Override
	@Transactional(readOnly = false)
	public void save(InquirySheet inquirySheet) {
		super.save(inquirySheet);
		if (inquirySheet.getInquiryProductList().size() > 0) {
			inquirySheet.getInquiryProductList().forEach(p -> {
				p.setInquirySheet(inquirySheet);
				if (InquirySheet.DEL_FLAG_NORMAL.equals(p.getDelFlag())) {
					if (StringUtils.isBlank(p.getId())) {
						p.preInsert();
						inquiryProductDao.insert(p);
					} else {
						p.preUpdate();
						inquiryProductDao.update(p);
					}
				} else {
					inquiryProductDao.delete(p);
				}

			});
		}

	}

	/**
	 * 更新询价单状态
	 * 
	 * @param inquirySheet
	 */
	@Transactional(readOnly = false)
	public void updateStatus(InquirySheet inquirySheet) {
		dao.updateStatus(inquirySheet);
	}

}