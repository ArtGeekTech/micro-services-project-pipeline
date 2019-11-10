package com.techbow.microservices.common.model.dvo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.io.Serializable;

// JPA  Java Persistence API
@Entity
@Table(name = "data")
@JsonIgnoreProperties(value = {"id"}, allowGetters = true)
@EntityListeners(AuditingEntityListener.class)
public class Data implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long clientId;

    @NotNull
    @Min(20)
    @Max(100)
    private Double temperature;

    @NotNull
    @PositiveOrZero
    private Integer stepCount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    @Override
    public String toString() {
        return "Data{" +
                "id=" + id +
                ", clientId=" + clientId +
                ", temperature=" + temperature +
                ", stepCount=" + stepCount +
                '}';
    }
}
