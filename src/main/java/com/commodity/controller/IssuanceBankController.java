package com.commodity.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.web3j.crypto.CipherException;

import com.commodity.service.IssuanceBankService;
import com.commodity.vo.SalesContract;

@RestController
@RequestMapping("/issuance-bank-api")
public class IssuanceBankController {
	
	@Autowired
	private IssuanceBankService bankService;
	
	@GetMapping("/")
	public SalesContract getDetails(@RequestParam("contractAddress") String address) throws IOException, CipherException{
		
		return bankService.getDetails(address);
	}
	
	@GetMapping("/accept")
	public String AcceptContract(@RequestParam("contractAddress") String contractAddress) throws Exception{
		
		return bankService.acceptContract(contractAddress);
	}
	
	@GetMapping("/reject")
	public String rejectContract(@RequestParam("contractAddress") String contractAddress) throws Exception{
		
		return bankService.rejectContract(contractAddress);
	}
	

}
