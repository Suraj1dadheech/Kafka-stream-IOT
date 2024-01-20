package com.pdp.producer.service.producerService;

import java.util.Random;

public class MobilePhoneMonitoring implements Runnable{

    Random random = new Random();
    @Override
    public void run() {

        while (true){

            // Call Duration in seconds
            int callDuration = random.nextInt(3600); // Assuming calls can last up to 1 hour (3600 seconds)
            // SMS/MMS Count
            int smsCount = random.nextInt(100); // Assuming up to 100 messages
            // Data Usage in megabytes
            double dataUsage = random.nextDouble() * 100; // Assuming up to 100 MB
            // Internal Storage Usage in gigabytes
            double internalStorageUsage = random.nextDouble() * 100; // Assuming up to 100 GB
            // External Storage Usage in gigabytes
            double externalStorageUsage = random.nextDouble() * 100; // Assuming up to 100 GB
            // CPU Usage percentage
            double cpuUsage = random.nextDouble() * 100; // Assuming up to 100%
            // Device Lock/Unlock Events
            int lockUnlockEvents = random.nextInt(50); // Assuming up to 50 lock/unlock events

            // Display generated metrics
            System.out.println("--------------Mobile phone-----------------------");
            System.out.println("Call Duration: " + callDuration + " seconds");
            System.out.println("SMS/MMS Count: " + smsCount);
            System.out.println("Data Usage: " + String.format("%.2f", dataUsage) + " MB");
            System.out.println("Internal Storage Usage: " + String.format("%.2f", internalStorageUsage) + " GB");
            System.out.println("External Storage Usage: " + String.format("%.2f", externalStorageUsage) + " GB");
            System.out.println("CPU Usage: " + String.format("%.2f", cpuUsage) + "%");
            System.out.println("Device Lock/Unlock Events: " + lockUnlockEvents);

            try {
                Thread.sleep(1000); // Sleep for 3 seconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
