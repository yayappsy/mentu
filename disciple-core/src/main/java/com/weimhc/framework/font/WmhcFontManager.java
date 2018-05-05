
package com.weimhc.framework.font;

import java.awt.Font;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.simpleimage.font.FontLoader;
import com.thinkgem.javamg.common.utils.SystemPath;

/**
 * 
 * 
 * @author laozh
 * @version 2016年7月25日
 */
public class WmhcFontManager {

	private static final Map<String, FontLoader> caches = new HashMap<String, FontLoader>();

	private static final String DEFAULT_FONT_NAME = "方正黑体";
	static {
		FontLoader fontLoader = new WmhcFontLoader(DEFAULT_FONT_NAME, new File(
				SystemPath.getClassPath() + "META-INF/fonts/founderblack.ttf")
						.getAbsolutePath());
		caches.put(DEFAULT_FONT_NAME, fontLoader);

		FontLoader chinafontLoader = new WmhcFontLoader(DEFAULT_FONT_NAME,
				new File(SystemPath.getClassPath()
						+ "META-INF/fonts/chinaxingkai.ttf").getAbsolutePath());
		caches.put("华文行楷", chinafontLoader);
	}

	public static Font getFont(String name) {
		FontLoader loader = caches.get(name);
		if (loader == null) {
			return null;
		}

		return loader.getFont();
	}
}
