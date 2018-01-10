package org.gra4j.gazelleExample.util.web;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component("springContextUtils")
public class SpringContextUtils implements ApplicationContextAware {

	private static ApplicationContext applicationContext;


	public void setApplicationContext(ApplicationContext applicationContext) {
		SpringContextUtils.applicationContext = applicationContext;
	}

	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	public static Object getBean(String name) throws BeansException {
		return applicationContext.getBean(name);
	}

	public static Object getBean(Class<?> clz){
		return applicationContext.getBean(clz);
	}

	public static Map<String, ?> getBeans(Class<?> clz){
		return applicationContext.getBeansOfType(clz);
	}

	public static boolean loadSuccess () {
		return applicationContext!=null;
	}
}
