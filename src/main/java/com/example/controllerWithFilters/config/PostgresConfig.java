package com.example.controllerWithFilters.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "spring.data.postgres")
public class PostgresConfig {
    @Value("${driver-class-name}")
    private String driverClassName;
    @Value("${url}")
    private String url;
    @Value("${schema}")
    private String schema;
    @Value("${username}")
    private String username;
    @Value("${password}")
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
