package com.weimhc.modules.base.utils.setting;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class ProductSettingUtils {

	private static Logger logger = LoggerFactory
			.getLogger(ProductSettingUtils.class);

	/** 商品设置 */
	public static final String PRODUCT_SETTING = "product";

	/**
	 * 库存分配时间点
	 */
	public enum StockAllocationTime {

		/** 下订单 */
		order,

		/** 订单支付 */
		payment,

		/** 订单发货 */
		ship
	}

	/** 商品设置 code */
	public enum ProductSetting {

		/** 是否前台显示市场价 */
		isShowMarketPrice,

		/** 默认市场价换算比例 */
		defaultMarketPriceScale,
	}
	// ============== 网站金额设置 ==============

	/**
	 * 获取是否前台显示市场价
	 * 
	 * @return 是否前台显示市场价
	 */
	@NotNull
	public static Boolean getIsShowMarketPrice() {
		String value = SettingUtils.getSettingValue(
				ProductSetting.isShowMarketPrice.name(), PRODUCT_SETTING);
		Boolean isShowMarketPrice = Boolean.TRUE;
		if (!StringUtils.isBlank(value)) {
			isShowMarketPrice = BooleanUtils.toBooleanObject(value);
		}
		return isShowMarketPrice;
	}

	/**
	 * 获取默认市场价换算比例
	 * 
	 * @return 默认市场价换算比例
	 */
	@NotNull
	@Min(0)
	@Digits(integer = 3, fraction = 3)
	public static Double getDefaultMarketPriceScale() {
		String value = SettingUtils.getSettingValue(
				ProductSetting.defaultMarketPriceScale.name(), PRODUCT_SETTING);
		Double defaultMarketPriceScale = new Double("1.2");
		if (!StringUtils.isBlank(value)) {
			defaultMarketPriceScale = NumberUtils.toDouble(value);
		}
		return defaultMarketPriceScale;
	}

}