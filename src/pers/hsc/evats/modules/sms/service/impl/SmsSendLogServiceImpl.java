package pers.hsc.evats.modules.sms.service.impl;

import pers.hsc.evats.core.common.service.impl.CommonServiceImpl;
import pers.hsc.evats.modules.sms.entity.SmsSendLog;
import pers.hsc.evats.modules.sms.mapper.SmsSendLogMapper;
import pers.hsc.evats.modules.sms.service.ISmsSendLogService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**   
 * 短信发送日志
 * 
 * @author hsc
 *
 * Mar 29, 2018
 */
@Transactional
@Service("smsSendLogService")
public class SmsSendLogServiceImpl  extends CommonServiceImpl<SmsSendLogMapper,SmsSendLog> implements  ISmsSendLogService {

}
