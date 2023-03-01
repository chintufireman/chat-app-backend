package com.chatapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.chatapp.entity.User;
import com.chatapp.model.Message;
import com.chatapp.service.UserService;

@RestController
public class MessageController {

    @Autowired
    private UserService userService;

    @MessageMapping("/chatTo") 
    @SendTo("/topic/return-to")
    @CrossOrigin("*")
    public Message getContent(@RequestBody Message message) {
       try {
        //    Thread.sleep(1000);
       } catch (Exception e) {
           e.printStackTrace();
       }
        return message;
    }

    @PostMapping("/create")
    public User createUsre(@RequestBody User user){
        return userService.savUser(user);
    }
}
