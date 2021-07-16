package cn.com.ktm.mt.model.util.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanTools implements ApplicationContextAware {
	
	private static ApplicationContext applicationContext;

	public void setApplicationContext(ApplicationContext context) {
		applicationContext = context;
	}

	public static <T> T getBean(String classname, Class<T> requiredType) {
		try {
			T bean = applicationContext.getBean(classname, requiredType);
			return bean;
		} catch (Exception e) {
			return null;
		}
	}

	public static void setApplicationContext1(ApplicationContext context) {
		applicationContext = context;
	}
}
