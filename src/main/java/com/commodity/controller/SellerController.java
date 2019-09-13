package com.commodity.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.web3j.crypto.CipherException;

import com.commodity.service.SellerService;
import com.commodity.vo.Bank;
import com.commodity.vo.SalesContract;

@RestController
@RequestMapping("/seller-api")
public class SellerController {

	@Autowired
	private SellerService sellerService;

	// generate sales contract
	@PostMapping("/create")
	public String generateContract(@RequestBody SalesContract salesContract) {
		String state = sellerService.generateSalesContract(salesContract);
		return state;
	}

	@GetMapping("/")
	public SalesContract getDetails(@RequestParam("contractAddress") String contractAddress)
			throws IOException, CipherException {

		SalesContract contract = sellerService.getDetails(contractAddress);
		return contract;
	}

	@PostMapping("/setbank")
	public String setBank(@RequestBody Bank bank, @RequestParam("contractAddress") String contractAddress)
			throws Exception {
		String b = sellerService.setBank(bank, contractAddress);
		return b;

	}

}
