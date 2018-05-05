package com.weimhc.modules.base.entity;

import java.util.Date;

import org.joda.time.DateTime;

/**
 * 时间查询参数
 * 
 * @author lc
 * @version 2017-03-15
 */
public enum SearchTime {
	/**
	 * 今天
	 */
	now(0, "day"),
	/**
	 * 昨天
	 */
	yesterday(-1, "day"),

	/**
	 * 近七天
	 */
	lastWeek(-1, "week"),

	/**
	 * 近30天
	 */
	lastMonth(-1, "month"),

	/**
	 * 自定义
	 */
	custom(0, "other");

	private int interval;

	private String type;

	private SearchTime(int interval, String type) {
		this.interval = interval;
		this.type = type;
	}

	/**
	 * @return the interval
	 */
	public int getInterval() {
		return interval;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	public Date getBeginDate() {

		DateTime dateTime = new DateTime();
		switch (type) {
		case "day":
			dateTime = dateTime.plusDays(interval);
			return dateTime.toDate();
		case "week":
			dateTime = dateTime.plusWeeks(interval);
			return dateTime.toDate();
		case "month":
			dateTime = dateTime.plusMonths(interval);
			return dateTime.toDate();
		default:
			return null;
		}

	};

	public Date getEndDate() {
		return new Date();
	}

	public static void main(String[] args) {
		System.out.println(SearchTime.lastMonth.getBeginDate());
	}
}