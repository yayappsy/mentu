/**
 * 
 */
package com.weimhc.modules.image.entity;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.weimhc.framework.persistence.SortableEntity;
import com.weimhc.modules.base.entity.PathType;

/**
 * 图片Entity
 * 
 * @author zsm
 * @version 2017-05-17
 */
public class Image extends SortableEntity<Image> {

	private static final long serialVersionUID = 1L;
	/**
	 * 图片名称
	 * 
	 */
	private String name;
	/**
	 * 所属相册
	 * 
	 */
	private ImageAlbum album;
	/**
	 * 所属相册名称
	 * 
	 */
	private String albumName;
	/**
	 * 图片路径
	 * 
	 */
	private String storagePath;
	/**
	 * 图片访问链接
	 * 
	 */
	private String url;
	/**
	 * 图片路径类型
	 * 
	 */
	private PathType pathType;
	/**
	 * 扩展名
	 * 
	 */
	private String extension;
	/**
	 * 宽度
	 * 
	 */
	private String width;
	/**
	 * 高度
	 * 
	 */
	private String height;

	/**
	 * 是否显示
	 */
	private Boolean isShow;

	public Image() {
		super();
	}

	public Image(String id) {
		super(id);
	}

	/**
	 * 获取图片名称
	 * 
	 * @return 图片名称
	 */
	@Length(min = 1, max = 255)
	public String getName() {
		return name;
	}

	/**
	 * 设置图片名称
	 * 
	 * @param name
	 *            图片名称
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获取所属相册
	 * 
	 * @return 所属相册
	 */
	@NotNull
	public ImageAlbum getAlbum() {
		return album;
	}

	/**
	 * 设置所属相册
	 * 
	 * @param album
	 *            所属相册
	 */
	public void setAlbum(ImageAlbum album) {
		this.album = album;
	}

	/**
	 * 获取所属相册名称
	 * 
	 * @return 所属相册名称
	 */
	@Length(min = 1, max = 2000)
	public String getAlbumName() {
		return albumName;
	}

	/**
	 * 设置所属相册名称
	 * 
	 * @param albumName
	 *            所属相册名称
	 */
	public void setAlbumName(String albumName) {
		this.albumName = albumName;
	}

	/**
	 * 获取图片路径
	 * 
	 * @return 图片路径
	 */
	@Length(min = 1, max = 255)
	public String getStoragePath() {
		return storagePath;
	}

	/**
	 * 设置图片路径
	 * 
	 * @param storagePath
	 *            图片路径
	 */
	public void setStoragePath(String storagePath) {
		this.storagePath = storagePath;
	}

	/**
	 * 获取图片访问链接
	 * 
	 * @return 图片访问链接
	 */
	@Length(min = 1, max = 255)
	public String getUrl() {
		return url;
	}

	/**
	 * 设置图片访问链接
	 * 
	 * @param url
	 *            图片访问链接
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * 获取图片路径类型
	 * 
	 * @return 图片路径类型
	 */
	@NotNull
	public PathType getPathType() {
		return pathType;
	}

	/**
	 * 设置图片路径类型
	 * 
	 * @param pathType
	 *            图片路径类型
	 */
	public void setPathType(PathType pathType) {
		this.pathType = pathType;
	}

	/**
	 * 获取扩展名
	 * 
	 * @return 扩展名
	 */
	@Length(min = 1, max = 10)
	public String getExtension() {
		return extension;
	}

	/**
	 * 设置扩展名
	 * 
	 * @param extension
	 *            扩展名
	 */
	public void setExtension(String extension) {
		this.extension = extension;
	}

	/**
	 * 获取宽度
	 * 
	 * @return 宽度
	 */
	@Length(min = 1, max = 20)
	public String getWidth() {
		return width;
	}

	/**
	 * 设置宽度
	 * 
	 * @param width
	 *            宽度
	 */
	public void setWidth(String width) {
		this.width = width;
	}

	/**
	 * 获取高度
	 * 
	 * @return 高度
	 */
	@Length(min = 1, max = 20)
	public String getHeight() {
		return height;
	}

	/**
	 * 设置高度
	 * 
	 * @param height
	 *            高度
	 */
	public void setHeight(String height) {
		this.height = height;
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
}