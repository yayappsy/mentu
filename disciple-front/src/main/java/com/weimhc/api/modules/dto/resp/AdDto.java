/**
 * 
 */
package com.weimhc.api.modules.dto.resp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.weimhc.framework.dto.AbstractIdDto;
import com.weimhc.framework.utils.UploadUtils;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.StringUtils;
import org.springframework.format.annotation.DateTimeFormat;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Date;

/**
 * 商品信息 DTO
 * 
 * @author zsm
 * @version 2016-10-05
 */
public class AdDto extends AbstractIdDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6707586013228500755L;
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
	 * 返回广告标题
	 * 
	 * @return the title
	 */
	@ApiModelProperty(value = "广告标题")
	public String getTitle() {
		return title;
	}

	/**
	 * 设置广告标题
	 * 
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * 返回结束时间
	 * 
	 * @return the endDate
	 */
	@ApiIgnore
	@ApiModelProperty(value = "结束时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * 设置结束时间
	 * 
	 * @param endDate
	 *            the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * 返回开始时间
	 * 
	 * @return the beginDate
	 */
	@ApiIgnore
	@ApiModelProperty(value = "开始时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getBeginDate() {
		return beginDate;
	}

	/**
	 * 设置开始时间
	 * 
	 * @param beginDate
	 *            the beginDate to set
	 */
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	/**
	 * 返回广告文件地址
	 * 
	 * @return the path
	 */
	@ApiModelProperty(value = "广告文件地址")
	public String getPath() {
		if (StringUtils.isNotBlank(path)) {
			return UploadUtils.getAccessUrl(path);
		}
		return "";
	}

	/**
	 * 设置广告文件地址
	 * 
	 * @param path
	 *            the path to set
	 */
	public void setPath(String path) {
		this.path = path;
	}

	/**
	 * 返回广告内容
	 * 
	 * @return the content
	 */
	@ApiModelProperty(value = "广告内容")
	public String getContent() {
		return content;
	}

	/**
	 * 设置返回广告内容
	 * 
	 * @param content
	 *            the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * 返回广告跳转的URL
	 * 
	 * @return the url
	 */
	@ApiModelProperty(value = "广告跳转的URL")
	public String getUrl() {
		return url;
	}

	/**
	 * 设置广告跳转的URL
	 * 
	 * @param url
	 *            the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the description
	 */
	@ApiModelProperty(value = "描述")
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the effect
	 */
	@ApiModelProperty(value = "展示特效")
	public String getEffect() {
		return effect;
	}

	/**
	 * @param effect
	 *            the effect to set
	 */
	public void setEffect(String effect) {
		this.effect = effect;
	}

	/**
	 * @return the isForever
	 */
	@ApiModelProperty(value = "是否长期有效")
	public Boolean getIsForever() {
		return isForever;
	}

	/**
	 * @param isForever
	 *            the isForever to set
	 */
	public void setIsForever(Boolean isForever) {
		this.isForever = isForever;
	}

	/**
	 * @return the isAutoplay
	 */
	@ApiModelProperty(value = "是否自动播放")
	public Boolean getIsAutoplay() {
		return isAutoplay;
	}

	/**
	 * @param isAutoplay
	 *            the isAutoplay to set
	 */
	public void setIsAutoplay(Boolean isAutoplay) {
		this.isAutoplay = isAutoplay;
	}

	/**
	 * @return the isShow
	 */
	@ApiModelProperty(value = "是否显示")
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
	 * @return the height
	 */
	@ApiModelProperty(value = "高度px")
	public Integer getHeight() {
		return height;
	}

	/**
	 * @param height
	 *            the height to set
	 */
	public void setHeight(Integer height) {
		this.height = height;
	}

	/**
	 * 获取宽度px
	 * 
	 * @return the width
	 */
	@ApiModelProperty(value = "宽度px")
	public Integer getWidth() {
		return width;
	}

	/**
	 * 设置宽度px
	 * 
	 * @param width
	 *            the width to set
	 */
	public void setWidth(Integer width) {
		this.width = width;
	}

	/**
	 * 获取广告展示类型
	 * 
	 * @return the contentType
	 */
	@ApiModelProperty(value = "广告展示类型")
	public String getContentType() {
		return contentType;
	}

	/**
	 * 设置广告展示类型
	 * 
	 * @param contentType
	 *            the contentType to set
	 */
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

}