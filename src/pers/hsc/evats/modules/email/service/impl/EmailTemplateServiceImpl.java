package pers.hsc.evats.modules.email.service.impl;

import pers.hsc.evats.core.common.service.impl.CommonServiceImpl;
import pers.hsc.evats.modules.email.entity.EmailTemplate;
import pers.hsc.evats.modules.email.mapper.EmailTemplateMapper;
import pers.hsc.evats.modules.email.service.IEmailTemplateService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**   
 * 邮件发送模板
 * 
 * @author hsc
 *
 * Mar 29, 2018
 */
@Transactional
@Service("emailTemplateService")
public class EmailTemplateServiceImpl  extends CommonServiceImpl<EmailTemplateMapper,EmailTemplate> implements  IEmailTemplateService {

}
