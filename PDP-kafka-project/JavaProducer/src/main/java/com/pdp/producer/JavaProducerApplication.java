package com.pdp.producer;

import com.pdp.producer.service.producerService.HealthMonitoringDevice;
import com.pdp.producer.service.producerService.TemperatureMonitoringDevice;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JavaProducerApplication {
	

	public static void main(String[] args) throws InterruptedException {
		SpringApplication.run(JavaProducerApplication.class, args);

		Thread temperatureThread = new Thread(new TemperatureMonitoringDevice());
		Thread healthThread = new Thread(new HealthMonitoringDevice());
		temperatureThread.start();
		healthThread.start();
	}

}
