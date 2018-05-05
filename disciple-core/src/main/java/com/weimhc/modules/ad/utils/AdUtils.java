/**
 * 
 */
package com.weimhc.modules.ad.utils;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.thinkgem.javamg.common.utils.CacheUtils;
import com.thinkgem.javamg.common.utils.SpringContextHolder;
import com.thinkgem.javamg.common.utils.StringUtils;
import com.weimhc.modules.ad.dao.AdDao;
import com.weimhc.modules.ad.dao.AdPositionDao;
import com.weimhc.modules.ad.entity.Ad;
import com.weimhc.modules.ad.entity.AdPosition;
import com.weimhc.modules.ad.entity.AdType;

/**
 * @author szuo
 *
 */
public abstract class AdUtils {

	private static AdDao adBasicDao = SpringContextHolder.getBean(AdDao.class);

	private static AdPositionDao adPositionDao = SpringContextHolder
			.getBean(AdPositionDao.class);

	public final static String AD_CACHE = "adCache";
	public final static String AD_CACHE_LIST = "adCacheList";
	/** 广告位的缓存 */
	public final static String CACHE_AD_POSITION_LIST = "adPositionList";
	public final static String CACHE_AD_POSITION_BY_KEYWORD = "adPositionByKeyWordMap";
	public final static String CACHE_AD_POSITION_lIST_BY_AD_TYPE = "adPositionListByAdTypeMap";

	/** 广告缓存 */
	public final static String CACHE_AD_LIST_BY_KEYWORD = "adByKeyWord";

	public final static String CACHE_AD_SORT_LIST = "adSortList";

	/**
	 * 获取广告相关缓存
	 * 
	 * @param key
	 * @return
	 */
	public static Object getCache(String key) {
		return getCache(key, null);
	}

	/**
	 * 获取getCache相关缓存
	 * 
	 * @param key
	 * @return
	 */
	public static Object getCache(String key, Object defaultValue) {
		Object obj = CacheUtils.get(AD_CACHE, key);
		return obj == null ? defaultValue : obj;
	}

	/**
	 * 设置广告相关缓存
	 * 
	 * @param key
	 * @return
	 */
	public static void putCache(String key, Object value) {
		CacheUtils.put(AD_CACHE, key, value);
	}

	/**
	 * 清除广告相关缓存
	 * 
	 * @param key
	 * @return
	 */
	public static void removeCache(String key) {
		CacheUtils.remove(AD_CACHE, key);
	}

	/**
	 * 清除广告相关缓存
	 * 
	 * @param key
	 * @return
	 */
	public static void removeAdCache() {
		removeCache(CACHE_AD_LIST_BY_KEYWORD);
		removeCache(CACHE_AD_SORT_LIST);
		removeCache(CACHE_AD_POSITION_LIST);
		removeCache(CACHE_AD_LIST_BY_KEYWORD);
	}

	/**
	 * 清除广告位相关缓存
	 * 
	 * @param key
	 * @return
	 */
	public static void removeAdPositionCache() {
		removeCache(CACHE_AD_POSITION_BY_KEYWORD);
		removeCache(CACHE_AD_POSITION_lIST_BY_AD_TYPE);
		removeCache(CACHE_AD_POSITION_LIST);
	}

	/**
	 * 获取所有的广告
	 * 
	 * @return
	 */
	public static List<Ad> findAdAll() {
		@SuppressWarnings("unchecked")
		List<Ad> list = (List<Ad>) getCache(AD_CACHE_LIST);
		if (list == null) {
			list = adBasicDao.findAllList(new Ad());
			putCache(AD_CACHE_LIST, list);
		}
		return list;

	}

	/**
	 * 获取所有的广告
	 * 
	 * @return
	 */
	public static List<AdPosition> findAdPositonAll() {
		@SuppressWarnings("unchecked")
		List<AdPosition> list = (List<AdPosition>) getCache(
				CACHE_AD_POSITION_LIST);
		if (list == null || list.size() == 0) {
			list = adPositionDao.findAllList(new AdPosition());
			putCache(CACHE_AD_POSITION_LIST, list);
		}
		return list;
	}

	/**
	 * 根据adPosition获取广告位
	 * 
	 * @return
	 */
	public static AdPosition findAdPosition(AdPosition adPosition) {
		if (adPosition != null && StringUtils.isNotBlank(adPosition.getId())) {
			return findAdPositionById(adPosition.getId());
		}
		return null;
	}

	/**
	 * 根据id获取广告位
	 * 
	 * @return
	 */
	public static AdPosition findAdPositionById(String id) {
		for (AdPosition adPosition : findAdPositonAll()) {
			if (StringUtils.equals(id, adPosition.getId())) {
				return adPosition;
			}
		}
		return null;
	}

	/**
	 * 根据关键字获取广告位
	 * 
	 * @return
	 */
	public static AdPosition findAdPositionByKeyWord(String code) {
		@SuppressWarnings("unchecked")
		Map<String, AdPosition> adMap = (Map<String, AdPosition>) getCache(
				CACHE_AD_POSITION_BY_KEYWORD);
		if (adMap == null) {
			adMap = Maps.newHashMap();
			for (AdPosition adPosition : findAdPositonAll()) {
				AdPosition adp = adMap.get(adPosition.getCode().toString());
				if (adp != null) {
					continue;
				} else {
					adMap.put(adPosition.getCode().toString(), adPosition);
				}
			}
			putCache(CACHE_AD_POSITION_BY_KEYWORD, adMap);
		}
		AdPosition adp = adMap.get(code);
		if (adp == null) {
			adp = new AdPosition();
		}
		return adp;
	}

	/**
	 * 根据最高层广告类型获取广告位列表
	 * 
	 * @return
	 */
	public static List<AdPosition> findAdPositionListByTopAdType(
			AdType topLevelAdType) {
		@SuppressWarnings("unchecked")
		Map<String, List<AdPosition>> adMap = (Map<String, List<AdPosition>>) getCache(
				CACHE_AD_POSITION_lIST_BY_AD_TYPE);
		if (adMap == null) {
			adMap = Maps.newHashMap();
			for (AdPosition adPosition : findAdPositonAll()) {
				List<AdPosition> adps = adMap
						.get(adPosition.getAdType().getGroup().toString());
				if (adps != null) {
					adps.add(adPosition);
				} else {
					adps = Lists.newArrayList(adPosition);
					adMap.put(adPosition.getAdType().getGroup().toString(),
							adps);
				}
			}
			putCache(CACHE_AD_POSITION_lIST_BY_AD_TYPE, adMap);
		}
		return adMap.get(topLevelAdType.toString());
	}

	/**
	 * 根据关键字获取广告
	 * 
	 * @param keyword
	 * @return
	 */
	public static List<Ad> findAdByKeyword(String code) {
		@SuppressWarnings("unchecked")
		List<Ad> list = (List<Ad>) getCache(CACHE_AD_LIST_BY_KEYWORD);
		if (list == null) {
			list = Lists.newArrayList();
			for (Ad ad : findAdAll()) {
				if (ad.getAdPositionCode().equals(code)) {
					list.add(ad);
				}
			}
			putCache(CACHE_AD_LIST_BY_KEYWORD, list);
		}
		return list;
	}

}
