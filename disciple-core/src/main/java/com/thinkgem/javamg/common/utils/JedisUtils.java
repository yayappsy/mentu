/**
 * 
 */
package com.thinkgem.javamg.common.utils;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.thinkgem.javamg.common.config.Global;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.exceptions.JedisException;

/**
 * Jedis Cache 工具类
 * 
 * 
 * @version 2014-6-29
 */
public class JedisUtils {

	private static Logger logger = LoggerFactory.getLogger(JedisUtils.class);

	private static JedisPool jedisPool = SpringContextHolder.getBean(JedisPool.class);

	public static final String KEY_PREFIX = Global.getConfig("redis.keyPrefix");

	/**
	 * 获取缓存
	 * 
	 * @param key
	 *            键
	 * @return 值
	 */
	public static String get(String key) {
		String value = null;
		Jedis jedis = null;
		try {
			jedis = getResource();
			if (jedis.exists(key)) {
				value = jedis.get(key);
				value = StringUtils.isNotBlank(value) && !"nil".equalsIgnoreCase(value) ? value
						: null;
				printGetLogger(key, value);
			}
		} catch (Exception e) {
			del(key);
			logger.warn("get {} = {}", key, value, e);
		} finally {
			returnResource(jedis);
		}
		return value;
	}

	/**
	 * 获取缓存,并刷新过期时间
	 * 
	 * @param key
	 *            键
	 * @param timeToIdleSeconds
	 *            缓存element在过期前的空闲时间
	 * @return 值
	 */
	public static String get(String key, int timeToIdleSeconds) {
		String value = null;
		Jedis jedis = null;
		try {
			jedis = getResource();
			if (jedis.exists(key)) {
				value = jedis.get(key);
				value = StringUtils.isNotBlank(value) && !"nil".equalsIgnoreCase(value) ? value
						: null;
				if (value != null && timeToIdleSeconds != 0) {
					jedis.expire(key, timeToIdleSeconds);
				}
				printGetLogger(key, value);
			}
		} catch (Exception e) {
			del(key);
			logger.warn("get {} = {}", key, value, e);
		} finally {
			returnResource(jedis);
		}
		return value;
	}

	/**
	 * 获取缓存
	 * 
	 * @param key
	 *            键
	 * @param timeToIdleSeconds
	 *            缓存element在过期前的空闲时间
	 * @return 值
	 */
	public static Object getObject(String key, int timeToIdleSeconds) {
		Object value = null;
		Jedis jedis = null;
		try {
			jedis = getResource();
			if (jedis.exists(JRedisSerializationUtils.getBytesKey(key))) {
				value = JRedisSerializationUtils
						.toObject(jedis.get(JRedisSerializationUtils.getBytesKey(key)));
				if (timeToIdleSeconds >= 0) {
					jedis.expire(key, timeToIdleSeconds);
				}
				printGetLogger(key, value);
			}
		} catch (Exception e) {
			del(key);
			logger.warn("getObject {} = {}", key, value, e);
		} finally {
			returnResource(jedis);
		}
		return value;
	}

	/**
	 * 获取缓存
	 * 
	 * @param key
	 *            键
	 * @return 值
	 */
	public static Object getObject(String key) {
		Object value = null;
		Jedis jedis = null;
		try {
			jedis = getResource();
			if (jedis.exists(JRedisSerializationUtils.getBytesKey(key))) {
				value = JRedisSerializationUtils
						.toObject(jedis.get(JRedisSerializationUtils.getBytesKey(key)));
				printGetLogger(key, value);
			}
		} catch (Exception e) {
			del(key);
			logger.warn("getObject {} = {}", key, value, e);
		} finally {
			returnResource(jedis);
		}
		return value;
	}

	/**
	 * 返回value的值 如果value是List、Map、Set类型中的一个，则只显示类名和数量
	 * 
	 * @param value
	 * @return
	 */

	@SuppressWarnings("rawtypes")
	private static void printGetLogger(String key, Object value) {
		if (value instanceof Collection) {
			logger.debug("getObject {} = {} : {}", key, value.getClass(),
					((Collection) value).size());
		} else if (value instanceof Map) {
			logger.debug("getObject {} = {} : {}", key, value.getClass(), ((Map) value).size());
		} else if (value instanceof Set) {
			logger.debug("getObject {} = {} : {}", key, value.getClass(), ((Set) value).size());
		} else {
			logger.debug("getObject {} = {} ", key, value);
		}
	}

