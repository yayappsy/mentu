/**
 * 
 */
package com.weimhc.modules.member.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.javamg.common.persistence.Page;
import com.weimhc.framework.service.impl.CrudServiceImpl;
import com.weimhc.modules.member.dao.MemberRankDao;
import com.weimhc.modules.member.entity.MemberRank;

/**
 * 会员等级Service
 * @author lc
 * @version 2016-11-30
 */
@Service
@Transactional(readOnly = true)
public class MemberRankService extends CrudServiceImpl<MemberRankDao, MemberRank> {

	public MemberRank get(String id) {
		return super.get(id);
	}

	public long count(MemberRank memberRank) {
		return super.count(memberRank);
	}
	
	public boolean exists(String id) {
		return super.exists(id);
	}
	
	public List<MemberRank> findList(MemberRank memberRank) {
		return super.findList(memberRank);
	}
	
	public Page<MemberRank> findPage(Page<MemberRank> page, MemberRank memberRank) {
		return super.findPage(page, memberRank);
	}
	
	@Transactional(readOnly = false)
	public void save(MemberRank memberRank) {
		super.save(memberRank);
	}
	
	@Transactional(readOnly = false)
	public void deleteEntity(MemberRank memberRank) {
		super.deleteEntity(memberRank);
	}
	
	@Transactional(readOnly = false)
	public void deleteEntity(String... ids) {
		if (ids != null) {
			MemberRank memberRank = null;
			for (String id : ids) {
				memberRank = new MemberRank(id);
				deleteEntity(memberRank);
			}
		}
	}
	
}