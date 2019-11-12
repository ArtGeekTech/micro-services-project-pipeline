package com.techbow.microservices.common.model.dvo;

import java.io.Serializable;

public class Payload implements Serializable {
    private Long clientId;
    private Double temperature;
    private Integer stepCount;

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Integer getStepCount() {
        return stepCount;
    }

    public void setStepCount(Integer stepCount) {
        this.stepCount = stepCount;
    }

    public Payload(Long clientId, Double temperature, Integer stepCount) {
        this.clientId = clientId;
        this.temperature = temperature;
        this.stepCount = stepCount;
    }

    @Override
    public String toString() {
        return "Payload{" +
                "clientId=" + clientId +
                ", temperature=" + temperature +
                ", stepCount=" + stepCount +
                '}';
    }
}
