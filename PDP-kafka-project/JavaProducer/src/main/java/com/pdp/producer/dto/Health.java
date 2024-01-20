package com.pdp.producer.dto;

import lombok.*;
import org.apache.kafka.common.protocol.types.Field;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Health {
    private String UUID;
    private Integer heartRate ;
    private Integer spo2 ;
    private Integer sleepDuration;
    private Integer caloriesBurned ;
    private Integer stepsTaken ;
}
