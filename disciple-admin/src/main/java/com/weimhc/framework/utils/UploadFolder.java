package com.weimhc.framework.utils;

/**
 * 可供上传的文件夹
 * 
 * @author szuo
 *
 */
public enum UploadFolder {

	/** 任务 */
	task(false),

	/** 活动 */
	activity(false),

	/** 商铺广告图片 */
	storeAd(false),

	/** 商铺入驻 */
	storeJoin(false),

	/** 广告图片 */
	ad(false),

	/** 头像图片 */
	avatar(false),

	/** 导航图片 */
	nav(false),

	/** 网站基础数据 **/
	base(true),
	/** 服务解决方案详情数据 **/
	serviceSolutionItem(false),
	/** 服务解决方案数据 **/
	serviceSolution(false),
	/** 健康产品数据 **/
	healthProduct(false),
	/** 文章数据 **/
	article(false);

	/***
	 * 是否系统文件夹
	 */
	public boolean isSystemData;

	private UploadFolder(boolean isSystemData) {
		this.isSystemData = isSystemData;
	}
}
