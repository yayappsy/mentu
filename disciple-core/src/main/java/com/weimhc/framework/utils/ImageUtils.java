package com.weimhc.framework.utils;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thinkgem.javamg.common.utils.FileUtils;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

public abstract class ImageUtils {

	private static Logger logger = LoggerFactory.getLogger(ImageUtils.class);

	public static final int DEFAULT_WIDTH = 100;
	public static final int DEFAULT_HEIGHT = 100;

	/***
	 * 根据文字添加水印
	 * 
	 * @param filePath
	 * @param printPath
	 * @param markContent
	 * @return
	 */
	public static boolean createMark(String filePath, String printPath,
			String markContent, int x, int y) {
		// 根据文字自定义出一张水印图片
		BufferedImage bi = new BufferedImage(64, 64,
				BufferedImage.TYPE_INT_RGB);
		Graphics2D g = bi.createGraphics();
		g.setColor(Color.LIGHT_GRAY);
		g.drawRect(0, 0, 64, 64);
		char[] data = markContent.toCharArray();
		g.drawChars(data, 0, data.length, 5, 32);
		try {
			Thumbnails.of(filePath).watermark(Positions.BOTTOM_RIGHT, bi, 0.5f)
					.outputQuality(0.8f).toFile(printPath);
		} catch (IOException e) {
			logger.error(e.getMessage());
			return false;
		}

		return true;
	}

	public static BufferedImage getTextMarkImage(String markContent,
			String fontName, int fontStyle, int fontSize, Color color,
			float alpha) {
		BufferedImage bufferedImage = null;
		try {
			int width = 64;
			int height = 64;
			bufferedImage = new BufferedImage(64, 64,
					BufferedImage.TYPE_INT_RGB);
			Graphics2D g = bufferedImage.createGraphics();
			g.drawRect(0, 0, width, height);
			g.setFont(new Font(fontName, fontStyle, fontSize));
			g.setColor(color);
			g.setComposite(
					AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));

			g.drawString(markContent, 0, 0);
			g.dispose();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);;
		}
		return bufferedImage;
	}

	/**
	 * 获取字符长度，一个汉字作为 1 个字符, 一个英文字母作为 0.5 个字符
	 * 
	 * @param text
	 * @return 字符长度，如：text="中国",返回 2；text="test",返回 2；text="中国ABC",返回 4.
	 */
	public static int getLength(String text) {
		int textLength = text.length();
		int length = textLength;
		for (int i = 0; i < textLength; i++) {
			if (String.valueOf(text.charAt(i)).getBytes().length > 1) {
				length++;
			}
		}
		return (length % 2 == 0) ? length / 2 : length / 2 + 1;
	}

	/**
	 * 添加默认水印
	 * 
	 * @param filePath
	 * @param printPath
	 * @return
	 */
	public static boolean createMark(String filePath, String printPath) {

		try {
			Thumbnails.of(filePath)
					.watermark(Positions.BOTTOM_RIGHT,
							ImageIO.read(new File("E:\\test\\02.png")), 0.5f)
					.outputQuality(0.8f).toFile(printPath);
		} catch (IOException e) {
			logger.error(e.getMessage());
			return false;
		}

		return true;
	}

	/**
	 * 按照默认大小压缩图片 会自动创建缩略图文件夹
	 * 
	 * @param photoPath
	 * 
	 */
	public static void createThumbnail(String photoPath) {
		createThumbnail(photoPath, DEFAULT_WIDTH, DEFAULT_HEIGHT);
	}

	/**
	 * 创建缩略图 会自动创建缩略图文件夹
	 * 
	 * @param photoPath
	 *            真实文件路径
	 * 
	 * @param width
	 * @param height
	 */
	public static void createThumbnail(String photoPath, int width,
			int height) {
		String thumbnail = UploadUtils.getThumbnailPath(photoPath,
				ImageUtils.DEFAULT_WIDTH, ImageUtils.DEFAULT_HEIGHT);
		File file = new File(thumbnail);
		if (!file.exists()) {
			if (file.getParentFile().exists()
					|| FileUtils.createDirectory(file.getParent())) {
				try {
					Thumbnails.of(photoPath).size(width, height)
							.toFile(thumbnail);
					logger.debug("图片缩略图： " + thumbnail + " 创建成功！");
				} catch (IOException e) {
					logger.error(e.getMessage());
				}
			}
		} else {
			logger.debug("图片缩略图： " + thumbnail + " 已存在！");
		}
	}

}
