package com.weimhc.framework.ueditor.upload;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baidu.ueditor.PathFormat;
import com.baidu.ueditor.define.AppInfo;
import com.baidu.ueditor.define.BaseState;
import com.baidu.ueditor.define.FileType;
import com.baidu.ueditor.define.State;
import com.baidu.ueditor.upload.StorageManager;
import com.weimhc.framework.utils.FileTypeUtils;
import com.weimhc.framework.utils.ImageUtils;
import com.weimhc.framework.utils.UploadUtils;

public class BinaryUploader {

	private static Logger logger = LoggerFactory.getLogger(BinaryUploader.class);

	public static final State save(HttpServletRequest request, Map<String, Object> conf) {
		FileItemStream fileStream = null;
		boolean isAjaxUpload = request.getHeader("X_Requested_With") != null;

		if (!ServletFileUpload.isMultipartContent(request)) {
			return new BaseState(false, AppInfo.NOT_MULTIPART_CONTENT);
		}

		ServletFileUpload upload = new ServletFileUpload(new DiskFileItemFactory());

		if (isAjaxUpload) {
			upload.setHeaderEncoding("UTF-8");
		}

		try {
			FileItemIterator iterator = upload.getItemIterator(request);

			while (iterator.hasNext()) {
				fileStream = iterator.next();

				if (!fileStream.isFormField())
					break;
				fileStream = null;
			}

			if (fileStream == null) {
				return new BaseState(false, AppInfo.NOTFOUND_UPLOAD_DATA);
			}

			// 包括存储文件的名称以及后缀，不包括根文件路径
			String savePath = (String) conf.get("savePath");
			String originFileName = fileStream.getName();
			String suffix = FileType.getSuffixByFilename(originFileName);

			// 保存文件的位置
			String saveDir = UploadUtils.getUserfilesBaseDir() + (String) conf.get("uploadFolder");

			originFileName = originFileName.substring(0, originFileName.length() - suffix.length());
			savePath = savePath + suffix;

			long maxSize = ((Long) conf.get("maxSize")).longValue();

			if (!validType(suffix, (String[]) conf.get("allowFiles"))) {
				return new BaseState(false, AppInfo.NOT_ALLOW_FILE_TYPE);
			}

			savePath = PathFormat.parse(savePath, originFileName);

			// String physicalPath = (String) conf.get("rootPath") + savePath;
			String physicalPath = saveDir + savePath;

			InputStream is = fileStream.openStream();
			State storageState = StorageManager.saveFileByInputStream(is, physicalPath, maxSize);
			is.close();

			String filePath = UploadUtils.getPath(PathFormat.format(physicalPath));

			if (storageState.isSuccess()) {
				storageState.putInfo("url", UploadUtils.getAccessUrl(filePath));
				storageState.putInfo("filePath", filePath);
				storageState.putInfo("type", suffix);
				storageState.putInfo("original", originFileName + suffix);
				if (FileTypeUtils.isImageFile(physicalPath)) {
					ImageUtils.createThumbnail(physicalPath);
					int imgWidth = 100;
					int imgHeight = 100;
					BufferedImage sourceImg;
					try {
						sourceImg = ImageIO.read(new FileInputStream(physicalPath));
						imgWidth = sourceImg.getWidth();
						imgHeight = sourceImg.getHeight();
					} catch (IOException e) {
						logger.error("获取图片宽度和高度错误", e);
					}
					storageState.putInfo("thumbnail", UploadUtils.getAccessThumbnailUrl(filePath));
					storageState.putInfo("thumbPath", UploadUtils.getThumbnailPath(filePath));
					storageState.putInfo("imgWidth", imgWidth);
					storageState.putInfo("imgHeight", imgHeight);
					storageState.putInfo("ext", suffix);
				}

			}

			return storageState;
		} catch (FileUploadException e) {
			return new BaseState(false, AppInfo.PARSE_REQUEST_ERROR);
		} catch (IOException e) {
		}
		return new BaseState(false, AppInfo.IO_ERROR);
	}

	private static boolean validType(String type, String[] allowTypes) {
		List<String> list = Arrays.asList(allowTypes);

		return list.contains(type);
	}

}
