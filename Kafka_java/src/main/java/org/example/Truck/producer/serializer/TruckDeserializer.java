package org.example.Truck.producer.serializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serializer;
import org.example.Truck.producer.TruckLocation;

import java.io.IOException;

public class TruckDeserializer implements Deserializer<TruckLocation> {

	@Override
	public TruckLocation deserialize(String topic, byte[] bytes) {
		ObjectMapper objectMapper = new ObjectMapper();
		TruckLocation truckLocation = null;
		try {
			truckLocation =objectMapper.readValue(bytes,TruckLocation.class);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return truckLocation;
	}
}
