package com.weimhc.framework.ueditor;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import com.baidu.ueditor.define.AppInfo;
import com.baidu.ueditor.define.BaseState;
import com.baidu.ueditor.define.State;
import com.weimhc.framework.ueditor.define.ActionMap;
import com.weimhc.framework.ueditor.hunter.FileManager;
import com.weimhc.framework.ueditor.hunter.ImageHunter;
import com.weimhc.framework.ueditor.upload.Uploader;

/**
 * 继承百度编辑器的类，并做功能上的添加
 * 
 * @author szuo
 *
 */
public class ActionEnter extends com.baidu.ueditor.ActionEnter {

	private HttpServletRequest request = null;

	private String rootPath = null;
	private String contextPath = null;

	private String actionType = null;

	private ConfigManager configManager = null;

	public ActionEnter(HttpServletRequest request, String rootPath) {
		super(request, rootPath);
		this.request = request;
		this.rootPath = rootPath;
		this.actionType = request.getParameter("action");
		this.contextPath = request.getContextPath();
		this.configManager = ConfigManager.getInstance(this.rootPath,
				this.contextPath, request.getRequestURI());

	}

	@Override
	public String invoke() {

		if (actionType == null || !ActionMap.mapping.containsKey(actionType)) {
			return new BaseState(false, AppInfo.INVALID_ACTION).toJSONString();
		}

		if (this.configManager == null || !this.configManager.valid()) {
			return new BaseState(false, AppInfo.CONFIG_ERROR).toJSONString();
		}

		State state = null;

		int actionCode = ActionMap.getType(this.actionType);

		/** 基本的配置信息 */
		Map<String, Object> conf = null;

		/** 传递过来的附加信息 */
		Map<String, Object> serverParams = new HashMap<String, Object>();
		for (Entry<String, String[]> abc : this.request.getParameterMap()
				.entrySet()) {
			serverParams.put(abc.getKey(),
					StringUtils.join(abc.getValue(), ","));
		}

		switch (actionCode) {

		case ActionMap.CONFIG:
			return this.configManager.getAllConfig().toString();

		case ActionMap.UPLOAD_IMAGE:
		case ActionMap.UPLOAD_SCRAWL:
		case ActionMap.UPLOAD_VIDEO:
		case ActionMap.UPLOAD_FILE:
			conf = this.configManager.getConfig(actionCode);
			conf.putAll(serverParams);
			state = new Uploader(request, conf).doExec();
			break;

		case ActionMap.CATCH_IMAGE:
			conf = configManager.getConfig(actionCode);
			String[] list = this.request
					.getParameterValues((String) conf.get("fieldName"));
			state = new ImageHunter(conf).capture(list);
			break;

		case ActionMap.LIST_IMAGE:
		case ActionMap.LIST_FILE:
			conf = configManager.getConfig(actionCode);
			conf.putAll(serverParams);
			int start = this.getStartIndex();
			state = new FileManager(conf).listFile(start);
			break;
		case ActionMap.DELETE_IMAGE:
			conf = configManager.getConfig(actionCode);
			conf.putAll(serverParams);
			String fileName = this.request.getParameter("fileName");
			state = new FileManager(conf).deleteFile(fileName);
			break;

		}

		return state.toJSONString();

	}

}