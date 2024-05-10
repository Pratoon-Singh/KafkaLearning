package org.example.OrderTransactional;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.example.order.Order;
import org.example.order.serializer.OrderDeserializer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class OrderConsumer {
	public static void main(String[] args) {
		Properties properties = new Properties();
		properties.setProperty("bootstrap.servers", "localhost:9092");
//		properties.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
//		properties.setProperty("value.deserializer", "org.example.order.serializer.OrderDeserializer");
		// Other way to do it
		properties.setProperty("key.deserializer", StringDeserializer.class.getName());
		properties.setProperty("value.deserializer", OrderDeserializer.class.getName());
		properties.setProperty("group.id","OrderGroup");

		KafkaConsumer<String, org.example.order.Order> kafkaConsumer = new KafkaConsumer<String, org.example.order.Order>(properties);
		kafkaConsumer.subscribe(Collections.singleton("TruckTransaction"));

		ConsumerRecords<String, org.example.order.Order> consumerRecords = kafkaConsumer.poll(Duration.ofSeconds(20));
		for (ConsumerRecord<String, org.example.order.Order> consumerRecord:consumerRecords){
			String customerName = consumerRecord.key();
			Order order = consumerRecord.value();
			System.out.println("Customer Name:- "+ customerName);
			System.out.println("Product Name:- "+ order.getProduct());
			System.out.println("Quantity :- "+ order.getQuantity());
			System.out.println("Partition :- " + consumerRecord.partition());

		}
		kafkaConsumer.close();
	}
	//The onCompletion method on the asynchronous callback class we create receives which of the following along with the RecordMetadata
//	Exception
}
