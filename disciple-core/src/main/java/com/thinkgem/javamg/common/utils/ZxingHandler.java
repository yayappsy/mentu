package com.thinkgem.javamg.common.utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

/**
 * 条形码和二维码编码解码
 * 
 * 
 * @version 2014-02-28
 */
public class ZxingHandler {

	private static Logger logger = LoggerFactory.getLogger(WorkDayUtils.class);

	private static final int BLACK = 0xFF000000;
	private static final int WHITE = 0xFFFFFFFF;

	/**
	 * 条形码编码
	 * 
	 * @param contents
	 * @param width
	 * @param height
	 * @param imgPath
	 */
	public static void encode(String contents, int width, int height,
			String imgPath) {
		int codeWidth = 3 + // start guard
				(7 * 6) + // left bars
				5 + // middle guard
				(7 * 6) + // right bars
				3; // end guard
		codeWidth = Math.max(codeWidth, width);
		try {
			BitMatrix bitMatrix = new MultiFormatWriter().encode(contents,
					BarcodeFormat.EAN_13, codeWidth, height, null);

			MatrixToImageWriter.writeToFile(bitMatrix, "png",
					new File(imgPath));

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * 条形码解码
	 * 
	 * @param imgPath
	 * @return String
	 */
	public static String decode(String imgPath) {
		BufferedImage image = null;
		Result result = null;
		try {
			image = ImageIO.read(new File(imgPath));
			if (image == null) {
				System.out.println("the decode image may be not exit.");
			}
			LuminanceSource source = new BufferedImageLuminanceSource(image);
			BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

			result = new MultiFormatReader().decode(bitmap, null);
			return result.getText();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}

	/**
	 * 二维码编码
	 * 
	 * @param contents
	 * @param width
	 * @param height
	 * @param imgPath
	 */
	public static void encode2(String contents, int width, int height,
			String imgPath) {
		Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
		// 指定纠错等级
		hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
		// 指定编码格式
		hints.put(EncodeHintType.CHARACTER_SET, "GBK");
		try {
			BitMatrix bitMatrix = new MultiFormatWriter().encode(contents,
					BarcodeFormat.QR_CODE, width, height, hints);

			MatrixToImageWriter.writeToFile(bitMatrix, "png",
					new File(imgPath));

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * 二维码解码
	 * 
	 * @param imgPath
	 * @return String
	 */
	public static String decode2(String imgPath) {
		BufferedImage image = null;
		Result result = null;
		try {
			image = ImageIO.read(new File(imgPath));
			if (image == null) {
				System.out.println("the decode image may be not exit.");
			}
			LuminanceSource source = new BufferedImageLuminanceSource(image);
			BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

			Hashtable<DecodeHintType, Object> hints = new Hashtable<DecodeHintType, Object>();
			hints.put(DecodeHintType.CHARACTER_SET, "GBK");

			result = new MultiFormatReader().decode(bitmap, hints);
			return result.getText();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}

	private static BufferedImage toBufferedImage(BitMatrix matrix) {
		int width = matrix.getWidth();
		int height = matrix.getHeight();
		BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				image.setRGB(x, y, matrix.get(x, y) ? BLACK : WHITE);
			}
		}
		return image;
	}

	private static void writeToFile(BitMatrix matrix, String format, File file)
			throws IOException {
		BufferedImage image = toBufferedImage(matrix);
		if (!ImageIO.write(image, format, file)) {
			throw new IOException("Could not write an image of format " + format
					+ " to " + file);
		}
	}

	/**
	 * 将内容contents生成长宽均为width的图片，图片路径由imgPath指定
	 */
	public static File getQRCodeImge(String contents, int width,
			String imgPath) {
		return getQRCodeImge(contents, width, width, imgPath);
	}

	/**
	 * 将内容contents生成长为width，宽为width的图片，图片路径由imgPath指定
	 */
	public static File getQRCodeImge(String contents, int width, int height,
			String imgPath) {
		try {
			Map<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
			hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
			hints.put(EncodeHintType.CHARACTER_SET, "UTF8");

			BitMatrix bitMatrix = new MultiFormatWriter().encode(contents,
					BarcodeFormat.QR_CODE, width, height, hints);
			File imageFile = new File(imgPath);
			if (!imageFile.exists()) {
				FileUtils.createFile(imgPath);
			}
			writeToFile(bitMatrix, "png", imageFile);

			return imageFile;

		} catch (Exception e) {
			logger.error("create QR code error!", e);
			return null;
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		// 条形码
		String imgPath = "target\\zxing_EAN13.png";
		String contents = "6923450657713";
		int width = 105, height = 50;

		ZxingHandler.encode(contents, width, height, imgPath);
		System.out.println("finished zxing EAN-13 encode.");

		String decodeContent = ZxingHandler.decode(imgPath);
		System.out.println("解码内容如下：" + decodeContent);
		System.out.println("finished zxing EAN-13 decode.");

		// 二维码
		String imgPath2 = "target\\zxing.png";
		String contents2 = "Hello Gem, welcome to Zxing!"
				+ "\nBlog [ http://www.baidu.com ]"
				+ "\nEMail [ testjavamg@163.com ]";
		int width2 = 300, height2 = 300;

		ZxingHandler.encode2(contents2, width2, height2, imgPath2);
		System.out.println("finished zxing encode.");

		String decodeContent2 = ZxingHandler.decode2(imgPath2);
		System.out.println("解码内容如下：" + decodeContent2);
		System.out.println("finished zxing decode.");
	}

}