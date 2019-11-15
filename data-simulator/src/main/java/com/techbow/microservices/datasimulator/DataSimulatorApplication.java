package com.techbow.microservices.datasimulator;

import com.techbow.microservices.common.model.dvo.Payload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

@SpringBootApplication
@EnableDiscoveryClient
public class DataSimulatorApplication {

    private static final Logger logger = LoggerFactory.getLogger(DataSimulatorApplication.class);

    private static final String resourceUrl = "http://localhost:9001/ingest/data";

    private static final RestTemplate restTemplate = new RestTemplate();

    private static Timer timer = new Timer();

    public static void main(String[] args) {
        SpringApplication.run(DataSimulatorApplication.class, args);

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {

                Payload payload = new Payload((long) genRandom(), genRandom(), (int) genRandom());

                HttpEntity<Payload> request = new HttpEntity<>(payload);

                restTemplate.postForObject(resourceUrl, request, Object.class);

                logger.info("POST to: " + resourceUrl + " with request: " + request.toString());

            }
        }, 0, 10000);
    }

    private static double genRandom() {
        Random random = new Random();
        double minVal = 20;
        double maxVal = 100;
        return minVal + random.nextDouble() * (maxVal - minVal);
    }
}
