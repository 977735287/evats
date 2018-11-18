package pers.hsc.evats.modules.task.service.impl;

import java.io.Serializable;
import java.util.List;

import org.quartz.SchedulerException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.EntityWrapper;

import pers.hsc.evats.core.common.service.impl.CommonServiceImpl;
import pers.hsc.evats.core.quartz.QuartzManager;
import pers.hsc.evats.modules.task.entity.ScheduleJob;
import pers.hsc.evats.modules.task.mapper.ScheduleJobMapper;
import pers.hsc.evats.modules.task.service.IScheduleJobService;
import pers.hsc.evats.modules.task.utils.ScheduleJobUtils;

/**
 * 任务
 * 
 * @author hsc
 *
 * Mar 30, 2018
 */
@Transactional
@Service("scheduleJobService")
public class ScheduleJobServiceImpl extends CommonServiceImpl<ScheduleJobMapper, ScheduleJob>
		implements IScheduleJobService {
	private QuartzManager quartzManager;

	@Override
	public void updateCron(String jobId) throws SchedulerException {
		ScheduleJob scheduleJob = selectById(jobId);
		if (scheduleJob == null) {
			return;
		}
		if (pers.hsc.evats.core.quartz.data.ScheduleJob.STATUS_RUNNING.equals(scheduleJob.getJobStatus())) {
			quartzManager.updateJobCron(ScheduleJobUtils.entityToData(scheduleJob));
		}
		 super.insertOrUpdate(scheduleJob);
	}

	@Override
	public void changeStatus(String jobId, String cmd) throws SchedulerException {
		ScheduleJob scheduleJob = selectById(jobId);
		if (scheduleJob == null) {
			return;
		}
		if ("stop".equals(cmd)) {
			quartzManager.deleteJob(ScheduleJobUtils.entityToData(scheduleJob));
			scheduleJob.setJobStatus(pers.hsc.evats.core.quartz.data.ScheduleJob.STATUS_NOT_RUNNING);
		} else if ("start".equals(cmd)) {
			scheduleJob.setJobStatus(pers.hsc.evats.core.quartz.data.ScheduleJob.STATUS_RUNNING);
			quartzManager.addJob(ScheduleJobUtils.entityToData(scheduleJob));
		}
		 super.insertOrUpdate(scheduleJob);
	}

	@Override
	public boolean deleteById(Serializable id) {
		try {
			ScheduleJob scheduleJob = selectById(id);
			quartzManager.deleteJob(ScheduleJobUtils.entityToData(scheduleJob));
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
		return super.deleteById(id);
	}

	@Override
	public void initSchedule() throws SchedulerException {
		// 这里获取任务信息数据
		quartzManager = new QuartzManager();
		List<ScheduleJob> jobList = selectList(new EntityWrapper<ScheduleJob>());
		for (ScheduleJob scheduleJob : jobList) {
			quartzManager.addJob(ScheduleJobUtils.entityToData(scheduleJob));
		}
	}

}
