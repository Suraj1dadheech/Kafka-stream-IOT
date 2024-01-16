package com.pdp.producer.service.producerService;

import java.util.Random;

public class TemperatureMonitoringDevice implements Runnable {

    private Random random = new Random();

    @Override
    public void run() {
        while (true) {
            // Simulate temperature and humidity data
            double temperature = 25 + random.nextDouble() * 10;
            double humidity = 40 + random.nextDouble() * 20;

            System.out.println("Temperature Device - Temperature: " + temperature + "°C, Humidity: " + humidity + "%");

            try {
                Thread.sleep(5000); // Sleep for 3 seconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
