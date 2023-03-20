package com.chatapp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.chatapp.entity.Message;

public interface MessageRepo extends MongoRepository<Message,String>{
    
}
