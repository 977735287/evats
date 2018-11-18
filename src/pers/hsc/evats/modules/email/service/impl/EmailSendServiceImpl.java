package pers.hsc.evats.modules.email.service.impl;

import pers.hsc.evats.core.disruptor.email.EmailEvent.EmailHandlerCallBack;
import pers.hsc.evats.core.disruptor.email.EmailHelper;
import pers.hsc.evats.core.utils.SpringContextHolder;
import pers.hsc.evats.core.utils.email.EmailResult;
import pers.hsc.evats.core.utils.email.EmailUtils;
import pers.hsc.evats.modules.email.entity.EmailTemplate;
import pers.hsc.evats.modules.email.service.IEmailSendService;
import pers.hsc.evats.modules.email.service.IEmailTemplateService;

import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.baomidou.mybatisplus.mapper.EntityWrapper;

/**
 * 邮件模版
 * 
 * @author hsc
 *
 * Mar 29, 2018
 */
@Transactional
@Service("emailSendService")
public class EmailSendServiceImpl implements IEmailSendService {
	@Autowired
	private IEmailTemplateService emailTemplateService;

	@Override
	public void sendAsyncEmailByCode(String email, String code, String... datas) {
		sendAsyncEmailByCode(email, code, null, datas);
	}

	@Override
	public void sendAsyncEmailByCode(String email, String code, EmailHandlerCallBack callBack, String... datas) {
		EmailTemplate emailTemplate = emailTemplateService
				.selectOne(new EntityWrapper<EmailTemplate>().eq("code", code));
		String templateSubject = emailTemplate.getTemplateSubject();
		String templateContent = emailTemplate.getTemplateContent();
		templateContent = StringEscapeUtils.unescapeHtml4(StringEscapeUtils.unescapeHtml4(templateContent));
		SpringContextHolder.getBean(EmailHelper.class).sendAsyncEmail(email, templateSubject, templateContent, callBack,
				datas);
	}

	@Override
	public EmailResult sendSyncEmailByCode(String email, String code, String... datas) {
		EmailTemplate emailTemplate = emailTemplateService
				.selectOne(new EntityWrapper<EmailTemplate>().eq("code", code));
		String templateSubject = emailTemplate.getTemplateSubject();
		String templateContent = emailTemplate.getTemplateContent();
		templateContent = EmailUtils.analysisTemplate(templateContent,datas);
		templateContent = StringEscapeUtils.unescapeHtml4(StringEscapeUtils.unescapeHtml4(templateContent));
		return SpringContextHolder.getBean(EmailHelper.class).sendSyncEmail(email, templateSubject, templateContent,
				datas);
	}

	@Override
	public void sendAsyncEmail(String email, String subject, String content, String... datas) {
		SpringContextHolder.getBean(EmailHelper.class).sendAsyncEmail(email, subject, content, datas);
	}

	@Override
	public void sendAsyncEmail(String email, String subject, String content, EmailHandlerCallBack callBack,
			String... datas) {
		SpringContextHolder.getBean(EmailHelper.class).sendAsyncEmail(email, subject, content, callBack, datas);
	}

	@Override
	public EmailResult sendSyncEmail(String email, String subject, String content, String... datas) {
		return SpringContextHolder.getBean(EmailHelper.class).sendSyncEmail(email, subject, content, datas);
	}

}
