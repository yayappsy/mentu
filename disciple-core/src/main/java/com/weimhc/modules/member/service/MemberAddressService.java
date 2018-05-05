/**
 * 
 */
package com.weimhc.modules.member.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.javamg.common.persistence.Page;
import com.weimhc.framework.service.impl.CrudServiceImpl;
import com.weimhc.modules.member.dao.MemberAddressDao;
import com.weimhc.modules.member.entity.MemberAddress;

/**
 * 会员地址Service
 * 
 * @author liuchao
 * @version 2016-10-12
 */
@Service
@Transactional(readOnly = true)
public class MemberAddressService extends CrudServiceImpl<MemberAddressDao, MemberAddress> {

	@Override
	public MemberAddress get(String id) {
		return super.get(id);
	}

	@Override
	public long count(MemberAddress memberAddress) {
		return super.count(memberAddress);
	}

	@Override
	public boolean exists(String id) {
		return super.exists(id);
	}

	@Override
	public List<MemberAddress> findList(MemberAddress memberAddress) {
		return super.findList(memberAddress);
	}

	@Override
	public Page<MemberAddress> findPage(Page<MemberAddress> page, MemberAddress memberAddress) {
		return super.findPage(page, memberAddress);
	}

	@Override
	@Transactional(readOnly = false)
	public void save(MemberAddress memberAddress) {
		if (memberAddress.getIsDefault()) {
			dao.resetDefaultAddress(memberAddress);
		}
		if (memberAddress.getIsNewRecord()) {
			memberAddress.preInsert();
			dao.insert(memberAddress);
		} else {
			memberAddress.preUpdate();
			dao.update(memberAddress);
		}
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteEntity(MemberAddress memberAddress) {
		super.deleteEntity(memberAddress);
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteEntity(String... ids) {
		if (ids != null) {
			MemberAddress memberAddress = null;
			for (String id : ids) {
				memberAddress = new MemberAddress(id);
				deleteEntity(memberAddress);
			}
		}
	}

}