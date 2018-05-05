/**
 * 
 */
package com.weimhc.framework.dto.resp;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Lists;
import com.thinkgem.javamg.common.persistence.Pageable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 
 * 分页返回数据封装
 * <P>
 * 使用 JsonIgnore 避免生成额外的josn
 * </P>
 * 
 * @author szuo
 * @see com.fasterxml.jackson.annotation.JsonIgnore
 */
@ApiModel
public class PageRS<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2235321448310715318L;
	/**
	 * 当前页有多少条记录
	 */
	private long currentCount;

	/** 分页信息 */
	private List<T> dataList = Lists.newArrayList();

	private Pageable pageable;

	/**
	 * 返回 当前页有多少条记录
	 * 
	 * @return the currentCount
	 */
	@ApiModelProperty("当前页有多少条记录")
	public long getCurrentCount() {
		if (dataList != null) {
			return dataList.size();
		}
		return currentCount;
	}

	/**
	 * 设置 当前页有多少条记录
	 * 
	 * @param currentCount
	 *            the currentCount to set
	 */
	public void setCurrentCount(long currentCount) {
		this.currentCount = currentCount;
	}

	/**
	 * 返回 分页信息
	 * 
	 * @return the list
	 */
	@ApiModelProperty("list数据")
	public List<T> getDataList() {
		return dataList;
	}

	/**
	 * 设置 分页信息
	 * 
	 * @param list
	 *            the list to set
	 */
	public void setDataList(List<T> dataList) {
		this.dataList = dataList;
	}

	/**
	 * @return the pageable
	 */
	@ApiIgnore
	@JsonIgnore
	public Pageable getPageable() {
		return pageable;
	}

	/**
	 * @param pageable
	 *            the pageable to set
	 */
	public void setPageable(Pageable pageable) {
		this.pageable = pageable;
	}

	@ApiModelProperty(value = "当前页码")
	public Integer getPageNo() {
		if (getPageable() != null) {
			return getPageable().getPageNo();
		}
		return null;
	}

	@ApiModelProperty(value = "每页多少条记录")
	public Integer getPageSize() {
		if (getPageable() != null) {
			return getPageable().getPageSize();
		}
		return null;
	}

	@ApiModelProperty(value = "总记录数")
	public long getCount() {
		if (getPageable() != null) {
			return getPageable().getCount();
		}
		return 0;
	}

	@ApiModelProperty(value = "总共多少页")
	public long getLastCount() {
		if (getPageable() != null) {
			return getPageable().getLast();
		}
		return 0;
	}

	/**
	 * 是否为最后一页
	 * 
	 * @return
	 */
	@ApiModelProperty(value = "是否最后一页")
	public boolean getIsLastPage() {
		if (getPageable() != null) {
			return getPageable().isLastPage();
		}
		return false;
	}
}
