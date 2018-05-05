package com.weimhc.modules.ad.entity;

/**
 * 广告类型
 * 
 * @author szuo
 *
 */
public enum AdType {
	/**
	 * 普通广告
	 */
	normal(null),

	/** 对联广告 **/
	couplet(AdType.normal),

	/** 图片广告 **/
	image(AdType.normal),

	/** 文字广告 **/
	text(AdType.normal),

	/** 弹出层广告 **/
	pop(AdType.normal),

	/** 浮动广告 **/
	floating(AdType.normal),

	/**
	 * 轮播
	 */
	slider(null);

	/**
	 * 分组
	 */
	private AdType parent;

	private AdType(AdType parent) {
		this.parent = parent;
	}

	/**
	 * @return 返回最顶层的广告类型
	 */
	public AdType getGroup() {
		if (this.parent != null) {
			return parent.getGroup();
		}
		return this;
	}
}