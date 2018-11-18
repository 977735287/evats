package pers.hsc.evats.modules.task.service;

import org.quartz.SchedulerException;

import pers.hsc.evats.core.common.service.ICommonService;
import pers.hsc.evats.modules.task.entity.ScheduleJob;

/**
 * 任务
 *
 * @author hsc
 *
 * Mar 29, 2018
 */
public interface IScheduleJobService extends ICommonService<ScheduleJob> {
	/**
	 * 
	 * @title: initSchedule
	 * @description: 初始化任务
	 * @throws SchedulerException
	 * @return: void
	 */
	public void initSchedule() throws SchedulerException;

	/**
	 * 更改状态
	 * 
	 * @throws SchedulerException
	 */
	public void changeStatus(String jobId, String cmd) throws SchedulerException;

	/**
	 * 更改任务 cron表达式
	 * 
	 * @throws SchedulerException
	 */
	public void updateCron(String jobId) throws SchedulerException;
}
