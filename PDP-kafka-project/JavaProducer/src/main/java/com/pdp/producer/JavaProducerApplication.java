package com.pdp.producer;

import com.pdp.producer.service.producerService.Health.*;
import com.pdp.producer.service.producerService.Temperature.*;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JavaProducerApplication {


    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(JavaProducerApplication.class, args);

        Thread temperatureThread = new Thread(new TemperatureMonitoringDevice1());
        Thread temperatureThread2 = new Thread(new TemperatureMonitoringDevice2());
        Thread temperatureThread3 = new Thread(new TemperatureMonitoringDevice3());
        Thread temperatureThread4 = new Thread(new TemperatureMonitoringDevice4());
        Thread temperatureThread5 = new Thread(new TemperatureMonitoringDevice5());
        Thread healthThread = new Thread(new HealthMonitoringDevice1());
        Thread healthThread2 = new Thread(new HealthMonitoringDevice2());
        Thread healthThread3 = new Thread(new HealthMonitoringDevice3());
        Thread healthThread4 = new Thread(new HealthMonitoringDevice4());
        Thread healthThread5 = new Thread(new HealthMonitoringDevice5());

//        Thread carThread = new Thread(new CarMonitoringDevice());
//        Thread mobile = new Thread(new MobilePhoneMonitoring());
//        Thread cctv = new Thread(new CCTVMonitoring());
//        Thread smartTv = new Thread(new SmartTvMonitoring());

        temperatureThread.start();
        temperatureThread2.start();
        temperatureThread3.start();
        temperatureThread4.start();
        temperatureThread5.start();

        healthThread.start();
        healthThread2.start();
        healthThread3.start();
        healthThread4.start();
        healthThread5.start();


//        carThread.start();
//        cctv.start();
//        smartTv.start();
//        mobile.start();

    }

}
