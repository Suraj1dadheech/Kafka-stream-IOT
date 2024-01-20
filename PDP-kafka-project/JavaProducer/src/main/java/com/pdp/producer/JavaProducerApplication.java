package com.pdp.producer;

import com.pdp.producer.service.producerService.CarMonitoringDevice;
import com.pdp.producer.service.producerService.HealthMonitoringDevice;
import com.pdp.producer.service.producerService.MobilePhoneMonitoring;
import com.pdp.producer.service.producerService.TemperatureMonitoringDevice;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JavaProducerApplication {
	

	public static void main(String[] args) throws InterruptedException {
		SpringApplication.run(JavaProducerApplication.class, args);

		Thread temperatureThread = new Thread(new TemperatureMonitoringDevice());
		Thread healthThread = new Thread(new HealthMonitoringDevice());
		Thread carThread = new Thread(new CarMonitoringDevice());
		Thread mobile =new Thread(new MobilePhoneMonitoring());
		temperatureThread.start();
		healthThread.start();
		carThread.start();
		mobile.start();
	}

}
