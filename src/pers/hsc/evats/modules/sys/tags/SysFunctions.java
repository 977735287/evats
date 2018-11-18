package pers.hsc.evats.modules.sys.tags;

import javax.servlet.http.Cookie;
import pers.hsc.evats.core.utils.EvatsPropertiesUtil;
import pers.hsc.evats.core.utils.ServletUtils;
import pers.hsc.evats.core.utils.StringUtils;

/**
 * @author hsc
 *
 * Mar 29, 2018
 */
public class SysFunctions {
	/**
	 * 获得后台地址
	 * 
	 * @title: getAdminUrlPrefix
	 * @description: 获得后台地址
	 * @return
	 * @return: String
	 */
	public static String getAdminUrlPrefix() {
		String adminUrlPrefix = EvatsPropertiesUtil.getConfig("admin.url.prefix");
		return adminUrlPrefix;
	}

	/**
	 * 获得后台地址
	 * 
	 * @title: getAdminUrlPrefix
	 * @description: 获得后台地址
	 * @return
	 * @return: String
	 */
	public static String get() {
		String adminUrlPrefix = EvatsPropertiesUtil.getConfig("admin.url.prefix");
		return adminUrlPrefix;
	}

	/**
	 * 加载风格
	 * 
	 * @title: getTheme
	 * @description: TODO(这里用一句话描述这个方法的作用)
	 * @param request
	 * @return
	 * @return: String
	 */
	public static String getTheme() {
		// 默认风格
		String theme = EvatsPropertiesUtil.getConfig("admin.default.theme");
		if (StringUtils.isEmpty(theme)) {
			theme = "uadmin";
		}
		// cookies配置中的模版
		Cookie[] cookies = ServletUtils.getRequest().getCookies();
		for (Cookie cookie : cookies) {
			if (cookie == null || StringUtils.isEmpty(cookie.getName())) {
				continue;
			}
			if (cookie.getName().equalsIgnoreCase("theme")) {
				theme = cookie.getValue();
			}
		}
		return theme;
	}
}
