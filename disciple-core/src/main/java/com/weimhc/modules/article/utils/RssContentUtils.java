/*
 * 
 * 
 * 
 */
package com.weimhc.modules.article.utils;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import com.rometools.rome.feed.rss.Channel;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import com.thinkgem.javamg.common.utils.CacheUtils;
import com.thinkgem.javamg.common.utils.SpringContextHolder;
import com.weimhc.modules.article.dao.ArticleDao;
import com.weimhc.modules.article.dao.RssContentDao;
import com.weimhc.modules.article.dao.RssFeedDao;
import com.weimhc.modules.article.entity.Article;
import com.weimhc.modules.article.entity.RssContent;
import com.weimhc.modules.article.entity.RssFeed;

/**
 * Utils - rss
 * 
 * 
 */
public abstract class RssContentUtils {

	private static Logger logger = LoggerFactory
			.getLogger(RssContentUtils.class);

	private static ArticleDao articleDao = SpringContextHolder
			.getBean(ArticleDao.class);

	private static RssContentDao rssContentDao = SpringContextHolder
			.getBean(RssContentDao.class);

	private static RssFeedDao rssFeedDao = SpringContextHolder
			.getBean(RssFeedDao.class);

	/**
	 * 信息源相关缓存 暂时未使用
	 */
	public final static String RSS_CACHE = "rssCache";

	/** 信息源地址相关缓存列表 */
	public final static String CACHE_RSS_FEED_LIST = "rssFeed_list";

	/** 信息源地址相关缓存列表 */
	public final static String CACHE_RSS_FEED_BY_ID = "rssFeed_id";

	/**
	 * 获取信息源相关缓存
	 * 
	 * @param key
	 * @return
	 */
	public static Object getCache(String key) {
		return getCache(key, null);
	}

	/**
	 * 获取相关缓存 从默认缓存中
	 * 
	 * @param key
	 * @return
	 */
	public static Object getCache(String key, Object defaultValue) {
		Object obj = CacheUtils.get(key);
		return obj == null ? defaultValue : obj;
	}

	/**
	 * 设置信息源相关缓存
	 * 
	 * @param key
	 * @return
	 */
	public static void putCache(String key, Object value) {
		CacheUtils.put(key, value);
	}

	/**
	 * 获取所有的信息源地址
	 * 
	 * @return
	 */
	public static List<RssFeed> findAllRssFeed() {
		@SuppressWarnings("unchecked")
		List<RssFeed> list = (List<RssFeed>) getCache(CACHE_RSS_FEED_LIST);
		if (list == null) {
			list = rssFeedDao.findAllList(new RssFeed());
			putCache(CACHE_RSS_FEED_LIST, list);
		}
		return list;

	}

	/**
	 * id查询信息源
	 * 
	 * @param id
	 * @return
	 */
	public static RssFeed findRssFeedById(String id) {
		List<RssFeed> list = findAllRssFeed();
		for (RssFeed entity : list) {
			if (StringUtils.equals(id, entity.getId())) {
				return entity;
			}
		}
		return new RssFeed();
	}

	/**
	 * 抓取rss新闻
	 * 
	 * @param rssContent
	 */
	public static RssContent fetchRss(RssContent rssContent) {
		SyndFeed feed = null;
		try {
			String feedUrl = StringEscapeUtils
					.unescapeHtml4(rssContent.getRssUrl());
			logger.debug("rss源:{},rss地址:{}", rssContent.getRssName(), feedUrl);

			SyndFeedInput syndFeedInput = new SyndFeedInput();
			syndFeedInput.setPreserveWireFeed(true);
			feed = syndFeedInput.build(new XmlReader(new URL(feedUrl)));

		} catch (IllegalArgumentException | FeedException | IOException e) {
			logger.debug(e.getMessage());
		}
		if (feed != null && isNeedUpdate(rssContent,
				(Channel) (feed.originalWireFeed()))) {
			updateFetchCount(rssContent, feed);
		}
		return rssContent;

	}

	/**
	 * 判断是否需要更新新闻数据
	 * 
	 * @return
	 */
	private static boolean isNeedUpdate(RssContent rssContent,
			Channel channel) {
		long ttlMilliseconds = channel.getTtl() * 60 * 1000L;// 过期时间
		long lastFetchTime = rssContent.getLastFetchDate().getTime();
		long now = System.currentTimeMillis();
		logger.debug("上次更新时间：{}，过期时间：{}，当前时间：{}", lastFetchTime,
				ttlMilliseconds, now);
		if ((lastFetchTime + ttlMilliseconds) < now) {
			return true;
		}
		return false;
	}

	/**
	 * 更新信息
	 * 
	 * @param rssContent
	 * @param feed
	 */
	private static void updateFetchCount(RssContent rssContent, SyndFeed feed) {
		int fetchNumber = fetchNumber(rssContent, feed);

		logger.debug("需要抓取的关键词：{},满足条件的新闻数量：{}", rssContent.getKeywords(),
				fetchNumber);
		if (rssContent.getNumber() == null) {
			rssContent.setNumber(fetchNumber);
		} else {
			rssContent.setNumber(rssContent.getNumber() + fetchNumber);
		}
		rssContent.preUpdate();
		rssContent.setLastFetchDate(rssContent.getUpdateDate());
		rssContentDao.updateFetchCount(rssContent);
	}

	/**
	 * 获取 更新数据
	 * 
	 * @param rssContent
	 * @param feed
	 * @return
	 */
	private static int fetchNumber(RssContent rssContent, SyndFeed feed) {
		List<SyndEntry> list = feed.getEntries();
		int fetchNumber = 0;
		for (SyndEntry syndEntry : list) {
			if (!searchKeywords(rssContent.getKeywords(), syndEntry)) {
				continue;
			}
			Article article = getArticleFromSyndEntry(rssContent, syndEntry);
			article.preInsert();
			articleDao.insert(article);
			fetchNumber++;
		}
		return fetchNumber;
	}

	/**
	 * 搜索关键词 在标题或内容中中是否存在
	 * 
	 * @param searchKeywords
	 * @param syndEntry
	 * @return
	 */
	private static boolean searchKeywords(String searchKeywords,
			SyndEntry syndEntry) {

		if (StringUtils.isBlank(searchKeywords)) {
			return true;
		}
		if (StringUtils.contains(syndEntry.getTitle(), searchKeywords)) {
			return true;
		}
		if (syndEntry.getDescription() != null && StringUtils.contains(
				syndEntry.getDescription().getValue(), searchKeywords)) {
			return true;
		}

		return false;
	}

	/**
	 * 从实体中获取文章信息
	 * 
	 * @param rssContent
	 * @param syndEntry
	 * @return
	 */
	private static Article getArticleFromSyndEntry(RssContent rssContent,
			SyndEntry syndEntry) {
		Article article = new Article();
		BeanUtils.copyProperties(syndEntry, article);
		article.setDescription(syndEntry.getDescription() == null ? ""
				: syndEntry.getDescription().getValue());
		article.setUrl(syndEntry.getLink());
		article.setArticleSource(syndEntry.getSource() == null ? ""
				: syndEntry.getSource().getTitle());
		article.setCategory(rssContent.getArticleCategory());
		article.setArticleType(
				ArticleTypeUtils.findById(ArticleTypeUtils.LINK));
		return article;
	}
}