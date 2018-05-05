/**
 * 
 */
package com.weimhc.framework.jms.producer.queue;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

/**
 * @author szuo
 *
 */
@Service
public class AlertServiceImpl implements AlertService {

	@Autowired
	JmsTemplate jmsTemplate;

	/* (non-Javadoc)
	 * @see com.thinkgem.javamg.online.jms.service.AlertService#sendAlert(com.thinkgem.javamg.online.entity.Student)
	 */
	@Override
	public void sendAlert(final String message) {
		jmsTemplate.send("online.queue", new MessageCreator() {

			@Override
			public Message createMessage(Session session) throws JMSException {
				// TODO Auto-generated method stub
				return session.createTextMessage(message);
			}
		});

	}

}
