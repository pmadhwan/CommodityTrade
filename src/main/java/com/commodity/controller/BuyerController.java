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
import com.commodity.service.BuyerService;
import com.commodity.vo.ContractVO;
import com.commodity.vo.SalesContract;

@RestController
@CrossOrigin(origins="*")
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
	public ResponseEntity<ContractVO> getLOC(@RequestParam("contractAddress") String contractAddress)
			throws Exception {
		String txHash = buyerService.requestLOC(contractAddress);
		ContractVO contractVO=new ContractVO();
		contractVO.setTxHash(txHash);
		System.out.println("contract VO :"+contractVO);
		return ResponseEntity.status(HttpStatus.OK).body(contractVO);
	}
	
	@PostMapping("/tender")
	public Commodity generateTender(@RequestBody Commodity commodity) {
		System.out.println("in buyer controller-generate tender");
		return buyerService.createTender(commodity);
	}

	@PostMapping("/setbank")
	public String setBank(@RequestParam("bankAddress") String bankAddress,
			@RequestParam("bankType") String type,
			@RequestParam("ContractAddress") String ContractAddress)
			throws Exception {
		String b = buyerService.setBank(bankAddress,type,ContractAddress);
		return b;

	}
	
	@GetMapping("/all")
	public ResponseEntity<List<Commodity>> getAllFOrBuyer(@RequestParam("buyerId") String buyerId)
			throws IOException, CipherException {
		List<Commodity> cm = buyerService.getAll(buyerId);
		return  ResponseEntity.status(HttpStatus.OK).body(cm);
		
	}
	
	@GetMapping("/commodity/id")
	public ResponseEntity<SalesContract> getByCommodityId(@RequestParam("CommodityId") String CommodityId)
			throws IOException, CipherException {
		
		SalesContract cm = buyerService.getByCommId(CommodityId);
		return  ResponseEntity.status(HttpStatus.OK).body(cm);
		
	}
	
	

}
