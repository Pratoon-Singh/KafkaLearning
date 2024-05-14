package com.KafakConsumer.service;

import com.KafakConsumer.model.User;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class UserConsumerService {


	@KafkaListener(topics = {"user-topic"})
	public void consumeData(User user){
		System.out.println("User age is "+ user.getAge()+" User favourite genre is "+user.getFavGenre());
	}
}
