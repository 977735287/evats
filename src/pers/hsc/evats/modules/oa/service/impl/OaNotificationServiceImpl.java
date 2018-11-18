package pers.hsc.evats.modules.oa.service.impl;

import pers.hsc.evats.core.common.service.impl.CommonServiceImpl;
import pers.hsc.evats.modules.oa.entity.OaNotification;
import pers.hsc.evats.modules.oa.mapper.OaNotificationMapper;
import pers.hsc.evats.modules.oa.service.IOaNotificationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**   
 * 通知公告
 * 
 * @author hsc
 *
 * Mar 31, 2018
 */
@Transactional
@Service("oaNotificationService")
public class OaNotificationServiceImpl  extends CommonServiceImpl<OaNotificationMapper,OaNotification> implements  IOaNotificationService {

}
