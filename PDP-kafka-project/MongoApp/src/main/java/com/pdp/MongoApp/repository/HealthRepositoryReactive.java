package com.pdp.MongoApp.repository;

import com.pdp.MongoApp.collection.Health;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HealthRepositoryReactive extends ReactiveMongoRepository<Health, String> {
}
