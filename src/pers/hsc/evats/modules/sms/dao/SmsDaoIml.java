package pers.hsc.evats.modules.sms.dao;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.baomidou.mybatisplus.mapper.EntityWrapper;

import pers.hsc.evats.core.disruptor.sms.SmsDao;
import pers.hsc.evats.core.disruptor.sms.SmsData;
import pers.hsc.evats.core.utils.StringUtils;
import pers.hsc.evats.core.utils.sms.data.SmsResult;
import pers.hsc.evats.modules.sms.entity.SmsSendLog;
import pers.hsc.evats.modules.sms.entity.SmsTemplate;
import pers.hsc.evats.modules.sms.service.ISmsSendLogService;
import pers.hsc.evats.modules.sms.service.ISmsTemplateService;

/**
 * 短信信息返回
 * 
 * @author hsc
 *
 * Mar 29, 2018
 */
public class SmsDaoIml implements SmsDao {
	@Autowired
	private ISmsSendLogService smsSendLogService;
	@Autowired
	private ISmsTemplateService smsTemplateService;

	@Override
	public void doStart() {

	}

	@Override
	public void doSend(String eventId, SmsData smsData) {
		SmsSendLog smsSendLog = new SmsSendLog();
		smsSendLog.setId(eventId);
		String templateId = smsData.getSmsTemplate().getTemplateId();
		try {
			SmsTemplate smsTemplateEntity = smsTemplateService
					.selectOne(new EntityWrapper<SmsTemplate>().eq("templateId", templateId));
			if (smsTemplateEntity != null) {
				smsSendLog.setBusinessType(smsTemplateEntity.getBusinessType());
			} else {
				smsSendLog.setBusinessType("99");
			}
		} catch (Exception e) {
			smsSendLog.setBusinessType("99");
		}

		smsSendLog.setTemplateContent(smsData.getSmsTemplate().getTemplateContent());
		smsSendLog.setTemplateId(smsData.getSmsTemplate().getTemplateId());
		smsSendLog.setPhone(smsData.getPhone());
		smsSendLog.setSenddata(StringUtils.join(smsData.getDatas(), ","));
		smsSendLog.setStatus("0");
		smsSendLogService.insert(smsSendLog);
	}

	@Override
	public void doResult(String eventId, SmsData smsData, SmsResult smsResult) {
		SmsSendLog smsSendLog = smsSendLogService.selectById(eventId);
		smsSendLog.setCode(smsResult.getCode());
		smsSendLog.setMsg(smsResult.getMsg());
		smsSendLog.setSmsid(smsResult.getSmsid());
		if (smsResult.isSuccess()) {
			smsSendLog.setStatus("1");
		} else {
			smsSendLog.setStatus("0");
		}
		smsSendLog.setResponseDate(new Date());
		smsSendLogService.insertOrUpdate(smsSendLog);
	}

	@Override
	public void doShutdown() {

	}

}
