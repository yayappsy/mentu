/**
 * 
 */
package com.thinkgem.javamg.common.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * 序列化
 * 
 * @author shaozuo
 *
 */
public abstract class JRedisSerializationUtils {

	/**
	 * 获取byte[]类型Key
	 * 
	 * @param key
	 * @return
	 */
	public static byte[] getBytesKey(Object object) {
		if (object instanceof String) {
			return StringUtils.getBytes((String) object);
		} else {
			return FSTUtils.serializationObject(object);
		}
	}

	/**
	 * Object转换byte[]类型
	 * 
	 * @param key
	 * @return
	 */
	public static byte[] toBytes(Object object) {
		return FSTUtils.serializationObject(object);
	}

	/**
	 * byte[]型转换Object
	 * 
	 * @param key
	 * @return
	 */
	public static Object toObject(byte[] bytes) {
		return FSTUtils.deserializationObject(bytes);
	}

	// jdk原生序列换方案
	public static byte[] jdkserialize(Object obj) {
		try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
				ObjectOutputStream oos = new ObjectOutputStream(baos);) {
			oos.writeObject(obj);
			return baos.toByteArray();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static Object jdkdeserialize(byte[] bits) {
		try (ByteArrayInputStream bais = new ByteArrayInputStream(bits);
				ObjectInputStream ois = new ObjectInputStream(bais);) {
			return ois.readObject();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
