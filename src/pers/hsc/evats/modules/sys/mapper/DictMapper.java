package pers.hsc.evats.modules.sys.mapper;

import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import pers.hsc.evats.modules.sys.entity.Dict;

public interface DictMapper extends BaseMapper<Dict> {
	List<Dict> selectDictList();
}