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

import com.chatapp.jwt.JwtAuthRequest;
import com.chatapp.jwt.JwtAuthResponse;
import com.chatapp.jwt.JwtTokenHelper;

@RestController
public class AuthenticationController {
	@Autowired
	private JwtTokenHelper helper;
	
	@Autowired
	UserDetailsService detailsService;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	
	@PostMapping("/token")
	public ResponseEntity<JwtAuthResponse> createToken(@RequestBody JwtAuthRequest request ) throws Exception{
		
		this.authenticate(request.getUsername(),request.getPassword());
		
		UserDetails userdetails = this.detailsService.loadUserByUsername(request.getUsername());
		
		String token = this.helper.generateToken(userdetails);
		
		JwtAuthResponse response = new JwtAuthResponse(); 
		response.setToken(token);
		
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
