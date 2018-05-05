/**
 * 
 */
package com.weimhc.modules.ad.entity;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.weimhc.framework.persistence.SortableEntity;

/**
 * 广告基本信息Entity
 * 
 * @author zsm
 * @version 2017-04-01
 */
public class Ad extends SortableEntity<Ad> {

	private static final long serialVersionUID = 1L;
	/**
	 * 广告位
	 * 
	 */
	private AdPosition adPosition;
	/**
	 * 广告位代码
	 */
	private String adPositionCode;
	/**
	 * 广告使用位置（PC端或移动端）
	 * 
	 */
	private AdSide side;
	/**
	 * 标题
	 * 
	 */
	private String title;
	/**
	 * 描述
	 * 
	 */
	private String description;
	/**
	 * 展示特效
	 * 
	 */
	private String effect;
	/**
	 * 结束时间
	 * 
	 */
	private Date endDate;
	/**
	 *
	 * 开始时间
	 */
	private Date beginDate;
	/**
	 * 是否长期有效
	 * 
	 */
	private Boolean isForever;
	/**
	 * 是否自动播放
	 * 
	 */
	private Boolean isAutoplay;

	/**
	 * 是否显示
	 * 
	 */
	private Boolean isShow;

	/**
	 * 高度(px)
	 * 
	 */
	private Integer height;
	/**
	 * 宽度(px)
	 * 
	 */
	private Integer width;
	/**
	 * 弹出框高度(px)
	 * 
	 */
	private Integer popHeight;
	/**
	 * 弹出框宽度(px)
	 * 
	 */
	private Integer popWidth;
	/**
	 * 上边距(px)
	 * 
	 */
	private Integer locationTop;
	/**
	 * 左边距(px)
	 * 
	 */
	private Integer locationLeft;
	/**
	 * 广告展示类型
	 * 
	 */
	private String contentType;
	/**
	 * 广告文件地址
	 * 
	 */
	private String path;
	/**
	 * 广告指向链接
	 * 
	 */
	private String url;
	/**
	 * 广告内容
	 * 
	 */
	private String content;

	/**
	 * 广告类型，查询时使用
	 */
	private AdType adType;

	public Ad() {
		super();
	}

	public Ad(String id) {
		super(id);
	}

	public enum AdSide {
		/**
		 * PC端
		 */
		pc,
		/**
		 * 移动端
		 */
		mobile;
	}

	/**
	 * 获取广告位
	 * 
	 * @return 广告位
	 */
	@NotNull
	public AdPosition getAdPosition() {
		return adPosition;
	}

	/**
	 * 设置广告位
	 * 
	 * @param adPosition
	 *            广告位
	 */
	public void setAdPosition(AdPosition adPosition) {
		this.adPosition = adPosition;
	}

	/**
	 * 获取标题
	 * 
	 * @return 标题
	 */
	@Length(min = 1, max = 255)
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
	 * 获取描述
	 * 
	 * @return 描述
	 */
	@Length(min = 0, max = 255)
	public String getDescription() {
		return description;
	}

	/**
	 * 设置描述
	 * 
	 * @param description
	 *            描述
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * 获取展示特效
	 * 
	 * @return 展示特效
	 */
	@Length(min = 0, max = 25)
	public String getEffect() {
		return effect;
	}

	/**
	 * 设置展示特效
	 * 
	 * @param effect
	 *            展示特效
	 */
	public void setEffect(String effect) {
		this.effect = effect;
	}

	/**
	 * 获取广告服务端
	 * 
	 * @return the side
	 */
	public AdSide getSide() {
		return side;
	}

	/**
	 * 设置广告服务端
	 * 
	 * @param side
	 *            the side to set
	 */
	public void setSide(AdSide side) {
		this.side = side;
	}

	/**
	 * 获取开始时间
	 * 
	 * @return 开始时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * 设置结束时间
	 * 
	 * @param endDate
	 *            结束时间
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * 获取 结束时间
	 * 
	 * @return 结束时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getBeginDate() {
		return beginDate;
	}

	/**
	 * 设置 开始时间
	 * 
	 * @param beginDate
	 *            开始时间
	 */
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	/**
	 * 返回 是否长期有效
	 * 
	 * @return the isForever
	 */
	public Boolean getIsForever() {
		return isForever;
	}

