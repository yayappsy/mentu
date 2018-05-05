/**
 * 
 */
package com.weimhc.api.modules.dto.resp;

import com.weimhc.framework.dto.AbstractIdDto;
import io.swagger.annotations.ApiModelProperty;

/**
 * 地区信息 DTO
 * 
 * @author zsm
 * @version 2016-10-05
 */
public class AreaDto extends AbstractIdDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1095501047336970672L;

	/**
	 * 父地区id
	 */
	private String parentId;
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
	private String type;
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
	 * 拼音
	 * 
	 */
	private String pinyin;
	/**
	 * 拼音首字母
	 * 
	 */
	private String firstLetter;

	/**
	 * 获取名称
	 * 
	 * @return 名称
	 */
	@ApiModelProperty(value = "名称")
	public String getName() {
		return name;
	}

	/**
	 * 设置名称
	 * 
	 * @param name
	 *            名称
	 */

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获取简称
	 * 
	 * @return 简称
	 */
	@ApiModelProperty(value = "简称")
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
	@ApiModelProperty(value = "省市区聚合")
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
	@ApiModelProperty(value = "省市区聚合简称")
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
	@ApiModelProperty(value = "区号")
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
	@ApiModelProperty(value = "邮政编码")
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
	@ApiModelProperty(value = "区域类型")
	public String getType() {
		return type;
	}

	/**
	 * 设置区域类型
	 * 
	 * @param levelType
	 *            区域类型
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * 获取经度
	 * 
	 * @return 经度
	 */
	@ApiModelProperty(value = "经度")
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
	@ApiModelProperty(value = "纬度")
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
	@ApiModelProperty(value = "拼音")
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
	@ApiModelProperty(value = "拼音首字母")
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
	 * 返回 父地区id
	 * 
	 * @return the parentId
	 */
	public String getParentId() {
		return parentId;
	}

	/**
	 * 设置 父地区id
	 * 
	 * @param parentId
	 *            the parentId to set
	 */
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

}