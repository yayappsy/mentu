/**
 * 
 */
package com.weimhc.framework.utils;

/**
 * 分页就算工具类
 *
 * @author laozh
 * @version 2017年3月31日
 */
public abstract class PageUtils {
	/**
	 * 计算分页数量
	 * 
	 * @param total
	 *            总数
	 * @param pageSize
	 *            每页多少条
	 * @return
	 */
	public static int getPageCount(int count, int pageSize) {
		return (count % pageSize == 0) ? (count / pageSize)
				: (count / pageSize + 1);
	}
}
