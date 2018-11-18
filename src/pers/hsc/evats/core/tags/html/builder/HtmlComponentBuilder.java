package pers.hsc.evats.core.tags.html.builder;

import java.io.IOException;
import java.util.Map;

/**
 * 组建构造器具
 * 
 * @author hsc
 *
 * Mar 29, 2018
 */
public interface HtmlComponentBuilder {
	/**
	 * JSMap
	 * 
	 * @return
	 */
	public Map<String, String> getJsComponents();

	/**
	 * css语句map
	 * 
	 * @return
	 */
	public Map<String, String> getCssComponents();
	
	/**
	 * html语句map
	 * 
	 * @return
	 */
	public Map<String, String> getFragmentComponents();

	/**
	 * 初始化
	 * 
	 * @throws IOException
	 */
	public void init() throws IOException;
}