package com.test.warehousetest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "com.test.warehousetest.dao")
@SpringBootApplication
public class WarehouseTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(WarehouseTestApplication.class, args);
    }

}
