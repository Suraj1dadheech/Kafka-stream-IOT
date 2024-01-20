package com.java.consumer.config;

import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.stereotype.Service;

@Service
public class KafkaConfig {
	
	public KafkaConsumer<String,String> invokeKafkaConfig(){
		String bootstrapServers = "127.0.0.1:9092";
		String groupId = "iot-monitor";
		Properties properties = new Properties();
		
		properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
		properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, groupId);
		properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
		
		return new KafkaConsumer<String, String>(properties);
		
		
	}

}
