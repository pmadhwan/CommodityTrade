package com.commodity.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.commodity.entity.User;
import com.commodity.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepo;
	
	public Boolean create(User u) {
		try {
			userRepo.save(u);
			return true;
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public User get(String id) {
		Optional<User> u = userRepo.findById(id);
		if(u.isPresent()) {
			return u.get();
		} else return null;
	}
}
