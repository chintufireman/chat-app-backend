package com.chatapp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chatapp.redis.MessagePublisher;
import com.chatapp.redis.UserRepoRedis;
import com.chatapp.redis.entity.User;

import io.lettuce.core.dynamic.annotation.Param;

@RestController
public class RedisController {

	@Autowired
	private MessagePublisher messagePublisher;
	
	@Autowired
	private UserRepoRedis redis;
	
	@GetMapping("/redis")
	public ResponseEntity<String> publishMessage(@RequestParam ("message") String message){
		messagePublisher.publishMessage(message);
		return ResponseEntity.ok("Message published: " + message); 
	}
	
	@GetMapping("/redis/save")
	public User save(@RequestBody User user){
		return  redis.saveUser(user);
	}
	@GetMapping("/redis/getall")
	public List<User> getAll(@RequestParam("email") String email){
		return  redis.findAll();
	}
	@GetMapping("/redis/get")
	public User getUser(@RequestParam("email") String email){
		return  redis.getUser(email);
	}
}
