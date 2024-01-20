package com.pdp.producer;

import com.pdp.producer.service.producerService.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JavaProducerApplication {


    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(JavaProducerApplication.class, args);

        Thread temperatureThread = new Thread(new TemperatureMonitoringDevice());
        Thread healthThread = new Thread(new HealthMonitoringDevice());
        Thread carThread = new Thread(new CarMonitoringDevice());
        Thread mobile = new Thread(new MobilePhoneMonitoring());
        Thread cctv = new Thread(new CCTVMonitoring());
        temperatureThread.start();
        healthThread.start();
        carThread.start();
        cctv.start();
        mobile.start();
    }

}
