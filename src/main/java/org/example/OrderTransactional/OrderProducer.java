package org.example.OrderTransactional;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.example.order.Order;

import java.util.Properties;
import java.util.concurrent.Future;


public class OrderProducer {
		public static void main(String[] args) {
			Properties properties = new Properties();
			properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
			properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
			properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.example.order.serializer.OrderSerializer");
			properties.setProperty(ProducerConfig.TRANSACTIONAL_ID_CONFIG,"Order_ID");
//			properties.setProperty(ProducerConfig.MAX_BLOCK_MS_CONFIG,"1000");

			KafkaProducer<String, OrderModel> kafkaProducer = new KafkaProducer<String, OrderModel>(properties);
			kafkaProducer.initTransactions();
			OrderModel order1 = new OrderModel();
			order1.setCustomerName("Jhon");
			order1.setProduct("Xioami");
			order1.setQuantity(2);
			ProducerRecord<String, OrderModel> record = new ProducerRecord<String, OrderModel>(
					"TruckTransaction", order1.getCustomerName(), order1);
			OrderModel order2 = new OrderModel();
			order2.setCustomerName("Pratoon");
			order2.setProduct("MacBook");
			order2.setQuantity(2);
			ProducerRecord<String, OrderModel> record1 = new ProducerRecord<String, OrderModel>(
					"TruckTransaction", order2.getCustomerName(), order2);

			OrderModel order3 = new OrderModel();
			order3.setCustomerName("Jimmy");
			order3.setProduct("HP");
			order3.setQuantity(2);
			ProducerRecord<String, OrderModel> record2 = new ProducerRecord<String, OrderModel>(
					"TruckTransaction", order3.getCustomerName(), order3);
			OrderModel order4 = new OrderModel();
			order4.setCustomerName("Alex");
			order4.setProduct("Lenovo");
			order4.setQuantity(2);
			ProducerRecord<String, OrderModel> record3 = new ProducerRecord<String, OrderModel>(
					"TruckTransaction", order4.getCustomerName(), order4);
			try {
				kafkaProducer.beginTransaction();
				kafkaProducer.send(record1);
				kafkaProducer.send(record2);
				kafkaProducer.send(record3);
				kafkaProducer.send(record);
				kafkaProducer.commitTransaction();

			}catch (Exception e){
				kafkaProducer.abortTransaction();
				e.printStackTrace();
			}finally {
				kafkaProducer.close();
			}
		}
	}

