package com.weimhc.framework.ueditor.hunter;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baidu.ueditor.PathFormat;
import com.baidu.ueditor.define.AppInfo;
import com.baidu.ueditor.define.BaseState;
import com.baidu.ueditor.define.MultiState;
import com.baidu.ueditor.define.State;
import com.weimhc.framework.utils.FileTypeUtils;
import com.weimhc.framework.utils.UploadUtils;

public class FileManager extends com.baidu.ueditor.hunter.FileManager {

	private static Logger logger = LoggerFactory.getLogger(FileManager.class);

	/** 配置文件传入的路径 */
	private String dir = null;

	/** 暂时不使用，它指向的是容器的根路径 */
	private String rootPath = null;

	private String[] allowFiles = null;
	private int count = 0;

	/** 文件存储实际文件夹,由根路径+其他路径构成 +在config.json中配置的路径构成 **/
	private String realSaveDir;

	public FileManager(Map<String, Object> conf) {
		super(conf);
		this.rootPath = (String) conf.get("rootPath");
		this.dir = (String) conf.get("dir");
		this.allowFiles = this.getAllowFiles(conf.get("allowFiles"));
		this.count = (Integer) conf.get("count");
		// 新加的功能
		this.realSaveDir = UploadUtils.getUserfilesBaseDir() + (String) conf.get("uploadFolder")
				+ this.dir;

	}

	@Override
	public State listFile(int index) {

		File dir = new File(this.realSaveDir);
		State state = null;

		if (!dir.exists()) {
			return new BaseState(false, AppInfo.NOT_EXIST);
		}

		if (!dir.isDirectory()) {
			return new BaseState(false, AppInfo.NOT_DIRECTORY);
		}

		Collection<File> list = FileUtils.listFiles(dir, this.allowFiles, true);

		if (index < 0 || index > list.size()) {
			state = new MultiState(true);
		} else {
			Object[] fileList = Arrays.copyOfRange(list.toArray(), index, index + this.count);
			state = this.getState(fileList);
		}

		state.putInfo("start", index);
		state.putInfo("total", list.size());

		return state;

	}

	private State getState(Object[] files) {

		MultiState state = new MultiState(true);
		BaseState fileState = null;

		File file = null;

		for (Object obj : files) {
			if (obj == null) {
				break;
			}
			file = (File) obj;
			String filePath = this.getPath(file);
			fileState = new BaseState(true);

			fileState.putInfo("url", UploadUtils.getAccessUrl(filePath));
			fileState.putInfo("fileName", file.getName());
			fileState.putInfo("filePath", filePath);
			if (FileTypeUtils.isImageFile(filePath)) {
				int imgWidth = 100;
				int imgHeight = 100;
				BufferedImage sourceImg;
				try {
					sourceImg = ImageIO.read(new FileInputStream(file));
					imgWidth = sourceImg.getWidth();
					imgHeight = sourceImg.getHeight();
				} catch (IOException e) {
					logger.error("获取图片宽度和高度错误", e);
				}
				fileState.putInfo("imgWidth", imgWidth);
				fileState.putInfo("imgHeight", imgHeight);
				fileState.putInfo("ext", FileTypeUtils.getSuffixByFilename(filePath));
				fileState.putInfo("thumbnail", UploadUtils.getAccessThumbnailUrl(filePath));
				fileState.putInfo("thumbPath", UploadUtils.getThumbnailPath(filePath));
			}
			state.addState(fileState);
		}

		return state;

	}

	/***
	 * 获取实际路径
	 * 
	 * @param file
	 * @return
	 */
	private String getPath(File file) {

		String path = PathFormat.format(file.getAbsolutePath());

		return UploadUtils.getPath(path);

	}

	private String[] getAllowFiles(Object fileExt) {

		String[] exts = null;
		String ext = null;

		if (fileExt == null) {
			return new String[0];
		}

		exts = (String[]) fileExt;

		for (int i = 0, len = exts.length; i < len; i++) {

			ext = exts[i];
			exts[i] = ext.replace(".", "");

		}

		return exts;

	}

	/**
	 * 暂时，不管删除什么都返回成功
	 * 
	 * @param fileName
	 * @return
	 */
	public State deleteFile(String fileName) {

		String delFileName = this.realSaveDir + fileName;
		File delFile = FileUtils.getFile(delFileName);

		MultiState state = new MultiState(true);
		BaseState fileState = null;

		if (delFile.exists()) {
			boolean delFlag = FileUtils.deleteQuietly(delFile);
			if (delFlag) {
				fileState = new BaseState(true);
				state.addState(fileState);
			}
		}
		return state;
	}
}
