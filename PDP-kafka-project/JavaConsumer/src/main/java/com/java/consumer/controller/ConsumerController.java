package com.java.consumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java.consumer.service.ConsumerInvokerService;

@RestController
@RequestMapping("/api/v1")
public class ConsumerController {
	
	String healthTopic = "HEALTH_TOPIC";
	
	String temperatureTopic = "TEMPERATURE_TOPIC";
	
	@Autowired
	private ConsumerInvokerService consumerInvokerService;
	
	@GetMapping("/consume/health")
	public void consumeHealthData() {
		consumerInvokerService.invokeConsumer(healthTopic);
	}
	
	@GetMapping("/consume/temperature")
	public void consumeTemperatureData() {
		consumerInvokerService.invokeConsumer(temperatureTopic);
	}

}
