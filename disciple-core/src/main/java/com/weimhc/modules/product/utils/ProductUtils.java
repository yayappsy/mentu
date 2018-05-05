package com.weimhc.modules.product.utils;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.thinkgem.javamg.common.utils.CacheUtils;
import com.thinkgem.javamg.common.utils.SpringContextHolder;
import com.weimhc.modules.product.dao.ProductBrandDao;
import com.weimhc.modules.product.dao.ProductCategoryDao;
import com.weimhc.modules.product.entity.ProductBrand;
import com.weimhc.modules.product.entity.ProductCategory;

/**
 * @author szuo
 *
 */
public abstract class ProductUtils {

	private static ProductCategoryDao productCategoryDao = SpringContextHolder
			.getBean(ProductCategoryDao.class);

	private static ProductBrandDao productBrandDao = SpringContextHolder
			.getBean(ProductBrandDao.class);

	/** 商品相关缓存 */
	public final static String PRODUCT_CACHE = "productCache";

	/** 商品类型缓存前缀 */
	public final static String CACHE_PRODUCT_TYPE_ID = "pid_";

	/** 商品分类列表 */
	public final static String CACHE_PRODUCT_CATEGORY_LIST = "productCategoryList";
	public final static String CACHE_PRODUCT_CATEGORY_MAP_BY_PARENT = "productCategoryMapByParentId";
	public final static String CACHE_PRODUCT_CATEGORY_MAP_BY_ID = "productCategoryMapById";

	/** 品牌系列列表 */
	public final static String CACHE_PRODUCT_BRAND_LIST = "productBrandList";

	/** 值字符串分隔符 */
	public final static String VAVLUES_SEPARATOR = ",";

	/** 缓存key分隔符 */
	public final static String CACHE_KEY_SEPARATOR = "_";

	/**
	 * 获取商品相关缓存
	 * 
	 * @param key
	 * @return
	 */
	public static Object getCache(String key) {
		return getCache(key, null);
	}

	/**
	 * 获取商品相关缓存
	 * 
	 * @param key
	 * @return
	 */
	public static Object getCache(String key, Object defaultValue) {
		Object obj = CacheUtils.get(PRODUCT_CACHE, key);
		return obj == null ? defaultValue : obj;
	}

	/**
	 * 设置商品相关缓存
	 * 
	 * @param key
	 * @return
	 */
	public static void putCache(String key, Object value) {
		CacheUtils.put(PRODUCT_CACHE, key, value);
	}

	/**
	 * 根据key值，清除商品相关缓存
	 * 
	 * @param key
	 */
	public static void removeCache(String key) {
		CacheUtils.remove(PRODUCT_CACHE, key);
	}

	/**
	 * 获取所有商品分类
	 * 
	 * @return
	 */
	public static List<ProductCategory> findProductCategoryAll() {
		@SuppressWarnings("unchecked")
		List<ProductCategory> list = (List<ProductCategory>) getCache(
				CACHE_PRODUCT_CATEGORY_LIST);
		if (list == null) {
			list = productCategoryDao.findAllList(new ProductCategory());
			putCache(CACHE_PRODUCT_CATEGORY_LIST, list);
		}
		return list;
	}

	/**
	 * 根据父类目获取子类目
	 * 
	 * @param parentId
	 * @return
	 */
	public static List<ProductCategory> findCategoryByParentId(
			String parentId) {
		@SuppressWarnings("unchecked")
		Map<String, List<ProductCategory>> categoryMap = (Map<String, List<ProductCategory>>) CacheUtils
				.get(CACHE_PRODUCT_CATEGORY_MAP_BY_PARENT);
		if (categoryMap == null) {
			categoryMap = Maps.newHashMap();
			for (ProductCategory category : findProductCategoryAll()) {
				List<ProductCategory> categoryList = categoryMap
						.get(category.getParentId());
				if (categoryList != null) {
					categoryList.add(category);
				} else {
					categoryMap.put(category.getParentId(),
							Lists.newArrayList(category));
				}
			}
			CacheUtils.put(CACHE_PRODUCT_CATEGORY_MAP_BY_PARENT, categoryMap);
		}
		List<ProductCategory> list = categoryMap.get(parentId);
		if (list == null) {
			list = Lists.newArrayList();
		}
		return list;
	}

	/**
	 * 根据categoryId获取分类
	 * 
	 * @param parentId
	 * @return
	 */
	public static ProductCategory findCategoryById(String categoryId) {
		@SuppressWarnings("unchecked")
		Map<String, ProductCategory> categoryMap = (Map<String, ProductCategory>) CacheUtils
				.get(CACHE_PRODUCT_CATEGORY_MAP_BY_ID);
		if (categoryMap == null) {
			categoryMap = Maps.newHashMap();
			for (ProductCategory category : findProductCategoryAll()) {
				ProductCategory cate = categoryMap.get(category.getId());
				if (cate != null) {
					continue;
				} else {
					categoryMap.put(category.getId(), category);
				}
			}
			CacheUtils.put(CACHE_PRODUCT_CATEGORY_MAP_BY_ID, categoryMap);
		}
		ProductCategory category = categoryMap.get(categoryId);
		if (category == null) {
			category = new ProductCategory();
		}
		return category;
	}

	/**
	 * 获取所有品牌类型
	 * 
	 * @return
	 */
	public static List<ProductBrand> findProductBrandAll() {
		@SuppressWarnings("unchecked")
		List<ProductBrand> list = (List<ProductBrand>) getCache(
				CACHE_PRODUCT_BRAND_LIST);
		if (list == null) {
			ProductBrand brand = new ProductBrand();
			list = productBrandDao.findAllList(brand);
			putCache(CACHE_PRODUCT_BRAND_LIST, list);
		}
		return list;
	}

}
