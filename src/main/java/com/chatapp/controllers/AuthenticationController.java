package com.chatapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.chatapp.entity.User;
import com.chatapp.payloads.JwtAuthRequest;
import com.chatapp.payloads.JwtAuthResponse;
import com.chatapp.payloads.LoginDetails;
import com.chatapp.payloads.UserDto;
import com.chatapp.security.JwtTokenHelper;
import com.chatapp.service.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@RestController
public class AuthenticationController {
	private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

	@Autowired
	private JwtTokenHelper helper;
	
	@Autowired
	UserDetailsService detailsService;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	private UserService service;
	
	
	
	@PostMapping("/token")
	public ResponseEntity<JwtAuthResponse> createToken(@RequestBody JwtAuthRequest request ) throws Exception{
		JwtAuthResponse response = new JwtAuthResponse(); 
		try {
			this.authenticate(request.getUsername(),request.getPassword());
		
			UserDetails userdetails = this.detailsService.loadUserByUsername(request.getUsername());
		
			String token = this.helper.generateToken(userdetails);
		
			
			response.setToken(token);
		
			//Logindetails to replicate get user functionality from email and password
			LoginDetails login = new LoginDetails();
			login.setPassword(request.getPassword());
			login.setUsername(request.getUsername());
		
			//get user
				User getUser = service.getUser(login);
		
				UserDto user = new UserDto();
				user.setName(getUser.getName());
				user.setUsername(getUser.getEmail());
		
				//set user
				response.setUser(user);
		}
		catch(Exception e) {
			logger.error("Error in authentication controller", e);
		}
		return new ResponseEntity<JwtAuthResponse>(response,HttpStatus.OK);
	}

	private void authenticate(String username, String password) throws Exception {
		
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,password); 
		//this will authenticate if out username and password are right or wrong
		
		try {
			this.authenticationManager.authenticate(authenticationToken);
		}
		catch(BadCredentialsException e){
			System.out.println("invalid details");
			throw new Exception("invalid username and passwd");
		}
	}
	
}
