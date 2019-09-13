package com.commodity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.commodity.service.IssuanceBankService;

@RestController
@RequestMapping("/bank-api")
public class BankController {
	
	@Autowired
	private IssuanceBankService bankService;
	
	@GetMapping("/")
	public String getDetails(){
		
		return "success";
	}
	

}
