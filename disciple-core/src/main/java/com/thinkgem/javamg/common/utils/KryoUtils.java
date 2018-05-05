/**
 * 
 */
package com.thinkgem.javamg.common.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

/**
 * 对象序列化/反序列化工具类
 * 
 * @version 2014-6-29
 */
public abstract class KryoUtils {

	private static Logger logger = LoggerFactory.getLogger(KryoUtils.class);

	private static final Kryo kryo = new Kryo();

	static {
		kryo.setReferences(false);
		kryo.setRegistrationRequired(false);
		kryo.setAutoReset(false);
		kryo.getFieldSerializerConfig().setUseAsm(true);
		kryo.register(ArrayList.class);
		kryo.register(HashMap.class);
		kryo.register(Object.class);
	}

	public static byte[] serializationObject(Object object) {
		ByteArrayOutputStream baos = null;
		Output output = null;
		try {
			if (object != null) {
				baos = new ByteArrayOutputStream();
				output = new Output(baos);
				kryo.writeClassAndObject(output, object);
				output.flush();
				return baos.toByteArray();
			}
		} finally {
			if (output != null) {
				output.close();
			}
		}
		return null;

	}

	public static Object deserializationObject(byte[] bytes) {
		ByteArrayInputStream bais = null;
		try {
			if (bytes != null && bytes.length > 0) {
				bais = new ByteArrayInputStream(bytes);
				Input input = new Input(bais);
				return kryo.readClassAndObject(input);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return null;

	}

	public static <T extends Serializable> byte[] serializationObject(T object) {
		ObjectOutputStream oos = null;
		ByteArrayOutputStream baos = null;
		try {
			if (object != null) {
				baos = new ByteArrayOutputStream();
				oos = new ObjectOutputStream(baos);
				oos.writeObject(object);
				return baos.toByteArray();
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return null;

	}

	@SuppressWarnings("unchecked")
	public static <T extends Serializable> T deserializationObject(byte[] bytes, Class<T> clazz) {
		ByteArrayInputStream bais = null;
		try {
			if (bytes != null && bytes.length > 0) {
				bais = new ByteArrayInputStream(bytes);
				Input input = new Input(bais);
				return (T) kryo.readClassAndObject(input);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return null;

	}
}
