//package com.pdp.producer.service.producerService;
//
//import com.pdp.producer.dto.SmartTv;
//
//import java.util.Random;
//
//import static com.pdp.producer.utils.Constants.UUID_SMART_TV;
//
//public class SmartTvMonitoring implements Runnable{
//
//    Random random = new Random();
//    @Override
//    public void run() {
//        while (true){
//            // Generate random smart TV data
//            int currentChannel = random.nextInt(100) + 1;  // Simulating channels between 1 and 100
//            int volumeLevel = random.nextInt(100) + 1;  // Simulating volume level between 1 and 100
//            boolean isPoweredOn = random.nextBoolean();
//            String currentApp = this.generateRandomAppName();
//
//            // Display generated smart TV data
////            System.out.println("Smart TV Data:");
////            System.out.println("Current Channel: " + currentChannel);
////            System.out.println("Volume Level: " + volumeLevel);
////            System.out.println("Power Status: " + (isPoweredOn ? "On" : "Off"));
////            System.out.println("Current App: " + currentApp);
////            System.out.println("----------------------------");
//
//            SmartTv smartTv=new SmartTv();
//            smartTv.setUUID(UUID_SMART_TV);
//            smartTv.setCurrentApp(currentApp);
//            smartTv.setIsPoweredOn(isPoweredOn);
//            smartTv.setCurrentChannel(currentChannel);
//            smartTv.setVolumeLevel(volumeLevel);
//
//            System.out.println(smartTv);
//
//            try {
//                Thread.sleep(1000); // Sleep for 3 seconds
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//
//
//    }
//
//    public String generateRandomAppName() {
//        String[] apps = {"Netflix", "YouTube", "Hulu", "Prime Video", "Disney+", "BBC iPlayer", "Spotify", "Twitch", "ESPN"};
//        Random random = new Random();
//        return apps[random.nextInt(apps.length)];
//    }
//
//}
