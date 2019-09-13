package com.commodity.vo;

import com.commodity.commons.BankType;

public class Bank {

	private String BanknodeAddress;
	private BankType type;
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
