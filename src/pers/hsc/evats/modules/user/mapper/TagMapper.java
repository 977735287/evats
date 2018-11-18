package pers.hsc.evats.modules.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;

import pers.hsc.evats.modules.user.entity.RfidTag;

/**
 * @author hsc
 *
 *         Apr 9, 2018
 */
public interface TagMapper extends BaseMapper<RfidTag> {

	List<RfidTag> selectTagPage(Page<RfidTag> page, @Param("ew") Wrapper<RfidTag> wrapper);
}
