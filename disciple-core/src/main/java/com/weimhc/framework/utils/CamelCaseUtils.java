/**
 * 
 */
package com.weimhc.framework.utils;

import com.thinkgem.javamg.common.utils.StringUtils;

/**
 * @author szuo
 *
 */
public abstract class CamelCaseUtils {

	private static final char SEPARATOR = '_';

	public static String toUnderlineName(String str) {
		if (str == null) {
			return null;
		}

		StringBuilder sb = new StringBuilder();
		boolean upperCase = false;
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);

			boolean nextUpperCase = true;

			if (i < (str.length() - 1)) {
				nextUpperCase = Character.isUpperCase(str.charAt(i + 1));
			}

			if ((i >= 0) && Character.isUpperCase(c)) {
				if (!upperCase || !nextUpperCase) {
					if (i > 0)
						sb.append(SEPARATOR);
				}
				upperCase = true;
			} else {
				upperCase = false;
			}

			sb.append(Character.toLowerCase(c));
		}

		return sb.toString();
	}

	public static String toCamelCase(String str) {
		if (str == null) {
			return null;
		}

		str = str.toLowerCase();

		StringBuilder sb = new StringBuilder(str.length());
		boolean upperCase = false;
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);

			if (c == SEPARATOR) {
				upperCase = true;
			} else if (upperCase) {
				sb.append(Character.toUpperCase(c));
				upperCase = false;
			} else {
				sb.append(c);
			}
		}

		return sb.toString();
	}

	public static String toCapitalizeCamelCase(String s) {
		if (s == null) {
			return null;
		}
		s = toCamelCase(s);
		return s.substring(0, 1).toUpperCase() + s.substring(1);
	}

	public static void main(String[] args) {
		String[] underlingStrings = { "siteName", "siteUrl", "logo",
				"hotSearch", "address", "phone", "zipCode", "email", "certtext",
				"isSiteEnabled", "siteCloseMessage", "largeProductImageWidth",
				"largeProductImageHeight", "mediumProductImageWidth",
				"mediumProductImageHeight", "thumbnailProductImageWidth",
				"thumbnailProductImageHeight", "defaultLargeProductImage",
				"defaultMediumProductImage", "defaultThumbnailProductImage",
				"watermarkAlpha", "watermarkImage", "watermarkPosition",
				"priceScale", "isShowMarketPrice", "defaultMarketPriceScale",
				"isRegisterEnabled", "isDuplicateEmail", "disabledUsername",
				"usernameMinLength", "usernameMaxLength", "passwordMinLength",
				"passwordMaxLength", "registerAgreement", "isEmailLogin",
				"captchaTypes", "accountLockTypes", "accountLockCount",
				"accountLockTime", "safeKeyExpiryTime", "uploadMaxSize",
				"uploadImageExtension", "uploadFlashExtension",
				"uploadMediaExtension", "uploadFileExtension",
				"imageUploadPath", "flashUploadPath", "mediaUploadPath",
				"fileUploadPath", "smtpFromMail", "smtpHost", "smtpPort",
				"smtpUsername", "smtpPassword", "currencySign", "currencyUnit",
				"stockAlertCount", "stockAllocationTime", "efaultPointScale",
				"isDevelopmentEnabled", "isReviewEnabled", "isReviewCheck",
				"eviewAuthority", "isConsultationEnabled",
				"isConsultationCheck", "consultationAuthority",
				"isInvoiceEnabled", };

		for (String str : underlingStrings) {
			System.out.print("public static final String ");
			System.out.print(
					StringUtils.upperCase(CamelCaseUtils.toUnderlineName(str)));
			System.out.println(";");
		}
	}

}
