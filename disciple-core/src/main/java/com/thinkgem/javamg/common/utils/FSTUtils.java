/**
 * 
 */
package com.thinkgem.javamg.common.utils;

import org.nustaq.serialization.FSTConfiguration;

/**
 * @author shaozuo
 *
 */
public abstract class FSTUtils {

	static FSTConfiguration configuration = FSTConfiguration.createDefaultConfiguration();

	public static byte[] serializationObject(Object obj) {
		return configuration.asByteArray(obj);
	}

	public static Object deserializationObject(byte[] sec) {
		return configuration.asObject(sec);
	}

}
