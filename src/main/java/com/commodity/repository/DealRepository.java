package com.commodity.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.commodity.entity.Deal;

public interface DealRepository extends MongoRepository<Deal, String>{
	
	Deal findByContractAddress(String contractAddress);
	

}
