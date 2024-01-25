package com.java.consumer.service;

import java.time.Duration;
import java.util.Arrays;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import com.java.consumer.config.KafkaConfig;

public class ConsumerInvokerService {
	
	private KafkaConfig kafkaConfig = new KafkaConfig();
	
	public void invokeConsumer(String topic) {
		KafkaConsumer<String, String> consumer = kafkaConfig.invokeKafkaConfig();
		
		consumer.subscribe(Arrays.asList(topic));
		int count = 0;
		while(true) {
			 ConsumerRecords<String, String> consumerRecords = consumer.poll(Duration.ofMillis(10000));
			 System.out.println("polling..................");
			 
//			 consumerRecords.iterator().forEachRemaining(System.out::println);
			 
			 for(ConsumerRecord<String, String> record:consumerRecords) {
				 System.out.println(topic+" - "+count);
				 System.out.println(record.key()+"    ::::::    "+record.value());
				 count++;
			 }
			 
		}
		
	}
	

}
