package com.weimhc.framework.job.quartz.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thinkgem.javamg.common.utils.SpringContextHolder;
import com.thinkgem.javamg.common.utils.StringUtils;
import com.weimhc.framework.job.quartz.entity.ScheduleJob;

public abstract class TaskUtils {
	/**
	 * 日志对象
	 */
	private static Logger logger = LoggerFactory.getLogger(TaskUtils.class);

	/**
	 * 通过反射调用scheduleJob中定义的方法
	 * 
	 * @param scheduleJob
	 */
	public static void invokMethod(ScheduleJob scheduleJob) {
		Object object = null;
		Class<?> clazz = null;
		if (StringUtils.isNotBlank(scheduleJob.getSpringId())) {
			object = SpringContextHolder.getBean(scheduleJob.getSpringId());
		} else if (StringUtils.isNotBlank(scheduleJob.getBeanClass())) {
			try {
				clazz = Class.forName(scheduleJob.getBeanClass());
				object = clazz.newInstance();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				logger.error(e.getMessage(), e);;
			}

		}
		if (object == null) {
			logger.error("任务名称 = [" + scheduleJob.getJobName()
					+ "]---------------未启动成功，请检查是否配置正确！！！");
			return;
		}
		clazz = object.getClass();
		Method method = null;
		try {
			method = clazz.getDeclaredMethod(scheduleJob.getMethodName());
		} catch (NoSuchMethodException e) {
			logger.error("任务名称 = [" + scheduleJob.getJobName()
					+ "]---------------未启动成功，方法名设置错误！！！");
		} catch (SecurityException e) {
			logger.error(e.getMessage(), e);;
		}
		if (method != null) {
			try {
				method.invoke(object);
			} catch (IllegalAccessException e) {
				logger.error(e.getMessage(), e);;
			} catch (IllegalArgumentException e) {
				logger.error(e.getMessage(), e);;
			} catch (InvocationTargetException e) {
				logger.error(e.getMessage(), e);;
			}
		}
		logger.debug("任务名称 = [" + scheduleJob.getJobName() + "]----------启动成功");
	}
}
