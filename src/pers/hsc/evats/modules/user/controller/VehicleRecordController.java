package pers.hsc.evats.modules.user.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import pers.hsc.evats.core.common.controller.BaseCRUDController;
import pers.hsc.evats.core.query.data.Queryable;
import pers.hsc.evats.core.security.shiro.authz.annotation.RequiresPathPermission;
import pers.hsc.evats.core.utils.R;
import pers.hsc.evats.modules.user.entity.LostVehicle;
import pers.hsc.evats.modules.user.entity.Vehicle;
import pers.hsc.evats.modules.user.entity.VehicleRecord;
import pers.hsc.evats.modules.user.service.ILostVehicleService;
import pers.hsc.evats.modules.user.service.IVehicleRecordService;
import pers.hsc.evats.modules.user.service.IVehicleService;

/**
 * 车辆通过记录
 * 
 * @author hsc
 *
 *         Jun 3, 2018
 */
@Controller
@RequestMapping("${admin.url.prefix}/user/vehicle/record/")
@RequiresPathPermission("user:vehicle:record")
public class VehicleRecordController extends BaseCRUDController<VehicleRecord, String> {

	@Autowired
	IVehicleService iVehicleService;

	@Autowired
	ILostVehicleService iLostVehicleService;
	
	@Autowired
	IVehicleRecordService iVehicleRecordService;
	

	@RequestMapping(value = "list", method = RequestMethod.GET)
	public String displayList(HttpServletRequest request, HttpServletResponse response) {
		return display("list");
	}
	
	@ResponseBody
	@RequestMapping(value = "getVehicleRecordList", method = RequestMethod.POST)
	public R getVehicleRecordList(Queryable queryable, HttpServletRequest request, HttpServletResponse response) {
		R r = new R();
		List<VehicleRecord> vehicleRecordList = iVehicleRecordService.listWithNoPage(queryable);
		r.put("vehicleRecordList", vehicleRecordList);
		return r;
	}

	@RequestMapping(value = "addInit", method = RequestMethod.POST)
	public R addV(Queryable queryable, HttpServletRequest request, HttpServletResponse response) throws ParseException {
		List<Vehicle> list1 = iVehicleService.listWithNoPage(queryable);
		List<LostVehicle> list2 = iLostVehicleService.listWithNoPage(queryable);
		List<VehicleRecord> list3 = new ArrayList<VehicleRecord>();
		int[] n1 = { 2, 0, 5, 36, 38, 42, 26, 16, 51, 22, 9, 1 };
		int[] n2 = { 1, 0, 3, 42, 36, 55, 32, 9, 38, 26, 2, 0 };
		int[] n3 = { 0, 1, 8, 50, 39, 64, 19, 21, 39, 12, 5, 6 };
		int[] n4 = { 3, 0, 5, 29, 54, 38, 25, 19, 55, 31, 15, 2 };
		int[] n5 = { 1, 0, 3, 32, 45, 54, 16, 31, 48, 14, 2, 3 };
		int[] n6 = { 1, 0, 3, 46, 28, 48, 25, 25, 49, 9, 7, 1 };
		int[] n7 = { 0, 1, 4, 42, 35, 51, 41, 19, 52, 22, 12, 2 };
		int[] n8 = { 2, 0, 1, 28, 49, 36, 30, 16, 39, 21, 5, 0 };

		addData("2018-6-5", n1, list1, list2, list3);
		addData("2018-6-4", n2, list1, list2, list3);
		addData("2018-6-3", n3, list1, list2, list3);
		addData("2018-6-2", n4, list1, list2, list3);
		addData("2018-6-1", n5, list1, list2, list3);
		addData("2018-5-31", n6, list1, list2, list3);
		addData("2018-5-30", n7, list1, list2, list3);
		addData("2018-5-29", n8, list1, list2, list3);
		System.out.println(list3.size());
		iVehicleRecordService.insertBatch(list3);
		return R.ok();
	}

