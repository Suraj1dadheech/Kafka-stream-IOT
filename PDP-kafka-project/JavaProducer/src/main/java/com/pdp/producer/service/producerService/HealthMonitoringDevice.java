package com.pdp.producer.service.producerService;

import java.util.Random;

public class HealthMonitoringDevice implements Runnable{

    private Random random = new Random();
    @Override
    public void run() {
        while (true) {
            // Simulate health data
            int heartRate = 60 + random.nextInt(40);
            int spo2 = 95 + random.nextInt(5);

            System.out.println("Health Device - Heart Rate: " + heartRate + " bpm, SpO2: " + spo2 + "%");

            try {
                Thread.sleep(3000); // Sleep for 3 seconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
