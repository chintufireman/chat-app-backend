package com.chatapp.service;

import java.util.List;

import com.chatapp.entity.User;
import com.chatapp.payloads.LoginDetails;

public interface UserService {
    public User saveUser(User user);
    public User getUser(LoginDetails details) throws Exception;
    public List<User> getAllUsers();
    public User searchUser(String userName);
    public List<User> getAllReceiversFromTheSender(String senderId);
}
