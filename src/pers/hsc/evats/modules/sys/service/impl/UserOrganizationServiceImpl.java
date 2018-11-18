package pers.hsc.evats.modules.sys.service.impl;

import pers.hsc.evats.core.common.service.impl.CommonServiceImpl;
import pers.hsc.evats.modules.sys.entity.UserOrganization;
import pers.hsc.evats.modules.sys.mapper.UserOrganizationMapper;
import pers.hsc.evats.modules.sys.service.IUserOrganizationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Transactional
@Service("userOrganizationService")
public class UserOrganizationServiceImpl extends CommonServiceImpl<UserOrganizationMapper, UserOrganization>
		implements IUserOrganizationService {

}
