package com.pdp.producer.config;

import java.io.IOException;
import java.util.Map;

import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pdp.producer.entity.EnvironmentalData;

public class EnvironmentalDataSerde implements Serde<EnvironmentalData> {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        // No additional configuration needed
    }

    @Override
    public void close() {
        // No resources to close
    }

    @Override
    public Serializer<EnvironmentalData> serializer() {
        return new Serializer<EnvironmentalData>() {
            @Override
            public byte[] serialize(String topic, EnvironmentalData data) {
                try {
                    return objectMapper.writeValueAsBytes(data);
                } catch (Exception e) {
                    throw new SerializationException("Error serializing EnvironmentalData: " + e.getMessage(), e);
                }
            }
        };
    }

    @Override
    public Deserializer<EnvironmentalData> deserializer() {
        return new Deserializer<EnvironmentalData>() {
            @Override
            public EnvironmentalData deserialize(String topic, byte[] data) {
                if (data == null) {
                    return null;
                }

                try {
                    return objectMapper.readValue(data, EnvironmentalData.class);
                } catch (IOException e) {
                    throw new SerializationException("Error deserializing EnvironmentalData: " + e.getMessage(), e);
                }
            }
        };
    }
}