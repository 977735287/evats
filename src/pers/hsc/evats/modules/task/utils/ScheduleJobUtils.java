package pers.hsc.evats.modules.task.utils;

import pers.hsc.evats.modules.task.entity.ScheduleJob;

public class ScheduleJobUtils {
     public static pers.hsc.evats.core.quartz.data.ScheduleJob entityToData(ScheduleJob scheduleJobEntity){
    	 pers.hsc.evats.core.quartz.data.ScheduleJob scheduleJob=new pers.hsc.evats.core.quartz.data.ScheduleJob();
    	 scheduleJob.setBeanClass(scheduleJobEntity.getBeanClass());
    	 scheduleJob.setCronExpression(scheduleJobEntity.getCronExpression());
    	 scheduleJob.setDescription(scheduleJobEntity.getDescription());
    	 scheduleJob.setIsConcurrent(scheduleJobEntity.getIsConcurrent());
    	 scheduleJob.setJobName(scheduleJobEntity.getJobName());
    	 scheduleJob.setJobGroup(scheduleJobEntity.getJobGroup());
    	 scheduleJob.setJobStatus(scheduleJobEntity.getJobStatus());
    	 scheduleJob.setMethodName(scheduleJobEntity.getMethodName());
    	 scheduleJob.setSpringBean(scheduleJobEntity.getSpringBean());
    	 return scheduleJob;
     }
}
