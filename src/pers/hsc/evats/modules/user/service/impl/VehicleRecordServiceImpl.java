package pers.hsc.evats.modules.user.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;

import pers.hsc.evats.core.common.service.impl.CommonServiceImpl;
import pers.hsc.evats.modules.user.entity.VehicleRecord;
import pers.hsc.evats.modules.user.mapper.VehicleRecordMapper;
import pers.hsc.evats.modules.user.service.IVehicleRecordService;

/**
 * @author hsc
 *
 * Jun 3, 2018
 */
@Transactional
@Service("VehicleRecordService")
public class VehicleRecordServiceImpl extends CommonServiceImpl<VehicleRecordMapper,VehicleRecord> implements IVehicleRecordService  {

	@Override
	public Page<VehicleRecord> selectPage(Page<VehicleRecord> page, Wrapper<VehicleRecord> wrapper) {
		wrapper.eq("1", "1");
		page.setRecords(baseMapper.selectVehicleRecordPage(page, wrapper));
		return page;
	}
}
