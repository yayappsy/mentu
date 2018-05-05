package com.weimhc.framework.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * MoneyUtils.java
 *
 * @desc Money转换工具
 * @author Guoxp
 * @datatime Apr 7, 2013 3:47:51 P
 * 
 * @author szuo
 */
public class MoneyUtils {
	private static final Pattern AMOUNT_PATTERN = Pattern
			.compile("^(0|[1-9]\\d{0,11})\\.(\\d\\d)$"); // 不考虑分隔符的正确性
	private static final char[] RMB_NUMS = "零壹贰叁肆伍陆柒捌玖".toCharArray();
	private static final String[] UNITS = { "元", "角", "分", "整" };
	private static final String[] U1 = { "", "拾", "佰", "仟" };
	private static final String[] U2 = { "", "万", "亿" };

	/**
	 * 将金额（整数部分等于或少于12位，小数部分2位）转换为中文大写形式.
	 * 
	 * @param amount
	 *            金额数字
	 * @return 中文大写
	 * @throws IllegalArgumentException
	 */
	public static String convert(String amount)
			throws IllegalArgumentException {
		// 去掉分隔符
		amount = amount.replace(",", "");

		// 验证金额正确性
		if (amount.equals("0.00")) {
			throw new IllegalArgumentException("金额不能为零.");
		}
		Matcher matcher = AMOUNT_PATTERN.matcher(amount);
		if (!matcher.find()) {
			throw new IllegalArgumentException("输入金额有误.");
		}

		String integer = matcher.group(1); // 整数部分
		String fraction = matcher.group(2); // 小数部分

		String result = "";
		if (!integer.equals("0")) {
			result += integer2rmb(integer) + UNITS[0]; // 整数部分
		}
		if (fraction.equals("00")) {
			result += UNITS[3]; // 添加[整]
		} else if (fraction.startsWith("0") && integer.equals("0")) {
			result += fraction2rmb(fraction).substring(1); // 去掉分前面的[零]
		} else {
			result += fraction2rmb(fraction); // 小数部分
		}

		return result;
	}

	// 将金额小数部分转换为中文大写
	private static String fraction2rmb(String fraction) {
		char jiao = fraction.charAt(0); // 角
		char fen = fraction.charAt(1); // 分
		return (RMB_NUMS[jiao - '0'] + (jiao > '0' ? UNITS[1] : ""))
				+ (fen > '0' ? RMB_NUMS[fen - '0'] + UNITS[2] : "");
	}

	// 将金额整数部分转换为中文大写
	private static String integer2rmb(String integer) {
		StringBuilder buffer = new StringBuilder();
		// 从个位数开始转换
		int i, j;
		for (i = integer.length() - 1, j = 0; i >= 0; i--, j++) {
			char n = integer.charAt(i);
			if (n == '0') {
				// 当n是0且n的右边一位不是0时，插入[零]
				if (i < integer.length() - 1 && integer.charAt(i + 1) != '0') {
					buffer.append(RMB_NUMS[0]);
				}
				// 插入[万]或者[亿]
				if (j % 4 == 0) {
					if (i > 0 && integer.charAt(i - 1) != '0'
							|| i > 1 && integer.charAt(i - 2) != '0'
							|| i > 2 && integer.charAt(i - 3) != '0') {
						buffer.append(U2[j / 4]);
					}
				}
			} else {
				if (j % 4 == 0) {
					buffer.append(U2[j / 4]); // 插入[万]或者[亿]
				}
				buffer.append(U1[j % 4]); // 插入[拾]、[佰]或[仟]
				buffer.append(RMB_NUMS[n - '0']); // 插入数字
			}
		}
		return buffer.reverse().toString();
	}

	/**
	 * 对金额的格式调整到分
	 * 
	 * @param money
	 * @return
	 */
	public static String moneyFormat(String money) {// 23->23.00
		StringBuffer sb = new StringBuffer();
		if (money == null) {
			return "0.00";
		}
		int index = money.indexOf(".");
		if (index == -1) {
			return money + ".00";
		} else {
			String s0 = money.substring(0, index);// 整数部分
			String s1 = money.substring(index + 1);// 小数部分
			if (s1.length() == 1) {// 小数点后一位
				s1 = s1 + "0";
			} else if (s1.length() > 2) {// 如果超过3位小数，截取2位就可以了
				s1 = s1.substring(0, 2);
			}
			sb.append(s0);
			sb.append(".");
			sb.append(s1);
		}
		return sb.toString();
	}

	/**
	 * 格式化数字字符串为特定格式的字符串.
	 * 
	 * @param String
	 *            s 原有的数字字符串
	 * @param int
	 *            dot 后跟小数点位数
	 * @return String 返回特定格式的字符串
	 */
	public static String formatNumber(String s, int dot) {
		double d;
		if (s == null)
			return "";
		try {
			d = Double.parseDouble(s);
		} catch (NumberFormatException e) {
			// logger.error(e);
			return s;
		}
		NumberFormat nf = NumberFormat.getInstance();
		nf.setMinimumFractionDigits(dot);
		nf.setMaximumFractionDigits(dot);
		return nf.format(d);
	}

