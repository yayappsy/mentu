package com.weimhc.modules.ad.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * 广告位代码
 * 
 * @author szuo
 *
 */
public enum AdPositionCode {

	/** 首页对联广告 **/
	indexCouplet(AdType.couplet),

	/** 首页图片广告 **/
	indexImage(AdType.image),
	/** 个人中心图片广告 **/
	centerImage(AdType.image),
	/** 首页文字广告 **/
	indexText(AdType.text),

	/** 首页弹出层广告 **/
	indexPop(AdType.pop),

	/** 首页浮动广告 **/
	indexFloating(AdType.floating),

	/** 首页轮播广告位 **/
	indexCarousel(AdType.slider);

	/**
	 * 广告类型
	 */
	private AdType adType;

	private AdPositionCode(AdType adType) {
		this.adType = adType;
	}

	/**
	 * 获取广告类型
	 * 
	 * @return
	 */
	public AdType getAdType() {
		return this.adType;
	}

	/**
	 * 根据AdType 获取 广告位代码列表
	 * 
	 * @param adType
	 * @return
	 */
	public static List<AdPositionCode> getAdPositionCodesByAdType(
			AdType adType) {
		List<AdPositionCode> codes = new ArrayList<>();
		for (AdPositionCode code : AdPositionCode.values()) {
			codes.add(code);
		}
		return codes;
	}
}