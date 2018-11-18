package pers.hsc.evats.modules.sys.service.impl;

import pers.hsc.evats.core.common.service.impl.CommonServiceImpl;
import pers.hsc.evats.modules.sys.entity.RoleMenu;
import pers.hsc.evats.modules.sys.mapper.RoleMenuMapper;
import pers.hsc.evats.modules.sys.service.IRoleMenuService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service("roleMenuService")
public class RoleMenuServiceImpl extends CommonServiceImpl<RoleMenuMapper,RoleMenu> implements IRoleMenuService {

}
