package com.commodity.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Deal {

	private String buyerBank;
	private String sellerBank;
	private String salesContractId;
	@Id()
	private String contractAddress;
	private String SellerId;
	private String BuyerId;
	private String commodityId;
	
	
	
	
	public Deal() {
		//super();
	}
	public Deal(String buyerBank, String sellerBank, String salesContractId, String contractAddress, String sellerId,
			String buyerId, String commodityId) {
		super();
		this.buyerBank = buyerBank;
		this.sellerBank = sellerBank;
		this.salesContractId = salesContractId;
		this.contractAddress = contractAddress;
		SellerId = sellerId;
		BuyerId = buyerId;
		this.commodityId = commodityId;
	}
	public String getSellerId() {
		return SellerId;
	}
	public void setSellerId(String sellerId) {
		SellerId = sellerId;
	}
	public String getBuyerId() {
		return BuyerId;
	}
	public void setBuyerId(String buyerId) {
		BuyerId = buyerId;
	}
	public String getCommodityId() {
		return commodityId;
	}
	public void setCommodityId(String commodityId) {
		this.commodityId = commodityId;
	}
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