	/**
	 * 设置缓存
	 * 
	 * @param key
	 *            键
	 * @param value
	 *            值
	 * @param cacheSeconds
	 *            超时时间，0为不超时
	 * @return
	 */
	public static String set(String key, String value, int cacheSeconds) {
		String result = null;
		Jedis jedis = null;
		try {
			jedis = getResource();
			result = jedis.set(key, value);
			if (cacheSeconds != 0) {
				jedis.expire(key, cacheSeconds);
			}
			logger.debug("set {} = {}", key, value);
		} catch (Exception e) {
			logger.warn("set {} = {}", key, value, e);
		} finally {
			returnResource(jedis);
		}
		return result;
	}

	/**
	 * 设置缓存
	 * 
	 * @param key
	 *            键
	 * @param value
	 *            值
	 * @param cacheSeconds
	 *            超时时间，0为不超时
	 * @return
	 */
	public static String setObject(String key, Object value, int cacheSeconds) {
		String result = null;
		Jedis jedis = null;
		try {
			jedis = getResource();
			result = jedis.set(JRedisSerializationUtils.getBytesKey(key),
					JRedisSerializationUtils.toBytes(value));
			if (cacheSeconds > 0) {
				jedis.expire(key, cacheSeconds);
			}
			logger.debug("setObject {} = {},过期时间是 {}", key, value.getClass(), cacheSeconds);
		} catch (Exception e) {
			logger.warn("setObject {} = {}", key, value.getClass(), e);
		} finally {
			returnResource(jedis);
		}
		return result;
	}

	/**
	 * 获取List缓存
	 * 
	 * @param key
	 *            键
	 * @return 值
	 */
	public static List<String> getList(String key) {
		List<String> value = null;
		Jedis jedis = null;
		try {
			jedis = getResource();
			if (jedis.exists(key)) {
				value = jedis.lrange(key, 0, -1);
				logger.debug("getList {} = {}", key, value);
			}
		} catch (Exception e) {
			del(key);
			logger.warn("getList {} = {}", key, value, e);
		} finally {
			returnResource(jedis);
		}
		return value;
	}

	/**
	 * 获取List缓存
	 * 
	 * @param key
	 *            键
	 * @return 值
	 */
	public static List<Object> getObjectList(String key) {
		List<Object> value = null;
		Jedis jedis = null;
		try {
			jedis = getResource();
			if (jedis.exists(JRedisSerializationUtils.getBytesKey(key))) {
				List<byte[]> list = jedis.lrange(JRedisSerializationUtils.getBytesKey(key), 0, -1);
				value = Lists.newArrayList();
				for (byte[] bs : list) {
					value.add(JRedisSerializationUtils.toObject(bs));
				}
				logger.debug("getObjectList {} = {}", key, value);
			}
		} catch (Exception e) {
			del(key);
			logger.warn("getObjectList {} = {}", key, value, e);
		} finally {
			returnResource(jedis);
		}
		return value;
	}

	/**
	 * 设置List缓存
	 * 
	 * @param key
	 *            键
	 * @param value
	 *            值
	 * @param cacheSeconds
	 *            超时时间，0为不超时
	 * @return
	 */
	public static long setList(String key, List<String> value, int cacheSeconds) {
		long result = 0;
		Jedis jedis = null;
		try {
			jedis = getResource();
			if (jedis.exists(key)) {
				jedis.del(key);
			}
			result = jedis.rpush(key, value.toArray(new String[value.size()]));
			if (cacheSeconds != 0) {
				jedis.expire(key, cacheSeconds);
			}
			logger.debug("setList {} = {}", key, value);
		} catch (Exception e) {
			logger.warn("setList {} = {}", key, value, e);
		} finally {
			returnResource(jedis);
		}
		return result;
	}

	/**
	 * 设置List缓存
	 * 
	 * @param key
	 *            键
	 * @param value
	 *            值
	 * @param cacheSeconds
	 *            超时时间，0为不超时
	 * @return
	 */
	public static long setObjectList(String key, List<Object> value, int cacheSeconds) {
		long result = 0;
		Jedis jedis = null;
		try {
			jedis = getResource();
			if (jedis.exists(JRedisSerializationUtils.getBytesKey(key))) {
				jedis.del(key);
			}
			List<byte[]> list = Lists.newArrayList();
			for (Object o : value) {
				list.add(JRedisSerializationUtils.toBytes(o));
			}
			result = jedis.rpush(JRedisSerializationUtils.getBytesKey(key),
					(byte[][]) list.toArray());
			if (cacheSeconds != 0) {
				jedis.expire(key, cacheSeconds);
			}
			logger.debug("setObjectList {} = {}", key, value);
		} catch (Exception e) {
			del(key);
			logger.warn("setObjectList {} = {}", key, value, e);
		} finally {
			returnResource(jedis);
		}
		return result;
	}

