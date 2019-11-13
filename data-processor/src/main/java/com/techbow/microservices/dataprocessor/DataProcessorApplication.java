package com.techbow.microservices.dataprocessor;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by guang on 11:42 AM 8/18/18.
 */
@SpringBootApplication(scanBasePackages = "com.techbow.microservices")
@EnableDiscoveryClient
@EnableRabbit
//@ComponentScan(basePackages={"com.techbow.microservices.common.model"})
@EnableJpaRepositories(basePackages={"com.techbow.microservices.common.model.dao"})
@EntityScan(basePackages={"com.techbow.microservices.common.model.dvo"})
public class DataProcessorApplication {

//    @Bean
//    public Declarables topicBindings() {
//        TopicExchange topicExchange = new TopicExchange(Constant.EXCHANGE_NAME);
//        Queue topicQueue1 = new Queue(Constant.QUEUE_HISTORY, true);
//        return new Declarables(
//                topicExchange,
//                topicQueue1,
//                BindingBuilder.bind(topicQueue1).to(topicExchange).with(Constant.ROUTING_KEY_HISTORY)
//        );
//    }

    public static void main(String[] args) {
        SpringApplication.run(DataProcessorApplication.class, args);
    }
}
