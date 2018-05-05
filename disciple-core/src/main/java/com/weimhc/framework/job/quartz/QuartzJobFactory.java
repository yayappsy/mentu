package com.weimhc.framework.job.quartz;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.weimhc.framework.job.quartz.entity.ScheduleJob;
import com.weimhc.framework.job.quartz.utils.TaskUtils;

/**
 * 定时任务运行工厂类
 * 
 * @author szuo
 */
public class QuartzJobFactory extends AbstractQuartzJob {

	@Override
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		ScheduleJob scheduleJob = (ScheduleJob) context.getMergedJobDataMap()
				.get("scheduleJob");
		TaskUtils.invokMethod(scheduleJob);

	}

}
