/**
 * 
 */
package com.weimhc.modules.track.entity;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.javamg.common.persistence.DataEntity;
import com.thinkgem.javamg.common.utils.DateUtils;

/**
 * 访问跟踪Entity
 * 
 * @author lc
 * @version 2017-04-07
 */
public class VisitTrack extends DataEntity<VisitTrack> {

	private static final long serialVersionUID = 1L;
	/**
	 * 网站id
	 * 
	 */
	private String siteId;
	/**
	 * 访问者
	 * 
	 */
	private String visitorId;
	/**
	 * 浏览id
	 * 
	 */
	private String browseId;
	/**
	 * 是否新的访问者
	 * 
	 */
	private Boolean ifNewVisitor;
	/**
	 * 是否新的浏览
	 * 
	 */
	private Boolean ifNewBrowse;
	/**
	 * 访问链接
	 * 
	 */
	private String url;
	/**
	 * 标题
	 * 
	 */
	private String title;
	/**
	 * 来源
	 * 
	 */
	private String referrer;
	
	/**
	 * 访问来源类型
	 */
	private SourceType sourceType;

	/**
	 * 来源网站
	 */
	private String searchSite;
	/**
	 * 查询关键字
	 * 
	 */
	private String searchKeywords;
	/**
	 * 操作系统
	 * 
	 */
	private String os;
	/**
	 * 设备类型
	 * 
	 */
	private String deviceType;
	/**
	 * 屏幕分辨率
	 * 
	 */
	private String resolution;
	/**
	 * 屏幕颜色深度
	 * 
	 */
	private Integer colorDepth;
	/**
	 * 浏览器名称
	 * 
	 */
	private String browserName;
	/**
	 * 浏览器类型
	 * 
	 */
	private String browserType;
	/**
	 * 浏览器版本
	 * 
	 */
	private String browserVersion;
	/**
	 * 是否支持cookie
	 * 
	 */
	private Boolean cookieEnabled;
	/**
	 * 是否支持java
	 * 
	 */
	private Boolean javaEnabled;
	/**
	 * 是否支持flash
	 * 
	 */
	private Boolean flashEnabled;
	/**
	 * flash版本
	 * 
	 */
	private String flashVersion;
	/**
	 * 浏览器语言
	 * 
	 */
	private String language;
	/**
	 * 访问ip
	 * 
	 */
	private String ip;
	/**
	 * 访问国家
	 * 
	 */
	private String country;
	/**
	 * 访问地区
	 * 
	 */
	private String area;
	/**
	 * 访问省份
	 * 
	 */
	private String province;
	/**
	 * 访问城市
	 * 
	 */
	private String city;
	/**
	 * isp
	 * 
	 */
	private String isp;
	/**
	 * 访问时间
	 * 
	 */
	private Date time;
	/**
	 * 页面停留时间（秒）
	 * 
	 */
	private Integer timeOnPage;
	/**
	 * 页面深度
	 * 
	 */
	private Integer pageViewDepth;
	/**
	 * 年
	 */
	private String year;
	/**
	 * 月
	 */
	private String month;
	/**
	 * 日
	 */
	private String day;
	/**
	 * 时
	 */
	private String hour;

	public VisitTrack() {
		super();
	}

	public VisitTrack(String id) {
		super(id);
	}

	@Override
	public void preInsert() {
		super.preInsert();
		this.setYear(DateUtils.getYear());
		this.setMonth(DateUtils.getMonth());
		this.setDay(DateUtils.getDay());
		this.setHour(DateUtils.getHour());
	}

	/**
	 * 获取网站id
	 * 
	 * @return 网站id
	 */
	@Length(min = 0, max = 64)
	public String getSiteId() {
		return siteId;
	}

