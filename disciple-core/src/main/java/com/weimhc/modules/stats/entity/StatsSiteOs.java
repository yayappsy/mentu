/**
 * 
 */
package com.weimhc.modules.stats.entity;

import java.math.BigDecimal;
import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.javamg.common.persistence.DataEntity;

/**
 * 统计网络设备类型Entity
 * @author lc
 * @version 2017-04-12
 */
public class StatsSiteOs extends DataEntity<StatsSiteOs>{
	
	private static final long serialVersionUID = 1L;
	/**
	 * 页面浏览量
	 * 
	 */		
	private Integer pageViewCount;	
	/**
	 * 访客数量
	 * 
	 */		
	private Integer visitorCount;	
	/**
	 * 新访客数量
	 * 
	 */		
	private Integer newVisitorCount;	
	/**
	 * 新访客比例
	 * 
	 */		
	private BigDecimal newVisitorRate;	
	/**
	 * 访问次数
	 * 
	 */		
	private Integer browseCount;	
	/**
	 * 独立ip数量
	 * 
	 */		
	private Integer ipCount;	
	/**
	 * 跳出率%
	 * 
	 */		
	private BigDecimal bounceRate;	
	/**
	 * 转化次数
	 * 
	 */		
	private Integer conversionsCount;	
	/**
	 * 转化率%
	 * 
	 */		
	private BigDecimal conversionsRate;	
	/**
	 * 平均访问页数
	 * 
	 */		
	private BigDecimal averageBrowsePageCount;	
	/**
	 * 平均访问时长
	 * 
	 */		
	private Date averageAccessTime;	
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
	 * 统计维度
	 * 
	 */		
	private String dimensions;	
	/**
	 * 年
	 * 
	 */		
	private String year;	
	/**
	 * 月
	 * 
	 */		
	private String month;	
	/**
	 * 日
	 * 
	 */		
	private String day;	
	/**
	 * 时
	 * 
	 */		
	private String hour;	
	
	public StatsSiteOs() {
		super();
	}

	public StatsSiteOs(String id){
		super(id);
	}

	/**
	 * 获取页面浏览量
	 * 
	 * @return 页面浏览量
	 */	
	public Integer getPageViewCount() {
		return pageViewCount;
	}
	/**
	 * 设置页面浏览量
	 * 
	 * @param pageViewCount
	 *            页面浏览量
	 */
	public void setPageViewCount(Integer pageViewCount) {
		this.pageViewCount = pageViewCount;
	}
	
	/**
	 * 获取访客数量
	 * 
	 * @return 访客数量
	 */	
	public Integer getVisitorCount() {
		return visitorCount;
	}
	/**
	 * 设置访客数量
	 * 
	 * @param visitorCount
	 *            访客数量
	 */
	public void setVisitorCount(Integer visitorCount) {
		this.visitorCount = visitorCount;
	}
	
	/**
	 * 获取新访客数量
	 * 
	 * @return 新访客数量
	 */	
	public Integer getNewVisitorCount() {
		return newVisitorCount;
	}
	/**
	 * 设置新访客数量
	 * 
	 * @param newVisitorCount
	 *            新访客数量
	 */
	public void setNewVisitorCount(Integer newVisitorCount) {
		this.newVisitorCount = newVisitorCount;
	}
	
	/**
	 * 获取新访客比例
	 * 
	 * @return 新访客比例
	 */	
	public BigDecimal getNewVisitorRate() {
		return newVisitorRate;
	}
	/**
	 * 设置新访客比例
	 * 
	 * @param newVisitorRate
	 *            新访客比例
	 */
	public void setNewVisitorRate(BigDecimal newVisitorRate) {
		this.newVisitorRate = newVisitorRate;
	}
	
	/**
	 * 获取访问次数
	 * 
	 * @return 访问次数
	 */	
	public Integer getBrowseCount() {
		return browseCount;
	}
	/**
	 * 设置访问次数
	 * 
	 * @param browseCount
	 *            访问次数
	 */
	public void setBrowseCount(Integer browseCount) {
		this.browseCount = browseCount;
	}
	
