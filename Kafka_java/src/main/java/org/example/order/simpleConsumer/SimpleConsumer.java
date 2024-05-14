package org.example.order.simpleConsumer;


import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.example.order.Order;
import org.example.order.serializer.OrderDeserializer;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

public class SimpleConsumer {
	public static void main(String[] args) {
		Properties properties = new Properties();
		properties.setProperty("bootstrap.servers", "localhost:9092");
		properties.setProperty("key.deserializer", StringDeserializer.class.getName());
		properties.setProperty("value.deserializer", OrderDeserializer.class.getName());
		properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,"earliest");


		KafkaConsumer<String, Order> kafkaConsumer = new KafkaConsumer<String, Order>(properties);
		 List<PartitionInfo> simpleConsumerTopicInfos = kafkaConsumer.partitionsFor("SimpleConsumerTopic");

		 ArrayList<TopicPartition> partitions = new ArrayList<>();
		for (PartitionInfo info:simpleConsumerTopicInfos){
			partitions.add(new TopicPartition("SimpleConsumerTopic",info.partition()));
		}
		kafkaConsumer.assign(partitions);
		ConsumerRecords<String,Order> consumerRecords = kafkaConsumer.poll(Duration.ofSeconds(20));
		for (ConsumerRecord<String,Order> consumerRecord:consumerRecords){
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

