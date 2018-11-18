package pers.hsc.evats.modules.task.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.quartz.CronExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import pers.hsc.evats.core.common.controller.BaseCRUDController;
import pers.hsc.evats.core.model.AjaxJson;
import pers.hsc.evats.core.security.shiro.authz.annotation.RequiresPathPermission;
import pers.hsc.evats.core.utils.MyBeanUtils;
import pers.hsc.evats.core.utils.ObjectUtils;
import pers.hsc.evats.modules.task.entity.ScheduleJob;
import pers.hsc.evats.modules.task.service.IScheduleJobService;

/**
 * 任务
 * 
 * @author hsc
 *
 * Mar 30, 2018
 */
@Controller
@RequestMapping("${admin.url.prefix}/task/schedulejob")
@RequiresPathPermission("task:schedulejob")
public class ScheduleJobController extends BaseCRUDController<ScheduleJob, String> {

	@Autowired
	private IScheduleJobService scheduleJobService;

	@RequestMapping(value = "/changeJobStatus", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson changeJobStatus(ScheduleJob scheduleJob, HttpServletRequest request,
			HttpServletResponse response) {
		String cmd = request.getParameter("cmd");
		AjaxJson ajaxJson = new AjaxJson();
		String label = "停止";
		if (cmd.equals("start")) {
			label = "启动";
		} else {
			label = "停止";
		}
		ajaxJson.success("任务" + label + "成功");
		try {
			scheduleJobService.changeStatus(scheduleJob.getId(), cmd);
		} catch (Exception e) {
			e.printStackTrace();
			ajaxJson.fail("任务" + label + "失败" + e.getMessage());
		}
		return ajaxJson;
	}

	@RequestMapping(value = "/updateCron", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson updateCron(ScheduleJob scheduleJob, HttpServletRequest request,
			HttpServletResponse response) {
		AjaxJson ajaxJson = new AjaxJson();
		ajaxJson.success("任务更新成功");
		try {
			scheduleJobService.updateCron(scheduleJob.getId());
		} catch (Exception e) {
			e.printStackTrace();
			ajaxJson.fail("任务更新失败" + e.getMessage());
		}
		return ajaxJson;
	}
	
	@RequestMapping(value = "/saveScheduleJob", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson saveScheduleJob(ScheduleJob scheduleJob, HttpServletRequest request, HttpServletResponse response) {
		AjaxJson ajaxJson = new AjaxJson();
		ajaxJson.success("保存成功");
		if (!CronExpression.isValidExpression(scheduleJob.getCronExpression())) {
			ajaxJson.fail("cron表达式格式不对");
	    	return ajaxJson;
		}
		try {
			 if (ObjectUtils.isNullOrEmpty(scheduleJob.getId())) {
				commonService.insert(scheduleJob);
			} else {
				// FORM NULL不更新
				ScheduleJob oldEntity = commonService.selectById(scheduleJob.getId());
				MyBeanUtils.copyBeanNotNull2Bean(scheduleJob, oldEntity);
				commonService.insertOrUpdate(oldEntity);
			}
		} catch (Exception e) {
			e.printStackTrace();
			ajaxJson.fail("保存失败"+e.getMessage());
		}
		return ajaxJson;
	}
 
}
