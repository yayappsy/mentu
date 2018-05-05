package com.weimhc.framework.spring.aop;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import com.weimhc.framework.spring.support.DataSourceKeyHolder;
import com.weimhc.framework.spring.support.ReflectUtil;
import com.weimhc.framework.spring.support.annotation.TargetDataSource;

/**
 * <li>类描述：完成数据源的切换，抽类切面，具体项目继承一下，不需要重写即可使用</li>
 *
 * @author： zsm
 * 
 * @since v1.0
 */
//@Aspect
public abstract class DynamicDataSourceAspect {

	protected static final ThreadLocal<String> PRE_DATA_SOURCE_HOLDER = new ThreadLocal<String>();

	@Pointcut("@annotation(com.weimhc.framework.spring.support.annotation.TargetDataSource)")
	public void methodWithTargetDataSourceAnnotation() {

	}

	/**
	 * 根据@TargetDataSource的属性值设置不同的dataSourceKey,以供DynamicDataSource
	 */
	@Before("methodWithTargetDataSourceAnnotation()")
	public void changeDataSourceBeforeMethodExecution(JoinPoint jp) {
		//拿到anotation中配置的数据源
		String resultDS = determineDataSource(jp);
		//没有配置实用默认数据源
		if (resultDS == null) {
			DataSourceKeyHolder.setDataSourceKey(null);
			return;
		}
		PRE_DATA_SOURCE_HOLDER.set(DataSourceKeyHolder.getDataSourceKey());
		//将数据源设置到数据源持有者
		DataSourceKeyHolder.setDataSourceKey(resultDS);

	}

	/**
	 * <li>获取注解中配置的数据源</li>
	 * 
	 * @param jp
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	protected String determineDataSource(JoinPoint jp) {
		String methodName = jp.getSignature().getName();
		Class targetClass = jp.getSignature().getDeclaringType();
		String dataSourceForTargetClass = resolveDataSourceFromClass(
				targetClass);
		String dataSourceForTargetMethod = resolveDataSourceFromMethod(
				targetClass, methodName);
		String resultDS = determinateDataSource(dataSourceForTargetClass,
				dataSourceForTargetMethod);
		return resultDS;
	}

	/**
	 * 方法执行完毕以后，数据源切换回之前的数据源。 比如foo()方法里面调用bar()，但是bar()另外一个数据源，
	 * bar()执行时，切换到自己数据源，执行完以后，要切换到foo()所需要的数据源，以供 foo()继续执行。
	 * <p>
	 * 创建时间： 2013-8-16 下午4:27:06
	 * </p>
	 */
	@After("methodWithChooseAnnotation()")
	public void restoreDataSourceAfterMethodExecution() {
		DataSourceKeyHolder.setDataSourceKey(PRE_DATA_SOURCE_HOLDER.get());
		PRE_DATA_SOURCE_HOLDER.remove();
	}

	/**
	 * <li>方法级别的 @TargetDataSource 的解析</li>
	 *
	 * @param targetClass
	 * @param methodName
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	private String resolveDataSourceFromMethod(Class targetClass,
			String methodName) {

		Method m = ReflectUtil.findUniqueMethod(targetClass, methodName);
		if (m != null) {
			TargetDataSource choDs = m.getAnnotation(TargetDataSource.class);
			return resolveDataSourceName(choDs);
		}
		return null;
	}

	/**
	 * <li>创建时间： 2013-6-17 下午5:06:02</li>
	 * <li>方法描述 : 确定
	 * 最终数据源，如果方法上设置有数据源，则以方法上的为准，如果方法上没有设置，则以类上的为准，如果类上没有设置，则使用默认数据源</li>
	 *
	 * @param classDS
	 * @param methodDS
	 * @return
	 */
	private String determinateDataSource(String classDS, String methodDS) {
		// 两者必有一个不为null,如果两者都为Null，也会返回Null
		return methodDS == null ? classDS : methodDS;
	}

	/**
	 * <li>方法描述 : 类级别的 @TargetDataSource 的解析</li>
	 *
	 * @param targetClass
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private String resolveDataSourceFromClass(Class targetClass) {
		TargetDataSource classAnnotation = (TargetDataSource) targetClass
				.getAnnotation(TargetDataSource.class);
		// 直接为整个类进行设置
		return null != classAnnotation ? resolveDataSourceName(classAnnotation)
				: null;
	}

	/**
	 * <li>方法描述 : 组装DataSource的名字</li>
	 *
	 * @param ds
	 * @return
	 */
	private String resolveDataSourceName(TargetDataSource ds) {
		return ds == null ? null : ds.value();
	}

}
