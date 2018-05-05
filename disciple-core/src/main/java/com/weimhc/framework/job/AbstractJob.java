package com.weimhc.framework.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thinkgem.javamg.common.config.Global;

/**
 * 定时任务运行虚拟类
 * 
 * @author szuo
 */
public abstract class AbstractJob {

	/**
	 * 日志对象
	 */
	protected Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 判断是否开启定时任务
	 * 
	 * @return
	 */
	public static boolean checkJobIsOpen() {
		return Global.TRUE.equals(Global.getConfig("job.isOpen"));
	}

}
