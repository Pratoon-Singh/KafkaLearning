package org.example.OrderTransactional.serializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;
import org.example.order.Order;

import java.io.IOException;

public class OrderDeserializer implements Deserializer<Order> {
	@Override
	public Order deserialize(String topic, byte[] bytes) {

		ObjectMapper objectMapper = new ObjectMapper();
		Order order = null;
		try {
			order = objectMapper.readValue(bytes,Order.class);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return order;
	}
}
