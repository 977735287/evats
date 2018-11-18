package pers.hsc.evats.core.common.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import com.alibaba.fastjson.JSON;
import pers.hsc.evats.core.utils.StringUtils;
import pers.hsc.evats.core.utils.convert.DateConvertEditor;
import pers.hsc.evats.core.utils.convert.StringConvertEditor;
import pers.hsc.evats.modules.user.entity.LostVehicle;

/**
 * 基础控制器 http://blog.csdn.net/catoop/article/details/51278675 写得不错的表单验证
 * 
 * @author hsc
 *
 * Mar 29, 2018
 */
public class BaseController {
	/**
	 * 日志对象
	 */
	protected Logger logger = LoggerFactory.getLogger(getClass());
	private String viewPrefix;
	private List<LostVehicle> lostVehicleList;  // 储存失窃车辆信息便于匹配
	private Map<String, Date> aleadyPassMap = new HashMap<String, Date>(); // 已识别通过的标签（车辆标签和用户标签匹配成功）和识别的时间点
	private Map<String, Date> preCheckMap = new HashMap<String, Date>(); // 读取到的标签和读取的时间点
	private Map<String, String> queryMacthingMap = new HashMap<String, String>(); // 读取到的标签和配对的标签
	private String recordTagNumber;
	private String hint;
	private HttpServletResponse response;
	private List<String> errorScanList;
	private List<String> normalScanList;

	protected BaseController() {
		setViewPrefix(defaultViewPrefix());
	}

	/**
	 * 返回JSON字符串
	 * 
	 * @param response
	 * @param object
	 * @return
	 * @return
	 */
	protected void printString(HttpServletResponse response, Object object) {
		printString(response, JSON.toJSONString(object));
	}

	/**
	 * 打印字符串到页面
	 * 
	 * @param response
	 * @param string
	 * @return
	 */
	protected void printString(HttpServletResponse response, String string) {
		try {
			response.reset();
			response.setContentType("application/json");
			response.setCharacterEncoding("utf-8");
			response.getWriter().print(string);
		} catch (IOException e) {

		}
	}

	/**
	 * 初始化数据绑定
	 * 
	 * @param binder
	 */
	@InitBinder
	void initBinder(ServletRequestDataBinder binder) {
		// 将所有传递进来的String进行HTML编码，防止XSS攻击
		// 这个会导致数据库数据
		binder.registerCustomEditor(String.class, new StringConvertEditor());
		// 日期格式
		binder.registerCustomEditor(Date.class, new DateConvertEditor());
	}

	/**
	 * 当前模块 视图的前缀 默认 1、获取当前类头上的@RequestMapping中的value作为前缀 2、如果没有就使用当前模型小写的简单类名
	 */
	public void setViewPrefix(String viewPrefix) {
		if (viewPrefix.startsWith("/")) {
			viewPrefix = viewPrefix.substring(1);
		}
		this.viewPrefix = viewPrefix;
	}

	public String getViewPrefix() {
		return viewPrefix;
	}

	/**
	 * 获取视图名称：即prefixViewName + "/" + suffixName
	 *
	 * @return
	 */
	public String display(String suffixName) {
		if (!suffixName.startsWith("/")) {
			suffixName = "/" + suffixName;
		}
		return getViewPrefix().toLowerCase() + suffixName;
	}

	protected String defaultViewPrefix() {
		String currentViewPrefix = "";
		RequestMapping requestMapping = AnnotationUtils.findAnnotation(getClass(), RequestMapping.class);
		if (requestMapping != null && requestMapping.value().length > 0) {
			currentViewPrefix = requestMapping.value()[0];
			// 替换${admin.url.prefix}
			currentViewPrefix = currentViewPrefix.replace("${admin.url.prefix}", "modules");
		}
		if (StringUtils.isEmpty(currentViewPrefix)) {
			currentViewPrefix = this.getClass().getSimpleName().replace("Controller", "").toLowerCase();
		}
		return currentViewPrefix;
	}

	public List<LostVehicle> getLostVehicleList() {
		return lostVehicleList;
	}

	public void setLostVehicleList(List<LostVehicle> lostVehicleList) {
		this.lostVehicleList = lostVehicleList;
	}

	public Map<String, Date> getAleadyPassMap() {
		return aleadyPassMap;
	}

	public void setAleadyPassMap(Map<String, Date> aleadyPassMap) {
		this.aleadyPassMap = aleadyPassMap;
	}

	public Map<String, Date> getPreCheckMap() {
		return preCheckMap;
	}

	public void setPreCheckMap(Map<String, Date> preCheckMap) {
		this.preCheckMap = preCheckMap;
	}

	public Map<String, String> getQueryMacthingMap() {
		return queryMacthingMap;
	}

	public void setQueryMacthingMap(Map<String, String> queryMacthingMap) {
		this.queryMacthingMap = queryMacthingMap;
	}

	public String getRecordTagNumber() {
		return recordTagNumber;
	}

	public void setRecordTagNumber(String recordTagNumber) {
		this.recordTagNumber = recordTagNumber;
	}

	public String getHint() {
		return hint;
	}

	public void setHint(String hint) {
		this.hint = hint;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	public List<String> getErrorScanList() {
		return errorScanList;
	}

	public void setErrorScanList(List<String> errorScanList) {
		this.errorScanList = errorScanList;
	}

	public List<String> getNormalScanList() {
		return normalScanList;
	}

	public void setNormalScanList(List<String> normalScanList) {
		this.normalScanList = normalScanList;
	}
	
}
