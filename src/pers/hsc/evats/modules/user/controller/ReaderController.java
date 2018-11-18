package pers.hsc.evats.modules.user.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import pers.hsc.evats.core.common.controller.BaseController;
import pers.hsc.evats.core.query.data.Queryable;
import pers.hsc.evats.core.security.shiro.authz.annotation.RequiresPathPermission;
import pers.hsc.evats.core.utils.DateUtils;
import pers.hsc.evats.core.utils.R;
import pers.hsc.evats.core.utils.SpeakUtil;
import pers.hsc.evats.core.utils.rfid.RFIDReaderUtil;
import pers.hsc.evats.modules.email.service.IEmailSendService;
import pers.hsc.evats.modules.sms.service.ISmsSendService;
import pers.hsc.evats.modules.user.entity.LostVehicle;
import pers.hsc.evats.modules.user.entity.RfidTag;
import pers.hsc.evats.modules.user.entity.Vehicle;
import pers.hsc.evats.modules.user.entity.VehicleRecord;
import pers.hsc.evats.modules.user.service.ILostVehicleService;
import pers.hsc.evats.modules.user.service.ITagService;
import pers.hsc.evats.modules.user.service.IVehicleRecordService;
import pers.hsc.evats.modules.user.service.IVehicleService;

/**
 * 读写器控制
 * 
 * @author hsc
 *
 *         Apr 19, 2018
 */
@Controller
@RequestMapping("${admin.url.prefix}/user/reader/")
@RequiresPathPermission("user:reader")
public class ReaderController extends BaseController {

	@Autowired
	ILostVehicleService iLostVehicleService;

	@Autowired
	ITagService iTagService;

	@Autowired
	IVehicleService iVehicleService;

	@Autowired
	IVehicleRecordService iVehicleRecordService;

	@Autowired
	private ISmsSendService smsSendService;

	@Autowired
	private IEmailSendService emailSendService;

	private Thread t1 = new Thread(); // 处理读取到的标签
	private Thread t2 = new Thread(); // 处理已识别标签的有效时间

	@RequestMapping(value = "/reader", method = RequestMethod.GET)
	public String reader(HttpServletRequest request, HttpServletResponse response) {
		return display("reader");
	}

	@ResponseBody
	@RequestMapping(value = "status", method = RequestMethod.POST)
	public R status(HttpServletRequest request, HttpServletResponse response) {
		R r = new R();
		r.put("connectStatus", RFIDReaderUtil.isConnect());
		if (RFIDReaderUtil.isConnect()) {
			r.put("readerVersion", RFIDReaderUtil.getVersion());
		}
		r.put("startStatus", RFIDReaderUtil.isStart());
		return r;
	}

	@ResponseBody
	@RequestMapping(value = "connect", method = RequestMethod.POST)
	public R connect(HttpServletRequest request, HttpServletResponse response) {
		R r = new R();
		RFIDReaderUtil.connect();
		r.put("readerVersion", RFIDReaderUtil.getVersion());
		return r;
	}

	@ResponseBody
	@RequestMapping(value = "disconnect", method = RequestMethod.POST)
	public R disconnect(HttpServletRequest request, HttpServletResponse response) {
		R r = new R();
		RFIDReaderUtil.disConnect();
		return r;
	}

