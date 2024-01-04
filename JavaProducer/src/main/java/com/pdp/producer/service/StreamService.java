package com.pdp.producer.service;

import java.util.Properties;

import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pdp.producer.config.KafkaStreamConfig;
import com.pdp.producer.entity.EnvironmentalData;

@Service
public class StreamService {
	
	@Autowired
	private KafkaStreamConfig kafkaStreamConfig;
	
	public void runStream() {
		Properties properties = kafkaStreamConfig.invokeKafkaStreamConfig();
		StreamsBuilder builder = new StreamsBuilder();
		KStream<String, String> environmentalDataStream = builder.stream("environmental_data");
		System.out.println("Started KStream.............................................");
		 KStream<String, EnvironmentalData> parsedDataStream = environmentalDataStream.mapValues(value -> {
	            return parseEnvironmentalDataFromJson(value);
	        });
		
//		 parsedDataStream.groupByKey()
//	        .windowedBy(TimeWindows.of(Duration.ofMinutes(5)).grace(Duration.ZERO))
//	        .aggregate(
//	                EnvironmentalData::new,
//	                (key, value, aggregate) -> aggregate.add(value),
//	                Materialized.<String, String, WindowStore<Bytes, byte[]>>as("environmental-data-aggregator")
//	                        .withValueSerde(Serdes.String()) // Use String serde for EnvironmentalDataAggregator
//	        )
//	        .toStream()
//	        .map((key, value) -> KeyValue.pair(key.key(), value.computeAverage()))
//	        .to("aggregation_env_topic");


		 
		KafkaStreams streams = new KafkaStreams(builder.build(),properties);
		streams.start();
		//printed the topology
		System.out.println(streams.toString());
		
		// shutdown hook to correctly close the streams application
		Runtime.getRuntime().addShutdownHook(new Thread(streams::close));
	}
	
	private EnvironmentalData parseEnvironmentalDataFromJson(String json) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(json, EnvironmentalData.class);
        } catch (Exception e) {
            // Handle the exception (e.g., log it) and return null or throw a custom exception
            e.printStackTrace();
            return null;
        }
    }

}
