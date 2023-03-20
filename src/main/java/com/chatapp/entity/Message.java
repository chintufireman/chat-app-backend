package com.chatapp.entity;

import java.util.Date;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document("message-data")
public class Message {
    @Id
    private String id;
	private String data;
	@Field("sender_id")
	private String senderId;
	@Field("reciver_id")
	private String reciverId;
	@CreatedDate
	@Field("created_date")
    private Date createdDate;

	public Message() {
	}
	public Message(String id, String data, String senderId, String reciverId, Date createdDate) {
		this.id = id;
		this.data = data;
		this.senderId = senderId;
		this.reciverId = reciverId;
		this.createdDate = createdDate;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getSenderId() {
		return senderId;
	}
	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}
	public String getReciverId() {
		return reciverId;
	}
	public void setReciverId(String reciverId) {
		this.reciverId = reciverId;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
}
