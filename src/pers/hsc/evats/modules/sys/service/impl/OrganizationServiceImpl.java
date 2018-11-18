package pers.hsc.evats.modules.sys.service.impl;

import pers.hsc.evats.core.common.service.impl.TreeCommonServiceImpl;
import pers.hsc.evats.modules.sys.entity.Organization;
import pers.hsc.evats.modules.sys.mapper.OrganizationMapper;
import pers.hsc.evats.modules.sys.service.IOrganizationService;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service("organizationService")
public class OrganizationServiceImpl extends TreeCommonServiceImpl<OrganizationMapper, Organization, String>
		implements IOrganizationService {

	@Override
	public List<Organization> findListByUserId(String userid) {
		return baseMapper.findListByUserId(userid);
	}

}
