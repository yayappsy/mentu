/**
 * 
 */
package com.weimhc.modules.base.utils;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.stuxuhai.jpinyin.PinyinException;
import com.github.stuxuhai.jpinyin.PinyinHelper;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.thinkgem.javamg.common.mapper.JsonMapper;
import com.thinkgem.javamg.common.utils.CacheUtils;
import com.thinkgem.javamg.common.utils.SpringContextHolder;
import com.weimhc.modules.base.dao.RegionDao;
import com.weimhc.modules.base.entity.Region;

/**
 * 地区工具类
 * 
 * @author zsm
 * @version 2016-02-18
 */
public class RegionUtils {

	private static Logger logger = LoggerFactory.getLogger(RegionUtils.class);

	private static RegionDao regionDao = SpringContextHolder.getBean(RegionDao.class);

	/** 地区相关缓存 */
	public final static String REGION_CACHE = "regionCache";

	/** 根据type查询地区相关缓存 */
	public final static String CACHE_REGION_MAP_BY_TYPE = "regionMap_type";

	/** 地区按照上级 */
	public static final String CACHE_REGION_MAP_BY_PARENT_ID = "regionMap_pid";

	/***/
	public static final String CACHE_REGION_MAP_BY_ID = "regionMap_id";

	/** 所有地区 */
	public static final String CACHE_REGION_LIST = "regionList";

	/** 中国 */
	public static final String REGION_CHINA_ID = "100000";

	/** 初始节点 */
	public static final String REGION_INIT_ID = "0";

	/** 所有省市区 */
	public static final String CACHE_REGION_MAP_FOR_APP = "appRegions";

	/** 使用首字母节点 */
	public static final String CACHE_REGION_MAP_BY_FIRST_LETTER_AND_TYPE = "region_firstLetter_";

	/**
	 * 获取地区相关缓存
	 * 
	 * @param key
	 * @return
	 */
	public static Object getCache(String key) {
		return getCache(key, null);
	}

	/**
	 * 获取地区相关缓存
	 * 
	 * @param key
	 * @return
	 */
	public static Object getCache(String key, Object defaultValue) {
		Object obj = CacheUtils.get(REGION_CACHE, key);
		return obj == null ? defaultValue : obj;
	}

	/**
	 * 设置地区相关缓存
	 * 
	 * @param key
	 * @return
	 */
	public static void putCache(String key, Object value) {
		CacheUtils.put(REGION_CACHE, key, value);
	}

	/**
	 * 根据key值，清除地区相关缓存
	 * 
	 * @param key
	 */
	public static void removeCache(String key) {
		CacheUtils.remove(REGION_CACHE, key);
	}

	/**
	 * 清除地区相关缓存
	 */
	public static void clearCache() {
		removeCache(CACHE_REGION_LIST);
		removeCache(CACHE_REGION_MAP_BY_ID);
		removeCache(CACHE_REGION_MAP_BY_PARENT_ID);
		removeCache(CACHE_REGION_MAP_BY_TYPE);
	}

	/**
	 * 根据上级地区，本地区id，获取地区名字
	 * 
	 * @param id
	 * @param parentId
	 * @param defaultValue
	 * @return
	 */
	public static String getRegionName(String id, String parentId, String defaultValue) {
		if (StringUtils.isNotBlank(parentId) && StringUtils.isNotBlank(id)) {
			for (Region region : getSubRegionList(parentId)) {
				if (parentId.equals(region.getParentId()) && id.equals(region.getCode())) {
					return region.getName();
				}
			}
		}
		return defaultValue;
	}

	/**
	 * 根据上级地区id，地区名，获得地区id
	 * 
	 * @param name
	 * @param parentId
	 * @param defaultCode
	 * @return
	 */
	public static String getRegionId(String name, String parentId, String defaultValue) {
		if (StringUtils.isNotBlank(parentId) && StringUtils.isNotBlank(name)) {
			for (Region region : getSubRegionList(parentId)) {
				if (parentId.equals(region.getParentId()) && name.equals(region.getName())) {
					return region.getCode();
				}
			}
		}
		return defaultValue;
	}

