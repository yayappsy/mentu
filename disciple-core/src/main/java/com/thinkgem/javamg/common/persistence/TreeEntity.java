/**
 * 
 */
package com.thinkgem.javamg.common.persistence;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Lists;
import com.thinkgem.javamg.common.utils.Reflections;
import com.thinkgem.javamg.common.utils.StringUtils;

/**
 * 数据Entity类
 * 
 * @version 2014-05-16
 */
public abstract class TreeEntity<T> extends DataEntity<T> {

	private static final long serialVersionUID = 1L;
	/**
	 * 默认根节点（0）
	 */
	public static final String ROOT_ID = "0";

	protected T parent; // 父级编号
	protected String parentIds; // 所有父级编号
	protected String parentNames;// 所有父级名称
	protected String oldName;// 修改之前节点的名称
	protected String name; // 名称
	protected Integer sort; // 排序

	protected Integer grade; // 级别

	protected List<String> prarentIdList;

	/**
	 * 是否显示
	 */
	protected Boolean isShow;
	/**
	 * 搜索使用 只搜索该节点以下节点
	 */
	private String topLevelId;

	public TreeEntity() {
		super();
		this.sort = 30;
	}

	public TreeEntity(String id) {
		super(id);
	}

	/**
	 * 父对象，只能通过子类实现，父类实现mybatis无法读取
	 * 
	 * @return
	 */
	@JsonBackReference
	@NotNull
	public abstract T getParent();

	/**
	 * 父对象，只能通过子类实现，父类实现mybatis无法读取
	 * 
	 * @return
	 */
	public abstract void setParent(T parent);

	@Length(min = 1, max = 2000)
	public String getParentIds() {
		return parentIds;
	}

	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}

	@Length(min = 1, max = 100)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
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
	 * 返回 级别
	 * 
	 * @return the grade
	 */
	public Integer getGrade() {
		return grade;
	}

	/**
	 * 设置 级别
	 * 
	 * @param grade
	 *            the grade to set
	 */
	public void setGrade(Integer grade) {
		this.grade = grade;
	}

	public String getParentId() {
		String id = null;
		if (parent != null) {
			id = (String) Reflections.getFieldValue(parent, "id");
		}
		return StringUtils.isNotBlank(id) ? id : "0";
	}

	/**
	 * 获取父级名称
	 * 
	 * @return the parentNames
	 */
	public String getParentNames() {
		return parentNames;
	}

	/**
	 * 设置父级名称
	 * 
	 * @param parentNames
	 *            the parentNames to set
	 */
	public void setParentNames(String parentNames) {
		this.parentNames = parentNames;
	}

	/**
	 * 获取修改之前节点的名称
	 * 
	 * @return the oldNames
	 */
	public String getOldName() {
		return oldName;
	}

	/**
	 * 设置修改之前节点的名称
	 * 
	 * @param oldNames
	 *            the oldNames to set
	 */
	public void setOldNames(String oldName) {
		this.oldName = oldName;
	}

	@JsonIgnore
	public List<String> getParentIdList() {
		prarentIdList = Lists
				.newArrayList(StringUtils.split(getParentIds(), ","));
		return prarentIdList;
	}

	/**
	 * 返回 搜索使用 只搜索该节点以下节点
	 * 
	 * @return the topLevelId
	 */
	public String getTopLevelId() {
		return topLevelId;
	}

	/**
	 * 设置 搜索使用 只搜索该节点以下节点
	 * 
	 * @param topLevelId
	 *            the topLevelId to set
	 */
	public void setTopLevelId(String topLevelId) {
		this.topLevelId = topLevelId;
	}

	/**
	 * 抽取指定根节点下的树节点，并排序
	 * 
	 * @param sourcelist
	 *            源列表
	 * @param targetList
	 *            已排序好的树结构（列表中不包括该父节点）
	 * @param parentId
	 *            要查询的父节点
	 * @param cascade
	 *            是否还有子节点
	 */
	@JsonIgnore
	public static <T extends TreeEntity<T>> void sortList(List<T> sourcelist,
			List<T> targetList, String parentId, boolean cascade) {

		for (int i = 0; i < sourcelist.size(); i++) {
			T e = sourcelist.get(i);
			if (e.getParent() != null && StringUtils
					.equalsIgnoreCase(e.getParent().getId(), parentId)) {
				targetList.add(e);
				if (cascade) {
					// 判断是否还有子节点, 有则继续获取子节点
					for (int j = 0; j < sourcelist.size(); j++) {
						T child = sourcelist.get(j);
						if (child.getParent() != null
								&& StringUtils.equalsIgnoreCase(
										child.getParent().getId(), e.getId())) {
							sortList(sourcelist, targetList, e.getId(), true);
							break;
						}
					}
				}
			}
		}
	}

}
