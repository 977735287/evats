package pers.hsc.evats.modules.sys.service;

import java.util.List;

import pers.hsc.evats.core.common.service.ITreeCommonService;
import pers.hsc.evats.modules.sys.entity.Organization;

/**
 * @author hsc
 *
 * Mar 31, 2018
 */
public interface IOrganizationService extends ITreeCommonService<Organization, String> {
	/**
	 * 通过用户ID查找角色
	 */
	public List<Organization> findListByUserId(String userid);
}
