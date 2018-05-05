/**
 * 
 */
package com.weimhc.framework.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thinkgem.javamg.common.utils.StringUtils;

/**
 * @author szuo
 *
 */
public abstract class EncodingConverterUtils {

	private static Logger logger = LoggerFactory
			.getLogger(EncodingConverterUtils.class);

	/**
	 * 获取模板所在路径
	 * 
	 * @return
	 */
	public static String getSourthPath() {
		return "D:/projects/workspace/shop/weimhc-shops/src/main/webapp/WEB-INF/language/";
	}

	/**
	 * 获取模板所在路径
	 * 
	 * @return
	 */
	public static String getTargetPath() {
		return "D:/projects/workspace/shop/weimhc-shops/src/main/webapp/WEB-INF/utf/";
	}

	/**
	 * 获取模板所在路径
	 * 
	 * @return
	 */
	public static String getReversPath() {
		return "D:/projects/workspace/shop/weimhc-shops/src/main/webapp/WEB-INF/revers/";
	}

	/**
	 * main方法入口
	 * 
	 * @param args
	 *            args[0] 给定需要转换的文件夹 args[1] 指定需要转换的编码，如utf-8等
	 */
	public static void main(String[] args) {

		// 暂时用src_path替换args[0]，encoding_name替换arg[1]
		String encoding_name = "utf-8";
		EncodingConverterUtils.convertEncode(getSourthPath(), getReversPath(),
				encoding_name, true);

	}

	public static void convertEncode(String sourceFloder, String targetFloder,
			String encodingName, boolean reverse) {

		File target = new File(targetFloder);
		if (!target.exists()) {
			target.mkdirs();
		}
		File file = new File(sourceFloder);
		String[] files = file.list();
		if (files != null) {

			for (String fileName : files) {
				if (fileName.indexOf('.') == -1) { // 表明这是个子目录，回归调用此函数
					convertEncode(file.getAbsolutePath() + "\\" + fileName,
							targetFloder + "\\" + fileName, encodingName,
							reverse);
				} else {
					if (fileName.endsWith("properties")) { // 只处理以Java结尾的文件
						doConvertEncode(
								file.getAbsolutePath() + "\\" + fileName,
								targetFloder + "\\" + fileName, encodingName,
								reverse);
					}
				}
			}
		}
	}

	/**
	 * 完成具体的编码转换工作
	 * 
	 * @param inputFile
	 *            输入文件
	 * @param outputFile
	 *            输出文件
	 * @param encoding_name
	 *            需要转成的编码格式
	 */
	public static void doConvertEncode(String inputFile, String outputFile,
			String encoding_name, boolean reverse) {
		Runtime rt = Runtime.getRuntime();
		String exeCmd = null;
		if (reverse) {
			String[] cmd = { "native2ascii.exe", "-reverse", "-encoding",
					encoding_name, inputFile, outputFile };
			exeCmd = StringUtils.join(cmd, " ");
		} else {
			String[] cmd = { "native2ascii.exe", "-encoding", encoding_name,
					inputFile, outputFile };
			exeCmd = StringUtils.join(cmd, " ");
		}

		System.out.println("Execing convert command for " + inputFile + " ...");
		System.out.println("outputFile " + outputFile + " ...");

		try {
			Process proc = rt.exec(exeCmd);

			// any error message?
			StreamGobbler errorGobbler = new StreamGobbler(
					proc.getErrorStream(), "ERROR");

			// any output?
			StreamGobbler outputGobbler = new StreamGobbler(
					proc.getInputStream(), "OUTPUT");

			// kick them off
			errorGobbler.start();
			outputGobbler.start();

			// any error???
			int exitVal = proc.waitFor();
			System.out.println("ExitValue: " + exitVal);

		} catch (Throwable e) {
			logger.error(e.getMessage(), e);
		}
	}

}

class StreamGobbler extends Thread {

	InputStream is;
	String type;

	StreamGobbler(InputStream is, String type) {
		this.is = is;
		this.type = type;
	}

	@Override
	public void run() {
		try {
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			String line = null;
			while ((line = br.readLine()) != null)
				System.out.println(type + ">" + line);
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
}