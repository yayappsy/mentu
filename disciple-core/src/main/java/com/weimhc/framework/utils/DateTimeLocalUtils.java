package com.weimhc.framework.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/***
 * 解析h5页面的datetime-local类型
 * 
 * @author szuo
 *
 */
public abstract class DateTimeLocalUtils {

	/**
	 * 日志对象
	 */
	protected static Logger logger = LoggerFactory
			.getLogger(DateTimeLocalUtils.class);

	public static final String DATA_FROMAT = "yyyy-MM-dd hh:mm";
	public static SimpleDateFormat sft = new SimpleDateFormat(DATA_FROMAT);

	/**
	 * 解析日期
	 * 
	 * @param dataStr
	 * @return
	 */
	public static Date praseDate(String dataStr) {
		try {
			return sft.parse(dataStr);
		} catch (ParseException e) {
			logger.debug(e.getMessage());
		}
		return new Date();
	}
}
