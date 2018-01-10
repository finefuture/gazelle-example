package org.gra4j.gazelleExample.configuration;

import org.gra4j.gazelle.core.*;
import org.gra4j.gazelle.modify.Setter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by Administrator on 2017/12/28.
 */
@Configuration
public class GazelleConfiguration {

    @PersistenceContext
    EntityManager entityManager;

    @Bean
    public Criterion criterion () {
        return new Criterion(jpa());
    }

    @Bean
    public Jpa jpa () {
        return new Jpa(entityManager);
    }

    @Bean
    public Special special () {
        return new Special(jpa());
    }

    @Bean
    public Where where () {
        return new Where(jpa());
    }

    @Bean
    public Recovery recovery () {
        return new Recovery(jpa(), special(), where());
    }

    @Bean
    public Setter setter () {
        return new Setter(jpa());
    }

}
