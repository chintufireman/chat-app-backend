package com.chatapp.redis;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class Receiver implements MessageListener{

	//after receiving msg from client 
	@Override
	public void onMessage(Message message, @Nullable byte[] pattern) {
		//getting chhanel
		String channel= new String(message.getChannel());
		
		//message body
		String body = new String(message.getBody());
		
		System.out.println("Received message from channel: " + channel);
        System.out.println("Message body: " + body);
	}
	
}
