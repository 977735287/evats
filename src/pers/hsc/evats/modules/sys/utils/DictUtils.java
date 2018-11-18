package pers.hsc.evats.modules.sys.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import com.google.common.collect.Lists;
import pers.hsc.evats.core.utils.CacheUtils;
import pers.hsc.evats.core.utils.SpringContextHolder;
import pers.hsc.evats.modules.sys.Constants;
import pers.hsc.evats.modules.sys.entity.Dict;
import pers.hsc.evats.modules.sys.service.IDictService;

/**
 * 字典工具类
 * 
 * @author hsc
 *
 * Mar 31, 2018
 */
@SuppressWarnings("unchecked")
public class DictUtils {

	private static IDictService dictService = SpringContextHolder.getBean(IDictService.class);

	public static String getDictLabel(String value, String code, String defaultValue) {
		if (StringUtils.isNotBlank(code) && StringUtils.isNotBlank(value)) {
			for (Dict dict : getDictList(code)) {
				if (code.equals(dict.getCode()) && value.equals(dict.getValue())) {
					return dict.getLabel();
				}
			}
		}
		return defaultValue;
	}

	public static String getDictLabels(String values, String code, String defaultValue) {
		if (StringUtils.isNotBlank(code) && StringUtils.isNotBlank(values)) {
			List<String> valueList = new ArrayList<String>();
			for (String value : StringUtils.split(values, ",")) {
				valueList.add(getDictLabel(value, code, defaultValue));
			}
			return StringUtils.join(valueList, ",");
		}
		return defaultValue;
	}

	public static String getDictValue(String label, String code, String defaultLabel) {
		if (StringUtils.isNotBlank(code) && StringUtils.isNotBlank(label)) {
			for (Dict dict : getDictList(code)) {
				if (code.equals(dict.getCode()) && label.equals(dict.getLabel())) {
					return dict.getValue();
				}
			}
		}
		return defaultLabel;
	}

	public static List<Dict> getDictList(String code) {
		Map<String, List<Dict>> dictMap = (Map<String, List<Dict>>) CacheUtils
				.get(Constants.CACHE_DICT_MAP);
		if (dictMap == null) {
			dictMap = new HashMap<String, List<Dict>>();
			for (Dict dict : dictService.selectDictList()) {
				List<Dict> dictList = dictMap.get(dict.getCode());
				if (dictList != null) {
					dictList.add(dict);
				} else {
					dictMap.put(dict.getCode(), Lists.newArrayList(dict));
				}
			}
			CacheUtils.put(Constants.CACHE_DICT_MAP, dictMap);
		}
		List<Dict> dictList = dictMap.get(code);
		if (dictList == null) {
			dictList = new ArrayList<Dict>();
		}
		return dictList;
	}

	/*
	 * 清除换成
	 */
	public static void clear() {
		CacheUtils.remove(Constants.CACHE_DICT_MAP);
	}

}
