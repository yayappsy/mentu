
package com.weimhc.framework.font;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import com.alibaba.simpleimage.font.FontLoader;

/**
 * 字体加载类
 * 
 * @author laozh
 * @version 2016年7月25日
 */
public class WmhcFontLoader extends FontLoader {

	public WmhcFontLoader(String name, int fontType, String path) {
		super(name, fontType, path);
	}

	public WmhcFontLoader(String name, String path) {
		this(name, Font.TRUETYPE_FONT, path);
	}

	@Override
	protected Font loadFont(int fontType, String path) {
		InputStream fontStream = null;
		try {
			fontStream = new FileInputStream(new File(path));

			return Font.createFont(fontType, fontStream);
		} catch (FontFormatException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			if (fontStream != null) {
				try {
					fontStream.close();
				} catch (IOException ignore) {

				}
			}
		}
	}
}
