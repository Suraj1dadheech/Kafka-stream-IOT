package com.java.consumer.repository;

import com.java.consumer.collection.WeatherData;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeatherDataRepository extends MongoRepository<WeatherData,String> {

}
