package org.example.order.simpleConsumer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.example.order.Order;
import org.example.order.VIPpartioner;

import java.util.Properties;
import java.util.concurrent.Future;

public class SimpleProducer {
	public static void main(String[] args) {
		Properties properties = new Properties();
		properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
		properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.example.order.serializer.OrderSerializer");


		KafkaProducer<String, Order> kafkaProducer = new KafkaProducer<String,Order>(properties);
		Order order = new Order();
		order.setCustomerName("Jhon");
		order.setProduct("MacBook");
		order.setQuantity(2);
		ProducerRecord<String, Order> record = new ProducerRecord<String, Order>("SimpleConsumerTopic", order.getCustomerName(), order);

		try {
			Future<RecordMetadata> send = kafkaProducer.send(record);// This is fire and forget
		}catch (Exception e){
			e.printStackTrace();
		}finally {
			kafkaProducer.close();
		}
	}
}
