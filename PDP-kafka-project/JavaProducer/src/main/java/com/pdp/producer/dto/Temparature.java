package com.pdp.producer.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Temparature {
    private String UUID;
    private Double temperature;
    private Double humidity;
    private Double pressure;
    private Double windSpeed;
    private Double windDirection;
}
