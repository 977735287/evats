package pers.hsc.evats.modules.sys.service;

import java.util.List;

import pers.hsc.evats.core.common.service.ITreeCommonService;
import pers.hsc.evats.modules.sys.entity.Menu;

/**
 * @author hsc
 *
 * Mar 29, 2018
 */
public interface IMenuService extends ITreeCommonService<Menu, String> {

	/**
	 * 通过用户ID查找菜单
	 * 
	 * @return
	 */
	List<Menu> findMenuByUserId(String userId);

	/**
	 * 通过角色查找菜单
	 * 
	 * @return
	 */
	List<Menu> findMenuByRoleId(String roleId);
}
