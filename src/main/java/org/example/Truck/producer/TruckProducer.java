package org.example.Truck.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.example.Truck.producer.serializer.TruckSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.table.TableRowSorter;
import java.util.Properties;
import java.util.concurrent.Future;


public class TruckProducer {
	public static void main(String[] args) {
		Logger log =   LoggerFactory.getLogger(TruckProducer.class);
		Properties properties = new Properties();
		properties.setProperty("bootstrap.servers","localhost:9092");
		properties.setProperty("key.serializer", "org.apache.kafka.common.serialization.IntegerSerializer");
		properties.setProperty("value.serializer", TruckSerializer.class.getName());

		KafkaProducer<Integer , TruckLocation> kafkaProducer = new KafkaProducer<>(properties);
		TruckLocation truckLocation = new TruckLocation();
		truckLocation.setId(1);
		truckLocation.setLatitude("357191 E");
		truckLocation.setLongitude("873919 N");
		ProducerRecord<Integer, TruckLocation> geoLocation = new ProducerRecord<Integer, TruckLocation>("TruckCSTracking", truckLocation.getId(),truckLocation);

try{
		Future<RecordMetadata> latitudeSend = kafkaProducer.send(geoLocation);
		log.info("geoLocation sent for consuming");

}catch (Exception e){
	log.error("Error in sending location ");
		e.printStackTrace();
	}finally {
	log.info("Closing the kafka producer");
		kafkaProducer.close();
	}

	}
}
