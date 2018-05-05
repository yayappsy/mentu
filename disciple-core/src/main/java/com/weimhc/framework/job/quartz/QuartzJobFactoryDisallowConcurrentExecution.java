package com.weimhc.framework.job.quartz;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.weimhc.framework.job.quartz.entity.ScheduleJob;
import com.weimhc.framework.job.quartz.utils.TaskUtils;

/**
 * 
 * @Description: 若一个方法一次执行不完下次轮转时则等待改方法执行完后才执行下一次操作
 * @author szuo
 */
@DisallowConcurrentExecution
public class QuartzJobFactoryDisallowConcurrentExecution
		extends AbstractQuartzJob {

	@Override
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		ScheduleJob scheduleJob = (ScheduleJob) context.getMergedJobDataMap()
				.get("scheduleJob");
		TaskUtils.invokMethod(scheduleJob);

	}
}