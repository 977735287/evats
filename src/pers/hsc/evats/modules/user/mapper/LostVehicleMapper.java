package pers.hsc.evats.modules.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;

import pers.hsc.evats.modules.user.entity.LostVehicle;

/**
 * @author hsc
 *
 * Apr 11, 2018
 */
public interface LostVehicleMapper extends BaseMapper<LostVehicle> {
	List<LostVehicle> selectLostVehiclePage(Page<LostVehicle> page, @Param("ew") Wrapper<LostVehicle> wrapper);
}
