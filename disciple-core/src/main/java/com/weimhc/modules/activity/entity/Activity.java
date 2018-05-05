/**
 * 
 */
package com.weimhc.modules.activity.entity;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.weimhc.framework.persistence.SortableEntity;

/**
 * 活动Entity
 * 
 * @author zsm
 * @version 2017-04-24
 */
public class Activity extends SortableEntity<Activity> {

	private static final long serialVersionUID = 1L;
	/**
	 * 活动名称
	 * 
	 */
	private String name;
	/**
	 * 活动标题
	 * 
	 */
	private String title;
	/**
	 * 活动介绍
	 * 
	 */
	private String detail;
	/**
	 * 活动类型
	 * 
	 */
	private ActivityType activityType;
	/**
	 * 描述图片
	 * 
	 */
	private String imgUrl;
	/**
	 * 活动背景
	 * 
	 */
	private String background;
	/**
	 * 活动流程
	 * 
	 */
	private String flow;
	/**
	 * 活动开始时间
	 * 
	 */
	private Date beginDate;
	/**
	 * 活动结束时间
	 * 
	 */
	private Date endDate;
	/**
	 * 报名开始时间
	 * 
	 */
	private Date applyBeginDate;
	/**
	 * 报名结束时间
	 * 
	 */
	private Date applyEndDate;
	/**
	 * 活动限制人数
	 * 
	 */
	private Integer numberLimit;
	/**
	 * 具体链接地址
	 * 
	 */
	private String url;
	/**
	 * 活动地点
	 * 
	 */
	private String address;
	/**
	 * 是否推荐
	 * 
	 */
	private Boolean isRecommend;
	/**
	 * 是否热门
	 * 
	 */
	private Boolean isHot;
	/**
	 * 推荐图片
	 * 
	 */
	private String recommendImage;

	public Activity() {
		super();
	}

	public Activity(String id) {
		super(id);
	}

	/**
	 * 获取活动名称
	 * 
	 * @return 活动名称
	 */
	@Length(min = 0, max = 255)
	public String getName() {
		return name;
	}

	/**
	 * 设置活动名称
	 * 
	 * @param name
	 *            活动名称
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获取活动标题
	 * 
	 * @return 活动标题
	 */
	@Length(min = 1, max = 255)
	public String getTitle() {
		return title;
	}

	/**
	 * 设置活动标题
	 * 
	 * @param title
	 *            活动标题
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * 获取活动介绍
	 * 
	 * @return 活动介绍
	 */
	@Length(min = 0, max = 1024)
	public String getDetail() {
		return detail;
	}

	/**
	 * 设置活动介绍
	 * 
	 * @param detail
	 *            活动介绍
	 */
	public void setDetail(String detail) {
		this.detail = detail;
	}

	/**
	 * 获取活动类型
	 * 
	 * @return 活动类型
	 */
	public ActivityType getActivityType() {
		return activityType;
	}

	/**
	 * 设置活动类型
	 * 
	 * @param activityType
	 *            活动类型
	 */
	public void setActivityType(ActivityType activityType) {
		this.activityType = activityType;
	}

	/**
	 * 获取描述图片
	 * 
	 * @return 描述图片
	 */
	@Length(min = 0, max = 512)
	public String getImgUrl() {
		return imgUrl;
	}

	/**
	 * 设置描述图片
	 * 
	 * @param imgUrl
	 *            描述图片
	 */
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	/**
	 * 获取活动背景
	 * 
	 * @return 活动背景
	 */
	public String getBackground() {
		return background;
	}

	/**
	 * 设置活动背景
	 * 
	 * @param background
	 *            活动背景
	 */
	public void setBackground(String background) {
		this.background = background;
	}

	/**
	 * 获取活动流程
	 * 
	 * @return 活动流程
	 */
	public String getFlow() {
		return flow;
	}

	/**
	 * 设置活动流程
	 * 
	 * @param flow
	 *            活动流程
	 */
	public void setFlow(String flow) {
		this.flow = flow;
	}

	/**
	 * 获取活动开始时间
	 * 
	 * @return 活动开始时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getBeginDate() {
		return beginDate;
	}

	/**
	 * 设置活动开始时间
	 * 
	 * @param beginDate
	 *            活动开始时间
	 */
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	/**
	 * 获取活动结束时间
	 * 
	 * @return 活动结束时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * 设置活动结束时间
	 * 
	 * @param endDate
	 *            活动结束时间
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * 获取报名开始时间
	 * 
	 * @return 报名开始时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getApplyBeginDate() {
		return applyBeginDate;
	}

	/**
	 * 设置报名开始时间
	 * 
	 * @param applyBeginDate
	 *            报名开始时间
	 */
	public void setApplyBeginDate(Date applyBeginDate) {
		this.applyBeginDate = applyBeginDate;
	}

	/**
	 * 获取报名结束时间
	 * 
	 * @return 报名结束时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getApplyEndDate() {
		return applyEndDate;
	}

	/**
	 * 设置报名结束时间
	 * 
	 * @param applyEndDate
	 *            报名结束时间
	 */
	public void setApplyEndDate(Date applyEndDate) {
		this.applyEndDate = applyEndDate;
	}

	/**
	 * 获取活动限制人数
	 * 
	 * @return 活动限制人数
	 */
	public Integer getNumberLimit() {
		return numberLimit;
	}

	/**
	 * 设置活动限制人数
	 * 
	 * @param numberLimit
	 *            活动限制人数
	 */
	public void setNumberLimit(Integer numberLimit) {
		this.numberLimit = numberLimit;
	}

	/**
	 * 获取具体链接地址
	 * 
	 * @return 具体链接地址
	 */
	@Length(min = 0, max = 255)
	public String getUrl() {
		return url;
	}

	/**
	 * 设置具体链接地址
	 * 
	 * @param url
	 *            具体链接地址
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * 获取活动地点
	 * 
	 * @return 活动地点
	 */
	@Length(min = 0, max = 255)
	public String getAddress() {
		return address;
	}

	/**
	 * 设置活动地点
	 * 
	 * @param address
	 *            活动地点
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * 获取是否推荐
	 * 
	 * @return 是否推荐
	 */
	@NotNull()
	public Boolean getIsRecommend() {
		return isRecommend;
	}

	/**
	 * 设置是否推荐
	 * 
	 * @param isRecommend
	 *            是否推荐
	 */
	public void setIsRecommend(Boolean isRecommend) {
		this.isRecommend = isRecommend;
	}

	/**
	 * 获取是否热门
	 * 
	 * @return 是否热门
	 */
	public Boolean getIsHot() {
		return isHot;
	}

	/**
	 * 设置是否热门
	 * 
	 * @param isHot
	 *            是否热门
	 */
	public void setIsHot(Boolean isHot) {
		this.isHot = isHot;
	}

	/**
	 * 获取推荐图片
	 * 
	 * @return 推荐图片
	 */
	@Length(min = 0, max = 255)
	public String getRecommendImage() {
		return recommendImage;
	}

	/**
	 * 设置推荐图片
	 * 
	 * @param recommendImage
	 *            推荐图片
	 */
	public void setRecommendImage(String recommendImage) {
		this.recommendImage = recommendImage;
	}

}