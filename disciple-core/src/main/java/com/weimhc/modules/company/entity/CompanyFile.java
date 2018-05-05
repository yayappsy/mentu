/**
 * 
 */
package com.weimhc.modules.company.entity;

import org.hibernate.validator.constraints.Length;

import com.weimhc.framework.persistence.BaseNameEntity;
import com.weimhc.modules.base.entity.PathType;

/**
 * 企业文件Entity
 * 
 * @author zsm
 * @version 2017-06-26
 */
public class CompanyFile extends BaseNameEntity<CompanyFile> {

	private static final long serialVersionUID = 1L;

	/**
	 * 存储路径
	 * 
	 */
	private String storagePath;
	/**
	 * 访问链接
	 * 
	 */
	private String url;
	/**
	 * 路径类型
	 * 
	 */
	private PathType pathType;
	/**
	 * 扩展名
	 * 
	 */
	private String extension;
	/**
	 * 大小
	 * 
	 */
	private String size;

	public CompanyFile() {
		super();
	}

	public CompanyFile(String id) {
		super(id);
	}

	/**
	 * 获取存储路径
	 * 
	 * @return 存储路径
	 */
	@Length(min = 0, max = 255)
	public String getStoragePath() {
		return storagePath;
	}

	/**
	 * 设置存储路径
	 * 
	 * @param storagePath
	 *            存储路径
	 */
	public void setStoragePath(String storagePath) {
		this.storagePath = storagePath;
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
	 * 获取路径类型
	 * 
	 * @return 路径类型
	 */
	public PathType getPathType() {
		return pathType;
	}

	/**
	 * 设置路径类型
	 * 
	 * @param pathType
	 *            路径类型
	 */
	public void setPathType(PathType pathType) {
		this.pathType = pathType;
	}

	/**
	 * 获取扩展名
	 * 
	 * @return 扩展名
	 */
	@Length(min = 0, max = 10)
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
	 * 获取大小
	 * 
	 * @return 大小
	 */
	@Length(min = 1, max = 20)
	public String getSize() {
		return size;
	}

	/**
	 * 设置大小
	 * 
	 * @param size
	 *            大小
	 */
	public void setSize(String size) {
		this.size = size;
	}
}