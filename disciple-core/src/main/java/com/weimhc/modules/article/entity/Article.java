/**
 * 
 */
package com.weimhc.modules.article.entity;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.weimhc.framework.persistence.SortableEntity;

/**
 * 文章Entity
 * 
 * @author lc
 * @version 2016-11-22
 */
public class Article extends SortableEntity<Article> {

	private static final long serialVersionUID = 1L;
	private ArticleCategory category; // 栏目
	private String title; // 标题
	private String url; // 文章链接
	private String color; // 标题颜色
	private String image; // 文章图片
	private String keywords; // 关键字
	private String description; // 描述、摘要
	private String weight; // 权重，越大越靠前
	private Date weightDate; // 权重期限
	private String hits; // 点击数
	private String posid; // 推荐位，多选
	private String customContentView; // 自定义内容视图
	private String viewConfig; // 视图配置
	private String content; // 文章内容
	private ArticleType articleType; // 文章类型
	private String articleSource;// 文章来源
	private String author;// 作者
	private String authorIdentity;// 作者身份
	/**
	 * 是否推荐
	 */
	private Boolean isRecommend;
	/**
	 * 是否热门
	 */
	private Boolean isHot;
	/**
	 * 是否置顶
	 */
	private Boolean isTop;
	/**
	 * 是否显示
	 */
	private Boolean isShow;
	private Integer pageViewCount;// 浏览量

	public Article() {
		super();
	}

	public Article(String id) {
		super(id);
	}

	/**
	 * 获取栏目
	 * 
	 * @return 栏目
	 */
	@Length(min = 1, max = 64)
	public ArticleCategory getCategory() {
		return category;
	}

