package com.commodity.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.commodity.entity.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

	
	
}
