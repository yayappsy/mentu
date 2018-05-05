/**
 * 
 */
package com.weimhc.api.modules.dto.resp;

import com.weimhc.framework.web.utils.MessageSourceUtils;
import com.weimhc.modules.track.entity.SourceType;

/**
 * 全部来源dto
 * 
 * @author lc
 * @version 2017-04-18
 */
public class StatsSiteSourceDto extends StatsSiteDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4371093896275487489L;
	/**
	 * 来源
	 * 
	 */
	private String referrer;
	/**
	 * 来源网站
	 * 
	 */
	private String searchSite;
	/**
	 * 来源类型
	 * 
	 */
	private SourceType sourceType;
	/**
	 * 来源类型
	 * 
	 */
	private String sourceTypeDescribe;
	/**
	 * 搜索关键字
	 * 
	 */
	private String searchKeywords;
	/**
	 * 访问设备类型
	 * 
	 */
	private String deviceType;
	/**
	 * 是否新访客
	 * 
	 */
	private Boolean ifNewVisitor;

	/**
	 * 获取来源
	 * 
	 * @return 来源
	 */
	public String getReferrer() {
		return referrer;
	}

	/**
	 * 设置来源
	 * 
	 * @param referrer
	 *            来源
	 */
	public void setReferrer(String referrer) {
		this.referrer = referrer;
	}

	/**
	 * 获取来源网站
	 * 
	 * @return 来源网站
	 */
	public String getSearchSite() {
		return searchSite;
	}

	/**
	 * 设置来源网站
	 * 
	 * @param searchSite
	 *            来源网站
	 */
	public void setSearchSite(String searchSite) {
		this.searchSite = searchSite;
	}

	/**
	 * 获取来源类型
	 * 
	 * @return 来源类型
	 */
	public SourceType getSourceType() {
		return sourceType;
	}

	/**
	 * 设置来源类型
	 * 
	 * @param sourceType
	 *            来源类型
	 */
	public void setSourceType(SourceType sourceType) {
		this.sourceType = sourceType;
	}

	/**
	 * 获取搜索关键字
	 * 
	 * @return 搜索关键字
	 */
	public String getSearchKeywords() {
		return searchKeywords;
	}

	/**
	 * 设置搜索关键字
	 * 
	 * @param searchKeywords
	 *            搜索关键字
	 */
	public void setSearchKeywords(String searchKeywords) {
		this.searchKeywords = searchKeywords;
	}

	/**
	 * 获取访问设备类型
	 * 
	 * @return 访问设备类型
	 */
	public String getDeviceType() {
		return deviceType;
	}

	/**
	 * 设置访问设备类型
	 * 
	 * @param deviceType
	 *            访问设备类型
	 */
	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	/**
	 * 获取是否新访客
	 * 
	 * @return 是否新访客
	 */
	public Boolean getIfNewVisitor() {
		return ifNewVisitor;
	}

	/**
	 * 设置是否新访客
	 * 
	 * @param ifNewVisitor
	 *            是否新访客
	 */
	public void setIfNewVisitor(Boolean ifNewVisitor) {
		this.ifNewVisitor = ifNewVisitor;
	}

	/**
	 * 获取来源类型描述
	 * 
	 * @return the sourceTypeDescribe
	 */
	public String getSourceTypeDescribe() {
		if (getSourceType() != null) {
			return MessageSourceUtils
					.getMessage("SourceType." + getSourceType());
		}
		return sourceTypeDescribe;
	}

	/**
	 * 设置获取来源类型描述
	 * 
	 * @param sourceTypeDescribe
	 *            the sourceTypeDescribe to set
	 */
	public void setSourceTypeDescribe(String sourceTypeDescribe) {
		this.sourceTypeDescribe = sourceTypeDescribe;
	}

}