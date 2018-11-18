package pers.hsc.evats.modules.email.dao;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import pers.hsc.evats.core.disruptor.email.EmailDao;
import pers.hsc.evats.core.disruptor.email.EmailData;
import pers.hsc.evats.core.utils.StringUtils;
import pers.hsc.evats.core.utils.email.EmailResult;
import pers.hsc.evats.modules.email.entity.EmailSendLog;
import pers.hsc.evats.modules.email.service.IEmailSendLogService;

/**
 * 邮件信息返回
 * 
 * @author hsc
 *
 * Mar 29, 2018
 */
public class EmailDaoIml implements EmailDao {
	@Autowired
	private IEmailSendLogService emailSendLogService;

	@Override
	public void doStart() {

	}

	@Override
	public void doSend(String eventId, EmailData emailData) {
		EmailSendLog emailSendLog = new EmailSendLog();
		emailSendLog.setId(eventId);
		emailSendLog.setBusinessType(emailData.getBusinessType());
		emailSendLog.setSubject(emailData.getSubject());
		emailSendLog.setContent(emailData.getContent());
		emailSendLog.setEmail(emailData.getEmail());
		emailSendLog.setSenddata(StringUtils.join(emailData.getDatas(), ","));
		emailSendLog.setStatus("0");
		emailSendLogService.insert(emailSendLog);
	}

	@Override
	public void doResult(String eventId, EmailData emailData, EmailResult emailResult) {
		EmailSendLog emailSendLog = emailSendLogService.selectById(eventId);
		emailSendLog.setMsg(emailResult.getMsg());
		if (emailResult.isSuccess()) {
			emailSendLog.setStatus("1");
		} else {
			emailSendLog.setStatus("0");
		}
		emailSendLog.setResponseDate(new Date());
		emailSendLogService.insertOrUpdate(emailSendLog);
	}

	@Override
	public void doShutdown() {

	}

}
