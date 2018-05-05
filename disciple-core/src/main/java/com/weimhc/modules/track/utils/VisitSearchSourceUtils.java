package com.weimhc.modules.track.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.weimhc.modules.base.entity.SearchEngine;
import com.weimhc.modules.base.utils.SearchEngineUtils;
import com.weimhc.modules.track.entity.SourceType;
import com.weimhc.modules.track.entity.VisitTrack;

/**
 * 根据referrer 获取搜索来源等信息
 * 
 * 
 */
public abstract class VisitSearchSourceUtils {

	private static Logger logger = LoggerFactory
			.getLogger(VisitSearchSourceUtils.class);

	public static void dealWithSearchSource(VisitTrack visitTrack) {
		String referrer = visitTrack.getReferrer();
		if (StringUtils.isBlank(referrer)) {
			visitTrack.setSourceType(SourceType.direct);
			visitTrack.setSearchSite(SourceType.direct.name());
		} else {
			boolean isSearchEngine = getBySearchEngine(referrer, visitTrack);

			if (!isSearchEngine) {
				visitTrack.setSourceType(SourceType.link);
				visitTrack.setSearchSite(getSearchSite(referrer));
			}

		}
	}

	/**
	 * 从搜索引擎中获取数据
	 * 
	 * @param url
	 * @param visitTrack
	 * @return
	 */
	public static boolean getBySearchEngine(String url, VisitTrack visitTrack) {
		List<SearchEngine> searchEngines = SearchEngineUtils.findAll();
		Matcher matcherUrl, matchParamter;
		boolean isSearchEngine = false;
		for (SearchEngine se : searchEngines) {
			matcherUrl = se.getSerchUrlPattern().matcher(url);
			if (matcherUrl.find()) {
				isSearchEngine = true;
				visitTrack.setSourceType(SourceType.searchEngine);
				visitTrack.setSearchSite(se.getName());
				matchParamter = se.getSearchParameterPattern().matcher(url);
				if (matchParamter.find()) {
					visitTrack.setSearchKeywords(
							getSearchKeywords(matchParamter.group()));
				}
				break;
			}
		}
		return isSearchEngine;
	}

	public static String getSearchKeywords(String searchResult) {
		String result = null;
		try {
			result = URLDecoder.decode(searchResult, "UTF-8");
			result = result.substring(result.indexOf("=") + 1,
					result.length() - 1);
		} catch (UnsupportedEncodingException e) {
			logger.error(e.getMessage());
		}
		return result;
	}

	/**
	 * 获取来源网站
	 * <p>
	 * http://www.so.com/link?url=http 返回 http://www.so.com/
	 * </p>
	 * 
	 * @param url
	 * @return
	 */
	public static String getSearchSite(String url) {
		Pattern pattern = Pattern.compile("^https?://([\\w-]+\\.)+[\\w-]+/");
		Matcher matcher = pattern.matcher(url);
		if (matcher.find()) {
			return matcher.group();
		}
		return "";
	}

	public static void main(String args[]) {
		String referrer = "http://www.so.com/link?url=http%3A%2F%2Fwww.zhihu.com%2Ftopic%2F19599437%2Ftop-answers&q=%E7%99%BE%E5%BA%A6%E7%BB%9F%E8%AE%A1&ts=1491897176&t=78737f226d9f81082717ff5421888e0";
		Pattern pattern = Pattern.compile("http://www.so.com(.*)");
		Pattern patternP = Pattern.compile("(?:^|/?|&)q=([^&]*)(?:&|$)");

		Matcher matcherUrl = pattern.matcher(referrer);
		System.out.println(matcherUrl.find());

		Matcher matcherP = patternP.matcher(referrer);
		System.out.println(matcherP.find());
		System.out.println(getSearchKeywords(matcherP.group()));

		System.out.println(getSearchSite(referrer));
	}
}