package com.weimhc.framework.mybatis.support;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionUtils;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * <b>function:</b> MyBatis 动态SqlSessionFactory
 * 
 * @author hoojo
 * @createDate 2013-10-14 下午02:32:19
 * @file DynamicSqlSessionDaoSupport.java
 * @package com.hoo.framework.mybatis.support
 * @project SHMB
 * @blog http://blog.csdn.net/IBM_hoojo
 * @email hoojo_@126.com
 * @version 1.0
 */
public class DynamicSqlSessionDaoSupport extends SqlSessionDaoSupport
		implements ApplicationContextAware {

	private ApplicationContext applicationContext;

	private Map<Object, SqlSessionFactory> targetSqlSessionFactorys;
	private SqlSessionFactory defaultTargetSqlSessionFactory;
	private SqlSession sqlSession;

	@Override
	public final SqlSession getSqlSession() {
		SqlSessionFactory targetSqlSessionFactory = targetSqlSessionFactorys
				.get(CustomerContextHolder.getContextType());
		if (targetSqlSessionFactory != null) {
			setSqlSessionFactory(targetSqlSessionFactory);
		} else if (defaultTargetSqlSessionFactory != null) {
			setSqlSessionFactory(defaultTargetSqlSessionFactory);
			targetSqlSessionFactory = defaultTargetSqlSessionFactory;
		} else {
			targetSqlSessionFactory = (SqlSessionFactory) applicationContext
					.getBean(CustomerContextHolder.getContextType());
			setSqlSessionFactory(targetSqlSessionFactory);
		}
		this.sqlSession = SqlSessionUtils
				.getSqlSession(targetSqlSessionFactory);
		return this.sqlSession;
	}

	@Override
	protected void checkDaoConfig() {
		//Assert.notNull(getSqlSession(), "Property 'sqlSessionFactory' or 'sqlSessionTemplate' are required");
	}

	public void setTargetSqlSessionFactorys(
			Map<Object, SqlSessionFactory> targetSqlSessionFactorys) {
		this.targetSqlSessionFactorys = targetSqlSessionFactorys;
	}

	public void setDefaultTargetSqlSessionFactory(
			SqlSessionFactory defaultTargetSqlSessionFactory) {
		this.defaultTargetSqlSessionFactory = defaultTargetSqlSessionFactory;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;
	}
}
