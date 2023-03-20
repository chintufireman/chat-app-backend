package com.chatapp.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.chatapp.entity.User;

public interface UserRepo extends MongoRepository<User,String> {
    Optional<User> findByEmail(String email);
}