	/**
	 * 向List缓存中添加值
	 * 
	 * @param key
	 *            键
	 * @param value
	 *            值
	 * @return
	 */
	public static long listAdd(String key, String... value) {
		long result = 0;
		Jedis jedis = null;
		try {
			jedis = getResource();
			result = jedis.rpush(key, value);
			logger.debug("listAdd {} = {}", key, value);
		} catch (Exception e) {
			logger.warn("listAdd {} = {}", key, value, e);
		} finally {
			returnResource(jedis);
		}
		return result;
	}

	/**
	 * 向List缓存中添加值
	 * 
	 * @param key
	 *            键
	 * @param value
	 *            值
	 * @return
	 */
	public static long listObjectAdd(String key, Object... value) {
		long result = 0;
		Jedis jedis = null;
		try {
			jedis = getResource();
			List<byte[]> list = Lists.newArrayList();
			for (Object o : value) {
				list.add(JRedisSerializationUtils.toBytes(o));
			}
			result = jedis.rpush(JRedisSerializationUtils.getBytesKey(key),
					(byte[][]) list.toArray());
			logger.debug("listObjectAdd {} = {}", key, value);
		} catch (Exception e) {
			logger.warn("listObjectAdd {} = {}", key, value, e);
		} finally {
			returnResource(jedis);
		}
		return result;
	}

	/**
	 * 获取缓存
	 * 
	 * @param key
	 *            键
	 * @return 值
	 */
	public static Set<String> getSet(String key) {
		Set<String> value = null;
		Jedis jedis = null;
		try {
			jedis = getResource();
			if (jedis.exists(key)) {
				value = jedis.smembers(key);
				logger.debug("getSet {} = {}", key, value);
			}
		} catch (Exception e) {
			del(key);
			logger.warn("getSet {} = {}", key, value, e);
		} finally {
			returnResource(jedis);
		}
		return value;
	}

	/**
	 * 获取缓存
	 * 
	 * @param key
	 *            键
	 * @return 值
	 */
	public static Set<Object> getObjectSet(String key) {
		Set<Object> value = null;
		Jedis jedis = null;
		try {
			jedis = getResource();
			if (jedis.exists(JRedisSerializationUtils.getBytesKey(key))) {
				value = Sets.newHashSet();
				Set<byte[]> set = jedis.smembers(JRedisSerializationUtils.getBytesKey(key));
				for (byte[] bs : set) {
					value.add(JRedisSerializationUtils.toObject(bs));
				}
				logger.debug("getObjectSet {} = {}", key, value);
			}
		} catch (Exception e) {
			del(key);
			logger.warn("getObjectSet {} = {}", key, value, e);
		} finally {
			returnResource(jedis);
		}
		return value;
	}

	/**
	 * 设置Set缓存
	 * 
	 * @param key
	 *            键
	 * @param value
	 *            值
	 * @param cacheSeconds
	 *            超时时间，0为不超时
	 * @return
	 */
	public static long setSet(String key, Set<String> value, int cacheSeconds) {
		long result = 0;
		Jedis jedis = null;
		try {
			jedis = getResource();
			if (jedis.exists(key)) {
				jedis.del(key);
			}
			result = jedis.sadd(key, value.toArray(new String[value.size()]));
			if (cacheSeconds != 0) {
				jedis.expire(key, cacheSeconds);
			}
			logger.debug("setSet {} = {}", key, value);
		} catch (Exception e) {
			logger.warn("setSet {} = {}", key, value, e);
		} finally {
			returnResource(jedis);
		}
		return result;
	}

