package com.chatapp.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.chatapp.config.AppConstants;
import com.chatapp.entity.Message;
import com.chatapp.entity.Roles;
import com.chatapp.entity.User;
import com.chatapp.payloads.LoginDetails;
import com.chatapp.repository.MessageRepo;
import com.chatapp.repository.RolesRepo;
import com.chatapp.repository.UserRepo;
import com.chatapp.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepo userRepo;

	@Autowired
	private MongoTemplate mongoTemplate;

	@Autowired
	private MessageRepo messageRepo;

	@Autowired
	private RolesRepo rolesRepo;

	@Override
	public User saveUser(User user) {
		user.setId(UUID.randomUUID().toString());
		Roles role = this.rolesRepo.findByRole("USER").get();
		List<Roles> roles = new ArrayList<Roles>();
		roles.add(role);
		user.setRoles(roles);
		User u = userRepo.save(user);
		return u;
	}

	@Override
	public User getUser(LoginDetails loginDetails) throws Exception {
		User u = userRepo.findByEmail(loginDetails.getUsername()).orElseThrow(() -> new Exception("User not found"));
		if (u.getPassword().equals(loginDetails.getPassword())) {
			return u;
		}
		return null;
	}

	@Override
	public List<User> getAllUsers() {
		List<User> list = userRepo.findAll();
		return list;
	}

	@Override
	public User searchUser(String userName) {
		User user = null;
		try {
			user = this.userRepo.findByEmail(userName)
					.orElseThrow(() -> new Exception("No user with such username found"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public List<User> getAllReceiversFromTheSender(String senderId) {
		List<String> receiverIds = this.getReceiverIdsBySenderId(senderId);

		// this.userRepo.findAllById(reciversIds) by using this it will hit database for
		// reciverId, instead of that use mongotemplate
		List<User> users = mongoTemplate.find(Query.query(Criteria.where("email").in(receiverIds)), User.class);
		return users;

	}

	public List<String> getReceiverIdsBySenderId(String senderId) {
		Query query = new Query();
		query.addCriteria(Criteria.where("sender_id").is(senderId));
		query.fields().include("reciver_id");
		List<String> receiverIds = mongoTemplate.findDistinct(query, "reciver_id", Message.class, String.class);
		// we have to specify Message.class to show that document class that is being
		// queried
		return receiverIds;
	}

}
