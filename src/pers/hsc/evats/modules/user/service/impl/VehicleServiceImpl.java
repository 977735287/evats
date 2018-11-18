package pers.hsc.evats.modules.user.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;

import pers.hsc.evats.core.common.service.impl.CommonServiceImpl;
import pers.hsc.evats.core.query.wrapper.EntityWrapper;
import pers.hsc.evats.core.utils.StringUtils;
import pers.hsc.evats.modules.user.entity.Vehicle;
import pers.hsc.evats.modules.user.mapper.VehicleMapper;
import pers.hsc.evats.modules.user.service.IVehicleService;

/**
 * @author hsc
 *
 *         Apr 10, 2018
 */
@Transactional
@Service("vehicleService")
public class VehicleServiceImpl extends CommonServiceImpl<VehicleMapper,Vehicle> implements IVehicleService  {
	@Override
	public Page<Vehicle> selectPage(Page<Vehicle> page, Wrapper<Vehicle> wrapper) {
		wrapper.eq("1", "1");
		page.setRecords(baseMapper.selectVehiclePage(page, wrapper));
		return page;
	}
	
	@Override
	public Vehicle findBySelfTag(String selfTag){
		if (StringUtils.isEmpty(selfTag)) {
			return null;
		}
		return selectOne(new EntityWrapper<Vehicle>(Vehicle.class).eq("selfTag", selfTag));
	}
	
	@Override
	public Vehicle findByUserTag(String userTag){
		if (StringUtils.isEmpty(userTag)) {
			return null;
		}
		return selectOne(new EntityWrapper<Vehicle>(Vehicle.class).eq("userTag", userTag));
	}
}
