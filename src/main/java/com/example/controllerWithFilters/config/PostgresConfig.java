package com.example.controllerWithFilters.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Getter
@Setter
@Configuration
public class PostgresConfig {
    @Value("${spring.data.postgres.driver-class-name}")
    private String driverClassName;
    @Value("${spring.data.postgres.url}")
    private String url;
    @Value("${spring.data.postgres.schema}")
    private String schema;
    @Value("${spring.data.postgres.username}")
    private String username;
    @Value("${spring.data.postgres.password}")
    private String password;

    @Bean
    public DataSource getDataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setSchema(schema);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }
}
