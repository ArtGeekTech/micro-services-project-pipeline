package com.techbow.microservices.datadashboard.controller;

import com.techbow.microservices.common.config.Constant;
import com.techbow.microservices.common.model.dvo.Payload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {

    private static final Logger logger = LoggerFactory.getLogger(WebSocketController.class);

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @RabbitListener(queues = {Constant.QUEUE_REALTIME})  // Subscribe to the Message Queue
    public void sendRealTimeData(Payload payload) {
        logger.info("Received message payload from MQ '{}'", payload);
        messagingTemplate.convertAndSend("/topic/dashboard-realtime", payload);
    }
}
