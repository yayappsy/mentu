/**
 * 
 */
package com.weimhc.framework.cache.config;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * 项目Jedis 缓存配置
 * 
 * @author shaozuo
 *
 */
@XmlRootElement(name = "cache")
public class JedisCacheConfiguration {

	/**
	 * Default eternal value
	 */
	public static final boolean DEFAULT_ETERNAL_VALUE = false;

	/**
	 * Default singleton value
	 */
	public static final boolean DEFAULT_SINGLETON_VALUE = false;
	/**
	 * Default value for ttl
	 */
	public static final long DEFAULT_TTL = 0;

	/**
	 * Default value for tti
	 */
	public static final long DEFAULT_TTI = 0;
	/**
	 * 缓存名称
	 */
	private String name;
	/**
	 * 是否永久保存
	 */
	private boolean eternal = DEFAULT_ETERNAL_VALUE;

	/**
	 * 缓存element在过期前的空闲时间<br/>
	 * the time to idle for an element before it expires. Is only used if the
	 * element is not eternal.A value of 0 means do not check for idling.
	 */
	protected long timeToIdleSeconds = DEFAULT_TTI;

	/**
	 * 缓存element的有效生命期<br/>
	 * Sets the time to idle for an element before it expires. Is only used if
	 * the element is not eternal. This attribute is optional in the
	 * configuration. A value of 0 means do not check time to live.
	 */
	protected long timeToLiveSeconds = DEFAULT_TTL;

	/**
	 * 是否单例<br>
	 * 表示redis中，只有一个统一的前缀，没有下级前缀
	 */
	private boolean singleton = DEFAULT_SINGLETON_VALUE;

	/**
	 * 
	 */
	public JedisCacheConfiguration() {
	}

	/**
	 * 
	 */
	public JedisCacheConfiguration(String name) {
		this.name = name;
	}

	/**
	 * 返回 缓存名称
	 * 
	 * @return the name
	 */
	@XmlAttribute
	public String getName() {
		return name;
	}

	/**
	 * 设置 缓存名称
	 * 
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 返回 是否永久保存
	 * 
	 * @return the eternal
	 */
	@XmlAttribute
	public boolean isEternal() {
		return eternal;
	}

	/**
	 * 设置 是否永久保存
	 * 
	 * @param eternal
	 *            the eternal to set
	 */
	public void setEternal(boolean eternal) {
		this.eternal = eternal;
	}

	/**
	 * 返回 缓存element在过期前的空闲时间
	 * 
	 * @return the timeToIdleSeconds
	 */
	@XmlAttribute
	public long getTimeToIdleSeconds() {
		return timeToIdleSeconds;
	}

	/**
	 * 设置 缓存element在过期前的空闲时间
	 * 
	 * @param timeToIdleSeconds
	 *            the timeToIdleSeconds to set
	 */
	public void setTimeToIdleSeconds(long timeToIdleSeconds) {
		this.timeToIdleSeconds = timeToIdleSeconds;
	}

	/**
	 * 返回 缓存element的有效生命期
	 * 
	 * @return the timeToLiveSeconds
	 */
	@XmlAttribute
	public long getTimeToLiveSeconds() {
		return timeToLiveSeconds;
	}

	/**
	 * 设置 缓存element的有效生命期
	 * 
	 * @param timeToLiveSeconds
	 *            the timeToLiveSeconds to set
	 */
	public void setTimeToLiveSeconds(long timeToLiveSeconds) {
		this.timeToLiveSeconds = timeToLiveSeconds;
	}

	/**
	 * 返回 是否单例
	 * 
	 * @return the singleton
	 */
	@XmlAttribute
	public boolean isSingleton() {
		return singleton;
	}

	/**
	 * 设置 是否单例
	 * 
	 * @param singleton
	 *            the singleton to set
	 */
	public void setSingleton(boolean singleton) {
		this.singleton = singleton;
	}

}
