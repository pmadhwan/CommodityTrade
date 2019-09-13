package com.commodity.vo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.commodity.commons.ContractState;
import com.commodity.commons.Grade;
import com.commodity.entity.Commodity;

@Document
public class SalesContract {
	
	@Id
	private String contractAddress;
	private String buyerName;
	private String sellerName;
	private Commodity commodity;
	private Grade grade;
	private long intendedDelievryDate;
	private String comments;
	private ContractState status;
	

	

	public Commodity getCommodity() {
		return commodity;
	}

	public void setCommodity(Commodity commodity) {
		this.commodity = commodity;
	}

	public ContractState getStatus() {
		return status;
	}

	public void setStatus(ContractState status) {
		this.status = status;
	}



	public String getContractAddress() {
		return contractAddress;
	}

	public void setContractAddress(String contractAddress) {
		this.contractAddress = contractAddress;
	}

	public String getBuyerName() {
		return buyerName;
	}

	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}

	public String getSellerName() {
		return sellerName;
	}

	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}

	

	public Grade getGrade() {
		return grade;
	}

	public void setGrade(Grade grade) {
		this.grade = grade;
	}

	public long getIntendedDelievryDate() {
		return intendedDelievryDate;
	}

	public void setIntendedDelievryDate(long l) {
		this.intendedDelievryDate = l;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	@Override
	public String toString() {
		return "SalesContract [contractAddress=" + contractAddress + ", buyerName=" + buyerName + ", sellerName="
				+ sellerName + ", commodity=" + commodity + ", grade=" + grade + ", intendedDelievryDate="
				+ intendedDelievryDate + ", comments=" + comments + ", status=" + status + "]";
	}

	
	

}
