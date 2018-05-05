package com.weimhc.framework.plugin;

public interface Plugin {

	/**
	 * 
	 * @return 插件类型
	 */
	public String getType();

	/**
	 * 
	 * @return 插件Id
	 */
	public String getId();

	/**
	 * 
	 * @return 插件名称
	 */
	public String getName();

	/**
	 * 
	 * @return 插件版本
	 */
	public String getVersion();

	/**
	 * 
	 * @return 作者
	 */
	public String getAuthor();

	/**
	 * 执行插件功能
	 * 
	 * @param params
	 *            业务类传递给插件的参数
	 */
	public void perform(Object... params);
}
