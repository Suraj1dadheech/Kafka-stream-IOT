package com.pdp.producer.service.producerService;

import java.util.Random;

import static java.text.ChoiceFormat.nextDouble;

public class TemperatureMonitoringDevice implements Runnable {

    private Random random = new Random();

    @Override
    public void run() {
        while (true) {
            // Simulate temperature and humidity data
            double temperature = 25 + random.nextDouble() * 10;
            double humidity = 40 + random.nextDouble() * 20;
            double pressure=random.nextDouble() * 100.0 + 950.0;
            double windSpeed=random.nextDouble() * 30.0;
            double windDirection=random.nextInt(361);

            System.out.println("-----------------TemperatureMonitoringDevice---------------------------");
            System.out.println("Temperature: " + temperature + " degrees Celsius");
            System.out.println("Humidity: " + humidity + "%");
            System.out.println("Pressure: " + pressure + " hPa");
            System.out.println("Wind Speed: " + windSpeed + " m/s");
            System.out.println("Wind Direction: " + windDirection + " degrees");

            try {
                Thread.sleep(1000); // Sleep for 3 seconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
