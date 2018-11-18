package pers.hsc.evats.modules.sms.service.impl;

import pers.hsc.evats.core.common.service.impl.CommonServiceImpl;
import pers.hsc.evats.modules.sms.entity.SmsTemplate;
import pers.hsc.evats.modules.sms.mapper.SmsTemplateMapper;
import pers.hsc.evats.modules.sms.service.ISmsTemplateService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 短信模版
 * 
 * @author hsc
 *
 * Mar 29, 2018
 */
@Transactional
@Service("smsTemplateService")
public class SmsTemplateServiceImpl extends CommonServiceImpl<SmsTemplateMapper,SmsTemplate> implements ISmsTemplateService {
}
