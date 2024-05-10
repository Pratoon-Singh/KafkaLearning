package org.example.Truck.producer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.example.Truck.producer.serializer.TruckDeserializer;
import org.example.order.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class TruckConsumer {
	public static void main(String[] args) {
			Logger log =   LoggerFactory.getLogger(TruckConsumer.class);
			Properties properties = new Properties();
			properties.setProperty("bootstrap.servers","localhost:9092");
		properties.setProperty("key.deserializer", "org.apache.kafka.common.serialization.IntegerDeserializer");
		properties.setProperty("value.deserializer", TruckDeserializer.class.getName());
			properties.setProperty("group.id","TruckGPS");

		KafkaConsumer<Integer, TruckLocation> kafkaConsumer = new KafkaConsumer<>(properties);
		kafkaConsumer.subscribe(Collections.singleton("TruckCSTracking"));

		ConsumerRecords<Integer, TruckLocation> consumerRecords = kafkaConsumer.poll(Duration.ofSeconds(40));
		for (ConsumerRecord<Integer, TruckLocation> consumerRecord:consumerRecords){
//			log.info("Truck Id "+ consumerRecord.key());
//			System.out.println("Truck Id "+ consumerRecord.key());
//			log.info("Geo Location "+ consumerRecord.value());
//			System.out.println("Geo Location "+ consumerRecord.value());

			int id = consumerRecord.key();
			TruckLocation truckLocation = consumerRecord.value();
			System.out.println("ID :- "+ id);
			System.out.println("Latitude :- "+ truckLocation.getLatitude());
			System.out.println("Longitude :- "+ truckLocation.getLongitude());
		}
		kafkaConsumer.close();

	}
}
