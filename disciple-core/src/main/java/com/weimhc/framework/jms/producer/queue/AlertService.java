/**
 * 
 */
package com.weimhc.framework.jms.producer.queue;

/**
 * jms Service
 * 
 * @author zsm
 * @version 2015-11-20
 */
public interface AlertService {
	void sendAlert(String message);
}