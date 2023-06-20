package com.chatapp.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

//register the Receiver with the message listener container
//so that it will receive messages. 

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Autowired
	private Receiver receiver;

	@Autowired
	private RedisConnectionFactory redisConnectionFactory;

	
	@Bean
	public ChannelTopic channelTopic() {
		return new ChannelTopic("chat-app");
	}

	@Bean
	public RedisMessageListenerContainer redisMessageListenerContainer() {
		RedisMessageListenerContainer container = new RedisMessageListenerContainer();
		container.setConnectionFactory(redisConnectionFactory);
		container.addMessageListener(messageListenerAdapter(), channelTopic());
		return container;
	}

	/*
	 * 
	 * The MessageListenerAdapter is responsible for converting the incoming Redis
	 * messages and delegating them to the receiver's onMessage method.
	 * 
	 */
	@Bean
	public MessageListenerAdapter messageListenerAdapter() {
		return new MessageListenerAdapter(receiver);
	}

	/*
	 * The MessagePublisher is a separate class that uses the StringRedisTemplate to
	 * publish messages to Redis.
	 */

	@Bean
	public MessagePublisher messagePublisher() {
		return new MessagePublisher(stringRedisTemplate, channelTopic());
	}

	
	
	
	RedisTemplate redisTemplate() {
		RedisTemplate redisTemplate = new RedisTemplate();
		redisTemplate.setConnectionFactory(redisConnectionFactory);
		return redisTemplate;
	}
}
