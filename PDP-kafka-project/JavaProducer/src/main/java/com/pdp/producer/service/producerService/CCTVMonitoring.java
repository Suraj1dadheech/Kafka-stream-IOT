package com.pdp.producer.service.producerService;

import com.pdp.producer.dto.CCTV;

import java.util.Random;

import static com.pdp.producer.utils.Constants.UUID_CCTV;

public class CCTVMonitoring implements Runnable{
    private Random random = new Random();
    @Override
    public void run() {
        while (true) {
            // Generate random camera metrics
            int resolution = random.nextInt(2) == 0 ? 720 : 1080;  // Simulating 720p or 1080p resolution
            int fps = random.nextInt(30) + 1;  // Simulating FPS between 1 and 30
            int fieldOfView = random.nextInt(180) + 1;  // Simulating field of view between 1 and 180 degrees
            int storageCapacity = random.nextInt(500) + 500;  // Simulating storage capacity between 500 and 1000 GB
            int networkBandwidth = random.nextInt(100) + 50;  // Simulating network bandwidth between 50 and 150 Mbps

            // Display generated metrics
//        System.out.println("Resolution: " + resolution + "p");
//        System.out.println("Frames per Second (FPS): " + fps);
//        System.out.println("Field of View: " + fieldOfView + " degrees");
//        System.out.println("Storage Capacity: " + storageCapacity + " GB");
//        System.out.println("Network Bandwidth: " + networkBandwidth + " Mbps");
//        System.out.println("----------------------------");

            CCTV cctv = new CCTV();
            cctv.setUUID(UUID_CCTV);
            cctv.setFps(fps);
            cctv.setFieldOfView(fieldOfView);
            cctv.setResolution(resolution);
            System.out.println(cctv);

            try {
                Thread.sleep(1000); // Sleep for 3 seconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
