package com.pdp.producer.service.producerService;

import java.util.Random;

public class HealthMonitoringDevice implements Runnable {

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

            System.out.println("--------------------HealthMonitoringDevice------------------");
            System.out.println("Heart Rate:- "+heartRate+" bpm");
            System.out.println("Spo2:- "+spo2+" %");
            System.out.println("sleepDuration:- "+sleepDuration+" hrs");
            System.out.println("caloriesBurned:- "+caloriesBurned+" calories");
            System.out.println("stepsTaken:- "+stepsTaken+" steps");

            try {
                Thread.sleep(1000); // Sleep for 3 seconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
