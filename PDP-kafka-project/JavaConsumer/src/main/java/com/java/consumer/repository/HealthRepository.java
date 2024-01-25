package com.java.consumer.repository;

import com.java.consumer.collection.Health;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HealthRepository extends MongoRepository<Health,String> {

}
