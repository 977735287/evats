package pers.hsc.evats.modules.email.service.impl;

import pers.hsc.evats.core.common.service.impl.CommonServiceImpl;
import pers.hsc.evats.modules.email.entity.EmailSendLog;
import pers.hsc.evats.modules.email.mapper.EmailSendLogMapper;
import pers.hsc.evats.modules.email.service.IEmailSendLogService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**   
 * 邮件发送日志
 * 
 * @author hsc
 *
 * Mar 29, 2018
 */
@Transactional
@Service("emailSendLogService")
public class EmailSendLogServiceImpl  extends CommonServiceImpl<EmailSendLogMapper, EmailSendLog> implements  IEmailSendLogService {

	 
	 
}
