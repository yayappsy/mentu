/**
 * 
 */
package com.weimhc.modules.base.entity;

import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.thinkgem.javamg.common.persistence.TreeEntity;

/**
 * 全国行政区（供前后台使用）Entity
 * 
 * @author zsm
 * @version 2016-10-05
 */
public class Region extends TreeEntity<Region> {

	private static final long serialVersionUID = 1L;

	/**
	 * 名称
	 * 
	 */
	private String name;
	/**
	 * 简称
	 * 
	 */
	private String shortName;
	/**
	 * 省市区聚合
	 * 
	 */
	private String mergerName;
	/**
	 * 省市区聚合简称
	 * 
	 */
	private String mergerShortName;

	/**
	 * 区号
	 * 
	 */
	private String code;
	/**
	 * 邮政编码
	 * 
	 */
	private String zipCode;
	/**
	 * 区域类型
	 * 
	 */
	private String levelType;
	/**
	 * 经度
	 * 
	 */
	private String lng;
	/**
	 * 纬度
	 * 
	 */
	private String lat;
	/**
	 * 全拼
	 * 
	 */
	private String pinyin;
	/**
	 * 简拼
	 * 
	 */
	private String jianpin;
	/**
	 * 拼音首字母
	 * 
	 */
	private String firstLetter;

	public Region() {
		super();
	}

	public Region(String id) {
		super(id);
	}

	/**
	 * 获取父级编号
	 * 
	 * @return 父级编号
	 */
	@Override
	@JsonBackReference
	@NotNull()
	public Region getParent() {
		return parent;
	}

	/**
	 * 设置父级编号
	 * 
	 * @param parent
	 *            父级编号
	 */
	@Override
	public void setParent(Region parent) {
		this.parent = parent;
	}

	/**
	 * 获取所有父级编号
	 * 
	 * @return 所有父级编号
	 */
	@Override
	@Length(min = 1, max = 2000)
	public String getParentIds() {
		return parentIds;
	}

	/**
	 * 设置所有父级编号
	 * 
	 * @param parentIds
	 *            所有父级编号
	 */
	@Override
	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}

	/**
	 * 获取名称
	 * 
	 * @return 名称
	 */
	@Override
	@Length(min = 1, max = 100)
	public String getName() {
		return name;
	}

	/**
	 * 设置名称
	 * 
	 * @param name
	 *            名称
	 */
	@Override
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获取简称
	 * 
	 * @return 简称
	 */
	@Length(min = 1, max = 100)
	public String getShortName() {
		return shortName;
	}

	/**
	 * 设置简称
	 * 
	 * @param shortName
	 *            简称
	 */
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	/**
	 * 获取省市区聚合
	 * 
	 * @return 省市区聚合
	 */
	@Length(min = 1, max = 500)
	public String getMergerName() {
		return mergerName;
	}

	/**
	 * 设置省市区聚合
	 * 
	 * @param mergerName
	 *            省市区聚合
	 */
	public void setMergerName(String mergerName) {
		this.mergerName = mergerName;
	}

	/**
	 * 获取省市区聚合简称
	 * 
	 * @return 省市区聚合简称
	 */
	@Length(min = 1, max = 500)
	public String getMergerShortName() {
		return mergerShortName;
	}

	/**
	 * 设置省市区聚合简称
	 * 
	 * @param mergerShortName
	 *            省市区聚合简称
	 */
	public void setMergerShortName(String mergerShortName) {
		this.mergerShortName = mergerShortName;
	}

	/**
	 * 获取区号
	 * 
	 * @return 区号
	 */
	@Length(min = 0, max = 10)
	public String getCode() {
		return code;
	}

	/**
	 * 设置区号
	 * 
	 * @param code
	 *            区号
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * 获取邮政编码
	 * 
	 * @return 邮政编码
	 */
	@Length(min = 0, max = 10)
	public String getZipCode() {
		return zipCode;
	}

	/**
	 * 设置邮政编码
	 * 
	 * @param zipCode
	 *            邮政编码
	 */
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	/**
	 * 获取区域类型
	 * 
	 * @return 区域类型
	 */
	@Length(min = 0, max = 1)
	public String getLevelType() {
		return levelType;
	}

	/**
	 * 设置区域类型
	 * 
	 * @param levelType
	 *            区域类型
	 */
	public void setLevelType(String levelType) {
		this.levelType = levelType;
	}

	/**
	 * 获取经度
	 * 
	 * @return 经度
	 */
	@Length(min = 0, max = 50)
	public String getLng() {
		return lng;
	}

	/**
	 * 设置经度
	 * 
	 * @param lng
	 *            经度
	 */
	public void setLng(String lng) {
		this.lng = lng;
	}

	/**
	 * 获取纬度
	 * 
	 * @return 纬度
	 */
	@Length(min = 0, max = 50)
	public String getLat() {
		return lat;
	}

	/**
	 * 设置纬度
	 * 
	 * @param lat
	 *            纬度
	 */
	public void setLat(String lat) {
		this.lat = lat;
	}

	/**
	 * 获取拼音
	 * 
	 * @return 拼音
	 */
	@Length(min = 0, max = 100)
	public String getPinyin() {
		return pinyin;
	}

	/**
	 * 设置拼音
	 * 
	 * @param pinyin
	 *            拼音
	 */
	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}

	/**
	 * 获取拼音首字母
	 * 
	 * @return 拼音首字母
	 */
	@Length(min = 0, max = 1)
	public String getFirstLetter() {
		return firstLetter;
	}

	/**
	 * 设置拼音首字母
	 * 
	 * @param firstLetter
	 *            拼音首字母
	 */
	public void setFirstLetter(String firstLetter) {
		this.firstLetter = firstLetter;
	}

	/**
	 * 返回等级
	 * 
	 * @return 返回getTevelType()的值
	 */
	public String getType() {
		return levelType;
	}

	/**
	 * 设置返回
	 * 
	 * @param levelType
	 *            返回
	 */
	public void setType(String type) {
		if (StringUtils.isNotBlank(type)) {
			setLevelType(type);
		}
	}

	/**
	 * 返回 简拼
	 * 
	 * @return the jianpin
	 */
	public String getJianpin() {
		return jianpin;
	}

	/**
	 * 设置 简拼
	 * 
	 * @param jianpin
	 *            the jianpin to set
	 */
	public void setJianpin(String jianpin) {
		this.jianpin = jianpin;
	}

}