package com.chatapp.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document("role-data")
public class Roles {
	@Field("role")
	private String role;
	@Id
	private String id;
	public Roles() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Roles(String role, String id) {
		super();
		this.role = role;
		this.id = id;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
}
