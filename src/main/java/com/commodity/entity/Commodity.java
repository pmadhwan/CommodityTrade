package com.commodity.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.commodity.commons.ContractState;

@Document
public class Commodity {
	
	@Id
	private String id;
	private String commodityName;
	private Long weight;
	private Long price;
	private String buyerId;
	private ContractState status;
	
	
	public Commodity() {
		
	}
	
	
	
	
	


	public Commodity(String id, String commodityName, Long weight, Long price, String buyerId, ContractState status) {
		super();
		this.id = id;
		this.commodityName = commodityName;
		this.weight = weight;
		this.price = price;
		this.buyerId = buyerId;
		this.status = status;
	}







	public String getBuyerId() {
		return buyerId;
	}




	public void setBuyerId(String buyerId) {
		this.buyerId = buyerId;
	}




	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCommodityName() {
		return commodityName;
	}
	public void setCommodityName(String commodityName) {
		this.commodityName = commodityName;
	}
	public Long getWeight() {
		return weight;
	}
	public void setWeight(Long weight) {
		this.weight = weight;
	}




	@Override
	public String toString() {
		return "Commodity [id=" + id + ", commodityName=" + commodityName + ", weight=" + weight + ", price=" + price
				+ ", buyerId=" + buyerId + ", status=" + status + "]";
	}




	public Long getPrice() {
		return price;
	}




	public void setPrice(Long price) {
		this.price = price;
	}




	public ContractState getStatus() {
		return status;
	}




	public void setStatus(ContractState contractState) {
		this.status = contractState;
	}




}
