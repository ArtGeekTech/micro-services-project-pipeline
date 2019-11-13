package com.techbow.microservices.dataingest;

import com.techbow.microservices.common.config.Constant;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Declarables;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
@EnableDiscoveryClient
@EnableRabbit
public class DataIngestApplication {

    @Bean
    public Declarables topicBindings() {
        TopicExchange topicExchange = new TopicExchange(Constant.EXCHANGE_NAME);
        Queue topicQueue1 = new Queue(Constant.QUEUE_HISTORY, false);
        Queue topicQueue2 = new Queue(Constant.QUEUE_REALTIME, false);
        return new Declarables(
                topicExchange,
                topicQueue1,
                topicQueue2,
                BindingBuilder.bind(topicQueue1).to(topicExchange).with(Constant.ROUTING_KEY_HISTORY),
                BindingBuilder.bind(topicQueue2).to(topicExchange).with(Constant.ROUTING_KEY_REALTIME)
        );
    }

    public static void main(String[] args) {
        SpringApplication.run(DataIngestApplication.class, args);
    }
}
