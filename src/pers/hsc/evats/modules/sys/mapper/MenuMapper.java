package pers.hsc.evats.modules.sys.mapper;

import java.util.List;
import pers.hsc.evats.core.common.mapper.BaseTreeMapper;
import pers.hsc.evats.modules.sys.entity.Menu;

public interface MenuMapper extends BaseTreeMapper<Menu> {

	/**
	 * 
	 * @title: findMenuByUserId
	 * @description: 通过用户查找菜单
	 * @param userId
	 * @return
	 * @return: List<Menu>
	 */
	List<Menu> findMenuByUserId(String userId);
	
	/**
	 * 
	 * @title: findMenuByRoleId
	 * @description: 通过角色查找菜单
	 * @param userId
	 * @return
	 * @return: List<Menu>
	 */
	List<Menu> findMenuByRoleId(String roleId);
}