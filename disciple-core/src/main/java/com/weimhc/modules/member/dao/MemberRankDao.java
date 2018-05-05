/**
 * 
 */
package com.weimhc.modules.member.dao;

import com.thinkgem.javamg.common.persistence.annotation.MyBatisDao;
import com.weimhc.framework.persistence.CrudDao;
import com.weimhc.modules.member.entity.MemberRank;

/**
 * 会员等级DAO接口
 * @author lc
 * @version 2016-11-30
 */
@MyBatisDao
public interface MemberRankDao extends CrudDao<MemberRank> {
	
}