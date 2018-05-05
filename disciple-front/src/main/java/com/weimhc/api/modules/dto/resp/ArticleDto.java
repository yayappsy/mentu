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

import java.util.Date;

/**
 * 学院动态DTO
 * 
 * @author lc
 * @version 2016-12-15
 */
public class ArticleDto extends AbstractIdDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1095501047336970672L;

	/**
	 * 文章标题
	 * 
	 */
	private String title;
	/**
	 * 文章简介
	 * 
	 */
	private String description;

	private String Image;// 文章图片

	private Date createDate;

	private String articleSource;
	/**
	 * 文章内容
	 * 
	 */
	private String content;

	/**
	 * 浏览量
	 * 
	 */
	private Integer pageViewCount;

	/**
	 * 获取文章标题
	 * 
	 * @return the title
	 */
	@ApiModelProperty(value = "文章标题")
	public String getTitle() {
		return title;
	}

	/**
	 * 设置文章标题
	 * 
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * 获取文章简介
	 * 
	 * @return the description
	 */
	@ApiModelProperty(value = "文章简介")
	public String getDescription() {
		return description;
	}

	/**
	 * 设置文章简介
	 * 
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * 获取创建时间
	 * 
	 * @return the createDate
	 */
	@ApiModelProperty(value = "创建时间")
	@JsonFormat(pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * 设置创建时间
	 * 
	 * @param createDate
	 *            the createDate to set
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * 获取来源
	 * 
	 * @return the articleSource
	 */
	@ApiModelProperty(value = "文章来源")
	public String getArticleSource() {
		return articleSource;
	}

	/**
	 * 设置来源
	 * 
	 * @param articleSource
	 *            the articleSource to set
	 */
	public void setArticleSource(String articleSource) {
		this.articleSource = articleSource;
	}

	/**
	 * 获取文章图片
	 * 
	 * @return the image
	 */
	@ApiModelProperty(value = "图片")
	public String getImage() {
		if (StringUtils.isNotBlank(Image)) {
			return UploadUtils.getAccessUrl(Image);
		}
		return "";
	}

	/**
	 * 获取图片
	 * 
	 * @param image
	 *            the image to set
	 */
	public void setImage(String image) {
		Image = image;
	}

	/**
	 * 获取文章内容
	 * 
	 * @return the content
	 */
	@ApiModelProperty(value = "文章内容")
	public String getContent() {
		return content;
	}

	/**
	 * 设置文章内容
	 * 
	 * @param content
	 *            the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * 获取浏览量
	 * 
	 * @return the pageViewCount
	 */
	@ApiModelProperty(value = "浏览量")
	public Integer getPageViewCount() {
		return pageViewCount;
	}

	/**
	 * 设置浏览量
	 * 
	 * @param pageViewCount
	 *            the pageViewCount to set
	 */
	public void setPageViewCount(Integer pageViewCount) {
		this.pageViewCount = pageViewCount;
	}

}