	/**
	 * 格式化double类型为特定格式的字符串.
	 * 
	 * @param double
	 *            d 双精度数字
	 * @param int
	 *            dot 后跟小数点位数
	 * @return String 返回特定格式的字符串
	 */
	public static String formatNumber(double d, int dot) {
		NumberFormat nf = NumberFormat.getInstance();
		nf.setMinimumFractionDigits(dot);
		nf.setMaximumFractionDigits(dot);
		return nf.format(d);
	}

	/**
	 * 格式化double类型为特定格式的.不带千位分隔符
	 * 
	 * @param double
	 *            d 双精度数字
	 * @param int
	 *            dot 后跟小数点位数
	 * @return String 返回特定格式的字符串
	 */
	public static String formatDecimal(double d, int dot) {
		if ("NaN".equals(Double.toString(d))) {
			return "NaN";
		}
		return new BigDecimal(Double.toString(d))
				.setScale(dot, BigDecimal.ROUND_HALF_UP).toString();

	}

	/**
	 * 格式化金额
	 */
	public static String formatMoney(double d) {
		return formatDecimal(d, 2);
	}

	/**
	 * 
	 * @param d
	 * @return
	 */
	public static boolean isZero(double d) {
		if (Math.abs(d) < 0.0000000001)
			return true;
		else
			return false;
	}

	/**
	 * 智能格式化 自动判断小数点后位数和是否万、百万、亿进位
	 * 
	 * @param d
	 * @return
	 */
	public static String formatMoney(String s) {
		String ret = null;
		double d = MoneyUtils.parseDouble(s);
		int i = (int) d;
		if (MoneyUtils.isZero(d - i * 1.0)) {
			if (i < 100) {
				return MoneyUtils.formatNumber(d, 0);
			}
			int[] m = { 100000000, // 亿
					10000000, // 千万
					1000000, // 百万
					10000, // 万
					1000, // 千
					100 // 百
			};
			boolean[] mf = { true, // 亿
					false, // 千万
					false, // 百万
					true, // 万
					false, // 千
					false // 百
			};
			String[] md = { "亿", "千万", "百万", "万", "千", "百" };
			for (int t = 0; t < m.length; t++) {
				if (i % m[t] == 0 && (mf[t] || i / m[t] < 10)) {
					return MoneyUtils.formatNumber(i / m[t], 0) + md[t];
				}
			}
			ret = MoneyUtils.formatNumber(d, 0);
		} else {
			ret = MoneyUtils.formatNumber(d, 2);
		}
		return ret;
	}

	/**
	 * 格式化利率
	 */
	public static String formatIntRate(double d) {
		return formatDecimal(d, 8);
	}

	/**
	 * 格式化double类型为特定格式的字符串.
	 * 
	 * @param double
	 *            d 双精度数字
	 * @param int
	 *            dot 后跟小数点位数
	 * @param int
	 *            type 0-不进行转换,1-百分数计算,2-除10000
	 * @return String 返回特定格式的字符串
	 */
	public static String formatNumber(String s, int dot, int type) {
		if (type == 0)
			return formatNumber(s, dot);
		if (type == 1) {
			double d;
			try {
				d = Double.parseDouble(s);
			} catch (NumberFormatException e) {
				// logger.error(e);
				return s;
			}
			d = d * 100;
			return formatNumber(d, dot);

		}
		if (type == 2) {
			double d;
			try {
				d = Double.parseDouble(s);
			} catch (NumberFormatException e) {
				// logger.error(e);
				return s;
			}
			d = d / 10000;
			return formatNumber(d, dot);

		}
		return s;
	}

	/**
	 * 格式化String为double型，如果字符串为空，返回0.
	 * 
	 * @param String
	 *            s 双精度数字
	 * @return double 返回double类型数值
	 */
	public static double parseDouble(String s) {
		double d = 0;
		if (s != null) {
			try {
				d = Double.parseDouble(s);
			} catch (NumberFormatException e) {
				try {
					String t = s.replaceAll(",", "");
					d = Double.parseDouble(t);
				} catch (NumberFormatException e2) {
					d = 0;
					// logger.error(e.getMessage());
				}
			}
		}
		return d;
	}

	/**
	 * 格式化String为float型，如果字符串为空，返回0.
	 * 
	 * @param String
	 *            s 双精度数字
	 * @return float 返回float类型数值
	 */
	public static float parseFloat(String s) {
		float f = 0;
		if (s != null) {
			try {
				f = Float.parseFloat(s);
			} catch (NumberFormatException e) {
				f = 0;
				// logger.error(e.getMessage());
			}
		}
		return f;
	}

	/**
	 * 格式化String为int型，如果字符串为空，返回0.
	 * 
	 * @param String
	 *            s 双精度数字
	 * @return int 返回int类型数值
	 */
	public static int parseInt(String s) {
		int i = 0;
		if (s != null) {
			try {
				i = Integer.parseInt(s);
			} catch (NumberFormatException e) {
				i = 0;
				double t = parseDouble(s);
				if (t != 0) {
					i = (int) t;
				}

				// logger.error(e.getMessage());
			}
		}
		return i;
	}

	/***
	 * 根据scale获取金额格式
	 * 
	 * @param money
	 * @param scale
	 * @return
	 */
	public static String getMoneyStr(BigDecimal money, int scale) {
		if (scale < 0) {
			scale = 0;
		}
		return money.setScale(scale, RoundingMode.HALF_DOWN).toString();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(MoneyUtils.getMoneyStr(new BigDecimal("100.01"), 0));
		System.out.println(MoneyUtils.convert("1000.00"));
	}

}
