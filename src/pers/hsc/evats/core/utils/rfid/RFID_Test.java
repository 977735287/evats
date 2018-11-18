package pers.hsc.evats.core.utils.rfid;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import pers.hsc.evats.core.utils.SpeakUtil;

/**
 * @author hsc
 *
 *         Apr 8, 2018
 */
public class RFID_Test {
	
	private static int a = 0;
	
	@SuppressWarnings("resource")
	public static void main(String[] args) throws ParseException {
		
//		String d1 = "2018-05-29 05:59:00";
//		String d2 = "2018-05-29 05:59:10";
//		Calendar cal = Calendar.getInstance();
//		Calendar cal2 = Calendar.getInstance();
//		cal.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(d1));
//		cal2.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(d2));		
//		System.out.println(cal2.getTimeInMillis() - cal.getTimeInMillis());
//		new Thread() {
//			public void run() {
//				boolean c = true;
//				while(c) {
//					System.out.println(a++);
//					try {
//						sleep(1000);
//					} catch (InterruptedException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//					if(a == 10) {
//						c = false;
//					}
//				}
//				
//			}
//		}.start();
//		SpeakUtil.sound("D:/4031.wav");
		
		
		Scanner scanner = new Scanner(System.in);
		
		boolean cc = false;
		while(cc){
			System.out.println("0：读取结束\n1：获取读写器版本\n2：记录当前标签\n");
			System.out.println("input n :");
			int n = scanner.nextInt();
			if (n == 0) {
				cc = false;
			} else{
				//再建一个map用来记录标签读取时间间隔并实现报警功能（线程实现）,重定义map到utils等里面过新建util
				Map<String, String> map = new HashMap<String, String>(); // 读取存储匹配标签号
				Map<String, String> map1 = new HashMap<String, String>();// 已匹配的标签号，设置10秒不在识别该标签

				RFIDReaderUtil.start();
				boolean ctr = false;
				while (!ctr) {
					System.out.println("input:");
					int input = scanner.nextInt();
					if (input == 0) {
						RFIDReaderUtil.stop();
						ctr = true;
					} else if (input == 1) {
						System.out.println(RFIDReaderUtil.isConnect());
						System.out.println(RFIDReaderUtil.getVersion());
					} else {

						if (map1.size() > 0) {
							int c1 = 0;
							for (Object o1 : map1.keySet()) {
								if (o1 == RFIDReaderUtil.getEPC_LABEL_NUMBER()
										|| map.get(o1) == RFIDReaderUtil.getEPC_LABEL_NUMBER()) {
									break;
								} else {
									c1++;
								}
								if (c1 == map1.size()) {
									if (map.size() == 0) {
										// 数据库查询RFIDReaderUtil.getEPC_LABEL_NUMBER()并取出对应标签
										//识别是否失窃车辆
										map.put(RFIDReaderUtil.getEPC_LABEL_NUMBER(), null);
									} else {
										int c = 0;
										for (Object o : map.keySet()) {
											if (o == RFIDReaderUtil.getEPC_LABEL_NUMBER()) {
												break;
											}
											// else if
											// map.get(o)==RFIDReaderUtil.getEPC_LABEL_NUMBER()
											// 记录当前车辆经过的时间点
											// map1.set(o,map.get(o));
											// map.remove(o);
											// break;
											else {
												c++;
											}
											if (c == map.size()) {
												map.put(RFIDReaderUtil.getEPC_LABEL_NUMBER(), null);
											}
										}
									}
								}
							}
						} else {
							if (map.size() == 0) {
								// 数据库查询RFIDReaderUtil.getEPC_LABEL_NUMBER()并取出对应标签放到null的位置
								map.put(RFIDReaderUtil.getEPC_LABEL_NUMBER(), null);
							} else {
								int c = 0;
								for (Object o : map.keySet()) {
									if (o == RFIDReaderUtil.getEPC_LABEL_NUMBER()) {
										break;
									}
									// else if
									// map.get(o)==RFIDReaderUtil.getEPC_LABEL_NUMBER()
									// 记录当前车辆经过的时间点
									// map1.set(o,map.get(o));
									// map.remove(o);
									// break;
									else {
										c++;
									}
									if (c == map.size()) {
										map.put(RFIDReaderUtil.getEPC_LABEL_NUMBER(), null);
									}
								}
							}
						}
					}
				}

				System.out.println(map.size());

				// map.put("test", null);
				for (Object object : map.keySet()) {
					System.out.println(object);
				}
			}	
		}
		
	}
}
