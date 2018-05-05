/**
 * 
 */
package com.weimhc.modules.sys.entity;

import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import com.thinkgem.javamg.common.persistence.TreeEntity;

/**
 * 系统后台地区(数据从region中来，只用于后台)Entity
 * 
 * @author zsm
 * @version 2016-02-18
 */
public class Area extends TreeEntity<Area> {

	private static final long serialVersionUID = 1L;

	/**
	 * 区号
	 */
	private String code; //

	/**
	 * 区域类型（1：国家；2：省份、直辖市；3：地市；4：区县）
	 */
	private String type; //

	/**
	 * 简称
	 */
	private String shortName;

	/**
	 * 省市区聚合
	 */
	private String mergerName;

	/**
	 * 邮编
	 */
	private String zipCode;

	/**
	 * 省市区聚合简称
	 */
	private String mergerShortName;

	/**
	 * 拼音
	 */
	private String pinyin;
	/**
	 * 简拼
	 */
	private String jianpin;

	/**
	 * 首字母
	 */
	private String firstLetter;
	/**
	 * 经度
	 */
	private String lng;
	/**
	 * 纬度
	 */
	private String lat;

	/**
	 * 是否热门
	 */
	private Boolean isHot;

	/**
	 * 是否显示
	 */
	private Boolean isShow;
	/**
	 * 是否开通服务
	 */
	private Boolean isOpenService;

	public Area() {
		super();
		this.sort = 30;
	}

	public Area(String id) {
		super(id);
	}

	/**
	 * 获取父级编号
	 * 
	 * @return 父级编号
	 */
	// @JsonBackReference
	// @NotNull()
	@Override
	public Area getParent() {
		return parent;
	}

	/**
	 * 设置父级编号
	 * 
	 * @param parent
	 *            父级编号
	 */
	@Override
	public void setParent(Area parent) {
		this.parent = parent;
	}

	/**
	 * 获取区域编码
	 * 
	 * @return 区域编码
	 */
	@Length(min = 0, max = 100)
	public String getCode() {
		return code;
	}

	/**
	 * 设置区域编码
	 * 
	 * @param code
	 *            区域编码
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * 获取区域类型
	 * 
	 * @return 区域类型
	 */
	@Length(min = 0, max = 1)
	public String getType() {
		return type;
	}

	/**
	 * 设置区域类型
	 * 
	 * @param type
	 *            区域类型
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the shortName
	 */
	@ApiModelProperty(value = "地区", example = "北京")
	public String getShortName() {
		return shortName;
	}

	/**
	 * @param shortName
	 *            the shortName to set
	 */
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	/**
	 * @return the mergerName
	 */
	public String getMergerName() {
		return mergerName;
	}

	/**
	 * @param mergerName
	 *            the mergerName to set
	 */
	public void setMergerName(String mergerName) {
		this.mergerName = mergerName;
	}

	/**
	 * @return the zipCode
	 */
	public String getZipCode() {
		return zipCode;
	}

	/**
	 * @param zipCode
	 *            the zipCode to set
	 */
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	/**
	 * @return the mergerShortName
	 */
	public String getMergerShorName() {
		return mergerShortName;
	}

	/**
	 * @param mergerShortName
	 *            the mergerShortName to set
	 */
	public void setMergerShorName(String mergerShortName) {
		this.mergerShortName = mergerShortName;
	}

	/**
	 * @return the pinyin
	 */
	public String getPinyin() {
		return pinyin;
	}

	/**
	 * @param pinyin
	 *            the pinyin to set
	 */
	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}

	/**
	 * @return the jianpin
	 */
	public String getJianpin() {
		return jianpin;
	}

	/**
	 * @param jianpin
	 *            the jianpin to set
	 */
	public void setJianpin(String jianpin) {
		this.jianpin = jianpin;
	}

	/**
	 * @return the firstLetter
	 */
	public String getFirstLetter() {
		return firstLetter;
	}

	/**
	 * @param firstLetter
	 *            the firstLetter to set
	 */
	public void setFirstLetter(String firstLetter) {
		this.firstLetter = firstLetter;
	}

	/**
	 * @return the lng
	 */
	public String getLng() {
		return lng;
	}

	/**
	 * @param lng
	 *            the lng to set
	 */
	public void setLng(String lng) {
		this.lng = lng;
	}

	/**
	 * @return the lat
	 */
	public String getLat() {
		return lat;
	}

	/**
	 * @param lat
	 *            the lat to set
	 */
	public void setLat(String lat) {
		this.lat = lat;
	}

	/**
	 * @return the isHot
	 */
	public Boolean getIsHot() {
		return isHot;
	}

	/**
	 * @param isHot
	 *            the isHot to set
	 */
	public void setIsHot(Boolean isHot) {
		this.isHot = isHot;
	}

	/**
	 * @return the isShow
	 */
	@Override
	public Boolean getIsShow() {
		return isShow;
	}

	/**
	 * @param isShow
	 *            the isShow to set
	 */
	@Override
	public void setIsShow(Boolean isShow) {
		this.isShow = isShow;
	}

	/**
	 * @return the isOpenService
	 */
	public Boolean getIsOpenService() {
		return isOpenService;
	}

	/**
	 * @param isOpenService
	 *            the isOpenService to set
	 */
	public void setIsOpenService(Boolean isOpenService) {
		this.isOpenService = isOpenService;
	}

}