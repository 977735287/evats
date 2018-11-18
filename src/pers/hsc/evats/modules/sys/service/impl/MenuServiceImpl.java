package pers.hsc.evats.modules.sys.service.impl;

import pers.hsc.evats.core.common.service.impl.TreeCommonServiceImpl;
import pers.hsc.evats.modules.sys.entity.Menu;
import pers.hsc.evats.modules.sys.mapper.MenuMapper;
import pers.hsc.evats.modules.sys.service.IMenuService;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service("menuService")
public class MenuServiceImpl extends TreeCommonServiceImpl<MenuMapper, Menu, String> implements IMenuService {

	@Override
	public List<Menu> findMenuByUserId(String userId) {
		return baseMapper.findMenuByUserId(userId);
	}

	@Override
	public List<Menu> findMenuByRoleId(String roleId) {
		return baseMapper.findMenuByRoleId(roleId);
	}

}
