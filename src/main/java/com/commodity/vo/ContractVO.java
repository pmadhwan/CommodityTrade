package com.commodity.vo;

public class ContractVO {

	private String contractAddress;
	private String txHash;
	
	

	
	public ContractVO(){
		
	}
	

	public ContractVO(String contractAddress, String txHash) {
		super();
		this.contractAddress = contractAddress;
		this.txHash = txHash;
	}


	public String getTxHash() {
		return txHash;
	}


	public void setTxHash(String txHash) {
		this.txHash = txHash;
	}


	@Override
	public String toString() {
		return "ContractVO [contractAddress=" + contractAddress + "]";
	}

	public String getContractAddress() {
		return contractAddress;
	}

	public void setContractAddress(String contractAddress) {
		this.contractAddress = contractAddress;
	}
	
}
