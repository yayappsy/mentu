package com.weimhc.modules.base.entity;

/**
 * 编号类型
 */
public enum SnType {

	/** 商品 */
	product("0000", "YYYYMMdd"),

	/** 商店 */
	store("0000", "YYYY"),

	/** 订单 */
	order("0000", "YYYYMMdd"),

	/** 会员套餐 */
	memberPackage("0000", "YYYYMMdd"),

	/** 收款单 */
	payment("0000", "YYYYMMdd"),

	/** 退款单 */
	refunds("0000", "YYYYMMdd"),

	/** 发货单 */
	shipping("0000", "YYYYMMdd"),

	/** 退货单 */
	returns("0000", "YYYYMMdd"),

	/** 用户编号 */
	user("0000", "YYYYMMdd"),

	/** 询价单 */
	inquirySheet("0000", "YYYYMMdd"),

	/** 红包编号 */
	redPack("0000000000", "YYYYMMdd"),
	/** 二维码 */
	code("0000", "YYYYMMdd");

	private String numberFormatStr;
	private String dateFormatStr;

	private SnType(String numberFormatStr, String dateFormatStr) {
		this.numberFormatStr = numberFormatStr;
		this.dateFormatStr = dateFormatStr;
	}

	/**
	 * 获取数字格式化模板
	 * 
	 * @return the numberFormatStr
	 */
	public String getNumberFormatStr() {
		return numberFormatStr;
	}

	/**
	 * 设置数字格式化模板
	 * 
	 * @param formatStr
	 *            the formatStr to set
	 */
	public void setNumberFormatStr(String numberFormatStr) {
		this.numberFormatStr = numberFormatStr;
	}

	/**
	 * 获取时间格式化模板
	 * 
	 * @return the dateFormatStr
	 */
	public String getDateFormatStr() {
		return dateFormatStr;
	}

	/**
	 * 设置时间格式化模板
	 * 
	 * @param dateFormatStr
	 *            the dateFormatStr to set
	 */
	public void setDateFormatStr(String dateFormatStr) {
		this.dateFormatStr = dateFormatStr;
	}

}