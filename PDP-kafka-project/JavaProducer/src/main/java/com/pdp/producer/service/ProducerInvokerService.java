package com.pdp.producer.service;

import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.stereotype.Service;

import com.pdp.producer.config.KafkaConfig;
import com.pdp.producer.service.producerService.Health.HealthMonitoringDevice;
import com.pdp.producer.service.producerService.Health.HealthMonitoringDevice2;
import com.pdp.producer.service.producerService.Health.HealthMonitoringDevice3;
import com.pdp.producer.service.producerService.Health.HealthMonitoringDevice4;
import com.pdp.producer.service.producerService.Health.HealthMonitoringDevice5;
import com.pdp.producer.service.producerService.Temperature.WeatherMonitoringDevice;
import com.pdp.producer.service.producerService.Temperature.TemperatureMonitoringDevice2;
import com.pdp.producer.service.producerService.Temperature.TemperatureMonitoringDevice3;
import com.pdp.producer.service.producerService.Temperature.TemperatureMonitoringDevice4;
import com.pdp.producer.service.producerService.Temperature.TemperatureMonitoringDevice5;

@Service
public class ProducerInvokerService {

//	@Autowired
	private KafkaConfig kafkaConfig = new KafkaConfig();

	Producer<String, String> producer = kafkaConfig.invokeKafkaConfig();

	public void invokeProducer() {
		initiateThreads();
	}

	public void initiateThreads() {
		System.out.println("Threads are ready to initiate.................");
		Thread temperatureThread = new Thread(new WeatherMonitoringDevice());
		Thread temperatureThread2 = new Thread(new WeatherMonitoringDevice());
		Thread temperatureThread3 = new Thread(new WeatherMonitoringDevice());
		Thread temperatureThread4 = new Thread(new WeatherMonitoringDevice());
		Thread temperatureThread5 = new Thread(new WeatherMonitoringDevice());

		Thread healthThread = new Thread(new HealthMonitoringDevice());
		Thread healthThread2 = new Thread(new HealthMonitoringDevice());
		Thread healthThread3 = new Thread(new HealthMonitoringDevice());
		Thread healthThread4 = new Thread(new HealthMonitoringDevice());
		Thread healthThread5 = new Thread(new HealthMonitoringDevice());

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
	}

	public void produceIOTdataToKafka(String currentTime, Object iotData, String currentTopic) {
		ProducerRecord<String, String> producerRecord = new ProducerRecord<String, String>(currentTopic, currentTime,
				iotData.toString());
		producer.send(producerRecord);
	}
}
