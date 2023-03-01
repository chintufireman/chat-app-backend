package com.chatapp.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("message-data")
public class Message {
    @Id
    private String id;
    private String data;
    private String sentId;
    private String toId;
}
