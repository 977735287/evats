package pers.hsc.evats.core.utils;

import java.util.Map;

import com.google.common.collect.Maps;

/**
 * 全局配置文件工具类
 * 
 * @author hsc
 *
 * Mar 29, 2018
 */
public class EvatsPropertiesUtil extends PropertiesUtil {
	private static String JEEWE_BPROPERTIES_FILENAME = "car-anti-theft.properties";
	private static EvatsPropertiesUtil propertiesUtil = new EvatsPropertiesUtil();
	// 保存全局属性值
	private static Map<String, String> configMap = Maps.newHashMap();

	public EvatsPropertiesUtil() {
		load(JEEWE_BPROPERTIES_FILENAME);
	}

	public static EvatsPropertiesUtil getProperties() {
		if (propertiesUtil != null) {
			propertiesUtil = new EvatsPropertiesUtil();
		}
		return propertiesUtil;
	}

	/**
	 * 获得配置
	 * 
	 * @param key
	 * @return
	 */
	public static String getConfig(String key) {
		String value = configMap.get(key);
		if (value == null) {
			value = propertiesUtil.getString(key);
			configMap.put(key, value != null ? value : StringUtils.EMPTY);
		}
		return value;
	}

	/**
	 * 设置配置
	 * 
	 * @param key
	 * @param value
	 */
	public static void setConfig(String key, Object value) {
		propertiesUtil.set(key, value);
	}

	/**
	 * 移除配置
	 * 
	 * @param key
	 * @return
	 */
	public static boolean removeConfig(String key) {
		return propertiesUtil.remove(key);
	}
}
