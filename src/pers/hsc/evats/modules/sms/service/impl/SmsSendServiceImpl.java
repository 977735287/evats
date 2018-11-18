package pers.hsc.evats.modules.sms.service.impl;

import pers.hsc.evats.core.disruptor.sms.SmsEvent.SmsHandlerCallBack;
import pers.hsc.evats.core.disruptor.sms.SmsHelper;
import pers.hsc.evats.core.utils.SpringContextHolder;
import pers.hsc.evats.core.utils.sms.data.SmsResult;
import pers.hsc.evats.modules.sms.entity.SmsTemplate;
import pers.hsc.evats.modules.sms.service.ISmsSendService;
import pers.hsc.evats.modules.sms.service.ISmsTemplateService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.EntityWrapper;

/**
 * 短信模版
 * 
 * @author hsc
 *
 * Mar 29, 2018
 */
@Transactional
@Service("smsSendService")
public class SmsSendServiceImpl implements ISmsSendService {
	@Autowired
	private ISmsTemplateService smsTemplateService;

	@Override
	public void sendAsyncSmsByCode(String phone, String code, String... datas) {
		sendAsyncSmsByCode(phone, code, null, datas);
	}

	@Override
	public void sendAsyncSmsByCode(String phone, String code, SmsHandlerCallBack callBack, String... datas) {
		SmsTemplate smsTemplate = smsTemplateService.selectOne(new EntityWrapper<SmsTemplate>().eq("code", code));
		String templateContent = smsTemplate.getTemplateContent();
		String templateId = smsTemplate.getTemplateId();
		pers.hsc.evats.core.utils.sms.data.SmsTemplate template = pers.hsc.evats.core.utils.sms.data.SmsTemplate.newTemplate(templateId, templateContent);
		SpringContextHolder.getBean(SmsHelper.class).sendAsyncSms(phone, template, callBack, datas);
	}

	@Override
	public SmsResult huyiSendSmsByCode(String phone, String code, String... datas) {
		SmsTemplate smsTemplate = smsTemplateService.selectOne(new EntityWrapper<SmsTemplate>().eq("code", code));
		String templateContent = smsTemplate.getTemplateContent();
		String templateId = smsTemplate.getTemplateId();
		pers.hsc.evats.core.utils.sms.data.SmsTemplate template = pers.hsc.evats.core.utils.sms.data.SmsTemplate.newTemplate(templateId, templateContent);
		return SpringContextHolder.getBean(SmsHelper.class).sendSyncSmsByHuyi(phone, template, datas);
	}

	@Override
	public SmsResult ucpaasSendSmsByCode(String phone, String code, String... datas) {
		SmsTemplate smsTemplate = smsTemplateService.selectOne(new EntityWrapper<SmsTemplate>().eq("code", code));
		String templateContent = smsTemplate.getTemplateContent();
		String templateId = smsTemplate.getTemplateId();
		pers.hsc.evats.core.utils.sms.data.SmsTemplate template = pers.hsc.evats.core.utils.sms.data.SmsTemplate.newTemplate(templateId, templateContent);
		return SpringContextHolder.getBean(SmsHelper.class).sendSyncSmsByUcpaas(phone, template, datas);
	}

}
