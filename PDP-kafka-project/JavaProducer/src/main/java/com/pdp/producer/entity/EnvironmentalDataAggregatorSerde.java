package com.pdp.producer.entity;

import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;

import com.fasterxml.jackson.databind.ObjectMapper;

public class EnvironmentalDataAggregatorSerde implements Serde<EnvironmentalData> {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Serializer<EnvironmentalData> serializer() {
        return (topic, data) -> {
            try {
                return objectMapper.writeValueAsString(data).getBytes();
            } catch (Exception e) {
                throw new RuntimeException("Error serializing EnvironmentalDataAggregator to JSON", e);
            }
        };
    }

    @Override
    public Deserializer<EnvironmentalData> deserializer() {
        return (topic, data) -> {
            if (data == null) {
                return null;
            }

            try {
                return objectMapper.readValue(new String(data), EnvironmentalData.class);
            } catch (Exception e) {
                throw new RuntimeException("Error deserializing JSON to EnvironmentalDataAggregator", e);
            }
        };
    }
}