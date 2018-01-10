package org.gra4j.gazelleExample.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.sql.DataSource;
import java.util.Map;

/**
 * Created by Administrator on 2017/12/5.
 */
@Configuration
public class EntityManageConfiguration {

    @Autowired
    @Qualifier("jpaDataSource")
    private DataSource primaryDataSource;

    @Autowired
    private JpaProperties jpaProperties;

    private Map<String, String> getVendorProperties(DataSource dataSource) {
        return jpaProperties.getHibernateProperties(dataSource);
    }

    @Bean(name = "entityManagerFactoryPrimary")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryCreate(EntityManagerFactoryBuilder builder) {
        return builder.dataSource(primaryDataSource).properties(getVendorProperties(primaryDataSource))
                .packages("org.gra4j.gazelleExample.crud.entity")
                .persistenceUnit("primaryPersistenceUnit").build();
    }

}
