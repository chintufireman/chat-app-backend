package com.chatapp.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.chatapp.entity.Roles;

public interface RolesRepo extends MongoRepository<Roles, Integer> {
	Optional<Roles> findByRole(String name);
}