	/**
	 * 设置Set缓存
	 * 
	 * @param key
	 *            键
	 * @param value
	 *            值
	 * @param cacheSeconds
	 *            超时时间，0为不超时
	 * @return
	 */
	public static long setObjectSet(String key, Set<Object> value, int cacheSeconds) {
		long result = 0;
		Jedis jedis = null;
		try {
			jedis = getResource();
			if (jedis.exists(JRedisSerializationUtils.getBytesKey(key))) {
				jedis.del(key);
			}
			Set<byte[]> set = Sets.newHashSet();
			for (Object o : value) {
				set.add(JRedisSerializationUtils.toBytes(o));
			}
			result = jedis.sadd(JRedisSerializationUtils.getBytesKey(key),
					(byte[][]) set.toArray());
			if (cacheSeconds != 0) {
				jedis.expire(key, cacheSeconds);
			}
			logger.debug("setObjectSet {} = {}", key, value);
		} catch (Exception e) {
			logger.warn("setObjectSet {} = {}", key, value, e);
		} finally {
			returnResource(jedis);
		}
		return result;
	}

	/**
	 * 向Set缓存中添加值
	 * 
	 * @param key
	 *            键
	 * @param value
	 *            值
	 * @return
	 */
	public static long setSetAdd(String key, String... value) {
		long result = 0;
		Jedis jedis = null;
		try {
			jedis = getResource();
			result = jedis.sadd(key, value);
			logger.debug("setSetAdd {} = {}", key, value);
		} catch (Exception e) {
			logger.warn("setSetAdd {} = {}", key, value, e);
		} finally {
			returnResource(jedis);
		}
		return result;
	}

	/**
	 * 向Set缓存中添加值
	 * 
	 * @param key
	 *            键
	 * @param value
	 *            值
	 * @return
	 */
	public static long setSetObjectAdd(String key, Object... value) {
		long result = 0;
		Jedis jedis = null;
		try {
			jedis = getResource();
			Set<byte[]> set = Sets.newHashSet();
			for (Object o : value) {
				set.add(JRedisSerializationUtils.toBytes(o));
			}
			result = jedis.rpush(JRedisSerializationUtils.getBytesKey(key),
					(byte[][]) set.toArray());
			logger.debug("setSetObjectAdd {} = {}", key, value);
		} catch (Exception e) {
			logger.warn("setSetObjectAdd {} = {}", key, value, e);
		} finally {
			returnResource(jedis);
		}
		return result;
	}

	/**
	 * 获取Map缓存
	 * 
	 * @param key
	 *            键
	 * @return 值
	 */
	public static Map<String, String> getMap(String key) {
		Map<String, String> value = null;
		Jedis jedis = null;
		try {
			jedis = getResource();
			if (jedis.exists(key)) {
				value = jedis.hgetAll(key);
				logger.debug("getMap {} = {}", key, value);
			}
		} catch (Exception e) {
			logger.warn("getMap {} = {}", key, value, e);
		} finally {
			returnResource(jedis);
		}
		return value;
	}

	/**
	 * 获取Map缓存
	 * 
	 * @param key
	 *            键
	 * @return 值
	 */
	public static Map<String, Object> getObjectMap(String key) {
		Map<String, Object> value = null;
		Jedis jedis = null;
		try {
			jedis = getResource();
			if (jedis.exists(JRedisSerializationUtils.getBytesKey(key))) {
				value = Maps.newHashMap();
				Map<byte[], byte[]> map = jedis.hgetAll(JRedisSerializationUtils.getBytesKey(key));
				for (Map.Entry<byte[], byte[]> e : map.entrySet()) {
					value.put(StringUtils.toString(e.getKey()),
							JRedisSerializationUtils.toObject(e.getValue()));
				}
				logger.debug("getObjectMap {} = {}", key, value);
			}
		} catch (Exception e) {
			logger.warn("getObjectMap {} = {}", key, value, e);
		} finally {
			returnResource(jedis);
		}
		return value;
	}

	/**
	 * 设置Map缓存
	 * 
	 * @param key
	 *            键
	 * @param value
	 *            值
	 * @param cacheSeconds
	 *            超时时间，0为不超时
	 * @return
	 */
	public static String setMap(String key, Map<String, String> value, int cacheSeconds) {
		String result = null;
		Jedis jedis = null;
		try {
			jedis = getResource();
			if (jedis.exists(key)) {
				jedis.del(key);
			}
			result = jedis.hmset(key, value);
			if (cacheSeconds != 0) {
				jedis.expire(key, cacheSeconds);
			}
			logger.debug("setMap {} = {}", key, value);
		} catch (Exception e) {
			logger.warn("setMap {} = {}", key, value, e);
		} finally {
			returnResource(jedis);
		}
		return result;
	}

