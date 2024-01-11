package com.pdp.producer.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pdp.producer.config.KafkaConfig;
import com.pdp.producer.entity.EnvironmentalData;

@Service
public class ProducerInvokerService {
	
	@Autowired
	private KafkaConfig kafkaConfig;
	
	@Autowired
	private EnvironmentalDataService environmentalDataService;
	
	private String environmentalTopic = "environmental_data";
	private String healthTopic = "health_data";

	public void invokeProducer() {
		Producer<String, String> producer = kafkaConfig.invokeKafkaConfig();
    	System.out.println("Started Producing the data.......................");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SS");
        int count = 0;
        while (true) {   
//        	if(count==6) {
//        		break;
//        	}
        	count++;
        	String currentTime = LocalDateTime.now().format(formatter);
        	
        	// generating random data........
            EnvironmentalData environmentalData = environmentalDataService.generateIotRandomData(currentTime);
            
            // producing environmental data to environmental topic.........
            produceIOTdataToKafka(producer, currentTime, environmentalData,environmentalTopic);
            try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
        }
        
	}

	private void produceIOTdataToKafka(Producer<String, String> producer, String currentTime, Object iotData, String currentTopic) {
		ProducerRecord<String, String> producerRecord = new ProducerRecord<String, String>(currentTopic, currentTime, iotData.toString());
		producer.send(producerRecord);
	}
}
