package com.commodity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.commodity.entity.User;
import com.commodity.service.UserService;

@RestController
@CrossOrigin(origins="*")
@RequestMapping("/user-api")
public class UserController {

@Autowired
UserService userService;
	
	@PostMapping()
	public Boolean create(@RequestBody User user) {
		
		return userService.create(user);
		
		
				
	}
}
