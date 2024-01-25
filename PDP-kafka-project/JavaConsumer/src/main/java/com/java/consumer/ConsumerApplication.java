package com.java.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;

import com.java.consumer.service.ConsumerInvokerService;

@SpringBootApplication
public class ConsumerApplication {
	static String healthTopic = "HEALTH_TOPIC";

	static String temperatureTopic = "TEMPERATURE_TOPIC";

	private static ConsumerInvokerService consumerInvokerService = new ConsumerInvokerService();

	public static void main(String[] args) {
		SpringApplication.run(ConsumerApplication.class, args);
		invokeConsumers();
	}

	public static void invokeConsumers() {
		consumerInvokerService.invokeConsumer(healthTopic);
		consumerInvokerService.invokeConsumer(temperatureTopic);
	}

}
