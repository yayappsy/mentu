/**
 * 
 */
package com.weimhc.framework.persistence;

import com.thinkgem.javamg.common.persistence.DataEntity;

/**
 * 可排序实体
 * 
 * @version 2014-05-16
 */
public abstract class SortableEntity<T> extends DataEntity<T> {

	private static final long serialVersionUID = 1L;

	protected Integer sort; // 排序

	public SortableEntity() {
		super();
		this.sort = 10;
	}

	public SortableEntity(String id) {
		super(id);
	}

	/**
	 * 获取排序
	 * 
	 * @return 排序
	 */
	public Integer getSort() {
		return sort;
	}

	/**
	 * 设置排序
	 * 
	 * @param sort
	 *            排序
	 */
	public void setSort(Integer sort) {
		this.sort = sort;
	}

	/* (non-Javadoc)
	 * @see com.thinkgem.javamg.common.persistence.DataEntity#setDefaultValue()
	 */
	@Override
	public void setDefaultValue() {
		super.setDefaultValue();
		if (this.getSort() == null) {
			this.setSort(30);
		}
	}
}
