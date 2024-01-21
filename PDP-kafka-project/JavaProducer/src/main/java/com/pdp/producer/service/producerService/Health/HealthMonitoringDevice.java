package com.pdp.producer.service.producerService.Health;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pdp.producer.dto.Health;
import com.pdp.producer.service.ProducerInvokerService;
import com.pdp.producer.utils.ProducerUtils;
import org.springframework.beans.factory.annotation.Autowired;

public class HealthMonitoringDevice implements Runnable {

	
	private ProducerInvokerService producerInvokerService = new ProducerInvokerService();
	
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SS");

	private Random random = new Random();

	@Autowired
	private ProducerUtils producerUtils;

	@Override
	public void run() {
		while (true) {
			// Simulate health data
			int heartRate = 60 + random.nextInt(40);
			int spo2 = 95 + random.nextInt(5);
			int sleepDuration = random.nextInt(6) + 4;
			int caloriesBurned = random.nextInt(401) + 100;
			int stepsTaken = random.nextInt(9001) + 1000;

//            System.out.println("--------------------HealthMonitoringDevice------------------");
//            System.out.println("Heart Rate:- "+heartRate+" bpm");
//            System.out.println("Spo2:- "+spo2+" %");
//            System.out.println("sleepDuration:- "+sleepDuration+" hrs");
//            System.out.println("caloriesBurned:- "+caloriesBurned+" calories");
//            System.out.println("stepsTaken:- "+stepsTaken+" steps");

			Health health = new Health();
			String uuid=producerUtils.fetchRandomUUIDForHealthDevice();
			health.setUUID(uuid);
			health.setSpo2(spo2);
			health.setCaloriesBurned(caloriesBurned);
			health.setStepsTaken(stepsTaken);
			health.setHeartRate(heartRate);
			health.setSleepDuration(sleepDuration);

//			System.out.println(health);

			String currentTime = LocalDateTime.now().format(formatter);
			producerInvokerService.produceIOTdataToKafka(currentTime, generatePartialJson(health), "HEALTH_TOPIC");

			try {
				Thread.sleep(1000); // Sleep for 3 seconds
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	 private static String generatePartialJson(Health health) {
	        // Convert WeatherDTO to a Map
	        Map<String, Object> attributeMap = new ObjectMapper().convertValue(health, Map.class);

	        // Randomly choose a subset of keys to include in the JSON
	        int selectedKeyCount = new Random().nextInt(attributeMap.size()) + 1;
	        Object[] allKeys = attributeMap.keySet().toArray();
	        Object[] selectedKeys = new Object[selectedKeyCount];
	        System.arraycopy(allKeys, 0, selectedKeys, 0, selectedKeyCount);

	        // Create a Map with only the selected key-value pairs
	        Map<String, Object> partialJsonMap = new HashMap<>();
	        for (Object key : selectedKeys) {
	            partialJsonMap.put((String) key, attributeMap.get(key));
	        }

	        // Convert the Map to JSON
	        try {
	            return new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(partialJsonMap);
	        } catch (Exception e) {
	            e.printStackTrace();
	            return null;
	        }
	    }
}
