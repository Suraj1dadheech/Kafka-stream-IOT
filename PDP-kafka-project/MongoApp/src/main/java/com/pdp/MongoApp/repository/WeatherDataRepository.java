package com.pdp.MongoApp.repository;

import com.pdp.MongoApp.collection.WeatherData;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeatherDataRepository extends MongoRepository<WeatherData,String> {
}