	public void addData(String date, int[] n, List<Vehicle> list1, List<LostVehicle> list2, List<VehicleRecord> list3)
			throws ParseException {
		Random r = new Random();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		for (int i = 0; i < n.length; i++) {
			if (i == 0) {
				if (n[i] > 0) {
					for (int j = 0; j < n[i]; j++) {
						int index = r.nextInt(list1.size());
						Vehicle vehicle = list1.get(index);
						VehicleRecord vehicleRecord = new VehicleRecord();
						String d = date + " " + "01:00:00";
						vehicleRecord.setReadTime(sdf.parse(d));
						vehicleRecord.setVehicleTagNum(vehicle.getSelfTag());
						vehicleRecord.setUserTagNum(vehicle.getUserTag());
						if (index < 10) {
							vehicleRecord
									.setDescribe("发现失窃车辆，车辆标签号：" + vehicle.getSelfTag() + "，" + vehicle.getDescribe());
						} else if (index < 35 && index > 30) {
							vehicleRecord.setDescribe(
									"车辆品牌为:" + vehicle.getBrand() + "，" + vehicle.getDescribe() + " 的车辆存在异常骑行！请注意！");
						} else {
							vehicleRecord.setDescribe("车辆标签号为" + vehicle.getSelfTag() + "的车辆在" + d + "经过小区门口");
						}
						list3.add(vehicleRecord);
					}
				}
			} else if (i == 1) {
				if (n[i] > 0) {
					for (int j = 0; j < n[i]; j++) {
						int index = r.nextInt(list1.size());
						Vehicle vehicle = list1.get(index);
						VehicleRecord vehicleRecord = new VehicleRecord();
						String d = date + " " + "03:00:00";
						vehicleRecord.setReadTime(sdf.parse(d));
						vehicleRecord.setVehicleTagNum(vehicle.getSelfTag());
						vehicleRecord.setUserTagNum(vehicle.getUserTag());
						if (index < 10) {
							vehicleRecord
									.setDescribe("发现失窃车辆，车辆标签号：" + vehicle.getSelfTag() + "，" + vehicle.getDescribe());
						} else if (index < 35 && index > 30) {
							vehicleRecord.setDescribe(
									"车辆品牌为:" + vehicle.getBrand() + "，" + vehicle.getDescribe() + " 的车辆存在异常骑行！请注意！");
						} else {
							vehicleRecord.setDescribe("车辆标签号为" + vehicle.getSelfTag() + "的车辆在" + d + "经过小区门口");
						}
						list3.add(vehicleRecord);
					}
				}
			} else if (i == 2) {
				if (n[i] > 0) {
					for (int j = 0; j < n[i]; j++) {
						int index = r.nextInt(list1.size());
						Vehicle vehicle = list1.get(index);
						VehicleRecord vehicleRecord = new VehicleRecord();
						String d = date + " " + "05:00:00";
						vehicleRecord.setReadTime(sdf.parse(d));
						vehicleRecord.setVehicleTagNum(vehicle.getSelfTag());
						vehicleRecord.setUserTagNum(vehicle.getUserTag());
						if (index < 10) {
							vehicleRecord
									.setDescribe("发现失窃车辆，车辆标签号：" + vehicle.getSelfTag() + "，" + vehicle.getDescribe());
						} else if (index < 35 && index > 30) {
							vehicleRecord.setDescribe(
									"车辆品牌为:" + vehicle.getBrand() + "，" + vehicle.getDescribe() + " 的车辆存在异常骑行！请注意！");
						} else {
							vehicleRecord.setDescribe("车辆标签号为" + vehicle.getSelfTag() + "的车辆在" + d + "经过小区门口");
						}
						list3.add(vehicleRecord);
					}
				}
			} else if (i == 3) {
				if (n[i] > 0) {
					for (int j = 0; j < n[i]; j++) {
						int index = r.nextInt(list1.size());
						Vehicle vehicle = list1.get(index);
						VehicleRecord vehicleRecord = new VehicleRecord();
						String d = date + " " + "07:00:00";
						vehicleRecord.setReadTime(sdf.parse(d));
						vehicleRecord.setVehicleTagNum(vehicle.getSelfTag());
						vehicleRecord.setUserTagNum(vehicle.getUserTag());
						if (index < 10) {
							vehicleRecord
									.setDescribe("发现失窃车辆，车辆标签号：" + vehicle.getSelfTag() + "，" + vehicle.getDescribe());
						} else if (index < 35 && index > 30) {
							vehicleRecord.setDescribe(
									"车辆品牌为:" + vehicle.getBrand() + "，" + vehicle.getDescribe() + " 的车辆存在异常骑行！请注意！");
						} else {
							vehicleRecord.setDescribe("车辆标签号为" + vehicle.getSelfTag() + "的车辆在" + d + "经过小区门口");
						}
						list3.add(vehicleRecord);
					}
				}
			} else if (i == 4) {
				if (n[i] > 0) {
					for (int j = 0; j < n[i]; j++) {
						int index = r.nextInt(list1.size());
						Vehicle vehicle = list1.get(index);
						VehicleRecord vehicleRecord = new VehicleRecord();
						String d = date + " " + "09:00:00";
						vehicleRecord.setReadTime(sdf.parse(d));
						vehicleRecord.setVehicleTagNum(vehicle.getSelfTag());
						vehicleRecord.setUserTagNum(vehicle.getUserTag());
						if (index < 10) {
							vehicleRecord
									.setDescribe("发现失窃车辆，车辆标签号：" + vehicle.getSelfTag() + "，" + vehicle.getDescribe());
						} else if (index < 35 && index > 30) {
							vehicleRecord.setDescribe(
									"车辆品牌为:" + vehicle.getBrand() + "，" + vehicle.getDescribe() + " 的车辆存在异常骑行！请注意！");
						} else {
							vehicleRecord.setDescribe("车辆标签号为" + vehicle.getSelfTag() + "的车辆在" + d + "经过小区门口");
						}
						list3.add(vehicleRecord);
					}
				}
			} else if (i == 5) {
				if (n[i] > 0) {
					for (int j = 0; j < n[i]; j++) {
						int index = r.nextInt(list1.size());
						Vehicle vehicle = list1.get(index);
						VehicleRecord vehicleRecord = new VehicleRecord();
						String d = date + " " + "11:00:00";
						vehicleRecord.setReadTime(sdf.parse(d));
						vehicleRecord.setVehicleTagNum(vehicle.getSelfTag());
						vehicleRecord.setUserTagNum(vehicle.getUserTag());
						if (index < 10) {
							vehicleRecord
									.setDescribe("发现失窃车辆，车辆标签号：" + vehicle.getSelfTag() + "，" + vehicle.getDescribe());
						} else if (index < 35 && index > 30) {
							vehicleRecord.setDescribe(
									"车辆品牌为:" + vehicle.getBrand() + "，" + vehicle.getDescribe() + " 的车辆存在异常骑行！请注意！");
						} else {
							vehicleRecord.setDescribe("车辆标签号为" + vehicle.getSelfTag() + "的车辆在" + d + "经过小区门口");
						}
						list3.add(vehicleRecord);
					}
				}
			} else if (i == 6) {
				if (n[i] > 0) {
					for (int j = 0; j < n[i]; j++) {
						int index = r.nextInt(list1.size());
						Vehicle vehicle = list1.get(index);
						VehicleRecord vehicleRecord = new VehicleRecord();
						String d = date + " " + "13:00:00";
						vehicleRecord.setReadTime(sdf.parse(d));
						vehicleRecord.setVehicleTagNum(vehicle.getSelfTag());
						vehicleRecord.setUserTagNum(vehicle.getUserTag());
						if (index < 10) {
							vehicleRecord
									.setDescribe("发现失窃车辆，车辆标签号：" + vehicle.getSelfTag() + "，" + vehicle.getDescribe());
						} else if (index < 35 && index > 30) {
							vehicleRecord.setDescribe(
									"车辆品牌为:" + vehicle.getBrand() + "，" + vehicle.getDescribe() + " 的车辆存在异常骑行！请注意！");
						} else {
							vehicleRecord.setDescribe("车辆标签号为" + vehicle.getSelfTag() + "的车辆在" + d + "经过小区门口");
						}
						list3.add(vehicleRecord);
					}
				}
			} else if (i == 7) {
				if (n[i] > 0) {
					for (int j = 0; j < n[i]; j++) {
						int index = r.nextInt(list1.size());
						Vehicle vehicle = list1.get(index);
						VehicleRecord vehicleRecord = new VehicleRecord();
						String d = date + " " + "15:00:00";
						vehicleRecord.setReadTime(sdf.parse(d));
						vehicleRecord.setVehicleTagNum(vehicle.getSelfTag());
						vehicleRecord.setUserTagNum(vehicle.getUserTag());
						if (index < 10) {
							vehicleRecord
									.setDescribe("发现失窃车辆，车辆标签号：" + vehicle.getSelfTag() + "，" + vehicle.getDescribe());
						} else if (index < 35 && index > 30) {
							vehicleRecord.setDescribe(
									"车辆品牌为:" + vehicle.getBrand() + "，" + vehicle.getDescribe() + " 的车辆存在异常骑行！请注意！");
						} else {
							vehicleRecord.setDescribe("车辆标签号为" + vehicle.getSelfTag() + "的车辆在" + d + "经过小区门口");
						}
						list3.add(vehicleRecord);
					}
				}
			} else if (i == 8) {
				if (n[i] > 0) {
					for (int j = 0; j < n[i]; j++) {
						int index = r.nextInt(list1.size());
						Vehicle vehicle = list1.get(index);
						VehicleRecord vehicleRecord = new VehicleRecord();
						String d = date + " " + "17:00:00";
						vehicleRecord.setReadTime(sdf.parse(d));
						vehicleRecord.setVehicleTagNum(vehicle.getSelfTag());
						vehicleRecord.setUserTagNum(vehicle.getUserTag());
						if (index < 10) {
							vehicleRecord
									.setDescribe("发现失窃车辆，车辆标签号：" + vehicle.getSelfTag() + "，" + vehicle.getDescribe());
						} else if (index < 35 && index > 30) {
							vehicleRecord.setDescribe(
									"车辆品牌为:" + vehicle.getBrand() + "，" + vehicle.getDescribe() + " 的车辆存在异常骑行！请注意！");
						} else {
							vehicleRecord.setDescribe("车辆标签号为" + vehicle.getSelfTag() + "的车辆在" + d + "经过小区门口");
						}
						list3.add(vehicleRecord);
					}
				}
			} else if (i == 9) {
				if (n[i] > 0) {
					for (int j = 0; j < n[i]; j++) {
						int index = r.nextInt(list1.size());
						Vehicle vehicle = list1.get(index);
						VehicleRecord vehicleRecord = new VehicleRecord();
						String d = date + " " + "19:00:00";
						vehicleRecord.setReadTime(sdf.parse(d));
						vehicleRecord.setVehicleTagNum(vehicle.getSelfTag());
						vehicleRecord.setUserTagNum(vehicle.getUserTag());
						if (index < 10) {
							vehicleRecord
									.setDescribe("发现失窃车辆，车辆标签号：" + vehicle.getSelfTag() + "，" + vehicle.getDescribe());
						} else if (index < 35 && index > 30) {
							vehicleRecord.setDescribe(
									"车辆品牌为:" + vehicle.getBrand() + "，" + vehicle.getDescribe() + " 的车辆存在异常骑行！请注意！");
						} else {
							vehicleRecord.setDescribe("车辆标签号为" + vehicle.getSelfTag() + "的车辆在" + d + "经过小区门口");
						}
						list3.add(vehicleRecord);
					}
				}
			} else if (i == 10) {
				if (n[i] > 0) {
					for (int j = 0; j < n[i]; j++) {
						int index = r.nextInt(list1.size());
						Vehicle vehicle = list1.get(index);
						VehicleRecord vehicleRecord = new VehicleRecord();
						String d = date + " " + "21:00:00";
						vehicleRecord.setReadTime(sdf.parse(d));
						vehicleRecord.setVehicleTagNum(vehicle.getSelfTag());
						vehicleRecord.setUserTagNum(vehicle.getUserTag());
						if (index < 10) {
							vehicleRecord
									.setDescribe("发现失窃车辆，车辆标签号：" + vehicle.getSelfTag() + "，" + vehicle.getDescribe());
						} else if (index < 35 && index > 30) {
							vehicleRecord.setDescribe(
									"车辆品牌为:" + vehicle.getBrand() + "，" + vehicle.getDescribe() + " 的车辆存在异常骑行！请注意！");
						} else {
							vehicleRecord.setDescribe("车辆标签号为" + vehicle.getSelfTag() + "的车辆在" + d + "经过小区门口");
						}
						list3.add(vehicleRecord);
					}
				}
			} else if (i == 11) {
				if (n[i] > 0) {
					for (int j = 0; j < n[i]; j++) {
						int index = r.nextInt(list1.size());
						Vehicle vehicle = list1.get(index);
						VehicleRecord vehicleRecord = new VehicleRecord();
						String d = date + " " + "23:00:00";
						vehicleRecord.setReadTime(sdf.parse(d));
						vehicleRecord.setVehicleTagNum(vehicle.getSelfTag());
						vehicleRecord.setUserTagNum(vehicle.getUserTag());
						if (index < 10) {
							vehicleRecord
									.setDescribe("发现失窃车辆，车辆标签号：" + vehicle.getSelfTag() + "，" + vehicle.getDescribe());
						} else if (index < 35 && index > 30) {
							vehicleRecord.setDescribe(
									"车辆品牌为:" + vehicle.getBrand() + "，" + vehicle.getDescribe() + " 的车辆存在异常骑行！请注意！");
						} else {
							vehicleRecord.setDescribe("车辆标签号为" + vehicle.getSelfTag() + "的车辆在" + d + "经过小区门口");
						}
						list3.add(vehicleRecord);
					}
				}
			}
		}
	}

	public static void main(String[] args) {
		int[] n1 = { 2, 0, 5, 36, 38, 42, 26, 16, 51, 22, 9, 1 };
		String d = "2018-6-3 02:01:11";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		try {
			System.out.println(sdf.parse(d));
			System.out.println(new Date());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int i = 0; i < n1.length; i++) {
			System.out.print(n1[i]);
		}
	}

}