	/**
	 * 设置栏目
	 * 
	 * @param categoryId
	 *            栏目
	 */
	public void setCategory(ArticleCategory category) {
		this.category = category;
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
	 * 获取文章链接
	 * 
	 * @return 文章链接
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * 设置文章链接
	 * 
	 * @param url
	 *            文章链接
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * 获取标题颜色
	 * 
	 * @return 标题颜色
	 */
	public String getColor() {
		return color;
	}

	/**
	 * 设置标题颜色
	 * 
	 * @param color
	 *            标题颜色
	 */
	public void setColor(String color) {
		this.color = color;
	}

	/**
	 * 获取文章图片
	 * 
	 * @return 文章图片
	 */
	public String getImage() {
		return image;
	}

	/**
	 * 设置文章图片
	 * 
	 * @param image
	 *            文章图片
	 */
	public void setImage(String image) {
		this.image = image;
	}

	/**
	 * 获取关键字
	 * 
	 * @return 关键字
	 */
	@Length(min = 0, max = 255)
	public String getKeywords() {
		return keywords;
	}

	/**
	 * 设置关键字
	 * 
	 * @param keywords
	 *            关键字
	 */
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	/**
	 * 获取描述、摘要
	 * 
	 * @return 描述、摘要
	 */
	@Length(min = 0, max = 1024)
	public String getDescription() {
		return description;
	}

	/**
	 * 设置描述、摘要
	 * 
	 * @param description
	 *            描述、摘要
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * 获取权重，越大越靠前
	 * 
	 * @return 权重，越大越靠前
	 */
	public String getWeight() {
		return weight;
	}

	/**
	 * 设置权重，越大越靠前
	 * 
	 * @param weight
	 *            权重，越大越靠前
	 */
	public void setWeight(String weight) {
		this.weight = weight;
	}

	/**
	 * 获取权重期限
	 * 
	 * @return 权重期限
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getWeightDate() {
		return weightDate;
	}

	/**
	 * 设置权重期限
	 * 
	 * @param weightDate
	 *            权重期限
	 */
	public void setWeightDate(Date weightDate) {
		this.weightDate = weightDate;
	}

	/**
	 * 获取点击数
	 * 
	 * @return 点击数
	 */

	public String getHits() {
		return hits;
	}

	/**
	 * 设置点击数
	 * 
	 * @param hits
	 *            点击数
	 */
	public void setHits(String hits) {
		this.hits = hits;
	}

	/**
	 * 获取推荐位，多选
	 * 
	 * @return 推荐位，多选
	 */
	public String getPosid() {
		return posid;
	}

	/**
	 * 设置推荐位，多选
	 * 
	 * @param posid
	 *            推荐位，多选
	 */
	public void setPosid(String posid) {
		this.posid = posid;
	}

	/**
	 * 获取自定义内容视图
	 * 
	 * @return 自定义内容视图
	 */
	public String getCustomContentView() {
		return customContentView;
	}

	/**
	 * 设置自定义内容视图
	 * 
	 * @param customContentView
	 *            自定义内容视图
	 */
	public void setCustomContentView(String customContentView) {
		this.customContentView = customContentView;
	}

	/**
	 * 获取视图配置
	 * 
	 * @return 视图配置
	 */
	public String getViewConfig() {
		return viewConfig;
	}

	/**
	 * 设置视图配置
	 * 
	 * @param viewConfig
	 *            视图配置
	 */
	public void setViewConfig(String viewConfig) {
		this.viewConfig = viewConfig;
	}

	/**
	 * 获取文章内容
	 * 
	 * @return 文章内容
	 */
	public String getContent() {
		return content;
	}

	/**
	 * 设置文章内容
	 * 
	 * @param content
	 *            文章内容
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * 获取是否推荐
	 * 
	 * @return
	 */
	public Boolean getIsRecommend() {
		return isRecommend;
	}

	/**
	 * 设置是否推荐
	 * 
	 * @param isRecommend
	 */
	public void setIsRecommend(Boolean isRecommend) {
		this.isRecommend = isRecommend;
	}

	/**
	 * 获取文章类型
	 * 
	 * @return
	 */
	public ArticleType getArticleType() {
		return articleType;
	}

	/**
	 * 设置文章类型
	 * 
	 * @return
	 */
	public void setArticleType(ArticleType articleType) {
		this.articleType = articleType;
	}

	/**
	 * 获取文章来源
	 * 
	 * @return
	 */
	public String getArticleSource() {
		return articleSource;
	}

	/**
	 * 设置文章来源
	 * 
	 * @return
	 */
	public void setArticleSource(String articleSource) {
		this.articleSource = articleSource;
	}

	/**
	 * 获取作者
	 * 
	 * @return
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * 设置作者
	 * 
	 * @return
	 */
	public void setAuthor(String author) {
		this.author = author;
	}

	/**
	 * 获取作者身份
	 * 
	 * @return
	 */
	public String getAuthorIdentity() {
		return authorIdentity;
	}

	/**
	 * 设置作者身份
	 * 
	 * @param authorIdentity
	 */
	public void setAuthorIdentity(String authorIdentity) {
		this.authorIdentity = authorIdentity;
	}

	/**
	 *
	 * 返回 是否热门
	 * 
	 * @return the isHot
	 */
	public Boolean getIsHot() {
		return isHot;
	}

	/**
	 * 设置 是否热门
	 * 
	 * @param isHot
	 *            the isHot to set
	 */
	public void setIsHot(Boolean isHot) {
		this.isHot = isHot;
	}

	/**
	 *
	 * 返回 是否置顶
	 * 
	 * @return the isHot
	 */
	public Boolean getIsTop() {
		return isTop;
	}

	/**
	 * 设置 是否置顶
	 * 
	 * @param isHot
	 *            the isHot to set
	 */
	public void setIsTop(Boolean isTop) {
		this.isTop = isTop;
	}

	/**
	 * 返回 是否显示
	 * 
	 * @return the isShow
	 */
	public Boolean getIsShow() {
		return isShow;
	}

	/**
	 * 设置 是否显示
	 * 
	 * @param isShow
	 *            the isShow to set
	 */
	public void setIsShow(Boolean isShow) {
		this.isShow = isShow;
	}

	/**
	 * @return the pageViewCount
	 */
	public Integer getPageViewCount() {
		return pageViewCount;
	}

	/**
	 * @param pageViewCount
	 *            the pageViewCount to set
	 */
	public void setPageViewCount(Integer pageViewCount) {
		this.pageViewCount = pageViewCount;
	}

}