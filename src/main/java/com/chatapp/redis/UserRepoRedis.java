package com.chatapp.redis;

import java.util.List;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.chatapp.redis.entity.User;

@Repository
public class UserRepoRedis {
	
	private HashOperations hashOperations;
	private RedisTemplate redisTemplate;
	
	public UserRepoRedis(RedisTemplate redisTemplate) {
		super();
		this.hashOperations = redisTemplate.opsForHash();
		this.redisTemplate = redisTemplate;
	}
	
	public User saveUser(User user) {
		hashOperations.put("user", user.getEmail(), user);
		return user;
	}
	public User getUser(String email) {
		return (User) hashOperations.get("user", email);
	}
	
	public List<User> findAll(){
		return hashOperations.values("user");
	}
	
	
}
