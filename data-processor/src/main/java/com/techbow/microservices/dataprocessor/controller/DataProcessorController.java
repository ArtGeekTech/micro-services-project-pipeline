package com.techbow.microservices.dataprocessor.controller;

import com.techbow.microservices.common.config.Constant;
import com.techbow.microservices.common.model.dao.DataDao;
import com.techbow.microservices.common.model.dvo.Data;
import com.techbow.microservices.common.model.dvo.Payload;
import com.techbow.microservices.dataprocessor.service.RuleEngineService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class DataProcessorController {
    private static final Logger logger = LoggerFactory.getLogger(DataProcessorController.class);

    @Autowired
    private DataDao dataDao;

    @Autowired
    private RuleEngineService ruleEngineService;


    @RabbitListener(queues = Constant.QUEUE_NAME1)  // Subscribe to the Message Queue of history data
    public void process(Payload payload) {

        logger.info("Consumed payload from MQ '{}'", payload);

        // apply rule engine and trigger actions
        ruleEngineService.applyRules(payload);

        // save to DB
        dataDao.save(convert(payload));

        logger.info("Created data to MySQL '{}'", payload);
        logger.info("Total data num in MySQL is:  " + dataDao.findAll(null).size());
    }

    private Data convert(Payload payload) {
        Data data = new Data();
        data.setClientId(payload.getClientId());
        data.setTemperature(payload.getTemperature());
        data.setStepCount(payload.getStepCount());
        return data;
    }
}
