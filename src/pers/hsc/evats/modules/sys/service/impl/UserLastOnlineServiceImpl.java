package pers.hsc.evats.modules.sys.service.impl;

import pers.hsc.evats.core.common.service.impl.CommonServiceImpl;
import pers.hsc.evats.modules.sys.entity.UserLastOnline;
import pers.hsc.evats.modules.sys.mapper.UserLastOnlineMapper;
import pers.hsc.evats.modules.sys.service.IUserLastOnlineService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**   
 * 最后在线情况
 * 
 * @author hsc
 *
 * Mar 31, 2018
 */
@Transactional
@Service("userLastOnlineService")
public class UserLastOnlineServiceImpl  extends CommonServiceImpl<UserLastOnlineMapper,UserLastOnline> implements  IUserLastOnlineService {

}
