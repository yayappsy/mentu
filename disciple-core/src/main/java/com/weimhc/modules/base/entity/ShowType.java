/**
 * 
 */
package com.weimhc.modules.base.entity;

/**
 * 字段展现类型，供自定义显示时使用
 *
 * @author laozh
 * @version 2017年3月23日
 */
public enum ShowType {

	/** 定义复选框 **/
	checkbox(true),
	/** 下拉列表 **/
	select(true),
	/** 定义输入字段和 "浏览"按钮，供文件上传 **/
	file(false),
	/** 定义图像形式 **/
	image(false),
	/** 定义密码字段 **/
	password(false),
	/** 定义单选框 **/
	radiobox(true),
	/** 定义单行的输入字段，用户可在其中输入文本,默认宽度为 20 个字符 **/
	text(false),
	/** 定义多行的输入字段 **/
	textarea(false),
	/** 字典选择 **/
	dict(false),
	/** 区域选择 **/
	area(false);

	/**
	 * 是否有可选项
	 */
	private boolean hasOptions;

	private ShowType(boolean hasOptions) {
		this.hasOptions = hasOptions;
	};

	public boolean getHasOptions() {
		return hasOptions;
	}
}
