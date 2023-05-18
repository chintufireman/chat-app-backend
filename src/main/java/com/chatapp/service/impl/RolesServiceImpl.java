package com.chatapp.service.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chatapp.entity.Roles;
import com.chatapp.repository.RolesRepo;
import com.chatapp.service.RolesService;

@Service
public class RolesServiceImpl implements RolesService{
	@Autowired
	private RolesRepo rolesRepo;

	@Override
	public Roles createRole(Roles role) {
		role.setId(UUID.randomUUID().toString());
		Roles newRole = this.rolesRepo.save(role);
		return newRole;
	}
	
	
}
