package com.chatapp.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Component;

public class MessagePublisher {
    private final StringRedisTemplate redisTemplate;
    private final ChannelTopic channelTopic;

    @Autowired
    public MessagePublisher(StringRedisTemplate redisTemplate, ChannelTopic channelTopic) {
        this.redisTemplate = redisTemplate;
        this.channelTopic = channelTopic;
    }

    public void publishMessage(String message) {
        redisTemplate.convertAndSend(channelTopic.getTopic(), message);
    }
}
