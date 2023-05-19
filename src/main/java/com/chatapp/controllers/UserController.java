package com.chatapp.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chatapp.entity.User;
import com.chatapp.payloads.LoginDetails;
import com.chatapp.service.UserService;

@RestController
@RequestMapping("/api/v1/user-handle")
public class UserController {
	@Autowired
	private UserService userService;
	
	@PostMapping("/create")
	public ResponseEntity<User> createUsre(@RequestBody User user) {
		User user2 = userService.saveUser(user); 
		return new ResponseEntity<User>(user2, HttpStatus.CREATED);
	}
	
	@PostMapping("/login")
	public User login(@RequestBody LoginDetails user) {
		User u = null;
		try {
			u = userService.getUser(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return u;
	}
	
	
	@GetMapping("/users")
	public ResponseEntity<List<User>> getUser() throws Exception {
		List<User> list = userService.getAllUsers();
		return new ResponseEntity<List<User>>(list,HttpStatus.OK);
	}

	@GetMapping("/find-user/{username}")
	public ResponseEntity<User> searchUser(@PathVariable String username) throws Exception {
		return ResponseEntity.ok(this.userService.searchUser(username));
	}

	@GetMapping("/sent-messages/{username}")
	public ResponseEntity<List<User>> getAllUsersFromSender(@PathVariable String username) throws Exception {
		List<User> receivers = this.userService.getAllReceiversFromTheSender(username);
		return ResponseEntity.ok(receivers);
	}


}
