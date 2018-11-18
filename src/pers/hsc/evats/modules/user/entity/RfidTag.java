package pers.hsc.evats.modules.user.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import pers.hsc.evats.core.common.entity.DataEntity;

/**
 * @author hsc
 *
 *         Apr 8, 2018
 */
@TableName("user_tag")
@SuppressWarnings("serial")
public class RfidTag extends DataEntity<String> {

	/** id */
	@TableId(value = "id", type = IdType.UUID)
	private String id;
	// 标签号
	private String tagNum;
	// 配对标签号
	private String tagBratherNum;
	// 标签描述
	private String tagDetail;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTagNum() {
		return tagNum;
	}
	public void setTagNum(String tagNum) {
		this.tagNum = tagNum;
	}
	public String getTagBratherNum() {
		return tagBratherNum;
	}
	public void setTagBratherNum(String tagBratherNum) {
		this.tagBratherNum = tagBratherNum;
	}
	public String getTagDetail() {
		return tagDetail;
	}
	public void setTagDetail(String tagDetail) {
		this.tagDetail = tagDetail;
	}
	
}
