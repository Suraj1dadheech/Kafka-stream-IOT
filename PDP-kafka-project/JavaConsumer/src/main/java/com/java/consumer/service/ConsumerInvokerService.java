package com.java.consumer.service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.consumer.collection.Health;
import com.java.consumer.collection.WeatherData;
import com.java.consumer.repository.HealthRepository;
import com.java.consumer.repository.WeatherDataRepository;
import com.java.consumer.util.JsonSchemaValidatorUtil;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import com.java.consumer.config.KafkaConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConsumerInvokerService {

	@Autowired
	private HealthRepository healthRepository;

	@Autowired
	private WeatherDataRepository weatherDataRepository;

	@Autowired
	private JsonSchemaValidatorUtil jsonSchemaValidatorUtil;

	@Autowired
	private ObjectMapper objectMapper;
	
	private KafkaConfig kafkaConfig = new KafkaConfig();

	Logger logger = LoggerFactory.getLogger(ConsumerInvokerService.class);
	
	public void invokeConsumer(String topic) {
		KafkaConsumer<String, String> consumer = kafkaConfig.invokeKafkaConfig();
		consumer.subscribe(Arrays.asList(topic));
		int count = 0;
		Health health;
		WeatherData weatherData;
		while(true) {
			 ConsumerRecords<String, String> consumerRecords = consumer.poll(Duration.ofMillis(1000));
			 List<WeatherData> weatherDataList = new ArrayList<>();
			 List<Health> healthList = new ArrayList<>();
			 for(ConsumerRecord<String, String> record:consumerRecords) {
				 logger.info(topic+" - "+count);
				 System.out.println(record.key()+"    ::::::    "+record.value());
				 count++;
				 try {
					if(topic.equals("HEALTH_TOPIC")) {
						health = objectMapper.readValue(record.value(), Health.class);
						if(jsonSchemaValidatorUtil.validateJson(health,"HEALTH")){
							healthList.add(health);
						}
					}else if(topic.equals("TEMPERATURE_TOPIC")){
						weatherData = objectMapper.readValue(record.value(), WeatherData.class);
						weatherDataList.add(weatherData);
					}

				 }catch(Exception exe){
					 exe.printStackTrace();
				 }
			 }
			if(!weatherDataList.isEmpty()){
				weatherDataRepository.saveAll(weatherDataList);
			}
			if(!healthList.isEmpty()){
				healthRepository.saveAll(healthList);
			}


		}
		
	}
	

}
