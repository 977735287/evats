package pers.hsc.evats.modules.user.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;

import pers.hsc.evats.core.common.service.impl.CommonServiceImpl;
import pers.hsc.evats.modules.user.entity.LostVehicle;
import pers.hsc.evats.modules.user.mapper.LostVehicleMapper;
import pers.hsc.evats.modules.user.service.ILostVehicleService;

/**
 * @author hsc
 *
 * Apr 11, 2018
 */
@Transactional
@Service("LostVehicleService")
public class LostVehicleServiceImpl extends CommonServiceImpl<LostVehicleMapper,LostVehicle> implements ILostVehicleService  {
	@Override
	public Page<LostVehicle> selectPage(Page<LostVehicle> page, Wrapper<LostVehicle> wrapper) {
		wrapper.eq("1", "1");
		page.setRecords(baseMapper.selectLostVehiclePage(page, wrapper));
		return page;
	}

}