	@SuppressWarnings("rawtypes")
	@ResponseBody
	@RequestMapping(value = "startup", method = RequestMethod.POST)
	public R startup(Queryable queryable, HttpServletRequest request, HttpServletResponse response) {
		setHint(request.getSession().getServletContext().getRealPath("/static/common/music/4031.wav"));
		R r = new R();
		RFIDReaderUtil.start(); // 启动读写器
		RFIDReaderUtil.setDEFAULT_CONTROLLER(true); // 控制线程循环
		List<LostVehicle> list = iLostVehicleService.listWithNoPage(queryable);
		List<LostVehicle> lostVehicleList = new ArrayList<LostVehicle>();
		for (LostVehicle lostVehicle : list) {
			if (lostVehicle.getLostStatus() == "1") {
				lostVehicleList.add(lostVehicle);
			}
		}
		setLostVehicleList(lostVehicleList); // 设置失窃车辆数据
		setResponse(response);
		t1 = new Thread() {
			public void run() {
				setRecordTagNumber("");
				while (RFIDReaderUtil.isDEFAULT_CONTROLLER()) {
					boolean isContinue = true;
					String labelNumber = RFIDReaderUtil.getEPC_LABEL_NUMBER();
					if (labelNumber != null && labelNumber.trim() != "") {
						if (labelNumber.equals(getRecordTagNumber())) {
							try {
								sleep(100);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						} else {

							isContinue = checkAleadyPass(getAleadyPassMap(), labelNumber); // 检测标签是否已识别通过

							if (isContinue) {

								isContinue = checkLostVehicle(getLostVehicleList(), labelNumber); // 检测失窃车辆

								if (isContinue) {

									Map<String, Date> preCheckMap = getPreCheckMap();

									isContinue = preCheck(preCheckMap, labelNumber);

									if (isContinue) {
										Map<String, String> queryMacthingMap = getQueryMacthingMap();
										if (queryMacthingMap == null || queryMacthingMap.size() == 0) {
											queryMacthingMap = new HashMap<String, String>();
										} else {
											Iterator iterator = queryMacthingMap.keySet().iterator();
											while (iterator.hasNext()) {
												String tag = (String) iterator.next();
												if (labelNumber.equals(queryMacthingMap.get(tag))) { // 匹配配对的标签，配对成功则删除当前map和preCheckMap记录，添加aleadyMap记录
													// queryMacthingMap.remove(tag);
													// mapRemove2(queryMacthingMap, tag);
													iterator.remove();
													queryMacthingMap.remove(tag);
													setQueryMacthingMap(queryMacthingMap);
													// preCheckMap.remove(tag);
													mapRemove1(preCheckMap, tag);
													// preCheckMap.remove(tag);
													setPreCheckMap(preCheckMap);
													System.out.println(getPreCheckMap().size());
													// if (preCheckMap != null) {
													// System.out.println(preCheckMap.size());
													// } else {
													// System.out.println("wuwuwuwuwuwuwu");
													// }
													Map<String, Date> aleadyMap = getAleadyPassMap();
													if (aleadyMap == null) {
														aleadyMap = new HashMap<String, Date>();
													}
													aleadyMap.put(labelNumber, new Date());

													setAleadyPassMap(aleadyMap);
													RfidTag rfidTag = iTagService.findByTagNum(labelNumber);
													if (rfidTag == null) {
														break;
													}
													String vehicleTagNum = "";
													String userTagNum = "";
													if ("用户标签".equals(rfidTag.getTagDetail())) {
														vehicleTagNum = rfidTag.getTagBratherNum();
														userTagNum = rfidTag.getTagNum();
													} else if ("车辆标签".equals(rfidTag.getTagDetail())) {
														vehicleTagNum = rfidTag.getTagNum();
														userTagNum = rfidTag.getTagBratherNum();
													}

													String normalScanInfo = "车辆标签号为" + vehicleTagNum + "的车辆在"
															+ DateUtils.formatDate(new Date(), "yyyy-MM-dd hh:mm:ss")
															+ "经过小区门口";
													List<String> normalScanList = getNormalScanList();
													if (normalScanList == null) {
														normalScanList = new ArrayList<String>();
													}
													normalScanList.add(normalScanInfo);
													setNormalScanList(normalScanList);
													addRecord(vehicleTagNum, userTagNum, normalScanInfo);
													try {
														sleep(100);
													} catch (InterruptedException e) {
														e.printStackTrace();
													}

													RFIDReaderUtil.setEPC_LABEL_NUMBER(null);
													isContinue = false;
													break;
												}
											}
										}

										if (isContinue) { // 匹配失败
											RfidTag rfidTag = iTagService.findByTagNum(labelNumber); // 查询是否存在标签
											// System.out.println(rfidTag);
											if (rfidTag != null) {
												preCheckMap.put(labelNumber, new Date());
												setPreCheckMap(preCheckMap);
												queryMacthingMap.put(labelNumber, rfidTag.getTagBratherNum());
												setQueryMacthingMap(queryMacthingMap);
											} else {
												RFIDReaderUtil.setEPC_LABEL_NUMBER(null);
											}
										}
									}
								}
							}
						}
					}
				}
			}
		};
		t2 = new Thread() {
			public void run() {
				while (RFIDReaderUtil.isDEFAULT_CONTROLLER()) {
					checkIsPass(getPreCheckMap());
					checkValidTime(getAleadyPassMap());
					try {
						sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		t1.start();
		t2.start();

		return r;
	}

	@SuppressWarnings("deprecation")
	@ResponseBody
	@RequestMapping(value = "shutdown", method = RequestMethod.POST)
	public R shutdown(HttpServletRequest request, HttpServletResponse response) {
		R r = new R();
		t1.stop();
		t2.stop();
		RFIDReaderUtil.stop();
		return r;
	}

	@ResponseBody
	@RequestMapping(value = "scanInfo", method = RequestMethod.POST)
	public R scanInfo(HttpServletRequest request, HttpServletResponse response) {
		R r = new R();
		r.put("errorScanList", getErrorScanList());
		// System.out.println(getErrorScanList());
		// System.out.println(getNormalScanList());
		r.put("normalScanList", getNormalScanList());
		setErrorScanList(null);
		setNormalScanList(null);
		return r;
	}

	@RequestMapping(value = "addInit", method = RequestMethod.POST)
	public R addV(HttpServletRequest request, HttpServletResponse response) {
		List<Vehicle> list1 = new ArrayList<Vehicle>();
		List<RfidTag> list2 = new ArrayList<RfidTag>();
		List<LostVehicle> list3 = new ArrayList<LostVehicle>();
		for (int i = 0; i < 100; i++) {
			Vehicle v = new Vehicle();
			LostVehicle l = new LostVehicle();
			if (i % 5 == 0) {
				v.setBrand("爱玛");
				l.setLostBrand("爱玛");
				String cc = getRandomColor();
				l.setLostDescribe("车身颜色为：" + cc);
				v.setDescribe("车身颜色为：" + cc);
			} else if (i % 5 == 1) {
				v.setBrand("小刀");
				l.setLostBrand("小刀");
				String cc = getRandomColor();
				l.setLostDescribe("车身颜色为：" + cc);
				v.setDescribe("车身颜色为：" + cc);
			} else if (i % 5 == 2) {
				v.setBrand("雅马哈");
				l.setLostBrand("雅马哈");
				String cc = getRandomColor();
				l.setLostDescribe("车身颜色为：" + cc);
				v.setDescribe("车身颜色为：" + cc);
			} else if (i % 5 == 3) {
				v.setBrand("绿源");
				l.setLostBrand("绿源");
				String cc = getRandomColor();
				l.setLostDescribe("车身颜色为：" + cc);
				v.setDescribe("车身颜色为：" + cc);
			} else if (i % 5 == 4) {
				v.setBrand("宝骏");
				l.setLostBrand("宝骏");
				String cc = getRandomColor();
				l.setLostDescribe("车身颜色为：" + cc);
				v.setDescribe("车身颜色为：" + cc);
			}
			String tag1 = getUUID();
			String tag2 = getUUID();
			l.setLostTag(tag1);
			l.setLostUserTag(tag2);
			l.setLostStatus("1");
			l.setLostTime(new Date());
			l.setBackTime(null);
			RfidTag r1 = new RfidTag();
			RfidTag r2 = new RfidTag();
			r1.setTagNum(tag1);
			r1.setTagBratherNum(tag2);
			r1.setTagDetail("车辆标签");
			list2.add(r1);
			r2.setTagNum(tag2);
			r2.setTagBratherNum(tag1);
			r2.setTagDetail("用户标签");
			list2.add(r2);
			v.setSelfTag(tag1);
			v.setUserTag(tag2);
			list1.add(v);
			if (i < 10) {
				list3.add(l);
			}
		}
		iVehicleService.insertBatch(list1);
		iTagService.insertBatch(list2);
		iLostVehicleService.insertBatch(list3);
		return R.ok();
	}

	/**
	 * 自动生成32位的UUid，对应数据库的主键id进行插入用。
	 * 
	 * @return
	 */
	public String getUUID() {

		String ss = UUID.randomUUID().toString().replace("-", "").substring(0, 24);
		String rs = "";

		for (int i = 0; i < ss.length(); i++) {
			if (i % 2 == 0 && i != 0) {
				rs += " " + ss.substring(i, i + 1);
			} else {
				rs += ss.substring(i, i + 1);
			}
		}

		return rs;
	}

	public String getRandomColor() {

		String c = "黑色";

		Random r = new Random();
		int ii = r.nextInt(7);
		if (ii == 0) {
			c = "黑色";
		} else if (ii == 1) {
			c = "白色";
		} else if (ii == 2) {
			c = "红色";
		} else if (ii == 3) {
			c = "黄色";
		} else if (ii == 4) {
			c = "黑白色";
		} else if (ii == 5) {
			c = "红黄色";
		} else if (ii == 6) {
			c = "绿色";
		}
		return c;
	}

	public void addRecord(String tag, String userTag, String describe) {
		VehicleRecord vehicleRecord = new VehicleRecord();
		vehicleRecord.setVehicleTagNum(tag);
		vehicleRecord.setUserTagNum(userTag);
		vehicleRecord.setReadTime(new Date());
		vehicleRecord.setDescribe(describe);
		iVehicleRecordService.insert(vehicleRecord);
	}

	@SuppressWarnings("rawtypes")
	public boolean checkAleadyPass(Map<String, Date> aleadyMap, String labelNumber) {
		if (aleadyMap != null && aleadyMap.size() > 0) {
			Iterator iterator = aleadyMap.keySet().iterator();
			while (iterator.hasNext()) {
				String tag = (String) iterator.next();
				if (labelNumber.equals(tag)) {
					RFIDReaderUtil.setEPC_LABEL_NUMBER(null);
					return false;
				}
			}
			//
			// for (String tag : aleadyMap.keySet()) { // 检测是否已记录过该标签
			//
			// }
		}
		return true;
	}

	public boolean checkLostVehicle(List<LostVehicle> lostVehicleList, String labelNumber) {
		for (LostVehicle lv : lostVehicleList) {
			if (labelNumber.equals(lv.getLostTag()) || labelNumber.equals(lv.getLostUserTag())) { // 与失窃车辆库对比，查找失窃车辆
				Map<String, Date> aleadyMap = getAleadyPassMap();
				if (aleadyMap == null) {
					aleadyMap = new HashMap<String, Date>();
				}
				aleadyMap.put(labelNumber, new Date());
				setAleadyPassMap(aleadyMap);
				SpeakUtil.speak("Discovery of stolen vehicles.");
				SpeakUtil.sound(getHint());
				String errorScanInfo = "发现失窃车辆，车辆标签号：" + lv.getLostTag() + "，" + lv.getLostDescribe();
				List<String> errorScanList = getErrorScanList();
				if (errorScanList == null) {
					errorScanList = new ArrayList<String>();
				}
				errorScanList.add(errorScanInfo);
				setErrorScanList(errorScanList);
				String normalScanInfo = "发现失窃车辆，车辆标签号：" + lv.getLostTag() + "，" + lv.getLostDescribe();
				List<String> normalScanList = getNormalScanList();
				if (normalScanList == null) {
					normalScanList = new ArrayList<String>();
				}
				normalScanList.add(normalScanInfo);
				setNormalScanList(normalScanList);
				addRecord(lv.getLostTag(), lv.getLostUserTag(), lv.getLostDescribe());

				Vehicle vehicle = iVehicleService.findBySelfTag(lv.getLostTag());
				String phone = vehicle.getUserPhone();
				String datas = vehicle.getUserName() + "," + DateUtils.getDate("yyyy-MM-dd hh:mm:ss");
				smsSendService.sendAsyncSmsByCode(phone, "332811", datas);

				RFIDReaderUtil.setEPC_LABEL_NUMBER(null);
				return false;
			}
		}
		return true;
	}

	@SuppressWarnings("rawtypes")
	public boolean preCheck(Map<String, Date> preCheckMap, String labelNumber) {
		if (preCheckMap != null && preCheckMap.size() > 0) {
			Iterator iterator = preCheckMap.keySet().iterator();
			while (iterator.hasNext()) {
				String tag = (String) iterator.next();
				if (labelNumber.equals(tag)) { // 判断是否已记录该标签
					return false;
				}
			}
			// for (String tag : preCheckMap.keySet()) {
			//
			// }
		} else {
			preCheckMap = new HashMap<String, Date>();
		}
		return true;
	}

	@SuppressWarnings("rawtypes")
	public void checkIsPass(Map<String, Date> preCheckMap) {
		if (preCheckMap != null && preCheckMap.size() > 0) {
			Iterator iterator = preCheckMap.keySet().iterator();
			while (iterator.hasNext()) {
				String tag = (String) iterator.next();
				RfidTag rfidTag = iTagService.findByTagNum(tag);
				if (rfidTag == null) {
					break;
				}
				if ("用户标签".equals(rfidTag.getTagNum())) {
					break;
				} else {
					if (DateUtils.getSecondOfTwoDate(preCheckMap.get(tag), new Date()) > 3) {
						// preCheckMapRemove.remove(tag);
						Map<String, Date> aleadyMap = getAleadyPassMap();
						if (aleadyMap == null) {
							aleadyMap = new HashMap<String, Date>();
						}
						aleadyMap.put(tag, new Date());
						setAleadyPassMap(aleadyMap);
						System.out.println(preCheckMap.size());
						iterator.remove();
						preCheckMap.remove(tag);
						setPreCheckMap(preCheckMap);
						System.out.println(getPreCheckMap().size());
						Vehicle vehicle = iVehicleService.findBySelfTag(tag);
						if (vehicle == null) {
							vehicle = iVehicleService.findByUserTag(tag);
							if (vehicle == null)
								vehicle = new Vehicle();
						}
						SpeakUtil.speak("A number of records fell during the sports meet.");
						SpeakUtil.sound(getHint());
						String errorScanInfo = "车辆品牌为" + vehicle.getBrand() + "," + vehicle.getDescribe()
								+ "的车辆存在异常骑行！请注意！";
						List<String> errorScanList = getErrorScanList();
						if (errorScanList == null) {
							errorScanList = new ArrayList<String>();
						}
						errorScanList.add(errorScanInfo);
						setErrorScanList(errorScanList);
						String normalScanInfo = "车辆品牌为" + vehicle.getBrand() + "," + vehicle.getDescribe()
								+ "的车辆存在异常骑行！请注意！";
						List<String> normalScanList = getNormalScanList();
						if (normalScanList == null) {
							normalScanList = new ArrayList<String>();
						}
						normalScanList.add(normalScanInfo);
						setNormalScanList(normalScanList);
						addRecord(vehicle.getSelfTag(), vehicle.getUserTag(), normalScanInfo);
						// System.out.println(errorScanInfo);
						String phone = vehicle.getUserPhone();
						String datas = vehicle.getUserName() + "," + DateUtils.getDate("yyyy-MM-dd hh:mm:ss");
						smsSendService.sendAsyncSmsByCode(phone, "309425", datas);

						String email = vehicle.getUserEmail();
						String emailData = "尊敬的" + vehicle.getUserName() + "，您好，您的电动车在"
								+ DateUtils.getDate("yyyy-MM-dd hh:mm:ss") + "存在异常骑行，请确认是否本人骑行，如是，请忽略本消息。";
						emailSendService.sendAsyncEmail(email, "电动车异常提醒", emailData);

					}
				}
			}
		}
		// if (preCheckMap != null && preCheckMap.size() > 0) {
		// for (String tag : preCheckMap.keySet()) {
		//
		// }
		// setPreCheckMap(preCheckMapRemove);
		// }
	}

	@SuppressWarnings("rawtypes")
	public void checkValidTime(Map<String, Date> aleadyMap) {
		if (aleadyMap != null && aleadyMap.size() > 0) {
			Iterator iterator = aleadyMap.keySet().iterator();
			while (iterator.hasNext()) {
				String tag = (String) iterator.next();
				if (DateUtils.getSecondOfTwoDate(aleadyMap.get(tag), new Date()) > 20) { // 20秒标签保存有效时长
					iterator.remove();
					aleadyMap.remove(tag);
					setAleadyPassMap(aleadyMap);
				}
			}
		}

	}

	@SuppressWarnings("rawtypes")
	public void mapRemove1(Map<String, Date> map, String tagNum) {
		Iterator iterator = map.keySet().iterator();
		while (iterator.hasNext()) {
			Object key = (Object) iterator.next();
			if (tagNum.equals(key)) {
				iterator.remove();
				map.remove(key);
			}
		}
	}

	@SuppressWarnings("rawtypes")
	public void mapRemove2(Map<String, String> map, String tagNum) {
		Iterator iterator = map.keySet().iterator();
		while (iterator.hasNext()) {
			Object key = (Object) iterator.next();
			if (tagNum.equals(key)) {
				iterator.remove();
				map.remove(key);
			}
		}
	}

	@SuppressWarnings("rawtypes")
	public static void main(String[] args) {
		Map<String, String> map = new HashMap<String, String>();
		Map<String, String> map1 = new HashMap<String, String>();
		map.put("1", "a");
		map.put("2", "b");
		map.put("3", "c");
		Iterator iterator = map.keySet().iterator();
		while (iterator.hasNext()) {
			Object key = (Object) iterator.next();
			if ("1".equals(key)) {
				iterator.remove();
				map.remove(key);
				map1 = map;
			}
		}

		System.out.println(map1.size());

	}

}
