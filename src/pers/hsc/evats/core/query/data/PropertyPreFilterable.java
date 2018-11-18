package pers.hsc.evats.core.query.data;

import com.alibaba.fastjson.serializer.SerializeFilter;

/**
 * JSON格式化输出
 * 
 * @author hsc
 *
 * Mar 29, 2018
 *
 */
public interface PropertyPreFilterable {
	
	public SerializeFilter constructFilter(Class<?> clazz);
	
	public void addQueryProperty(String... properties);
	
	public void addIncludeFilter(Class<?> clazz, String... properties);

	public void addExcludeFilter(Class<?> clazz, String... properties);
}
