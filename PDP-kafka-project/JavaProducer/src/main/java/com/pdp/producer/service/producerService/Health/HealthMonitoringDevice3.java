package com.pdp.producer.service.producerService.Health;

import static com.pdp.producer.utils.Constants.UUID_HEALTH3;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;

import com.pdp.producer.dto.Health;
import com.pdp.producer.service.ProducerInvokerService;

public class HealthMonitoringDevice3 implements Runnable {

	private ProducerInvokerService producerInvokerService = new ProducerInvokerService();
	
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SS");

	private Random random = new Random();

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
			health.setUUID(UUID_HEALTH3);
			health.setSpo2(spo2);
			health.setCaloriesBurned(caloriesBurned);
			health.setStepsTaken(stepsTaken);
			health.setHeartRate(heartRate);
            health.setSleepDuration(sleepDuration);

//			System.out.println(health);

			String currentTime = LocalDateTime.now().format(formatter);
			producerInvokerService.produceIOTdataToKafka(currentTime, health, "HEALTH_TOPIC");

			try {
				Thread.sleep(1000); // Sleep for 3 seconds
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
