package com.weimhc.modules.base.utils;

import java.text.DecimalFormat;
import java.util.List;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;
import com.thinkgem.javamg.common.utils.StringUtils;
import com.weimhc.modules.base.entity.Sn;
import com.weimhc.modules.base.entity.SnType;

public abstract class SnUtils {

	private static Logger logger = LoggerFactory.getLogger(SnUtils.class);

	/***
	 * 根据传入的编号规则生成新的编号
	 * 
	 * @param sn
	 * @return
	 */
	public static String generateSn(Sn sn) {

		StringBuilder generate = new StringBuilder();

		if (StringUtils.isNotBlank(sn.getPrefix())) {
			generate.append(sn.getPrefix());
		}

		generate.append(getSuffix(sn));
		logger.debug("生成流水号:" + sn.getSnType() + "," + generate.toString());
		return generate.toString();
	}

	private static String getSuffix(Sn sn) {
		StringBuilder suffix = new StringBuilder();

		sn.setLastValue(valueAddOne(sn.getLastValue(),
				sn.getSnType().getNumberFormatStr()));

		if (StringUtils.isNotBlank(sn.getFactor())) {
			suffix.append(sn.getFactor());
		}
		suffix.append(getCurrentDateStr(sn.getSnType().getDateFormatStr()));
		suffix.append(sn.getLastValue());

		return suffix.toString();
	}

	private static String valueAddOne(String value, String formatStr) {
		Integer lstValue = Integer.parseInt(value);
		lstValue++;
		DecimalFormat df = new DecimalFormat(formatStr);
		return df.format(lstValue);
	}

	public static String getCurrentDateStr(String fmtStr) {
		DateTime dateTime = DateTime.now();
		return dateTime.toString(fmtStr);
	}

	public static void main(String[] args) {
		List<Sn> sns = Lists.newArrayList();
		for (SnType snType : SnType.values()) {
			Sn sn = new Sn(snType);
			sn.setLastValue("01");
			sns.add(sn);
		}
		for (int i = 0; i < 10; i++) {
			for (Sn sn : sns) {
				System.out.println(generateSn(sn));
			}
		}

	}
}
