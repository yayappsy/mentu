/**
 * 
 */
package com.weimhc.modules.sys.utils;

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
import com.weimhc.modules.sys.dao.AreaDao;
import com.weimhc.modules.sys.entity.Area;

/**
 * 地区工具类
 * 
 * @author zsm
 * @version 2016-02-18
 */
public class AreaUtils {

	private static Logger logger = LoggerFactory.getLogger(AreaUtils.class);

	private static AreaDao areaDao = SpringContextHolder.getBean(AreaDao.class);

	/** 地区相关缓存 */
	public final static String AREA_CACHE = "areaCache";

	/** 根据type查询地区相关缓存 */
	public final static String CACHE_AREA_MAP_BY_TYPE = "areaMap_type";

	/** 地区按照上级 */
	public static final String CACHE_AREA_MAP_BY_PARENT_ID = "areaMap_pid";

	/***/
	public static final String CACHE_AREA_MAP_BY_ID = "areaMap_id";

	/** 所有地区 */
	public static final String CACHE_AREA_LIST = "areaList";

	/** 中国 */
	public static final String AREA_CHINA_ID = "100000";

	/** 初始节点 */
	public static final String AREA_INIT_ID = "0";

	/** 所有省市区 */
	public static final String CACHE_AREA_MAP_FOR_APP = "appAreas";

