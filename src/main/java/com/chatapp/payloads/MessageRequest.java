package com.chatapp.payloads;

import com.chatapp.redis.entity.User;
import com.fasterxml.jackson.annotation.JsonProperty;

public class MessageRequest {
    private String name;
    private String content;
    
    @JsonProperty("user")
    private User user;
    
    public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public MessageRequest(String name, String content) {
        this.name = name;
        this.content = content;
    }

    public MessageRequest() {
    }
}
