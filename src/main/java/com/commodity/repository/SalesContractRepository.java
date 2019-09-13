package com.commodity.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.commodity.vo.SalesContract;

public interface SalesContractRepository extends MongoRepository<SalesContract,String>{
	

}
