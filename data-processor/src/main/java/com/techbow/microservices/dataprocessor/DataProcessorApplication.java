package com.techbow.microservices.dataprocessor;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by guang on 11:42 AM 8/18/18.
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableRabbit
@ComponentScan(basePackages={"com.techbow.microservices.common.model"})
@EnableJpaRepositories(basePackages={"com.techbow.microservices.common.model.dao"})
@EntityScan(basePackages={"com.techbow.microservices.common.model.dvo"})
public class DataProcessorApplication {

//    @Bean
//    public Exchange dataExchange() {
//        return new TopicExchange(Constant.EXCHANGE_NAME);
//    }
//
//    @Bean
//    public Queue queue() {
//        return new Queue(Constant.QUEUE_NAME1);
//    }
//
//    @Bean
//    public Binding binding(Queue queue, Exchange dataExchange) {
//        return BindingBuilder
//                .bind(queue)
//                .to(dataExchange)
//                .with(Constant.ROUTING_KEY_HISTORY)
//                .noargs();
//    }

//    @Bean
//    public DataDao dataDao() {
//        return new DataJpaDao();
//    }

    public static void main(String[] args) {
        SpringApplication.run(DataProcessorApplication.class, args);
    }
}
