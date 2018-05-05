package com.weimhc.framework.ueditor.upload;

import java.util.Map;

import org.apache.commons.codec.binary.Base64;

import com.baidu.ueditor.PathFormat;
import com.baidu.ueditor.define.AppInfo;
import com.baidu.ueditor.define.BaseState;
import com.baidu.ueditor.define.FileType;
import com.baidu.ueditor.define.State;
import com.baidu.ueditor.upload.StorageManager;
import com.weimhc.framework.utils.UploadUtils;

public final class Base64Uploader {

	public static State save(String content, Map<String, Object> conf) {

		byte[] data = decode(content);

		long maxSize = ((Long) conf.get("maxSize")).longValue();

		if (!validSize(data, maxSize)) {
			return new BaseState(false, AppInfo.MAX_SIZE);
		}

		String suffix = FileType.getSuffix("JPG");

		String savePath = PathFormat.parse((String) conf.get("savePath"),
				(String) conf.get("filename"));

		savePath = savePath + suffix;

		//保存文件的位置
		String saveDir = UploadUtils.getUserfilesBaseDir()
				+ (String) conf.get("uploadFolder");

		String physicalPath = saveDir + savePath;

		State storageState = StorageManager.saveBinaryFile(data, physicalPath);

		String filePath = UploadUtils.getPath(PathFormat.format(physicalPath));

		if (storageState.isSuccess()) {
			storageState.putInfo("url",
					UploadUtils.getUserfilesBaseURL() + filePath);
			storageState.putInfo("filePath", filePath);
			storageState.putInfo("type", suffix);
			storageState.putInfo("original", "");
		}

		return storageState;
	}

	private static byte[] decode(String content) {
		return Base64.decodeBase64(content);
	}

	private static boolean validSize(byte[] data, long length) {
		return data.length <= length;
	}

}