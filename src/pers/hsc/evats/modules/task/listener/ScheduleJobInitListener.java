package pers.hsc.evats.modules.task.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import pers.hsc.evats.core.utils.SpringContextHolder;
import pers.hsc.evats.modules.task.service.IScheduleJobService;

public class ScheduleJobInitListener implements ApplicationListener<ContextRefreshedEvent> {

	protected IScheduleJobService scheduleJobService = SpringContextHolder.getApplicationContext()
			.getBean(IScheduleJobService.class);

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		try {
			scheduleJobService.initSchedule();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}