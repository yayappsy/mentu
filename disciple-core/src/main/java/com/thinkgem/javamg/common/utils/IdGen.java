/**
 * 
 */
package com.thinkgem.javamg.common.utils;

import java.io.Serializable;
import java.security.SecureRandom;
import java.util.UUID;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionIdGenerator;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.util.IdGenerator;

import com.thinkgem.javamg.common.config.Global;
import com.thinkgem.javamg.common.idcenter.snowfake.IdWorker;

/**
 * 封装各种生成唯一性ID算法的工具类.
 * 
 * @version 2013-01-15
 */
@Service
@Lazy(false)
public class IdGen implements IdGenerator, SessionIdGenerator {

	private static SecureRandom random = new SecureRandom();

	/**
	 * 数据中心id 用于snowflake 自定义主键生成算法使用
	 */
	private static final String DATA_CENTER_ID = Global.getConfig("idworker.datacenterId");

	/**
	 * 机器id或应用id（前台后台不一样） 用于snowflake 自定义主键生成算法使用
	 */
	private static final String WORKER_ID = Global.getConfig("idworker.workerId");

	private static IdWorker idWorker;

	static {
		int workerId = NumberUtils.toInt(WORKER_ID);
		int datacenterId = NumberUtils.toInt(DATA_CENTER_ID);
		idWorker = new IdWorker(datacenterId, workerId);
	}

	/**
	 * 使用idWoker生成主键
	 */
	public static String uuidByIdWorker() {
		if (idWorker != null) {
			return String.valueOf(idWorker.nextId());
		}
		return uuid();
	}

	/**
	 * 封装JDK自带的UUID, 通过Random数字生成, 中间无-分割.
	 */
	public static String uuid() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

	/**
	 * 使用SecureRandom随机生成Long.
	 */
	public static long randomLong() {
		return Math.abs(random.nextLong());
	}

	/**
	 * 基于Base62编码的SecureRandom随机生成bytes.
	 */
	public static String randomBase62(int length) {
		byte[] randomBytes = new byte[length];
		random.nextBytes(randomBytes);
		return Encodes.encodeBase62(randomBytes);
	}

	@Override
	public Serializable generateId(Session session) {
		return IdGen.uuid();
	}

	public static void main(String[] args) {
		System.out.println(IdGen.uuid());
		System.out.println(IdGen.uuid().length());
		for (int i = 0; i < 1000; i++) {
			System.out.println(IdGen.randomLong() + "  " + IdGen.randomBase62(5));
		}
	}

	@Override
	public UUID generateId() {
		// TODO Auto-generated method stub
		return null;
	}

}
