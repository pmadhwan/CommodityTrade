package com.commodity.vo;

import com.commodity.commons.BankType;

public class Bank {

	private String BanknodeAddress;
	private BankType type;
	private String txhash;
	
	public Bank() {
		
	}
	
	
	public Bank(String banknodeAddress, BankType type, String txhash) {
		super();
		BanknodeAddress = banknodeAddress;
		this.type = type;
		this.txhash = txhash;
	}
	public String getTxhash() {
		return txhash;
	}
	public void setTxhash(String txhash) {
		this.txhash = txhash;
	}
	public String getBanknodeAddress() {
		return BanknodeAddress;
	}
	public void setBanknodeAddress(String banknodeAddress) {
		BanknodeAddress = banknodeAddress;
	}
	public BankType getType() {
		return type;
	}
	public void setType(BankType type) {
		this.type = type;
	}
	
	
}