	/**
	 * 设置Map缓存
	 * 
	 * @param key
	 *            键
	 * @param value
	 *            值
	 * @param cacheSeconds
	 *            超时时间，0为不超时
	 * @return
	 */
	public static String setObjectMap(String key, Map<String, Object> value, int cacheSeconds) {
		String result = null;
		Jedis jedis = null;
		try {
			jedis = getResource();
			if (jedis.exists(JRedisSerializationUtils.getBytesKey(key))) {
				jedis.del(key);
			}
			Map<byte[], byte[]> map = Maps.newHashMap();
			for (Map.Entry<String, Object> e : value.entrySet()) {
				map.put(JRedisSerializationUtils.getBytesKey(e.getKey()),
						JRedisSerializationUtils.toBytes(e.getValue()));
			}
			result = jedis.hmset(JRedisSerializationUtils.getBytesKey(key), map);
			if (cacheSeconds != 0) {
				jedis.expire(key, cacheSeconds);
			}
			logger.debug("setObjectMap {} = {}", key, value);
		} catch (Exception e) {
			logger.warn("setObjectMap {} = {}", key, value, e);
		} finally {
			returnResource(jedis);
		}
		return result;
	}

	/**
	 * 向Map缓存中添加值
	 * 
	 * @param key
	 *            键
	 * @param value
	 *            值
	 * @return
	 */
	public static String mapPut(String key, Map<String, String> value) {
		String result = null;
		Jedis jedis = null;
		try {
			jedis = getResource();
			result = jedis.hmset(key, value);
			logger.debug("mapPut {} = {}", key, value);
		} catch (Exception e) {
			logger.warn("mapPut {} = {}", key, value, e);
		} finally {
			returnResource(jedis);
		}
		return result;
	}

	/**
	 * 向Map缓存中添加值
	 * 
	 * @param key
	 *            键
	 * @param value
	 *            值
	 * @return
	 */
	public static String mapObjectPut(String key, Map<String, Object> value) {
		String result = null;
		Jedis jedis = null;
		try {
			jedis = getResource();
			Map<byte[], byte[]> map = Maps.newHashMap();
			for (Map.Entry<String, Object> e : value.entrySet()) {
				map.put(JRedisSerializationUtils.getBytesKey(e.getKey()),
						JRedisSerializationUtils.toBytes(e.getValue()));
			}
			result = jedis.hmset(JRedisSerializationUtils.getBytesKey(key), map);
			logger.debug("mapObjectPut {} = {}", key, value);
		} catch (Exception e) {
			logger.warn("mapObjectPut {} = {}", key, value, e);
		} finally {
			returnResource(jedis);
		}
		return result;
	}

	/**
	 * 移除Map缓存中的值
	 * 
	 * @param key
	 *            键
	 * @param value
	 *            值
	 * @return
	 */
	public static long mapRemove(String key, String mapKey) {
		long result = 0;
		Jedis jedis = null;
		try {
			jedis = getResource();
			result = jedis.hdel(key, mapKey);
			logger.debug("mapRemove {}  {}", key, mapKey);
		} catch (Exception e) {
			logger.warn("mapRemove {}  {}", key, mapKey, e);
		} finally {
			returnResource(jedis);
		}
		return result;
	}

	/**
	 * 移除Map缓存中的值
	 * 
	 * @param key
	 *            键
	 * @param value
	 *            值
	 * @return
	 */
	public static long mapObjectRemove(String key, String mapKey) {
		long result = 0;
		Jedis jedis = null;
		try {
			jedis = getResource();
			result = jedis.hdel(JRedisSerializationUtils.getBytesKey(key),
					JRedisSerializationUtils.getBytesKey(mapKey));
			logger.debug("mapObjectRemove {}  {}", key, mapKey);
		} catch (Exception e) {
			logger.warn("mapObjectRemove {}  {}", key, mapKey, e);
		} finally {
			returnResource(jedis);
		}
		return result;
	}

	/**
	 * 判断Map缓存中的Key是否存在
	 * 
	 * @param key
	 *            键
	 * @param value
	 *            值
	 * @return
	 */
	public static boolean mapExists(String key, String mapKey) {
		boolean result = false;
		Jedis jedis = null;
		try {
			jedis = getResource();
			result = jedis.hexists(key, mapKey);
			logger.debug("mapExists {}  {}", key, mapKey);
		} catch (Exception e) {
			logger.warn("mapExists {}  {}", key, mapKey, e);
		} finally {
			returnResource(jedis);
		}
		return result;
	}

