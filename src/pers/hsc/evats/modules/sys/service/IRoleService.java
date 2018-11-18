package pers.hsc.evats.modules.sys.service;

import java.util.List;

import pers.hsc.evats.core.common.service.ICommonService;
import pers.hsc.evats.modules.sys.entity.Role;

/**
 * @author hsc
 *
 * Mar 29, 2018
 */
public interface IRoleService extends ICommonService<Role> {
	/**
	 * 通过用户ID查找角色
	 */
	public List<Role> findListByUserId(String userid);
}
