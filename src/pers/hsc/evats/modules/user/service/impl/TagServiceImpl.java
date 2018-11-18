package pers.hsc.evats.modules.user.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;

import pers.hsc.evats.core.common.service.impl.CommonServiceImpl;
import pers.hsc.evats.core.query.wrapper.EntityWrapper;
import pers.hsc.evats.core.utils.StringUtils;
import pers.hsc.evats.modules.user.entity.RfidTag;
import pers.hsc.evats.modules.user.mapper.TagMapper;
import pers.hsc.evats.modules.user.service.ITagService;

/**
 * @author hsc
 *
 *         Apr 9, 2018
 */
@Transactional
@Service("tagService")
public class TagServiceImpl extends CommonServiceImpl<TagMapper,RfidTag> implements ITagService {
	@Override
	public Page<RfidTag> selectPage(Page<RfidTag> page, Wrapper<RfidTag> wrapper) {
		wrapper.eq("1", "1");
		page.setRecords(baseMapper.selectTagPage(page, wrapper));
		return page;
	}
	
	@Override
	public RfidTag findByTagNum(String tagNum){
		if (StringUtils.isEmpty(tagNum)) {
			return null;
		}
		return selectOne(new EntityWrapper<RfidTag>(RfidTag.class).eq("tagNum", tagNum));
	}
	
	@Override
	public boolean insert(RfidTag rfidTag) {
		return super.insert(rfidTag);
	}
}
