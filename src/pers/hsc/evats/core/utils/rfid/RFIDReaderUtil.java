package pers.hsc.evats.core.utils.rfid;

/**
 * @author hsc
 *
 *         Apr 8, 2018
 */
public class RFIDReaderUtil {

	private static final String DEFAULT_PORT = "COM3"; // 连接的端口号
	private static String PC_NUMBER; // PC号
	private static String EPC_LABEL_NUMBER;// EPC标签号
	private static String RSSI_NUMBER; // RSSI
	private static boolean DEFAULT_READING_EPCDATA = false; // 控制读取循环
	private static boolean DEFAULT_CONTROLLER = false; // 提供调用控制while循环读取标签
	private static String readerVersion; // RSSI

	private static UhfReader mReader = new UhfReader();

	// 连接RFID读写器设备
	public static void connect() {
		if (mReader.isConnect()) {
			System.out.println("已连接，无需重复连接！");
		} else {
			int[] boudrates = { UhfReaderAPI.UHF_BAUDRATE_9600, UhfReaderAPI.UHF_BAUDRATE_19200,
					UhfReaderAPI.UHF_BAUDRATE_57600, UhfReaderAPI.UHF_BAUDRATE_115200 };
			int selIndex = 3;
			if (selIndex >= 0 && selIndex < boudrates.length) {
				if (!mReader.Connect(DEFAULT_PORT, boudrates[selIndex])) // 注意波特率为115200，否则会出现无法打开端口
				{
					System.out.println("连接串口失败，请确认串口号以及波特率");
					return;
				}
			}
			mReader.EASAlarm(); // 蜂鸣器 (暂无效果，返回false)
			mReader.SetMode(1); // 防碰撞群读识别（返回true）
			setReaderVersion(mReader.GetVersion()); // 获取版本号
			setDEFAULT_READING_EPCDATA(false);
		}
	}

	public static void start() {

		if (!mReader.isConnect()) {
			System.out.println("请先连接串口");
			return;
		}

		if (!getDEFAULT_READING_EPCDATA()) {
			// 双线程读取模式
			mReader.ReadInventory(); // 启动快速读
			new Thread() {
				public void run() {
					setDEFAULT_READING_EPCDATA(true);

					while (getDEFAULT_READING_EPCDATA()) {
						StringBuffer PC = new StringBuffer();
						StringBuffer EPC = new StringBuffer();
						StringBuffer RSSI = new StringBuffer();

						if (mReader.RecvData(PC, EPC, RSSI)) {
							RFIDReaderUtil.setPC_NUMBER(PC.toString());
							RFIDReaderUtil.setEPC_LABEL_NUMBER(EPC.toString());
							RFIDReaderUtil.setRSSI_NUMBER(RSSI.toString() + "dBm");
						}
//						try {
//							sleep(1000);
//						} catch (InterruptedException e) {
//							e.printStackTrace();
//						}
					}

					mReader.StopOperation(); // 注意用完后一定要记得停止操作
				}
			}.start();
		} // 双线程模式END
	}

	public static void stop() {
		setDEFAULT_READING_EPCDATA(false);
	}

	public static boolean isConnect() {
		return mReader.isConnect();
	}
	
	public static boolean isStart() {
		return getDEFAULT_READING_EPCDATA();
	}

	public static void disConnect() {
		mReader.Disconnect(); // 断开连接
	}

	public static String getVersion() {
		return getReaderVersion();
	}

	public static boolean getDEFAULT_READING_EPCDATA() {
		return DEFAULT_READING_EPCDATA;
	}

	public static void setDEFAULT_READING_EPCDATA(boolean dEFAULT_READING_EPCDATA) {
		DEFAULT_READING_EPCDATA = dEFAULT_READING_EPCDATA;
	}

	public static String getPC_NUMBER() {
		return PC_NUMBER;
	}

	public static void setPC_NUMBER(String pC_NUMBER) {
		PC_NUMBER = pC_NUMBER;
	}

	public static String getEPC_LABEL_NUMBER() {
		return EPC_LABEL_NUMBER;
	}

	public static void setEPC_LABEL_NUMBER(String ePC_LABEL_NUMBER) {
		EPC_LABEL_NUMBER = ePC_LABEL_NUMBER;
	}

	public static String getRSSI_NUMBER() {
		return RSSI_NUMBER;
	}

	public static void setRSSI_NUMBER(String rSSI_NUMBER) {
		RSSI_NUMBER = rSSI_NUMBER;
	}

	public static boolean isDEFAULT_CONTROLLER() {
		return DEFAULT_CONTROLLER;
	}

	public static void setDEFAULT_CONTROLLER(boolean dEFAULT_CONTROLLER) {
		DEFAULT_CONTROLLER = dEFAULT_CONTROLLER;
	}

	public static String getReaderVersion() {
		return readerVersion;
	}

	public static void setReaderVersion(String readerVersion) {
		RFIDReaderUtil.readerVersion = readerVersion;
	}

}
