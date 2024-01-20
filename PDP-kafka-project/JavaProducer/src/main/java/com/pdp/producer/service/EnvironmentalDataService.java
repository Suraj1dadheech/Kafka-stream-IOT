package com.pdp.producer.service;

import java.util.Random;

import org.springframework.stereotype.Service;

import com.pdp.producer.entity.EnvironmentalData;

@Service
public class EnvironmentalDataService {

	public static final Random random = new Random();

	public double generateTemperature() {
		return Math.round((10 + random.nextDouble() * 20) * 1000.0) / 1000.0;
	}

	public double generateHumidity() {
		return Math.round((30 + random.nextDouble() * 40) * 1000.0) / 1000.0;
	}

	public EnvironmentalData generateIotRandomData(String currentTime) {
		double pm25 = Math.round(random.nextDouble() * 20000.0) / 1000.0;
		double co2 = Math.round((300 + random.nextDouble() * 200) * 1000.0) / 1000.0;
		double temperature = generateTemperature();
		double humidity = generateHumidity();
		return new EnvironmentalData(pm25, co2, temperature, humidity, currentTime);
	}
}
