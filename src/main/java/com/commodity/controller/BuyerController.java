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

import com.commodity.entity.Commodity;
import com.commodity.service.BuyerService;
import com.commodity.vo.Bank;
import com.commodity.vo.SalesContract;

@RestController
@RequestMapping("/buyer-api")
public class BuyerController {

	@Autowired
	private BuyerService buyerService;

	@GetMapping("/")
	public SalesContract getDetails(@RequestParam("contractAddress") String contractAddress)
			throws IOException, CipherException {
		SalesContract contract = buyerService.getDetails(contractAddress);
		return contract;
	}
	
	
	@GetMapping("/request/loc")
	public String getLOC(@RequestParam("contractAddress") String contractAddress)
			throws Exception {
		String contract = buyerService.requestLOC(contractAddress);
		return contract;
	}
	
	@PostMapping("/tender")
	public Commodity generateTender(@RequestBody Commodity commodity) {
		System.out.println("in buyer controller-generate tender");
		return buyerService.createTender(commodity);
	}

	@PostMapping("/setbank")
	public String setBank(@RequestBody Bank bank, @RequestParam("contractAddress") String contractAddress)
			throws Exception {
		String b = buyerService.setBank(bank, contractAddress);
		return b;

	}

}
