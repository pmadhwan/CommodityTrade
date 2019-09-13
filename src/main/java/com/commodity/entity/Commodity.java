package com.commodity.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Commodity {
	
	@Id
	private String id;
	private String commodityName;
	private Long weight;
	private Double quotePrice;
	private long selectedBidPrice;
	
	public Commodity() {
		
	}
	
	public Commodity(String id, String commodityName, Long weight, Double quotePrice) {
	
		this.id = id;
		this.commodityName = commodityName;
		this.weight = weight;
		this.quotePrice = quotePrice;
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
	public Double getQuotePrice() {
		return quotePrice;
	}
	public void setQuotePrice(Double quotePrice) {
		this.quotePrice = quotePrice;
	}
	
	
	public long getSelectedBidPrice() {
		return selectedBidPrice;
	}

	public void setSelectedBidPrice(long selectedBidPrice) {
		this.selectedBidPrice = selectedBidPrice;
	}

	@Override
	public String toString() {
		return "Commodity [id=" + id + ", commodityName=" + commodityName + ", weight=" + weight + ", quotePrice="
				+ quotePrice + "]";
	}
	
	

}
