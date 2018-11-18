package pers.hsc.evats.modules.user.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import pers.hsc.evats.core.common.entity.DataEntity;

/**
 * 过往车辆记录
 * 
 * @author hsc
 *
 *         Jun 3, 2018
 */
@TableName("user_vehicle_record")
@SuppressWarnings("serial")
public class VehicleRecord extends DataEntity<String> {

	/** id */
	@TableId(value = "id", type = IdType.UUID)
	private String id;
	// 车辆标签号
	private String vehicleTagNum;
	// 用户标签号
	private String userTagNum;
	// 描述
	private String describe;
	// 识别的时间
	private Date readTime;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getVehicleTagNum() {
		return vehicleTagNum;
	}
	public void setVehicleTagNum(String vehicleTagNum) {
		this.vehicleTagNum = vehicleTagNum;
	}
	public String getUserTagNum() {
		return userTagNum;
	}
	public void setUserTagNum(String userTagNum) {
		this.userTagNum = userTagNum;
	}
	public String getDescribe() {
		return describe;
	}
	public void setDescribe(String describe) {
		this.describe = describe;
	}
	public Date getReadTime() {
		return readTime;
	}
	public void setReadTime(Date readTime) {
		this.readTime = readTime;
	}
	
}
