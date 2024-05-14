package com.example.KafakApp.controller;

import com.example.KafakApp.model.User;
import com.example.KafakApp.service.UserProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/userapi")
public class KafkaController {

	@Autowired
	private UserProductService userProductService;

	@PostMapping("/publishuserdata")
	public void sendUserData(@RequestBody User user){

		userProductService.sendUserData(user);
	}

}
