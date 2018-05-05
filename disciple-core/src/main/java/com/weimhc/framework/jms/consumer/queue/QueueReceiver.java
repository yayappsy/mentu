package com.weimhc.framework.jms.consumer.queue;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 消息接收者
 * 
 * @author szuo
 * 
 */
@Component
public class QueueReceiver implements MessageListener {

	private static Logger logger = LoggerFactory.getLogger(QueueReceiver.class);

	@Override
	public void onMessage(Message message) {
		try {
			System.out.println(
					"QueueReceiver1接收到消息:" + ((TextMessage) message).getText());
		} catch (JMSException e) {
			logger.error(e.getMessage(), e);
		}

	}

}
