package com.pdp.producer.config;

import java.io.IOException;
import java.util.Map;

import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pdp.producer.entity.EnvironmentalData;

public class EnvironmentalDataDeserializer implements Deserializer<EnvironmentalData> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        // No additional configuration needed
    }

    @Override
    public EnvironmentalData deserialize(String topic, byte[] data) {
        if (data == null) {
            return null;
        }

        try {
            JsonNode jsonNode = objectMapper.readTree(data);
            EnvironmentalData environmentalData = new EnvironmentalData();
            environmentalData.setPm25(jsonNode.path("pm25").asDouble());
            environmentalData.setCo2(jsonNode.path("co2").asDouble());
            environmentalData.setTemperature(jsonNode.path("temperature").asDouble());
            environmentalData.setHumidity(jsonNode.path("humidity").asDouble());
            environmentalData.setTime(jsonNode.path("time").asText());
            return environmentalData;
        } catch (IOException e) {
            throw new SerializationException("Error deserializing JSON", e);
        }
    }

    @Override
    public void close() {
        // No resources to release
    }
}