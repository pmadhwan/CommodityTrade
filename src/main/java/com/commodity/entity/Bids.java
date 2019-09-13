package com.commodity.entity;

import java.util.Map;

public class Bids {

	private Commodity commodity;
	private Map<Seller ,Double> bids; //seller and the amount he bids for
	
	
	public Commodity getCommodity() {
		return commodity;
	}
	public void setCommodity(Commodity commodity) {
		this.commodity = commodity;
	}
	public Map<Seller, Double> getBids() {
		return bids;
	}
	public void setBids(Map<Seller, Double> bids) {
		this.bids = bids;
	}
	
	
	 
}
