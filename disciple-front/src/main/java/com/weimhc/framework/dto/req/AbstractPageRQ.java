/**
 * 
 */
package com.weimhc.framework.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 所有分页请求封装
 * 
 * @author szuo
 *
 */
@ApiModel
public abstract class AbstractPageRQ extends AbstractRQ {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8041111535285638861L;

	/**
	 * 第几页
	 */
	private int pageNo = 1; // 当前页码
	/**
	 * 每页多少条
	 */
	private int pageSize = 10;

	/**
	 * 返回 第几页
	 * 
	 * @return the pageNo
	 */
	@ApiModelProperty(value = "第几页", example = "1")
	public int getPageNo() {
		return pageNo;
	}

	/**
	 * 设置 第几页
	 * 
	 * @param pageNo
	 *            the pageNo to set
	 */
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	/**
	 * 返回 每页多少条
	 * 
	 * @return the pageSize
	 */
	@ApiModelProperty(value = "每页多少条记录", example = "10")
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * 设置 每页多少条
	 * 
	 * @param pageSize
	 *            the pageSize to set
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

}
