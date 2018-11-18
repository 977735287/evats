package pers.hsc.evats.modules.user.service;

import pers.hsc.evats.core.common.service.ICommonService;
import pers.hsc.evats.modules.user.entity.Vehicle;

/**
 * @author hsc
 *
 *         Apr 10, 2018
 */
public interface IVehicleService extends ICommonService<Vehicle> {

	/**
	 * 根据车辆标签号查找标签
	 * 
	 * @param selfTag
	 * @return
	 */
	public Vehicle findBySelfTag(String selfTag);
	
	/**
	 * 根据用户标签号查找标签
	 * 
	 * @param tagNum
	 * @return
	 */
	public Vehicle findByUserTag(String userTag);
}
