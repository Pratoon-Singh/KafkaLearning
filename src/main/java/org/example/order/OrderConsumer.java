package org.example.order;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.example.order.serializer.OrderDeserializer;

import java.time.Duration;
import java.util.Collection;
import java.util.Collections;
import java.util.Properties;
import java.util.concurrent.Future;

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

		KafkaConsumer<String, Order> kafkaConsumer = new KafkaConsumer<String, Order>(properties);
		kafkaConsumer.subscribe(Collections.singleton("OrderPartionedTopic"));

		ConsumerRecords<String,Order> consumerRecords = kafkaConsumer.poll(Duration.ofSeconds(20));
		for (ConsumerRecord<String,Order> consumerRecord:consumerRecords){
			String customerName = consumerRecord.key();
			Order order = consumerRecord.value();
			System.out.println("Customer Name:- "+ customerName);
			System.out.println("Product Name:- "+ order.getProduct());
			System.out.println("Quantity :- "+ order.getQuantity());

		}
		kafkaConsumer.close();
	}
	//The onCompletion method on the asynchronous callback class we create receives which of the following along with the RecordMetadata
//	Exception
}
