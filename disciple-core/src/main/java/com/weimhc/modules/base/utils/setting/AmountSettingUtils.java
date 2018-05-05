package com.weimhc.modules.base.utils.setting;

import java.math.BigDecimal;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AmountSettingUtils {

	private static Logger logger = LoggerFactory
			.getLogger(AmountSettingUtils.class);

	/** 商品设置 */
	public static final String AMOUNT_SETTING = "amount";

	/** 商品设置 code */
	public enum AmountSetting {
		/** 价格精确位数 */
		priceScale,

		/** 价格精确方式 */
		priceRoundType,

	}
	// ============== 网站金额设置 ==============

	/**
	 * 获取价格精确位数
	 * 
	 * @return 价格精确位数
	 */
	@NotNull
	@Min(0)
	@Max(3)
	public static Integer getPriceScale() {
		String value = SettingUtils.getSettingValue(
				AmountSetting.priceScale.name(), AMOUNT_SETTING);
		Integer priceScale = new Integer(2);
		if (!StringUtils.isBlank(value)) {
			priceScale = NumberUtils.toInt(value);
		}
		return priceScale;
	}

	/**
	 * 获取价格精确方式
	 * 
	 * @return 价格精确方式
	 */
	@NotNull
	public static RoundType getPriceRoundType() {
		String value = SettingUtils.getSettingValue(
				AmountSetting.priceRoundType.name(), AMOUNT_SETTING);
		RoundType priceRoundType = RoundType.roundHalfUp;
		if (!StringUtils.isBlank(value)) {
			priceRoundType = RoundType.valueOf(value);
		}
		return priceRoundType;
	}

	/**
	 * 设置精度
	 * 
	 * @param amount
	 *            数值
	 * @return 数值
	 */
	public static BigDecimal setScale(BigDecimal amount) {
		if (amount == null) {
			return null;
		}
		int roundingMode;
		if (getPriceRoundType() == RoundType.roundUp) {
			roundingMode = BigDecimal.ROUND_UP;
		} else if (getPriceRoundType() == RoundType.roundDown) {
			roundingMode = BigDecimal.ROUND_DOWN;
		} else {
			roundingMode = BigDecimal.ROUND_HALF_UP;
		}
		return amount.setScale(getPriceScale(), roundingMode);

	}
}