package com.techbow.microservices.dataingest.controller;

import com.techbow.microservices.common.config.Constant;
import com.techbow.microservices.common.model.dvo.Payload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class DataIngestController {
    private static final Logger logger = LoggerFactory.getLogger(DataIngestController.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostMapping("ingest/data")
    public Payload ingest(@Valid @RequestBody Payload payload) { // validate payload from request body

        // preprocess data: e.g. standardize data format, type conversion, int --> enum etc
        payload.setTemperature(Math.round(payload.getTemperature() * 100.0) / 100.0);

        // publish to MQ, both history & realtime queues
        rabbitTemplate.convertAndSend(Constant.EXCHANGE_NAME, Constant.ROUTING_KEY_HISTORY, payload);
        rabbitTemplate.convertAndSend(Constant.EXCHANGE_NAME, Constant.ROUTING_KEY_REALTIME, payload);

        logger.info("ingested to MQ: " + payload.toString());
        return payload;
    }
}