	/**
	 * 设置网站id
	 * 
	 * @param siteId
	 *            网站id
	 */
	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}

	/**
	 * 获取访问者
	 * 
	 * @return 访问者
	 */
	@Length(min = 0, max = 64)
	public String getVisitorId() {
		return visitorId;
	}

	/**
	 * 设置访问者
	 * 
	 * @param visitorId
	 *            访问者
	 */
	public void setVisitorId(String visitorId) {
		this.visitorId = visitorId;
	}

	/**
	 * 获取浏览id
	 * 
	 * @return 浏览id
	 */
	@Length(min = 0, max = 64)
	public String getBrowseId() {
		return browseId;
	}

	/**
	 * 设置浏览id
	 * 
	 * @param browseId
	 *            浏览id
	 */
	public void setBrowseId(String browseId) {
		this.browseId = browseId;
	}

	/**
	 * 获取是否新的访问者
	 * 
	 * @return 是否新的访问者
	 */
	@NotNull()
	public Boolean getIfNewVisitor() {
		return ifNewVisitor;
	}

	/**
	 * 设置是否新的访问者
	 * 
	 * @param ifNewVisitor
	 *            是否新的访问者
	 */
	public void setIfNewVisitor(Boolean ifNewVisitor) {
		this.ifNewVisitor = ifNewVisitor;
	}

	/**
	 * 获取是否新的浏览
	 * 
	 * @return 是否新的浏览
	 */
	@NotNull()
	public Boolean getIfNewBrowse() {
		return ifNewBrowse;
	}

	/**
	 * 设置是否新的浏览
	 * 
	 * @param ifNewBrowse
	 *            是否新的浏览
	 */
	public void setIfNewBrowse(Boolean ifNewBrowse) {
		this.ifNewBrowse = ifNewBrowse;
	}

	/**
	 * 获取访问链接
	 * 
	 * @return 访问链接
	 */
	@Length(min = 0, max = 255)
	public String getUrl() {
		return url;
	}

	/**
	 * 设置访问链接
	 * 
	 * @param url
	 *            访问链接
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * 获取标题
	 * 
	 * @return 标题
	 */
	@Length(min = 0, max = 255)
	public String getTitle() {
		return title;
	}

	/**
	 * 设置标题
	 * 
	 * @param title
	 *            标题
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * 获取来源
	 * 
	 * @return 来源
	 */
	@Length(min = 0, max = 1024)
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
	 * 获取查询关键字
	 * 
	 * @return 查询关键字
	 */
	@Length(min = 0, max = 64)
	public String getSearchKeywords() {
		return searchKeywords;
	}

	/**
	 * 设置查询关键字
	 * 
	 * @param searchKeywords
	 *            查询关键字
	 */
	public void setSearchKeywords(String searchKeywords) {
		this.searchKeywords = searchKeywords;
	}

	/**
	 * 获取操作系统
	 * 
	 * @return 操作系统
	 */
	@Length(min = 0, max = 25)
	public String getOs() {
		return os;
	}

	/**
	 * 设置操作系统
	 * 
	 * @param os
	 *            操作系统
	 */
	public void setOs(String os) {
		this.os = os;
	}

	/**
	 * 获取设备类型
	 * 
	 * @return 设备类型
	 */
	@Length(min = 0, max = 25)
	public String getDeviceType() {
		return deviceType;
	}

	/**
	 * 设置设备类型
	 * 
	 * @param deviceType
	 *            设备类型
	 */
	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	/**
	 * 获取屏幕分辨率
	 * 
	 * @return 屏幕分辨率
	 */
	@Length(min = 0, max = 20)
	public String getResolution() {
		return resolution;
	}

	/**
	 * 设置屏幕分辨率
	 * 
	 * @param resolution
	 *            屏幕分辨率
	 */
	public void setResolution(String resolution) {
		this.resolution = resolution;
	}

	/**
	 * 获取屏幕颜色深度
	 * 
	 * @return 屏幕颜色深度
	 */
	public Integer getColorDepth() {
		return colorDepth;
	}

	/**
	 * 设置屏幕颜色深度
	 * 
	 * @param colorDepth
	 *            屏幕颜色深度
	 */
	public void setColorDepth(Integer colorDepth) {
		this.colorDepth = colorDepth;
	}

	/**
	 * 获取浏览器名称
	 * 
	 * @return 浏览器名称
	 */
	@Length(min = 0, max = 64)
	public String getBrowserName() {
		return browserName;
	}

	/**
	 * 设置浏览器名称
	 * 
	 * @param browserName
	 *            浏览器名称
	 */
	public void setBrowserName(String browserName) {
		this.browserName = browserName;
	}

	/**
	 * 获取浏览器版本
	 * 
	 * @return 浏览器版本
	 */
	public String getBrowserVersion() {
		return browserVersion;
	}

	/**
	 * 设置浏览器版本
	 * 
	 * @param browserVersion
	 *            浏览器版本
	 */
	public void setBrowserVersion(String browserVersion) {
		this.browserVersion = browserVersion;
	}

	/**
	 * 获取是否支持cookie
	 * 
	 * @return 是否支持cookie
	 */
	@NotNull()
	public Boolean getCookieEnabled() {
		return cookieEnabled;
	}

	/**
	 * 设置是否支持cookie
	 * 
	 * @param cookieEnabled
	 *            是否支持cookie
	 */
	public void setCookieEnabled(Boolean cookieEnabled) {
		this.cookieEnabled = cookieEnabled;
	}

	/**
	 * 获取是否支持java
	 * 
	 * @return 是否支持java
	 */
	@NotNull()
	public Boolean getJavaEnabled() {
		return javaEnabled;
	}

	/**
	 * 设置是否支持java
	 * 
	 * @param javaEnabled
	 *            是否支持java
	 */
	public void setJavaEnabled(Boolean javaEnabled) {
		this.javaEnabled = javaEnabled;
	}

	/**
	 * 获取是否支持flash
	 * 
	 * @return 是否支持flash
	 */
	@NotNull()
	public Boolean getFlashEnabled() {
		return flashEnabled;
	}

	/**
	 * 设置是否支持flash
	 * 
	 * @param flashEnabled
	 *            是否支持flash
	 */
	public void setFlashEnabled(Boolean flashEnabled) {
		this.flashEnabled = flashEnabled;
	}

	/**
	 * 获取flash版本
	 * 
	 * @return flash版本
	 */
	@Length(min = 0, max = 64)
	public String getFlashVersion() {
		return flashVersion;
	}

	/**
	 * 设置flash版本
	 * 
	 * @param flashVersion
	 *            flash版本
	 */
	public void setFlashVersion(String flashVersion) {
		this.flashVersion = flashVersion;
	}

	/**
	 * 获取浏览器语言
	 * 
	 * @return 浏览器语言
	 */
	@Length(min = 1, max = 10)
	public String getLanguage() {
		return language;
	}

	/**
	 * 设置浏览器语言
	 * 
	 * @param language
	 *            浏览器语言
	 */
	public void setLanguage(String language) {
		this.language = language;
	}

	/**
	 * 获取访问ip
	 * 
	 * @return 访问ip
	 */
	@Length(min = 1, max = 64)
	public String getIp() {
		return ip;
	}

	/**
	 * 设置访问ip
	 * 
	 * @param ip
	 *            访问ip
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}

	/**
	 * 获取访问国家
	 * 
	 * @return 访问国家
	 */
	@Length(min = 1, max = 64)
	public String getCountry() {
		return country;
	}

	/**
	 * 设置访问国家
	 * 
	 * @param country
	 *            访问国家
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * 获取访问地区
	 * 
	 * @return 访问地区
	 */
	@Length(min = 0, max = 64)
	public String getArea() {
		return area;
	}

	/**
	 * 设置访问地区
	 * 
	 * @param area
	 *            访问地区
	 */
	public void setArea(String area) {
		this.area = area;
	}

	/**
	 * 获取访问省份
	 * 
	 * @return 访问省份
	 */
	@Length(min = 0, max = 64)
	public String getProvince() {
		return province;
	}

	/**
	 * 设置访问省份
	 * 
	 * @param province
	 *            访问省份
	 */
	public void setProvince(String province) {
		this.province = province;
	}

	/**
	 * 获取访问城市
	 * 
	 * @return 访问城市
	 */
	public String getCity() {
		return city;
	}

	/**
	 * 设置访问城市
	 * 
	 * @param city
	 *            访问城市
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * 获取isp
	 * 
	 * @return isp
	 */
	@Length(min = 0, max = 64)
	public String getIsp() {
		return isp;
	}

	/**
	 * 设置isp
	 * 
	 * @param isp
	 *            isp
	 */
	public void setIsp(String isp) {
		this.isp = isp;
	}

	/**
	 * 获取访问时间
	 * 
	 * @return 访问时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getTime() {
		return time;
	}

	/**
	 * 设置访问时间
	 * 
	 * @param time
	 *            访问时间
	 */
	public void setTime(Date time) {
		this.time = time;
	}

	/**
	 * 获取页面停留时间（秒）
	 * 
	 * @return 页面停留时间（秒）
	 */
	public Integer getTimeOnPage() {
		return timeOnPage;
	}

	/**
	 * 设置页面停留时间（秒）
	 * 
	 * @param timeOnPage
	 *            页面停留时间（秒）
	 */
	public void setTimeOnPage(Integer timeOnPage) {
		this.timeOnPage = timeOnPage;
	}

	/**
	 * 获取页面深度
	 * 
	 * @return 页面深度
	 */
	public Integer getPageViewDepth() {
		return pageViewDepth;
	}

	/**
	 * 设置页面深度
	 * 
	 * @param pageViewDepth
	 *            页面深度
	 */
	public void setPageViewDepth(Integer pageViewDepth) {
		this.pageViewDepth = pageViewDepth;
	}

	/**
	 * 获取年
	 * 
	 * @return the year
	 */
	public String getYear() {
		return year;
	}

	/**
	 * 设置年
	 * 
	 * @param year
	 *            the year to set
	 */
	public void setYear(String year) {
		this.year = year;
	}

	/**
	 * 获取月
	 * 
	 * @return the month
	 */
	public String getMonth() {
		return month;
	}

	/**
	 * 设置月
	 * 
	 * @param month
	 *            the month to set
	 */
	public void setMonth(String month) {
		this.month = month;
	}

	/**
	 * 获取天
	 * 
	 * @return the day
	 */
	public String getDay() {
		return day;
	}

	/**
	 * 设置天
	 * 
	 * @param day
	 *            the day to set
	 */
	public void setDay(String day) {
		this.day = day;
	}

	/**
	 * 获取时
	 * 
	 * @return the hour
	 */
	public String getHour() {
		return hour;
	}

	/**
	 * 设置时
	 * 
	 * @param hour
	 *            the hour to set
	 */
	public void setHour(String hour) {
		this.hour = hour;
	}

	/**
	 * 获取来源网站
	 * 
	 * @return the searchSite
	 */
	public String getSearchSite() {
		return searchSite;
	}

	/**
	 * 设置来源网站
	 * 
	 * @param searchSite
	 *            the searchSite to set
	 */
	public void setSearchSite(String searchSite) {
		this.searchSite = searchSite;
	}
	
	/**
	 * 返回 访问来源类型
	 * 
	 * @return the sourceType
	 */
	public SourceType getSourceType() {
		return sourceType;
	}

	/**
	 * 设置 访问来源类型
	 * 
	 * @param sourceType
	 *            the sourceType to set
	 */
	public void setSourceType(SourceType sourceType) {
		this.sourceType = sourceType;
	}

	/**
	 * 获取浏览器类型
	 * 
	 * @return the browserType
	 */
	public String getBrowserType() {
		return browserType;
	}

	/**
	 * 设置浏览器类型
	 * 
	 * @param browserType
	 *            the browserType to set
	 */
	public void setBrowserType(String browserType) {
		this.browserType = browserType;
	}

}