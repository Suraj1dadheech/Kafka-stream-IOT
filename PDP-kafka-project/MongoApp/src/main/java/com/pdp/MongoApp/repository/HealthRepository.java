package com.pdp.MongoApp.repository;

import com.pdp.MongoApp.collection.Health;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HealthRepository extends MongoRepository<Health,String> {

    @Query(value="{stepsTaken:{$gte:?0},heartRate:{$lt:?1}}",fields="{sleepDuration:0 , spo2:0}")
    public List<Health> findByStepsAndHeartRate(Integer stepsTaken, Integer heartRate);
}
