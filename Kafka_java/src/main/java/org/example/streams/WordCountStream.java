package org.example.streams;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.*;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;

import java.util.Arrays;
import java.util.Properties;

public class WordCountStream {
	public static void main(String[] args) {
		Properties properties = new Properties();
		properties.put(StreamsConfig.APPLICATION_ID_CONFIG,"streams-wordcount");
		properties.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
		properties.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
		properties.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG,Serdes.String().getClass().getName());
		properties.put(StreamsConfig.STATESTORE_CACHE_MAX_BYTES_CONFIG,0);
		StreamsBuilder streamsBuilder = new StreamsBuilder();
		KStream<String, String> stream = streamsBuilder.stream("streams-wordcount-input");
		stream.flatMapValues(value-> Arrays.asList(value.toLowerCase().split(" ")))
				.groupBy((key,value)->value)
				.count()
				.toStream().to("streams-wordcount-output", Produced.with(Serdes.String(),Serdes.Long()));

		Topology topology = streamsBuilder.build();
		System.out.println(topology.describe());
		KafkaStreams kafkaStreams = new KafkaStreams(topology,properties);
		kafkaStreams.start();

		Runtime.getRuntime().addShutdownHook(new Thread(kafkaStreams::close));

	}
}
