package com.pdp.producer.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Mobile {
    private String UUID;
    private Integer callDuration;
    private Integer smsCount;
    private Double dataUsage;
    private Double internalStorageUsage;
    private Double externalStorageUsage;
    private Double cpuUsage;
    private Integer lockUnlockEvents;
}
