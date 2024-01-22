package com.pdp.MongoApp.service;


import com.pdp.MongoApp.collection.Health;
import com.pdp.MongoApp.collection.WeatherData;
import com.pdp.MongoApp.repository.HealthRepository;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.lang.annotation.Documented;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;

@Service
public class IotService {

    @Autowired
    private HealthRepository healthRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    Logger logger = LoggerFactory.getLogger(IotService.class);

    public ResponseEntity<List<Health>> getHealthByStepsAndHeartRate(Integer steps, Integer heartRate) {
        logger.info("Fetching records for steps taken greater than +"+ steps + "and heart rate less than "+ heartRate);
        List<Health> healthData = healthRepository.findByStepsAndHeartRate(steps, heartRate);
        logger.info("Returning " + healthData.size() + " datapoints.");
        return new ResponseEntity<>(healthData, HttpStatus.OK);
    }

    public ResponseEntity<List<Document>> getMaxTemperatureOfEveryDevice(){
        MatchOperation matchOperation = Aggregation.match(Criteria.where("humidity").gt(20).lt(50));
        GroupOperation groupOperation = Aggregation.group("UUID").max("temperature").as("maxTemperature");
        SortOperation sortOperation = Aggregation.sort(Sort.Direction.ASC,"maxTemperature");
        ProjectionOperation projectOperation = Aggregation.project().and("_id").as("UUID").and("maxTemperature").as("maxTemperature").andExclude("_id");
        Aggregation aggregation = Aggregation.newAggregation(matchOperation,groupOperation,projectOperation,sortOperation);
        List<Document> result = mongoTemplate.aggregate(aggregation, WeatherData.class,Document.class).getMappedResults();
        return new ResponseEntity<>(result,HttpStatus.OK) ;
/*
        Mongosh Query:
        db.weather_data.aggregate([
                {$match:{humidity:{$gt:30,$lt:50}}},
        {$group:{_id:{uuid:"$UUID"},maxTemperature:{$max: "$temperature"}}},
        {$sort:{maxTemperature:1}}
    ])
*/
    }

    public ResponseEntity<List<Document>> getMaximumCaloriesBurned(String startDateTime, String endTimeStamp){
        /*
        db.health.aggregate([ { $match: { timestamp: { $gte: ISODate('2024-01-21T00:00:00.000Z'), $lt: ISODate('2024-01-22T00:00:00.000Z') } } }, { $group: { _id: "$UUID", averageCaloriesBurned: { $avg: "$caloriesBurned" } } }] )
         */
        MatchOperation matchOperation = Aggregation.match(Criteria.where("timestamp").gte(ZonedDateTime.parse(startDateTime).toInstant()).lt(ZonedDateTime.parse(endTimeStamp).toInstant()));
        GroupOperation groupOperation = Aggregation.group("UUID").avg("caloriesBurned").as("averageCaloriesBurned");
        ProjectionOperation projectOperation = Aggregation.project().and("_id").as("UUID").and("averageCaloriesBurned").as("averageCaloriesBurned").andExclude("_id");
        Aggregation aggregation = Aggregation.newAggregation(matchOperation,groupOperation,projectOperation);
        List<Document> result = mongoTemplate.aggregate(aggregation,Health.class,Document.class).getMappedResults();
        return new ResponseEntity<>(result,HttpStatus.OK);
    }




}
