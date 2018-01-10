package org.gra4j.gazelleExample.configuration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * Created by Administrator on 2017/12/5.
 */
@Configuration
public class DataSourceConfiguration {

    @Value("${spring.datasource.type}")
    private Class<? extends DataSource> dataSourceType;

    @Bean
    @Primary
    @Qualifier("jpaDataSource")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource createJpaDataSource() {
        return DataSourceBuilder.create().type(dataSourceType).build();
    }

}
