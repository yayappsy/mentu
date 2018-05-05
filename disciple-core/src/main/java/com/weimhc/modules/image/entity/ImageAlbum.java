/**
 * 
 */
package com.weimhc.modules.image.entity;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.thinkgem.javamg.common.persistence.TreeEntity;

/**
 * 相册Entity
 * 
 * @author zsm
 * @version 2017-05-17
 */
public class ImageAlbum extends TreeEntity<ImageAlbum> {

	private static final long serialVersionUID = 1L;
	/**
	 * 相册名称
	 * 
	 */
	private String name;

	/**
	 * 描述
	 * 
	 */
	private String description;

	public ImageAlbum() {
		super();
	}

	public ImageAlbum(String id) {
		super(id);
	}

	/**
	 * 获取相册名称
	 * 
	 * @return 相册名称
	 */
	@Override
	@Length(min = 1, max = 100)
	public String getName() {
		return name;
	}

	/**
	 * 设置相册名称
	 * 
	 * @param name
	 *            相册名称
	 */
	@Override
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获取父节点
	 * 
	 * @return 父节点
	 */
	@Override
	@JsonBackReference
	@NotNull()
	public ImageAlbum getParent() {
		return parent;
	}

	/**
	 * 设置父节点
	 * 
	 * @param parent
	 *            父节点
	 */
	@Override
	public void setParent(ImageAlbum parent) {
		this.parent = parent;
	}

	/**
	 * 获取树路径
	 * 
	 * @return 树路径
	 */
	@Override
	@Length(min = 1, max = 2000)
	public String getParentIds() {
		return parentIds;
	}

	/**
	 * 设置树路径
	 * 
	 * @param parentIds
	 *            树路径
	 */
	@Override
	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
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
	 * 获取 父节点id
	 * 
	 * @return 父节点id
	 */
	@Override
	public String getParentId() {
		return parent != null && parent.getId() != null ? parent.getId() : "0";
	}
}