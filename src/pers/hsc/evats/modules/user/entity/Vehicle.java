package pers.hsc.evats.modules.user.entity;

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
@TableName("user_vehicle")
@SuppressWarnings("serial")
public class Vehicle extends DataEntity<String> {

	/** id */
	@TableId(value = "id", type = IdType.UUID)
	private String id;
	//车辆品牌
	private String brand;
	//车辆对应的标签号
	private String selfTag;
	//车辆对应的用户标签号
	private String userTag;
	//车辆描述，比如车身颜色等标志性的东西
	private String describe;
	//车主姓名
	private String userName;
	//车主手机号码
	private String userPhone;
	//车主邮箱
	private String userEmail;
	
	//车辆所属用户
	private User user;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getSelfTag() {
		return selfTag;
	}

	public void setSelfTag(String selfTag) {
		this.selfTag = selfTag;
	}

	public String getUserTag() {
		return userTag;
	}

	public void setUserTag(String userTag) {
		this.userTag = userTag;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}
