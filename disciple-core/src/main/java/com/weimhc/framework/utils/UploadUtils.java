package com.weimhc.framework.utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thinkgem.javamg.common.config.Global;
import com.thinkgem.javamg.common.security.BasePrincipal;
import com.thinkgem.javamg.common.utils.FileUtils;
import com.thinkgem.javamg.common.utils.StringUtils;

/****
 * 定义所有文件（图片）上传的根路径，以及返回地址
 * 
 * @author szuo
 *
 */
public abstract class UploadUtils {

	private static Logger logger = LoggerFactory.getLogger(UploadUtils.class);

	/**
	 * 上传缓存
	 */
	public static final String UPLOAD_CACHE = "uploadCache";

	/**
	 * 上传路径缓存,根据
	 */
	public static final String CACHE_UPLOAD_PATH_MAP = "pathMap";

	/**
	 * 上传文件基础虚拟路径
	 */
	public static final String USERFILES_BASE_URL = "/userfiles/";

	/**
	 * 图片路基
	 */
	public static final String THUMBNAIL_DIR = "thumbnail/";

	/** 路径分隔符 */
	public static final String PATH_SEPARATOR = "/";
	/***
	 * 多个文件 路径分隔符
	 */
	public static final String MULTIPART_FILE_SEPARATOR = "|";
	/**
	 * 文件存储根路径
	 */
	public static final String BASE_DIR = Global.getConfig("userfiles.baseDir");

	/**
	 * 文件访问url路径
	 */
	public static final String BASE_URL = Global.getConfig("userfiles.baseURL");

	/**
	 * 缩略图访问url路径
	 */
	public static final String THUMBNAIL_BASE_URL = Global.getConfig("userfiles.baseURL")
			+ PATH_SEPARATOR + THUMBNAIL_DIR;

	/**
	 * 获取上传文件的根目录,通过shiro获取每个用户的文件夹
	 * 
	 * @return
	 */
	public static String getUserfilesBaseDir() {

		Subject subject = SecurityUtils.getSubject();
		BasePrincipal principal = (BasePrincipal) subject.getPrincipal();

		return getUserfilesBaseDir(principal.getId());
	}

	/**
	 * 获取上传文件的根目录 根据会员或用户的Id
	 * 
	 * @param id
	 *            会员或用户的Id
	 * @return
	 */
	public static String getUserfilesBaseDir(String id) {
		String dir = BASE_DIR;
		if (StringUtils.isBlank(dir)) {
			try {
				dir = ServletContextFactory.getServletContext().getRealPath(PATH_SEPARATOR);
			} catch (Exception e) {
				return "";
			}
		}

		dir = dir + PATH_SEPARATOR + id;

		if (!dir.endsWith(PATH_SEPARATOR)) {
			dir += PATH_SEPARATOR;
		}
		logger.debug("userfiles.basedir: " + dir);
		dir = FileUtils.path(dir);
		if (!(new File(dir)).exists()) {
			FileUtils.createDirectory(dir);
		}
		return dir;
	}

	/**
	 * 获取存放缩略图的根目录,通过shiro获取每个用户的文件夹
	 * 
	 * 
	 * @return
	 */
	public static String getUserfilesThumbnailBaseDir() {

		Subject subject = SecurityUtils.getSubject();
		BasePrincipal principal = (BasePrincipal) subject.getPrincipal();

		return getUserfilesThumbnailBaseDir(principal.getId());
	}

	/**
	 * 获取上传文件的根目录 根据会员或用户的Id
	 * 
	 * @param id
	 *            会员或用户的Id
	 * @return
	 */
	public static String getUserfilesThumbnailBaseDir(String id) {
		String dir = BASE_DIR;
		if (StringUtils.isBlank(dir)) {
			try {
				dir = ServletContextFactory.getServletContext().getRealPath(PATH_SEPARATOR);
			} catch (Exception e) {
				return "";
			}
		}

		dir += PATH_SEPARATOR + THUMBNAIL_DIR + id;

		if (!dir.endsWith(PATH_SEPARATOR)) {
			dir += PATH_SEPARATOR;
		}
		logger.debug("userfiles.basedir: " + dir);
		dir = FileUtils.path(dir);
		return dir;
	}

