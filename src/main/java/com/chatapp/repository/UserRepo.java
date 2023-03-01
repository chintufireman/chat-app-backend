package com.chatapp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.chatapp.entity.User;

public interface UserRepo extends MongoRepository<User,String> {
    
}
