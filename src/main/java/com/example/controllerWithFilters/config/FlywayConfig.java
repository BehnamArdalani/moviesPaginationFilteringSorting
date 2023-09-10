package com.example.controllerWithFilters.config;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.Setter;
import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Getter
@Setter
@Configuration
public class FlywayConfig {

    @Autowired
    private DataSource dataSource;

    private ApplicationContext applicationContext;

    @PostConstruct
    public void performMigration(){
        Flyway flyway = Flyway.configure().dataSource(dataSource).load();
        flyway.migrate();
    }

}
