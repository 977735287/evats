package pers.hsc.evats.modules.user.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import pers.hsc.evats.core.common.entity.DataEntity;
import pers.hsc.evats.modules.sys.entity.User;

/**
 * @author hsc
 *
 *         Apr 10, 2018
 */
@TableName("user_vehicle_lost")
@SuppressWarnings("serial")
public class LostVehicle extends DataEntity<String> {

	/** id */
	@TableId(value = "id", type = IdType.UUID)
	private String id;
	// 失窃车辆品牌
	private String lostBrand;
	// 失窃车辆标签号
	private String lostTag;
	// 失窃车辆用户标签
	private String lostUserTag;
	// 车辆状态
	private String lostStatus;
	// 失窃车辆描述
	private String lostDescribe;
	// 失窃日期
	private Date lostTime;
	// 找回日期
	private Date backTime;

	// 车辆所属用户
	private User LostUser;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLostBrand() {
		return lostBrand;
	}

	public void setLostBrand(String lostBrand) {
		this.lostBrand = lostBrand;
	}

	public String getLostTag() {
		return lostTag;
	}

	public void setLostTag(String lostTag) {
		this.lostTag = lostTag;
	}

	public String getLostUserTag() {
		return lostUserTag;
	}

	public void setLostUserTag(String lostUserTag) {
		this.lostUserTag = lostUserTag;
	}

	public String getLostStatus() {
		return lostStatus;
	}

	public void setLostStatus(String lostStatus) {
		this.lostStatus = lostStatus;
	}

	public String getLostDescribe() {
		return lostDescribe;
	}

	public void setLostDescribe(String lostDescribe) {
		this.lostDescribe = lostDescribe;
	}

	public Date getLostTime() {
		return lostTime;
	}

	public void setLostTime(Date lostTime) {
		this.lostTime = lostTime;
	}

	public Date getBackTime() {
		return backTime;
	}

	public void setBackTime(Date backTime) {
		this.backTime = backTime;
	}

	public User getLostUser() {
		return LostUser;
	}

	public void setLostUser(User lostUser) {
		LostUser = lostUser;
	}

}
