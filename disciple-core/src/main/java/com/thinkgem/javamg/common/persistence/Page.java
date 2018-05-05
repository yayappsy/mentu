/**
 * 
 */
package com.thinkgem.javamg.common.persistence;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import com.thinkgem.javamg.common.utils.CookieUtils;

/**
 * 分页类
 * 
 * @version 2013-7-2
 * @param <T>
 */
public class Page<T> {

	/** 总记录数 */
	private long count;

	/** 分页信息 */
	private final Pageable pageable;

	/** 分页信息 */
	private List<T> list = new ArrayList<T>();

	public Page() {
		this.count = 0L;
		this.pageable = new Pageable();
	}

	/**
	 * 构造方法
	 * 
	 * @param request
	 *            传递 repage 参数，来记住页码
	 * @param response
	 *            用于设置 Cookie，记住页码
	 */
	public Page(HttpServletRequest request, HttpServletResponse response) {
		this(request, response, -2);
	}

	/**
	 * 构造方法
	 * 
	 * @param request
	 *            传递 repage 参数，来记住页码
	 * @param response
	 *            用于设置 Cookie，记住页码
	 * @param defaultPageSize
	 *            默认分页大小，如果传递 -1 则为不分页，返回所有数据
	 */
	public Page(HttpServletRequest request, HttpServletResponse response, int defaultPageSize) {
		this.pageable = new Pageable();
		// 设置页码参数（传递repage参数，来记住页码）
		String no = request.getParameter("pageNo");
		if (StringUtils.isNumeric(no)) {
			CookieUtils.setCookie(response, "pageNo", no);
			pageable.setPageNo(Integer.parseInt(no));
		} else if (request.getParameter("repage") != null) {
			no = CookieUtils.getCookie(request, "pageNo");
			if (StringUtils.isNumeric(no)) {
				pageable.setPageNo(Integer.parseInt(no));
			}
		}
		// 设置页面大小参数（传递repage参数，来记住页码大小）
		String size = request.getParameter("pageSize");
		if (NumberUtils.isCreatable(size)) {
			CookieUtils.setCookie(response, "pageSize", size);
			pageable.setPageSize(Integer.parseInt(size));
		} else if (request.getParameter("repage") != null) {
			no = CookieUtils.getCookie(request, "pageSize");
			if (StringUtils.isNumeric(size)) {
				pageable.setPageSize(Integer.parseInt(size));
			}
		} else if (defaultPageSize != -2) {
			pageable.setPageSize(defaultPageSize);
		}
		// 设置排序参数
		String orderBy = request.getParameter("orderBy");
		if (StringUtils.isNotBlank(orderBy)) {
			pageable.setOrderBy(orderBy);
		}
	}

	/**
	 * 构造方法
	 * 
	 * @param pageNo
	 *            当前页码
	 * @param pageSize
	 *            分页大小
	 */
	public Page(int pageNo, int pageSize) {
		this(pageNo, pageSize, 0);
	}

	/**
	 * 构造方法
	 * 
	 * @param pageNo
	 *            当前页码
	 * @param pageSize
	 *            分页大小
	 * @param count
	 *            数据条数
	 */
	public Page(int pageNo, int pageSize, long count) {
		this(pageNo, pageSize, count, new ArrayList<T>());
	}

	/**
	 * 构造方法
	 * 
	 * @param pageNo
	 *            当前页码
	 * @param pageSize
	 *            分页大小
	 * @param count
	 *            数据条数
	 * @param list
	 *            本页数据对象列表
	 */
	public Page(int pageNo, int pageSize, long count, List<T> list) {
		this.pageable = new Pageable(pageNo, pageSize, count);
		this.list = list;
	}

	/**
	 * 获取本页数据对象列表
	 * 
	 * @return List<T>
	 */
	public List<T> getList() {
		return list;
	}

	/**
	 * 设置本页数据对象列表
	 * 
	 * @param list
	 */
	public Page<T> setList(List<T> list) {
		this.list = list;
		pageable.initialize();
		return this;
	}

	/**
	 * 获取分页信息
	 * 
	 * @return 分页信息
	 */
	public Pageable getPageable() {
		return pageable;
	}

	/**
	 * 获取设置总数
	 * 
	 * @return
	 */
	public long getCount() {
		return count;
	}

	/**
	 * 设置数据总数
	 * 
	 * @param count
	 */
	public void setCount(long count) {
		this.count = count;
		pageable.setCount(count);
	}

}
