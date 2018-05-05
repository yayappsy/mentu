/**
 * 
 */
package com.weimhc.modules.member.dao;

import com.thinkgem.javamg.common.persistence.annotation.MyBatisDao;
import com.weimhc.framework.persistence.CrudDao;
import com.weimhc.modules.member.entity.MemberAddress;

/**
 * 会员地址DAO接口
 * 
 * @author liuchao
 * @version 2016-10-12
 */
@MyBatisDao
public interface MemberAddressDao extends CrudDao<MemberAddress> {

	/**
	 * 重置默认地址
	 * 
	 * @param memberAddress
	 */
	void resetDefaultAddress(MemberAddress memberAddress);

}