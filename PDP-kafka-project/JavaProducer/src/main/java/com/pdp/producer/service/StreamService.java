package com.pdp.producer.service;

import java.time.Duration;
import java.util.Properties;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.utils.Bytes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.ForeachAction;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.kstream.Suppressed;
import org.apache.kafka.streams.kstream.TimeWindows;
import org.apache.kafka.streams.kstream.Windowed;
import org.apache.kafka.streams.state.WindowStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pdp.producer.config.EnvironmentalDataDeserializer;
import com.pdp.producer.config.EnvironmentalDataSerializer;
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
		 

		

		 
		 KStream<Windowed<String>, EnvironmentalData> averagedStream = parsedDataStream
				    .groupByKey()
				    .windowedBy(TimeWindows.of(Duration.ofMinutes(1)).grace(Duration.ZERO))
				    .aggregate(
				        EnvironmentalData::new, // initializer
				        (key, value, aggregate) -> updateAverage(aggregate, value), // aggregator
				        Materialized.as("average-store")  // No need for value serde, it's handled automatically
				    )
				    .toStream();


	        
//		 KStream<String, String> transformedStream = averagedStream
//			        .map((key, value) -> KeyValue.pair(key, convertToOutputFormat(key, value)));

		 
//		 averagedStream.foreach(new ForeachAction<Windowed<String>, EnvironmentalData>() {
//			    public void apply(Windowed<String> key, EnvironmentalData value) {
//			        System.out.println(key.key() + ": " + value.toString() );
//			    }
//			});


		 
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
            e.printStackTrace();
            return null;
        }
    }
	
    private static EnvironmentalData updateAverage(EnvironmentalData aggregate, EnvironmentalData newValue) {
        // Update the averages based on the new value
        aggregate.setPm25(aggregate.getPm25() + newValue.getPm25());
        aggregate.setCo2(aggregate.getCo2() + newValue.getCo2());
        aggregate.setTemperature(aggregate.getTemperature() + newValue.getTemperature());
        aggregate.setHumidity(aggregate.getHumidity() + newValue.getHumidity());
        // time field is not averaged, so no need to update it here
        return aggregate;
    }
    
    private static String convertToOutputFormat(Windowed<String> windowedKey, EnvironmentalData value) {
        // Convert the averaged data to the desired output format
        double avgPm25 = value.getPm25();
        double avgCo2 = value.getCo2();
        double avgTemperature = value.getTemperature();
        double avgHumidity = value.getHumidity();
        String time = windowedKey.window().startTime().toString();
        // Create a JSON string with the averaged values
        return String.format("{\"avgPm25\": %.3f, \"avgCo2\": %.3f, \"avgTemperature\": %.3f, \"avgHumidity\": %.3f, \"time\": \"%s\"}", avgPm25, avgCo2, avgTemperature, avgHumidity, time);
    }


}
