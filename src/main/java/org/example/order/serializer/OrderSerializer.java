package org.example.order.serializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Serializer;
import org.example.order.Order;

public class OrderSerializer implements Serializer<Order> {
	@Override
	public byte[] serialize(String topic, Order order ) {
		byte[] response= null;
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			response = objectMapper.writeValueAsString(order).getBytes();
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
		return response;
	}
}
