package com.chatapp.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chatapp.entity.User;
import com.chatapp.repository.UserRepo;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo;
    @Override
    public User savUser(User user) {
        user.setId(UUID.randomUUID().toString());
        User u =userRepo.save(user);
        return u;
    }
    
}
