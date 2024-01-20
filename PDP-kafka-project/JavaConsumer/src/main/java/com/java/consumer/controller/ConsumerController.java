package com.java.consumer.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java.consumer.service.ConsumerInvokerService;

@RestController
@RequestMapping("/api/v1")
public class ConsumerController {
	
	private ConsumerInvokerService consumerInvokerService = new ConsumerInvokerService();
	
	@GetMapping("/consumer")
	public void produceRecords() {
		consumerInvokerService.invokeConsumer();
	}

}