	/**
	 * 根据父地区 获取regionList
	 * 
	 * @param parentId
	 * @return
	 */
	public static List<Region> getSubRegionList(String parentId) {
		@SuppressWarnings("unchecked")
		Map<String, List<Region>> regionMap = (Map<String, List<Region>>) getCache(
				CACHE_REGION_MAP_BY_PARENT_ID);
		if (regionMap == null) {
			regionMap = Maps.newHashMap();
			// 获取所有地区信息，然后按照父地区分类。
			for (Region region : regionDao.findAllList(new Region())) {
				List<Region> regionList = regionMap.get(region.getParentId());
				if (regionList != null) {
					regionList.add(region);
				} else {
					regionMap.put(region.getParentId(), Lists.newArrayList(region));
				}
			}
			putCache(CACHE_REGION_MAP_BY_PARENT_ID, regionMap);
		}
		List<Region> regionList = regionMap.get(parentId);
		if (regionList == null) {
			regionList = Lists.newArrayList();
		}
		return regionList;
	}

	/**
	 * 获取中国所有省
	 * 
	 * @param parentId
	 * @return
	 */
	public static List<Region> getProvinceList() {
		List<Region> regionList = getSubRegionList(REGION_CHINA_ID);
		return regionList;
	}

	/**
	 * 获取当前用户授权的区域
	 * 
	 * @return
	 */
	public static List<Region> findAll() {
		@SuppressWarnings("unchecked")
		List<Region> regionList = (List<Region>) getCache(CACHE_REGION_LIST);
		if (regionList == null || regionList.isEmpty()) {
			regionList = regionDao.findAllList(new Region());
			putCache(CACHE_REGION_LIST, regionList);
		}
		return regionList;
	}

	/**
	 * 根据type查询地区
	 * 
	 * @return
	 */
	public static List<Region> findRegionByType(String type) {
		@SuppressWarnings("unchecked")
		Map<String, List<Region>> regionTypeMap = (Map<String, List<Region>>) getCache(
				CACHE_REGION_MAP_BY_TYPE);
		if (regionTypeMap == null) {
			regionTypeMap = Maps.newHashMap();
		}
		List<Region> regionList = regionTypeMap.get(type);
		if (regionList == null || regionList.size() == 0) {
			regionList = Lists.newArrayList();
			for (Region region : findAll()) {
				if (StringUtils.equals(type, region.getType())) {
					if (StringUtils.isNotBlank(region.getFirstLetter())) {
						regionList.add(region);
					}
				}
			}
			regionTypeMap.put(type, regionList);
			putCache(CACHE_REGION_MAP_BY_TYPE, regionTypeMap);
		}
		return regionList;

	}

	/***
	 * 生成地址Json数据(生成格式为省市区)，供插件使用
	 * 
	 * @param parentId
	 *            父节点
	 * @param stopType
	 *            结束的层级
	 * @return
	 */
	public static List<Map<String, Object>> generateFrontRegionJosn(String parentId,
			String stopType) {
		if (StringUtils.isBlank(parentId)) {
			parentId = REGION_CHINA_ID;
		}
		List<Region> childList = RegionUtils.getSubRegionList(parentId);
		List<Map<String, Object>> mapChildList = Lists.newArrayList();
		for (int i = 0; i < childList.size(); i++) {
			Region e = childList.get(i);
			Map<String, Object> childMap = Maps.newHashMap();
			childMap.put("id", e.getId());
			childMap.put("pId", e.getParentId());
			childMap.put("parentId", e.getParentId());
			childMap.put("name", e.getName());
			childMap.put("type", e.getType());
			if (!StringUtils.equals(e.getType(), stopType)) {
				childMap.put("child", generateFrontRegionJosn(e.getId(), stopType));
			}
			mapChildList.add(childMap);
		}

		return mapChildList;
	}

