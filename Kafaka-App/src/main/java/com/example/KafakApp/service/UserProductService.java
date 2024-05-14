package com.example.KafakApp.service;

import com.example.KafakApp.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserProductService {

	@Autowired
	private KafkaTemplate<String, User> kafkaTemplate;

	public void sendUserData(User user){
		kafkaTemplate.send("user-topic",user.getName(), user);
	}

}
