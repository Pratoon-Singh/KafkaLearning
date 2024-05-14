package org.example.streams;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.*;
import org.apache.kafka.streams.kstream.KStream;

import java.util.Properties;


public class DataFlowStream {
	public static void main(String[] args) {
		Properties properties = new Properties();
		properties.put(StreamsConfig.APPLICATION_ID_CONFIG,"streams-dataflow");
		properties.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
		properties.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
		properties.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG,Serdes.String().getClass().getName());

		StreamsBuilder streamsBuilder = new StreamsBuilder();
		 KStream<String, String> stream = streamsBuilder.stream("streams-dataflow-input");
		 stream.foreach((key,value)-> System.out.println("Key:- "+key+" Value:-"+value));
		 stream.filter((key, value) -> value.contains("TOKEN"))
//				 .mapValues(value->value.toUpperCase())
				 .map((key,value)->new KeyValue<>(key,value.toLowerCase()))
				.to("streams-dataflow-output");


		 Topology topology = streamsBuilder.build();
		System.out.println(topology.describe());
		KafkaStreams kafkaStreams = new KafkaStreams(topology,properties);
		 kafkaStreams.start();

		 Runtime.getRuntime().addShutdownHook(new Thread(kafkaStreams::close));

	}
}