package com.chatapp.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document("sender_receiver_relation")
public class SenderReceiverRelation {
	@Id
	private String id;
	@DBRef
	@Field("sender_id")
	private User sender;
	@DBRef
	@Field("receiver_id")
	private User receiver;

	public SenderReceiverRelation() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SenderReceiverRelation(String id, User sender, User receiver) {
		super();
		this.id = id;
		this.sender = sender;
		this.receiver = receiver;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	public User getReceiver() {
		return receiver;
	}

	public void setReceiver(User receiver) {
		this.receiver = receiver;
	}
}