	/**
	 * 获取上传文件的访问路径 ,通过shiro获取
	 * 
	 * @return
	 */
	public static String getUserfilesBaseURL() {

		Subject subject = SecurityUtils.getSubject();
		BasePrincipal principal = (BasePrincipal) subject.getPrincipal();

		return getUserfilesBaseURL(principal.getId());
	}

	/**
	 * 获取上传文件的根目录 根据会员或用户的Id
	 * 
	 * @param id
	 *            会员或用户的Id
	 * @return
	 */
	public static String getUserfilesBaseURL(String id) {
		String baseURL = BASE_URL;
		if (StringUtils.isBlank(baseURL)) {
			baseURL = USERFILES_BASE_URL;
		}

		baseURL = baseURL + PATH_SEPARATOR + id;

		return baseURL;
	}

	/***
	 * 获取保存到数据库中的路径 去除根路径（BASE_DIR）之外的路径
	 * 
	 * @param filePath
	 * @return
	 */
	public static String getPath(String filePath) {

		String path = FileUtils.path(filePath);

		return path.replace(BASE_DIR, "");

	}

	/***
	 * 获取缩略图存储路径 增加前缀
	 * 
	 * @param originalPath
	 *            比如 D:\\tmp\\userfiles\\1.jpg
	 * @param width
	 *            100
	 * @param height
	 *            100
	 * @return 缩略图存储路径 D:\\tmp\\userfiles\\thumbnail\\100x100\\1.jpg
	 */
	public static String getThumbnailPath(String originalPath, int width, int height) {
		StringBuilder urls = new StringBuilder(BASE_DIR);
		urls.append(UploadUtils.PATH_SEPARATOR);
		urls.append(THUMBNAIL_DIR);
		urls.append(width);
		urls.append("x");
		urls.append(height);
		urls.append(FileUtils.path(originalPath).replace(FileUtils.path(BASE_DIR), ""));
		return FileUtils.path(urls.toString());

	}

	/***
	 * 获取缩略图存储路径 增加前缀 /thumbnail/100x100/
	 * 
	 * @param originalPath
	 *            正常图片路径
	 * @return
	 */
	public static String getThumbnailPath(String originalPath) {
		return getThumbnailPath(originalPath, 100, 100);

	}

	/**
	 * 获取可直接通过浏览器访问的路径
	 * 
	 * @return
	 */
	public static String getAccessUrl(String url) {
		return getAccessUrl(url, false, 0, 0);
	}

	/**
	 * 获取可直接通过浏览器访问的路径
	 * 
	 * @return
	 */
	private static String getAccessUrl(String url, boolean ifThumbnail, int width, int height) {
		if (StringUtils.isBlank(url)) {
			return "";
		} else if (StringUtils.startsWith(url, "http")) {
			return url;
		}
		if (ifThumbnail) {
			url = getThumbnailPath(url, width, height);
		}
		return BASE_URL + url.replace(BASE_DIR, "");
	}

	/**
	 * 获取可直接通过浏览器访问的缩略图路径 100x100
	 * 
	 * @return
	 */
	public static String getAccessThumbnailUrl(String url) {
		return getAccessThumbnailUrl(url, 100, 100);
	}

	/**
	 * 获取可直接通过浏览器访问的缩略图路径
	 * 
	 * @return
	 */
	public static String getAccessThumbnailUrl(String url, int width, int height) {
		return getAccessUrl(url, true, width, height);
	}

	public static void main(String[] args) {
		System.out.println(getThumbnailPath(
				"/8406ba189d02466884d894a8325b43b8/pd/image/1459311361728008842.jpg", 100, 100));
	}

	/**
	 * 构建文件保存路径
	 * 
	 * @param authenticationId
	 *            用户标志id
	 * @param uploadFolder
	 *            上传文件夹
	 * @return
	 */
	public static String createPathDir(String authenticationId, String uploadFolder) {
		/** 构建图片保存的目录 **/
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyyMMddHH");
		String pathDir = getUserfilesBaseDir(authenticationId) + uploadFolder
				+ dateformat.format(new Date());
		pathDir = FileUtils.path(pathDir);

		FileUtils.createDirectory(pathDir);

		return pathDir;

	}

}
