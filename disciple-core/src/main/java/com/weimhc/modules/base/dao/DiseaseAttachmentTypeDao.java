/**
 * 
 */
package com.weimhc.modules.base.dao;

import com.thinkgem.javamg.common.persistence.annotation.MyBatisDao;
import com.weimhc.framework.persistence.CrudDao;
import com.weimhc.modules.base.entity.DiseaseAttachmentType;

/**
 * 疾病附件 DAO接口
 * 
 * @author lc
 * @version 2017-01-06
 */
@MyBatisDao
public interface DiseaseAttachmentTypeDao
		extends CrudDao<DiseaseAttachmentType> {

}