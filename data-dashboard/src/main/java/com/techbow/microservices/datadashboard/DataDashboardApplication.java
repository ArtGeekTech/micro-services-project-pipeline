package com.techbow.microservices.datadashboard;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication(scanBasePackages = "com.techbow.microservices")
@EnableJpaAuditing
@EnableCaching
@EnableDiscoveryClient
@EnableRabbit
@EnableJpaRepositories(basePackages={"com.techbow.microservices.common.model.dao"})
@EntityScan(basePackages={"com.techbow.microservices.common.model.dvo"})
public class DataDashboardApplication {

    public static void main(String[] args) {
        SpringApplication.run(DataDashboardApplication.class, args);
    }
}
