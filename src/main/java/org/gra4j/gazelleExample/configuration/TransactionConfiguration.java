package org.gra4j.gazelleExample.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;

/**
 * Created by Administrator on 2017/12/5.
 */
@Configuration
@EnableTransactionManagement
public class TransactionConfiguration {

    @Autowired
    @Qualifier("entityManagerFactoryPrimary")
    private LocalContainerEntityManagerFactoryBean entityManagerFactoryPrimary;


    @Bean(name = "entityManagerPrimary")
    public EntityManager entityManager() {
        return entityManagerFactoryPrimary.getObject().createEntityManager();
    }

    @Bean(name = "transactionManagerPrimary")
    PlatformTransactionManager transactionManagerPrimary() {
        return new JpaTransactionManager(entityManagerFactoryPrimary.getObject());
    }
}
