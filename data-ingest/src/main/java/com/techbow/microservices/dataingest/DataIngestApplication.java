package com.techbow.microservices.dataingest;

import com.techbow.microservices.common.config.Constant;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.List;


@SpringBootApplication
@EnableDiscoveryClient
@EnableRabbit
public class DataIngestApplication {

    @Bean
    public Exchange dataExchange() {
        return new TopicExchange(Constant.EXCHANGE_NAME);
    }

    @Bean
    public Queue queue1() {
        return new Queue(Constant.QUEUE_NAME1);
    }

    @Bean
    public Queue queue2() {
        return new Queue(Constant.QUEUE_NAME2);
    }

    @Bean
    List<Binding> bindings() {
        return Arrays.asList(
                BindingBuilder.bind(queue1()).to(dataExchange()).with(Constant.ROUTING_KEY_HISTORY).noargs(),
                BindingBuilder.bind(queue2()).to(dataExchange()).with(Constant.ROUTING_KEY_REALTIME).noargs()
        );
    }

    public static void main(String[] args) {
        SpringApplication.run(DataIngestApplication.class, args);
    }
}
