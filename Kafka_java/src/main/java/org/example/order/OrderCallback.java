package org.example.order;

import org.apache.kafka.clients.producer.RecordMetadata;

public class OrderCallback implements org.apache.kafka.clients.producer.Callback {
	@Override
	public void onCompletion(RecordMetadata recordMetadata, Exception e) {
		System.out.println(recordMetadata.offset());
		System.out.println(recordMetadata.partition());
		System.out.println("Message sent successfully");
		if(e!=null){
			e.printStackTrace();
		}
	}
}
