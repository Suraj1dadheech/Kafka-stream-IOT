package com.pdp.producer.service.producerService.Temperature;

import com.pdp.producer.dto.Temparature;
import com.pdp.producer.service.ProducerInvokerService;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;

import static com.pdp.producer.utils.Constants.UUID_TEMPERATURE;
import static java.text.ChoiceFormat.nextDouble;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TemperatureMonitoringDevice1 implements Runnable {
	
	private ProducerInvokerService producerInvokerService = new ProducerInvokerService();
	
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SS");

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

//            System.out.println("-----------------TemperatureMonitoringDevice---------------------------");
//            System.out.println("Temperature: " + temperature + " degrees Celsius");
//            System.out.println("Humidity: " + humidity + "%");
//            System.out.println("Pressure: " + pressure + " hPa");
//            System.out.println("Wind Speed: " + windSpeed + " m/s");
//            System.out.println("Wind Direction: " + windDirection + " degrees");

            Temparature temparature=new Temparature();
            temparature.setUUID(UUID_TEMPERATURE);
            temparature.setTemperature(temperature);
            temparature.setHumidity(humidity);
            temparature.setPressure(pressure);
            temparature.setWindSpeed(windSpeed);
            temparature.setWindDirection(windDirection);

//            System.out.println(temparature);
            
            String currentTime = LocalDateTime.now().format(formatter);
            producerInvokerService.produceIOTdataToKafka(currentTime, temparature, "TEMPERATURE_TOPIC");

            try {
                Thread.sleep(1000); // Sleep for 3 seconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
