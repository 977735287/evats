package pers.hsc.evats.modules.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;

import pers.hsc.evats.modules.user.entity.Vehicle;

/**
 * @author hsc
 *
 *         Apr 10, 2018
 */
public interface VehicleMapper extends BaseMapper<Vehicle> {
	List<Vehicle> selectVehiclePage(Page<Vehicle> page, @Param("ew") Wrapper<Vehicle> wrapper);
}
