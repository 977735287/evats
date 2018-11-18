package pers.hsc.evats.modules.sys.service;


import java.util.List;

import pers.hsc.evats.core.common.service.ICommonService;
import pers.hsc.evats.modules.sys.entity.Dict;

/**
 * @author hsc
 *
 * Mar 31, 2018
 */
public interface IDictService extends ICommonService<Dict> {
    public List<Dict> selectDictList();
}