	/***
	 * 生成地址Json数据(按照字母分类生成)，供插件使用
	 * 
	 * @param parentId
	 *            父节点
	 * @param stopType
	 *            结束的层级
	 * @return
	 */
	public static Map<String, List<Region>> generateFirstLetterRegionJosn(String type) {
		Region region = new Region();
		region.setType(type);
		region.getSqlMap().put("searchType", "firstLetter");
		List<Region> childList = regionDao.findAllList(region);
		Map<String, List<Region>> mapChildList = Maps.newHashMap();
		List<Region> subRegion = null;
		for (int i = 0; i < childList.size(); i++) {
			Region e = childList.get(i);
			subRegion = mapChildList.get(e.getFirstLetter());
			if (subRegion == null) {
				subRegion = Lists.newArrayList(e);
				mapChildList.put(e.getFirstLetter(), subRegion);
			} else {
				subRegion.add(e);
			}
		}
		return mapChildList;
	}

	/***
	 * 生成地址Json数据，供app使用首页
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, List<Region>> getAppFirstLetterRegions(String type) {
		Map<String, List<Region>> regions = (Map<String, List<Region>>) RegionUtils
				.getCache(CACHE_REGION_MAP_BY_FIRST_LETTER_AND_TYPE + type);
		if (regions == null) {
			regions = RegionUtils.generateFirstLetterRegionJosn(type);
			RegionUtils.putCache(CACHE_REGION_MAP_BY_FIRST_LETTER_AND_TYPE + type, regions);
		}
		return regions;
	}

	/***
	 * 生成地址Json数据，供app使用
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Map<String, Object>> getAppRegions() {

		List<Map<String, Object>> regions = (List<Map<String, Object>>) RegionUtils
				.getCache(CACHE_REGION_MAP_FOR_APP);
		if (regions == null) {
			regions = RegionUtils.generateFrontRegionJosn(RegionUtils.REGION_CHINA_ID, "4");
			RegionUtils.putCache(CACHE_REGION_MAP_FOR_APP, regions);
		}
		return regions;
	}

	/**
	 * 生成后台select2使用的json数据，供插件使用
	 * 
	 * @param parentId
	 *            父节点
	 * @param stopType
	 *            结束的层级
	 * @return
	 */
	public static Map<String, Map<String, String>> generateSystemRegionJosn(String parentId,
			String stopType) {
		List<Region> allList = RegionUtils.findAll();
		Map<String, Map<String, String>> maps = Maps.newHashMap();
		System.out.println(allList.size());
		for (int i = 0; i < allList.size(); i++) {
			Region e = allList.get(i);
			if (StringUtils.equals(e.getType(), stopType)) {
				continue;
			}
			List<Region> childList = RegionUtils.getSubRegionList(e.getId());
			Map<String, String> childMap = Maps.newHashMap();
			System.out.println(childList.size());
			for (int j = 0; j < childList.size(); j++) {
				Region c = childList.get(j);
				childMap.put(c.getId(), c.getName());
			}
			System.out.println(e.getParentIds() + e.getId());
			maps.put(e.getParentIds() + e.getId(), childMap);

		}
		System.out.println(JsonMapper.toJsonString(maps));
		return maps;
	}

	/**
	 * 根据id获取地区信息
	 * 
	 * @param parentId
	 * @return
	 */
	public static Region getRegion(String regionId) {
		@SuppressWarnings("unchecked")
		Map<String, Region> regionMap = (Map<String, Region>) CacheUtils
				.get(CACHE_REGION_MAP_BY_ID);
		if (regionMap == null) {
			regionMap = Maps.newHashMap();
			// 获取所有地区信息，然后按照父地区分类。
			for (Region region : findAll()) {
				regionMap.put(region.getId(), region);
			}
			CacheUtils.put(CACHE_REGION_MAP_BY_ID, regionMap);
		}
		// 获取地区的描述
		Region region = regionMap.get(regionId);
		return region;
	}

	/**
	 * 根据对象获取地区信息
	 * 
	 * @param parentId
	 * @return
	 */
	public static Region getRegion(Region region) {
		if (region == null) {
			return null;
		}
		return getRegion(region.getId());
	}

	public static void generateJianPing() {
		List<Region> alls = findAll();
		try {
			for (Region region : alls) {
				region.setJianpin(PinyinHelper.getShortPinyin(region.getShortName()));
				regionDao.update(region);
			}
		} catch (PinyinException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage(), e);
		}
	}
}
