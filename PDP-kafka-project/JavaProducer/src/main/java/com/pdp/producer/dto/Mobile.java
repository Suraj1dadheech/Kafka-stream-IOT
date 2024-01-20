package com.pdp.producer.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
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
