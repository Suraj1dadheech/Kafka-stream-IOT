package com.pdp.producer.service.producerService;

import java.util.Random;

import static com.pdp.producer.service.EnvironmentalDataService.random;

public class CarMonitoringDevice implements Runnable{

    private Random random = new Random();
    @Override
    public void run() {

        while (true) {
            // Generate random data for Speed (in km/h)
            double speed = random.nextDouble() * 100;
            // Generate random data for RPM (Revolutions Per Minute)
            int rpm = random.nextInt(7000) + 1000;
            // Generate random data for Fuel Level (in percentage)
            double fuelLevel = random.nextDouble() * 100;
            // Generate random data for Engine Temperature (in Celsius)
            double engineTemperature = random.nextDouble() * 100;
            // Generate random data for Odometer (in kilometers)
            double odometer = random.nextDouble() * 100000;
            // Generate random data for Fuel Efficiency (in km/l)
            double fuelEfficiency = random.nextDouble() * 20;
            // Generate random data for Airbag System Status
            boolean airbagStatus = random.nextBoolean();
            // Generate random data for GPS Location
            double latitude = random.nextDouble() * 180 - 90;  // Range: -90 to 90
            double longitude = random.nextDouble() * 360 - 180; // Range: -180 to 180

            // Display generated data
            System.out.println("--------CarMonitoringDevice---------------------- ");
            System.out.println("Speed: " + speed + " km/h");
            System.out.println("RPM: " + rpm + " revolutions per minute");
            System.out.println("Fuel Level: " + fuelLevel + "%");
            System.out.println("Engine Temperature: " + engineTemperature + "°C");
            System.out.println("Odometer: " + odometer + " km");
            System.out.println("Fuel Efficiency: " + fuelEfficiency + " km/l");
            System.out.println("Airbag System Status: " + (airbagStatus ? "OK" : "Not OK"));
            System.out.println("GPS Location: (" + latitude + ", " + longitude + ")");

            try {
                Thread.sleep(1000); // Sleep for 3 seconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
