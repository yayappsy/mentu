package com.weimhc.modules.order.entity;

/**
 * 服务状态
 */
public enum ServiceStatus {

	/** 等待服务 */
	waiting,

	/** 服务已开始 */
	started,

	/** 已经开始录制视屏 */
	recordingStarted,

	/** 服务已完成 */
	completed,

	/** 视频已上传 */
	uploaded;

}