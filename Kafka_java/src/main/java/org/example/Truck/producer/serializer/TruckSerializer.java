package org.example.Truck.producer.serializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Serializer;
import org.example.Truck.producer.TruckLocation;

public class TruckSerializer implements Serializer<TruckLocation> {
	@Override
	public byte[] serialize(String topic, TruckLocation truckLocation) {
		byte[] response = null;
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			response=objectMapper.writeValueAsString(truckLocation).getBytes();
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
		return response;
	}
}
