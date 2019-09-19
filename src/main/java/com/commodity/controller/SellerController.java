package com.commodity.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.web3j.crypto.CipherException;

import com.commodity.entity.Commodity;
import com.commodity.service.SellerService;
import com.commodity.vo.Bank;
import com.commodity.vo.ContractVO;
import com.commodity.vo.SalesContract;

@RestController
@CrossOrigin(origins="*")
@RequestMapping("/seller-api")
public class SellerController {

	@Autowired
	private SellerService sellerService;

	// generate sales contract
	@PostMapping("/create")
	public ResponseEntity<ContractVO> generateContract(@RequestBody SalesContract salesContract) {
		System.out.println("sales contract from controller:"+salesContract);
		String address = sellerService.generateSalesContract(salesContract);
		ContractVO con= new ContractVO();
		con.setContractAddress(address);
		return  ResponseEntity.status(HttpStatus.OK).body(con);
	}

	@GetMapping("/")
	public SalesContract getDetails(@RequestParam("contractAddress") String contractAddress)
			throws IOException, CipherException {

		SalesContract contract = sellerService.getDetails(contractAddress);
		return contract;
	}

	@PostMapping("/setbank")
	public ResponseEntity<Bank> setBank(@RequestParam("bankAddress") String bankAddress,
			@RequestParam("bankType") String bankType,
			@RequestParam("contractAddress") String contractAddress)
			throws Exception {
		String b = sellerService.setBank(bankAddress,bankType,contractAddress);
		Bank bank = new Bank();
		bank.setTxhash(b);
		return ResponseEntity.status(HttpStatus.OK).body(bank);

	}
	
	@GetMapping("/all")
	public ResponseEntity<List<Commodity>> getAllFOrSeller()
			throws IOException, CipherException {
		List<Commodity> cm = sellerService.getAll();
		System.out.println(cm);
		return  ResponseEntity.status(HttpStatus.OK).body(cm);
		
	}
	

}
