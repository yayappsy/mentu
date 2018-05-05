/**
 * 
 */
package com.thinkgem.javamg.common.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.javamg.common.service.BaseService;

/**
 * Service基类
 * 
 * @version 2014-05-16
 */
@Transactional(readOnly = true)
public abstract class BaseServiceImpl implements BaseService {

	/**
	 * 日志对象
	 */
	protected Logger logger = LoggerFactory.getLogger(getClass());

}
