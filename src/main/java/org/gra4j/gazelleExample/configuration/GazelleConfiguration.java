package org.gra4j.gazelleExample.configuration;

import org.gra4j.gazelle.JPAQuery.core.Jpa;
import org.gra4j.gazelle.repository.JpaContext;
import org.gra4j.gazelle.repository.register.EnableGazelleRepository;
import org.gra4j.gazelle.transaction.TransactionalType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by Administrator on 2017/12/28.
 */
@Configuration
@EnableGazelleRepository(basePackages = "org.gra4j.gazelleExample.crud.dao.jpa")
public class GazelleConfiguration {

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    PlatformTransactionManager tx;

    @Bean
    @PostConstruct
    public Object jpa () {
        JpaContext.setEntityManager(entityManager);
        JpaContext.setTransactionType(TransactionalType.spring);
        JpaContext.setSpringTransactionManager(tx);
        return new Object();
    }

}
