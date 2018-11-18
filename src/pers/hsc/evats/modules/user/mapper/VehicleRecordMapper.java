package pers.hsc.evats.modules.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;

import pers.hsc.evats.modules.user.entity.VehicleRecord;

/**
 * @author hsc
 *
 * Jun 3, 2018
 */
public interface VehicleRecordMapper extends BaseMapper<VehicleRecord> {
	List<VehicleRecord> selectVehicleRecordPage(Page<VehicleRecord> page, @Param("ew") Wrapper<VehicleRecord> wrapper);
}
