package pers.hsc.evats.modules.user.service;

import pers.hsc.evats.core.common.service.ICommonService;
import pers.hsc.evats.modules.user.entity.RfidTag;

/**
 * @author hsc
 *
 *         Apr 9, 2018
 */
public interface ITagService extends ICommonService<RfidTag> {
	
	/**
	 * 根据标签号查找标签
	 * 
	 * @param tagNum
	 * @return
	 */
	public RfidTag findByTagNum(String tagNum);
}
