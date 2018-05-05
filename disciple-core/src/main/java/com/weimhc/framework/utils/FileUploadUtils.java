package com.weimhc.framework.utils;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.baidu.ueditor.PathFormat;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.thinkgem.javamg.common.utils.IdGen;
import com.thinkgem.javamg.common.utils.StringUtils;

/**
 * 文件上传工具类
 * 
 * @author szuo
 *
 */
public abstract class FileUploadUtils {

	/**
	 * 日志对象
	 */
	protected static Logger logger = LoggerFactory.getLogger(FileUploadUtils.class);

	/***
	 * 处理图片上传 解析多个图片
	 * 
	 * @param request
	 * @param fileParamterName
	 *            html中 file的名称
	 * @param width
	 *            压缩图片宽度
	 * @param height
	 *            压缩图片高度
	 * @return
	 * @throws IOException
	 */
	public static List<Map<String, Object>> processImages(HttpServletRequest request,
			String fileParamterName, int width, int height, String authenticationId)
			throws IOException {

		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;

		// 获取上传图片存储路径
		String uploadFolder = multipartRequest.getParameter("uploadFolder");
		if (StringUtils.isBlank(uploadFolder)) {
			uploadFolder = "default/images/";
		}

		// 创建文件目录
		String pathDir = UploadUtils.createPathDir(authenticationId, uploadFolder);

		List<Map<String, Object>> results = Lists.newArrayList();
		/** 页面控件的文件流 **/
		List<MultipartFile> multipartFiles = multipartRequest.getFiles(fileParamterName);

		String fileName = null;
		File file = null;
		String suffix = null;
		String imageName = null;
		MultipartFile multipartFile = null;
		for (int i = 0; i < multipartFiles.size(); i++) {
			multipartFile = multipartFiles.get(i);

			if (multipartFile.getSize() <= 0) {
				return null;
			}

			/** 获取文件的后缀 **/
			suffix = multipartFile.getOriginalFilename()
					.substring(multipartFile.getOriginalFilename().lastIndexOf("."));

			// **使用UUID生成文件名称**/
			imageName = IdGen.uuidByIdWorker() + suffix;// multipartFile.getOriginalFilename();

			file = new File(pathDir, imageName);
			try {
				multipartFile.transferTo(file);

			} catch (IllegalStateException e) {
				logger.error(e.getMessage());
				continue;
			}

			fileName = file.getAbsolutePath();
			// 生成缩略图
			ImageUtils.createThumbnail(fileName, width, height);

			// 获取文件信息
			Map<String, Object> result = getStorageInfo(fileName, true, width, height);

			results.add(result);
		}
		return results;
	}

	/**
	 * 根据文件路径 获取信息
	 * 
	 * @param fileName
	 * @param width
	 * @param height
	 * @return
	 */
	private static Map<String, Object> getStorageInfo(String fileName, boolean isImage, int width,
			int height) {
		String filePath = UploadUtils.getPath(PathFormat.format(fileName));
		Map<String, Object> result = Maps.newHashMap();
		result.put("url", UploadUtils.getAccessUrl(filePath));
		result.put("filePath", filePath);
		result.put("result", "success");
		if (isImage) {
			result.put("thumbnail", UploadUtils.getAccessThumbnailUrl(filePath, width, height));
			result.put("smallPath", UploadUtils.getThumbnailPath(filePath, width, height));
			result.put("smallFileName", UploadUtils.getThumbnailPath(fileName, width, height));
		}
		return result;
	}

	/**
	 * 根据文件路径 获取信息
	 * 
	 * @param fileName
	 * @param width
	 * @param height
	 * @return
	 */
	private static Map<String, Object> getFileStorageInfo(String fileName) {
		return getStorageInfo(fileName, false, 0, 0);
	}

	/***
	 * 
	 * @param request
	 * @param fileParamterName
	 *            html中 file的名称
	 * @return
	 * @throws IOException
	 */
	public static List<Map<String, Object>> processImages(HttpServletRequest request,
			String fileParamterName, String authenticationId) throws IOException {
		return processImages(request, fileParamterName, ImageUtils.DEFAULT_WIDTH,
				ImageUtils.DEFAULT_HEIGHT, authenticationId);
	}

	/***
	 * 处理文件上传 解析多个文件
	 * 
	 * @param request
	 * @param fileParamterName
	 *            html中 file的名称
	 * @return
	 * @throws IOException
	 */
	public static List<Map<String, Object>> processFiles(HttpServletRequest request,
			String fileParamterName, String authenticationId) throws IOException {

		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;

		// 获取上传图片存储路径
		String uploadFolder = multipartRequest.getParameter("uploadFolder");
		if (StringUtils.isBlank(uploadFolder)) {
			uploadFolder = "default/files/";
		} else {
			uploadFolder += "/files/";
		}

		String pathDir = UploadUtils.createPathDir(authenticationId, uploadFolder);

		List<Map<String, Object>> results = Lists.newArrayList();

		/** 页面控件的文件流 **/
		List<MultipartFile> multipartFiles = multipartRequest.getFiles(fileParamterName);
		String fileName = null;
		for (int i = 0; i < multipartFiles.size(); i++) {
			MultipartFile multipartFile = multipartFiles.get(i);

			if (multipartFile.getSize() <= 0) {
				return null;
			}

			/** 拼成完整的文件保存路径加文件 **/
			String logName = multipartFile.getOriginalFilename();
			File file = new File(pathDir, logName);
			try {
				multipartFile.transferTo(file);
			} catch (IllegalStateException e) {
				logger.error(e.getMessage(), e);
				continue;
			}

			fileName = file.getAbsolutePath();
			Map<String, Object> result = getFileStorageInfo(fileName);
			results.add(result);
		}
		return results;
	}

	/***
	 * 文件上传 解析多个文件
	 * 
	 * @param request
	 * @param fileParamterName
	 *            html中 file的名称
	 * @param authenticationId
	 *            会员
	 * @param uploadType
	 *            TODO
	 * @return
	 * @throws IOException
	 */
	public static List<Map<String, Object>> processUpload(HttpServletRequest request,
			String fileParamterName, String authenticationId, UploadType uploadType)
			throws IOException {

		List<Map<String, Object>> results = Lists.newArrayList();
		if (ServletFileUpload.isMultipartContent(request)) {
			if (UploadType.image.equals(uploadType)) {
				results = processImages(request, fileParamterName, ImageUtils.DEFAULT_WIDTH,
						ImageUtils.DEFAULT_HEIGHT, authenticationId);
			} else if (UploadType.file.equals(uploadType)) {
				results = processImages(request, fileParamterName, ImageUtils.DEFAULT_WIDTH,
						ImageUtils.DEFAULT_HEIGHT, authenticationId);
			}
		}

		return results;
	}

	/**
	 * 获取上传的文件路径 以;为分隔符
	 * 
	 * @param results
	 * @return
	 */
	public static String getFilePath(List<Map<String, Object>> results) {
		String filepath = "";
		for (Map<String, Object> result : results) {
			filepath += result.get("filePath").toString() + UploadUtils.MULTIPART_FILE_SEPARATOR;
		}
		// 去除最后一个分隔符
		filepath = StringUtils.removeEnd(filepath, UploadUtils.MULTIPART_FILE_SEPARATOR);
		return filepath;
	}
}
