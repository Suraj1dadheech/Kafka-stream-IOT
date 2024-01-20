package com.pdp.producer.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.apache.kafka.common.protocol.types.Field;

//@AllArgsConstructor
//@NoArgsConstructor
//@Getter
//@Setter
//@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Health {
    private String UUID;
    private Integer heartRate ;
    private Integer spo2 ;
    private Integer sleepDuration;
    private Integer caloriesBurned ;
    private Integer stepsTaken ;

    public Health() {
    }

    public Health(String UUID, Integer heartRate, Integer spo2, Integer sleepDuration, Integer caloriesBurned, Integer stepsTaken) {
        this.UUID = UUID;
        this.heartRate = heartRate;
        this.spo2 = spo2;
        this.sleepDuration = sleepDuration;
        this.caloriesBurned = caloriesBurned;
        this.stepsTaken = stepsTaken;
    }

    public String getUUID() {
        return UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    public Integer getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(Integer heartRate) {
        this.heartRate = heartRate;
    }

    public Integer getSpo2() {
        return spo2;
    }

    public void setSpo2(Integer spo2) {
        this.spo2 = spo2;
    }

    public Integer getSleepDuration() {
        return sleepDuration;
    }

    public void setSleepDuration(Integer sleepDuration) {
        this.sleepDuration = sleepDuration;
    }

    public Integer getCaloriesBurned() {
        return caloriesBurned;
    }

    public void setCaloriesBurned(Integer caloriesBurned) {
        this.caloriesBurned = caloriesBurned;
    }

    public Integer getStepsTaken() {
        return stepsTaken;
    }

    public void setStepsTaken(Integer stepsTaken) {
        this.stepsTaken = stepsTaken;
    }

    @Override
    public String toString() {
        return "{ \"UUID\" : \"" + UUID + "\", \"heartRate\" : " + heartRate + ", \"spo2\" : " + spo2 + ", \"sleepDuration\" : "+ sleepDuration + ", \"caloriesBurned\" : " + caloriesBurned + ", \"stepsTaken\" : " + stepsTaken + "}";
    }
}
