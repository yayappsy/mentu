/*
 * 
 * 
 * 
 */
package com.weimhc.modules.member.utils;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.thinkgem.javamg.common.utils.CacheUtils;
import com.thinkgem.javamg.common.utils.SpringContextHolder;
import com.weimhc.modules.member.dao.MemberRankDao;
import com.weimhc.modules.member.entity.MemberRank;

/**
 * Utils - 用户等级
 * 
 * 
 */
public abstract class MemberRankUtils {

	private static MemberRankDao memberRankDao = SpringContextHolder
			.getBean(MemberRankDao.class);

	/**
	 * 用户等级相关缓存 暂时未使用
	 */
	public final static String MEMBER_RANK_CACHE = "memberRankCache";

	/** 用户等级相关缓存列表 */
	public final static String CACHE_MEMBER_RANK_LIST = "memberRank_list";
	/** 用户等级相关缓存列表 */
	public final static String CACHE_MEMBER_RANK_BY_ID = "memberRank_id";

	/**
	 * 获取用户等级相关缓存
	 * 
	 * @param key
	 * @return
	 */
	public static Object getCache(String key) {
		return getCache(key, null);
	}

	/**
	 * 获取相关缓存 从默认缓存中
	 * 
	 * @param key
	 * @return
	 */
	public static Object getCache(String key, Object defaultValue) {
		Object obj = CacheUtils.get(key);
		return obj == null ? defaultValue : obj;
	}

	/**
	 * 设置用户等级相关缓存
	 * 
	 * @param key
	 * @return
	 */
	public static void putCache(String key, Object value) {
		CacheUtils.put(key, value);
	}

	/**
	 * 获取所有的用户等级
	 * 
	 * @return
	 */
	public static List<MemberRank> findAll() {
		@SuppressWarnings("unchecked")
		List<MemberRank> list = (List<MemberRank>) getCache(
				CACHE_MEMBER_RANK_LIST);
		if (list == null) {
			list = memberRankDao.findAllList(new MemberRank());
			putCache(CACHE_MEMBER_RANK_LIST, list);
		}
		return list;

	}

	/**
	 * id查询用户等级
	 * 
	 * @param id
	 * @return
	 */
	public static MemberRank findById(String id) {
		List<MemberRank> memberRankList = findAll();
		for (MemberRank memberRank : memberRankList) {
			if (StringUtils.equals(id, memberRank.getId())) {
				return memberRank;
			}
		}
		return new MemberRank();
	}

	/**
	 * id查询用户等级
	 * 
	 * @param id
	 * @return
	 */
	public static MemberRank findDefault() {
		List<MemberRank> memberRankList = findAll();
		for (MemberRank memberRank : memberRankList) {
			if (memberRank.getIsDefault()) {
				return memberRank;
			}
		}
		return new MemberRank("3");
	}

}