	/** 使用首字母节点 */
	public static final String CACHE_AREA_MAP_BY_FIRST_LETTER_AND_TYPE = "area_firstLetter_";

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
		Object obj = CacheUtils.get(AREA_CACHE, key);
		return obj == null ? defaultValue : obj;
	}

	/**
	 * 设置地区相关缓存
	 * 
	 * @param key
	 * @return
	 */
	public static void putCache(String key, Object value) {
		CacheUtils.put(AREA_CACHE, key, value);
	}

	/**
	 * 根据key值，清除地区相关缓存
	 * 
	 * @param key
	 */
	public static void removeCache(String key) {
		CacheUtils.remove(AREA_CACHE, key);
	}

	/**
	 * 清除地区相关缓存
	 */
	public static void clearCache() {
		removeCache(CACHE_AREA_LIST);
		removeCache(CACHE_AREA_MAP_BY_ID);
		removeCache(CACHE_AREA_MAP_BY_PARENT_ID);
		removeCache(CACHE_AREA_MAP_BY_TYPE);
	}

	/**
	 * 根据上级地区，本地区id，获取地区名字
	 * 
	 * @param id
	 * @param parentId
	 * @param defaultValue
	 * @return
	 */
	public static String getAreaName(String id, String parentId,
			String defaultValue) {
		if (StringUtils.isNotBlank(parentId) && StringUtils.isNotBlank(id)) {
			for (Area area : getSubAreaList(parentId)) {
				if (parentId.equals(area.getParentId())
						&& id.equals(area.getCode())) {
					return area.getName();
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
	public static String getAreaId(String name, String parentId,
			String defaultValue) {
		if (StringUtils.isNotBlank(parentId) && StringUtils.isNotBlank(name)) {
			for (Area area : getSubAreaList(parentId)) {
				if (parentId.equals(area.getParentId())
						&& name.equals(area.getName())) {
					return area.getCode();
				}
			}
		}
		return defaultValue;
	}

	/**
	 * 根据父地区 获取areaList
	 * 
	 * @param parentId
	 * @return
	 */
	public static List<Area> getSubAreaList(String parentId) {
		@SuppressWarnings("unchecked")
		Map<String, List<Area>> areaMap = (Map<String, List<Area>>) getCache(
				CACHE_AREA_MAP_BY_PARENT_ID);
		if (areaMap == null) {
			areaMap = Maps.newHashMap();
			// 获取所有地区信息，然后按照父地区分类。
			for (Area area : areaDao.findAllList(new Area())) {
				List<Area> areaList = areaMap.get(area.getParentId());
				if (areaList != null) {
					areaList.add(area);
				} else {
					areaMap.put(area.getParentId(), Lists.newArrayList(area));
				}
			}
			putCache(CACHE_AREA_MAP_BY_PARENT_ID, areaMap);
		}
		List<Area> areaList = areaMap.get(parentId);
		if (areaList == null) {
			areaList = Lists.newArrayList();
		}
		return areaList;
	}

	/**
	 * 获取中国所有省
	 * 
	 * @param parentId
	 * @return
	 */
	public static List<Area> getProvinceList() {
		List<Area> areaList = getSubAreaList(AREA_CHINA_ID);
		return areaList;
	}

	/**
	 * 获取当前用户授权的区域
	 * 
	 * @return
	 */
	public static List<Area> findAll() {
		@SuppressWarnings("unchecked")
		List<Area> areaList = (List<Area>) getCache(CACHE_AREA_LIST);
		if (areaList == null) {
			areaList = areaDao.findAllList(new Area());
			putCache(CACHE_AREA_LIST, areaList);
		}
		return areaList;
	}

	/**
	 * 根据type查询地区
	 * 
	 * @return
	 */
	public static List<Area> findAreaByType(String type) {
		@SuppressWarnings("unchecked")
		Map<String, List<Area>> areaTypeMap = (Map<String, List<Area>>) getCache(
				CACHE_AREA_MAP_BY_TYPE);
		if (areaTypeMap == null) {
			areaTypeMap = Maps.newHashMap();
		}
		List<Area> areaList = areaTypeMap.get(type);
		if (areaList == null || areaList.size() == 0) {
			areaList = Lists.newArrayList();
			for (Area area : findAll()) {
				if (StringUtils.equals(type, area.getType())) {
					if (StringUtils.isNotBlank(area.getFirstLetter())) {
						areaList.add(area);
					}
				}
			}
			areaTypeMap.put(type, areaList);
			putCache(CACHE_AREA_MAP_BY_TYPE, areaTypeMap);
		}
		return areaList;

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
	public static List<Map<String, Object>> generateFrontAreaJosn(
			String parentId, String stopType) {
		if (StringUtils.isBlank(parentId)) {
			parentId = AREA_CHINA_ID;
		}
		List<Area> childList = AreaUtils.getSubAreaList(parentId);
		List<Map<String, Object>> mapChildList = Lists.newArrayList();
		for (int i = 0; i < childList.size(); i++) {
			Area e = childList.get(i);
			Map<String, Object> childMap = Maps.newHashMap();
			childMap.put("id", e.getId());
			childMap.put("pId", e.getParentId());
			childMap.put("name", e.getName());
			childMap.put("type", e.getType());
			if (!StringUtils.equals(e.getType(), stopType)) {
				childMap.put("child",
						generateFrontAreaJosn(e.getId(), stopType));
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
	public static Map<String, List<Area>> generateFirstLetterAreaJosn(
			String type) {
		Area area = new Area();
		area.setType(type);
		area.getSqlMap().put("searchType", "firstLetter");
		List<Area> childList = areaDao.findAllList(area);
		Map<String, List<Area>> mapChildList = Maps.newHashMap();
		List<Area> subArea = null;
		for (int i = 0; i < childList.size(); i++) {
			Area e = childList.get(i);
			subArea = mapChildList.get(e.getFirstLetter());
			if (subArea == null) {
				subArea = Lists.newArrayList(e);
				mapChildList.put(e.getFirstLetter(), subArea);
			} else {
				subArea.add(e);
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
	public static Map<String, List<Area>> getAppFirstLetterAreas(String type) {
		Map<String, List<Area>> areas = (Map<String, List<Area>>) AreaUtils
				.getCache(CACHE_AREA_MAP_BY_FIRST_LETTER_AND_TYPE + type);
		if (areas == null) {
			areas = AreaUtils.generateFirstLetterAreaJosn(type);
			AreaUtils.putCache(CACHE_AREA_MAP_BY_FIRST_LETTER_AND_TYPE + type,
					areas);
		}
		return areas;
	}

	/***
	 * 生成地址Json数据，供app使用
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Map<String, Object>> getAppAreas() {

		List<Map<String, Object>> areas = (List<Map<String, Object>>) AreaUtils
				.getCache(CACHE_AREA_MAP_FOR_APP);
		if (areas == null) {
			areas = AreaUtils.generateFrontAreaJosn(AreaUtils.AREA_CHINA_ID,
					"4");
			AreaUtils.putCache(CACHE_AREA_MAP_FOR_APP, areas);
		}
		return areas;
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
	public static Map<String, Map<String, String>> generateSystemAreaJosn(
			String parentId, String stopType) {
		List<Area> allList = AreaUtils.findAll();
		Map<String, Map<String, String>> maps = Maps.newHashMap();
		System.out.println(allList.size());
		for (int i = 0; i < allList.size(); i++) {
			Area e = allList.get(i);
			if (StringUtils.equals(e.getType(), stopType)) {
				continue;
			}
			List<Area> childList = AreaUtils.getSubAreaList(e.getId());
			Map<String, String> childMap = Maps.newHashMap();
			System.out.println(childList.size());
			for (int j = 0; j < childList.size(); j++) {
				Area c = childList.get(j);
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
	public static Area getArea(String areaId) {
		@SuppressWarnings("unchecked")
		Map<String, Area> areaMap = (Map<String, Area>) CacheUtils
				.get(CACHE_AREA_MAP_BY_ID);
		if (areaMap == null) {
			areaMap = Maps.newHashMap();
			// 获取所有地区信息，然后按照父地区分类。
			for (Area area : findAll()) {
				areaMap.put(area.getId(), area);
			}
			CacheUtils.put(CACHE_AREA_MAP_BY_ID, areaMap);
		}
		// 获取地区的描述
		Area area = areaMap.get(areaId);
		return area;
	}

	/**
	 * 根据对象获取地区信息
	 * 
	 * @param parentId
	 * @return
	 */
	public static Area getArea(Area area) {
		if (area == null) {
			return null;
		}
		return getArea(area.getId());
	}

	public static void generateJianPing() {
		List<Area> alls = findAll();
		try {
			for (Area area : alls) {
				area.setJianpin(
						PinyinHelper.getShortPinyin(area.getShortName()));
				areaDao.update(area);
			}
		} catch (PinyinException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage(), e);
		}
	}
}
