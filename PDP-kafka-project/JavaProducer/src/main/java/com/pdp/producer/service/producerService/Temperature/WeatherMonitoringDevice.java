package com.pdp.producer.service.producerService.Temperature;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pdp.producer.dto.Weather;
import com.pdp.producer.service.ProducerInvokerService;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.pdp.producer.utils.ProducerUtils;
import org.springframework.beans.factory.annotation.Autowired;

import static java.text.ChoiceFormat.nextDouble;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class WeatherMonitoringDevice implements Runnable {
	
	private ProducerInvokerService producerInvokerService = new ProducerInvokerService();
	
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SS");

    private Random random = new Random();
    @Autowired
    private ProducerUtils producerUtils;

    @Override
    public void run() {
        while (true) {
            // Simulate temperature and humidity data
            double temperature = 25 + random.nextDouble() * 10;
            double humidity = 40 + random.nextDouble() * 20;
            double pressure=random.nextDouble() * 100.0 + 950.0;
            double windSpeed=random.nextDouble() * 30.0;
            double windDirection=random.nextInt(361);

//            System.out.println("-----------------TemperatureMonitoringDevice---------------------------");
//            System.out.println("Temperature: " + temperature + " degrees Celsius");
//            System.out.println("Humidity: " + humidity + "%");
//            System.out.println("Pressure: " + pressure + " hPa");
//            System.out.println("Wind Speed: " + windSpeed + " m/s");
//            System.out.println("Wind Direction: " + windDirection + " degrees");

            Weather weather =new Weather();
            String uuid=producerUtils.fetchRandomUUIDForWeatherDevice();
            weather.setUUID(uuid);
            weather.setTemperature(temperature);
            weather.setHumidity(humidity);
            weather.setPressure(pressure);
            weather.setWindSpeed(windSpeed);
            weather.setWindDirection(windDirection);

//            System.out.println(temparature);
            
            String currentTime = LocalDateTime.now().format(formatter);
            producerInvokerService.produceIOTdataToKafka(currentTime, generatePartialJson(weather), "TEMPERATURE_TOPIC");

            try {
                Thread.sleep(1000); // Sleep for 3 seconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    private static String generatePartialJson(Weather weather) {
        // Convert WeatherDTO to a Map
        Map<String, Object> attributeMap = new ObjectMapper().convertValue(weather, Map.class);

        // Randomly choose a subset of keys to include in the JSON
        int selectedKeyCount = new Random().nextInt(attributeMap.size()) + 1;
        Object[] allKeys = attributeMap.keySet().toArray();
        Object[] selectedKeys = new Object[selectedKeyCount];
        System.arraycopy(allKeys, 0, selectedKeys, 0, selectedKeyCount);

        // Create a Map with only the selected key-value pairs
        Map<String, Object> partialJsonMap = new HashMap<>();
        for (Object key : selectedKeys) {
            partialJsonMap.put((String) key, attributeMap.get(key));
        }

        // Convert the Map to JSON
        try {
            return new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(partialJsonMap);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