	/**
	 * 获取独立ip数量
	 * 
	 * @return 独立ip数量
	 */	
	public Integer getIpCount() {
		return ipCount;
	}
	/**
	 * 设置独立ip数量
	 * 
	 * @param ipCount
	 *            独立ip数量
	 */
	public void setIpCount(Integer ipCount) {
		this.ipCount = ipCount;
	}
	
	/**
	 * 获取跳出率%
	 * 
	 * @return 跳出率%
	 */	
	public BigDecimal getBounceRate() {
		return bounceRate;
	}
	/**
	 * 设置跳出率%
	 * 
	 * @param bounceRate
	 *            跳出率%
	 */
	public void setBounceRate(BigDecimal bounceRate) {
		this.bounceRate = bounceRate;
	}
	
	/**
	 * 获取转化次数
	 * 
	 * @return 转化次数
	 */	
	public Integer getConversionsCount() {
		return conversionsCount;
	}
	/**
	 * 设置转化次数
	 * 
	 * @param conversionsCount
	 *            转化次数
	 */
	public void setConversionsCount(Integer conversionsCount) {
		this.conversionsCount = conversionsCount;
	}
	
	/**
	 * 获取转化率%
	 * 
	 * @return 转化率%
	 */	
	public BigDecimal getConversionsRate() {
		return conversionsRate;
	}
	/**
	 * 设置转化率%
	 * 
	 * @param conversionsRate
	 *            转化率%
	 */
	public void setConversionsRate(BigDecimal conversionsRate) {
		this.conversionsRate = conversionsRate;
	}
	
	/**
	 * 获取平均访问页数
	 * 
	 * @return 平均访问页数
	 */	
	public BigDecimal getAverageBrowsePageCount() {
		return averageBrowsePageCount;
	}
	/**
	 * 设置平均访问页数
	 * 
	 * @param averageBrowsePageCount
	 *            平均访问页数
	 */
	public void setAverageBrowsePageCount(BigDecimal averageBrowsePageCount) {
		this.averageBrowsePageCount = averageBrowsePageCount;
	}
	
	/**
	 * 获取平均访问时长
	 * 
	 * @return 平均访问时长
	 */	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getAverageAccessTime() {
		return averageAccessTime;
	}
	/**
	 * 设置平均访问时长
	 * 
	 * @param averageAccessTime
	 *            平均访问时长
	 */
	public void setAverageAccessTime(Date averageAccessTime) {
		this.averageAccessTime = averageAccessTime;
	}
	
	/**
	 * 获取操作系统
	 * 
	 * @return 操作系统
	 */	
	@Length(min=0, max=64)
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
	@Length(min=0, max=64)
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
	 * 获取统计维度
	 * 
	 * @return 统计维度
	 */	
	@Length(min=0, max=5)
	public String getDimensions() {
		return dimensions;
	}
	/**
	 * 设置统计维度
	 * 
	 * @param dimensions
	 *            统计维度
	 */
	public void setDimensions(String dimensions) {
		this.dimensions = dimensions;
	}
	
	/**
	 * 获取年
	 * 
	 * @return 年
	 */	
	@Length(min=0, max=5)
	public String getYear() {
		return year;
	}
	/**
	 * 设置年
	 * 
	 * @param year
	 *            年
	 */
	public void setYear(String year) {
		this.year = year;
	}
	
	/**
	 * 获取月
	 * 
	 * @return 月
	 */	
	@Length(min=0, max=5)
	public String getMonth() {
		return month;
	}
	/**
	 * 设置月
	 * 
	 * @param month
	 *            月
	 */
	public void setMonth(String month) {
		this.month = month;
	}
	
	/**
	 * 获取日
	 * 
	 * @return 日
	 */	
	@Length(min=0, max=5)
	public String getDay() {
		return day;
	}
	/**
	 * 设置日
	 * 
	 * @param day
	 *            日
	 */
	public void setDay(String day) {
		this.day = day;
	}
	
	/**
	 * 获取时
	 * 
	 * @return 时
	 */	
	@Length(min=0, max=5)
	public String getHour() {
		return hour;
	}
	/**
	 * 设置时
	 * 
	 * @param hour
	 *            时
	 */
	public void setHour(String hour) {
		this.hour = hour;
	}
	
}