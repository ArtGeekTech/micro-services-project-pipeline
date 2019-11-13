package com.techbow.microservices.dataprocessor.service;

import com.techbow.microservices.common.model.dvo.Payload;
import org.springframework.stereotype.Component;

/**
 * Created by guang on 7:16 PM 8/21/18.
 */
@Component
public class RuleEngineService {

    public void applyRulesByCallingREST(Payload data) {

        if (data.getTemperature() > 55.5) {
            triggerActionAlert("Temperature too high, pls cool down your body!!!");
        }
        if (data.getStepCount() < 25) {
            triggerActionAlert("StepCount too little, get up and work out!!!");
        }
    }

    private void triggerActionAlert(String msg) {
        System.out.println("\n\nAction Triggered! \n!!!!!Sending the App Notification Alert: " + msg + "\n\n");
        // Actions performed here, e.g. send app notification, send email, send text...
    }
}
