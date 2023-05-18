package com.chatapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.chatapp.entity.Roles;
import com.chatapp.service.RolesService;

@RestController
public class RolesController {

	@Autowired
	private RolesService rolesService;
	
	@PostMapping("/createRole")
	public ResponseEntity<Roles> addRole(@RequestBody Roles roles){
		Roles roles2 = this.rolesService.createRole(roles);
		return new ResponseEntity<Roles>(roles2,HttpStatus.ACCEPTED);
	}
}
