package com.pdp.MongoApp.service;


import com.pdp.MongoApp.collection.Health;
import com.pdp.MongoApp.repository.HealthRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IotService {

    @Autowired
    private HealthRepository healthRepository;

    Logger logger = LoggerFactory.getLogger(IotService.class);

    public ResponseEntity<List<Health>> getHealthByStepsAndHeartRate(Integer steps, Integer heartRate) {
        logger.info("Fetching records for steps taken greater than +"+ steps + "and heart rate less than "+ heartRate);
        List<Health> healthData = healthRepository.findByStepsAndHeartRate(steps, heartRate);
        logger.info("Returning " + healthData.size() + " datapoints.");
        return new ResponseEntity<>(healthData, HttpStatus.OK);
    }
}
