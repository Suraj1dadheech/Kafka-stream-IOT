package com.pdp.producer.service.producerService;

import com.pdp.producer.dto.Mobile;

import java.util.Random;

import static com.pdp.producer.utils.Constants.UUID_MOBILE;

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
//            System.out.println("--------------Mobile phone-----------------------");
//            System.out.println("Call Duration: " + callDuration + " seconds");
//            System.out.println("SMS/MMS Count: " + smsCount);
//            System.out.println("Data Usage: " + String.format("%.2f", dataUsage) + " MB");
//            System.out.println("Internal Storage Usage: " + String.format("%.2f", internalStorageUsage) + " GB");
//            System.out.println("External Storage Usage: " + String.format("%.2f", externalStorageUsage) + " GB");
//            System.out.println("CPU Usage: " + String.format("%.2f", cpuUsage) + "%");
//            System.out.println("Device Lock/Unlock Events: " + lockUnlockEvents);

            Mobile mobile=new Mobile();
            mobile.setUUID(UUID_MOBILE);
            mobile.setCpuUsage(cpuUsage);
            mobile.setCallDuration(callDuration);
            mobile.setDataUsage(dataUsage);
            mobile.setSmsCount(smsCount);
            mobile.setExternalStorageUsage(externalStorageUsage);
            mobile.setLockUnlockEvents(lockUnlockEvents);
            mobile.setInternalStorageUsage(internalStorageUsage);

            System.out.println(mobile);

            try {
                Thread.sleep(1000); // Sleep for 3 seconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
