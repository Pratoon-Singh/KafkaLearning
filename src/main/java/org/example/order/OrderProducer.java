package org.example.order;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Properties;
import java.util.concurrent.Future;


public class OrderProducer {
		public static void main(String[] args) {
			Properties properties = new Properties();
//			properties.setProperty("bootstrap.servers", "localhost:9092");
			properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
//			properties.setProperty("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
			properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
//			properties.setProperty("value.serializer", "org.example.order.serializer.OrderSerializer");
			properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.example.order.serializer.OrderSerializer");
//			properties.setProperty("partitioner.class",VIPpartioner.class.getName());
			properties.setProperty(ProducerConfig.PARTITIONER_CLASS_CONFIG,VIPpartioner.class.getName());
			properties.setProperty(ProducerConfig.ACKS_CONFIG,"all");// have the three option 0, 1, all
			properties.setProperty(ProducerConfig.BUFFER_MEMORY_CONFIG,"4762767236"); // by default the memory is 256mb
			properties.setProperty(ProducerConfig.COMPRESSION_TYPE_CONFIG,"snappy");// the are 4 diifrent type of com pression snappy , gzip,lz,lz4
			properties.setProperty(ProducerConfig.RETRIES_CONFIG,"2");//by default 100 milli second
			properties.setProperty(ProducerConfig.RETRY_BACKOFF_MS_CONFIG,"400");// wait for time between each retry
			properties.setProperty(ProducerConfig.BATCH_SIZE_CONFIG,"1024");// the size of batch by default it is 16kb
			properties.setProperty(ProducerConfig.LINGER_MS_CONFIG,"244");// it helps to send the more message to the broker
			properties.setProperty(ProducerConfig.REQUEST_TIMEOUT_MS_CONFIG,"1023");// Producer will wait for the defined time and if fails it will retry
			properties.setProperty(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG,"true");//


			KafkaProducer<String, Order> kafkaProducer = new KafkaProducer<String,Order>(properties);
			Order order = new Order();
			order.setCustomerName("Jhon");
			order.setProduct("MacBook");
			order.setQuantity(2);
			ProducerRecord<String, Order> record = new ProducerRecord<String, Order>("OrderPartionedTopic", order.getCustomerName(), order);

			try {
//				int i = 10;
//				while(i==10){
				Future<RecordMetadata> send = kafkaProducer.send(record);// This is fire and forget
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

