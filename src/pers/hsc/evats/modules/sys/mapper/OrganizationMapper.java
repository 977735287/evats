package pers.hsc.evats.modules.sys.mapper;

import java.util.List;

import pers.hsc.evats.core.common.mapper.BaseTreeMapper;
import pers.hsc.evats.modules.sys.entity.Organization;

public interface OrganizationMapper extends BaseTreeMapper<Organization> {

	/**
	 * 通过用户查找组织机构
	 * 
	 * @author hsc
	 *
	 *         Mar 31, 2018
	 * 
	 * @return: List<Organization>
	 */
	List<Organization> findListByUserId(String userId);
}