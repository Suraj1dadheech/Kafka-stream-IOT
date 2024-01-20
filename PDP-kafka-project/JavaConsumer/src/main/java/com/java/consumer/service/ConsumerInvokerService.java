package com.java.consumer.service;

import java.time.Duration;
import java.util.Arrays;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import com.java.consumer.config.KafkaConfig;

public class ConsumerInvokerService {
	
	private String topic = "HEALTH_TOPIC";
	
	private KafkaConfig kafkaConfig = new KafkaConfig();
	
	public void invokeConsumer() {
		KafkaConsumer<String, String> consumer = kafkaConfig.invokeKafkaConfig();
		
		consumer.subscribe(Arrays.asList(topic));

		while(true) {
//			 System.out.println("polling started...............");
			 
			 ConsumerRecords<String, String> consumerRecords = consumer.poll(Duration.ofMillis(1000));
			 
			 for(ConsumerRecord<String, String> record:consumerRecords) {
				 System.out.println(record.key()+"    ::::::    "+record.value());
			 }
			 
		}
		
	}
	

}
