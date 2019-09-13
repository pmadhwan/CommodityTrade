package com.commodity.entity;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Deal {

	private String buyerBank;
	private String sellerBank;
	private String salesContractId;
	private String contractAddress;
	
	
	public String getBuyerBank() {
		return buyerBank;
	}
	public void setBuyerBank(String buyerBank) {
		this.buyerBank = buyerBank;
	}
	public String getSellerBank() {
		return sellerBank;
	}
	public void setSellerBank(String sellerBank) {
		this.sellerBank = sellerBank;
	}
	
	public String getContractAddress() {
		return contractAddress;
	}
	public void setContractAddress(String contractAddress) {
		this.contractAddress = contractAddress;
	}
	public String getSalesContractId() {
		return salesContractId;
	}
	public void setSalesContractId(String salesContractId) {
		this.salesContractId = salesContractId;
	}
	@Override
	public String toString() {
		return "Deal [buyerBank=" + buyerBank + ", sellerBank=" + sellerBank + ", contract="  + "]";
	}
	
	
	
}
