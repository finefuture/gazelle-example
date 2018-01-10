package org.gra4j.gazelleExample.util.web;

import org.gra4j.gazelleExample.util.ClassCache;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.metamodel.EntityType;
import java.util.Iterator;
import java.util.Set;

@Component
public class ContextRefreshedListener implements ApplicationListener<ContextRefreshedEvent> {

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		EntityManager em = (EntityManager) event.getApplicationContext().getBean("entityManagerPrimary");
		Set<EntityType<?>> entitys = em.getMetamodel().getEntities();
		
		Iterator<EntityType<?>> iter = entitys.iterator();
		while(iter.hasNext()){
			EntityType<?> et = iter.next();
			ClassCache.setValueByClass(et.getJavaType());
		}
	}

}
