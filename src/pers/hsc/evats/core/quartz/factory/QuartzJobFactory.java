package pers.hsc.evats.core.quartz.factory;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import pers.hsc.evats.core.quartz.data.ScheduleJob;
import pers.hsc.evats.core.quartz.utils.TaskUtils;

/**
 * 计划任务执行处 无状态
 * 
 * @author hsc
 *
 * Mar 30, 2018
 */
public class QuartzJobFactory implements Job {
	public final Logger log = Logger.getLogger(this.getClass());

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		ScheduleJob scheduleJob = (ScheduleJob) context.getMergedJobDataMap().get("scheduleJob");
		TaskUtils.invokMethod(scheduleJob);
	}
}