package com.pdp.producer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pdp.producer.service.ProducerInvokerService;
import com.pdp.producer.service.StreamService;

@RestController
@RequestMapping("/api/v1")
public class KafkaController {

	@Autowired
	private ProducerInvokerService producerInvokerService;
	
	@Autowired
	private StreamService streamService;

	@PostMapping("/produce")
	public void produceRecords() {
		producerInvokerService.invokeProducer();
	}
	
	@PostMapping("/stream")
	public void runAggregatedStream() {
		streamService.runStream();
	}

}
