package com.commodity.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.web3j.crypto.CipherException;

import com.commodity.entity.Commodity;
import com.commodity.service.BeneficiaryBankService;
import com.commodity.service.IssuanceBankService;
import com.commodity.vo.ContractVO;
import com.commodity.vo.SalesContract;

@RestController
@CrossOrigin(origins="*")
@RequestMapping("/beneficiary-bank-api")
public class BeneficiaryBankController {
	
	@Autowired
	private BeneficiaryBankService bankService;

	@GetMapping("/all")
	public ResponseEntity<List<Commodity>> getAll(@RequestParam("bankId") String bankId) throws IOException, CipherException{
		
		List<Commodity> list=bankService.getAll(bankId);
		return ResponseEntity.status(HttpStatus.OK).body(list);
	}
	@GetMapping("/")
	public SalesContract getDetails(@RequestParam("contractAddress") String address) throws IOException, CipherException{
		
		return bankService.getDetails(address);
	}
	
	@GetMapping("/accept")
	public ResponseEntity<ContractVO> AcceptContract(@RequestParam("contractAddress") String contractAddress) throws Exception{
		
		String txHash=bankService.acceptContract(contractAddress);
		ContractVO con= new ContractVO();
		con.setTxHash(txHash);
		return  ResponseEntity.status(HttpStatus.OK).body(con);
	}
	
	@GetMapping("/reject")
	public ResponseEntity<ContractVO> rejectContract(@RequestParam("contractAddress") String contractAddress) throws Exception{
		
		String txHash= bankService.rejectContract(contractAddress);
		ContractVO con= new ContractVO();
		con.setTxHash(txHash);
		return  ResponseEntity.status(HttpStatus.OK).body(con);
	}
	

}
