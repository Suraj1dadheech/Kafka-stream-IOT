//package com.pdp.producer.service.producerService;
//
//public class BulbMonitoring implements Runnable {
//
//    @Override
//    public void run() {
//        while (true) {
//            int wattage = generateRandomInteger(5, 100); // Random wattage between 5 and 100 watts
//            int luminous = generateRandomInteger(200, 1600); // Random lumens between 200 and 1600 lumens
//            int colorTemperature = generateRandomInteger(2700, 6500); // Random color temperature between 2700K and 6500K
//            int lifetimeHours = generateRandomInteger(1000, 50000); // Random lifetime between 1000 and 50000 hours
//            String bulbType = generateRandomBulbType();
//
//        }
//
//    }
//    private static int generateRandomInteger(int min, int max) {
//        return (int) (Math.random() * (max - min + 1)) + min;
//    }
//
//    private static String generateRandomBulbType() {
//        String[] bulbTypes = {"Incandescent", "LED", "CFL", "Halogen"};
//        int index = generateRandomInteger(0, bulbTypes.length - 1);
//        return bulbTypes[index];
//    }
//}
