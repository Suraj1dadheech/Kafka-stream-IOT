package com.pdp.producer.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Car {
    private String UUID;
    private Double speed;
    private Integer rpm;
    private Double fuelLevel;
    private Double engineTemperature;
    private Double odometer;
    private Double fuelEfficiency;
    private Boolean airbagStatus;
    private Double latitude;
    private Double longitude;
}