	/**
	 * 设置 是否长期有效
	 * 
	 * @param isForever
	 *            the isForever to set
	 */
	public void setIsForever(Boolean isForever) {
		this.isForever = isForever;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * 获取是否自动播放
	 * 
	 * @return 是否自动播放
	 */
	@NotNull()
	public Boolean getIsAutoplay() {
		return isAutoplay;
	}

	/**
	 * 设置是否自动播放
	 * 
	 * @param isAutoplay
	 *            是否自动播放
	 */
	public void setIsAutoplay(Boolean isAutoplay) {
		this.isAutoplay = isAutoplay;
	}

	/**
	 * 返回 广告位代码
	 * 
	 * @return the adPositionCode
	 */
	public String getAdPositionCode() {
		return adPositionCode;
	}

	/**
	 * 设置 广告位代码
	 * 
	 * @param adPositionCode
	 *            the adPositionCode to set
	 */
	public void setAdPositionCode(String adPositionCode) {
		this.adPositionCode = adPositionCode;
	}

	/**
	 * @return the isShow
	 */
	public Boolean getIsShow() {
		return isShow;
	}

	/**
	 * @param isShow
	 *            the isShow to set
	 */
	public void setIsShow(Boolean isShow) {
		this.isShow = isShow;
	}

	/**
	 * 返回 广告类型
	 * 
	 * @return the adType
	 */
	public AdType getAdType() {
		return adType;
	}

	/**
	 * 设置 广告类型
	 * 
	 * @param adType
	 *            the adType to set
	 */
	public void setAdType(AdType adType) {
		this.adType = adType;
	}

	/**
	 * 获取高度(px)
	 * 
	 * @return 高度(px)
	 */
	public Integer getHeight() {
		return height;
	}

	/**
	 * 设置高度(px)
	 * 
	 * @param height
	 *            高度(px)
	 */
	public void setHeight(Integer height) {
		this.height = height;
	}

	/**
	 * 获取宽度(px)
	 * 
	 * @return 宽度(px)
	 */
	public Integer getWidth() {
		return width;
	}

	/**
	 * 设置宽度(px)
	 * 
	 * @param width
	 *            宽度(px)
	 */
	public void setWidth(Integer width) {
		this.width = width;
	}

	/**
	 * 获取弹出框高度(px)
	 * 
	 * @return 弹出框高度(px)
	 */
	public Integer getPopHeight() {
		return popHeight;
	}

	/**
	 * 设置弹出框高度(px)
	 * 
	 * @param popHeight
	 *            弹出框高度(px)
	 */
	public void setPopHeight(Integer popHeight) {
		this.popHeight = popHeight;
	}

	/**
	 * 获取弹出框宽度(px)
	 * 
	 * @return 弹出框宽度(px)
	 */
	public Integer getPopWidth() {
		return popWidth;
	}

	/**
	 * 设置弹出框宽度(px)
	 * 
	 * @param popWidth
	 *            弹出框宽度(px)
	 */
	public void setPopWidth(Integer popWidth) {
		this.popWidth = popWidth;
	}

	/**
	 * 获取上边距(px)
	 * 
	 * @return 上边距(px)
	 */
	public Integer getLocationTop() {
		return locationTop;
	}

	/**
	 * 设置上边距(px)
	 * 
	 * @param locationTop
	 *            上边距(px)
	 */
	public void setLocationTop(Integer locationTop) {
		this.locationTop = locationTop;
	}

	/**
	 * 获取左边距(px)
	 * 
	 * @return 左边距(px)
	 */
	public Integer getLocationLeft() {
		return locationLeft;
	}

	/**
	 * 设置左边距(px)
	 * 
	 * @param locationLeft
	 *            左边距(px)
	 */
	public void setLocationLeft(Integer locationLeft) {
		this.locationLeft = locationLeft;
	}

	/**
	 * 获取广告展示类型
	 * 
	 * @return 广告展示类型
	 */
	@Length(min = 1, max = 25)
	public String getContentType() {
		return contentType;
	}

	/**
	 * 设置广告展示类型
	 * 
	 * @param contentType
	 *            广告展示类型
	 */
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	/**
	 * 获取广告文件地址
	 * 
	 * @return 广告文件地址
	 */
	@Length(min = 0, max = 255)
	public String getPath() {
		return path;
	}

	/**
	 * 设置广告文件地址
	 * 
	 * @param path
	 *            广告文件地址
	 */
	public void setPath(String path) {
		this.path = path;
	}

	/**
	 * 获取广告指向链接
	 * 
	 * @return 广告指向链接
	 */
	@Length(min = 0, max = 255)
	public String getUrl() {
		return url;
	}

	/**
	 * 设置广告指向链接
	 * 
	 * @param url
	 *            广告指向链接
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * 获取广告内容
	 * 
	 * @return 广告内容
	 */
	public String getContent() {
		return content;
	}

	/**
	 * 设置广告内容
	 * 
	 * @param content
	 *            广告内容
	 */
	public void setContent(String content) {
		this.content = content;
	}

}