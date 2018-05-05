/**
 * 
 */
package com.weimhc.modules.sys.entity;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thinkgem.javamg.common.persistence.TreeEntity;

/**
 * 菜单Entity
 * 
 * @author zsm
 * @version 2016-02-18
 */
public class Menu extends TreeEntity<Menu> {

	private static final long serialVersionUID = 1L;
	/**
	 * 菜单的根节点（1）
	 */
	public static final String MENU_ROOT_ID = "1";

	private String href; // 链接
	private String target; // 目标（ mainFrame、_blank、_self、_parent、_top）
	private String icon; // 图标
	private String permission; // 权限标识

	private String userId;
	/**
	 * 是否进行权限分割
	 */
	private Boolean ifSeparate;

	public Menu() {
		super();
	}

	public Menu(String id) {
		super(id);
	}

	/**
	 * 获取父级编号
	 * 
	 * @return 父级编号
	 */
	@Override
	@JsonBackReference
	@NotNull()
	public Menu getParent() {
		return parent;
	}

	/**
	 * 设置父级编号
	 * 
	 * @param parent
	 *            父级编号
	 */
	@Override
	public void setParent(Menu parent) {
		this.parent = parent;
	}

	/**
	 * 获取所有父级编号
	 * 
	 * @return 所有父级编号
	 */
	@Override
	@Length(min = 1, max = 2000)
	public String getParentIds() {
		return parentIds;
	}

	/**
	 * 设置所有父级编号
	 * 
	 * @param parentIds
	 *            所有父级编号
	 */
	@Override
	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}

	/**
	 * 获取名称
	 * 
	 * @return 名称
	 */
	@Override
	@Length(min = 1, max = 100)
	public String getName() {
		return name;
	}

	/**
	 * 设置名称
	 * 
	 * @param name
	 *            名称
	 */
	@Override
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获取链接
	 * 
	 * @return 链接
	 */
	@Length(min = 0, max = 2000)
	public String getHref() {
		return href;
	}

	/**
	 * 设置链接
	 * 
	 * @param href
	 *            链接
	 */
	public void setHref(String href) {
		this.href = href;
	}

	/**
	 * 获取目标
	 * 
	 * @return 目标
	 */
	@Length(min = 0, max = 20)
	public String getTarget() {
		return target;
	}

	/**
	 * 设置目标
	 * 
	 * @param target
	 *            目标
	 */
	public void setTarget(String target) {
		this.target = target;
	}

	/**
	 * 获取图标
	 * 
	 * @return 图标
	 */
	@Length(min = 0, max = 100)
	public String getIcon() {
		return icon;
	}

	/**
	 * 设置图标
	 * 
	 * @param icon
	 *            图标
	 */
	public void setIcon(String icon) {
		this.icon = icon;
	}

	/**
	 * 获取排序
	 * 
	 * @return 排序
	 */
	@Override
	@NotNull
	public Integer getSort() {
		return sort;
	}

	/**
	 * 设置排序
	 * 
	 * @param sort
	 *            排序
	 */
	@Override
	public void setSort(Integer sort) {
		this.sort = sort;
	}

	/**
	 * 
	 * /** 获取权限标识
	 * 
	 * @return 权限标识
	 */
	@Length(min = 0, max = 200)
	public String getPermission() {
		return permission;
	}

	/**
	 * 设置权限标识
	 * 
	 * @param permission
	 *            权限标识
	 */
	public void setPermission(String permission) {
		this.permission = permission;
	}

	@Override
	public String getParentId() {
		return parent != null && parent.getId() != null ? parent.getId() : "0";
	}

	@JsonIgnore
	public static String getRootId() {
		return MENU_ROOT_ID;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return name;
	}

	/**
	 * 返回 是否进行权限分割
	 * 
	 * @return the ifSeparate
	 */
	public Boolean getIfSeparate() {
		return ifSeparate;
	}

	/**
	 * 设置 是否进行权限分割
	 * 
	 * @param ifSeparate
	 *            the ifSeparate to set
	 */
	public void setIfSeparate(Boolean ifSeparate) {
		this.ifSeparate = ifSeparate;
	}

}