package com.commodity.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.commodity.entity.Deal;

public interface DealRepository extends MongoRepository<Deal, String>{
	
	Deal findByContractAddress(String contractAddress);
	
	Deal findByCommodityId(String id);
	
	List<Deal> findByBuyerBank(String bankAddress);
	
	List<Deal> findBySellerBank(String bankAddress);
	

}
