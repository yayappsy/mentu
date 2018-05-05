package com.weimhc.framework.spring.support;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 多数据源实现-不区分主从
 * 
 * @author zsm
 * @version 1.0
 */

public class MultipleDataSource extends AbstractRoutingDataSource {

	@Override
	protected Object determineCurrentLookupKey() {
		return DataSourceKeyHolder.getDataSourceKey();
	}
}