	/**
	 * 判断Map缓存中的Key是否存在
	 * 
	 * @param key
	 *            键
	 * @param value
	 *            值
	 * @return
	 */
	public static boolean mapObjectExists(String key, String mapKey) {
		boolean result = false;
		Jedis jedis = null;
		try {
			jedis = getResource();
			result = jedis.hexists(JRedisSerializationUtils.getBytesKey(key),
					JRedisSerializationUtils.getBytesKey(mapKey));
			logger.debug("mapObjectExists {}  {}", key, mapKey);
		} catch (Exception e) {
			logger.warn("mapObjectExists {}  {}", key, mapKey, e);
		} finally {
			returnResource(jedis);
		}
		return result;
	}

	/**
	 * 删除缓存
	 * 
	 * @param key
	 *            键
	 * @return
	 */
	public static long del(String key) {
		long result = 0;
		Jedis jedis = null;
		try {
			jedis = getResource();
			if (jedis.exists(key)) {
				result = jedis.del(key);
				logger.debug("del {}", key);
			} else {
				logger.debug("del {} not exists", key);
			}
		} catch (Exception e) {
			logger.warn("del {}", key, e);
		} finally {
			returnResource(jedis);
		}
		return result;
	}

	/**
	 * 删除多个缓存
	 * 
	 * @param key
	 *            键
	 * @return
	 */
	public static long del(String[] keys) {
		long result = 0;
		Jedis jedis = null;
		try {
			jedis = getResource();
			result = jedis.del(keys);
			logger.debug("del {}", keys.toString());
		} catch (Exception e) {
			logger.warn("del {}", keys, e);
		} finally {
			returnResource(jedis);
		}
		return result;
	}

	/**
	 * 删除缓存
	 * 
	 * @param key
	 *            键
	 * @return
	 */
	public static long delObject(String key) {
		long result = 0;
		Jedis jedis = null;
		try {
			jedis = getResource();
			if (jedis.exists(JRedisSerializationUtils.getBytesKey(key))) {
				result = jedis.del(JRedisSerializationUtils.getBytesKey(key));
				logger.debug("delObject {}", key);
			} else {
				logger.debug("delObject {} not exists", key);
			}
		} catch (Exception e) {
			logger.warn("delObject {}", key, e);
		} finally {
			returnResource(jedis);
		}
		return result;
	}

	/**
	 * 缓存是否存在
	 * 
	 * @param key
	 *            键
	 * @return
	 */
	public static boolean exists(String key) {
		boolean result = false;
		Jedis jedis = null;
		try {
			jedis = getResource();
			result = jedis.exists(key);
			logger.debug("exists {}", key);
		} catch (Exception e) {
			logger.warn("exists {}", key, e);
		} finally {
			returnResource(jedis);
		}
		return result;
	}

	/**
	 * 缓存是否存在
	 * 
	 * @param key
	 *            键
	 * @return
	 */
	public static boolean existsObject(String key) {
		boolean result = false;
		Jedis jedis = null;
		try {
			jedis = getResource();
			result = jedis.exists(JRedisSerializationUtils.getBytesKey(key));
			logger.debug("existsObject {}", key);
		} catch (Exception e) {
			logger.warn("existsObject {}", key, e);
		} finally {
			returnResource(jedis);
		}
		return result;
	}

	/**
	 * 模式匹配 获取keys
	 * 
	 * @param pattern
	 * @return
	 */
	public static Set<String> keys(String pattern) {
		Set<String> result = null;
		Jedis jedis = null;
		try {
			jedis = getResource();
			result = jedis.keys(pattern);
			logger.debug("keys {}", result);
		} catch (Exception e) {
			logger.warn("keys {}", pattern, e);
		} finally {
			returnResource(jedis);
		}
		return result;
	}

	/**
	 * 获取资源
	 * 
	 * @return
	 * @throws JedisException
	 */
	public static Jedis getResource() throws JedisException {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			// logger.debug("getResource.", jedis);
		} catch (JedisException e) {
			logger.warn("getResource.", e);
			returnBrokenResource(jedis);
			throw e;
		}
		return jedis;
	}

	/**
	 * 归还资源
	 * 
	 * @param jedis
	 * @param isBroken
	 */
	public static void returnBrokenResource(Jedis jedis) {
		if (jedis != null) {
			jedisPool.returnBrokenResource(jedis);
		}
	}

	/**
	 * 释放资源
	 * 
	 * @param jedis
	 * @param isBroken
	 */
	public static void returnResource(Jedis jedis) {
		if (jedis != null) {
			jedisPool.returnResource(jedis);
		}
	}

}
