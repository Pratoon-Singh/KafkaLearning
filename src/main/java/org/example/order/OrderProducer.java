package org.example.order;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Properties;
import java.util.concurrent.Future;


public class OrderProducer {
		public static void main(String[] args) {
			Properties properties = new Properties();
			properties.setProperty("bootstrap.servers", "localhost:9092");
			properties.setProperty("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
			properties.setProperty("value.serializer", "org.example.order.serializer.OrderSerializer");
			properties.setProperty("")

			KafkaProducer<String, Order> kafkaProducer = new KafkaProducer<String,Order>(properties);
			Order order = new Order();
			order.setCustomerName("Pratoon");
			order.setProduct("MacBook");
			order.setQuantity(2);
			ProducerRecord<String, Order> record = new ProducerRecord<String, Order>("OrderPartionedTopic", order.getCustomerName(), order);

			try {
				int i = 10;
				while(i==10){
				Future<RecordMetadata> send = kafkaProducer.send(record);}// This is fire and forget
				//RecordMetadata send1 = kafkaProducer.send(record).get();// This is syncronous call we are waiting for the call
				//System.out.println(send1.offset());
				//System.out.println(send1.partition());
				//System.out.println("message sent successfully");
				//kafkaProducer.send(record,new OrderCallback());// This is Asyncronous call and we have to pass a callback in the producer
				//it will not wait whenever the response is back the order callback will be called

			}catch (Exception e){
				e.printStackTrace();
			}finally {
				kafkaProducer.close();
			}
		}
	}

