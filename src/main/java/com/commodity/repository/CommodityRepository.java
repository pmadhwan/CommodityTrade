package com.commodity.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.commodity.entity.Commodity;

@Repository
public interface CommodityRepository extends MongoRepository<Commodity, String> {

	

	//public List<UserVO> findByUserIdNot(String userId);
}
